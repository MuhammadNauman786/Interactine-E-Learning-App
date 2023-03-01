package com.javahelps.com.javahelps.interactive_e_learning_app.Courses;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.javahelps.com.javahelps.interactive_e_learning_app.DataBaseOpenHelper.DatabaseAccess;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.ChatMessage;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.DiscussionRoom;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.MessageAdapter;
import com.javahelps.com.javahelps.interactive_e_learning_app.Notes.NotesSection;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CourseSelection extends AppCompatActivity {

    private ListView listView;
    public ArrayList<Course> courses;
    public CourseAdapter adapter;
    public static TextView text;

    public static String co_id, co_title;

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_selection);




        listView = (ListView) findViewById(R.id.list);
        text = findViewById(R.id.videoText);
        text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        reference = FirebaseDatabase.getInstance().getReference().child("courses");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                courses = new ArrayList<Course>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Course course = ds.getValue(Course.class);
                    courses.add(course);
                }
                adapter = new CourseAdapter(CourseSelection.this, courses);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        };

        reference.addValueEventListener(postListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = courses.get(i);

                co_id = course.getCourse_id();
                co_title = course.getCourse_title();

                Intent intent = new Intent(CourseSelection.this, CourseContent.class);
                startActivity(intent);


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
        Intent intent = new Intent(CourseSelection.this, LoginActivity.class);
        startActivity(intent);
    }

    public static void setCo_id(String co){

        co_id = co;

    }

}