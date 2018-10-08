package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.EventBig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EVENT_BIG".
*/
public class EventBigDao extends AbstractDao<EventBig, Long> {

    public static final String TABLENAME = "EVENT_BIG";

    /**
     * Properties of entity EventBig.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property CheckedIds = new Property(2, String.class, "checkedIds", false, "CHECKED_IDS");
        public final static Property CheckedNameStr = new Property(3, String.class, "checkedNameStr", false, "CHECKED_NAME_STR");
        public final static Property EventBaseString = new Property(4, String.class, "eventBaseString", false, "EVENT_BASE_STRING");
    }


    public EventBigDao(DaoConfig config) {
        super(config);
    }
    
    public EventBigDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EVENT_BIG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"CHECKED_IDS\" TEXT," + // 2: checkedIds
                "\"CHECKED_NAME_STR\" TEXT," + // 3: checkedNameStr
                "\"EVENT_BASE_STRING\" TEXT);"); // 4: eventBaseString
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EVENT_BIG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EventBig entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String checkedIds = entity.getCheckedIds();
        if (checkedIds != null) {
            stmt.bindString(3, checkedIds);
        }
 
        String checkedNameStr = entity.getCheckedNameStr();
        if (checkedNameStr != null) {
            stmt.bindString(4, checkedNameStr);
        }
 
        String eventBaseString = entity.getEventBaseString();
        if (eventBaseString != null) {
            stmt.bindString(5, eventBaseString);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EventBig entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String checkedIds = entity.getCheckedIds();
        if (checkedIds != null) {
            stmt.bindString(3, checkedIds);
        }
 
        String checkedNameStr = entity.getCheckedNameStr();
        if (checkedNameStr != null) {
            stmt.bindString(4, checkedNameStr);
        }
 
        String eventBaseString = entity.getEventBaseString();
        if (eventBaseString != null) {
            stmt.bindString(5, eventBaseString);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public EventBig readEntity(Cursor cursor, int offset) {
        EventBig entity = new EventBig( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // checkedIds
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // checkedNameStr
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // eventBaseString
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EventBig entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCheckedIds(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCheckedNameStr(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEventBaseString(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EventBig entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EventBig entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EventBig entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
