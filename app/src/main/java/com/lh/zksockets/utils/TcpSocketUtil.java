package com.lh.zksockets.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketUtil {


    private static ServerSocket serverSocket;
    private static Socket socket;
    private static boolean isRead = false;

    public static void startTcpServer() {
        closeSocket();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (serverSocket == null) {
                        serverSocket = new ServerSocket(12136);
                    }
                    socket = serverSocket.accept();
                    ELog.d("=======TcpSocketUtil====1=======");
                    InputStream inputStream = socket.getInputStream();//得到一个输入流，接收客户端传递的信息
                    byte[] buffer = new byte[1024 * 6];
                    isRead = true;
                    while (isRead) {
                        try {
                            int len = inputStream.read(buffer);
                            ELog.d("=======TcpSocketUtil=====len==" + len);
                            if (len > 0) {
                                String readMsgdata = new String(buffer, 0, len);
                                ELog.d("=======TcpSocketUtil=====服务端接收到客户端信息==" + readMsgdata);
                                sendData("{\"code\":1001,\"result\":0,\"timestamp\":" + System.currentTimeMillis() + "}");
//                                DeviceStatusUtil.sendService(readMsgdata);
                            } else {
                                isRead = false;
                            }
                        } catch (Exception e) {
                            ELog.e("======TcpSocketUtil===isRead=Exception====");
                            e.printStackTrace();
                        }
                    }
                    ELog.i("======TcpSocketUtil==断开重新启动====");
                } catch (Exception e) {
                    ELog.e("======TcpSocketUtil====Exception====");
                    e.printStackTrace();
                } finally {
                    ELog.e("======TcpSocketUtil====finally====");
                    startTcpServer();
                }
            }
        }.start();

    }

    private static void closeSocket() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                ELog.i("=====TcpSocketUtil=======closeSocket=====Exception");
                e.printStackTrace();
            }
            socket = null;
        }
    }

    public static void sendData(String msgdata) {
        if (socket == null) {
            ELog.i("=====TcpSocketUtil==========请先连接设备");
            return;
        }
        ELog.i("=====TcpSocketUtil====msgdata===" + msgdata);
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(msgdata.getBytes());
            outputStream.flush();
            ELog.i("=====TcpSocketUtil============发送数据成功");
        } catch (Exception e) {
            ELog.i("=====TcpSocketUtil============发送数据Exception");
            e.printStackTrace();
            startTcpServer();
        }
    }

    public static void doHKWangguan(String ml) {
        // 5-1-1 5-10-1
        String[] mls = ml.split("-");
        if (mls.length != 3) {
            return;
        }
        if (mls[2].equals("1")) {
            String msg = "{\"code\":3003,\"id\":" + mls[1] + ",\"serial\":12004}";
            TcpSocketUtil.sendData(msg);
        }
    }

}
