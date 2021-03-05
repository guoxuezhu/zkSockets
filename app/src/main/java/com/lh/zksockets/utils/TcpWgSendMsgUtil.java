package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.WgDeviceInfoDao;
import com.lh.zksockets.data.model.WgDeviceInfo;

import java.util.List;

import static java.lang.Thread.sleep;

public class TcpWgSendMsgUtil {

    public static void makeTcpWangguan(Long id) {

        WgDeviceInfoDao wgDeviceInfoDao = MyApplication.getDaoSession().getWgDeviceInfoDao();
        if (wgDeviceInfoDao.loadAll().size() == 0) {
            return;
        }
        String msg = "";
        if (id == 3) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%M%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffefeb128","port":8,"agreement":260,"deviceid":514,"serial":12004,"control":{"cts":0}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":0}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 1001) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%M%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffefeb128","port":8,"agreement":260,"deviceid":514,"serial":12004,"control":{"cts":1}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":2}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 4) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%M%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffefeb128","port":8,"agreement":260,"deviceid":514,"serial":12004,"control":{"cts":1}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":1}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 5) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("M1"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":0}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 1002) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("M1"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":2}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 6) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("M1"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":1}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 7) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("M2"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":0}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 1003) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("M2"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":2}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 8) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("M2"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"cts\":1}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (id == 13) {
//            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
//                    .where(WgDeviceInfoDao.Properties.Name.like("%L%"))
//                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
//                    .list();
//            //{"code":1002,"id":"ffffffffffffffff","port":11,"agreement":49246,"deviceid":66,"serial":12004,"control":{"control":0,"poweronctm":0}}
//            //{"code":1002,"id":"588e81fffed9a398","port":11,"agreement":49246,"deviceid":66,"serial":12004,"control":{"control":1,"poweronctm":100}}
//            if (wgDeviceInfos.size() != 0) {
//                for (int i = 0; i < wgDeviceInfos.size(); i++) {
//                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
//                            + "\",\"port\":" + wgDeviceInfos.get(i).port
//                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
//                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
//                            + ",\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
//                    TcpSocketUtil.sendData(msg);
//                }
//            }
//            msg = "{\"code\":1002,\"id\":\"fffffffffffffffe\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
//            TcpSocketUtil.sendData(msg);
//            try {
//                sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            msg = "{\"code\":1002,\"id\":\"ffffffffffffffff\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
            TcpSocketUtil.sendData(msg);
        } else if (id == 14) {
//            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
//                    .where(WgDeviceInfoDao.Properties.Name.like("%L%"))
//                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
//                    .list();
//            //{"code":1002,"id":"588e81fffed9a398","port":11,"agreement":49246,"deviceid":66,"serial":12004,"control":{"control":0,"poweronctm":0}}
//            if (wgDeviceInfos.size() != 0) {
//                for (int i = 0; i < wgDeviceInfos.size(); i++) {
//                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
//                            + "\",\"port\":" + wgDeviceInfos.get(i).port
//                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
//                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
//                            + ",\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
//                    TcpSocketUtil.sendData(msg);
//                }
//            }
//            msg = "{\"code\":1002,\"id\":\"fffffffffffffffe\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
//            TcpSocketUtil.sendData(msg);
//            try {
//                sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            msg = "{\"code\":1002,\"id\":\"ffffffffffffffff\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
            TcpSocketUtil.sendData(msg);
        } else if (id == 62) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("L1"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
//            msg = "{\"code\":1002,\"id\":\"fffffffffffffffe\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
//            TcpSocketUtil.sendData(msg);
        } else if (id == 63) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("L1"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
//            msg = "{\"code\":1002,\"id\":\"fffffffffffffffe\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
//            TcpSocketUtil.sendData(msg);
        } else if (id == 64) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("L2"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
//            msg = "{\"code\":1002,\"id\":\"ffffffffffffffff\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":1,\"poweronctm\":100}}";
//            TcpSocketUtil.sendData(msg);
        } else if (id == 65) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("L2"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
                    TcpSocketUtil.sendData(msg);
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
//            msg = "{\"code\":1002,\"id\":\"ffffffffffffffff\",\"port\":11,\"agreement\":49246,\"deviceid\":66,\"serial\":12004,\"control\":{\"control\":0,\"poweronctm\":0}}";
//            TcpSocketUtil.sendData(msg);
        } else if (id == 1011) {
            msg = "{\"code\":3003,\"id\":5,\"serial\":12004}";
            TcpSocketUtil.sendData(msg);
        } else if (id == 1012) {
            msg = "{\"code\":3003,\"id\":6,\"serial\":12004}";
            TcpSocketUtil.sendData(msg);
        } else if (id == 1013) {
            msg = "{\"code\":3003,\"id\":8,\"serial\":12004}";
            TcpSocketUtil.sendData(msg);
        } else if (id == 1014) {
            msg = "{\"code\":3003,\"id\":7,\"serial\":12004}";
            TcpSocketUtil.sendData(msg);
        } else if (id == 68) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%K%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffefeb128","port":8,"agreement":260,"deviceid":9,"serial":12004,"control":{"on":true}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"on\":true}}";
                    TcpSocketUtil.sendData(msg);
                }
            }
        } else if (id == 69) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%K%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffefeb128","port":8,"agreement":260,"deviceid":9,"serial":12004,"control":{"on":true}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"on\":false}}";
                    TcpSocketUtil.sendData(msg);
                }
            }
        }

    }

}
