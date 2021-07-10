package com.lh.zksockets.utils;


import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.KongTiaoDataDao;
import com.lh.zksockets.data.DbDao.VidStatusDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.model.WenShiDu;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by guoxuezhu on 18-9-23.
 */
public class TimerUtils {
    private static Timer jdqTimer1, jdqTimer2, jdqTimer3, jdqTimer4, jdqTimer5, jdqTimer6, jdqTimer7, jdqTimer8;
    private static Timer dangerOutTimer1, dangerOutTimer2, dangerOutTimer3, dangerOutTimer4;
    private static Timer ioOutTimer1, ioOutTimer2, ioOutTimer3, ioOutTimer4;
    private static Timer wenshiTimer, KaijiTimer, duandianTimer;
    private static int wsdCount = 0;


    public static void setHuifuJDQstatus(String jdqPort, int time, int status) {
        try {
            if (jdqPort.equals("1")) {
                startJDQtimer1(time, status);
            } else if (jdqPort.equals("2")) {
                startJDQtimer2(time, status);
            } else if (jdqPort.equals("3")) {
                startJDQtimer3(time, status);
            } else if (jdqPort.equals("4")) {
                startJDQtimer4(time, status);
            } else if (jdqPort.equals("5")) {
                startJDQtimer5(time, status);
            } else if (jdqPort.equals("6")) {
                startJDQtimer6(time, status);
            } else if (jdqPort.equals("7")) {
                startJDQtimer7(time, status);
            } else if (jdqPort.equals("8")) {
                startJDQtimer8(time, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ELog.e("======JDQtimer====时间异常======");
        }
    }

    private static void startJDQtimer1(int time, final int status) {
        if (jdqTimer1 != null) {
            jdqTimer1.cancel();
            jdqTimer1 = null;
        }
        jdqTimer1 = new Timer();
        jdqTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY1:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY1:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer1==========");
                if (jdqTimer1 != null) {
                    jdqTimer1.cancel();
                    jdqTimer1 = null;
                }

            }
        }, time * 1000);
    }

    private static void startJDQtimer2(int time, final int status) {
        if (jdqTimer2 != null) {
            jdqTimer2.cancel();
            jdqTimer2 = null;
        }
        jdqTimer2 = new Timer();
        jdqTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY2:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY2:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer2==========");
                if (jdqTimer2 != null) {
                    jdqTimer2.cancel();
                    jdqTimer2 = null;
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer3(int time, final int status) {
        if (jdqTimer3 != null) {
            jdqTimer3.cancel();
            jdqTimer3 = null;
        }
        jdqTimer3 = new Timer();
        jdqTimer3.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY3:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY3:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer3==========");
                if (jdqTimer3 != null) {
                    jdqTimer3.cancel();
                    jdqTimer3 = null;
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer4(int time, final int status) {
        if (jdqTimer4 != null) {
            jdqTimer4.cancel();
            jdqTimer4 = null;
        }
        jdqTimer4 = new Timer();
        jdqTimer4.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY4:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY4:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer4==========");
                if (jdqTimer4 != null) {
                    jdqTimer4.cancel();
                    jdqTimer4 = null;
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer5(int time, final int status) {
        if (jdqTimer5 != null) {
            jdqTimer5.cancel();
            jdqTimer5 = null;
        }
        jdqTimer5 = new Timer();
        jdqTimer5.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY5:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY5:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer5==========");
                if (jdqTimer5 != null) {
                    jdqTimer5.cancel();
                    jdqTimer5 = null;
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer6(int time, final int status) {
        if (jdqTimer6 != null) {
            jdqTimer6.cancel();
            jdqTimer6 = null;
        }
        jdqTimer6 = new Timer();
        jdqTimer6.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY6:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY6:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer6==========");
                if (jdqTimer6 != null) {
                    jdqTimer6.cancel();
                    jdqTimer6 = null;
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer7(int time, final int status) {
        if (jdqTimer7 != null) {
            jdqTimer7.cancel();
            jdqTimer7 = null;
        }
        jdqTimer7 = new Timer();
        jdqTimer7.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY7:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY7:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer7==========");
                if (jdqTimer7 != null) {
                    jdqTimer7.cancel();
                    jdqTimer7 = null;
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer8(int time, final int status) {
        if (jdqTimer8 != null) {
            jdqTimer8.cancel();
            jdqTimer8 = null;
        }
        jdqTimer8 = new Timer();
        jdqTimer8.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[REY8:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[REY8:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========jdqTimer8==========");
                if (jdqTimer8 != null) {
                    jdqTimer8.cancel();
                    jdqTimer8 = null;
                }
            }
        }, time * 1000);
    }

    public static void setHuifuDangerOutstatus(String dangerOut, int time, int status) {
        if (dangerOut.equals("1")) {
            dangerOutTimer1(time, status);
        } else if (dangerOut.equals("2")) {
            dangerOutTimer2(time, status);
        } else if (dangerOut.equals("3")) {
            dangerOutTimer3(time, status);
        } else if (dangerOut.equals("4")) {
            dangerOutTimer4(time, status);
        }
    }

    private static void dangerOutTimer1(int time, final int status) {
        if (dangerOutTimer1 != null) {
            dangerOutTimer1.cancel();
            dangerOutTimer1 = null;
        }
        dangerOutTimer1 = new Timer();
        dangerOutTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[ARM1:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[ARM1:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========dangerOutTimer1==========");
                if (dangerOutTimer1 != null) {
                    dangerOutTimer1.cancel();
                    dangerOutTimer1 = null;
                }

            }
        }, time * 1000);
    }

    private static void dangerOutTimer2(int time, final int status) {
        if (dangerOutTimer2 != null) {
            dangerOutTimer2.cancel();
            dangerOutTimer2 = null;
        }
        dangerOutTimer2 = new Timer();
        dangerOutTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[ARM2:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[ARM2:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========dangerOutTimer2==========");
                if (dangerOutTimer2 != null) {
                    dangerOutTimer2.cancel();
                    dangerOutTimer2 = null;
                }

            }
        }, time * 1000);
    }

    private static void dangerOutTimer3(int time, final int status) {
        if (dangerOutTimer3 != null) {
            dangerOutTimer3.cancel();
            dangerOutTimer3 = null;
        }
        dangerOutTimer3 = new Timer();
        dangerOutTimer3.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[ARM3:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[ARM3:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========dangerOutTimer3==========");
                if (dangerOutTimer3 != null) {
                    dangerOutTimer3.cancel();
                    dangerOutTimer3 = null;
                }

            }
        }, time * 1000);
    }

    private static void dangerOutTimer4(int time, final int status) {
        if (dangerOutTimer4 != null) {
            dangerOutTimer4.cancel();
            dangerOutTimer4 = null;
        }
        dangerOutTimer4 = new Timer();
        dangerOutTimer4.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[ARM4:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[ARM4:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========dangerOutTimer4==========");
                if (dangerOutTimer4 != null) {
                    dangerOutTimer4.cancel();
                    dangerOutTimer4 = null;
                }

            }
        }, time * 1000);
    }


    public static void setHuifuIoOutstatus(String ioOut, int time, int status) {
        if (ioOut.equals("1")) {
            setIoOutTimer1(time, status);
        } else if (ioOut.equals("2")) {
            setIoOutTimer2(time, status);
        } else if (ioOut.equals("3")) {
            setIoOutTimer3(time, status);
        } else if (ioOut.equals("4")) {
            setIoOutTimer4(time, status);
        }

    }

    private static void setIoOutTimer1(int time, final int status) {
        if (ioOutTimer1 != null) {
            ioOutTimer1.cancel();
            ioOutTimer1 = null;
        }
        ioOutTimer1 = new Timer();
        ioOutTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[IOL1:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[IOL1:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========ioOutTimer1==========");
                if (ioOutTimer1 != null) {
                    ioOutTimer1.cancel();
                    ioOutTimer1 = null;
                }

            }
        }, time * 1000);
    }

    private static void setIoOutTimer2(int time, final int status) {
        if (ioOutTimer2 != null) {
            ioOutTimer2.cancel();
            ioOutTimer2 = null;
        }
        ioOutTimer2 = new Timer();
        ioOutTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[IOL2:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[IOL2:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========ioOutTimer2==========");
                if (ioOutTimer2 != null) {
                    ioOutTimer2.cancel();
                    ioOutTimer2 = null;
                }

            }
        }, time * 1000);
    }

    private static void setIoOutTimer3(int time, final int status) {
        if (ioOutTimer3 != null) {
            ioOutTimer3.cancel();
            ioOutTimer3 = null;
        }
        ioOutTimer3 = new Timer();
        ioOutTimer3.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[IOL3:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[IOL3:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========ioOutTimer3==========");
                if (ioOutTimer3 != null) {
                    ioOutTimer3.cancel();
                    ioOutTimer3 = null;
                }

            }
        }, time * 1000);
    }

    private static void setIoOutTimer4(int time, final int status) {
        if (ioOutTimer4 != null) {
            ioOutTimer4.cancel();
            ioOutTimer4 = null;
        }
        ioOutTimer4 = new Timer();
        ioOutTimer4.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status == 0) {
                    SerialPortUtil.sendMsg("{[IOL4:DT:A005]<CLOSE>}".getBytes());
                } else {
                    SerialPortUtil.sendMsg("{[IOL4:DT:A004]<OPEN>}".getBytes());
                }
                ELog.d("=========ioOutTimer4==========");
                if (ioOutTimer4 != null) {
                    ioOutTimer4.cancel();
                    ioOutTimer4 = null;
                }

            }
        }, time * 1000);
    }


    public static void setWenshiduTimer() {

        if (wenshiTimer != null) {
            wenshiTimer.cancel();
        }
        wenshiTimer = new Timer();
        wenshiTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    wsdCount++;
                    WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();
                    if (wenShiDuDao.loadAll().size() != 0) {
                        WenShiDu wenShiDu = wenShiDuDao.loadAll().get(0);
                        String wsd = "WSD;" + wenShiDu.wenStr + ";" + wenShiDu.shiStr + ";" + wenShiDu.PM25;
                        SerialPortUtil.sendMsg1(wsd.getBytes());
                        if (wsdCount == 1) {
                            kongtiaoWendu(wenShiDu);
                            SerialPortUtil.wsdSendLog(wenShiDu);
                            DeviceStatusUtil.dianliangSendLog();
                            ELog.e("==========dianliangSendLog====timer===");
                        }
                    }
//                    SerialPortUtil.doSerialPort("1-801");

                    if (wsdCount == 1) {
                        SportDataUtil.readMsgType(1);
                        SerialPortUtil.doSerialPort("1-801");
                    } else if (wsdCount == 2) {
                        SportDataUtil.readMsgType(2);
                        SerialPortUtil.doSerialPort("1-802");
                    } else if (wsdCount == 3) {
                        SportDataUtil.readMsgType(3);
                        SerialPortUtil.doSerialPort("1-803");
                    } else if (wsdCount == 4) {
                        SportDataUtil.readMsgType(4);
                        SerialPortUtil.doSerialPort("1-804");
                    } else if (wsdCount == 5) {
                        SportDataUtil.readMsgType(5);
                        SerialPortUtil.doSerialPort("1-805");
                    } else if (wsdCount >= 10) {
                        wsdCount = 0;
                    }
                } catch (Exception e) {
                    ELog.d("=========wenshiTimer===Exception=======" + e.toString());
                }
            }
        }, 12000, 3 * 1000);
    }

    private static void kongtiaoWendu(WenShiDu wenShiDu) {
        KongTiaoDataDao kongTiaoDataDao = MyApplication.getDaoSession().getKongTiaoDataDao();
        if (kongTiaoDataDao.loadAll().size() == 0) {
            return;
        }
        if (kongTiaoDataDao.loadAll().get(0).kt_status == 0) {
            return;
        }
        if (wenShiDu.wenStr.equals("") || wenShiDu.wenStr.isEmpty()) {
            return;
        }
        int wenInt = Integer.valueOf(wenShiDu.wenStr.split("\\.")[0]);
        if (wenInt >= Integer.valueOf(kongTiaoDataDao.loadAll().get(0).wenstr_leng)) {
            if (DateUtil.compareDate(System.currentTimeMillis(), DateUtil.getTimeyyyyMMdd() + " " + kongTiaoDataDao.loadAll().get(0).leng_timeStart)
                    && !DateUtil.compareDate(System.currentTimeMillis(), DateUtil.getTimeyyyyMMdd() + " " + kongTiaoDataDao.loadAll().get(0).leng_timeEnd)) {
                SerialPortUtil.getEventId(kongTiaoDataDao.loadAll().get(0).leng_ml);
            }
        }
        if (wenInt <= Integer.valueOf(kongTiaoDataDao.loadAll().get(0).wenstr_re)) {
            if (DateUtil.compareDate(System.currentTimeMillis(), DateUtil.getTimeyyyyMMdd() + " " + kongTiaoDataDao.loadAll().get(0).re_timeStart)
                    && !DateUtil.compareDate(System.currentTimeMillis(), DateUtil.getTimeyyyyMMdd() + " " + kongTiaoDataDao.loadAll().get(0).re_timeEnd)) {
                SerialPortUtil.getEventId(kongTiaoDataDao.loadAll().get(0).re_ml);
            }
        }
    }


    public static void setKaijiTimer() {
        if (KaijiTimer != null) {
            KaijiTimer.cancel();
            KaijiTimer = null;
        }
        KaijiTimer = new Timer();
        KaijiTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SerialPortUtil.makeML((long) 45);
                setWenshiduTimer();
//                ELog.getMyLogcat();
                UDPUtil.startReadUdpMsg();
                VidStatusDao vidStatusDao = MyApplication.getDaoSession().getVidStatusDao();
                vidStatusDao.deleteAll();
                SerialPortUtil.sendMsg(SerialPortUtil.StringToBytes("BB060080"));
                ELog.d("=========setKaijiTimer==========");
                if (KaijiTimer != null) {
                    KaijiTimer.cancel();
                    KaijiTimer = null;
                }
            }
        }, 5 * 1000);

    }

    private static void qitacaozuo() {
        if (KaijiTimer != null) {
            KaijiTimer.cancel();
            KaijiTimer = null;
        }
        KaijiTimer = new Timer();
        KaijiTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SerialPortUtil.makeML((long) 60);
                if (KaijiTimer != null) {
                    KaijiTimer.cancel();
                    KaijiTimer = null;
                }
            }
        }, 30 * 1000);
    }


    public static void setDuandianTimer() {
        if (duandianTimer != null) {
            duandianTimer.cancel();
            duandianTimer = null;
        }
        duandianTimer = new Timer();
        duandianTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (DateUtil.getHHmmss().equals(MyApplication.prefs.getCloseTimer())) {
//                    SerialPortUtil.makeML((long) 38);
                    reboot();
                }
            }
        }, 1000 * 50, 1000);
    }

    public static void reboot() {
        if (DisplayTools.isOnline()) {
            MyApplication.prefs.setIsReboot(true);
            try {
                Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
