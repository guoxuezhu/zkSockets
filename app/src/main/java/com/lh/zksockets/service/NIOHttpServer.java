package com.lh.zksockets.service;

import android.util.Log;

import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;

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
        Log.d(TAG, "=======aaaaaaaa======");
        if (request.getMethod().equals("POST")) {
            Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
            Log.d(TAG, "=======parms========= " + parms.toString() + "===zkbtn=== " + parms.getString("zkbtn"));

            String msg = parms.getString("zkbtn");

            if (msg.length() > 3) {
                if (msg.substring(0, 3).equals("VID")) {
                    SerialPortUtil.sendShipinType(msg);
                } else if (msg.substring(0, 3).equals("LUB")) {
                    HttpUtil.setlubo(msg);
                } else if (msg.substring(0, 3).equals("MBS")) {
                    try {
                        SerialPortUtil.makeML(Long.valueOf(msg.substring(3)));
                    } catch (Exception e) {
                        Log.d(TAG, "==========http===POST===接收到了数据====Long.valueOf==异常========" + e.toString());
                    }
                }
            }
            response.send("200");

        } else {
            response.send("100");
        }


    }
}

