package com.javahelps.com.javahelps.interactive_e_learning_app.Professor;

import java.security.PrivateKey;

public class Professor {
    private String prof_id;
    private String password;
    private String prof_name;
    private String gender;
    private String phone_no;
    private String qualification;
    private String email;

    public Professor(String prof_id, String prof_name, String gender, String phone_no, String qualification, String email) {
        this.prof_id = prof_id;
        this.prof_name = prof_name;
        this.gender = gender;
        this.phone_no = phone_no;
        this.qualification = qualification;
        this.email = email;
    }

    public Professor(String prof_id, String password, String prof_name, String gender, String phone_no, String qualification, String email) {
        this.prof_id = prof_id;
        this.password = password;
        this.prof_name = prof_name;
        this.gender = gender;
        this.phone_no = phone_no;
        this.qualification = qualification;
        this.email = email;
    }

    public Professor() {
    }

    public String getProf_id() {
        return prof_id;
    }

    public void setProf_id(String prof_id) {
        this.prof_id = prof_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProf_name() {
        return prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
