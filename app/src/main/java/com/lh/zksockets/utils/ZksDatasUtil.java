package com.lh.zksockets.utils;

import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.UIsetDataDao;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.UIsetData;

public class ZksDatasUtil {

    public static final int COMCOUNT = 5;

    public static void getEventDatas(MLsListsDao mLsListsDao) {
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "一键-上课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "一键-下课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 215, "一键-下课1.5分钟", "", ""));
            mLsListsDao.insert(new MLsLists((long) 45, "一键-开机", "", ""));

            mLsListsDao.insert(new MLsLists((long) 3, "窗帘-开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "窗帘-关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘-暂停(全暂停)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1001, "窗帘1-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1002, "窗帘1-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1003, "窗帘1-暂停", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1004, "窗帘2-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1005, "窗帘2-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1006, "窗帘2-暂停", "", ""));

            mLsListsDao.insert(new MLsLists((long) 9, "投影机-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "投影机-幕布升", "2-8-0", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "投影机-幕布降", "2-7-0", ""));

            mLsListsDao.insert(new MLsLists((long) 13, "灯光-开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光-关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3001, "黑板灯-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3002, "黑板灯-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3003, "教室灯1-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3004, "教室灯1-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3005, "教室灯2-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3006, "教室灯2-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3007, "教室灯3-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3008, "教室灯3-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3009, "教室灯4-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3010, "教室灯4-关", "", ""));

            mLsListsDao.insert(new MLsLists((long) 4101, "音量-1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4102, "音量-2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4103, "音量-3级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4104, "音量-4级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4105, "音量-5级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4106, "音量-6级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4107, "音量-7级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4108, "音量-8级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4109, "音量-9级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4110, "音量-10级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4111, "音量-静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4112, "音量-静音关", "", ""));

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

            mLsListsDao.insert(new MLsLists((long) 54, "录播-开始录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "录播-暂停录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "录播-继续录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "录播-停止录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "录播-开始直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "录播-停止直播", "", ""));

            mLsListsDao.insert(new MLsLists((long) 5001, "大屏一体机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5002, "大屏一体机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5003, "大屏一体机OPS信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5004, "大屏一体机HDMI信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5005, "大屏1-开/关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5006, "大屏2-开/关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5007, "大屏3-开/关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5008, "大屏4-开/关", "", ""));

            mLsListsDao.insert(new MLsLists((long) 6001, "新风-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6002, "新风-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6003, "新风-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6004, "新风-低速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6005, "新风-中速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6006, "新风-高速", "", ""));

            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "", ""));

            mLsListsDao.insert(new MLsLists((long) 7001, "网关-自习模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7002, "网关-投影模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7003, "网关-课件模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7004, "网关-自动模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7005, "网关-模式1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7006, "网关-模式2", "", ""));

            mLsListsDao.insert(new MLsLists((long) 8001, "电风扇-全关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8002, "电风扇-全风速1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8003, "电风扇-全风速2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8004, "电风扇-全风速3级", "", ""));
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

    public static void getDeviceStatusDatas(EventShangkeDao eventShangkeDao) {
        if (eventShangkeDao.loadAll().size() == 0) {
            eventShangkeDao.insert(new EventShangke(0, (long) 1, "投影机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 201, "窗帘1", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 202, "窗帘2", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 301, "黑板灯", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 302, "教室灯1", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 303, "教室灯2", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 304, "教室灯3", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 305, "教室灯4", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 5, "空调", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 6, "录播", 0, false, 0));
        }
    }

    public static void getjdqDatas(JDQstatusDao jdqStatusDao) {
        if (jdqStatusDao.loadAll().size() == 0) {
            jdqStatusDao.insert(new JDQstatus((long) 1, "继电器1", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 2, "继电器2", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 3, "继电器3", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 4, "继电器4", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 5, "继电器5", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 6, "继电器6", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 7, "继电器7", "幕布降", 1, 180));
            jdqStatusDao.insert(new JDQstatus((long) 8, "继电器8", "幕布升", 1, 180));
        }
    }

    public static void getUIstatusDatas(UIsetDataDao uIsetDataDao) {
        if (uIsetDataDao.loadAll().size() == 0) {
            uIsetDataDao.insert(new UIsetData((long) 1, "互动", "1"));
            uIsetDataDao.insert(new UIsetData((long) 2, "多媒体", "1"));
            uIsetDataDao.insert(new UIsetData((long) 3, "环境控制", "1"));
            uIsetDataDao.insert(new UIsetData((long) 4, "音量", "1"));
            uIsetDataDao.insert(new UIsetData((long) 5, "录播", "1"));
        }
    }
}