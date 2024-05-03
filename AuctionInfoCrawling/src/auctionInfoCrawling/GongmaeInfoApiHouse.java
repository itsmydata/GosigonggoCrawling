package auctionInfoCrawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GongmaeInfoApiHouse{
	
	public StringBuilder GongmaeInfo() {

		StringBuilder urlBuilder = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();
        String strToday = sdf.format(cal.getTime());
        cal.add(Calendar.YEAR, 1);
        String strNextYear = sdf.format(cal.getTime());
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        StringBuilder sb = new StringBuilder();
        
		try{
	    	urlBuilder = new StringBuilder("http://openapi.onbid.co.kr/openapi/services/ThingInfoInquireSvc/getUnifyUsageCltr"); /*URL*/
	    	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=GzgfDRv78yu08wAhqvwCXqNWcOtYZO5d5SaF9eEfxXCS1co0Sy6z5k946X8UCZ1ieDGQnyeJfbGzKbn%2FETqwJg%3D%3D"); /*Service Key*/
	    	urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID","UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8")); //부동산
	    	urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID_MID","UTF-8") + "=" + URLEncoder.encode("10200", "UTF-8")); //주거용
	    	urlBuilder.append("&" + URLEncoder.encode("DPSL_MTD_CD","UTF-8") + "=" + URLEncoder.encode("0001", "UTF-8")); /*0001 매각 0002 임대(대부)*/
//	        urlBuilder.append("&" + URLEncoder.encode("SIDO","UTF-8") + "=" + URLEncoder.encode("제주특별자치도", "UTF-8")); /*물건소재지(시도)*/
//	        urlBuilder.append("&" + URLEncoder.encode("SGK","UTF-8") + "=" + URLEncoder.encode("서귀포시", "UTF-8")); /*물건소재지(시군구)*/
//			urlBuilder.append("&" + URLEncoder.encode("EMD","UTF-8") + "=" + URLEncoder.encode("청북읍", "UTF-8")); /*물건소재지(읍면동)*/
//	        urlBuilder.append("&" + URLEncoder.encode("GOODS_PRICE_FROM","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*감정가하한*/
//	  	 	urlBuilder.append("&" + URLEncoder.encode("GOODS_PRICE_TO","UTF-8") + "=" + URLEncoder.encode("10000000", "UTF-8")); /*감정가상한*/
//	        urlBuilder.append("&" + URLEncoder.encode("OPEN_PRICE_FROM","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*최저입찰가하한*/
//	        urlBuilder.append("&" + URLEncoder.encode("OPEN_PRICE_TO","UTF-8") + "=" + URLEncoder.encode("200000000", "UTF-8")); /*최저입찰가상한*/
	        urlBuilder.append("&" + URLEncoder.encode("PBCT_BEGN_DTM","UTF-8") + "=" + URLEncoder.encode(strToday, "UTF-8")); /*YYYYMMDD*/
	        urlBuilder.append("&" + URLEncoder.encode("PBCT_CLS_DTM","UTF-8") +  "=" + URLEncoder.encode(strNextYear, "UTF-8")); /*YYYYMMDD*/ 
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") +  "=" + URLEncoder.encode("100000", "UTF-8")); /*페이지당 데이터개수*/ 
	        urlBuilder.append("&" + URLEncoder.encode("ORG_BASE_NO","UTF-8") +  "=" + URLEncoder.encode("10000", "UTF-8")); /*기관등록번호(온비드)*/ 
	        
	        url = new URL(urlBuilder.toString());
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
//	        System.out.println("Response code: " + conn.getResponseCode());
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlBuilder;
    }
}
