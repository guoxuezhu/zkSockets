package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.IOYuan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IOYUAN".
*/
public class IOYuanDao extends AbstractDao<IOYuan, Long> {

    public static final String TABLENAME = "IOYUAN";

    /**
     * Properties of entity IOYuan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DangerIoStatus = new Property(1, int.class, "dangerIoStatus", false, "DANGER_IO_STATUS");
        public final static Property DangerMl = new Property(2, String.class, "dangerMl", false, "DANGER_ML");
        public final static Property NoDangerMl = new Property(3, String.class, "noDangerMl", false, "NO_DANGER_ML");
    }


    public IOYuanDao(DaoConfig config) {
        super(config);
    }
    
    public IOYuanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IOYUAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DANGER_IO_STATUS\" INTEGER NOT NULL ," + // 1: dangerIoStatus
                "\"DANGER_ML\" TEXT," + // 2: dangerMl
                "\"NO_DANGER_ML\" TEXT);"); // 3: noDangerMl
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IOYUAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IOYuan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDangerIoStatus());
 
        String dangerMl = entity.getDangerMl();
        if (dangerMl != null) {
            stmt.bindString(3, dangerMl);
        }
 
        String noDangerMl = entity.getNoDangerMl();
        if (noDangerMl != null) {
            stmt.bindString(4, noDangerMl);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IOYuan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDangerIoStatus());
 
        String dangerMl = entity.getDangerMl();
        if (dangerMl != null) {
            stmt.bindString(3, dangerMl);
        }
 
        String noDangerMl = entity.getNoDangerMl();
        if (noDangerMl != null) {
            stmt.bindString(4, noDangerMl);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IOYuan readEntity(Cursor cursor, int offset) {
        IOYuan entity = new IOYuan( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // dangerIoStatus
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // dangerMl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // noDangerMl
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IOYuan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDangerIoStatus(cursor.getInt(offset + 1));
        entity.setDangerMl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNoDangerMl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IOYuan entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IOYuan entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IOYuan entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
