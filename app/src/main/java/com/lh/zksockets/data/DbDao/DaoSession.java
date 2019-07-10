package com.lh.zksockets.data.DbDao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.Computer;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.DoorInfo;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.data.model.EventKejianRest;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.data.model.EventXiake;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.Projector;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.data.model.ZkInfo;

import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.DoorInfoDao;
import com.lh.zksockets.data.DbDao.EventBigDao;
import com.lh.zksockets.data.DbDao.EventKejianRestDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.EventXiakeDao;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig baseInfoDaoConfig;
    private final DaoConfig chazuoDataDaoConfig;
    private final DaoConfig computerDaoConfig;
    private final DaoConfig dangerOutDaoConfig;
    private final DaoConfig doorInfoDaoConfig;
    private final DaoConfig eventBigDaoConfig;
    private final DaoConfig eventKejianRestDaoConfig;
    private final DaoConfig eventShangkeDaoConfig;
    private final DaoConfig eventXiakeDaoConfig;
    private final DaoConfig icCardDaoConfig;
    private final DaoConfig ioPortDataDaoConfig;
    private final DaoConfig iOYuanDaoConfig;
    private final DaoConfig jDQstatusDaoConfig;
    private final DaoConfig lampDaoConfig;
    private final DaoConfig luboInfoDaoConfig;
    private final DaoConfig mLsListsDaoConfig;
    private final DaoConfig projectorDaoConfig;
    private final DaoConfig serialCommandDaoConfig;
    private final DaoConfig serialPortDataDaoConfig;
    private final DaoConfig usersDaoConfig;
    private final DaoConfig wenShiDuDaoConfig;
    private final DaoConfig zkInfoDaoConfig;

    private final BaseInfoDao baseInfoDao;
    private final ChazuoDataDao chazuoDataDao;
    private final ComputerDao computerDao;
    private final DangerOutDao dangerOutDao;
    private final DoorInfoDao doorInfoDao;
    private final EventBigDao eventBigDao;
    private final EventKejianRestDao eventKejianRestDao;
    private final EventShangkeDao eventShangkeDao;
    private final EventXiakeDao eventXiakeDao;
    private final IcCardDao icCardDao;
    private final IoPortDataDao ioPortDataDao;
    private final IOYuanDao iOYuanDao;
    private final JDQstatusDao jDQstatusDao;
    private final LampDao lampDao;
    private final LuboInfoDao luboInfoDao;
    private final MLsListsDao mLsListsDao;
    private final ProjectorDao projectorDao;
    private final SerialCommandDao serialCommandDao;
    private final SerialPortDataDao serialPortDataDao;
    private final UsersDao usersDao;
    private final WenShiDuDao wenShiDuDao;
    private final ZkInfoDao zkInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        baseInfoDaoConfig = daoConfigMap.get(BaseInfoDao.class).clone();
        baseInfoDaoConfig.initIdentityScope(type);

        chazuoDataDaoConfig = daoConfigMap.get(ChazuoDataDao.class).clone();
        chazuoDataDaoConfig.initIdentityScope(type);

        computerDaoConfig = daoConfigMap.get(ComputerDao.class).clone();
        computerDaoConfig.initIdentityScope(type);

        dangerOutDaoConfig = daoConfigMap.get(DangerOutDao.class).clone();
        dangerOutDaoConfig.initIdentityScope(type);

        doorInfoDaoConfig = daoConfigMap.get(DoorInfoDao.class).clone();
        doorInfoDaoConfig.initIdentityScope(type);

        eventBigDaoConfig = daoConfigMap.get(EventBigDao.class).clone();
        eventBigDaoConfig.initIdentityScope(type);

        eventKejianRestDaoConfig = daoConfigMap.get(EventKejianRestDao.class).clone();
        eventKejianRestDaoConfig.initIdentityScope(type);

        eventShangkeDaoConfig = daoConfigMap.get(EventShangkeDao.class).clone();
        eventShangkeDaoConfig.initIdentityScope(type);

        eventXiakeDaoConfig = daoConfigMap.get(EventXiakeDao.class).clone();
        eventXiakeDaoConfig.initIdentityScope(type);

        icCardDaoConfig = daoConfigMap.get(IcCardDao.class).clone();
        icCardDaoConfig.initIdentityScope(type);

        ioPortDataDaoConfig = daoConfigMap.get(IoPortDataDao.class).clone();
        ioPortDataDaoConfig.initIdentityScope(type);

        iOYuanDaoConfig = daoConfigMap.get(IOYuanDao.class).clone();
        iOYuanDaoConfig.initIdentityScope(type);

        jDQstatusDaoConfig = daoConfigMap.get(JDQstatusDao.class).clone();
        jDQstatusDaoConfig.initIdentityScope(type);

        lampDaoConfig = daoConfigMap.get(LampDao.class).clone();
        lampDaoConfig.initIdentityScope(type);

        luboInfoDaoConfig = daoConfigMap.get(LuboInfoDao.class).clone();
        luboInfoDaoConfig.initIdentityScope(type);

        mLsListsDaoConfig = daoConfigMap.get(MLsListsDao.class).clone();
        mLsListsDaoConfig.initIdentityScope(type);

        projectorDaoConfig = daoConfigMap.get(ProjectorDao.class).clone();
        projectorDaoConfig.initIdentityScope(type);

        serialCommandDaoConfig = daoConfigMap.get(SerialCommandDao.class).clone();
        serialCommandDaoConfig.initIdentityScope(type);

        serialPortDataDaoConfig = daoConfigMap.get(SerialPortDataDao.class).clone();
        serialPortDataDaoConfig.initIdentityScope(type);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        wenShiDuDaoConfig = daoConfigMap.get(WenShiDuDao.class).clone();
        wenShiDuDaoConfig.initIdentityScope(type);

        zkInfoDaoConfig = daoConfigMap.get(ZkInfoDao.class).clone();
        zkInfoDaoConfig.initIdentityScope(type);

        baseInfoDao = new BaseInfoDao(baseInfoDaoConfig, this);
        chazuoDataDao = new ChazuoDataDao(chazuoDataDaoConfig, this);
        computerDao = new ComputerDao(computerDaoConfig, this);
        dangerOutDao = new DangerOutDao(dangerOutDaoConfig, this);
        doorInfoDao = new DoorInfoDao(doorInfoDaoConfig, this);
        eventBigDao = new EventBigDao(eventBigDaoConfig, this);
        eventKejianRestDao = new EventKejianRestDao(eventKejianRestDaoConfig, this);
        eventShangkeDao = new EventShangkeDao(eventShangkeDaoConfig, this);
        eventXiakeDao = new EventXiakeDao(eventXiakeDaoConfig, this);
        icCardDao = new IcCardDao(icCardDaoConfig, this);
        ioPortDataDao = new IoPortDataDao(ioPortDataDaoConfig, this);
        iOYuanDao = new IOYuanDao(iOYuanDaoConfig, this);
        jDQstatusDao = new JDQstatusDao(jDQstatusDaoConfig, this);
        lampDao = new LampDao(lampDaoConfig, this);
        luboInfoDao = new LuboInfoDao(luboInfoDaoConfig, this);
        mLsListsDao = new MLsListsDao(mLsListsDaoConfig, this);
        projectorDao = new ProjectorDao(projectorDaoConfig, this);
        serialCommandDao = new SerialCommandDao(serialCommandDaoConfig, this);
        serialPortDataDao = new SerialPortDataDao(serialPortDataDaoConfig, this);
        usersDao = new UsersDao(usersDaoConfig, this);
        wenShiDuDao = new WenShiDuDao(wenShiDuDaoConfig, this);
        zkInfoDao = new ZkInfoDao(zkInfoDaoConfig, this);

        registerDao(BaseInfo.class, baseInfoDao);
        registerDao(ChazuoData.class, chazuoDataDao);
        registerDao(Computer.class, computerDao);
        registerDao(DangerOut.class, dangerOutDao);
        registerDao(DoorInfo.class, doorInfoDao);
        registerDao(EventBig.class, eventBigDao);
        registerDao(EventKejianRest.class, eventKejianRestDao);
        registerDao(EventShangke.class, eventShangkeDao);
        registerDao(EventXiake.class, eventXiakeDao);
        registerDao(IcCard.class, icCardDao);
        registerDao(IoPortData.class, ioPortDataDao);
        registerDao(IOYuan.class, iOYuanDao);
        registerDao(JDQstatus.class, jDQstatusDao);
        registerDao(Lamp.class, lampDao);
        registerDao(LuboInfo.class, luboInfoDao);
        registerDao(MLsLists.class, mLsListsDao);
        registerDao(Projector.class, projectorDao);
        registerDao(SerialCommand.class, serialCommandDao);
        registerDao(SerialPortData.class, serialPortDataDao);
        registerDao(Users.class, usersDao);
        registerDao(WenShiDu.class, wenShiDuDao);
        registerDao(ZkInfo.class, zkInfoDao);
    }
    
    public void clear() {
        baseInfoDaoConfig.clearIdentityScope();
        chazuoDataDaoConfig.clearIdentityScope();
        computerDaoConfig.clearIdentityScope();
        dangerOutDaoConfig.clearIdentityScope();
        doorInfoDaoConfig.clearIdentityScope();
        eventBigDaoConfig.clearIdentityScope();
        eventKejianRestDaoConfig.clearIdentityScope();
        eventShangkeDaoConfig.clearIdentityScope();
        eventXiakeDaoConfig.clearIdentityScope();
        icCardDaoConfig.clearIdentityScope();
        ioPortDataDaoConfig.clearIdentityScope();
        iOYuanDaoConfig.clearIdentityScope();
        jDQstatusDaoConfig.clearIdentityScope();
        lampDaoConfig.clearIdentityScope();
        luboInfoDaoConfig.clearIdentityScope();
        mLsListsDaoConfig.clearIdentityScope();
        projectorDaoConfig.clearIdentityScope();
        serialCommandDaoConfig.clearIdentityScope();
        serialPortDataDaoConfig.clearIdentityScope();
        usersDaoConfig.clearIdentityScope();
        wenShiDuDaoConfig.clearIdentityScope();
        zkInfoDaoConfig.clearIdentityScope();
    }

    public BaseInfoDao getBaseInfoDao() {
        return baseInfoDao;
    }

    public ChazuoDataDao getChazuoDataDao() {
        return chazuoDataDao;
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public DangerOutDao getDangerOutDao() {
        return dangerOutDao;
    }

    public DoorInfoDao getDoorInfoDao() {
        return doorInfoDao;
    }

    public EventBigDao getEventBigDao() {
        return eventBigDao;
    }

    public EventKejianRestDao getEventKejianRestDao() {
        return eventKejianRestDao;
    }

    public EventShangkeDao getEventShangkeDao() {
        return eventShangkeDao;
    }

    public EventXiakeDao getEventXiakeDao() {
        return eventXiakeDao;
    }

    public IcCardDao getIcCardDao() {
        return icCardDao;
    }

    public IoPortDataDao getIoPortDataDao() {
        return ioPortDataDao;
    }

    public IOYuanDao getIOYuanDao() {
        return iOYuanDao;
    }

    public JDQstatusDao getJDQstatusDao() {
        return jDQstatusDao;
    }

    public LampDao getLampDao() {
        return lampDao;
    }

    public LuboInfoDao getLuboInfoDao() {
        return luboInfoDao;
    }

    public MLsListsDao getMLsListsDao() {
        return mLsListsDao;
    }

    public ProjectorDao getProjectorDao() {
        return projectorDao;
    }

    public SerialCommandDao getSerialCommandDao() {
        return serialCommandDao;
    }

    public SerialPortDataDao getSerialPortDataDao() {
        return serialPortDataDao;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public WenShiDuDao getWenShiDuDao() {
        return wenShiDuDao;
    }

    public ZkInfoDao getZkInfoDao() {
        return zkInfoDao;
    }

}
