package com.javahelps.com.javahelps.interactive_e_learning_app.Courses;

public class Course {
    private  String course_id;
    private String course_title;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public Course() {
    }

    public Course(String course_id, String course_title) {
        this.course_id = course_id;
        this.course_title = course_title;
    }
}
