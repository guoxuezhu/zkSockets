package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.model.MLsLists;

public class ZksDatasUtil {

    public static void getEventDatas() {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 45, "开机", "", ""));


            mLsListsDao.insert(new MLsLists((long) 3, "窗帘开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "窗帘关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘暂停(全暂停)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1001, "窗帘1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1002, "窗帘1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1003, "窗帘1暂停", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1004, "窗帘2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1005, "窗帘2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1006, "窗帘2暂停", "", ""));


            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", "2-7-0", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", "2-8-0", ""));


            mLsListsDao.insert(new MLsLists((long) 13, "灯光开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3001, "黑板灯开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3002, "黑板灯关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3003, "教室灯1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3004, "教室灯1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3005, "教室灯2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3006, "教室灯2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3007, "教室灯3开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3008, "教室灯3关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3009, "教室灯4开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3010, "教室灯4关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 4101, "音量1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4102, "音量2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4103, "音量3级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4104, "音量4级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4105, "音量5级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4106, "音量6级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4107, "音量7级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4108, "音量8级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4109, "音量9级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4110, "音量10级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4111, "静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4112, "静音关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 39, "空调-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 40, "空调-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2001, "空调-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2002, "空调-制冷", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2003, "空调-制热", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2004, "空调-风速低", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2005, "空调-风速中", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2006, "空调-风速高", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2007, "空调-摆风", "", ""));


            mLsListsDao.insert(new MLsLists((long) 46, "门禁-前门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 47, "门禁-后门", "", ""));

            mLsListsDao.insert(new MLsLists((long) 54, "开始录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "暂停录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "继续录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "停止录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "开始直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "停止直播", "", ""));


            mLsListsDao.insert(new MLsLists((long) 5001, "一体机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5002, "一体机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5003, "一体机内置显示", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5004, "一体机外置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5005, "大屏1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5006, "大屏2", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5007, "大屏3", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5008, "大屏4", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5091, "老师大屏一体机信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5092, "其它设备信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5101, "HDMI信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5102, "OPS信号输出", "", ""));


            mLsListsDao.insert(new MLsLists((long) 6001, "新风开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6002, "新风关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6003, "新风-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6004, "新风-低速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6005, "新风-中速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6006, "新风-高速", "", ""));


            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "", ""));

            mLsListsDao.insert(new MLsLists((long) 7001, "自习模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7002, "投影模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7003, "课件模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7004, "自动模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7005, "模式1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7006, "模式2", "", ""));

            mLsListsDao.insert(new MLsLists((long) 8001, "电风扇全关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8002, "电风扇全1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8003, "电风扇全2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8004, "电风扇全3级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8005, "电风扇1-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8006, "电风扇1-风速1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8007, "电风扇1-风速2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8008, "电风扇1-风速3级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8009, "电风扇2-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8010, "电风扇2-风速1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8011, "电风扇2-风速2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8012, "电风扇2-风速3级", "", ""));

        }
    }
}