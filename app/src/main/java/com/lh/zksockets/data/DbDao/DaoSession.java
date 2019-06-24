package com.lh.zksockets.data.DbDao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.ZkInfo;
import com.lh.zksockets.data.model.EventKejianRest;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.data.model.EventXiake;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.data.model.Computer;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.Projector;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;

import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.DbDao.EventKejianRestDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.EventBigDao;
import com.lh.zksockets.data.DbDao.EventXiakeDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dangerOutDaoConfig;
    private final DaoConfig zkInfoDaoConfig;
    private final DaoConfig eventKejianRestDaoConfig;
    private final DaoConfig ioPortDataDaoConfig;
    private final DaoConfig baseInfoDaoConfig;
    private final DaoConfig eventBigDaoConfig;
    private final DaoConfig eventXiakeDaoConfig;
    private final DaoConfig usersDaoConfig;
    private final DaoConfig computerDaoConfig;
    private final DaoConfig chazuoDataDaoConfig;
    private final DaoConfig mLsListsDaoConfig;
    private final DaoConfig wenShiDuDaoConfig;
    private final DaoConfig eventShangkeDaoConfig;
    private final DaoConfig icCardDaoConfig;
    private final DaoConfig projectorDaoConfig;
    private final DaoConfig jDQstatusDaoConfig;
    private final DaoConfig luboInfoDaoConfig;
    private final DaoConfig iOYuanDaoConfig;
    private final DaoConfig lampDaoConfig;
    private final DaoConfig serialCommandDaoConfig;
    private final DaoConfig serialPortDataDaoConfig;

    private final DangerOutDao dangerOutDao;
    private final ZkInfoDao zkInfoDao;
    private final EventKejianRestDao eventKejianRestDao;
    private final IoPortDataDao ioPortDataDao;
    private final BaseInfoDao baseInfoDao;
    private final EventBigDao eventBigDao;
    private final EventXiakeDao eventXiakeDao;
    private final UsersDao usersDao;
    private final ComputerDao computerDao;
    private final ChazuoDataDao chazuoDataDao;
    private final MLsListsDao mLsListsDao;
    private final WenShiDuDao wenShiDuDao;
    private final EventShangkeDao eventShangkeDao;
    private final IcCardDao icCardDao;
    private final ProjectorDao projectorDao;
    private final JDQstatusDao jDQstatusDao;
    private final LuboInfoDao luboInfoDao;
    private final IOYuanDao iOYuanDao;
    private final LampDao lampDao;
    private final SerialCommandDao serialCommandDao;
    private final SerialPortDataDao serialPortDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dangerOutDaoConfig = daoConfigMap.get(DangerOutDao.class).clone();
        dangerOutDaoConfig.initIdentityScope(type);

        zkInfoDaoConfig = daoConfigMap.get(ZkInfoDao.class).clone();
        zkInfoDaoConfig.initIdentityScope(type);

        eventKejianRestDaoConfig = daoConfigMap.get(EventKejianRestDao.class).clone();
        eventKejianRestDaoConfig.initIdentityScope(type);

        ioPortDataDaoConfig = daoConfigMap.get(IoPortDataDao.class).clone();
        ioPortDataDaoConfig.initIdentityScope(type);

        baseInfoDaoConfig = daoConfigMap.get(BaseInfoDao.class).clone();
        baseInfoDaoConfig.initIdentityScope(type);

        eventBigDaoConfig = daoConfigMap.get(EventBigDao.class).clone();
        eventBigDaoConfig.initIdentityScope(type);

        eventXiakeDaoConfig = daoConfigMap.get(EventXiakeDao.class).clone();
        eventXiakeDaoConfig.initIdentityScope(type);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        computerDaoConfig = daoConfigMap.get(ComputerDao.class).clone();
        computerDaoConfig.initIdentityScope(type);

        chazuoDataDaoConfig = daoConfigMap.get(ChazuoDataDao.class).clone();
        chazuoDataDaoConfig.initIdentityScope(type);

        mLsListsDaoConfig = daoConfigMap.get(MLsListsDao.class).clone();
        mLsListsDaoConfig.initIdentityScope(type);

        wenShiDuDaoConfig = daoConfigMap.get(WenShiDuDao.class).clone();
        wenShiDuDaoConfig.initIdentityScope(type);

        eventShangkeDaoConfig = daoConfigMap.get(EventShangkeDao.class).clone();
        eventShangkeDaoConfig.initIdentityScope(type);

        icCardDaoConfig = daoConfigMap.get(IcCardDao.class).clone();
        icCardDaoConfig.initIdentityScope(type);

        projectorDaoConfig = daoConfigMap.get(ProjectorDao.class).clone();
        projectorDaoConfig.initIdentityScope(type);

        jDQstatusDaoConfig = daoConfigMap.get(JDQstatusDao.class).clone();
        jDQstatusDaoConfig.initIdentityScope(type);

        luboInfoDaoConfig = daoConfigMap.get(LuboInfoDao.class).clone();
        luboInfoDaoConfig.initIdentityScope(type);

        iOYuanDaoConfig = daoConfigMap.get(IOYuanDao.class).clone();
        iOYuanDaoConfig.initIdentityScope(type);

        lampDaoConfig = daoConfigMap.get(LampDao.class).clone();
        lampDaoConfig.initIdentityScope(type);

        serialCommandDaoConfig = daoConfigMap.get(SerialCommandDao.class).clone();
        serialCommandDaoConfig.initIdentityScope(type);

        serialPortDataDaoConfig = daoConfigMap.get(SerialPortDataDao.class).clone();
        serialPortDataDaoConfig.initIdentityScope(type);

        dangerOutDao = new DangerOutDao(dangerOutDaoConfig, this);
        zkInfoDao = new ZkInfoDao(zkInfoDaoConfig, this);
        eventKejianRestDao = new EventKejianRestDao(eventKejianRestDaoConfig, this);
        ioPortDataDao = new IoPortDataDao(ioPortDataDaoConfig, this);
        baseInfoDao = new BaseInfoDao(baseInfoDaoConfig, this);
        eventBigDao = new EventBigDao(eventBigDaoConfig, this);
        eventXiakeDao = new EventXiakeDao(eventXiakeDaoConfig, this);
        usersDao = new UsersDao(usersDaoConfig, this);
        computerDao = new ComputerDao(computerDaoConfig, this);
        chazuoDataDao = new ChazuoDataDao(chazuoDataDaoConfig, this);
        mLsListsDao = new MLsListsDao(mLsListsDaoConfig, this);
        wenShiDuDao = new WenShiDuDao(wenShiDuDaoConfig, this);
        eventShangkeDao = new EventShangkeDao(eventShangkeDaoConfig, this);
        icCardDao = new IcCardDao(icCardDaoConfig, this);
        projectorDao = new ProjectorDao(projectorDaoConfig, this);
        jDQstatusDao = new JDQstatusDao(jDQstatusDaoConfig, this);
        luboInfoDao = new LuboInfoDao(luboInfoDaoConfig, this);
        iOYuanDao = new IOYuanDao(iOYuanDaoConfig, this);
        lampDao = new LampDao(lampDaoConfig, this);
        serialCommandDao = new SerialCommandDao(serialCommandDaoConfig, this);
        serialPortDataDao = new SerialPortDataDao(serialPortDataDaoConfig, this);

        registerDao(DangerOut.class, dangerOutDao);
        registerDao(ZkInfo.class, zkInfoDao);
        registerDao(EventKejianRest.class, eventKejianRestDao);
        registerDao(IoPortData.class, ioPortDataDao);
        registerDao(BaseInfo.class, baseInfoDao);
        registerDao(EventBig.class, eventBigDao);
        registerDao(EventXiake.class, eventXiakeDao);
        registerDao(Users.class, usersDao);
        registerDao(Computer.class, computerDao);
        registerDao(ChazuoData.class, chazuoDataDao);
        registerDao(MLsLists.class, mLsListsDao);
        registerDao(WenShiDu.class, wenShiDuDao);
        registerDao(EventShangke.class, eventShangkeDao);
        registerDao(IcCard.class, icCardDao);
        registerDao(Projector.class, projectorDao);
        registerDao(JDQstatus.class, jDQstatusDao);
        registerDao(LuboInfo.class, luboInfoDao);
        registerDao(IOYuan.class, iOYuanDao);
        registerDao(Lamp.class, lampDao);
        registerDao(SerialCommand.class, serialCommandDao);
        registerDao(SerialPortData.class, serialPortDataDao);
    }
    
    public void clear() {
        dangerOutDaoConfig.clearIdentityScope();
        zkInfoDaoConfig.clearIdentityScope();
        eventKejianRestDaoConfig.clearIdentityScope();
        ioPortDataDaoConfig.clearIdentityScope();
        baseInfoDaoConfig.clearIdentityScope();
        eventBigDaoConfig.clearIdentityScope();
        eventXiakeDaoConfig.clearIdentityScope();
        usersDaoConfig.clearIdentityScope();
        computerDaoConfig.clearIdentityScope();
        chazuoDataDaoConfig.clearIdentityScope();
        mLsListsDaoConfig.clearIdentityScope();
        wenShiDuDaoConfig.clearIdentityScope();
        eventShangkeDaoConfig.clearIdentityScope();
        icCardDaoConfig.clearIdentityScope();
        projectorDaoConfig.clearIdentityScope();
        jDQstatusDaoConfig.clearIdentityScope();
        luboInfoDaoConfig.clearIdentityScope();
        iOYuanDaoConfig.clearIdentityScope();
        lampDaoConfig.clearIdentityScope();
        serialCommandDaoConfig.clearIdentityScope();
        serialPortDataDaoConfig.clearIdentityScope();
    }

    public DangerOutDao getDangerOutDao() {
        return dangerOutDao;
    }

    public ZkInfoDao getZkInfoDao() {
        return zkInfoDao;
    }

    public EventKejianRestDao getEventKejianRestDao() {
        return eventKejianRestDao;
    }

    public IoPortDataDao getIoPortDataDao() {
        return ioPortDataDao;
    }

    public BaseInfoDao getBaseInfoDao() {
        return baseInfoDao;
    }

    public EventBigDao getEventBigDao() {
        return eventBigDao;
    }

    public EventXiakeDao getEventXiakeDao() {
        return eventXiakeDao;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public ChazuoDataDao getChazuoDataDao() {
        return chazuoDataDao;
    }

    public MLsListsDao getMLsListsDao() {
        return mLsListsDao;
    }

    public WenShiDuDao getWenShiDuDao() {
        return wenShiDuDao;
    }

    public EventShangkeDao getEventShangkeDao() {
        return eventShangkeDao;
    }

    public IcCardDao getIcCardDao() {
        return icCardDao;
    }

    public ProjectorDao getProjectorDao() {
        return projectorDao;
    }

    public JDQstatusDao getJDQstatusDao() {
        return jDQstatusDao;
    }

    public LuboInfoDao getLuboInfoDao() {
        return luboInfoDao;
    }

    public IOYuanDao getIOYuanDao() {
        return iOYuanDao;
    }

    public LampDao getLampDao() {
        return lampDao;
    }

    public SerialCommandDao getSerialCommandDao() {
        return serialCommandDao;
    }

    public SerialPortDataDao getSerialPortDataDao() {
        return serialPortDataDao;
    }

}
