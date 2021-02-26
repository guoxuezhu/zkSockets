package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.WgDeviceInfoDao;
import com.lh.zksockets.data.model.WgDeviceInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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
                    WgDeviceInfoDao wgDeviceInfoDao = MyApplication.getDaoSession().getWgDeviceInfoDao();
                    wgDeviceInfoDao.deleteAll();
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
                                JSONObject jsonObject = new JSONObject(readMsgdata);
                                int code = jsonObject.getInt("code");
                                JSONArray devices = jsonObject.getJSONArray("devices");
                                if (code == 101 && devices != null) {
                                    ELog.e("======TcpSocketUtil==心跳数据====" + devices);
                                    String Lstatus1 = "0";
                                    String Lstatus2 = "0";
                                    String Mstatus1 = "0";
                                    String Mstatus2 = "0";
                                    for (int i = 0; i < devices.length(); i++) {
                                        JSONObject deviceJson = new JSONObject(devices.getString(i));
                                        List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                                                .where(WgDeviceInfoDao.Properties.Name.eq(deviceJson.getString("name")), WgDeviceInfoDao.Properties.StringId.eq(deviceJson.getString("id")))
                                                .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                                                .list();
                                        if (deviceJson.getString("name").contains("L")) {//调光灯
                                            //{"id":"588e81fffed9a398","port":11,"agreement":49246,"attribute":{"on":true,"bri":59,"ctp":500,"sat":0,"hue":0},"online":true,"deviceid":66,"name":"调光灯a398"}
                                            if (wgDeviceInfos.size() == 0) {
                                                wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"), deviceJson.getString("id"),
                                                        deviceJson.getString("name"), deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                        new JSONObject(deviceJson.getString("attribute")).getInt("bri"), 0,
                                                        new JSONObject(deviceJson.getString("attribute")).getBoolean("on"),
                                                        "", 0, 0, 0, 0, 0, 0, 0, 0));
                                            } else {
                                                wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"), deviceJson.getString("id"),
                                                        deviceJson.getString("name"), deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                        new JSONObject(deviceJson.getString("attribute")).getInt("bri"), 0,
                                                        new JSONObject(deviceJson.getString("attribute")).getBoolean("on"), "", 0,
                                                        0, 0, 0, 0, 0, 0, 0));
                                            }
                                            if (deviceJson.getString("name").equals("L1")) {
                                                if (new JSONObject(deviceJson.getString("attribute")).getBoolean("on")) {
                                                    Lstatus1 = "1";
                                                } else {
                                                    Lstatus1 = "0";
                                                }
                                            } else {
                                                if (new JSONObject(deviceJson.getString("attribute")).getBoolean("on")) {
                                                    Lstatus2 = "1";
                                                } else {
                                                    Lstatus2 = "0";
                                                }
                                            }
                                        } else if (deviceJson.getString("name").contains("M")) {//窗帘
                                            if (wgDeviceInfos.size() == 0) {
                                                wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                        deviceJson.getString("id"), deviceJson.getString("name"),
                                                        deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004, 0, 0, false, "",
                                                        new JSONObject(deviceJson.getString("attribute")).getInt("pt"),
                                                        0, 0, 0, 0, 0, 0, 0));
                                            } else {
                                                wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                        deviceJson.getString("id"), deviceJson.getString("name"),
                                                        deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004, 0, 0, false, "",
                                                        new JSONObject(deviceJson.getString("attribute")).getInt("pt"),
                                                        0, 0, 0, 0, 0, 0, 0));
                                            }
                                            if (deviceJson.getString("name").equals("M1")) {
                                                if (new JSONObject(deviceJson.getString("attribute")).getInt("pt") != 0) {
                                                    Mstatus1 = "1";
                                                } else {
                                                    Mstatus1 = "0";
                                                }
                                            } else {
                                                if (new JSONObject(deviceJson.getString("attribute")).getInt("pt") != 0) {
                                                    Mstatus2 = "1";
                                                } else {
                                                    Mstatus2 = "0";
                                                }
                                            }
                                        } else if (deviceJson.getString("name").contains("K")) {//智能插座
                                            if (wgDeviceInfos.size() == 0) {
                                                try {
                                                    wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getBoolean("on"), "", 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("current"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("voltage"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("energy"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("power"),
                                                            0, 0, 0));
                                                } catch (Exception e) {
                                                    wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getBoolean("on"),
                                                            "", 0, 0, 0, 0, 0, 0, 0, 0));
                                                }
                                            } else {
                                                try {
                                                    wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, new JSONObject(deviceJson.getString("attribute")).getBoolean("on"), "", 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("current"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("voltage"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("energy"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("power"),
                                                            0, 0, 0));
                                                } catch (Exception e) {
                                                    wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getBoolean("on"),
                                                            "", 0, 0, 0, 0, 0, 0, 0, 0));
                                                }
                                            }
                                        } else if (deviceJson.getString("name").contains("T")) {//多合一传感器
                                            if (wgDeviceInfos.size() == 0) {
                                                try {
                                                    wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mtemp"),
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mhumi"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("pm2.5")));
                                                } catch (Exception e) {
                                                    wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mtemp"),
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mhumi"),
                                                            0));
                                                }
                                            } else {
                                                try {
                                                    wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mtemp"),
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mhumi"),
                                                            new JSONObject(deviceJson.getString("attribute")).getInt("pm2.5")));
                                                } catch (Exception e) {
                                                    wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mtemp"),
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mhumi"),
                                                            0));
                                                }
                                            }
                                        } else if (deviceJson.getString("name").contains("S")) {
                                            if (wgDeviceInfos.size() == 0) {
                                                try {
                                                    wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mtemp"),
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mhumi"),
                                                            0));
                                                } catch (Exception e) {
                                                    wgDeviceInfoDao.insert(new WgDeviceInfo(null, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0, 0, 0, 0));
                                                }
                                            } else {
                                                try {
                                                    wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0,
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mtemp"),
                                                            new JSONObject(deviceJson.getString("attribute")).getDouble("mhumi"),
                                                            0));
                                                } catch (Exception e) {
                                                    wgDeviceInfoDao.update(new WgDeviceInfo(wgDeviceInfos.get(0).longId, deviceJson.getInt("deviceid"),
                                                            deviceJson.getString("id"), deviceJson.getString("name"),
                                                            deviceJson.getInt("port"), deviceJson.getInt("agreement"), 12004,
                                                            0, 0, false, "", 0, 0, 0, 0, 0, 0, 0, 0));
                                                }
                                            }
                                        } else if (deviceJson.getString("name").contains("遥控器")) {

                                        }
                                    }
                                    String statusStr = "TST;" + Lstatus1 + ";" + Lstatus2 + ";" + Mstatus1 + ";" + Mstatus2 + "";
                                    SerialPortUtil.sendMsg1(statusStr.getBytes());
                                }
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

}
