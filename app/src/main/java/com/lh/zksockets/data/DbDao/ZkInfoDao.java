package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.ZkInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ZK_INFO".
*/
public class ZkInfoDao extends AbstractDao<ZkInfo, Void> {

    public static final String TABLENAME = "ZK_INFO";

    /**
     * Properties of entity ZkInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Zkname = new Property(0, String.class, "zkname", false, "ZKNAME");
        public final static Property Zkip = new Property(1, String.class, "zkip", false, "ZKIP");
        public final static Property ZkVersion = new Property(2, String.class, "zkVersion", false, "ZK_VERSION");
        public final static Property GeendaoVersion = new Property(3, String.class, "geendaoVersion", false, "GEENDAO_VERSION");
    }


    public ZkInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ZkInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ZK_INFO\" (" + //
                "\"ZKNAME\" TEXT," + // 0: zkname
                "\"ZKIP\" TEXT," + // 1: zkip
                "\"ZK_VERSION\" TEXT," + // 2: zkVersion
                "\"GEENDAO_VERSION\" TEXT);"); // 3: geendaoVersion
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ZK_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ZkInfo entity) {
        stmt.clearBindings();
 
        String zkname = entity.getZkname();
        if (zkname != null) {
            stmt.bindString(1, zkname);
        }
 
        String zkip = entity.getZkip();
        if (zkip != null) {
            stmt.bindString(2, zkip);
        }
 
        String zkVersion = entity.getZkVersion();
        if (zkVersion != null) {
            stmt.bindString(3, zkVersion);
        }
 
        String geendaoVersion = entity.getGeendaoVersion();
        if (geendaoVersion != null) {
            stmt.bindString(4, geendaoVersion);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ZkInfo entity) {
        stmt.clearBindings();
 
        String zkname = entity.getZkname();
        if (zkname != null) {
            stmt.bindString(1, zkname);
        }
 
        String zkip = entity.getZkip();
        if (zkip != null) {
            stmt.bindString(2, zkip);
        }
 
        String zkVersion = entity.getZkVersion();
        if (zkVersion != null) {
            stmt.bindString(3, zkVersion);
        }
 
        String geendaoVersion = entity.getGeendaoVersion();
        if (geendaoVersion != null) {
            stmt.bindString(4, geendaoVersion);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public ZkInfo readEntity(Cursor cursor, int offset) {
        ZkInfo entity = new ZkInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // zkname
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // zkip
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // zkVersion
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // geendaoVersion
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ZkInfo entity, int offset) {
        entity.setZkname(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setZkip(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setZkVersion(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGeendaoVersion(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(ZkInfo entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(ZkInfo entity) {
        return null;
    }

    @Override
    public boolean hasKey(ZkInfo entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
