package com.example.demo_deployment.services;

import com.example.demo_deployment.domain.ApkControlModel;
import com.example.demo_deployment.domain.MusicModel;
import com.example.demo_deployment.repository.MainRepository;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import javax.swing.text.AbstractDocument;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainImpl implements MainRepository {

    private String title;
    private String videoId;
    private String photo;


    @Override
    public List<ApkControlModel> apkControl() {

        //apkAktifPasif=true ise apkAktif çalışıyor demektir.
        // Uygulama yayından kaldırıldıysa false yapılıp not yazılır ve yeni uygulamanın store adresi verilir.

        Boolean apkAktifPasif=true;
        String not="Yeni Uygulamamızı indirmek için tıklayınız !";
        String yeniApkAdres="market://details?id=ts.myt.memoo";


        List<ApkControlModel> apkControlModelList = new ArrayList<>();

        try {
            ApkControlModel model = new ApkControlModel();
            model.setNot(not);
            model.setYeniApkAdres(yeniApkAdres);
            model.setApkAktifPasif(apkAktifPasif);
            apkControlModelList.add(model);

        } catch (Exception e) {
        }
        return apkControlModelList;
    }

    public List<MusicModel> searchMerged(String searchQuery){
        List<MusicModel> model;
        List<MusicModel> model1;
        List<MusicModel> model2;


        model1=searchSarkiyukle(searchQuery);

        if(model1.size()>0){
            model=model1;
        } else {
            model=searchTbzy(searchQuery);
        }

        return model;
    }



    @Override
    public List<MusicModel> searchTbzy(String searchQuery) {

        //String url1 = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS9hcmFtYS8/YXJhPQ==");
        String url1 = decodeStrings("aHR0cHM6Ly90dWJhenkuY29tLz9hcmE9");
        String search_str = searchQuery.trim().replace(" ", "+");
        Document doc = null;
        List<MusicModel> modelList = new ArrayList<>();


        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            doc = Jsoup.connect(url1 + search_str).get();
            Elements elements = doc.select("div#items > div");

            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);
                MusicModel model = new MusicModel();
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
    public List<MusicModel> searchTbdy(String searchQuery) {

        String url1 = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS9hcmFtYS8/YXJhPQ==");
        String search_str = searchQuery.trim().replace(" ", "+");
        Document doc = null;
        List<MusicModel> modelList = new ArrayList<>();


        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            doc = Jsoup.connect(url1 + search_str).get();
            Elements elements = doc.select("div#items > div");

            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);
                MusicModel model = new MusicModel();
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
    public List<MusicModel> searchSarkiyukle(String searchQuery) {



        String url1 = decodeStrings("aHR0cHM6Ly9zYXJraXl1a2xlaW5kaXIuY29tL2FyYW1hLnBocD9xPQ==");

        String search_str = searchQuery.trim().replace(" ", "+");
        Document doc = null;
        List<MusicModel> modelList = new ArrayList<>();


        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            doc = Jsoup.connect(url1 + search_str).get();
            //Elements elements = doc.select("div#items > div");
            Elements elements = doc.select("div");
            List<Node> node=elements.get(1).parentNode().childNodes();

            for(int i=0; i<node.size();i++) {
                title = node.get(i).attr("title");
                //sarkiyukle sitesinde title i olan node larda videoId var . Bu yüzden bu sekilde yazıldı.
                if(title.length()>0){
                    videoId = (node.get(i).attr("href")).substring(7);
                    photo="https://i.ytimg.com/vi/"+videoId+"/hqdefault.jpg";
                    MusicModel model = new MusicModel();
                    model.setVideo_id_list(videoId);
                    model.setPhoto_url_list(photo);
                    model.setTitle_list(title);
                    modelList.add(model);
                }
            }

        } catch (Exception e) {
        }

        return modelList;
    }

    @Override
    public List<MusicModel> popularTr() {

        String url_tr = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS95ZXJsaS1wb3B1bGVyLXNhcmtpbGFyLw==");
        Document doc = null;
        List<MusicModel> modelList = new ArrayList<>();

        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            doc = Jsoup.connect(url_tr).get();
            Elements elements = doc.select("div#primary").select("div.post-thumb");
            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);

                    MusicModel model = new MusicModel();
                    model.setVideo_id_list((videoId));
                    model.setPhoto_url_list(photo);
                    model.setTitle_list(title);
                    modelList.add(model);

            }
        } catch (Exception e) {
        }
        return modelList;
    }



    @Override
    public List<MusicModel> popularWr() {

        String url_wr = decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS95YWJhbmNpLXBvcHVsZXItc2Fya2lsYXIv");
        Document doc = null;
        List<MusicModel> modelList = new ArrayList<>();

        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            doc = Jsoup.connect(url_wr).get();
            Elements elements = doc.select("div#primary").select("div.post-thumb");
            for (Element element : elements) {
                photo = element.select("img").attr("src");
                title = element.select("img").attr("title");
                videoId = getVideoIdfromImgUrl(photo);
                    MusicModel model = new MusicModel();
                    model.setVideo_id_list((videoId));
                    model.setPhoto_url_list(photo);
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
                //return "Song Not Found";
                return null;
        } catch (Exception e1) {
            //return "Error occured";
            return null;
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
