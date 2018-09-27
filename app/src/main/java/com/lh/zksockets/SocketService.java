package com.lh.zksockets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.WorksUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService extends Service {

    private ServerSocket server;
    private Socket socket;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ELog.d("======onCreate====");
        try {
            server = new ServerSocket(8089);
            beginListen();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //电源箱操作
        WorksUtil.powerWorkOpen();



    }

    //https://blog.csdn.net/zhangqufa/article/details/69228949
    private void beginListen() {
        new Thread() {
            @Override
            public void run() {
                try {
                    socket = server.accept();
                    InputStream in = socket.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    BufferedReader bufReader = new BufferedReader(reader);
                    String s = null;

                    while ((s = bufReader.readLine()) != null) {
                        ELog.d("======msg====" + s);
                        if(s.equals("000000")){
                            //设备
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    ELog.e("====server.accept()====IOException====" + e);
                }

            }
        }.start();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ELog.d("====onDestroy====");
        try {
            if (server != null) {
                server.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
