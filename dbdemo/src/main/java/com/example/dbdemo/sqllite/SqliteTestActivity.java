package com.example.dbdemo.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dbdemo.R;

import java.util.ArrayList;

public class SqliteTestActivity extends AppCompatActivity {

    private static final String TAG = SqliteTestActivity.class.getSimpleName();
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);

        //创建库
        MySqLiteOpenHelper myFirstDb = new MySqLiteOpenHelper(this, "myfirstdb", null, 2);
        //打开数据库并进行写入操作
        db = myFirstDb.getWritableDatabase();

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert(db);
            }
        });

        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query(db);
            }
        });
    }

    private void insert(SQLiteDatabase db) {
        String name = "Tony";
        String address = "皇后大道, insert time:" + System.currentTimeMillis();
        String insert = new StringBuilder()
                .append("insert into SqliteDemo (name,address) values('")
                .append(name).append("','").append(address).append("')")
                .toString();
        //或是
        // String insert = "insert into SqlteDemo (name,address) values ('"+name+"','"+address+"')";
        db.execSQL(insert);
    }

    private void query(SQLiteDatabase db) {
        String query = "select * from SqliteDemo";
        Cursor cursor = db.rawQuery(query, null);

        //拿到Cursor后获取数据
        getDataFromCursor(cursor);
    }

    private void getDataFromCursor(Cursor cursor) {
        ArrayList<SqliteDemo> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));

            SqliteDemo sqliteDemo = new SqliteDemo();
            sqliteDemo.id = id;
            sqliteDemo.name = name;
            sqliteDemo.address = address;

            list.add(sqliteDemo);
        }
        for (SqliteDemo bean : list) {
            Log.w(TAG, "getDataFromCursor: =====list:"
                    + " id:" + bean.id
                    + " name:" + bean.name
                    + " address:" + bean.address);
        }
    }

}
