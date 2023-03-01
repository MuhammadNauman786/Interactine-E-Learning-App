package com.javahelps.com.javahelps.interactive_e_learning_app.LectureVideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.DataBaseOpenHelper.DatabaseAccess;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.ChatMessage;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.DiscussionRoom;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.MessageAdapter;
import com.javahelps.com.javahelps.interactive_e_learning_app.Notes.NotesSection;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.User;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.javahelps.com.javahelps.interactive_e_learning_app.DataBaseOpenHelper.DatabaseAccess.course_id_title;

public class LectureVideosList extends AppCompatActivity {
    ListView lectureVideosList;
    ArrayList<Video> arrayList;
    VideoAdapter adapter;
    public TextView text;
    public ImageView back;
    DatabaseReference reference;

    public static String video_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_videos_list);

        back = findViewById(R.id.backbutton5);
        text = findViewById(R.id.videoText);
        text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        text.setText(CourseSelection.co_id+" - "+CourseSelection.co_title+":");
        lectureVideosList = findViewById(R.id.listView);

        reference = FirebaseDatabase.getInstance().getReference().child("videos").child(CourseSelection.co_id);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                arrayList = new ArrayList<Video>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Video video = ds.getValue(Video.class);
                    arrayList.add(video);
                }
                adapter = new VideoAdapter(LectureVideosList.this, arrayList);
                lectureVideosList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        reference.addValueEventListener(postListener);



        lectureVideosList.setOnItemClickListener((parent, view, position, id)->{

            Video video = arrayList.get(position);
            video_id = video.getVideo_url();

               Intent intent = new Intent(LectureVideosList.this, video_player.class);
               startActivity(intent);
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            logout();
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){

        Intent intent = new Intent(LectureVideosList.this, LoginActivity.class);
        startActivity(intent);
    }
}

