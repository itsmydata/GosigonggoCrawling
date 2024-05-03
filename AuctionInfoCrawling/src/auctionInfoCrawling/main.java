package auctionInfoCrawling;

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
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class main {
	@SuppressWarnings("null")
	public static void main(String[] args)
			throws IOException, SQLException, ParserConfigurationException, SAXException {

		// 변수 선언부
		int rowIndex = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    Calendar cal = Calendar.getInstance();
	    String strToday = sdf.format(cal.getTime());
	    
	    // 현재까지 업로드된 경, 공매 물건정보 가져오기
	    // API(공매), 크롤링(경매) 호출
		// 공매 토지물건 정보 API로 가져오기
		System.out.println("GongmaeInfoApiLand Start");
		GongmaeInfoApiLand gongInfoLand = new GongmaeInfoApiLand();
		StringBuilder gongmaeSbLand = gongInfoLand.GongmaeInfo();

		// 공매 단독주택물건 정보 API로 가져오기
		System.out.println("GongmaeInfoApiHouse Start");
		GongmaeInfoApiHouse gongInfoHouse = new GongmaeInfoApiHouse();
		StringBuilder gongmaeSbHouse = gongInfoHouse.GongmaeInfo();
		
		// 경매(대법원) 물건정보 크롤링으로 가져오기
		System.out.println("CourtAuctionCrawling Start");
		GyeongmaeInfoCrawling gyeongInfo = new GyeongmaeInfoCrawling();
		String gyeongmaeInfoArr[][] = gyeongInfo.CourtAuctionCrawling();

		// 가져온 공매정보 파싱
		System.out.println("GongmaeInfoParse Start");
		String gongmaeInfoArrLand[][] = GongmaeParse(gongmaeSbLand);
		String gongmaeInfoArrHouse[][] = GongmaeParse(gongmaeSbHouse);
		
		// 불러온 경, 공매정보 배열의 총 크기
		int totalLength = gongmaeInfoArrLand.length
				+ gongmaeInfoArrHouse.length
				+ gyeongmaeInfoArr.length;		
		String[][] integrateArr = new String[totalLength][20];

		// 경매+공매정보 한 배열로 합치기
		for (int i = 0; i < gongmaeInfoArrLand.length; i++) {
		    System.arraycopy(gongmaeInfoArrLand[i], 0, integrateArr[rowIndex++], 0, 14);
		}
		for (int i = 0; i < gongmaeInfoArrHouse.length; i++) {
		    System.arraycopy(gongmaeInfoArrHouse[i], 0, integrateArr[rowIndex++], 0, 14);
		}
		for (int i = 0; i < gyeongmaeInfoArr.length; i++) {
		    System.arraycopy(gyeongmaeInfoArr[i], 0, integrateArr[rowIndex++], 0, 14);
		}

		System.out.println("총 공매토지 추출 건수: " + gongmaeInfoArrLand.length);
		System.out.println("총 공매주택 추출 건수: " + gongmaeInfoArrHouse.length);
		System.out.println("총 경매 추출 건수: " + gyeongmaeInfoArr.length);
		System.out.println("총 추출 건수: " + integrateArr.length);
		
		// 좌표정보 추가(미사용)
		//integrateArr = AddCoordinateInfo(integrateArr);

		// 최종 추출 공매정보 배열 생성
		String extractArr[][] = new String[integrateArr.length][20];

		// 엑셀 제목 행 설정
		extractArr[0][0] = "물건관리번호";
		extractArr[0][1] = "용도";
		extractArr[0][2] = "물건소재지";
		extractArr[0][3] = "입찰시작일시";
		extractArr[0][4] = "입찰종료일시";
		extractArr[0][5] = "물건상태";
		extractArr[0][6] = "유찰횟수";
		extractArr[0][7] = "조회수";
		extractArr[0][8] = "물건상세정보";
		extractArr[0][9] = "입찰방식";
		extractArr[0][10] = "최저입찰가";
		extractArr[0][11] = "최저입찰가율";
		extractArr[0][12] = "평균감정가";
		extractArr[0][13] = "다주소여부";
		extractArr[0][14] = "지목";
		extractArr[0][15] = "면적";
		extractArr[0][16] = "국계법에 따른 지역지구";
		extractArr[0][17] = "기타법에 따른 지역지구";
		extractArr[0][18] = "「토지이용규제 기본법 시행령」제9조 제4항 각 호에 해당되는 사항";
		extractArr[0][19] = "등록일자";

//      //////////////////////////////////////
//      // DB 커넥션
//      //////////////////////////////////////
		
		Statement st = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String sql = "insert into courtAuctionInfo(" + "CLTR_MNMT_NO" + ",CTGR_FULL_NM" + ",LDNM_ADRS"
				+ ",PBCT_BEGN_DTM" + ",PBCT_CLS_DTM" + ",PBCT_CLTR_STAT_NM" + ",USCBD_CNT" + ",IQRY_CNT" + ",GOODS_NM"
				+ ",BID_MTD_NM" + ",MIN_BID_PRC" + ",FEE_RATE" + ",APSL_ASES_AVG_AMT" + ",MANY_ADDR_YN" + ",JIMOK" + ",AREA" + ",LOCA1" + ",LOCA2" + ",LOCA3" + ",REG_DATE"
				+ ")values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "a");
			connection.setAutoCommit(false);

			st = connection.createStatement();
			pstmt = connection.prepareStatement(sql);

			// 공매정보 기존 데이터 삭제
			st.executeUpdate("truncate courtAuctionInfo");

			// API로 불러온 경공매 정보를 수치 처리 후 DB에 저장
			for (rowIndex = 1; rowIndex < integrateArr.length; rowIndex++) {
				
				pstmt.setString(1, integrateArr[rowIndex][0]);
				pstmt.setString(2, integrateArr[rowIndex][1]);
				pstmt.setString(3, integrateArr[rowIndex][2].trim());
				pstmt.setString(4, integrateArr[rowIndex][3]);
				pstmt.setString(5, integrateArr[rowIndex][4]);
				pstmt.setString(6, integrateArr[rowIndex][5]);
				pstmt.setString(7, integrateArr[rowIndex][6].toString());
				pstmt.setString(8, integrateArr[rowIndex][7].toString());
				pstmt.setString(9, integrateArr[rowIndex][8]);
				pstmt.setString(10, integrateArr[rowIndex][9]);
				
				//감정가 등 수치값이 잘못들어가는 경우 간혹 발생
				if(integrateArr[rowIndex][10].equals("-")) {
					pstmt.setLong(11, Long.parseLong("0"));
				}else if(integrateArr[rowIndex][10] == null){
					pstmt.setLong(11, Long.parseLong("0"));
				}else{
					pstmt.setLong(11, Long.parseLong(integrateArr[rowIndex][10]));
				}
				
				if(integrateArr[rowIndex][12].equals("-")) {
					pstmt.setLong(13, Long.parseLong("0"));
				}else if(integrateArr[rowIndex][10] == null){
					pstmt.setLong(13, Long.parseLong("0"));
				}else {
					pstmt.setLong(13, Long.parseLong(integrateArr[rowIndex][12]));
				}

				pstmt.setString(12, integrateArr[rowIndex][11]);
				pstmt.setString(14, integrateArr[rowIndex][13]);
				pstmt.setString(15, integrateArr[rowIndex][14]);
				pstmt.setString(16, integrateArr[rowIndex][15]);
				pstmt.setString(17, integrateArr[rowIndex][16]);
				pstmt.setString(18, integrateArr[rowIndex][17]);
				pstmt.setString(19, integrateArr[rowIndex][18]);
				pstmt.setString(20, strToday);

				// addBatch 하기
				pstmt.addBatch();

				// 파라미터 Clear
				pstmt.clearParameters();

				if (rowIndex % 1000 == 0) {
					System.out.println("현재 경매+공매정보 테이블 저장건수: " + rowIndex);
				}

			} // for

			// 다 담은 후 Batch 실행
			pstmt.executeBatch();

			// 커밋
			connection.commit();

			// Batch 초기화
			pstmt.clearBatch();
			
			//기존 공매정보 upsert 작업 수행
			st.executeUpdate("INSERT INTO courtauctionInfoExtract ("
							+ " cltr_mnmt_no, ctgr_full_nm, ldnm_adrs, pbct_begn_dtm, pbct_cls_dtm, pbct_cltr_stat_nm"
							+ " ,uscbd_cnt,goods_nm,bid_mtd_nm,min_bid_prc,fee_rate,apsl_ases_avg_amt"
							+ " ,many_addr_yn, jimok, area, loca1, loca2, loca3, reg_date) "
							+ " select XX.cltr_mnmt_no, XX.ctgr_full_nm, XX.ldnm_adrs, XX.pbct_begn_dtm, XX.pbct_cls_dtm "
							+ " ,XX.pbct_cltr_stat_nm,XX.uscbd_cnt,XX.goods_nm,XX.bid_mtd_nm,XX.min_bid_prc "
							+ " ,XX.fee_rate,XX.apsl_ases_avg_amt,XX.many_addr_yn,XX.jimok,XX.area,XX.loca1,XX.loca2,XX.loca3,XX.reg_date "
							+ " from( "
							+ " select X.cltr_mnmt_no, X.ctgr_full_nm, X.ldnm_adrs, X.pbct_begn_dtm, X.pbct_cls_dtm "
							+ " ,X.pbct_cltr_stat_nm,X.uscbd_cnt,X.goods_nm,X.bid_mtd_nm,X.min_bid_prc "
							+ " ,X.fee_rate,X.apsl_ases_avg_amt,X.many_addr_yn,X.jimok,X.area,X.loca1,X.loca2,X.loca3,X.reg_date "
							+ " ,RANK() OVER (PARTITION BY X.CLTR_MNMT_NO ORDER BY X.goods_nm) RRANK"
							+ " from( "
							+ " SELECT distinct AA.cltr_mnmt_no, AA.ctgr_full_nm, AA.ldnm_adrs, AA.pbct_begn_dtm, AA.pbct_cls_dtm"
							+ " ,AA.pbct_cltr_stat_nm,AA.uscbd_cnt,AA.goods_nm,AA.bid_mtd_nm,AA.min_bid_prc"
							+ " ,AA.fee_rate,AA.apsl_ases_avg_amt,AA.many_addr_yn,AA.jimok,AA.area,AA.loca1,AA.loca2,AA.loca3,AA.reg_date"
							+ " ,RANK() OVER (PARTITION BY AA.CLTR_MNMT_NO ORDER BY AA.iqry_cnt) RRANK"
							+ " FROM COURTAUCTIONINFO AA"
							+ " ,("
							+ "	select distinct"
							+ "	A.CLTR_MNMT_NO"
							+ "	,A.ldnm_adrs"
							+ "	,B.PBCT_BEGN_DTM"
							+ "	,RANK() OVER (PARTITION BY A.CLTR_MNMT_NO ORDER BY A.ldnm_adrs) RRANK "
							+ "	FROM COURTAUCTIONINFO A"
							+ "	,("
							+ "		SELECT distinct "
							+ "		CLTR_MNMT_NO,"
							+ "		PBCT_BEGN_DTM"
							+ "		,RANK() OVER (PARTITION BY CLTR_MNMT_NO ORDER BY PBCT_BEGN_DTM) RRANK "
							+ "		FROM COURTAUCTIONINFO "
							+ "		WHERE 1=1"
							+ " and (goods_nm like '토지%'"
							+ " or goods_nm like '창고용지%'"
							+ " or goods_nm like '종교용지%'"
							+ " or goods_nm like '제방%'"
							+ " or goods_nm like '전%'"
							+ " or goods_nm like '잡종지%'"
							+ " or goods_nm like '임야%'"
							+ " or goods_nm like '묘지%'"
							+ " or goods_nm like '유지%'"
							+ " or goods_nm like '도로%'"
							+ " or goods_nm like '대지%'"
							+ " or goods_nm like '목장용지%')"
							+ "		and (ctgr_full_nm like '%토지%'"
							+ "		or ctgr_full_nm like '%전답%'"
							+ "		or ctgr_full_nm like '%임야%'"
							+ "		or ctgr_full_nm like '%대지%'"
							+ "		or ctgr_full_nm = '기타'"
							+ "		or ctgr_full_nm like '%단독주택%')"
							+ "	) B"
							+ "	WHERE A.CLTR_MNMT_NO = B.CLTR_MNMT_NO"
							+ "	AND A.PBCT_BEGN_DTM = B.PBCT_BEGN_DTM"
							+ "	AND B.RRANK = '1'"
							+ " and (goods_nm like '토지%'"
							+ " or goods_nm like '창고용지%'"
							+ " or goods_nm like '종교용지%'"
							+ " or goods_nm like '제방%'"
							+ " or goods_nm like '전%'"
							+ " or goods_nm like '잡종지%'"
							+ " or goods_nm like '임야%'"
							+ " or goods_nm like '묘지%'"
							+ " or goods_nm like '유지%'"
							+ " or goods_nm like '도로%'"
							+ " or goods_nm like '대지%'"
							+ " or goods_nm like '목장용지%')"
							+ "	AND (ctgr_full_nm like '%토지%'"
							+ "		or ctgr_full_nm like '%전답%'"
							+ "		or ctgr_full_nm like '%임야%'"
							+ "		or ctgr_full_nm like '%대지%'"
							+ "		or ctgr_full_nm = '기타'"
							+ "		or ctgr_full_nm like '%단독주택%')"
							+ " )BB "
							+ " WHERE AA.CLTR_MNMT_NO = BB.CLTR_MNMT_NO"
							+ " AND AA.PBCT_BEGN_DTM = BB.PBCT_BEGN_DTM"
							+ " AND AA.ldnm_adrs = BB.ldnm_adrs"
							+ " AND BB.RRANK = '1'"
							+ " and (goods_nm like '토지%'"
							+ " or goods_nm like '창고용지%'"
							+ " or goods_nm like '종교용지%'"
							+ " or goods_nm like '제방%'"
							+ " or goods_nm like '전%'"
							+ " or goods_nm like '잡종지%'"
							+ " or goods_nm like '임야%'"
							+ " or goods_nm like '묘지%'"
							+ " or goods_nm like '유지%'"
							+ " or goods_nm like '도로%'"
							+ " or goods_nm like '대지%'"
							+ " or goods_nm like '목장용지%')"
							+ " AND (ctgr_full_nm like '%토지%'"
							+ "	or ctgr_full_nm like '%전답%'"
							+ "	or ctgr_full_nm like '%임야%'"
							+ "	or ctgr_full_nm like '%대지%'"
							+ "	or ctgr_full_nm = '기타'"
							+ "	or ctgr_full_nm like '%단독주택%')"
							+ " )X "
							+ " where X.rrank = '1' "
							+ " order by X.cltr_mnmt_no "
							+ " )XX "
							+ " where XX.rrank = '1' "
							+ " ON conflict on constraint pk_cltr_mnmt_no "
							+ " DO update SET "
							+ " ctgr_full_nm = excluded.ctgr_full_nm"
							+ " ,ldnm_adrs = excluded.ldnm_adrs"
							+ " ,pbct_begn_dtm = excluded.pbct_begn_dtm"
							+ " ,pbct_cls_dtm = excluded.pbct_cls_dtm"
							+ " ,pbct_cltr_stat_nm = excluded.pbct_cltr_stat_nm"
							+ " ,uscbd_cnt = excluded.uscbd_cnt"
							+ " ,goods_nm = excluded.goods_nm"
							+ " ,bid_mtd_nm = excluded.bid_mtd_nm"
							+ " ,min_bid_prc = excluded.min_bid_prc"
							+ " ,fee_rate = excluded.fee_rate"
							+ " ,apsl_ases_avg_amt = excluded.apsl_ases_avg_amt"
							+ " ,many_addr_yn = excluded.many_addr_yn");

			connection.commit();
			String extractSql = "select cltr_mnmt_no,ctgr_full_nm,ldnm_adrs,pbct_begn_dtm,"
								+ "pbct_cls_dtm,pbct_cltr_stat_nm,uscbd_cnt,iqry_cnt,goods_nm,bid_mtd_nm,"
								+ "min_bid_prc,fee_rate,apsl_ases_avg_amt,many_addr_yn"
								+ ",jimok,area,loca1,loca2,loca3,reg_date  from COURTAUCTIONINFOEXTRACT "
								+ " where (jimok is null or jimok = '') and pbct_cls_dtm > to_char(now(), 'YYYYMMDD')";
			
			pstmt = connection.prepareStatement(extractSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행
			
			//토지이용계획정보 업데이트
			String updateSql = "update COURTAUCTIONINFOEXTRACT set "
					+ "jimok = ?"
					+ ",area = ?"
					+ ",loca1 = ?"
					+ ",loca2 = ?"
					+ ",loca3 = ?"
					+ " where cltr_mnmt_no = ?"
					+ " and pbct_begn_dtm = ?";
  			pstmt = connection.prepareStatement(updateSql);
			int index = 1;
			
			//토지이용계획정보 수 구하기
			rs.last();
			int rsRow = rs.getRow();
			rs.beforeFirst();
			System.out.println("신규추가 건수: "+ rsRow);
			while (rs.next()) {

				extractArr[index][0] = rs.getString("cltr_mnmt_no");
				extractArr[index][1] = rs.getString("ctgr_full_nm");
				extractArr[index][2] = rs.getString("ldnm_adrs");
				extractArr[index][3] = rs.getString("pbct_begn_dtm");
				extractArr[index][4] = rs.getString("pbct_cls_dtm");
				extractArr[index][5] = rs.getString("pbct_cltr_stat_nm");
				extractArr[index][6] = rs.getString("uscbd_cnt");
				extractArr[index][7] = rs.getString("iqry_cnt");
				extractArr[index][8] = rs.getString("goods_nm");
				extractArr[index][9] = rs.getString("bid_mtd_nm");
				extractArr[index][10] = rs.getString("min_bid_prc");
				extractArr[index][11] = rs.getString("fee_rate").replace("(", "").replace(")", "");
				extractArr[index][12] = rs.getString("apsl_ases_avg_amt");
				extractArr[index][13] = rs.getString("many_addr_yn");
				
				// 토지이용계획정보
				System.out.println("소재지: " + extractArr[index][2]);
				String landArr[] = landUsage(extractArr[index][2]);
				if (landArr[0] != null) {
					extractArr[index][14] = landArr[0]; // 지목
					extractArr[index][15] = landArr[1].replaceAll("(\\ ㎡|\\,|\b|\t|\n|\f|\r|\\\'|\\\")", ""); // 면적
					extractArr[index][16] = landArr[2]; // 국계법에 따른 지역지구
					extractArr[index][17] = landArr[3]; // 기타법에 따른 지역지구
					extractArr[index][18] = landArr[4]; // 「토지이용규제 기본법 시행령」제9조 제4항 각 호에 해당되는 사항
				} else {
					extractArr[index][14] = "";
					extractArr[index][15] = "";
					extractArr[index][16] = "";
					extractArr[index][17] = "";
					extractArr[index][18] = "";
				} 

				extractArr[index][19] = rs.getString("reg_date");
				
	  			// 토지이용계획정보를 전부 업데이트
		    	  pstmt.setString(1, extractArr[index][14]);
		    	  pstmt.setString(2, extractArr[index][15]);
		    	  pstmt.setString(3, extractArr[index][16]);
		    	  pstmt.setString(4, extractArr[index][17]);
		    	  pstmt.setString(5, extractArr[index][18]);
		    	  pstmt.setString(6, extractArr[index][0]);	//물건번호
		    	  pstmt.setString(7, extractArr[index][3]);	//시작일자
		          //addBatch 하기
		          pstmt.addBatch();
		          //파라미터 Clear
		          pstmt.clearParameters();
		          
		    	//크롬드라이버 강제 task kill
		        try {
		            Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
		            Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
		            Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		            Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		        } catch (IOException e) {
		            System.out.println("Failed to close one or more driver exe files");
		        }

	    	    System.out.println("현재 토지이용계획 진행상황: " + index + " / "+rsRow);
		        
		        //20개마다 commit
		          if(index%20 == 0) {
		    	      //다 담은 후 Batch 실행
		    	      pstmt.executeBatch();
		    	          
		    	      //커밋
		    	      connection.commit();
		    	          
		    	      //Batch 초기화
		    	      pstmt.clearBatch();
		    	      
//		    	      System.out.println("현재 토지이용계획 진행상황: " + index + " / "+rsRow);
		          }
				index++;
			}
			
	      //다 담은 후 Batch 실행
	      pstmt.executeBatch();
	          
	      //커밋
	      connection.commit();
	          
	      //Batch 초기화
	      pstmt.clearBatch();
	      
	      System.out.println("현재 토지이용계획 진행상황: " + (index-1) + " / "+rsRow);
		      
		      
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 꼭 열었던 순서대로 닫아줘야함!!★★★
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}

		}


//		엑셀 추출(현재 미사용)
//		
//		List<XSSFWorkbook> list = new ArrayList<>();
//
//		// 엑셀 시트설정
//		for (int k = 0; k < (index / 2000) + 1; k++) {
//			XSSFWorkbook workBook = new XSSFWorkbook();
//			CellStyle defaultStyle = workBook.createCellStyle();
//			BufferedWriter bw = new BufferedWriter(
//					new OutputStreamWriter(new FileOutputStream("C:/EXCEL_TEST/GongmaeInfoCSV" + k + ".csv"), "MS949"));
//
//			// 테두리 설정
//			defaultStyle.setBorderTop(BorderStyle.THIN);
//			defaultStyle.setBorderLeft(BorderStyle.THIN);
//			defaultStyle.setBorderRight(BorderStyle.THIN);
//			defaultStyle.setBorderBottom(BorderStyle.THIN);
//
//			// 줄 바꿈 및 중앙 정렬
//			defaultStyle.setWrapText(true);
//			defaultStyle.setAlignment(HorizontalAlignment.CENTER);
//			defaultStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//
//			// 시트 생성 및 셀 높이 설정
//			XSSFSheet sheet = workBook.createSheet();
//			sheet.setDefaultRowHeightInPoints(30);
//
//			// 2000개 단위로 엑셀을 나눠서 데이터 저장시킴
//			for (int i = k * 2000; i < ((k + 1) * 2000) - 1; i++) {
//				String str = "";
//				if (extractArr[i][0] == null) {
//					System.out.println("중복제거한 총 데이터 수: " + (i-1));
//					break;
//				}
//				if (i % 2000 == 0) {
//					// 엑셀 제목 행 설정
//					extractArr[i][0] = "물건관리번호";
//					extractArr[i][1] = "용도";
//					extractArr[i][2] = "물건소재지";
//					extractArr[i][3] = "입찰시작일시";
//					extractArr[i][4] = "입찰종료일시";
//					extractArr[i][5] = "물건상태";
//					extractArr[i][6] = "유찰횟수";
//					extractArr[i][7] = "조회수";
//					extractArr[i][8] = "물건상세정보";
//					extractArr[i][9] = "입찰방식";
//					extractArr[i][10] = "최저입찰가";
//					extractArr[i][11] = "최저입찰가율";
//					extractArr[i][12] = "평균감정가";
//					extractArr[i][13] = "다주소여부";
//					extractArr[0][14] = "지목";
//					extractArr[0][15] = "면적";
//					extractArr[0][16] = "국계법에 따른 지역지구";
//					extractArr[0][17] = "기타법에 따른 지역지구";
//					extractArr[0][18] = "「토지이용규제 기본법 시행령」제9조 제4항 각 호에 해당되는 사항";
//					extractArr[0][19] = "등록일자";
//				}
//				Row row = sheet.createRow(i % 2000);
//				for (int j = 0; j < extractArr[i].length; j++) {
//
//					// 엑셀
//					Cell cell = row.createCell(j);
//					cell.setCellStyle(defaultStyle);
//					cell.setCellValue(extractArr[i][j]);
//					sheet.setColumnWidth(j, 3000);
//
//					// CSV
//					if (extractArr[i][j] != null) {
//						str += extractArr[i][j];
//					}
//					// 배열에 끝이 도래하기 전까지 "," 추가
//					if (j < extractArr[i].length - 1) {
//						str += ",";
//					}
//					bw.write(str);
//					bw.flush();
//					str = "";
//				}
//				bw.newLine();
//			}
//			bw.close();
//			list.add(workBook);
//		}
//
//		try {
//			for (int i = 0; i < (index / 2000) + 1; i++) {
//				File xlsxFile = new File("C:/EXCEL_TEST/GongmaeInfoExcel" + i + ".xlsx");
//				FileOutputStream fileOut = new FileOutputStream(xlsxFile);
//				list.get(i).write(fileOut);
//				list.get(i).close();
//			}
//			System.out.println("엑셀 출력 완료");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	} // main 종료

	// 좌표정보 구하는 함수(현재 미사용)
	private static String[] CoordinateApi(String addr) throws ParseException {
		String arr[] = new String[2];
		StringBuilder sb = new StringBuilder("https://api.vworld.kr/req/address");
		sb.append("?service=address");
		sb.append("&request=getCoord");
		sb.append("&format=json");
		sb.append("&crs=epsg:4326");
		sb.append("&key=27C46E25-8379-3926-9197-6D2E31E495DC");
		sb.append("&type=parcel");
		sb.append("&address=" + URLEncoder.encode(addr, StandardCharsets.UTF_8));

		try {
			URL url = new URL(sb.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

			JSONParser jspa = new JSONParser();
			JSONObject jsob = (JSONObject) jspa.parse(reader);
			JSONObject jsrs = (JSONObject) jsob.get("response");
//		    JSONObject jssv = (JSONObject) jsrs.get("service");
//		    System.out.println("좌표 응답시간: " + jssv.get("time"));

			if (jsrs.get("result") != null) {
				JSONObject jsResult = (JSONObject) jsrs.get("result");
				JSONObject jspoitn = (JSONObject) jsResult.get("point");

				arr[0] = (String) jspoitn.get("x");
				arr[1] = (String) jspoitn.get("y");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	private static String getTagValue(String tag, Element ele) {
		NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nodeList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();

	}

	private static String[][] GongmaeParse(StringBuilder sb) {

		int index = 0;
		int addrIndex = 0;
		String addr;
		String arr[][] = null;
		String coordinate[] = new String[2];
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(sb.toString());

			// root tag
			doc.getDocumentElement().normalize();

			// parsing tag
			NodeList nodeList = doc.getElementsByTagName("item");
			System.out.println("파싱할 리스트 수 : " + nodeList.getLength());
			arr = new String[nodeList.getLength()][20];

			for (index = 0; index < nodeList.getLength(); index++) {
				Node nNode = nodeList.item(index);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					arr[index][0] = getTagValue("CLTR_MNMT_NO", element); // 물건관리번호
					arr[index][1] = getTagValue("CTGR_FULL_NM", element); // 용도

					// 주소가 여러개일 경우
					addr = getTagValue("LDNM_ADRS", element);
					addrIndex = addr.indexOf(',');
					if (addrIndex == -1) {
						arr[index][2] = addr;
					} else {
						arr[index][2] = addr.substring(0, addrIndex);
						arr[index][13] = "Y";
					}
					arr[index][2] = arr[index][2]
							.replace("(", "")
							.replace(")", "")
							.replace("토지", "")
							.replace("건물", "")
							.replace("및", "")
							.replace("내제조표", "")
							.replace("지분", "")
							.replace("매각", "")
							.replace("토지", "")
							.replace("제1호", "")
							.replace("제2호", "")
							.trim();
					
					arr[index][3] = getTagValue("PBCT_BEGN_DTM", element).substring(0, 8); // 입찰시작일시
					arr[index][4] = getTagValue("PBCT_CLS_DTM", element).substring(0, 8); // 입찰종료일시
					arr[index][5] = getTagValue("PBCT_CLTR_STAT_NM", element);// 물건상태
					arr[index][6] = getTagValue("USCBD_CNT", element); // 유찰횟수
					arr[index][7] = getTagValue("IQRY_CNT", element); // 조회수
					
					// 물건정보 null 체크
					if(getTagValue("GOODS_NM", element) != null) {
						arr[index][8] = getTagValue("GOODS_NM", element).replaceAll("(\\,|\b|\t|\n|\f|\r|\\\'|\\\")", " ");
					}else{
						arr[index][8] = ""; // 물건상세정보	
					}

					arr[index][9] = getTagValue("BID_MTD_NM", element); // 입찰방식
					arr[index][10] = getTagValue("MIN_BID_PRC", element); // 최저입찰가
					arr[index][11] = getTagValue("FEE_RATE", element).replace("\\(", "").replace("\\)", ""); // 최저입찰가율
					arr[index][12] = getTagValue("APSL_ASES_AVG_AMT", element); // 평균감정가

//					// 좌표정보 출력
//					try {
//						coordinate = CoordinateApi(arr[index][2]);
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					arr[index][14] = coordinate[0]; // 경도
//					arr[index][15] = coordinate[1]; // 위도

				} // if

				if (index % 1000 == 0) {
					System.out.println("현재 파싱 건수: " + index + "/" + nodeList.getLength());
				}
			} // for
			System.out.println("총 파싱 건수: " + (index) + "/" + nodeList.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	private static int EnvParse(StringBuilder sb, String itemNo, String[][] envInfoArr, int envIndex) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(sb.toString());

			// root tag
			doc.getDocumentElement().normalize();

			// parsing tag
			NodeList nodeList = doc.getElementsByTagName("item");
			System.out.println("파싱할 리스트 수 : " + nodeList.getLength());

			if (!(nodeList.getLength() == 0)) {
				for (int index = 0; index < nodeList.getLength(); ++index) {
					Node nNode = nodeList.item(index);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						++envIndex;
						Element element = (Element) nNode;
						envInfoArr[envIndex][0] = itemNo; // 물건번호
						envInfoArr[envIndex][1] = getTagValue("num", element); // 번호
						envInfoArr[envIndex][2] = getTagValue("name", element); // 사업명
						System.out.println("envIndex : " + envIndex);
						System.out.println("name : " + getTagValue("name", element));
						envInfoArr[envIndex][3] = getTagValue("distance", element); // 사업지까지의 거리
					} // if
				} // for
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return envIndex;
	}
	

	public static String[] landUsage(String addr) {
//		String id = "webdriver.chrome.driver";
//		String path = "C:\\Users\\lemon\\eclipse-workspace\\chromedriver_win32\\chromedriver.exe"; // 드라이버 경로

		String[] arr = new String[5];

		// 크롬 드라이버 사용하기 위해 로딩
//		System.setProperty(id, path);

		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		String url = "http://www.eum.go.kr/web/am/amMain.jsp";

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
		try {
			// 실행된 드라이버로 주어진 url 접속시키기
			driver.get(url);
			Thread.sleep(1000);
			// 페이지내에서.요소찾기("addrTxt_back"라는 아이디로); 라는 뜻
			WebElement searchInput = driver.findElement(By.className("addrTxt_back"));

			// 검색창 클릭
			searchInput.click();

			// 검색창에 주소 문자열 전송하기
			searchInput.sendKeys(addr);

			Thread.sleep(2000);
			
			// driver에서 "ico01"라는 id를 가지고 있는 웹 요소 찾기
			WebElement searchBtn;
			
			searchBtn = driver.findElement(By.className("ico01"));
			
			// 검색 버튼 클릭
			searchBtn.click();
			
//			//가끔 일반번지를 검색해도 산번지를 찾는 경우가 있어 이러한 경우 방지를 위해 주소 동일한지 체크
//			if(driver.findElement(By.className("ico01")).getText().trim().equals(addr.trim())){
//				searchBtn = driver.findElement(By.className("ico01"));
//				// 검색 버튼 클릭
//				searchBtn.click();
//			}else {
//				searchBtn = driver.findElement(By.className("ico02"));
//				// 검색 버튼 클릭
//				searchBtn.click();
//			}
			
			Thread.sleep(1000);
			// 데이터 불러오기
			String jimok = driver.findElement(By.id("present_class_val")).getAttribute("value"); // 지목
			String area = driver.findElement(By.id("present_area")).getText(); // 면적
			String loca1 = driver.findElement(By.id("present_mark1")).getText(); // 국계법에 따른 지역지구
			String loca2 = driver.findElement(By.id("present_mark2")).getText(); // 기타법에 따른 지역지구
			String loca3 = driver.findElement(By.id("present_mark3")).getText(); // 「토지이용규제 기본법 시행령」제9조 제4항 각 호에 해당되는 사항

			loca1 = loca1.replace("\n", "");
			loca2 = loca2.replace("\n", "");
			loca3 = loca3.replace("\n", "");

			arr[0] = jimok;
			arr[1] = area;
			arr[2] = loca1;
			arr[3] = loca2;
			arr[4] = loca3;
			System.out.println("결과: " + loca1);
			driver.quit();
		} catch(NoSuchElementException ee){
			ee.printStackTrace();
			arr[0] = "검색불가";
			arr[1] = "검색불가";
			arr[2] = "검색불가";
			arr[3] = "검색불가";
			arr[4] = "검색불가";
			driver.quit();
		} catch(Exception e) {
			e.printStackTrace();
			driver.quit();
		}
		return arr;
	}

	private static String[][] AddCoordinateInfo(String[][] integrateArr) {

		int index = 0;
		String reArr[][] = new String[integrateArr.length][20];
		String coordinate[] = new String[2];

		for (index = 0; index < integrateArr.length; index++) {
			reArr[index][0] = integrateArr[index][0]; // 입찰시작일시
			reArr[index][1] = integrateArr[index][1]; // 입찰시작일시
			reArr[index][2] = integrateArr[index][2]; // 입찰시작일시
			reArr[index][3] = integrateArr[index][3]; // 입찰시작일시
			reArr[index][4] = integrateArr[index][4]; // 입찰시작일시
			reArr[index][5] = integrateArr[index][5]; // 입찰시작일시
			reArr[index][6] = integrateArr[index][6]; // 입찰시작일시
			reArr[index][7] = integrateArr[index][7]; // 입찰시작일시
			reArr[index][8] = integrateArr[index][8]; // 입찰시작일시
			reArr[index][9] = integrateArr[index][9]; // 입찰시작일시
			reArr[index][10] = integrateArr[index][10]; // 입찰시작일시
			reArr[index][11] = integrateArr[index][11]; // 입찰시작일시
			reArr[index][12] = integrateArr[index][12]; // 입찰시작일시
			reArr[index][13] = integrateArr[index][13]; // 입찰시작일시

			// 좌표정보 추가
			try {
				coordinate = CoordinateApi(reArr[index][2]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reArr[index][14] = coordinate[0]; // 경도
			reArr[index][15] = coordinate[1]; // 위도
			if(index%1000 == 0) {
				System.out.println("현재 좌표정보 추가 건수: " + index);
			}
		}
		return reArr;
	}
}
