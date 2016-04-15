package com.pknan.hellonotes;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/15.
 */
public class AddContent extends Activity implements View.OnClickListener{

    private Button btnSave, btnCancel;
    private ImageView addImage, addVideo;
    private EditText addText;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    private String flag = "";
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_content);
        flag = getIntent().getStringExtra("flag");
        initView();
    }

    private void initView() {
        addText = (EditText) findViewById(R.id.addText);
        addImage = (ImageView) findViewById(R.id.addImage);
        addVideo = (ImageView) findViewById(R.id.addVideo);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        notesDB = new NotesDB(this, "NotesDB", null, 1);
        dbWriter = notesDB.getWritableDatabase();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                addDB();
                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void addDB() {
        ContentValues cv = new ContentValues();
        cv.put("content", addText.getText().toString());
        cv.put("time", getTime());
        dbWriter.insert("Notes", null, cv);
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH-mm-ss");
        Date curDate = new Date();
        String time = dateFormat.format(curDate);
        return time;
    }
}
