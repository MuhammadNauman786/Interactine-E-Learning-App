package com.javahelps.com.javahelps.interactive_e_learning_app.DataBaseOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.javahelps.com.javahelps.interactive_e_learning_app.Professor.Professor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    public static String course_id;
    public static String course_id_title;
    public static String videoURL;
    public static String video_title;
    public  static String stu_id;
    public static boolean login;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();

        setVideoURL("SvT-QUTPG8o");
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of courses
     */
    public List<String> getCourses() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT course_id || ' - ' || course_title FROM Courses", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getVideos(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT video_title FROM LectureVideo WHERE course_id = ?", new String[]{getCourse_id()});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public boolean idPassCheck(String id, String pass){
        Cursor c;
        c = database.rawQuery("SELECT * FROM Student WHERE std_id = ?;", new String[]{id});
        boolean result = false;
        while (c.moveToNext()) {

            String std_id = c.getString(0);
            String std_pass = c.getString(5);
            if (id.equals(std_id) && pass.equals(std_pass)){
                result = true;
                stu_id = std_id;
            }

        }
        c.close();
        return result;
    }

//    public Professor getProfessorDetails(String cId){
//        Cursor c;
//        c = database.rawQuery("SELECT p.prof_id, p.prof_Name, p.gender, p.phone_no, p.qualification, p.email FROM Professor p JOIN Courses c ON p.prof_id = c.prof_id  WHERE course_id = ? ", new String[]{cId});
//        c.moveToFirst();
//        Professor prof = new Professor(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
//        c.close();
//        return prof;
//    }

    public void setCourse_id(String id){

        course_id = id;
    }
    public String getCourse_id(){

        return course_id;
    }

    public void setCourse_id_title(String id_title){

        course_id_title = id_title;
    }
    public String getCourse_id_title(){

        return course_id_title;
    }

    public void setVideoURL(String url){

        videoURL = url;
    }
    public String getVideoURL(){
        Cursor c;
        c = database.rawQuery("SELECT video_url FROM LectureVideo WHERE course_id = '"+course_id+"' AND video_title = '"+video_title+"' ;", null);

        setVideoURL(c.toString());
        c.close();
        return videoURL;
    }

    public static void setVideo_title(String title){
        video_title = title;
    }

    public String getVideo_title(){
        return video_title;
    }

    public void setLogin(boolean s){
        if(s == true){

        database.execSQL("UPDATE Student SET login_status = '1' WHERE std_id = ?;",new String[]{stu_id});
        }
        else {

            database.execSQL("UPDATE Student SET login_status = '0' WHERE std_id = ?;",new String[]{stu_id});

        }
    }

    public boolean getLogin(){
        Cursor c;

        c = database.rawQuery("SELECT login_status FROM Student WHERE std_id = '"+stu_id+"';",null);
        c.close();
        if (c.toString().equals("1")){
            return true;
        }
        else {
            return false;
        }
    }


}

