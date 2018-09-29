package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.PowerDeviceDao;
import com.lh.zksockets.data.model.PowerDevice;

import java.util.List;


/**
 * Created by guoxuezhu on 18-9-23.
 */
public class WorksUtil {


    public static void powerWorkOpen() {

        PowerDeviceDao powerDeviceDao = MyApplication.getDaoSession().getPowerDeviceDao();


        List<PowerDevice> powerDevices = powerDeviceDao.queryBuilder()
                .orderAsc(PowerDeviceDao.Properties.OpenTime)
                .list();
        ELog.d("====电源箱操作===OpenTime=====" + powerDevices.toString());

        if (powerDevices.size() != 0) {
            //TimerUtils.WorkTimer(powerDevices, 0);
            ELog.d("====电源箱操作===串口协议====");
            SerialPortUtil.sendMsg(1,"");
        }


    }


}
