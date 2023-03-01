package com.javahelps.com.javahelps.interactive_e_learning_app.Notes;

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

public class NotesList extends AppCompatActivity {

    DatabaseReference reference;


    public ListView listOfNotes;
    public ArrayList<Notes> arrayList;
    public NotesAdapter adapter;
    public ImageView back;

    TextView text;

    FloatingActionButton addNotes;

    public static String title, textNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        back = findViewById(R.id.backbutton2);
        text = findViewById(R.id.notesSection);
        addNotes = (FloatingActionButton) findViewById(R.id.AddNotes);

        text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        text.setText(CourseSelection.co_id+" - "+CourseSelection.co_title+":");

        listOfNotes = findViewById(R.id.NotesList);

        reference = FirebaseDatabase.getInstance().getReference().child("notes").child(LoginActivity.user_id).child(CourseSelection.co_id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList = new ArrayList<Notes>();
                if (snapshot != null) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Notes notes = ds.getValue(Notes.class);
                        arrayList.add(notes);
                    }
                    adapter = new NotesAdapter(NotesList.this, arrayList);
                    listOfNotes.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        listOfNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Notes notes = arrayList.get(i);

                title = notes.getNotesTitle();
                textNotes = notes.getNotesText();

                Intent intent = new Intent(NotesList.this, DisplayNotes.class);
                startActivity(intent);

            }
        });

        addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NotesList.this, NotesSection.class);
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
        Intent intent = new Intent(NotesList.this, LoginActivity.class);
        startActivity(intent);
    }

}