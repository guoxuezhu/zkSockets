package com.lh.zksockets.utils;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by guoxuezhu on 18-9-23.
 */
public class TimerUtils {
    private static Timer jdqTimer1, jdqTimer2, jdqTimer3, jdqTimer4, jdqTimer5, jdqTimer6, jdqTimer7, jdqTimer8;
    private static Timer dangerOutTimer1, dangerOutTimer2, dangerOutTimer3, dangerOutTimer4;
    private static Timer ioOutTimer1, ioOutTimer2, ioOutTimer3, ioOutTimer4;


    public static void setHuifuJDQstatus(String jdqPort, int time, int status) {
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


    }

    private static void startJDQtimer1(int time, final int status) {
        if (jdqTimer1 != null) {
            jdqTimer1.cancel();
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
                }

            }
        }, time * 1000);
    }

    private static void startJDQtimer2(int time, final int status) {
        if (jdqTimer2 != null) {
            jdqTimer2.cancel();
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
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer3(int time, final int status) {
        if (jdqTimer3 != null) {
            jdqTimer3.cancel();
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
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer4(int time, final int status) {
        if (jdqTimer4 != null) {
            jdqTimer4.cancel();
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
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer5(int time, final int status) {
        if (jdqTimer5 != null) {
            jdqTimer5.cancel();
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
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer6(int time, final int status) {
        if (jdqTimer6 != null) {
            jdqTimer6.cancel();
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
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer7(int time, final int status) {
        if (jdqTimer7 != null) {
            jdqTimer7.cancel();
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
                }
            }
        }, time * 1000);
    }

    private static void startJDQtimer8(int time, final int status) {
        if (jdqTimer8 != null) {
            jdqTimer8.cancel();
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
                }

            }
        }, time * 1000);
    }

    private static void dangerOutTimer2(int time, final int status) {
        if (dangerOutTimer2 != null) {
            dangerOutTimer2.cancel();
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
                }

            }
        }, time * 1000);
    }

    private static void dangerOutTimer3(int time, final int status) {
        if (dangerOutTimer3 != null) {
            dangerOutTimer3.cancel();
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
                }

            }
        }, time * 1000);
    }

    private static void dangerOutTimer4(int time, final int status) {
        if (dangerOutTimer4 != null) {
            dangerOutTimer4.cancel();
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
                }

            }
        }, time * 1000);
    }


}
