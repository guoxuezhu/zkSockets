package com.lh.zksockets.data.DbDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        BaseInfoDao.createTable(db, ifNotExists);
        ChazuoDataDao.createTable(db, ifNotExists);
        ComputerDao.createTable(db, ifNotExists);
        DangerOutDao.createTable(db, ifNotExists);
        DangerStatusDao.createTable(db, ifNotExists);
        DoorInfoDao.createTable(db, ifNotExists);
        EventBigDao.createTable(db, ifNotExists);
        EventKejianRestDao.createTable(db, ifNotExists);
        EventShangkeDao.createTable(db, ifNotExists);
        EventXiakeDao.createTable(db, ifNotExists);
        IcCardDao.createTable(db, ifNotExists);
        IoPortDataDao.createTable(db, ifNotExists);
        IOYuanDao.createTable(db, ifNotExists);
        JDQstatusDao.createTable(db, ifNotExists);
        LampDao.createTable(db, ifNotExists);
        LuboInfoDao.createTable(db, ifNotExists);
        MLsListsDao.createTable(db, ifNotExists);
        ProjectorDao.createTable(db, ifNotExists);
        SerialCommandDao.createTable(db, ifNotExists);
        SerialPortDataDao.createTable(db, ifNotExists);
        UDPInfoDao.createTable(db, ifNotExists);
        UsersDao.createTable(db, ifNotExists);
        WenShiDuDao.createTable(db, ifNotExists);
        ZkInfoDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        BaseInfoDao.dropTable(db, ifExists);
        ChazuoDataDao.dropTable(db, ifExists);
        ComputerDao.dropTable(db, ifExists);
        DangerOutDao.dropTable(db, ifExists);
        DangerStatusDao.dropTable(db, ifExists);
        DoorInfoDao.dropTable(db, ifExists);
        EventBigDao.dropTable(db, ifExists);
        EventKejianRestDao.dropTable(db, ifExists);
        EventShangkeDao.dropTable(db, ifExists);
        EventXiakeDao.dropTable(db, ifExists);
        IcCardDao.dropTable(db, ifExists);
        IoPortDataDao.dropTable(db, ifExists);
        IOYuanDao.dropTable(db, ifExists);
        JDQstatusDao.dropTable(db, ifExists);
        LampDao.dropTable(db, ifExists);
        LuboInfoDao.dropTable(db, ifExists);
        MLsListsDao.dropTable(db, ifExists);
        ProjectorDao.dropTable(db, ifExists);
        SerialCommandDao.dropTable(db, ifExists);
        SerialPortDataDao.dropTable(db, ifExists);
        UDPInfoDao.dropTable(db, ifExists);
        UsersDao.dropTable(db, ifExists);
        WenShiDuDao.dropTable(db, ifExists);
        ZkInfoDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(BaseInfoDao.class);
        registerDaoClass(ChazuoDataDao.class);
        registerDaoClass(ComputerDao.class);
        registerDaoClass(DangerOutDao.class);
        registerDaoClass(DangerStatusDao.class);
        registerDaoClass(DoorInfoDao.class);
        registerDaoClass(EventBigDao.class);
        registerDaoClass(EventKejianRestDao.class);
        registerDaoClass(EventShangkeDao.class);
        registerDaoClass(EventXiakeDao.class);
        registerDaoClass(IcCardDao.class);
        registerDaoClass(IoPortDataDao.class);
        registerDaoClass(IOYuanDao.class);
        registerDaoClass(JDQstatusDao.class);
        registerDaoClass(LampDao.class);
        registerDaoClass(LuboInfoDao.class);
        registerDaoClass(MLsListsDao.class);
        registerDaoClass(ProjectorDao.class);
        registerDaoClass(SerialCommandDao.class);
        registerDaoClass(SerialPortDataDao.class);
        registerDaoClass(UDPInfoDao.class);
        registerDaoClass(UsersDao.class);
        registerDaoClass(WenShiDuDao.class);
        registerDaoClass(ZkInfoDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
