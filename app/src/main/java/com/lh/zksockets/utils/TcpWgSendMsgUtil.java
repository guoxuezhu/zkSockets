package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.WgDeviceInfoDao;
import com.lh.zksockets.data.model.WgDeviceInfo;

import java.util.List;

public class TcpWgSendMsgUtil {

    public static void makeTcpWangguan(Long id) {

        WgDeviceInfoDao wgDeviceInfoDao = MyApplication.getDaoSession().getWgDeviceInfoDao();
        if (wgDeviceInfoDao.loadAll().size() == 0) {
            return;
        }
        String msg = "";
        if (id == 3) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%窗帘%"))
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
                }
            }
        } else if (id == 4) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%窗帘%"))
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
                }
            }
        } else if (id == 13) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%调光灯%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffed9a398","port":11,"agreement":49246,"deviceid":66,"serial":12004,"control":{"poweronctm":100}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"poweronctm\":100}}";
                    TcpSocketUtil.sendData(msg);
                }
            }
        } else if (id == 14) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%调光灯%"))
                    .orderAsc(WgDeviceInfoDao.Properties.Deviceid)
                    .list();
            //{"code":1002,"id":"588e81fffed9a398","port":11,"agreement":49246,"deviceid":66,"serial":12004,"control":{"poweronctm":0}}
            if (wgDeviceInfos.size() != 0) {
                for (int i = 0; i < wgDeviceInfos.size(); i++) {
                    msg = "{\"code\":1002,\"id\":\"" + wgDeviceInfos.get(i).stringId
                            + "\",\"port\":" + wgDeviceInfos.get(i).port
                            + ",\"agreement\":" + wgDeviceInfos.get(i).agreement
                            + ",\"deviceid\":" + wgDeviceInfos.get(i).deviceid
                            + ",\"serial\":12004,\"control\":{\"poweronctm\":0}}";
                    TcpSocketUtil.sendData(msg);
                }
            }
        } else if (id == 68) {
            List<WgDeviceInfo> wgDeviceInfos = wgDeviceInfoDao.queryBuilder()
                    .where(WgDeviceInfoDao.Properties.Name.like("%插座%"))
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
                    .where(WgDeviceInfoDao.Properties.Name.like("%插座%"))
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
