package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.EventXiake;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EVENT_XIAKE".
*/
public class EventXiakeDao extends AbstractDao<EventXiake, Void> {

    public static final String TABLENAME = "EVENT_XIAKE";

    /**
     * Properties of entity EventXiake.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property EventType = new Property(0, int.class, "eventType", false, "EVENT_TYPE");
        public final static Property EventId = new Property(1, Long.class, "eventId", false, "EVENT_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Status = new Property(3, int.class, "status", false, "STATUS");
        public final static Property IsChecked = new Property(4, boolean.class, "isChecked", false, "IS_CHECKED");
        public final static Property Time = new Property(5, int.class, "time", false, "TIME");
    }


    public EventXiakeDao(DaoConfig config) {
        super(config);
    }
    
    public EventXiakeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EVENT_XIAKE\" (" + //
                "\"EVENT_TYPE\" INTEGER NOT NULL ," + // 0: eventType
                "\"EVENT_ID\" INTEGER," + // 1: eventId
                "\"NAME\" TEXT," + // 2: name
                "\"STATUS\" INTEGER NOT NULL ," + // 3: status
                "\"IS_CHECKED\" INTEGER NOT NULL ," + // 4: isChecked
                "\"TIME\" INTEGER NOT NULL );"); // 5: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EVENT_XIAKE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EventXiake entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getEventType());
 
        Long eventId = entity.getEventId();
        if (eventId != null) {
            stmt.bindLong(2, eventId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getStatus());
        stmt.bindLong(5, entity.getIsChecked() ? 1L: 0L);
        stmt.bindLong(6, entity.getTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EventXiake entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getEventType());
 
        Long eventId = entity.getEventId();
        if (eventId != null) {
            stmt.bindLong(2, eventId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getStatus());
        stmt.bindLong(5, entity.getIsChecked() ? 1L: 0L);
        stmt.bindLong(6, entity.getTime());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public EventXiake readEntity(Cursor cursor, int offset) {
        EventXiake entity = new EventXiake( //
            cursor.getInt(offset + 0), // eventType
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // eventId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.getInt(offset + 3), // status
            cursor.getShort(offset + 4) != 0, // isChecked
            cursor.getInt(offset + 5) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EventXiake entity, int offset) {
        entity.setEventType(cursor.getInt(offset + 0));
        entity.setEventId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStatus(cursor.getInt(offset + 3));
        entity.setIsChecked(cursor.getShort(offset + 4) != 0);
        entity.setTime(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(EventXiake entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(EventXiake entity) {
        return null;
    }

    @Override
    public boolean hasKey(EventXiake entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
