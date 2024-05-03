package auctionInfoCrawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EnvironmentAssessInfoApi{
	
	public StringBuilder EnvironmentAssessInfo(String pointX, String pointY){
	       
		StringBuilder urlBuilder = new StringBuilder();
		
		try{
			 	urlBuilder = new StringBuilder("http://apis.data.go.kr/B090026/SelfDgnssLocplcInfoInqireService/getBeffatEnvrnExmntBeffatBsnsPlaceInfoInqire"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=GzgfDRv78yu08wAhqvwCXqNWcOtYZO5d5SaF9eEfxXCS1co0Sy6z5k946X8UCZ1ieDGQnyeJfbGzKbn%2FETqwJg%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
		        urlBuilder.append("&" + URLEncoder.encode("centerX","UTF-8") + "=" + pointX); /*X 좌표*/
		        urlBuilder.append("&" + URLEncoder.encode("centerY","UTF-8") + "=" + pointY); /*Y 좌표*/
//		        urlBuilder.append("&" + URLEncoder.encode("distance","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*사업지 정보 조회 반경 , 반경이 커질수록 조회건수가 많아짐*/
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
//		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
		return urlBuilder;
    }
}
