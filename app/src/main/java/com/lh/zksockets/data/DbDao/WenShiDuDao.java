package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.WenShiDu;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WEN_SHI_DU".
*/
public class WenShiDuDao extends AbstractDao<WenShiDu, Void> {

    public static final String TABLENAME = "WEN_SHI_DU";

    /**
     * Properties of entity WenShiDu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property WenStr = new Property(0, String.class, "wenStr", false, "WEN_STR");
        public final static Property ShiStr = new Property(1, String.class, "shiStr", false, "SHI_STR");
        public final static Property TimeStr = new Property(2, int.class, "timeStr", false, "TIME_STR");
        public final static Property SerialportML = new Property(3, String.class, "serialportML", false, "SERIALPORT_ML");
    }


    public WenShiDuDao(DaoConfig config) {
        super(config);
    }
    
    public WenShiDuDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WEN_SHI_DU\" (" + //
                "\"WEN_STR\" TEXT," + // 0: wenStr
                "\"SHI_STR\" TEXT," + // 1: shiStr
                "\"TIME_STR\" INTEGER NOT NULL ," + // 2: timeStr
                "\"SERIALPORT_ML\" TEXT);"); // 3: serialportML
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WEN_SHI_DU\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WenShiDu entity) {
        stmt.clearBindings();
 
        String wenStr = entity.getWenStr();
        if (wenStr != null) {
            stmt.bindString(1, wenStr);
        }
 
        String shiStr = entity.getShiStr();
        if (shiStr != null) {
            stmt.bindString(2, shiStr);
        }
        stmt.bindLong(3, entity.getTimeStr());
 
        String serialportML = entity.getSerialportML();
        if (serialportML != null) {
            stmt.bindString(4, serialportML);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WenShiDu entity) {
        stmt.clearBindings();
 
        String wenStr = entity.getWenStr();
        if (wenStr != null) {
            stmt.bindString(1, wenStr);
        }
 
        String shiStr = entity.getShiStr();
        if (shiStr != null) {
            stmt.bindString(2, shiStr);
        }
        stmt.bindLong(3, entity.getTimeStr());
 
        String serialportML = entity.getSerialportML();
        if (serialportML != null) {
            stmt.bindString(4, serialportML);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public WenShiDu readEntity(Cursor cursor, int offset) {
        WenShiDu entity = new WenShiDu( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // wenStr
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // shiStr
            cursor.getInt(offset + 2), // timeStr
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // serialportML
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WenShiDu entity, int offset) {
        entity.setWenStr(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setShiStr(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTimeStr(cursor.getInt(offset + 2));
        entity.setSerialportML(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(WenShiDu entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(WenShiDu entity) {
        return null;
    }

    @Override
    public boolean hasKey(WenShiDu entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
