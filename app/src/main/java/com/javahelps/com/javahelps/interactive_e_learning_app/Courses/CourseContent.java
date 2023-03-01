package com.javahelps.com.javahelps.interactive_e_learning_app.Courses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.DiscussionRoom;
import com.javahelps.com.javahelps.interactive_e_learning_app.HelpingMaterial.Material;
import com.javahelps.com.javahelps.interactive_e_learning_app.LectureVideo.LectureVideosList;
import com.javahelps.com.javahelps.interactive_e_learning_app.Notes.NotesList;
import com.javahelps.com.javahelps.interactive_e_learning_app.Professor.Professor_Detail;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;

public class CourseContent extends AppCompatActivity {


    public TextView text;
    public CardView prof, note, video, discussion, material;
    public ImageView back;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);

        back = findViewById(R.id.backbutton);
        text = findViewById(R.id.videoText);
        prof = findViewById(R.id.Prof_details);
        note = findViewById(R.id.NotesSection);
        video = findViewById(R.id.LectureVideo);
        discussion = findViewById(R.id.DiscussionBoard);
        material = findViewById(R.id.HelpingMaterial);
        text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        text.setText(CourseSelection.co_id+" - "+CourseSelection.co_title+":");

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseContent.this, Professor_Detail.class);
                startActivity(intent);
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseContent.this, NotesList.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseContent.this, LectureVideosList.class);
                startActivity(intent);
            }
        });

        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseContent.this, DiscussionRoom.class);
                startActivity(intent);
            }
        });

        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseContent.this, Material.class);
                startActivity(intent);
            }
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

        Intent intent = new Intent(CourseContent.this, LoginActivity.class);
        startActivity(intent);

    }


}