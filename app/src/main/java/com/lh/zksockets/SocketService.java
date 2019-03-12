package com.lh.zksockets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.SerialPortUtil;
import com.lh.zksockets.utils.WorksUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketService extends Service {

    private ServerSocket serverSocket;
    private Socket socket;
    private SocketReadThread socketReadThread;
    private boolean isRunReadThread = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ELog.d("======onCreate====");

//        startTimer();
//
//        SerialPortUtil.open();

        //电源箱操作
        // WorksUtil.powerWorkOpen();


    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (DisplayTools.isNetworkConnected(SocketService.this)) {
                    if (serverSocket == null || serverSocket.isClosed()) {
                        createServerSocket();
                    } else {
                        if (socket == null || socket.isClosed()) {
                            ELog.d("=======心跳=========11111=======socket=====");
                            if (!isRunReadThread && socketReadThread == null) {
                                isRunReadThread = true;
                                socketReadThread = new SocketReadThread();
                                ELog.d("=======心跳=======4444444444========socket=====");
                                socketReadThread.start();
                            }
                        }
                    }
                } else {
                    stopMySocket();
                }

            }
        }, 1, 5000);
    }

    private class SocketReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                createSocket();
                if (socket != null) {
                    InputStream in = socket.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    BufferedReader bufReader = new BufferedReader(reader);
                    String s = null;
                    while ((s = bufReader.readLine()) != null) {
                        ELog.d("======msg====" + s);
                        if (s.equals("上课")) {
                            //设备
                            sdfsdf();
                        } else {
                            sendMsgClient();
                        }
                    }
                    in.close();
                    reader.close();
                    bufReader.close();
                }
                stopMySocket();
            } catch (IOException e) {
                e.printStackTrace();
                ELog.e("====SocketReadThread====IOException====" + e);
                stopMySocket();
            } catch (Exception e) {
                e.printStackTrace();
                ELog.e("====SocketReadThread====Exception====" + e);
                stopMySocket();
            }
        }
    }

    private void sendMsgClient() {
        LampDao lampDao = MyApplication.getDaoSession().getLampDao();
        Gson gson = new Gson();
        String lampStr = gson.toJson(lampDao.loadAll());
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream);
            out.print(lampStr + "\n");
            out.flush();
            ELog.d("=======sendMsgClient===1111=====" + lampStr);
        } catch (IOException e) {
            e.printStackTrace();
            ELog.d("=======sendMsgClient===IOException=====");
        }
    }

    private void sdfsdf() {
        SerialPortUtil.sendMsg(1, "上课");
    }


    private void createSocket() {
        try {
            ELog.d("=======心跳===1111===createSocket=====");
            socket = serverSocket.accept();
            ELog.d("=======心跳===2222===createSocket=====" + socket);
        } catch (IOException e) {
            e.printStackTrace();
            ELog.e("=======心跳======createSocket==IOException===" + e);
        }

    }


    private void createServerSocket() {
        try {
            serverSocket = new ServerSocket(8089);
            ELog.d("=======心跳======createServerSocket=====");
        } catch (IOException e) {
            ELog.e("=======心跳====createServerSocket==eeeeeeeeee========");
            e.printStackTrace();
        }
    }

    private void stopMySocket() {
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            ELog.e("======stopMySocket=======IOException============" + e);
        }

        if (socketReadThread != null) {
            socketReadThread.interrupt();
            socketReadThread = null;
        }
        isRunReadThread = false;
        ELog.d("======stopMySocket====");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ELog.d("====onDestroy====");
        stopMySocket();
    }


}
