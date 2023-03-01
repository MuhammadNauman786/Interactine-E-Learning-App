package com.javahelps.com.javahelps.interactive_e_learning_app.Notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;


public class NotesSection extends AppCompatActivity {

    private EditText title, text;
    private TextView note;
    private Button addNotes;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_section);

        back = findViewById(R.id.backbutton3);
        title = findViewById(R.id.editTextTextPersonName);
        text = findViewById(R.id.editTextTextMultiLine);
        addNotes = findViewById(R.id.btn_add);
        note = findViewById(R.id.addNotesT);

        note.setText(CourseSelection.co_id+" - "+CourseSelection.co_title+":");

        note.setPaintFlags(note.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextWatcher addTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ti = title.getText().toString().trim();
                String te = text.getText().toString().trim();

                addNotes.setEnabled(!ti.isEmpty() && !te.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

                String ti = title.getText().toString().trim();
                String te = text.getText().toString().trim();

                addNotes.setEnabled(!ti.isEmpty() && !te.isEmpty());

            }
        };

        title.addTextChangedListener(addTextWatcher);
        text.addTextChangedListener(addTextWatcher);


        addNotes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(NotesSection.this, "Notes Successfully Added",Toast.LENGTH_SHORT).show();

                addNotes(title.getText().toString(), text.getText().toString());

                Intent intent = new Intent(NotesSection.this, NotesList.class);
                startActivity(intent);

                finish();
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
        Intent intent = new Intent(NotesSection.this, LoginActivity.class);
        startActivity(intent);
    }

    public void addNotes(String title, String text){
        FirebaseDatabase.getInstance().getReference().child("notes").child(LoginActivity.user_id).child(CourseSelection.co_id).push().setValue(new Notes(title,text));
    }

}