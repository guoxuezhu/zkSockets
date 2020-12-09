package com.lh.zksockets.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lh.zksockets.data.model.EventShangke;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EVENT_SHANGKE".
*/
public class EventShangkeDao extends AbstractDao<EventShangke, Long> {

    public static final String TABLENAME = "EVENT_SHANGKE";

    /**
     * Properties of entity EventShangke.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property EventType = new Property(0, int.class, "eventType", false, "EVENT_TYPE");
        public final static Property EventId = new Property(1, Long.class, "eventId", true, "_id");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Status = new Property(3, int.class, "status", false, "STATUS");
        public final static Property IsChecked = new Property(4, boolean.class, "isChecked", false, "IS_CHECKED");
        public final static Property Time = new Property(5, int.class, "time", false, "TIME");
    }


    public EventShangkeDao(DaoConfig config) {
        super(config);
    }
    
    public EventShangkeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EVENT_SHANGKE\" (" + //
                "\"EVENT_TYPE\" INTEGER NOT NULL ," + // 0: eventType
                "\"_id\" INTEGER PRIMARY KEY ," + // 1: eventId
                "\"NAME\" TEXT," + // 2: name
                "\"STATUS\" INTEGER NOT NULL ," + // 3: status
                "\"IS_CHECKED\" INTEGER NOT NULL ," + // 4: isChecked
                "\"TIME\" INTEGER NOT NULL );"); // 5: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EVENT_SHANGKE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EventShangke entity) {
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
    protected final void bindValues(SQLiteStatement stmt, EventShangke entity) {
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
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    @Override
    public EventShangke readEntity(Cursor cursor, int offset) {
        EventShangke entity = new EventShangke( //
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
    public void readEntity(Cursor cursor, EventShangke entity, int offset) {
        entity.setEventType(cursor.getInt(offset + 0));
        entity.setEventId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStatus(cursor.getInt(offset + 3));
        entity.setIsChecked(cursor.getShort(offset + 4) != 0);
        entity.setTime(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EventShangke entity, long rowId) {
        entity.setEventId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EventShangke entity) {
        if(entity != null) {
            return entity.getEventId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EventShangke entity) {
        return entity.getEventId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
