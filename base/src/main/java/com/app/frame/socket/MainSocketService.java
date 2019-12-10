package com.app.frame.socket;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.app.frame.manager.AppManager;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Socket服务器
 */
public class MainSocketService implements Runnable {

    private AsyncSocket asyncSocket = null;
    private static final Charset GBK = Charset.forName("GBK");
    //    private static final String host = "125.26.107.18";
//    private static final int port = 90;
    private static final String host = "192.168.0.85";
    private static final int port = 18214;

    private static MainSocketService single = null;
    private ByteBufferList bbl = new ByteBufferList();

    private final int ONE_SECOND = 1000;
    private final int TIME_CONNECT_FAILED_DELAY = 2;
    private final int TIME_CONNECT_SUCCESS_DELAY = 30;

    private int reConnectCount = 0;
    private boolean isConnected = false;
    private boolean isClosedSocket = false;
    private int closedSocketCount = 0;

    private static final int CONNECT_SUCCESS = 0;
    private static final int CONNECT_FAILED = 1;

    private static final String RN = System.getProperty("line.separator", "\n");


    private Handler mHandler = new Handler(AppManager.getAppManager().getApp().getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CONNECT_SUCCESS:
                    sendConnectInfo();
                    break;
                case CONNECT_FAILED:
                    if (reConnectCount > 4 || closedSocketCount > 3) {
                        maximumNumberOfConnections();
                    } else {
                        closeSocket();
                        try {
                            connect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };

    private MainSocketService() {
        mHandler.postDelayed(this, ONE_SECOND);
    }


    //Socket 重连机制
    @Override
    public void run() {
        if (isClosedSocket) return;
        mHandler.postDelayed(this, ONE_SECOND);
        reConnectCount++;
        if (isConnected) {
            if (reConnectCount > TIME_CONNECT_SUCCESS_DELAY) {
                reConnectCount = 0;
                sendMessage();
            }
        } else {
            if (reConnectCount % TIME_CONNECT_FAILED_DELAY == 0) {
                sendFailedMessage();
            }
        }
    }


    private void sendMessage() {
        Message msg = mHandler.obtainMessage();
        msg.what = MainSocketService.CONNECT_SUCCESS;
        mHandler.sendMessage(msg);
    }

    //延时俩秒
    private void sendFailedMessage() {
        Message msg = mHandler.obtainMessage();
        msg.what = MainSocketService.CONNECT_FAILED;
        mHandler.sendMessageDelayed(msg, 2000);
    }

    //发送连接成功确认信息
    private void sendConnectInfo() {
        if (isConnected) {
            writeString("keep_connect");
        } else {
            writeString("request_connect");
        }
    }


    public static MainSocketService getInstance() {
        if (single == null) {
            single = new MainSocketService();
        }
        return single;
    }

    /**
     * 连接 Socket
     */
    public void connect() {
        this.isClosedSocket = false;
        if (TextUtils.isEmpty(host)) {
            return;
        }

        reConnectCount++;
        closedSocketCount++;
        if (reConnectCount > 4 || closedSocketCount > 3) {
            maximumNumberOfConnections();
        }

        try {
            AsyncServer.getDefault().connectSocket(new InetSocketAddress(host, port), (ex, socket) -> {
                if (ex != null) {
                    isClosedSocket = false;
                    return;
                }
                if (socket == null) {
                    isClosedSocket = false;
                    if (reConnectCount > 4 || closedSocketCount > 3) {
                        maximumNumberOfConnections();
                    } else {
                        closeSocket();
                        try {
                            connect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                asyncSocket = socket;
                onConnectCompletedCallBack(socket);
                sendConnectInfo();
            });
        } catch (Exception e) {
            maximumNumberOfConnections();
        }
    }

    /**
     * 连接成功后回调
     */
    private void onConnectCompletedCallBack(final AsyncSocket socket) {
        socket.setDataCallback((emitter, bb) -> {
            if (bb == null) {
                return;
            }
            bbl.add(bb);
            bb.recycle();
            if (emitter.isChunked()) {
                return;
            }
            String jsonStr = bbl.readString(GBK);

            if (TextUtils.isEmpty(jsonStr)) {
                return;
            }
            String[] jsonArray = jsonStr.split(RN);
            Set<String> setJson = new LinkedHashSet<>();
            Collections.addAll(setJson, jsonArray);

            for (String msg : setJson) {

                bbl.recycle();
            }
        });

        socket.setClosedCallback(ex -> {
            isConnected = false;
            reConnectCount = 0;
            closedSocketCount++;
        });

        socket.setEndCallback(ex -> {
            isConnected = false;
            reConnectCount = 0;
            closedSocketCount++;
        });
    }


    @SuppressWarnings("all")
    public boolean writeString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (asyncSocket == null) {
            return false;
        }
        ByteBufferList bbl = new ByteBufferList();
        try {
            bbl.add(ByteBuffer.wrap(str.getBytes("UTF-8")));
            asyncSocket.write(bbl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return isConnected;
    }

    /**
     * Close Socket
     */
    private void closeSocket() {
        if (asyncSocket == null) {
            return;
        }
        asyncSocket.close();
        asyncSocket = null;
    }

    /**
     * 关闭Socket后，释放资源
     */
    public void release(boolean flag) {
        if (mHandler != null) {
            mHandler.removeCallbacks(null);
        }
        closeSocket();
        single = null;
        isClosedSocket = flag;
    }

    /**
     * 判断是否超过重连限制
     */
    private void maximumNumberOfConnections() {
        reConnectCount = 0;
        release(true);
    }

    private OnDataCallbackListener onDataCallbackListener; //Socket 接收数据监听器
    private OnCloseCallbackListener onCloseCallbackListener; //Socket Closed 监听器
    private OnEndCallbackListener onEndCallbackListener; //Socket End 监听器

    public interface OnDataCallbackListener {
        void onDataCallBack(String jsonStr);
    }

    public interface OnCloseCallbackListener {
        void onCloseCallBack();
    }

    public interface OnEndCallbackListener {
        void onEndCallBack();
    }

    public void setOnDataCallbackListener(OnDataCallbackListener onDataCallbackListener) {
        this.onDataCallbackListener = onDataCallbackListener;
    }
}
