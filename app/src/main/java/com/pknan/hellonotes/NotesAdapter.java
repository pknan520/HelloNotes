package com.pknan.hellonotes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/15.
 */
public class NotesAdapter extends BaseAdapter {

    private Context context;
    private Cursor cursor;
    private LinearLayout layout;

    public NotesAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.lv_content_item, null);
        ViewHolder viewHolder = new ViewHolder();
        cursor.moveToPosition(position);
        viewHolder.tvText.setText(cursor.getString(cursor.getColumnIndex("content")));
        viewHolder.tvTime.setText(cursor.getString(cursor.getColumnIndex("time")));
        return layout;
    }

    private class ViewHolder {
        TextView tvText = (TextView) layout.findViewById(R.id.tvText);
        TextView tvTime = (TextView) layout.findViewById(R.id.tvTime);
        ImageView ivPhoto = (ImageView) layout.findViewById(R.id.ivPhoto);
        ImageView ivVideo = (ImageView) layout.findViewById(R.id.ivVideo);
    }
}
