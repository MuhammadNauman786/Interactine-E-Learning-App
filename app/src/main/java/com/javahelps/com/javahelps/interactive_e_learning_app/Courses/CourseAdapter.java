package com.javahelps.com.javahelps.interactive_e_learning_app.Courses;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.javahelps.com.javahelps.interactive_e_learning_app.HelpingMaterial.File;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;

import java.util.ArrayList;

public class CourseAdapter extends BaseAdapter {
    Context c;
    ArrayList<Course> list;
    public CourseAdapter(Context context, ArrayList<Course> list) {
        this.c = context;
        this.list = list;
    }
    public void add(Course course){
        this.list.add(course);
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
            view = fileInflater.inflate(R.layout.course_list,viewGroup,false);
        }

        TextView course_nam = (TextView) view.findViewById(R.id.course_name);

        Course course = (Course) getItem(i);

        // Set their text
        course_nam.setText(course.getCourse_id()+" - "+course.getCourse_title());


        //checkBox.setVisibility(View.GONE);


        return view;
    }
}
