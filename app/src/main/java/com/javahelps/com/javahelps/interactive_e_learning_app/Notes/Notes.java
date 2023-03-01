package com.javahelps.com.javahelps.interactive_e_learning_app.Notes;

public class Notes {
    private String notesTitle;
    private String notesText;

    public Notes() {
    }

    public Notes(String notesTitle, String notesText) {
        this.notesTitle = notesTitle;
        this.notesText = notesText;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesText() {
        return notesText;
    }

    public void setNotesText(String notesText) {
        this.notesText = notesText;
    }
}
