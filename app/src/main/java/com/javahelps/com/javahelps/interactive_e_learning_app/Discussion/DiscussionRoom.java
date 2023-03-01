package com.javahelps.com.javahelps.interactive_e_learning_app.Discussion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.javahelps.com.javahelps.interactive_e_learning_app.R.layout.*;

public class DiscussionRoom extends AppCompatActivity {

      DatabaseReference reference;


      public ListView listOfMessages;
      public ArrayList<ChatMessage> arrayList;
      public MessageAdapter mAdapter;

      TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_discussion_room);

        listOfMessages = findViewById(R.id.list_of_messages);

        text = findViewById(R.id.input);
        text.setBackgroundColor(0);


        reference = FirebaseDatabase.getInstance().getReference().child("courses").child(CourseSelection.co_id).child("messages");


        loadData();



        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);


                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance().getReference().child("courses").child(CourseSelection.co_id).child("messages").child(""+(arrayList.size()+1))
                        .setValue(new ChatMessage(input.getText().toString(), LoginActivity.user_id));

                // Clear the input
                input.setText("");

                loadData();
            }

        });

        TextWatcher afterTextChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String te = text.getText().toString().trim();

                if (!te.isEmpty()) {
                    fab.setVisibility(View.VISIBLE);
                }
                else{
                    fab.setVisibility(View.INVISIBLE);
                }

                loadData();


            }

            @Override
            public void afterTextChanged(Editable editable) {

                String te = text.getText().toString().trim();
                if (!te.isEmpty()) {
                    fab.setVisibility(View.VISIBLE);
                }
                else{
                    fab.setVisibility(View.INVISIBLE);
                }

                loadData();

            }
        };

        text.addTextChangedListener(afterTextChangeListener);

    }

    public void loadData(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                // Get Post object and use the values to update the UI
                arrayList = new ArrayList<ChatMessage>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ChatMessage message = ds.getValue(ChatMessage.class) ;
                    arrayList.add(message);
                }
                mAdapter = new MessageAdapter(DiscussionRoom.this, arrayList);
                listOfMessages.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }


}