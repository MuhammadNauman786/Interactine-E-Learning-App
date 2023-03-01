package com.javahelps.com.javahelps.interactive_e_learning_app.LectureVideo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.javahelps.com.javahelps.interactive_e_learning_app.R;

import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {
    Context c;
    ArrayList<Video> list;
    public VideoAdapter(Context context, ArrayList<Video> list) {
        this.c = context;
        this.list = list;
    }
    public void add(Video video){
        this.list.add(video);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater fileInflater = (LayoutInflater) c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = fileInflater.inflate(R.layout.video_list,viewGroup,false);
        }

        TextView video_nam = (TextView) view.findViewById(R.id.Video_name);

        Video video = (Video) getItem(i);

        // Set their text
        video_nam.setText(video.getVideo_title());


        //checkBox.setVisibility(View.GONE);


        return view;
    }
}
