package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.IoPortData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IO_PORT_DATA".
*/
public class IoPortDataDao extends AbstractDao<IoPortData, Long> {

    public static final String TABLENAME = "IO_PORT_DATA";

    /**
     * Properties of entity IoPortData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property DeviceName = new Property(2, String.class, "deviceName", false, "DEVICE_NAME");
        public final static Property IoOutStatus = new Property(3, int.class, "ioOutStatus", false, "IO_OUT_STATUS");
        public final static Property Time = new Property(4, int.class, "time", false, "TIME");
    }


    public IoPortDataDao(DaoConfig config) {
        super(config);
    }
    
    public IoPortDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IO_PORT_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"DEVICE_NAME\" TEXT," + // 2: deviceName
                "\"IO_OUT_STATUS\" INTEGER NOT NULL ," + // 3: ioOutStatus
                "\"TIME\" INTEGER NOT NULL );"); // 4: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IO_PORT_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IoPortData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String deviceName = entity.getDeviceName();
        if (deviceName != null) {
            stmt.bindString(3, deviceName);
        }
        stmt.bindLong(4, entity.getIoOutStatus());
        stmt.bindLong(5, entity.getTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IoPortData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String deviceName = entity.getDeviceName();
        if (deviceName != null) {
            stmt.bindString(3, deviceName);
        }
        stmt.bindLong(4, entity.getIoOutStatus());
        stmt.bindLong(5, entity.getTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IoPortData readEntity(Cursor cursor, int offset) {
        IoPortData entity = new IoPortData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // deviceName
            cursor.getInt(offset + 3), // ioOutStatus
            cursor.getInt(offset + 4) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IoPortData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDeviceName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIoOutStatus(cursor.getInt(offset + 3));
        entity.setTime(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IoPortData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IoPortData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IoPortData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
