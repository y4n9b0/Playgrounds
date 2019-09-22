package com.step2hell.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button query, insert, insertSql, insertWithCompile, insertWithTransaction, insertWithCompileAndTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        query = findViewById(R.id.query);
        insert = findViewById(R.id.insert);
        insertSql = findViewById(R.id.insert_sql);
        insertWithCompile = findViewById(R.id.insert_with_compile);
        insertWithTransaction = findViewById(R.id.insert_with_transaction);
        insertWithCompileAndTransaction = findViewById(R.id.insert_with_compile_and_transaction);

        query.setOnClickListener(this);
        insert.setOnClickListener(this);
        insertSql.setOnClickListener(this);
        insertWithCompile.setOnClickListener(this);
        insertWithTransaction.setOnClickListener(this);
        insertWithCompileAndTransaction.setOnClickListener(this);


        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Log.e("Bob", "current version:" + db.getVersion());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                // query
                SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
//                Cursor cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_NAME + " where name=?", new String[]{"YangJinting"});
                Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    Log.e("Bob", cursor.getString(cursor.getColumnIndex("name")));
                }
                cursor.close();
                break;
            case R.id.insert:
                testInsert();
                break;
            case R.id.insert_sql:
                testInsertSql();
                break;
            case R.id.insert_with_compile:
                testInsertWithCompile();
                break;
            case R.id.insert_with_transaction:
                testInsertWithTransaction();
                break;
            case R.id.insert_with_compile_and_transaction:
                testInsertWithCompileAndTransaction();
                break;
        }
    }

    private void testInsert() {
        // 1145 ms
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        long start = SystemClock.currentThreadTimeMillis();
        ContentValues cvInsert = new ContentValues();
        for (int i = 0; i < 1000; i++) {
            cvInsert.clear();
            cvInsert.put("name", "YangJinting" + i);
            cvInsert.put("age", 27);
            db.insert(DatabaseHelper.TABLE_NAME, null, cvInsert);
        }
        Log.e("Bob", "testInsert cost time:" + (SystemClock.currentThreadTimeMillis() - start));
    }

    private void testInsertSql() {
        // 1017 ms
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        long start = SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String sql = "insert into " + DatabaseHelper.TABLE_NAME + "(name, age) values('YangJinting',27)";
            db.execSQL(sql);
        }
        Log.e("Bob", "testInsertSql cost time:" + (SystemClock.currentThreadTimeMillis() - start));
    }

    private void testInsertWithCompile() {
        // 664 ms
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        long start = SystemClock.currentThreadTimeMillis();
        String sql = "insert into " + DatabaseHelper.TABLE_NAME + " values (?,?,27);";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        for (int i = 0; i < 1000; i++) {
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindLong(1, i + 1);
            sqLiteStatement.bindString(2, "YangJinting" + i);
            sqLiteStatement.executeInsert();
        }
        Log.e("Bob", "testInsertWithCompile cost time:" + (SystemClock.currentThreadTimeMillis() - start));
    }

    private void testInsertWithTransaction() {
        // 168 ms
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        long start = SystemClock.currentThreadTimeMillis();
        db.beginTransaction();
        for (int i = 0; i < 1000; i++) {
            String sql = "insert into " + DatabaseHelper.TABLE_NAME + "(name, age) values('YangJinting',27)";
            db.execSQL(sql);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.e("Bob", "testInsertWithTransaction cost time:" + (SystemClock.currentThreadTimeMillis() - start));
    }

    private void testInsertWithCompileAndTransaction() {
        // 163 ms
        // String sql = "insert into " + DatabaseHelper.TABLE_NAME + "(name, age) values('YangJinting',27)"; 不 bind 参数 113 ms.
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        long start = SystemClock.currentThreadTimeMillis();
        db.beginTransaction();
        String sql = "insert into " + DatabaseHelper.TABLE_NAME + " values (?,?,27);";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        for (int i = 0; i < 1000; i++) {
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindLong(1, i + 1);
            sqLiteStatement.bindString(2, "YangJinting" + i);
            sqLiteStatement.executeInsert();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.e("Bob", "testInsertWithCompileAndTransaction cost time:" + (SystemClock.currentThreadTimeMillis() - start));
    }
}
