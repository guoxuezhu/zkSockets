package com.lh.zksockets.service;

import com.google.gson.Gson;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpRequestUtil;

public class NIOHttpServer implements HttpServerRequestCallback {

    private static NIOHttpServer mInstance;

    private static int PORT_LISTEN_DEFALT = 8099;
    private String LHKEY_TOKEN = "";
    private Long LH_TOKEN_TIME;

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

        ELog.i("===========111111=========" + request.getPath());
        ELog.i("===========2222=========" + request.getQuery());
        ELog.i("===========3333=========" + request.getMethod());

        if (request.getPath().equals("/api/lh_zk_login")) {
            String loginret = HttpRequestUtil.getLoginToken(request);
            if (loginret.equals("-1001")) {
                response.send(new Gson().toJson(new HttpResult("-1001", "data exception", false, null)));
            } else if (loginret.equals("-1002")) {
                response.send(new Gson().toJson(new HttpResult("-1002", "帐号错误", false, null)));
            } else if (loginret.equals("-1003")) {
                response.send(new Gson().toJson(new HttpResult("-1003", "密码错误", false, null)));
            } else if (loginret.equals("-1005")) {
                response.send(new Gson().toJson(new HttpResult("-1005", "密码错误,1次后此帐号锁定", false, null)));
            } else if (loginret.equals("-1006")) {
                response.send(new Gson().toJson(new HttpResult("-1006", "密码错误,2次后此帐号锁定", false, null)));
            } else if (loginret.equals("-1004")) {
                response.send(new Gson().toJson(new HttpResult("-1004", "此帐号已锁定", false, null)));
            } else {
                LHKEY_TOKEN = HttpRequestUtil.getLoginToken(request);
                LH_TOKEN_TIME = System.currentTimeMillis() + 1 * 1000 * 60 * 30;
                response.send(new Gson().toJson(new HttpResult("200", "登录成功", true, LHKEY_TOKEN)));
            }
        } else {
            String usertoken = "";
            if (request.getMethod().equals("GET")) {
                usertoken = request.getQuery().getString("lh_zks_token");
            } else if (request.getMethod().equals("POST")) {
                Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
                usertoken = parms.getString("lh_zks_token");
            }
            if (usertoken != null && usertoken.equals(LHKEY_TOKEN)) {
                long time = LH_TOKEN_TIME - System.currentTimeMillis();
                if (time > 0) {
                    LH_TOKEN_TIME = System.currentTimeMillis() + 1 * 1000 * 60 * 30;
                    try {
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
//                        } else if (request.getPath().equals("/api/updataMqttInfo")) {
//                            response.send(HttpRequestUtil.updataMqttInfo(request));
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
                        } else if (request.getPath().equals("/api/updataMsg")) {
                            response.send(HttpRequestUtil.updataMsg(request));
                        } else if (request.getPath().equals("/api/updataWgkzqInfo")) {
                            response.send(HttpRequestUtil.updataWgkzqInfo(request));
                        } else if (request.getPath().equals("/api/sportInfo")) {
                            response.send(HttpRequestUtil.getSportInfo(request));
                        } else if (request.getPath().equals("/api/dangerInfo")) {
                            response.send(HttpRequestUtil.getDangerInfo(request));
                        } else if (request.getPath().equals("/api/eventList")) {
                            response.send(HttpRequestUtil.getEventList(request));
                        } else if (request.getPath().equals("/api/jdqInfo")) {
                            response.send(HttpRequestUtil.getJDQList(request));
                        } else if (request.getPath().equals("/api/luboInfo")) {
                            response.send(HttpRequestUtil.getLuboList(request));
//                        } else if (request.getPath().equals("/api/mqttInfo")) {
//                            response.send(HttpRequestUtil.getMqttinfo(request));
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
                        } else if (request.getPath().equals("/api/wgkzqInfo")) {
                            response.send(HttpRequestUtil.getWgkzqInfo(request));
                        } else {
                            response.send("sys Exception");
                        }
                    } catch (Exception e) {
                        response.send(new Gson().toJson(new HttpResult("-200", "数据异常或不能为空", false, null)));
                    }
                } else {
                    response.send(new Gson().toJson(new HttpResult("-1201", "连接超时,请重新登录", false, null)));
                }
            } else {
                response.send(new Gson().toJson(new HttpResult("-1200", "请重新登录", false, null)));
            }
        }


    }
}

