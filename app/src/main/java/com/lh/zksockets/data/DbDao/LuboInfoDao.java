package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.LuboInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LUBO_INFO".
*/
public class LuboInfoDao extends AbstractDao<LuboInfo, Void> {

    public static final String TABLENAME = "LUBO_INFO";

    /**
     * Properties of entity LuboInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property IP = new Property(0, String.class, "IP", false, "IP");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property Password = new Property(2, String.class, "Password", false, "PASSWORD");
        public final static Property Token = new Property(3, String.class, "token", false, "TOKEN");
        public final static Property Status = new Property(4, int.class, "status", false, "STATUS");
    }


    public LuboInfoDao(DaoConfig config) {
        super(config);
    }
    
    public LuboInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LUBO_INFO\" (" + //
                "\"IP\" TEXT," + // 0: IP
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"PASSWORD\" TEXT," + // 2: Password
                "\"TOKEN\" TEXT," + // 3: token
                "\"STATUS\" INTEGER NOT NULL );"); // 4: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LUBO_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LuboInfo entity) {
        stmt.clearBindings();
 
        String IP = entity.getIP();
        if (IP != null) {
            stmt.bindString(1, IP);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String Password = entity.getPassword();
        if (Password != null) {
            stmt.bindString(3, Password);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(4, token);
        }
        stmt.bindLong(5, entity.getStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LuboInfo entity) {
        stmt.clearBindings();
 
        String IP = entity.getIP();
        if (IP != null) {
            stmt.bindString(1, IP);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String Password = entity.getPassword();
        if (Password != null) {
            stmt.bindString(3, Password);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(4, token);
        }
        stmt.bindLong(5, entity.getStatus());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LuboInfo readEntity(Cursor cursor, int offset) {
        LuboInfo entity = new LuboInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // IP
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Password
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // token
            cursor.getInt(offset + 4) // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LuboInfo entity, int offset) {
        entity.setIP(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setToken(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setStatus(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LuboInfo entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LuboInfo entity) {
        return null;
    }

    @Override
    public boolean hasKey(LuboInfo entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
