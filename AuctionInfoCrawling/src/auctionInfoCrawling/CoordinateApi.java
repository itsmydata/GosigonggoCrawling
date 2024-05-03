package auctionInfoCrawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CoordinateApi{
	
	public String[] CoordinateApi(String addr) throws ParseException, org.json.simple.parser.ParseException {
		
		String apikey = "27C46E25-8379-3926-9197-6D2E31E495DC";
		String searchType = "parcel";
		String searchAddr = addr;
		String epsg = "epsg:4326";
	
		StringBuilder sb = new StringBuilder("https://api.vworld.kr/req/address");
		sb.append("?service=address");
		sb.append("&request=getCoord");
		sb.append("&format=json");
		sb.append("&crs=" + epsg);
		sb.append("&key=" + apikey);
		sb.append("&type=" + searchType);
		sb.append("&address=" + URLEncoder.encode(searchAddr, StandardCharsets.UTF_8));
	
		try{
		    URL url = new URL(sb.toString());
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
	
		    JSONParser jspa = new JSONParser();
		    JSONObject jsob = (JSONObject) jspa.parse(reader);
		    JSONObject jsrs = (JSONObject) jsob.get("response");
		    JSONObject jsResult = (JSONObject) jsrs.get("result");
		    JSONObject jspoitn = (JSONObject) jsResult.get("point");
		    
		    String arr[] = new String[2];
		    
		    arr[0] = (String) jspoitn.get("x");
		    arr[1] = (String) jspoitn.get("y");
		    
		    return arr;
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
	
	}
}
