package gosigonggoCrawling;
import gosigonggoCrawling.BosangGonggoInfoCrawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.FileInputStream;

public class main {
	public static void main(String[] args) throws IOException {

	String[][] arrGwangyeok = new String[1000][5];
	String[][] arrGyeonggi = new String[1000][5];
	String[][] arrGangwon = new String[1000][5];
	String[][] arrChungbuk = new String[1000][5];
	String[][] arrChungnam = new String[1000][5];
	String[][] arrJeonnam = new String[1000][5];
	String[][] arrJeonbuk = new String[1000][5];
	String[][] arrGyeongnam = new String[1000][5];
	String[][] arrGyeongbuk = new String[1000][5];
	String[][] arrJeju = new String[1000][5];
	String[][] arrGita = new String[1000][5];
	
	
	String[][] arrSilsyGwangyeok = new String[1000][5];
	String[][] arrSilsyGyeonggi = new String[1000][5];
	String[][] arrSilsyGangwon = new String[1000][5];
	String[][] arrSilsyChungbuk = new String[1000][5];
	String[][] arrSilsyChungnam = new String[1000][5];
	String[][] arrSilsyJeonnam = new String[1000][5];
	String[][] arrSilsyJeonbuk = new String[1000][5];
	String[][] arrSilsyGyeongnam = new String[1000][5];
	String[][] arrSilsyGyeongbuk = new String[1000][5];
	String[][] arrSilsyJeju = new String[1000][5];
	
	BosangGonggoInfoCrawling bosangGonggoInfoCrawling = new BosangGonggoInfoCrawling();
	arrGwangyeok = bosangGonggoInfoCrawling.GwangyeokCrawling();
	arrGyeonggi = bosangGonggoInfoCrawling.GyeonggiCrawling();
	arrGangwon = bosangGonggoInfoCrawling.GangwonCrawling();
	arrChungnam = bosangGonggoInfoCrawling.ChungnamCrawling();
	arrChungbuk = bosangGonggoInfoCrawling.ChungbukCrawling();
	arrJeonbuk = bosangGonggoInfoCrawling.JeonbukCrawling();
	arrJeonnam = bosangGonggoInfoCrawling.JeonnamCrawling();
	arrGyeongbuk = bosangGonggoInfoCrawling.GyeongbukCrawling();
	arrGyeongnam = bosangGonggoInfoCrawling.GyeongnamCrawling();
	arrJeju = bosangGonggoInfoCrawling.JejuCrawling();
	arrGita = bosangGonggoInfoCrawling.GitaCrawling();

	SilsyGosiCrawling silsyGosiCrawling = new SilsyGosiCrawling();
	arrSilsyGwangyeok = silsyGosiCrawling.GwangyeokCrawling();
	arrSilsyGyeonggi = silsyGosiCrawling.GyeonggiCrawling();
	arrSilsyGangwon = silsyGosiCrawling.GangwonCrawling();
	arrSilsyChungnam = silsyGosiCrawling.ChungnamCrawling();
	arrSilsyChungbuk = silsyGosiCrawling.ChungbukCrawling();
	arrSilsyJeonnam = silsyGosiCrawling.JeonnamCrawling();
	arrSilsyJeonbuk = silsyGosiCrawling.JeonbukCrawling();
	arrSilsyGyeongnam = silsyGosiCrawling.GyeongnamCrawling();
	arrSilsyGyeongbuk = silsyGosiCrawling.GyeongbukCrawling();
	arrSilsyJeju = silsyGosiCrawling.JejuCrawling();
	
	
	// DB 커넥션
	Statement st = null;
	PreparedStatement pstmt = null;
	Connection connection = null;
	String sql = "insert into bosangGosiInfo(GIGWAN_NM,TITLE,DEPT,REG_DATE,GOSI_NO, TYPE)"
				+ "values (?, ?, ?, ?, ?, ?)";

	try {

		connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "a");
		connection.setAutoCommit(false);

		st = connection.createStatement();
		pstmt = connection.prepareStatement(sql);

		// 보상고시정보 기존 데이터 삭제
		st.executeUpdate("truncate bosangGosiInfo");

		for (int temp = 1; temp < arrGwangyeok.length; temp++) {
			pstmt.setString(1, arrGwangyeok[temp][0]);
			pstmt.setString(2, arrGwangyeok[temp][1]);
			pstmt.setString(3, arrGwangyeok[temp][2]);
			pstmt.setString(4, arrGwangyeok[temp][3]);
			pstmt.setString(5, arrGwangyeok[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrGyeonggi.length; temp++) {
			pstmt.setString(1, arrGyeonggi[temp][0]);
			pstmt.setString(2, arrGyeonggi[temp][1]);
			pstmt.setString(3, arrGyeonggi[temp][2]);
			pstmt.setString(4, arrGyeonggi[temp][3]);
			pstmt.setString(5, arrGyeonggi[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for

		for (int temp = 1; temp < arrGangwon.length; temp++) {
			pstmt.setString(1, arrGangwon[temp][0]);
			pstmt.setString(2, arrGangwon[temp][1]);
			pstmt.setString(3, arrGangwon[temp][2]);
			pstmt.setString(4, arrGangwon[temp][3]);
			pstmt.setString(5, arrGangwon[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrChungbuk.length; temp++) {
			pstmt.setString(1, arrChungbuk[temp][0]);
			pstmt.setString(2, arrChungbuk[temp][1]);
			pstmt.setString(3, arrChungbuk[temp][2]);
			pstmt.setString(4, arrChungbuk[temp][3]);
			pstmt.setString(5, arrChungbuk[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrChungnam.length; temp++) {
			pstmt.setString(1, arrChungnam[temp][0]);
			pstmt.setString(2, arrChungnam[temp][1]);
			pstmt.setString(3, arrChungnam[temp][2]);
			pstmt.setString(4, arrChungnam[temp][3]);
			pstmt.setString(5, arrChungnam[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrJeonbuk.length; temp++) {
			pstmt.setString(1, arrJeonbuk[temp][0]);
			pstmt.setString(2, arrJeonbuk[temp][1]);
			pstmt.setString(3, arrJeonbuk[temp][2]);
			pstmt.setString(4, arrJeonbuk[temp][3]);
			pstmt.setString(5, arrJeonbuk[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrJeonnam.length; temp++) {
			pstmt.setString(1, arrJeonnam[temp][0]);
			pstmt.setString(2, arrJeonnam[temp][1]);
			pstmt.setString(3, arrJeonnam[temp][2]);
			pstmt.setString(4, arrJeonnam[temp][3]);
			pstmt.setString(5, arrJeonnam[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrGyeongbuk.length; temp++) {
			pstmt.setString(1, arrGyeongbuk[temp][0]);
			pstmt.setString(2, arrGyeongbuk[temp][1]);
			pstmt.setString(3, arrGyeongbuk[temp][2]);
			pstmt.setString(4, arrGyeongbuk[temp][3]);
			pstmt.setString(5, arrGyeongbuk[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrGyeongnam.length; temp++) {
			pstmt.setString(1, arrGyeongnam[temp][0]);
			pstmt.setString(2, arrGyeongnam[temp][1]);
			pstmt.setString(3, arrGyeongnam[temp][2]);
			pstmt.setString(4, arrGyeongnam[temp][3]);
			pstmt.setString(5, arrGyeongnam[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for

		for (int temp = 1; temp < arrJeju.length; temp++) {
			pstmt.setString(1, arrJeju[temp][0]);
			pstmt.setString(2, arrJeju[temp][1]);
			pstmt.setString(3, arrJeju[temp][2]);
			pstmt.setString(4, arrJeju[temp][3]);
			pstmt.setString(5, arrJeju[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		for (int temp = 1; temp < arrGita.length; temp++) {
			pstmt.setString(1, arrGita[temp][0]);
			pstmt.setString(2, arrGita[temp][1]);
			pstmt.setString(3, arrGita[temp][2]);
			pstmt.setString(4, arrGita[temp][3]);
			pstmt.setString(5, arrGita[temp][4]);
			pstmt.setString(6, "보상계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		
		// 실시계획 시작
		for (int temp = 1; temp < arrSilsyGwangyeok.length; temp++) {
			if(arrSilsyGwangyeok[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyGwangyeok[temp][0]);
			pstmt.setString(2, arrSilsyGwangyeok[temp][1]);
			pstmt.setString(3, arrSilsyGwangyeok[temp][2]);
			pstmt.setString(4, arrSilsyGwangyeok[temp][3]);
			pstmt.setString(5, arrSilsyGwangyeok[temp][4]);
			pstmt.setString(6, "실시계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for

		for (int temp = 1; temp < arrSilsyGyeonggi.length; temp++) {
			if(arrSilsyGyeonggi[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyGyeonggi[temp][0]);
			pstmt.setString(2, arrSilsyGyeonggi[temp][1]);
			pstmt.setString(3, arrSilsyGyeonggi[temp][2]);
			pstmt.setString(4, arrSilsyGyeonggi[temp][3]);
			pstmt.setString(5, arrSilsyGyeonggi[temp][4]);
			pstmt.setString(6, "실시계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrSilsyGangwon.length; temp++) {
			if(arrSilsyGangwon[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyGangwon[temp][0]);
			pstmt.setString(2, arrSilsyGangwon[temp][1]);
			pstmt.setString(3, arrSilsyGangwon[temp][2]);
			pstmt.setString(4, arrSilsyGangwon[temp][3]);
			pstmt.setString(5, arrSilsyGangwon[temp][4]);
			pstmt.setString(6, "실시계획");

			// addBatch 하기
			pstmt.addBatch();

			// 파라미터 Clear
			pstmt.clearParameters();

		} // for
		
		for (int temp = 1; temp < arrSilsyChungbuk.length; temp++) {
			if(arrSilsyChungbuk[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyChungbuk[temp][0]);
			pstmt.setString(2, arrSilsyChungbuk[temp][1]);
			pstmt.setString(3, arrSilsyChungbuk[temp][2]);
			pstmt.setString(4, arrSilsyChungbuk[temp][3]);
			pstmt.setString(5, arrSilsyChungbuk[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for
	
		for (int temp = 1; temp < arrSilsyChungnam.length; temp++) {
			if(arrSilsyChungnam[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyChungnam[temp][0]);
			pstmt.setString(2, arrSilsyChungnam[temp][1]);
			pstmt.setString(3, arrSilsyChungnam[temp][2]);
			pstmt.setString(4, arrSilsyChungnam[temp][3]);
			pstmt.setString(5, arrSilsyChungnam[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for
		
		for (int temp = 1; temp < arrSilsyJeonbuk.length; temp++) {
			if(arrSilsyJeonbuk[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyJeonbuk[temp][0]);
			pstmt.setString(2, arrSilsyJeonbuk[temp][1]);
			pstmt.setString(3, arrSilsyJeonbuk[temp][2]);
			pstmt.setString(4, arrSilsyJeonbuk[temp][3]);
			pstmt.setString(5, arrSilsyJeonbuk[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for
		
		for (int temp = 1; temp < arrSilsyJeonnam.length; temp++) {
			if(arrSilsyJeonnam[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyJeonnam[temp][0]);
			pstmt.setString(2, arrSilsyJeonnam[temp][1]);
			pstmt.setString(3, arrSilsyJeonnam[temp][2]);
			pstmt.setString(4, arrSilsyJeonnam[temp][3]);
			pstmt.setString(5, arrSilsyJeonnam[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for
		
		for (int temp = 1; temp < arrSilsyGyeongbuk.length; temp++) {
			if(arrSilsyGyeongbuk[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyGyeongbuk[temp][0]);
			pstmt.setString(2, arrSilsyGyeongbuk[temp][1]);
			pstmt.setString(3, arrSilsyGyeongbuk[temp][2]);
			pstmt.setString(4, arrSilsyGyeongbuk[temp][3]);
			pstmt.setString(5, arrSilsyGyeongbuk[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for

		for (int temp = 1; temp < arrSilsyGyeongnam.length; temp++) {
			if(arrSilsyGyeongnam[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyGyeongnam[temp][0]);
			pstmt.setString(2, arrSilsyGyeongnam[temp][1]);
			pstmt.setString(3, arrSilsyGyeongnam[temp][2]);
			pstmt.setString(4, arrSilsyGyeongnam[temp][3]);
			pstmt.setString(5, arrSilsyGyeongnam[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for
		
		for (int temp = 1; temp < arrSilsyJeju.length; temp++) {
			if(arrSilsyJeju[temp][0] == null) {
				break;
			}
			pstmt.setString(1, arrSilsyJeju[temp][0]);
			pstmt.setString(2, arrSilsyJeju[temp][1]);
			pstmt.setString(3, arrSilsyJeju[temp][2]);
			pstmt.setString(4, arrSilsyJeju[temp][3]);
			pstmt.setString(5, arrSilsyJeju[temp][4]);
			pstmt.setString(6, "실시계획");
	
			// addBatch 하기
			pstmt.addBatch();
	
			// 파라미터 Clear
			pstmt.clearParameters();
	
		} // for
		// 다 담은 후 Batch 실행
		pstmt.executeBatch();

		// 커밋
		connection.commit();

		// Batch 초기화
		pstmt.clearBatch();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}