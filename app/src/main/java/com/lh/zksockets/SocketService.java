package com.lh.zksockets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

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
        Log.d("zklh", "=====onCreate====");
        try {
            server = new ServerSocket(8089);
            beginListen();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                        Log.d("zklh", "=====msg====" + s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("zklh", "=========server.accept()===========IOException====" + e);
                }

            }
        }.start();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("zklh", "=====onDestroy====");
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
