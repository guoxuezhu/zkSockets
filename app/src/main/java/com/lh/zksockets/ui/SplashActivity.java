package com.lh.zksockets.ui;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.DangerStatusDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.ApkInfo;
import com.lh.zksockets.data.model.DangerStatus;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.service.MyMqttService;
import com.lh.zksockets.service.NIOHttpServer;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.FileUtil;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;
import com.lh.zksockets.utils.TimerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.login_name)
    EditText login_name;
    @BindView(R.id.login_password)
    EditText login_password;

    private Timer stimer, reOpentimer;

    private Handler splashHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10:
                    ELog.e("======splashHandler=====10====" + msg.obj.toString());
                    Toast.makeText(SplashActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    stopDialog();
                    break;
                case 111:
                    stopDialog();
                    break;
            }

        }
    };
    private ProgressDialog progressDialog;

    private void stopDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        try {
            boolean isReset = this.getIntent().getBooleanExtra("isReset", false);
            if (!isReset) {
                DangerStatusDao dangerStatusDao = MyApplication.getDaoSession().getDangerStatusDao();
                dangerStatusDao.deleteAll();
                dangerStatusDao.insert(new DangerStatus((long) 1, 0, 0, 0, 0));
                initdata();
                UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
                if (usersDao.loadAll().size() == 0) {
                    usersDao.insert(new Users(null, "admin", "admin", 1, 1, (long) 1, 3, 1));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "配置的数据出现异常了", Toast.LENGTH_SHORT).show();
        }
    }

    private void initdata() {
        if (SerialPortUtil.open()) {
            startMysystem();
        } else {
            serialPortOpen();
        }
    }

    private void serialPortOpen() {
        if (reOpentimer != null) {
            reOpentimer.cancel();
            reOpentimer = null;
        }
        reOpentimer = new Timer();
        reOpentimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (reOpentimer != null) {
                    reOpentimer.cancel();
                    reOpentimer = null;
                }
                initdata();
                ELog.i("========串口===timer=======");
            }
        }, 1000 * 2);
    }

    private void startMysystem() {
        if (stimer != null) {
            stimer.cancel();
            stimer = null;
        }
        stimer = new Timer();
        stimer.schedule(new TimerTask() {
            @Override
            public void run() {

                SerialPortUtil.readMsg1();
                SerialPortUtil.readMsg2();
                SerialPortUtil.readMsg3();

                if (MyApplication.prefs.getIsReboot()) {
                    MyApplication.prefs.setIsReboot(false);
                } else {
                    setSerialport();
                    jdqOpenStatus();
//                    dangerOutStatus();
//                    ioOutStatus();
                    TimerUtils.setKaijiTimer();
                }

                NIOHttpServer.getInstance().startServer();

                HttpUtil.setLuboTokenTimer();
//                SerialPortUtil.sendMsg("{[VIDB:DT:A035]<0,2;1,3;2,4;3,5;4,6;5,7;6,8;7,0;8,1>}".getBytes());

                mqttServiceStart();

//                updataAPK();

                if (stimer != null) {
                    stimer.cancel();
                    stimer = null;
                }
            }
        }, 500);

    }

    private void updataAPK() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/get_soft_info?title=主机&version_code="
                        + DisplayTools.getVersionCode(this)
                        + "&version_name=" + DisplayTools.getVersionName(this))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
                if (splashHandler != null) {
                    Message message = new Message();
                    message.obj = "服务器连接失败,请检测网络";
                    message.what = 10;
                    splashHandler.sendMessage(message);
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("========升级==数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========升级==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<ApkInfo> apkInfoHttpData = gson.fromJson(responseText, new TypeToken<HttpData<ApkInfo>>() {
                    }.getType());
                    ELog.e("==========升级==url=====" + apkInfoHttpData.getData().files);

                    DownloadFile(apkInfoHttpData.getData().files);

                }
            }
        });
    }

    private void DownloadFile(String url) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//        request.setVisibleInDownloadsUi(true);
        request.setMimeType("application/vnd.android.package-archive");
        FileUtil.createFile();
        request.setDestinationInExternalPublicDir("lhFile/apkFile/", "主机.apk");
        ELog.e("==========升级==111=====");
        long downloadId = downloadManager.enqueue(request);
        ELog.e("==========升级==downloadId=====" + downloadId);

        listener(downloadId);
    }

    private void listener(final long Id) {

        // 注册广播监听系统的下载完成事件。
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (ID == Id) {
                    ELog.e("==========升级==downloadId=====" + " 下载完成!");
                    Toast.makeText(getApplicationContext(), "任务:" + Id + " 下载完成!", Toast.LENGTH_LONG).show();
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * 安装APK
     *
     * @param context
     * @param apkPath 安装包的路径
     */
    public static void installApk(Context context, Uri apkPath) {

    }

    private void mqttServiceStart() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() != 0 && zkInfoDao.loadAll().get(0).ismqttStart == 1) {
            ELog.d("=======SplashActivity==mqtt开启服务==========" + zkInfoDao.loadAll().get(0).zkname);
            MyMqttService.startService(this); //开启服务
        }
    }

    private void setSerialport() {
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        for (int j = 0; j < serialPortDataDao.loadAll().size(); j++) {
            String spStr = serialPortDataDao.loadAll().get(j).baudRate + ",n,8,1";
            String msg = "{[COM" + (serialPortDataDao.loadAll().get(j).id - 1) + ":ST:A0" + spStr.length() + "]<" + spStr + ">}";
            SerialPortUtil.sendMsg(msg.getBytes());
        }
    }

    private void ioOutStatus() {
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            return;
        }
        String status = ioPortDataDao.load((long) 4).ioOutStatus + "" + ioPortDataDao.load((long) 3).ioOutStatus + ""
                + ioPortDataDao.load((long) 2).ioOutStatus + "" + ioPortDataDao.load((long) 1).ioOutStatus;
        ELog.i("========ioOutStatus==11======" + status);
        String hex = Integer.toString(Integer.parseInt(status, 2), 16);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        byte[] data1 = "{[IOL0:DT:H001]<".getBytes();
        byte[] data2 = SerialPortUtil.StringToBytes(hex);
        if (data2 == null) {
            return;
        }
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        SerialPortUtil.sendMsg(data);

    }

    private void jdqOpenStatus() {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            return;
        }

        String status = jdqStatusDao.load((long) 8).jdqStatus + "" + jdqStatusDao.load((long) 7).jdqStatus + ""
                + jdqStatusDao.load((long) 6).jdqStatus + "" + jdqStatusDao.load((long) 5).jdqStatus + "1111";


        ELog.i("========jdqOpenStatus==11======" + status);
        String hex = Integer.toString(Integer.parseInt(status, 2), 16);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        byte[] data1 = "{[REY0:DT:H001]<".getBytes();
        byte[] data2 = SerialPortUtil.StringToBytes(hex);
        if (data2 == null) {
            return;
        }
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        SerialPortUtil.sendMsg(data);

    }

    private void dangerOutStatus() {
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            return;
        }

        String status = dangerOutDao.load((long) 4).dangerOutStatus + "" + dangerOutDao.load((long) 3).dangerOutStatus + ""
                + dangerOutDao.load((long) 2).dangerOutStatus + "" + dangerOutDao.load((long) 1).dangerOutStatus;


        ELog.i("========dangerOutStatus==11======" + status);
        String hex = Integer.toString(Integer.parseInt(status, 2), 16);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        byte[] data1 = "{[ARM0:DT:H001]<".getBytes();
        byte[] data2 = SerialPortUtil.StringToBytes(hex);
        if (data2 == null) {
            return;
        }
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        SerialPortUtil.sendMsg(data);

    }

    @OnClick(R.id.login_btn)
    public void login_btn() {
        if (login_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (login_password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (login_name.getText().toString().trim().equals("admin") && login_password.getText().toString().trim().equals("hik12345")) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            return;
        }
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        if (usersDao.loadAll().size() == 0) {
            Toast.makeText(this, "请断电重启", Toast.LENGTH_SHORT).show();
        } else {
            List<Users> users = usersDao.queryBuilder()
                    .where(UsersDao.Properties.Username.eq(login_name.getText().toString().trim()))
                    .list();
            if (users.size() != 0) {
                Users user = users.get(0);
                if (user.user_status == 1) {
                    if (user.userPaw.equals(login_password.getText().toString().trim())) {
                        user.setLogin_count(3);
                        usersDao.update(user);
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        int count = user.login_count - 1;
                        if (count == 0) {
                            user.setUser_status(0);
                            Toast.makeText(this, "密码错误,此帐号锁定", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "密码错误," + count + "次后此帐号锁定", Toast.LENGTH_SHORT).show();
                        }
                        user.setLogin_count(count);
                        ELog.e("==========user===login_count====" + user.toString());
                        usersDao.update(user);
                        ELog.e("==========usersDao===111====" + usersDao.loadAll().toString());

                    }
                } else {
                    Toast.makeText(this, "此帐号已锁定,请使用其它帐号登录", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "帐号错误", Toast.LENGTH_SHORT).show();
            }
        }
//        if (progressDialog == null) {
//            progressDialog = new ProgressDialog(this);
//        }
//        progressDialog.show();
//        progressDialog.setMessage("正在登录");
//        progressDialog.setCanceledOnTouchOutside(false);
//
//        if (login_name.getText().toString().trim().equals("hzlhadmin") && login_password.getText().toString().trim().equals("hzlhadmin")) {
//            splashHandler.sendEmptyMessage(111);
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            return;
//        }
//        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
//        if (zkInfoDao.loadAll().size() == 0) {
//            Toast.makeText(this, "请先使用管理员帐号设置基本信息", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        RequestBody requestBody = new FormBody.Builder()
//                .add("user_name", login_name.getText().toString().trim())
//                .add("user_pass", login_password.getText().toString().trim())
//                .build();
//
//        Request request = new Request.Builder()
//                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/user_login")
//                .post(requestBody)
//                .build();
//
//        //3.创建一个call对象,参数就是Request请求对象
//        Call call = okHttpClient.newCall(request);
//        //4.请求加入调度，重写回调方法
//        call.enqueue(new Callback() {
//            //请求失败执行的方法
//            @Override
//            public void onFailure(Call call, IOException e) {
//                ELog.e("==========onFailure=======" + e.toString());
//                if (splashHandler != null) {
//                    Message message = new Message();
//                    message.obj = "服务器连接失败,请检测网络";
//                    message.what = 10;
//                    splashHandler.sendMessage(message);
//                }
//            }
//
//            //请求成功执行的方法
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String responseText = response.body().string();
//                ELog.e("==========数据=======" + responseText);
//                Gson gson = new Gson();
//                HttpData httpData = gson.fromJson(responseText, HttpData.class);
//                ELog.e("==========数据==11=====" + httpData.toString());
//
//                if (httpData.flag == 1) {
//                    splashHandler.sendEmptyMessage(111);
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                } else {
//                    Message message = new Message();
//                    message.obj = httpData.msg;
//                    message.what = 10;
//                    splashHandler.sendMessage(message);
//                }
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
