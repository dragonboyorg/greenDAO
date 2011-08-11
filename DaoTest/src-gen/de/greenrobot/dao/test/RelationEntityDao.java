package de.greenrobot.dao.test;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.IdentityScope;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.SqlUtils;

import de.greenrobot.dao.test.RelationEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table RELATION_ENTITY (schema version 1).
*/
public class RelationEntityDao extends AbstractDao<RelationEntity, Long> {

    public static final String TABLENAME = "RELATION_ENTITY";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ParentId = new Property(1, Long.class, "parentId", false, "PARENT_ID");
        public final static Property TestId = new Property(2, Long.class, "testId", false, "TEST_ID");
        public final static Property TestIdNotNull = new Property(3, long.class, "testIdNotNull", false, "TEST_ID_NOT_NULL");
        public final static Property SimpleString = new Property(4, String.class, "simpleString", false, "SIMPLE_STRING");
    };

    private DaoMaster daoMaster;

    public RelationEntityDao(SQLiteDatabase db, DaoMaster daoMaster) {
        super(db);
        this.daoMaster = daoMaster;
    }

    public RelationEntityDao(SQLiteDatabase db) {
        super(db);
    }
    
    public RelationEntityDao(SQLiteDatabase db, IdentityScope<Long, RelationEntity> identityScope) {
        super(db, identityScope);
    }
    
    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "RELATION_ENTITY (" + //
                "_id INTEGER PRIMARY KEY ," + // 0
                "PARENT_ID INTEGER," + // 1
                "TEST_ID INTEGER," + // 2
                "TEST_ID_NOT_NULL INTEGER NOT NULL ," + // 3
                "SIMPLE_STRING TEXT);"; // 4
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "RELATION_ENTITY";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RelationEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindLong(2, parentId);
        }
 
        Long testId = entity.getTestId();
        if (testId != null) {
            stmt.bindLong(3, testId);
        }
        stmt.bindLong(4, entity.getTestIdNotNull());
 
        String simpleString = entity.getSimpleString();
        if (simpleString != null) {
            stmt.bindString(5, simpleString);
        }
    }

    @Override
    protected void attachEntity(Long key, RelationEntity entity) {
        super.attachEntity(key, entity);
        entity.__setDaoMaster(daoMaster);
    }

    /** @inheritdoc */
    @Override
    public Long readPkFrom(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RelationEntity readFrom(Cursor cursor, int offset) {
        RelationEntity entity = new RelationEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // parentId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // testId
            cursor.getLong(offset + 3), // testIdNotNull
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // simpleString
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readFrom(Cursor cursor, RelationEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setParentId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTestId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setTestIdNotNull(cursor.getLong(offset + 3));
        entity.setSimpleString(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected Long updateKeyAfterInsert(RelationEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getPrimaryKeyValue(RelationEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendCommaSeparated(builder, "T.", getAllColumns());
            builder.append(',');
            SqlUtils.appendCommaSeparated(builder, "T0.", daoMaster.getRelationEntityDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendCommaSeparated(builder, "T1.", daoMaster.getTestEntityDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendCommaSeparated(builder, "T2.", daoMaster.getTestEntityDao().getAllColumns());
            builder.append(" FROM RELATION_ENTITY T");
            builder.append(" LEFT JOIN RELATION_ENTITY T0 ON T.PARENT_ID=T0._id");
            builder.append(" LEFT JOIN TEST_ENTITY T1 ON T.TEST_ID=T1._id");
            builder.append(" LEFT JOIN TEST_ENTITY T2 ON T.TEST_ID_NOT_NULL=T2._id");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected RelationEntity readDeepFrom(Cursor cursor) {
        RelationEntity entity = fetchEntity(cursor, 0);
        int offset = getAllColumns().length;
        entity.setParent(daoMaster.getRelationEntityDao().fetchEntity(cursor, offset));
        offset += daoMaster.getRelationEntityDao().getAllColumns().length;
        entity.setTestEntity(daoMaster.getTestEntityDao().fetchEntity(cursor, offset));
        offset += daoMaster.getTestEntityDao().getAllColumns().length;
        entity.setTestNotNull(daoMaster.getTestEntityDao().fetchEntity(cursor, offset));
        return entity;    
    }

    public RelationEntity loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendCommaSeparatedEqPlaceholder(builder, "T.", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return readDeepFrom(cursor);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<RelationEntity> readDeepAllFrom(Cursor cursor) {
        List<RelationEntity> list = new ArrayList<RelationEntity>();
        if (cursor.moveToFirst()) {
            do {
                list.add(readDeepFrom(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }
    
    protected List<RelationEntity> readDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return readDeepAllFrom(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<RelationEntity> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return readDeepAllAndCloseCursor(cursor);
    }
 
}
