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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

    private static LuboInfoDao luboInfoDao;
    private static Timer luboTokenTimer;

    public static void setlubo(String msg) {
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

        }
    }


    public static void setLuboTokenTimer() {

        luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();
        if (luboInfoDao.loadAll().size() == 0) {
            return;
        }
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
        }, 10000, 13 * 60 * 1000);
    }


    private static void login() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + "/sdk/LoginSystem?{\"token\":\"\",\"userName\":\"" +
                        luboInfoDao.loadAll().get(0).userName + "\",\"password\":\"" + luboInfoDao.loadAll().get(0).Password + "\",\"md5Flag\":0}")
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
                    if (jsonObject.getString("result").equals("1")) {
                        JSONObject jsonObject1 = new JSONObject(jsonObject.getString("data"));
                        ELog.e("==========数据=======" + jsonObject1.getString("token"));

                        LuboInfo luboInfo = luboInfoDao.loadAll().get(0);
                        luboInfoDao.deleteAll();
                        luboInfoDao.insert(new LuboInfo(luboInfo.IP, luboInfo.userName, luboInfo.Password,
                                jsonObject1.getString("token")));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }


    private static void luboStart() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + "/sdk/StartRecord?token=" + luboInfoDao.loadAll().get(0).token)
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
                    if (!jsonObject.getString("result").equals("1")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static void luboPause() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + "/sdk/PauseRecord?token=" + luboInfoDao.loadAll().get(0).token)
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
                    if (!jsonObject.getString("result").equals("1")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private static void luboStop() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + "/sdk/StopRecord?token=" + luboInfoDao.loadAll().get(0).token)
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
                    if (!jsonObject.getString("result").equals("1")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static void luboZhiboStartTs() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + "/sdk/StartRtmp?channel=0&token=" + luboInfoDao.loadAll().get(0).token)
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
                    if (!jsonObject.getString("result").equals("1")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static void luboZhiboStopTs() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + luboInfoDao.loadAll().get(0).IP + "/sdk/StopRtmp?channel=0&token=" + luboInfoDao.loadAll().get(0).token)
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
                    if (!jsonObject.getString("result").equals("1")) {
                        setLuboTokenTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}