package com.javahelps.com.javahelps.interactive_e_learning_app.Notes;

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

public class NotesAdapter extends BaseAdapter {
    Context c;
    ArrayList<Notes> list;
    public NotesAdapter(Context context, ArrayList<Notes> list) {
        this.c = context;
        this.list = list;
    }
    public void add(Notes notes){
        this.list.add(notes);
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
            view = fileInflater.inflate(R.layout.notes,viewGroup,false);
        }

        TextView notes_nam = (TextView) view.findViewById(R.id.notes_name);

        Notes notes = (Notes) getItem(i);

        // Set their text
        notes_nam.setText(notes.getNotesTitle());


        //checkBox.setVisibility(View.GONE);


        return view;
    }
}
