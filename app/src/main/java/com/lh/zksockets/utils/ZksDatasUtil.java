package com.lh.zksockets.utils;

import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.UIsetDataDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.UIsetData;

public class ZksDatasUtil {

    public static final int COMCOUNT = 5;
    public static final int JDQ_COUNT = 9;
    public static final int IO_OUT_COUNT = 1;
    public static final int DANGER_OUT_COUNT = 1;
    public static final int DANGER_IN_COUNT = 1;

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

    public static void getComDatas(SerialPortDataDao serialPortDataDao, SerialCommandDao serialCommandDao) {
        if (serialPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < COMCOUNT; i++) {
                serialPortDataDao.insert(new SerialPortData((long) i, "串口" + i, "", 3,
                        "9600", 0, "NONE", 0, "8", 0, "1", 10));
                for (int j = 1; j < 31; j++) {
                    if (j >= 10) {
                        serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "" + j), i, j, "1-" + i + "" + j, "", "", 10));
                    } else {
                        serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "0" + j), i, j, "1-" + i + "0" + j, "", "", 10));
                    }
                }
            }

//            serialPortDataDao.update(new SerialPortData((long) 4, "串口4", "电能表", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
//            serialCommandDao.update(new SerialCommand(Long.valueOf("401"), 4, 1, "1-401", "电能表", "0104010000027037", 16));
//            serialPortDataDao.update(new SerialPortData((long) 4, "串口4", "温湿度", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
//            serialCommandDao.update(new SerialCommand(Long.valueOf("401"), 4, 1, "1-401", "温湿度", "01040000000271CB", 16));

            serialPortDataDao.insert(new SerialPortData((long) 11, "串口1", "爱普生投影机", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 12, "串口1", "奥图码投影机", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 13, "串口1", "英士投影机", 4, "19200", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 14, "串口1", "理光(K360/X600)投影机", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 15, "串口1", "理光(WX6170N/X6180N)投影机", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 16, "串口1", "理光(K310/K320)投影机", 4, "19200", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 17, "串口1", "理光(K7000/K8500/K9000)投影机", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 18, "串口1", "理光(W1000/W2000)投影机", 3, "9600", 0, "NONE", 0, "8", 0, "1", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("901"), 11, 1, "1-101", "开机", "505752204F4E0D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("902"), 11, 2, "1-102", "关机", "505752204F46460D", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("903"), 11, 1, "1-101", "开机", "7E3030303020310D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("904"), 11, 2, "1-102", "关机", "7E3030303020300D", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("905"), 11, 1, "1-101", "开机", "4330300D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("906"), 11, 2, "1-102", "关机", "4330310D", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("907"), 11, 1, "1-101", "开机", "23504F4E0D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("908"), 11, 2, "1-102", "关机", "23504F460D", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("909"), 11, 1, "1-101", "开机", "23504F4E", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("910"), 11, 2, "1-102", "关机", "23504446", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("911"), 11, 1, "1-101", "开机", "4130300D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("912"), 11, 2, "1-102", "关机", "4130310D", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("913"), 11, 1, "1-101", "开机", "3C70777220313E", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("914"), 11, 2, "1-102", "关机", "3C70777220303E", 16));

            serialCommandDao.insert(new SerialCommand(Long.valueOf("915"), 11, 1, "1-101", "开机", "23504F4E3A0D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("916"), 11, 2, "1-102", "关机", "23504F463A0D", 16));

        }
    }

    public static void getjdqDatas(JDQstatusDao jdqStatusDao) {
        if (jdqStatusDao.loadAll().size() == 0) {
//            jdqStatusDao.insert(new JDQstatus((long) 1, "继电器1", "", 1, 1));
//            jdqStatusDao.insert(new JDQstatus((long) 2, "继电器2", "", 1, 1));
//            jdqStatusDao.insert(new JDQstatus((long) 3, "继电器3", "", 1, 1));
//            jdqStatusDao.insert(new JDQstatus((long) 4, "继电器4", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 5, "继电器5", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 6, "继电器6", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 7, "继电器7", "幕布降", 1, 180));
            jdqStatusDao.insert(new JDQstatus((long) 8, "继电器8", "幕布升", 1, 180));
        }
    }

    public static void getIoOutDatas(IoPortDataDao ioPortDataDao) {
        if (ioPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < IO_OUT_COUNT; i++) {
                ioPortDataDao.insert(new IoPortData((long) i, "io输出" + i, "", 0, 10));
            }
        }
    }

    public static void getDangetOutDatas(DangerOutDao dangerOutDao) {
        if (dangerOutDao.loadAll().size() == 0) {
            for (int i = 1; i < DANGER_OUT_COUNT; i++) {
                dangerOutDao.insert(new DangerOut((long) i, "报警输出" + i, "", 1, 10));
            }
        }
    }

    public static void getDangetInDatas(IOYuanDao ioYuanDao) {
        if (ioYuanDao.loadAll().size() == 0) {
            for (int i = 1; i < DANGER_IN_COUNT; i++) {
                ioYuanDao.insert(new IOYuan((long) i, "报警" + i, "", 0, "", ""));
            }
        }
    }

    public static void getUIstatusDatas(UIsetDataDao uIsetDataDao) {
        if (uIsetDataDao.loadAll().size() == 0) {
            uIsetDataDao.insert(new UIsetData((long) 1, "一键互动", "1"));
            uIsetDataDao.insert(new UIsetData((long) 2, "自由互动", "1"));
            uIsetDataDao.insert(new UIsetData((long) 3, "录播", "1"));
            uIsetDataDao.insert(new UIsetData((long) 4, "多媒体", "1"));
            uIsetDataDao.insert(new UIsetData((long) 5, "窗帘", "1"));
            uIsetDataDao.insert(new UIsetData((long) 6, "灯光", "1"));
            uIsetDataDao.insert(new UIsetData((long) 7, "空调", "1"));
            uIsetDataDao.insert(new UIsetData((long) 8, "门禁", "1"));
            uIsetDataDao.insert(new UIsetData((long) 9, "音频", "1"));
            uIsetDataDao.insert(new UIsetData((long) 10, "电风扇", "1"));
        }
    }
}