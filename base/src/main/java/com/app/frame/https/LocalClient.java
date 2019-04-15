package com.app.frame.https;


import com.tencent.mmkv.MMKV;

public class LocalClient {

    private static volatile LocalClient localClient;
    private final MMKV mmkv;

    public static LocalClient getInstance() {
        if (localClient == null) {
            synchronized (LocalClient.class) {
                if (localClient == null) {
                    localClient = new LocalClient();
                }
            }
        }
        return localClient;
    }

    private LocalClient() {
        mmkv = MMKV.defaultMMKV();
    }

    public String getUserName(){
        return mmkv.decodeString("userName");
    }

    public void setUserName(String userName){
        mmkv.encode("userName", userName);
    }

    public String getPassword(){
        return mmkv.decodeString("password");
    }

    public void setPassword(String password){
        mmkv.encode("password", password);
    }
}
