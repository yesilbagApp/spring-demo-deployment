package com.example.demo_deployment;

public class Model {

    String video_id_list;
    String title_list;
    String photo_url_list;

    public String getVideo_id_list() {
        return video_id_list;
    }

    public void setVideo_id_list(String video_id_list) {
        this.video_id_list = video_id_list;
    }

    public String getTitle_list() {
        return title_list;
    }

    public void setTitle_list(String title_list) {
        this.title_list = title_list;
    }

    public String getPhoto_url_list() {
        return photo_url_list;
    }

    public void setPhoto_url_list(String photo_url_list) {
        this.photo_url_list = photo_url_list;
    }

    public Model(String video_id_list, String title_list, String photo_url_list) {
        this.video_id_list = video_id_list;
        this.title_list = title_list;
        this.photo_url_list = photo_url_list;
    }

    public Model(){
        //default
    }
}
