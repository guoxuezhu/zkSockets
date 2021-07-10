package com.lh.zksockets.data.DbDao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lh.zksockets.data.model.Computer;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.DangerStatus;
import com.lh.zksockets.data.model.DianliangData;
import com.lh.zksockets.data.model.DoorInfo;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.KongTiaoData;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.MicDatas;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.UIsetData;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.data.model.VidStatus;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.data.model.WuangguanInfo;
import com.lh.zksockets.data.model.ZkInfo;
import com.lh.zksockets.data.model.ZksData;

import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.DangerStatusDao;
import com.lh.zksockets.data.DbDao.DianliangDataDao;
import com.lh.zksockets.data.DbDao.DoorInfoDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.KongTiaoDataDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.MicDatasDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.UIsetDataDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.DbDao.VidStatusDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.DbDao.WuangguanInfoDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.DbDao.ZksDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig computerDaoConfig;
    private final DaoConfig dangerOutDaoConfig;
    private final DaoConfig dangerStatusDaoConfig;
    private final DaoConfig dianliangDataDaoConfig;
    private final DaoConfig doorInfoDaoConfig;
    private final DaoConfig eventShangkeDaoConfig;
    private final DaoConfig iOYuanDaoConfig;
    private final DaoConfig icCardDaoConfig;
    private final DaoConfig ioPortDataDaoConfig;
    private final DaoConfig jDQstatusDaoConfig;
    private final DaoConfig kongTiaoDataDaoConfig;
    private final DaoConfig luboInfoDaoConfig;
    private final DaoConfig mLsListsDaoConfig;
    private final DaoConfig micDatasDaoConfig;
    private final DaoConfig serialCommandDaoConfig;
    private final DaoConfig serialPortDataDaoConfig;
    private final DaoConfig uIsetDataDaoConfig;
    private final DaoConfig usersDaoConfig;
    private final DaoConfig vidStatusDaoConfig;
    private final DaoConfig wenShiDuDaoConfig;
    private final DaoConfig wuangguanInfoDaoConfig;
    private final DaoConfig zkInfoDaoConfig;
    private final DaoConfig zksDataDaoConfig;

    private final ComputerDao computerDao;
    private final DangerOutDao dangerOutDao;
    private final DangerStatusDao dangerStatusDao;
    private final DianliangDataDao dianliangDataDao;
    private final DoorInfoDao doorInfoDao;
    private final EventShangkeDao eventShangkeDao;
    private final IOYuanDao iOYuanDao;
    private final IcCardDao icCardDao;
    private final IoPortDataDao ioPortDataDao;
    private final JDQstatusDao jDQstatusDao;
    private final KongTiaoDataDao kongTiaoDataDao;
    private final LuboInfoDao luboInfoDao;
    private final MLsListsDao mLsListsDao;
    private final MicDatasDao micDatasDao;
    private final SerialCommandDao serialCommandDao;
    private final SerialPortDataDao serialPortDataDao;
    private final UIsetDataDao uIsetDataDao;
    private final UsersDao usersDao;
    private final VidStatusDao vidStatusDao;
    private final WenShiDuDao wenShiDuDao;
    private final WuangguanInfoDao wuangguanInfoDao;
    private final ZkInfoDao zkInfoDao;
    private final ZksDataDao zksDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        computerDaoConfig = daoConfigMap.get(ComputerDao.class).clone();
        computerDaoConfig.initIdentityScope(type);

        dangerOutDaoConfig = daoConfigMap.get(DangerOutDao.class).clone();
        dangerOutDaoConfig.initIdentityScope(type);

        dangerStatusDaoConfig = daoConfigMap.get(DangerStatusDao.class).clone();
        dangerStatusDaoConfig.initIdentityScope(type);

        dianliangDataDaoConfig = daoConfigMap.get(DianliangDataDao.class).clone();
        dianliangDataDaoConfig.initIdentityScope(type);

        doorInfoDaoConfig = daoConfigMap.get(DoorInfoDao.class).clone();
        doorInfoDaoConfig.initIdentityScope(type);

        eventShangkeDaoConfig = daoConfigMap.get(EventShangkeDao.class).clone();
        eventShangkeDaoConfig.initIdentityScope(type);

        iOYuanDaoConfig = daoConfigMap.get(IOYuanDao.class).clone();
        iOYuanDaoConfig.initIdentityScope(type);

        icCardDaoConfig = daoConfigMap.get(IcCardDao.class).clone();
        icCardDaoConfig.initIdentityScope(type);

        ioPortDataDaoConfig = daoConfigMap.get(IoPortDataDao.class).clone();
        ioPortDataDaoConfig.initIdentityScope(type);

        jDQstatusDaoConfig = daoConfigMap.get(JDQstatusDao.class).clone();
        jDQstatusDaoConfig.initIdentityScope(type);

        kongTiaoDataDaoConfig = daoConfigMap.get(KongTiaoDataDao.class).clone();
        kongTiaoDataDaoConfig.initIdentityScope(type);

        luboInfoDaoConfig = daoConfigMap.get(LuboInfoDao.class).clone();
        luboInfoDaoConfig.initIdentityScope(type);

        mLsListsDaoConfig = daoConfigMap.get(MLsListsDao.class).clone();
        mLsListsDaoConfig.initIdentityScope(type);

        micDatasDaoConfig = daoConfigMap.get(MicDatasDao.class).clone();
        micDatasDaoConfig.initIdentityScope(type);

        serialCommandDaoConfig = daoConfigMap.get(SerialCommandDao.class).clone();
        serialCommandDaoConfig.initIdentityScope(type);

        serialPortDataDaoConfig = daoConfigMap.get(SerialPortDataDao.class).clone();
        serialPortDataDaoConfig.initIdentityScope(type);

        uIsetDataDaoConfig = daoConfigMap.get(UIsetDataDao.class).clone();
        uIsetDataDaoConfig.initIdentityScope(type);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        vidStatusDaoConfig = daoConfigMap.get(VidStatusDao.class).clone();
        vidStatusDaoConfig.initIdentityScope(type);

        wenShiDuDaoConfig = daoConfigMap.get(WenShiDuDao.class).clone();
        wenShiDuDaoConfig.initIdentityScope(type);

        wuangguanInfoDaoConfig = daoConfigMap.get(WuangguanInfoDao.class).clone();
        wuangguanInfoDaoConfig.initIdentityScope(type);

        zkInfoDaoConfig = daoConfigMap.get(ZkInfoDao.class).clone();
        zkInfoDaoConfig.initIdentityScope(type);

        zksDataDaoConfig = daoConfigMap.get(ZksDataDao.class).clone();
        zksDataDaoConfig.initIdentityScope(type);

        computerDao = new ComputerDao(computerDaoConfig, this);
        dangerOutDao = new DangerOutDao(dangerOutDaoConfig, this);
        dangerStatusDao = new DangerStatusDao(dangerStatusDaoConfig, this);
        dianliangDataDao = new DianliangDataDao(dianliangDataDaoConfig, this);
        doorInfoDao = new DoorInfoDao(doorInfoDaoConfig, this);
        eventShangkeDao = new EventShangkeDao(eventShangkeDaoConfig, this);
        iOYuanDao = new IOYuanDao(iOYuanDaoConfig, this);
        icCardDao = new IcCardDao(icCardDaoConfig, this);
        ioPortDataDao = new IoPortDataDao(ioPortDataDaoConfig, this);
        jDQstatusDao = new JDQstatusDao(jDQstatusDaoConfig, this);
        kongTiaoDataDao = new KongTiaoDataDao(kongTiaoDataDaoConfig, this);
        luboInfoDao = new LuboInfoDao(luboInfoDaoConfig, this);
        mLsListsDao = new MLsListsDao(mLsListsDaoConfig, this);
        micDatasDao = new MicDatasDao(micDatasDaoConfig, this);
        serialCommandDao = new SerialCommandDao(serialCommandDaoConfig, this);
        serialPortDataDao = new SerialPortDataDao(serialPortDataDaoConfig, this);
        uIsetDataDao = new UIsetDataDao(uIsetDataDaoConfig, this);
        usersDao = new UsersDao(usersDaoConfig, this);
        vidStatusDao = new VidStatusDao(vidStatusDaoConfig, this);
        wenShiDuDao = new WenShiDuDao(wenShiDuDaoConfig, this);
        wuangguanInfoDao = new WuangguanInfoDao(wuangguanInfoDaoConfig, this);
        zkInfoDao = new ZkInfoDao(zkInfoDaoConfig, this);
        zksDataDao = new ZksDataDao(zksDataDaoConfig, this);

        registerDao(Computer.class, computerDao);
        registerDao(DangerOut.class, dangerOutDao);
        registerDao(DangerStatus.class, dangerStatusDao);
        registerDao(DianliangData.class, dianliangDataDao);
        registerDao(DoorInfo.class, doorInfoDao);
        registerDao(EventShangke.class, eventShangkeDao);
        registerDao(IOYuan.class, iOYuanDao);
        registerDao(IcCard.class, icCardDao);
        registerDao(IoPortData.class, ioPortDataDao);
        registerDao(JDQstatus.class, jDQstatusDao);
        registerDao(KongTiaoData.class, kongTiaoDataDao);
        registerDao(LuboInfo.class, luboInfoDao);
        registerDao(MLsLists.class, mLsListsDao);
        registerDao(MicDatas.class, micDatasDao);
        registerDao(SerialCommand.class, serialCommandDao);
        registerDao(SerialPortData.class, serialPortDataDao);
        registerDao(UIsetData.class, uIsetDataDao);
        registerDao(Users.class, usersDao);
        registerDao(VidStatus.class, vidStatusDao);
        registerDao(WenShiDu.class, wenShiDuDao);
        registerDao(WuangguanInfo.class, wuangguanInfoDao);
        registerDao(ZkInfo.class, zkInfoDao);
        registerDao(ZksData.class, zksDataDao);
    }
    
    public void clear() {
        computerDaoConfig.clearIdentityScope();
        dangerOutDaoConfig.clearIdentityScope();
        dangerStatusDaoConfig.clearIdentityScope();
        dianliangDataDaoConfig.clearIdentityScope();
        doorInfoDaoConfig.clearIdentityScope();
        eventShangkeDaoConfig.clearIdentityScope();
        iOYuanDaoConfig.clearIdentityScope();
        icCardDaoConfig.clearIdentityScope();
        ioPortDataDaoConfig.clearIdentityScope();
        jDQstatusDaoConfig.clearIdentityScope();
        kongTiaoDataDaoConfig.clearIdentityScope();
        luboInfoDaoConfig.clearIdentityScope();
        mLsListsDaoConfig.clearIdentityScope();
        micDatasDaoConfig.clearIdentityScope();
        serialCommandDaoConfig.clearIdentityScope();
        serialPortDataDaoConfig.clearIdentityScope();
        uIsetDataDaoConfig.clearIdentityScope();
        usersDaoConfig.clearIdentityScope();
        vidStatusDaoConfig.clearIdentityScope();
        wenShiDuDaoConfig.clearIdentityScope();
        wuangguanInfoDaoConfig.clearIdentityScope();
        zkInfoDaoConfig.clearIdentityScope();
        zksDataDaoConfig.clearIdentityScope();
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public DangerOutDao getDangerOutDao() {
        return dangerOutDao;
    }

    public DangerStatusDao getDangerStatusDao() {
        return dangerStatusDao;
    }

    public DianliangDataDao getDianliangDataDao() {
        return dianliangDataDao;
    }

    public DoorInfoDao getDoorInfoDao() {
        return doorInfoDao;
    }

    public EventShangkeDao getEventShangkeDao() {
        return eventShangkeDao;
    }

    public IOYuanDao getIOYuanDao() {
        return iOYuanDao;
    }

    public IcCardDao getIcCardDao() {
        return icCardDao;
    }

    public IoPortDataDao getIoPortDataDao() {
        return ioPortDataDao;
    }

    public JDQstatusDao getJDQstatusDao() {
        return jDQstatusDao;
    }

    public KongTiaoDataDao getKongTiaoDataDao() {
        return kongTiaoDataDao;
    }

    public LuboInfoDao getLuboInfoDao() {
        return luboInfoDao;
    }

    public MLsListsDao getMLsListsDao() {
        return mLsListsDao;
    }

    public MicDatasDao getMicDatasDao() {
        return micDatasDao;
    }

    public SerialCommandDao getSerialCommandDao() {
        return serialCommandDao;
    }

    public SerialPortDataDao getSerialPortDataDao() {
        return serialPortDataDao;
    }

    public UIsetDataDao getUIsetDataDao() {
        return uIsetDataDao;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public VidStatusDao getVidStatusDao() {
        return vidStatusDao;
    }

    public WenShiDuDao getWenShiDuDao() {
        return wenShiDuDao;
    }

    public WuangguanInfoDao getWuangguanInfoDao() {
        return wuangguanInfoDao;
    }

    public ZkInfoDao getZkInfoDao() {
        return zkInfoDao;
    }

    public ZksDataDao getZksDataDao() {
        return zksDataDao;
    }

}
