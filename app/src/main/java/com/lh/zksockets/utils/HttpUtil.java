package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.model.LuboInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    private static LuboInfoDao luboInfoDao;
    private static Timer luboTokenTimer;

    public static void setlubo(String msg) {
        if (getLuboData()) {
            if (msg.equals("LUB1")) {
                luboStart();
            } else if (msg.equals("LUB2")) {
                luboPause();
            } else if (msg.equals("LUB3")) {
                luboStop();
            } else if (msg.equals("LUB4")) {
                luboZhiboStartTs();
            } else if (msg.equals("LUB5")) {
                luboZhiboStopTs();
            } else if (msg.equals("LUB6")) {
                luboRestart();
            }
        } else {
            if (msg.equals("LUB1")) {
                SerialPortUtil.makeML((long) 54);
            } else if (msg.equals("LUB2")) {
                SerialPortUtil.makeML((long) 55);
            } else if (msg.equals("LUB3")) {
                SerialPortUtil.makeML((long) 57);
            } else if (msg.equals("LUB4")) {
                SerialPortUtil.makeML((long) 58);
            } else if (msg.equals("LUB5")) {
                SerialPortUtil.makeML((long) 59);
            } else if (msg.equals("LUB6")) {
                SerialPortUtil.makeML((long) 56);
            }
        }
    }


    public static void setLuboTokenTimer() {
        if (getLuboData()) {
            getToken();
        }
    }

    private static boolean getLuboData() {
        luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();
        if (luboInfoDao.loadAll().size() == 0) {
            return false;
        }
        if (luboInfoDao.loadAll().get(0).status == 0) {
            return false;
        }
        return true;
    }

    private static void getToken() {
        if (luboInfoDao.loadAll().get(0).IP.isEmpty()) {
            return;
        }
        if (luboInfoDao.loadAll().get(0).userName.isEmpty()) {
            return;
        }
        if (luboInfoDao.loadAll().get(0).Password.isEmpty()) {
            return;
        }
        if (luboTokenTimer != null) {
            luboTokenTimer.cancel();
        }
        luboTokenTimer = new Timer();
        luboTokenTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                login();
                ELog.d("=========luboTokenTimer==========");
            }
        }, 10000, 1000 * 60 * 50);
    }

    //http://172.31.20.84:8234/join?deviceId=_sunrise_unique&username=admin&password=613be55ddb2d6ae6552efb5c6f67a48e&dynamic=hzlh
    private static void login() {
        String password = luboInfoDao.loadAll().get(0).Password + "hzlh";
        String passwordmd5 = "";
        try {
            passwordmd5 = Coder.hashMD5(password.getBytes("utf8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/join?deviceId=_sunrise_unique&username=" +
                        luboInfoDao.loadAll().get(0).userName + "&password=" + passwordmd5 + "&dynamic=hzlh")
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====HttpUtil======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======HttpUtil====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (jsonObject.getString("rc").equals("0")) {
//                        JSONObject jsonObject1 = new JSONObject(jsonObject.getString("data"));
//                        ELog.e("==========token=======" + jsonObject1.getString("token"));

                        LuboInfo luboInfo = luboInfoDao.loadAll().get(0);
                        luboInfoDao.deleteAll();
                        luboInfoDao.insert(new LuboInfo(luboInfo.IP, luboInfo.userName, luboInfo.Password,
                                jsonObject.getString("token"), luboInfo.status));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //http://<ip>:8234/startRec?token=<token>
    private static void luboStart() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/startRec?token=" + luboInfoDao.loadAll().get(0).token)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====luboStart======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======luboStart====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (!jsonObject.getString("rc").equals("0")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //http://<ip>:8234/pauseRec?token=<token>
    private static void luboPause() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("pause", "1")
                .build();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/pauseRec?token=" + luboInfoDao.loadAll().get(0).token)
                .post(body)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====luboPause======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======luboPause====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (!jsonObject.getString("rc").equals("0")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static void luboRestart() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("pause", "0")
                .build();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/pauseRec?token=" + luboInfoDao.loadAll().get(0).token)
                .post(body)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====luboPause======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======luboPause====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (!jsonObject.getString("rc").equals("0")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    // http://<ip>:8234/stopRec?token=<token>
    private static void luboStop() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/stopRec?token=" + luboInfoDao.loadAll().get(0).token)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====luboStop======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======luboStop====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (!jsonObject.getString("rc").equals("0")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //http://<ip>:8234/startLive?token=<token>
    private static void luboZhiboStartTs() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/startLive?token=" + luboInfoDao.loadAll().get(0).token)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====luboStop======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======luboStop====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (!jsonObject.getString("rc").equals("0")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //http://<ip>:8234/stopLive?token=<token>
    private static void luboZhiboStopTs() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + ":8234/stopLive?token=" + luboInfoDao.loadAll().get(0).token)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("====luboStop======onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======luboStop====数据=======" + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    if (!jsonObject.getString("rc").equals("0")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}