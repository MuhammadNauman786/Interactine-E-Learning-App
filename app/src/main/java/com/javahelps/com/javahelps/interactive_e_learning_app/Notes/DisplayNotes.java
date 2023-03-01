package com.javahelps.com.javahelps.interactive_e_learning_app.Notes;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;

public class DisplayNotes extends AppCompatActivity {
    TextView title, text;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_display);

        back = findViewById(R.id.backbutton4);
        title = findViewById(R.id.textTitleOfNotes);
        text = findViewById(R.id.textOfNotes);

        title.setText(NotesList.title+":");
        text.setText(NotesList.textNotes);

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
        Intent intent = new Intent(DisplayNotes.this, LoginActivity.class);
        startActivity(intent);
    }
}
