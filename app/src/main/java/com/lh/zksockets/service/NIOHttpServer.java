package com.lh.zksockets.service;

import android.util.Log;

import com.google.gson.Gson;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.lh.zksockets.data.model.HttpResult;
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
        Log.d(TAG, "=======3333=========" + request.getMethod());

        try {
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
                } else if (request.getPath().equals("/api/updataMqttInfo")) {
                    response.send(HttpRequestUtil.updataMqttInfo(request));
                } else if (request.getPath().equals("/api/updataZKbaseInfo")) {
                    response.send(HttpRequestUtil.updataZkBaseInfo(request));
                } else if (request.getPath().equals("/api/updataIoOutInfo")) {
                    response.send(HttpRequestUtil.updataIoOutInfo(request));
                } else if (request.getPath().equals("/api/updataDangerOutInfo")) {
                    response.send(HttpRequestUtil.updataDangerOutInfo(request));
                } else if (request.getPath().equals("/api/updataDoorInfo")) {
                    response.send(HttpRequestUtil.updataDoorInfo(request));
                } else if (request.getPath().equals("/api/updataRebootTime")) {
                    response.send(HttpRequestUtil.updataRebootTime(request));
                } else if (request.getPath().equals("/api/zkczbtn")) {
                    response.send(HttpRequestUtil.zksendmsg(request));
                } else {
                    response.send("200");
                }

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
                } else if (request.getPath().equals("/api/mqttInfo")) {
                    response.send(HttpRequestUtil.getMqttinfo(request));
                } else if (request.getPath().equals("/api/zkBaseInfo")) {
                    response.send(HttpRequestUtil.getZkBaseInfo(request));
                } else if (request.getPath().equals("/api/iooutInfo")) {
                    response.send(HttpRequestUtil.getIoOutinfo(request));
                } else if (request.getPath().equals("/api/dangerOutInfo")) {
                    response.send(HttpRequestUtil.getDangerOutInfo(request));
                } else if (request.getPath().equals("/api/doorSeting")) {
                    response.send(HttpRequestUtil.getDoorInfo(request));
                } else if (request.getPath().equals("/api/rebootTime")) {
                    response.send(HttpRequestUtil.getRebootTime(request));
                } else if (request.getPath().equals("/api/wsddata")) {
                    response.send(HttpRequestUtil.getWSD(request));
                } else if (request.getPath().substring(0, 5).equals("/wsd/")) {
                    response.send(HttpRequestUtil.setWsdpm(request));
                } else if (request.getPath().equals("/api/deviceStatus")) {
                    response.send(HttpRequestUtil.getDeviceStatus(request));
                } else {
                    response.send("200");
                }

            }

        } catch (Exception e) {
            response.send(new Gson().toJson(new HttpResult("-200", "数据异常或不能为空", false, null)));
        }

    }
}

