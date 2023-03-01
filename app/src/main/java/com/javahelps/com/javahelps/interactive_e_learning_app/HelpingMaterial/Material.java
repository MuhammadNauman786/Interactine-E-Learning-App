package com.javahelps.com.javahelps.interactive_e_learning_app.HelpingMaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Material extends AppCompatActivity {

    FileAdapter fileAdapter;


    public ListView listView;

    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);


        listView = findViewById(R.id._dynamic);


        ArrayList<File> files = new ArrayList<File>();



        storageReference = FirebaseStorage.getInstance().getReference().child(CourseSelection.co_id);
        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {

                for(StorageReference fileRef : listResult.getItems()) {
                    // TODO: Download the file using its reference (fileRef)
                    File file = new File(fileRef.getName(),fileRef.getDownloadUrl().toString());

                    files.add(file);
                }
                fileAdapter = new FileAdapter(Material.this,files);

                listView.setAdapter(fileAdapter);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File file = files.get(i);

                StorageReference reference = FirebaseStorage.getInstance().getReference().child(CourseSelection.co_id).child(file.fileName);
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        String url = uri.toString();

                        Toast.makeText(Material.this, "Downloading is in process.....", Toast.LENGTH_SHORT).show();

                        downloadFile(Material.this,file.getFileName(),DIRECTORY_DOWNLOADS,url);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });
            }
        });

    }

    public void  downloadFile(Context context, String fileName, String destinationDirectory, String url){

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName);

        downloadManager.enqueue(request);
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
        Intent intent = new Intent(Material.this, LoginActivity.class);
        startActivity(intent);
    }
}