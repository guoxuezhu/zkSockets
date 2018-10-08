package com.lh.zksockets.data.DbDao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.Computer;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.PowerDevice;
import com.lh.zksockets.data.model.Projector;
import com.lh.zksockets.data.model.Users;

import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.DbDao.EventBigDao;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.PowerDeviceDao;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.DbDao.UsersDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig chazuoDataDaoConfig;
    private final DaoConfig computerDaoConfig;
    private final DaoConfig eventBigDaoConfig;
    private final DaoConfig icCardDaoConfig;
    private final DaoConfig iOYuanDaoConfig;
    private final DaoConfig powerDeviceDaoConfig;
    private final DaoConfig projectorDaoConfig;
    private final DaoConfig usersDaoConfig;

    private final ChazuoDataDao chazuoDataDao;
    private final ComputerDao computerDao;
    private final EventBigDao eventBigDao;
    private final IcCardDao icCardDao;
    private final IOYuanDao iOYuanDao;
    private final PowerDeviceDao powerDeviceDao;
    private final ProjectorDao projectorDao;
    private final UsersDao usersDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        chazuoDataDaoConfig = daoConfigMap.get(ChazuoDataDao.class).clone();
        chazuoDataDaoConfig.initIdentityScope(type);

        computerDaoConfig = daoConfigMap.get(ComputerDao.class).clone();
        computerDaoConfig.initIdentityScope(type);

        eventBigDaoConfig = daoConfigMap.get(EventBigDao.class).clone();
        eventBigDaoConfig.initIdentityScope(type);

        icCardDaoConfig = daoConfigMap.get(IcCardDao.class).clone();
        icCardDaoConfig.initIdentityScope(type);

        iOYuanDaoConfig = daoConfigMap.get(IOYuanDao.class).clone();
        iOYuanDaoConfig.initIdentityScope(type);

        powerDeviceDaoConfig = daoConfigMap.get(PowerDeviceDao.class).clone();
        powerDeviceDaoConfig.initIdentityScope(type);

        projectorDaoConfig = daoConfigMap.get(ProjectorDao.class).clone();
        projectorDaoConfig.initIdentityScope(type);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        chazuoDataDao = new ChazuoDataDao(chazuoDataDaoConfig, this);
        computerDao = new ComputerDao(computerDaoConfig, this);
        eventBigDao = new EventBigDao(eventBigDaoConfig, this);
        icCardDao = new IcCardDao(icCardDaoConfig, this);
        iOYuanDao = new IOYuanDao(iOYuanDaoConfig, this);
        powerDeviceDao = new PowerDeviceDao(powerDeviceDaoConfig, this);
        projectorDao = new ProjectorDao(projectorDaoConfig, this);
        usersDao = new UsersDao(usersDaoConfig, this);

        registerDao(ChazuoData.class, chazuoDataDao);
        registerDao(Computer.class, computerDao);
        registerDao(EventBig.class, eventBigDao);
        registerDao(IcCard.class, icCardDao);
        registerDao(IOYuan.class, iOYuanDao);
        registerDao(PowerDevice.class, powerDeviceDao);
        registerDao(Projector.class, projectorDao);
        registerDao(Users.class, usersDao);
    }
    
    public void clear() {
        chazuoDataDaoConfig.clearIdentityScope();
        computerDaoConfig.clearIdentityScope();
        eventBigDaoConfig.clearIdentityScope();
        icCardDaoConfig.clearIdentityScope();
        iOYuanDaoConfig.clearIdentityScope();
        powerDeviceDaoConfig.clearIdentityScope();
        projectorDaoConfig.clearIdentityScope();
        usersDaoConfig.clearIdentityScope();
    }

    public ChazuoDataDao getChazuoDataDao() {
        return chazuoDataDao;
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public EventBigDao getEventBigDao() {
        return eventBigDao;
    }

    public IcCardDao getIcCardDao() {
        return icCardDao;
    }

    public IOYuanDao getIOYuanDao() {
        return iOYuanDao;
    }

    public PowerDeviceDao getPowerDeviceDao() {
        return powerDeviceDao;
    }

    public ProjectorDao getProjectorDao() {
        return projectorDao;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

}
