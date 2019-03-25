package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.lh.zksockets.R;
import com.lh.zksockets.utils.ELog;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import android_serialport_api.SerialPort;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongKongActivity extends BaseActivity {

    @BindView(R.id.et_zk_1)
    EditText et_zk_1;
    @BindView(R.id.et_zk_2)
    EditText et_zk_2;
    private SerialPort serialPort;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_kong);
        ButterKnife.bind(this);

        try {
            serialPort = new SerialPort(new File("/dev/ttyS2"), 9600, 0);
            //获取打开的串口中的输入输出流，以便于串口数据的收发
            //inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
            ELog.e("======open_ck=====打开串口异常");
            e.printStackTrace();
        }
    }



    @OnClick(R.id.btn_zk_ok)
    public void btn_zk_ok() {
//        sendSerialPortData("BB0400020" + et_zk_1.getText().toString() + "0" + et_zk_2.getText().toString() + "0055");

        byte[] data1 = "{[IOL0:DT:H001]<".getBytes();
        byte[] data2 = StringToBytes("0F");
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }


//    @OnClick(R.id.btn_zk_ok)
//    public void btn_zk_ok() {
//        sendSerialPortData("BB0400020" + et_zk_1.getText().toString() + "0" + et_zk_2.getText().toString() + "0055");
//    }

    @OnClick(R.id.btn_daiji_open)
    public void btn_daiji_open() {
        byte[] data1 = "{[IOL0:DT:H001]<".getBytes();
        byte[] data2 = StringToBytes("0E");
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }

    }

    @OnClick(R.id.btn_daiji_closed)
    public void btn_daiji_closed() {

        byte[] data1 = "{[IOL0:DT:H001]<".getBytes();
        byte[] data2 = StringToBytes("0D");
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }



    }

    @OnClick(R.id.btn_all_open)
    public void btn_all_open() {
        String msg = "{[IOL1:DT:A004]<OPEN>}";

        byte[] data = msg.getBytes();
        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }

    @OnClick(R.id.btn_all_closed)
    public void btn_all_closed() {
        String msg = "{[IOL1:DT:A005]<CLOSE>}";

        byte[] data = msg.getBytes();
        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }


    @OnClick(R.id.btn_ch1_open)
    public void btn_ch1_open() {

        String msg = "{[ARM4:DT:A004]<OPEN>}";

        byte[] data = msg.getBytes();
        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }


    }

    @OnClick(R.id.btn_ch1_closed)
    public void btn_ch1_closed() {
        String msg = "{[ARM4:DT:A005]<CLOSE>}";

        byte[] data = msg.getBytes();
        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }

    @OnClick(R.id.btn_ch2_open)
    public void btn_ch2_open() {

        byte[] data1 = "{[ARM0:DT:H001]<".getBytes();
        byte[] data2 = StringToBytes("0E");
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }



    }

    @OnClick(R.id.btn_ch2_closed)
    public void btn_ch2_closed() {
        byte[] data1 = "{[ARM0:DT:H001]<".getBytes();
        byte[] data2 = StringToBytes("0D");
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }

    }

    @OnClick(R.id.btn_ch3_open)
    public void btn_ch3_open() {
        initData("02", "01");
    }

    @OnClick(R.id.btn_ch3_closed)
    public void btn_ch3_closed() {
        initData("02", "00");
    }

    @OnClick(R.id.btn_ch4_open)
    public void btn_ch4_open() {
        initData("03", "01");
    }

    @OnClick(R.id.btn_ch4_closed)
    public void btn_ch4_closed() {
        initData("03", "00");
    }

    @OnClick(R.id.btn_ch5_open)
    public void btn_ch5_open() {
        initData("04", "01");
    }

    @OnClick(R.id.btn_ch5_closed)
    public void btn_ch5_closed() {
        initData("04", "00");
    }

    @OnClick(R.id.btn_ch6_open)
    public void btn_ch6_open() {
        initData("05", "01");
    }

    @OnClick(R.id.btn_ch6_closed)
    public void btn_ch6_closed() {
        initData("05", "00");
    }

    @OnClick(R.id.btn_ch7_open)
    public void btn_ch7_open() {
        initData("06", "01");
    }

    @OnClick(R.id.btn_ch7_closed)
    public void btn_ch7_closed() {
        initData("06", "00");
    }

    @OnClick(R.id.btn_ch8_open)
    public void btn_ch8_open() {
        initData("07", "01");
    }

    @OnClick(R.id.btn_ch8_closed)
    public void btn_ch8_closed() {
        initData("07", "00");
    }

    private void initData(String position, String type) {
        sendSerialPortData("481A0005" + position + type + "004D");
    }

    private void sendSerialPortData(String strDatas) {
        byte[] data = StringToBytes(strDatas);
        try {
            if (data.length > 0) {
                outputStream.write(data);
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }


    private byte[] StringToBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i = i + 2) {
            bytes[i / 2] = (byte) Integer.parseInt(str.substring(i, i + 2), 16);
        }
        return bytes;
    }

    @OnClick(R.id.zk_back_btn)
    public void zk_back_btn() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (serialPort != null) {
            serialPort.close();
        }

    }
}
