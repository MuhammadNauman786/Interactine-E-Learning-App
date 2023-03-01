package com.javahelps.com.javahelps.interactive_e_learning_app.HelpingMaterial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.ChatMessage;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;

import java.util.ArrayList;

public class FileAdapter extends BaseAdapter{
    Context c;
    ArrayList<File> list;
    public FileAdapter(Context context, ArrayList<File> list) {
        this.c = context;
        this.list = list;
    }
    public void add(File file){
        this.list.add(file);
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
            view = fileInflater.inflate(R.layout.file,viewGroup,false);
        }

        TextView file_nam = (TextView) view.findViewById(R.id.file_name);
        ImageView image = view.findViewById(R.id.pdf_image);

        File file = (File) getItem(i);

        // Set file name text
        file_nam.setText(file.getFileName());

        String extension = file.getFileName().toString().substring(file.fileName.indexOf("."));

        if(extension.equals(".pdf")){

            image.setImageResource(R.drawable._4px_pdf_file_icon_svg);

        }
        else if(extension.equals(".docx") || extension.equals(".doc")){

            image.setImageResource(R.drawable.word_file);

        }
        else if(extension.equals(".xlsx")){

            image.setImageResource(R.drawable.excel_file);

        }
        else if(extension.equals(".ppt")){

            image.setImageResource(R.drawable.powerpoint_file);

        }
        else if(extension.equals(".png") || extension.equals(".jpeg") || extension.equals(".jpg")){

            image.setImageResource(R.drawable.image_file);

        }
        else{

            image.setImageResource(R.drawable.text_file);

        }


        return view;
    }
}
