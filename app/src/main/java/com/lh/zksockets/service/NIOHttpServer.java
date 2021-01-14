package com.lh.zksockets.service;

import com.google.gson.Gson;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpRequestUtil;

import java.util.List;

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

        if (request.getPath().equals("/api/lh_zk_login")) {
            String loginret = HttpRequestUtil.getLoginToken((Multimap) request.getBody().get());
            if (loginret.equals("-1001")) {
                response.send(new Gson().toJson(new HttpResult("-1001", "帐号或者密码存在问题", false, null)));
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
                LHKEY_TOKEN = loginret;
                LH_TOKEN_TIME = System.currentTimeMillis() + 1 * 1000 * 60 * 15;
                response.send(new Gson().toJson(new HttpResult("200", "连接成功", true, LHKEY_TOKEN)));
            }
        } else {
            String userName = "";
            String usertoken = "";
            Multimap parms = null;
            if (request.getMethod().equals("GET")) {
                parms = request.getQuery();
                usertoken = parms.getString("lh_zks_token");
                userName = parms.getString("user_name");
            } else if (request.getMethod().equals("POST")) {
                parms = (Multimap) request.getBody().get();
                usertoken = parms.getString("lh_zks_token");
                userName = parms.getString("user_name");
            }
            UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
            List<Users> users = usersDao.queryBuilder()
                    .where(UsersDao.Properties.Username.eq(userName))
                    .list();
            if (users.size() != 0) {
                if (users.get(0).getUser_status() == 0) {
                    response.send(new Gson().toJson(new HttpResult("-1200", "请重新连接", false, null)));
                } else {
                    if (usertoken != null && usertoken.equals(LHKEY_TOKEN)) {
                        long time = LH_TOKEN_TIME - System.currentTimeMillis();
                        if (time > 0) {
                            LH_TOKEN_TIME = System.currentTimeMillis() + 1 * 1000 * 60 * 30;
                            try {
                                if (request.getPath().equals("/api/updataSportInfo")) {
                                    response.send(HttpRequestUtil.updataSportInfo(parms));
                                } else if (request.getPath().equals("/api/updataDangerInfo")) {
                                    response.send(HttpRequestUtil.updataDangerInfo(parms));
                                } else if (request.getPath().equals("/api/updataEventInfo")) {
                                    response.send(HttpRequestUtil.updataEventInfo(parms));
                                } else if (request.getPath().equals("/api/updataJdqInfo")) {
                                    response.send(HttpRequestUtil.updataJdqInfo(parms));
                                } else if (request.getPath().equals("/api/updataLuboInfo")) {
                                    response.send(HttpRequestUtil.updataLuboInfo(parms));
//                        } else if (request.getPath().equals("/api/updataMqttInfo")) {
//                            response.send(HttpRequestUtil.updataMqttInfo(parms));
                                } else if (request.getPath().equals("/api/updataZKbaseInfo")) {
                                    response.send(HttpRequestUtil.updataZkBaseInfo(parms));
                                } else if (request.getPath().equals("/api/updataIoOutInfo")) {
                                    response.send(HttpRequestUtil.updataIoOutInfo(parms));
                                } else if (request.getPath().equals("/api/updataDangerOutInfo")) {
                                    response.send(HttpRequestUtil.updataDangerOutInfo(parms));
                                } else if (request.getPath().equals("/api/updataDoorInfo")) {
                                    response.send(HttpRequestUtil.updataDoorInfo(parms));
                                } else if (request.getPath().equals("/api/updataRebootTime")) {
                                    response.send(HttpRequestUtil.updataRebootTime(parms));
                                } else if (request.getPath().equals("/api/zkczbtn")) {
                                    response.send(HttpRequestUtil.zksendmsg(parms));
                                } else if (request.getPath().equals("/api/updataMsg")) {
                                    response.send(HttpRequestUtil.updataMsg(parms));
                                } else if (request.getPath().equals("/api/updataWgkzqInfo")) {
                                    response.send(HttpRequestUtil.updataWgkzqInfo(parms));
                                } else if (request.getPath().equals("/api/sportInfo")) {
                                    response.send(HttpRequestUtil.getSportInfo(parms));
                                } else if (request.getPath().equals("/api/dangerInfo")) {
                                    response.send(HttpRequestUtil.getDangerInfo(parms));
                                } else if (request.getPath().equals("/api/eventList")) {
                                    response.send(HttpRequestUtil.getEventList(parms));
                                } else if (request.getPath().equals("/api/jdqInfo")) {
                                    response.send(HttpRequestUtil.getJDQList(parms));
                                } else if (request.getPath().equals("/api/luboInfo")) {
                                    response.send(HttpRequestUtil.getLuboList(parms));
//                        } else if (request.getPath().equals("/api/mqttInfo")) {
//                            response.send(HttpRequestUtil.getMqttinfo(parms));
                                } else if (request.getPath().equals("/api/zkBaseInfo")) {
                                    response.send(HttpRequestUtil.getZkBaseInfo(parms));
                                } else if (request.getPath().equals("/api/iooutInfo")) {
                                    response.send(HttpRequestUtil.getIoOutinfo(parms));
                                } else if (request.getPath().equals("/api/dangerOutInfo")) {
                                    response.send(HttpRequestUtil.getDangerOutInfo(parms));
                                } else if (request.getPath().equals("/api/doorSeting")) {
                                    response.send(HttpRequestUtil.getDoorInfo(parms));
                                } else if (request.getPath().equals("/api/rebootTime")) {
                                    response.send(HttpRequestUtil.getRebootTime(parms));
                                } else if (request.getPath().equals("/api/wsddata")) {
                                    response.send(HttpRequestUtil.getWSD(parms));
//                        } else if (request.getPath().substring(0, 5).equals("/wsd/")) {
//                            response.send(HttpRequestUtil.setWsdpm(parms));
                                } else if (request.getPath().equals("/api/deviceStatus")) {
                                    response.send(HttpRequestUtil.getDeviceStatus(parms));
                                } else if (request.getPath().equals("/api/wgkzqInfo")) {
                                    response.send(HttpRequestUtil.getWgkzqInfo(parms));
                                } else if (request.getPath().equals("/api/ic_list_data")) {
                                    response.send(HttpRequestUtil.getIcdataLists(parms));
                                } else if (request.getPath().equals("/api/delete_ic_data")) {
                                    response.send(HttpRequestUtil.deleteIcData(parms));
                                } else if (request.getPath().equals("/api/add_ic_data")) {
                                    response.send(HttpRequestUtil.addIcData(parms));
                                } else if (request.getPath().equals("/api/delete_user_admin")) {
                                    response.send(HttpRequestUtil.deleteUseradmin(parms));
                                } else if (request.getPath().equals("/api/add_user_admin")) {
                                    response.send(HttpRequestUtil.addUseradmin(parms));
                                } else if (request.getPath().equals("/api/user_list_admin")) {
                                    response.send(HttpRequestUtil.getUserLists(parms));
                                } else {
                                    response.send("接口错误");
                                }
                            } catch (Exception e) {
                                response.send(new Gson().toJson(new HttpResult("-200", "数据异常或不能为空", false, null)));
                            }
                        } else {
                            response.send(new Gson().toJson(new HttpResult("-1201", "连接超时,请重新连接", false, null)));
                        }
                    } else if (request.getPath().substring(0, 5).equals("/wsd/")) {
                        response.send(HttpRequestUtil.setWsdpm(request));
                    } else {
                        response.send(new Gson().toJson(new HttpResult("-1200", "请重新连接", false, null)));
                    }
                }
            } else {
                response.send(new Gson().toJson(new HttpResult("-1200", "请重新连接", false, null)));
            }
        }


    }
}

