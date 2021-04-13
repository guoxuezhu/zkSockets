package com.lh.zksockets.service;

import com.google.gson.Gson;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.utils.Coder;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NIOHttpServer implements HttpServerRequestCallback {

    private static NIOHttpServer mInstance;

    private static int PORT_LISTEN_DEFALT = 8099;

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
        try {
            String sign = "";
            Multimap parms = null;
            if (request.getMethod().equals("GET")) {
                parms = request.getQuery();
                ELog.i("===========GET===parms======" + parms);
                sign = request.getQuery().getString("sign");
            } else if (request.getMethod().equals("POST")) {
                parms = (Multimap) request.getBody().get();
                ELog.i("===========POST===parms======" + parms);
                sign = parms.getString("sign");
            }
            if (mapToCheckSum(parms).equals(sign)) {
                if (request.getPath().equals("/api/lh_zk_login")) {
                    response.send(HttpRequestUtil.getLoginToken(parms));
                } else if (request.getPath().equals("/api/updataSportInfo")) {
                    response.send(HttpRequestUtil.updataSportInfo(parms));
                } else if (request.getPath().equals("/api/updataDangerInfo")) {
                    response.send(HttpRequestUtil.updataDangerInfo(parms));
                } else if (request.getPath().equals("/api/updataEventInfo")) {
                    response.send(HttpRequestUtil.updataEventInfo(parms));
                } else if (request.getPath().equals("/api/updataJdqInfo")) {
                    response.send(HttpRequestUtil.updataJdqInfo(parms));
                } else if (request.getPath().equals("/api/updataLuboInfo")) {
                    response.send(HttpRequestUtil.updataLuboInfo(parms));
                    // } else if (request.getPath().equals("/api/updataMqttInfo")) {
                    // response.send(HttpRequestUtil.updataMqttInfo(request));
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
                    //} else if (request.getPath().equals("/api/mqttInfo")) {
                    //response.send(HttpRequestUtil.getMqttinfo(request));
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
                } else if (request.getPath().equals("/api/deviceStatus")) {
                    response.send(HttpRequestUtil.getDeviceStatus(parms));
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
                } else if (request.getPath().equals("/api/get_vid_status")) {
                    response.send(HttpRequestUtil.getVIDstatus(parms));
                } else if (request.getPath().equals("/api/wgkzqInfo")) {
                    response.send(HttpRequestUtil.getWgkzqInfo(parms));
                } else if (request.getPath().equals("/api/get_kt_set")) {
                    response.send(HttpRequestUtil.getKongtiaoSet(parms));
                } else if (request.getPath().equals("/api/updata_kt_set")) {
                    response.send(HttpRequestUtil.updataKongtiaoSet(parms));
                } else if (request.getPath().equals("/api/get_diannao_set")) {
                    response.send(HttpRequestUtil.getDiannaoSet(parms));
                } else if (request.getPath().equals("/api/updata_diannao_set")) {
                    response.send(HttpRequestUtil.updataDiannaoSet(parms));
                } else if (request.getPath().equals("/api/updata_ui_status")) {
                    response.send(HttpRequestUtil.updataUIstatus(parms));
                } else if (request.getPath().equals("/api/get_ui_status")) {
                    response.send(HttpRequestUtil.getUIstatus(parms));
                } else if (request.getPath().equals("/api/get_datadao_wsd")) {
                    response.send(HttpRequestUtil.getWSDdatadao(parms));
                } else if (request.getPath().equals("/api/get_datadao_dnb")) {
                    response.send(HttpRequestUtil.getDNBdatadao(parms));
                } else if (request.getPath().equals("/api/ipconnect")) {
                    response.send(new Gson().toJson(new HttpResult("200", "连接成功", true, null)));
                } else {
                    response.send(new Gson().toJson(new HttpResult("-201", "api不存在", false, null)));
                }
            } else if (request.getPath().substring(0, 5).equals("/wsd/")) {
                response.send(HttpRequestUtil.setWsdpm(request));
            } else {
                response.send(new Gson().toJson(new HttpResult("-200", "签名错误", false, null)));
            }
        } catch (Exception e) {
            response.send(new Gson().toJson(new HttpResult("-200", "数据异常或不能为空", false, null)));
        }
    }

    private static String mapToCheckSum(Multimap querys) {
        String queryString = generateQueryString(querys) + "SXT";
        try {
            return Coder.hashMD5(queryString.getBytes("utf8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String generateQueryString(Multimap params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            if (!key.equals("sign")) {
                String value = params.getString(key);
                if (null == value) {
                    value = "";
                }
                value = getUtf8EscapedString(value);
                buffer.append("&").append(key).append("=").append(value);
            }
        }
        return buffer.substring(1);
    }

    private static String getUtf8EscapedString(String input) {
        String result = null;
        try {
            result = URLEncoder.encode(input, "utf8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return result;
    }

}

