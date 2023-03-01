package com.javahelps.com.javahelps.interactive_e_learning_app.Professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.Course;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseAdapter;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseContent;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.Discussion.DiscussionRoom;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HandledCourses extends AppCompatActivity {

    private ListView handleCourses;

    public ArrayList<Course> courses;
    public CourseAdapter adapter;
    public static TextView text;

    public static String co_id;


    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handled_courses);

        handleCourses = findViewById(R.id.HandleCoursesList);
        text = findViewById(R.id.HandleText);
        text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        reference = FirebaseDatabase.getInstance().getReference().child("courses");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                courses = new ArrayList<Course>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Course course = ds.getValue(Course.class);
                    Professor professor = ds.child("professor").getValue(Professor.class);
                    if (professor.getProf_id().equals(LoginActivity.user_id)) {
                        courses.add(course);
                    }
                }
                adapter = new CourseAdapter(HandledCourses.this, courses);
                handleCourses.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        };

        reference.addValueEventListener(postListener);


        handleCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = courses.get(i);

                CourseSelection.setCo_id(course.getCourse_id());

                Intent intent = new Intent(HandledCourses.this, DiscussionRoom.class);
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
        Intent intent = new Intent(HandledCourses.this, LoginActivity.class);
        startActivity(intent);
    }

}