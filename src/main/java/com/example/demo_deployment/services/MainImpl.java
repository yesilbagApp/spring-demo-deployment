package com.example.demo_deployment.services;

import com.example.demo_deployment.domain.Model;
import com.example.demo_deployment.repository.MainRepository;
import org.apache.tomcat.util.codec.binary.Base64;
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
        String title_str = null;
        String photo_url_str = null;
        String videoid_str = null;


        String url1 = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS9hcmFtYS8/YXJhPQ==");
        String search_str = searchQuery.trim().replace(" ", "+");
        Document doc = null;
        List<Model> modelList = new ArrayList<>();


        try {

            doc = Jsoup.connect(url1 + search_str).get();
            Elements searchh2 = doc.select("div#items > div");

            for (int i = 0; i < searchh2.size(); i++) {
                Element ser1 = searchh2.get(i);
                Elements ser2 = ser1.getElementsByClass("post-thumb");
                Elements el_img = ser2.select("img");
                photo_url_str = el_img.attr("src");
                title_str = el_img.attr("title");
                videoid_str = getVideoIdfromImgUrl(photo_url_str);
                Model model = new Model();
                model.setPhoto_url_list(photo_url_str);
                model.setVideo_id_list(videoid_str);
                model.setTitle_list(title_str);
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
