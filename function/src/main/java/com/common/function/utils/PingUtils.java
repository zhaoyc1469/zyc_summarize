package com.common.function.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingUtils {

    /**
     * 判断Ping 网址是否返回成功
     */
    public static String isPingSuccess(int pingNum, String ip) {
        if (ip == null || ip.length() <= 0) {
            ip = "223.5.5.5";// 阿里巴巴公共ip
        }

        StringBuilder tv_PingInfo = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        Process ipProcess = null;
        try {
            //-c 后边跟随的是重复的次数，-w后边跟随的是超时的时间，单位是秒，不是毫秒，要不然也不会anr了
            ipProcess = runtime.exec("ping -c " + pingNum + " -w 3 " + ip);

            int status = ipProcess.waitFor();
            if (status == 0) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(ipProcess.getInputStream()));
                String str;
                while ((str = buf.readLine()) != null) {
                    str = str + "\r\n";
                    tv_PingInfo.append(str);
                }
            }
            return tv_PingInfo.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            //在结束的时候应该对资源进行回收
            if (ipProcess != null) {
                ipProcess.destroy();
            }
            runtime.gc();
        }
        return "";
    }
}
