package com.step2hell.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_sample";
    public static final String TABLE_NAME = "girlfriends";
    private static final int VERSION = 1;

    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getInstance(@Nullable Context context){
        if (instance == null){
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Bob", "onCreate");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " +
//                TABLE_NAME + "(" +
//                "_id integer primary key autoincrement," +
//                "name" + "text" +
//                "age" + "integer" +
//                ")");

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(_id integer primary key autoincrement, name text NOT NULL, age integer DEFAULT 0)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("Bob", "onUpgrade, oldVersion:" + oldVersion + ", newVersion:" + newVersion);
        switch (oldVersion) {
            case 1:
                // insert
                String sqlInsert1 = "insert into " + TABLE_NAME + "(_id, name, age) values(1, 'Lisa', 27)";
                db.execSQL(sqlInsert1);

                String sqlInsert2 = "insert into " + TABLE_NAME + "(_id, name, age) values(2, 'Lisa',27)";
                db.execSQL(sqlInsert2);

                ContentValues cvInsert = new ContentValues();
                cvInsert.put("name", "YangJinting");
                cvInsert.put("age", 27);
                db.insert(TABLE_NAME, null, cvInsert);
            case 2:
                // delete
                String whereClauseDelete = "name=?";
                String[] whereArgsDelete = {"Lisa"};
                db.delete(TABLE_NAME, whereClauseDelete, whereArgsDelete);

//                String sqlDelete = "delete from " + TABLE_NAME + " where name = 'Lisa'";
//                db.execSQL(sqlDelete);
            case 3:
                // update
                String whereClauseUpdate = "_id=?";
                String[] whereArgsUpdate = {"3"};
                ContentValues cvUpdate = new ContentValues();
                cvUpdate.put("age", 30);
                db.update(TABLE_NAME, cvUpdate, whereClauseUpdate, whereArgsUpdate);

//                String sqlUpdate = "update " + TABLE_NAME + " set age=30 where _id=3";
//                db.execSQL(sqlUpdate);
            case 4:
                // add column
                String sqlAddColumn = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN cup varchar default 'C'";
                db.execSQL(sqlAddColumn);
            default:
                break;
        }
    }
}
