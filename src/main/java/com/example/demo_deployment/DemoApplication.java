package com.example.demo_deployment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String helloOpenShift(){
		return "Burak Yeşilbağ dan selamlar :D :D :D ";
	}

	@GetMapping("/{searchRequest}")
	public List<Model> hello(@PathVariable String searchRequest){

		return YtSearchMusicMp3(searchRequest);
	}

	public List<Model> YtSearchMusicMp3(String searchQuery){


		String title_str=null;
		String photo_url_str=null;
		String videoid_str=null;


		String url1 =decodeStrings("aHR0cHM6Ly93d3cudHViaWR5Y2VwLmNvbS9hcmFtYS8/YXJhPQ==");
		String search_str = searchQuery.trim().replace(" ", "+");
		Document doc = null;
		List<Model> modelList = new ArrayList<>();





		try {

			doc = Jsoup.connect(url1+search_str).get();
			Elements searchh2=doc.select ("div#items > div");

			for(int i=0;i<searchh2.size();i++){
				Element ser1=searchh2.get(i);
				Elements ser2=ser1.getElementsByClass("post-thumb");
				Elements el_img=ser2.select("img");
				photo_url_str=el_img.attr("src");
				title_str=el_img.attr("title");
				videoid_str=getVideoIdfromImgUrl(photo_url_str);
				Model model = new Model();
				model.setPhoto_url_list(photo_url_str);
				model.setVideo_id_list(videoid_str);
				model.setTitle_list(title_str);
				modelList.add(model);
			}

		}

		catch (Exception e){


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

	private String getVideoIdfromImgUrl(String imageurl){
		int hq_index=imageurl.lastIndexOf("default.")-3;
		int start_index=hq_index-11;
		String videoIdd=imageurl.substring(start_index,hq_index);
		return videoIdd;
	}

}
