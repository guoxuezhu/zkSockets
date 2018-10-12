package com.lh.zksockets.utils;


import java.util.Timer;

/**
 * Created by guoxuezhu on 18-9-23.
 */
public class TimerUtils {
    private static Timer timer;

//    public static void WorkTimer(List<PowerDevice> powerDevices, int position) {
//        PowerDevice powerDevice = powerDevices.get(position);
//        stopTimer1();
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//
//                ELog.d("====电源箱操作===OpenTime=111====");
//
//            }
//        }, powerDevice.openTime * 1000);
//    }

    private static void setdfsdg() {


    }

    public static void stopTimer1() {
        if (timer != null) {
            timer.cancel();
        }
    }


}
