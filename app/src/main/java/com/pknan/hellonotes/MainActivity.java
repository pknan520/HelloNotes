package com.pknan.hellonotes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final int ADD_TEXT = 1;
    public static final int ADD_PHOTO = 2;
    public static final int ADD_VIDEO = 3;

    private Button btnText, btnPhoto, btnVideo;
    private ListView lvContent;
    private NotesAdapter adapter;
    private Intent intent;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
//    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        selectDB();
    }

    private void selectDB() {
        Cursor cursor = dbReader.query("Notes", null, null, null, null, null, null);
        adapter = new NotesAdapter(this, cursor);
        lvContent.setAdapter(adapter);
    }

    private void initView() {
        btnText = (Button) findViewById(R.id.btnText);
        btnText.setOnClickListener(this);
        btnPhoto = (Button) findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(this);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(this);

        notesDB = new NotesDB(this, "NotesDB", null, 1);
        dbReader = notesDB.getReadableDatabase();

        lvContent = (ListView) findViewById(R.id.lvContent);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(this, AddContent.class);
        switch (v.getId()) {
            case R.id.btnText:
                intent.putExtra("flag", ADD_TEXT);
                startActivity(intent);
                break;
            case R.id.btnPhoto:
                intent.putExtra("flag", ADD_PHOTO);
                startActivity(intent);
                break;
            case R.id.btnVideo:
                intent.putExtra("flag", ADD_VIDEO);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        selectDB();
        super.onResume();
    }
}
