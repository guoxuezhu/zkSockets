package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.utils.SerialPortUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetStatusActivity extends BaseActivity {

    private SerialPortDataDao serialPortDataDao;
    private SerialCommandDao serialCommandDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_status);
        ButterKnife.bind(this);


        serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();

        setData();
    }

    private void setData() {


        if (serialPortDataDao.loadAll().size() < 4) {
            serialPortDataDao.insert(new SerialPortData((long) 1, "串口1", "投影机", 3, "9600", 0, "无", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 2, "串口2", "外部继电器", 3, "9600", 0, "无", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 3, "串口3", "电源时序器", 6, "57600", 0, "无", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 4, "串口4", "", 3, "9600", 0, "无", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 5, "串口5", "红外", 3, "9600", 0, "无", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 6, "串口6", "一体机", 7, "115200", 0, "无", 0, "8", 0, "1", 16));
            serialPortDataDao.insert(new SerialPortData((long) 7, "串口7", "音量", 3, "9600", 0, "无", 0, "8", 0, "1", 10));
            serialPortDataDao.insert(new SerialPortData((long) 8, "串口8", "温湿度", 3, "9600", 0, "无", 0, "8", 0, "1", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("101"), 1, 1, "1-101", "投影机开", "505752204F4E0D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("102"), 1, 2, "1-102", "投影机关", "505752204F46460D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("103"), 1, 3, "1-103", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("104"), 1, 4, "1-104", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("105"), 1, 5, "1-105", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("106"), 1, 6, "1-106", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("107"), 1, 7, "1-107", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("108"), 1, 8, "1-108", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("109"), 1, 9, "1-109", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("110"), 1, 10, "1-110", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("111"), 1, 11, "1-111", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("112"), 1, 12, "1-112", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("113"), 1, 13, "1-113", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("114"), 1, 14, "1-114", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("115"), 1, 15, "1-115", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("116"), 1, 16, "1-116", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("117"), 1, 17, "1-117", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("118"), 1, 18, "1-118", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("119"), 1, 19, "1-119", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("120"), 1, 20, "1-120", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("121"), 1, 21, "1-121", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("122"), 1, 22, "1-122", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("123"), 1, 23, "1-123", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("124"), 1, 24, "1-124", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("125"), 1, 25, "1-125", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("126"), 1, 26, "1-126", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("127"), 1, 27, "1-127", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("128"), 1, 28, "1-128", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("129"), 1, 29, "1-129", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("130"), 1, 30, "1-130", "", "", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("201"), 2, 1, "1-201", "K1开", "AF731800010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("202"), 2, 2, "1-202", "K1关", "AF731800000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("203"), 2, 3, "1-203", "K2开", "AF731801010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("204"), 2, 4, "1-204", "K2关", "AF731801000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("205"), 2, 5, "1-205", "K3开", "AF731802010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("206"), 2, 6, "1-206", "K3关", "AF731802000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("207"), 2, 7, "1-207", "K4开", "AF731803010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("208"), 2, 8, "1-208", "K4关", "AF731803000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("209"), 2, 9, "1-209", "K5开", "AF731804010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("210"), 2, 10, "1-210", "K5关", "AF731804000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("211"), 2, 11, "1-211", "K6开", "AF731805010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("212"), 2, 12, "1-212", "K6关", "AF731805000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("213"), 2, 13, "1-213", "K7开", "AF731806010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("214"), 2, 14, "1-214", "K7关", "AF731806000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("215"), 2, 15, "1-215", "K8开", "AF731807010D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("216"), 2, 16, "1-216", "K8关", "AF731807000D0A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("217"), 2, 17, "1-217", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("218"), 2, 18, "1-218", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("219"), 2, 19, "1-219", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("220"), 2, 20, "1-220", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("221"), 2, 21, "1-221", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("222"), 2, 22, "1-222", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("223"), 2, 23, "1-223", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("224"), 2, 24, "1-224", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("225"), 2, 25, "1-225", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("226"), 2, 26, "1-226", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("227"), 2, 27, "1-227", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("228"), 2, 28, "1-228", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("229"), 2, 29, "1-229", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("230"), 2, 30, "1-230", "", "", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("301"), 3, 1, "1-301", "通道1关", "481A00050000004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("302"), 3, 2, "1-302", "通道2关", "481A00050100004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("303"), 3, 3, "1-303", "通道3关", "481A00050200004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("304"), 3, 4, "1-304", "通道4关", "481A00050300004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("305"), 3, 5, "1-305", "通道5关", "481A00050400004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("306"), 3, 6, "1-306", "通道6关", "481A00050500004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("307"), 3, 7, "1-307", "通道7关", "481A00050600004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("308"), 3, 8, "1-308", "通道8关", "481A00050700004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("309"), 3, 9, "1-309", "通道1开", "481A00050001004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("310"), 3, 10, "1-310", "通道2开", "481A00050101004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("311"), 3, 11, "1-311", "通道3开", "481A00050201004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("312"), 3, 12, "1-312", "通道4开", "481A00050301004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("313"), 3, 13, "1-313", "通道5开", "481A00050401004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("314"), 3, 14, "1-314", "通道6开", "481A00050501004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("315"), 3, 15, "1-315", "通道7开", "481A00050601004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("316"), 3, 16, "1-316", "通道8开", "481A00050701004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("317"), 3, 17, "1-317", "全开", "481A00040100004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("318"), 3, 18, "1-318", "全关", "481A00040000004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("319"), 3, 19, "1-319", "待机按钮开", "481A00010100004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("320"), 3, 20, "1-320", "待机按钮关", "481A00010000004D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("321"), 3, 21, "1-321", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("322"), 3, 22, "1-322", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("323"), 3, 23, "1-323", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("324"), 3, 24, "1-324", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("325"), 3, 25, "1-325", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("326"), 3, 26, "1-326", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("327"), 3, 27, "1-327", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("328"), 3, 28, "1-328", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("329"), 3, 29, "1-329", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("330"), 3, 30, "1-330", "", "", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("401"), 4, 1, "1-401", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("402"), 4, 2, "1-402", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("403"), 4, 3, "1-403", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("404"), 4, 4, "1-404", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("405"), 4, 5, "1-405", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("406"), 4, 6, "1-406", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("407"), 4, 7, "1-407", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("408"), 4, 8, "1-408", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("409"), 4, 9, "1-409", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("410"), 4, 10, "1-410", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("411"), 4, 11, "1-411", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("412"), 4, 12, "1-412", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("413"), 4, 13, "1-413", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("414"), 4, 14, "1-414", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("415"), 4, 15, "1-415", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("416"), 4, 16, "1-416", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("417"), 4, 17, "1-417", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("418"), 4, 18, "1-418", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("419"), 4, 19, "1-419", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("420"), 4, 20, "1-420", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("421"), 4, 21, "1-421", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("422"), 4, 22, "1-422", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("423"), 4, 23, "1-423", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("424"), 4, 24, "1-424", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("425"), 4, 25, "1-425", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("426"), 4, 26, "1-426", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("427"), 4, 27, "1-427", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("428"), 4, 28, "1-428", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("429"), 4, 29, "1-429", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("430"), 4, 30, "1-430", "", "", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("501"), 5, 1, "1-501", "电视机１", "7E07003433343314010068", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("502"), 5, 2, "1-502", "电视机２", "7E07003433343314020069", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("503"), 5, 3, "1-503", "空调-开", "7E0700343334331403006A", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("504"), 5, 4, "1-504", "空调-关", "7E0700343334331404006B", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("505"), 5, 5, "1-505", "模式", "7E0700343334331405006C", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("506"), 5, 6, "1-506", "风速", "7E0700343334331406006D", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("507"), 5, 7, "1-507", "", "7E0700343334331407006E", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("508"), 5, 8, "1-508", "", "7E0700343334331408006F", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("509"), 5, 9, "1-509", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("510"), 5, 10, "1-510", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("511"), 5, 11, "1-511", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("512"), 5, 12, "1-512", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("513"), 5, 13, "1-513", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("514"), 5, 14, "1-514", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("515"), 5, 15, "1-515", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("516"), 5, 16, "1-516", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("517"), 5, 17, "1-517", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("518"), 5, 18, "1-518", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("519"), 5, 19, "1-519", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("520"), 5, 20, "1-520", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("521"), 5, 21, "1-521", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("522"), 5, 22, "1-522", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("523"), 5, 23, "1-523", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("524"), 5, 24, "1-524", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("525"), 5, 25, "1-525", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("526"), 5, 26, "1-526", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("527"), 5, 27, "1-527", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("528"), 5, 28, "1-528", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("529"), 5, 29, "1-529", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("530"), 5, 30, "1-530", "", "", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("601"), 6, 1, "1-601", "一体机开", "992380017FAA", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("602"), 6, 2, "1-602", "一体机关", "99230101FEAA", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("603"), 6, 3, "1-603", "一体机外置HDMI", "99230E01F1AA", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("604"), 6, 4, "1-604", "一体机内置HDMI", "99231101EEAA", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("605"), 6, 5, "1-605", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("606"), 6, 6, "1-606", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("607"), 6, 7, "1-607", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("608"), 6, 8, "1-608", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("609"), 6, 9, "1-609", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("610"), 6, 10, "1-610", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("611"), 6, 11, "1-611", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("612"), 6, 12, "1-612", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("613"), 6, 13, "1-613", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("614"), 6, 14, "1-614", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("615"), 6, 15, "1-615", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("616"), 6, 16, "1-616", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("617"), 6, 17, "1-617", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("618"), 6, 18, "1-618", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("619"), 6, 19, "1-619", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("620"), 6, 20, "1-620", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("621"), 6, 21, "1-621", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("622"), 6, 22, "1-622", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("623"), 6, 23, "1-623", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("624"), 6, 24, "1-624", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("625"), 6, 25, "1-625", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("626"), 6, 26, "1-626", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("627"), 6, 27, "1-627", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("628"), 6, 28, "1-628", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("629"), 6, 29, "1-629", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("630"), 6, 30, "1-630", "", "", 16));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("701"), 7, 1, "1-701", "1音量＋", "L1_add1#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("702"), 7, 2, "1-702", "1音量＋", "L1_sub1#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("703"), 7, 3, "1-703", "1静音开", "L1_Mute 1#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("704"), 7, 4, "1-704", "1静音关", "L1_UnMute 1#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("705"), 7, 5, "1-705", "2音量＋", "L1_add2#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("706"), 7, 6, "1-706", "2音量＋", "L1_sub2#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("707"), 7, 7, "1-707", "2静音开", "L1_Mute 2#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("708"), 7, 8, "1-708", "2静音关", "L1_UnMute 2#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("709"), 7, 9, "1-709", "3音量＋", "L1_add3#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("710"), 7, 10, "1-710", "3音量＋", "L1_sub3#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("711"), 7, 11, "1-711", "3静音开", "L1_Mute 3#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("712"), 7, 12, "1-712", "3静音关", "L1_UnMute 3#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("713"), 7, 13, "1-713", "4音量＋", "L1_add4#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("714"), 7, 14, "1-714", "4音量＋", "L1_sub4#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("715"), 7, 15, "1-715", "4静音开", "L1_Mute 4#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("716"), 7, 16, "1-716", "4静音关", "L1_UnMute 4#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("717"), 7, 17, "1-717", "5音量＋", "L1_add5#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("718"), 7, 18, "1-718", "5音量＋", "L1_sub5#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("719"), 7, 19, "1-719", "5静音开", "L1_Mute 5#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("720"), 7, 20, "1-720", "5静音关", "L1_UnMute 5#", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("721"), 7, 21, "1-721", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("722"), 7, 22, "1-722", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("723"), 7, 23, "1-723", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("724"), 7, 24, "1-724", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("725"), 7, 25, "1-725", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("726"), 7, 26, "1-726", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("727"), 7, 27, "1-727", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("728"), 7, 28, "1-728", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("729"), 7, 29, "1-729", "", "", 10));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("730"), 7, 30, "1-730", "", "", 10));


            serialCommandDao.insert(new SerialCommand(Long.valueOf("801"), 8, 1, "1-801", "温湿度", "01040000000271CB", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("802"), 8, 2, "1-802", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("803"), 8, 3, "1-803", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("804"), 8, 4, "1-804", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("805"), 8, 5, "1-805", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("806"), 8, 6, "1-806", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("807"), 8, 7, "1-807", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("808"), 8, 8, "1-808", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("809"), 8, 9, "1-809", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("810"), 8, 10, "1-810", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("811"), 8, 11, "1-811", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("812"), 8, 12, "1-812", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("813"), 8, 13, "1-813", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("814"), 8, 14, "1-814", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("815"), 8, 15, "1-815", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("816"), 8, 16, "1-816", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("817"), 8, 17, "1-817", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("818"), 8, 18, "1-818", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("819"), 8, 19, "1-819", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("820"), 8, 20, "1-820", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("821"), 8, 21, "1-821", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("822"), 8, 22, "1-822", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("823"), 8, 23, "1-823", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("824"), 8, 24, "1-824", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("825"), 8, 25, "1-825", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("826"), 8, 26, "1-826", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("827"), 8, 27, "1-827", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("828"), 8, 28, "1-828", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("829"), 8, 29, "1-829", "", "", 16));
            serialCommandDao.insert(new SerialCommand(Long.valueOf("830"), 8, 30, "1-830", "", "", 16));

        }


        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", "1-601,1-501,1-502"));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", "1-102,1-602,1-501,1-502"));
            mLsListsDao.insert(new MLsLists((long) 3, "自习", "1-102,1-602,1-501,1-502,1-503,1-504"));
            mLsListsDao.insert(new MLsLists((long) 4, "休息", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘1开", "2-2-1"));
            mLsListsDao.insert(new MLsLists((long) 6, "窗帘1关", "2-1-1"));
            mLsListsDao.insert(new MLsLists((long) 7, "窗帘2开", ""));
            mLsListsDao.insert(new MLsLists((long) 8, "窗帘2关", ""));
            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", "1-101"));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", "1-102"));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", "2-7-1"));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", "2-8-1"));
            mLsListsDao.insert(new MLsLists((long) 13, "灯光1开", "1-205"));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光1关", "1-206"));
            mLsListsDao.insert(new MLsLists((long) 15, "灯光2开", ""));
            mLsListsDao.insert(new MLsLists((long) 16, "灯光2关", ""));
            mLsListsDao.insert(new MLsLists((long) 17, "灯光3开", ""));
            mLsListsDao.insert(new MLsLists((long) 18, "灯光3关", ""));
            mLsListsDao.insert(new MLsLists((long) 19, "灯光4开", ""));
            mLsListsDao.insert(new MLsLists((long) 20, "灯光4关", ""));
            mLsListsDao.insert(new MLsLists((long) 21, "总音量+", "1-701,1-705,1-709"));
            mLsListsDao.insert(new MLsLists((long) 22, "总音量-", "1-702,1-706,1-710"));
            mLsListsDao.insert(new MLsLists((long) 23, "总音量静音开", "1-703,1-707,1-711"));
            mLsListsDao.insert(new MLsLists((long) 24, "总音量静音关", "1-704,1-708,1-712"));
            mLsListsDao.insert(new MLsLists((long) 25, "音响音量+", "1-701"));
            mLsListsDao.insert(new MLsLists((long) 26, "音响音量-", "1-702"));
            mLsListsDao.insert(new MLsLists((long) 27, "音响静音开", "1-703"));
            mLsListsDao.insert(new MLsLists((long) 28, "音响静音关", "1-704"));
            mLsListsDao.insert(new MLsLists((long) 29, "麦克风音量+", "1-705"));
            mLsListsDao.insert(new MLsLists((long) 30, "麦克风音量-", "1-706"));
            mLsListsDao.insert(new MLsLists((long) 31, "麦克风静音开", "1-707"));
            mLsListsDao.insert(new MLsLists((long) 32, "麦克风静音关", "1-708"));
            mLsListsDao.insert(new MLsLists((long) 33, "录播-录制", ""));
            mLsListsDao.insert(new MLsLists((long) 34, "录播-暂停", ""));
            mLsListsDao.insert(new MLsLists((long) 35, "录播-停止", ""));
            mLsListsDao.insert(new MLsLists((long) 36, "录播-直播", ""));
            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "1-319"));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "1-102,1-320"));

            mLsListsDao.insert(new MLsLists((long) 39, "空调-开", "1-503"));
            mLsListsDao.insert(new MLsLists((long) 40, "空调-模式", "1-505"));
            mLsListsDao.insert(new MLsLists((long) 41, "空调-风速", "1-506"));
            mLsListsDao.insert(new MLsLists((long) 42, "空调-风向", ""));
            mLsListsDao.insert(new MLsLists((long) 43, "空调-温度+", ""));
            mLsListsDao.insert(new MLsLists((long) 44, "空调-温度-", ""));
            mLsListsDao.insert(new MLsLists((long) 45, "中控开机", "1-319,1-201"));
            mLsListsDao.insert(new MLsLists((long) 46, "门禁-前门", "2-4-1"));
            mLsListsDao.insert(new MLsLists((long) 47, "门禁-后门", ""));
            mLsListsDao.insert(new MLsLists((long) 48, "空调-关", "1-504"));
            mLsListsDao.insert(new MLsLists((long) 49, "空调-摆风", "1-507"));
            mLsListsDao.insert(new MLsLists((long) 50, "一体机-内置HDMI", "1-604"));
            mLsListsDao.insert(new MLsLists((long) 51, "一体机-外置HDMI", "1-603"));

            mLsListsDao.insert(new MLsLists((long) 52, "电视机1", "1-501"));
            mLsListsDao.insert(new MLsLists((long) 53, "电视机2", "1-502"));
            mLsListsDao.insert(new MLsLists((long) 54, "电视机3", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "电视机4", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "电视机5", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "电视机6", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "电视机7", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "电视机8", ""));
            mLsListsDao.insert(new MLsLists((long) 60, "其它", ""));

        }


        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            ioYuanDao.insert(new IOYuan((long) 1, 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 2, 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 3, 1, "1-203", "1-204"));
            ioYuanDao.insert(new IOYuan((long) 4, 0, "", ""));
        }


        WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();

        if (wenShiDuDao.loadAll().size() == 0) {
            wenShiDuDao.insert(new WenShiDu("", "", "", "", "", "", "", 1, "1-801"));
        }


        LuboInfoDao luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();

        if (luboInfoDao.loadAll().size() == 0) {
            luboInfoDao.insert(new LuboInfo("192.168.5.211", "admin", "admin", ""));
        }


        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            jdqStatusDao.insert(new JDQstatus((long) 1, "继电器1", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 2, "继电器2", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 3, "继电器3", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 4, "继电器4", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 5, "继电器5", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 6, "继电器6", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 7, "继电器7", 1, 180));
            jdqStatusDao.insert(new JDQstatus((long) 8, "继电器8", 1, 180));
        }


        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        if (baseInfoDao.loadAll().size() == 0) {
            baseInfoDao.insert(new BaseInfo("101", "cmt7p9p/zk_user_1",
                    "vZakahehxugRHnZs", java.util.UUID.randomUUID().toString()));
        }


        Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.test_fm_ok)
    public void test_fm_ok() {
        SerialPortUtil.sendMsg("{[BEP0:DT:A004]<OPEN>}".getBytes());
    }

    @OnClick(R.id.test_fm_no)
    public void test_fm_no() {
        SerialPortUtil.sendMsg("{[BEP0:DT:A005]<CLOSE>}".getBytes());
    }

    @OnClick(R.id.test_danger_ok)
    public void test_danger_ok() {
        SerialPortUtil.sendMsg("{[ARM2:DT:A004]<OPEN>}".getBytes());
    }

    @OnClick(R.id.test_danger_no)
    public void test_danger_no() {
        SerialPortUtil.sendMsg("{[ARM2:DT:A005]<CLOSE>}".getBytes());
    }


    @OnClick(R.id.test_io_ok)
    public void test_io_ok() {
        SerialPortUtil.sendMsg("{[IOL2:DT:A004]<OPEN>}".getBytes());
    }

    @OnClick(R.id.test_io_no)
    public void test_io_no() {
        SerialPortUtil.sendMsg("{[IOL2:DT:A005]<CLOSE>}".getBytes());
    }




    @OnClick(R.id.net_btn_back)
    public void net_btn_back() {
        back();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }

    private void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
