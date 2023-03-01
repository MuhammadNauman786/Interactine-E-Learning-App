package com.javahelps.com.javahelps.interactive_e_learning_app.LectureVideo;

public class Video {
    private String video_title;
    private String video_id;
    private String video_url;

    public Video() {
    }

    public Video(String video_title, String video_id, String video_url) {
        this.video_title = video_title;
        this.video_id = video_id;
        this.video_url = video_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }
}
