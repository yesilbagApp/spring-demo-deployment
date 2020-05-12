package com.example.demo_deployment.services;

import com.example.demo_deployment.domain.Model;
import com.example.demo_deployment.repository.MainRepository;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainImpl implements MainRepository {

    private String title;
    private String videoId;
    private String photo;

    @Override
    public List<Model> search(String searchQuery) {

        String url1 = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS9hcmFtYS8/YXJhPQ==");
        String search_str = searchQuery.trim().replace(" ", "+");
        Document doc = null;
        List<Model> modelList = new ArrayList<>();


        try {

            doc = Jsoup.connect(url1 + search_str).get();
            Elements elements = doc.select("div#items > div");

            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);
                Model model = new Model();
                model.setPhoto_url_list(photo);
                model.setVideo_id_list(videoId);
                model.setTitle_list(title);
                modelList.add(model);
            }

        } catch (Exception e) {
        }

        return modelList;
    }

    @Override
    public List<Model> popularTr() {

        String url_tr = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS95ZXJsaS1wb3B1bGVyLXNhcmtpbGFyLw==");
        Document doc = null;
        List<Model> modelList = new ArrayList<>();

        try {
            doc = Jsoup.connect(url_tr).get();
            Elements elements = doc.select("div#primary").select("div.post-thumb");
            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);
                Model model = new Model();
                model.setPhoto_url_list(photo);
                model.setVideo_id_list(videoId);
                model.setTitle_list(title);
                modelList.add(model);
            }
        } catch (Exception e) {
        }
        return modelList;
    }

    @Override
    public List<Model> popularWr() {

        String url_wr = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS95YWJhbmNpLXBvcHVsZXItc2Fya2lsYXIv");
        Document doc = null;
        List<Model> modelList = new ArrayList<>();

        try {
            doc = Jsoup.connect(url_wr).get();
            Elements elements = doc.select("div#primary").select("div.post-thumb");
            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);
                Model model = new Model();
                model.setPhoto_url_list(photo);
                model.setVideo_id_list(videoId);
                model.setTitle_list(title);
                modelList.add(model);
            }
        } catch (Exception e) {
        }

        return modelList;
    }

    @Override
    public String giveMe(String request) {
        String yt_url = null;
        String yt_type = null;
        String yt_success = null;
        String url1 = decodeStrings("aHR0cHM6Ly9hcGkueW91dHViZS1tcDMub3JnLmluL0BhdWRpby8=");
        String music_download_url = url1 + videoId + "/?download";
        OkHttpClient client1 = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url(music_download_url)
                .build();

        try {
            Response response1 = client1.newCall(request1).execute();
            String response_str1 = response1.body().string();
            JSONObject mainjsonObj = new JSONObject(response_str1);
            Boolean success = (Boolean) mainjsonObj.get("success");
            String type = String.valueOf(mainjsonObj.get("type"));
            if (success && type.equals("download")) {
                yt_url = mainjsonObj.getString("url");
            } else
                return "Song Not Found";
        } catch (Exception e1) {
            return "Error occured";
        }


        return yt_url;
    }

    private String decodeStrings(String encoded) {
        byte[] dataDec = Base64.decodeBase64(encoded);
        String decodedString = "";
        try {
            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return decodedString;
        }
    }

    private String getVideoIdfromImgUrl(String imageurl) {
        int hq_index = imageurl.lastIndexOf("default.") - 3;
        int start_index = hq_index - 11;
        String videoIdd = imageurl.substring(start_index, hq_index);
        return videoIdd;
    }
}
