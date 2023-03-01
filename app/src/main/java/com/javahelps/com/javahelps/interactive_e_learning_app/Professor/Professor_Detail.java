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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.DataBaseOpenHelper.DatabaseAccess;
import com.javahelps.com.javahelps.interactive_e_learning_app.Notes.NotesSection;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.User;

import org.jetbrains.annotations.NotNull;

public class Professor_Detail extends AppCompatActivity {

    private TextView id, name, gender, phone, qualification, email, pr;
    private ImageView imag, back;

    DatabaseReference  reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_detail);

        back = findViewById(R.id.imageView6);
        id = findViewById(R.id.idResult);
        name = findViewById(R.id.nameResult);
        gender = findViewById(R.id.genderResult);
        phone = findViewById(R.id.phoResult);
        qualification = findViewById(R.id.qualiResult);
        email = findViewById(R.id.emailResult);
        pr = findViewById(R.id.videoText);
        imag = findViewById(R.id.imageView3);

        pr.setPaintFlags(pr.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        int re = getResources().getIdentifier("female1", "drawable", this.getPackageName());

        reference = FirebaseDatabase.getInstance().getReference().child("courses").child(CourseSelection.co_id).child("professor");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                Professor professor1 = snapshot.getValue(Professor.class);



                if (professor1.getGender().equals("female")) {
                    imag.setImageResource(re);

                }

                id.setText(professor1.getProf_id());
                name.setText(professor1.getProf_name());
                gender.setText(professor1.getGender());
                phone.setText(professor1.getPhone_no());
                qualification.setText(professor1.getQualification());
                email.setText(professor1.getEmail());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        
    }

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
        Intent intent = new Intent(Professor_Detail.this, LoginActivity.class);
        startActivity(intent);
    }
}