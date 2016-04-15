package com.pknan.hellonotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/4/15.
 */
public class NotesDB extends SQLiteOpenHelper {

    public static final String CREATE_NOTES = "create table Notes (" +
            "id integer primary key autoincrement, " +
            "content text, " +
            "path text, " +
            "video text, " +
            "time text)";
    private Context mContext;

    public NotesDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
