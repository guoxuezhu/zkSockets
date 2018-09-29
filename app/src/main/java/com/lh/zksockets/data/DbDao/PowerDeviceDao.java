package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.PowerDevice;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "POWER_DEVICE".
*/
public class PowerDeviceDao extends AbstractDao<PowerDevice, Long> {

    public static final String TABLENAME = "POWER_DEVICE";

    /**
     * Properties of entity PowerDevice.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ChazuoId = new Property(0, Long.class, "chazuoId", true, "_id");
        public final static Property ChazuoName = new Property(1, String.class, "chazuoName", false, "CHAZUO_NAME");
        public final static Property BindName = new Property(2, String.class, "bindName", false, "BIND_NAME");
        public final static Property OpenTime = new Property(3, int.class, "openTime", false, "OPEN_TIME");
        public final static Property ClosedTime = new Property(4, int.class, "closedTime", false, "CLOSED_TIME");
    }


    public PowerDeviceDao(DaoConfig config) {
        super(config);
    }
    
    public PowerDeviceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"POWER_DEVICE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: chazuoId
                "\"CHAZUO_NAME\" TEXT," + // 1: chazuoName
                "\"BIND_NAME\" TEXT," + // 2: bindName
                "\"OPEN_TIME\" INTEGER NOT NULL ," + // 3: openTime
                "\"CLOSED_TIME\" INTEGER NOT NULL );"); // 4: closedTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"POWER_DEVICE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PowerDevice entity) {
        stmt.clearBindings();
 
        Long chazuoId = entity.getChazuoId();
        if (chazuoId != null) {
            stmt.bindLong(1, chazuoId);
        }
 
        String chazuoName = entity.getChazuoName();
        if (chazuoName != null) {
            stmt.bindString(2, chazuoName);
        }
 
        String bindName = entity.getBindName();
        if (bindName != null) {
            stmt.bindString(3, bindName);
        }
        stmt.bindLong(4, entity.getOpenTime());
        stmt.bindLong(5, entity.getClosedTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PowerDevice entity) {
        stmt.clearBindings();
 
        Long chazuoId = entity.getChazuoId();
        if (chazuoId != null) {
            stmt.bindLong(1, chazuoId);
        }
 
        String chazuoName = entity.getChazuoName();
        if (chazuoName != null) {
            stmt.bindString(2, chazuoName);
        }
 
        String bindName = entity.getBindName();
        if (bindName != null) {
            stmt.bindString(3, bindName);
        }
        stmt.bindLong(4, entity.getOpenTime());
        stmt.bindLong(5, entity.getClosedTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PowerDevice readEntity(Cursor cursor, int offset) {
        PowerDevice entity = new PowerDevice( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // chazuoId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // chazuoName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // bindName
            cursor.getInt(offset + 3), // openTime
            cursor.getInt(offset + 4) // closedTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PowerDevice entity, int offset) {
        entity.setChazuoId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChazuoName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBindName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOpenTime(cursor.getInt(offset + 3));
        entity.setClosedTime(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PowerDevice entity, long rowId) {
        entity.setChazuoId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PowerDevice entity) {
        if(entity != null) {
            return entity.getChazuoId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PowerDevice entity) {
        return entity.getChazuoId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
