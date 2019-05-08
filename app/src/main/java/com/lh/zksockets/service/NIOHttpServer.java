package com.lh.zksockets.service;

import android.util.Log;

import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.lh.zksockets.utils.HttpRequestUtil;

public class NIOHttpServer implements HttpServerRequestCallback {

    private static final String TAG = "NIOHttpServer====";

    private static NIOHttpServer mInstance;

    public static int PORT_LISTEN_DEFALT = 8099;

    AsyncHttpServer server = new AsyncHttpServer();

    public static NIOHttpServer getInstance() {
        if (mInstance == null) {
            // 增加类锁,保证只初始化一次
            synchronized (NIOHttpServer.class) {
                if (mInstance == null) {
                    mInstance = new NIOHttpServer();
                }
            }
        }
        return mInstance;
    }

    /**
     * 开启本地服务
     */
    public void startServer() {
        //如果有其他的请求方式，例如下面一行代码的写法
        server.addAction("OPTIONS", "[\\d\\D]*", this);
        server.get("[\\d\\D]*", this);
        server.post("[\\d\\D]*", this);
        server.listen(PORT_LISTEN_DEFALT);
    }

    @Override
    public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {

        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST");

        Log.d(TAG, "=======111111=========" + request.getPath());
        Log.d(TAG, "=======2222=========" + request.getQuery());

        if (request.getMethod().equals("POST")) {

            if (request.getPath().equals("/api/updataSportInfo")) {
                response.send(HttpRequestUtil.updataSportInfo(request));
            } else if (request.getPath().equals("/api/updataDangerInfo")) {
                response.send(HttpRequestUtil.updataDangerInfo(request));
            } else if (request.getPath().equals("/api/updataEventInfo")) {
                response.send(HttpRequestUtil.updataEventInfo(request));
            } else if (request.getPath().equals("/api/updataJdqInfo")) {
                response.send(HttpRequestUtil.updataJdqInfo(request));
            } else if (request.getPath().equals("/api/updataLuboInfo")) {
                response.send(HttpRequestUtil.updataLuboInfo(request));
            } else {
                response.send("200");
            }

//            Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
//            Log.d(TAG, "=======parms========= " + parms.toString() + "===zkbtn=== " + parms.getString("zkbtn"));
//
//            String msg = parms.getString("zkbtn");
//
//            if (msg.length() > 3) {
//                if (msg.substring(0, 3).equals("VID")) {
//                    SerialPortUtil.sendShipinType(msg);
//                } else if (msg.substring(0, 3).equals("LUB")) {
//                    HttpUtil.setlubo(msg);
//                } else if (msg.substring(0, 3).equals("MBS")) {
//                    try {
//                        SerialPortUtil.makeML(Long.valueOf(msg.substring(3)));
//                    } catch (Exception e) {
//                        Log.d(TAG, "==========http===POST===接收到了数据====Long.valueOf==异常========" + e.toString());
//                    }
//                }
//            }

        } else if (request.getMethod().equals("GET")) {

            if (request.getPath().equals("/api/sportInfo")) {
                response.send(HttpRequestUtil.getSportInfo(request));
            } else if (request.getPath().equals("/api/dangerInfo")) {
                response.send(HttpRequestUtil.getDangerInfo(request));
            } else if (request.getPath().equals("/api/eventList")) {
                response.send(HttpRequestUtil.getEventList(request));
            } else if (request.getPath().equals("/api/jdqInfo")) {
                response.send(HttpRequestUtil.getJDQList(request));
            } else if (request.getPath().equals("/api/luboInfo")) {
                response.send(HttpRequestUtil.getLuboList(request));
            } else if (request.getPath().substring(0, 5).equals("/wsd/")) {
                response.send(HttpRequestUtil.setWsdpm(request));
            } else {
                response.send("200");
            }

        }


    }
}

