package com.javahelps.com.javahelps.interactive_e_learning_app.Discussion;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends BaseAdapter{

    Context c;

    ArrayList<ChatMessage> list;

    public MessageAdapter(Context context, ArrayList<ChatMessage> objects){
        c = context;
        this.list = objects;
    }

    public void add(ChatMessage message){
        this.list.add(message);
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
        return this.list.get(i).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater messageInflater = (LayoutInflater) c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ChatMessage list = (ChatMessage) getItem(position);


        if((convertView == null) && !list.getMessageUser().equals(LoginActivity.user_id)){

            convertView = messageInflater.inflate(R.layout.my_message,parent,false);
        }
        else if(convertView == null){

            convertView = messageInflater.inflate(R.layout.message,parent,false);
        }

        TextView messageText = (TextView) convertView.findViewById(R.id.message_text);
        TextView messageUser = (TextView) convertView.findViewById(R.id.message_user);
        TextView messageTime = (TextView) convertView.findViewById(R.id.message_time);



        // Set their text
        messageText.setText(list.getMessageText());
        messageUser.setText(list.getMessageUser());

        // Format the date before showing it
        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                list.getMessageTime()));

        //checkBox.setVisibility(View.GONE);


        return convertView;
    }
}
