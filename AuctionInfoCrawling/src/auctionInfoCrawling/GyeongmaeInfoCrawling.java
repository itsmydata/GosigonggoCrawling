package auctionInfoCrawling;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Connection.Response;

public class GyeongmaeInfoCrawling {
	
	public String[][] CourtAuctionCrawling() throws IOException {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

    Calendar cal = Calendar.getInstance();
    String strToday = sdf.format(cal.getTime());
    cal.add(Calendar.DATE, 90);
    String strNext90days = sdf.format(cal.getTime());
	int i = 0;
	int k = 0;
	int index = 0;

	Response response = Jsoup.connect("https://www.courtauction.go.kr/RetrieveRealEstMulDetailList.laf")
			.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
			.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
			.header("Cache-Control", "max-age=0")
			.header("Connection", "keep-alive")
			.header("Content-Type", "application/x-www-form-urlencoded")
			.cookie("315", "Y")
			.cookie("daepyoSidoCd", "")
			.cookie("daepyoSiguCd", "")
			.cookie("rd1Cd", "")
			.cookie("rd2Cd", "")
			.cookie("realVowel", "35207_45207")
			.cookie("327", "Y")
			.cookie("realJiwonNm", "%C0%CE%C3%B5%C1%F6%B9%E6%B9%FD%BF%F8")
			.cookie("343", "Y")
			.cookie("WMONID", "g_lmjg0vGX9")
			.cookie("page", "default40")
			.cookie("JSESSIONID", "4H9Kc34Wzo1AVvKUiyrARpZAHBxI6mjbxpy12vuM8d3HPdafxNaASqoqN29btSpI.amV1c19kb21haW4vYWlzMQ")
			.header("Origin", "https://www.courtauction.go.kr")
			.header("Referer", "https://www.courtauction.go.kr/RetrieveRealEstMulDetailList.laf")
			.header("Sec-Fetch-Dest", "frame")
			.header("Sec-Fetch-Mode", "navigate")
			.header("Sec-Fetch-Site", "same-origin")
			.header("Sec-Fetch-User", "?1")
			.header("Upgrade-Insecure-Requests", "1")
			.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
			.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
			.header("sec-ch-ua-mobile", "?0")
			.header("sec-ch-ua-platform", "\"Windows\"")
			.requestBody("page=default40"
					+ "&page=default40"
					+ "&bubwLocGubun=1"
					+ "&jiwonNm=%C0%FC%C3%BC"
					+ "jpDeptCd=000000"
					+ "&daepyoSidoCd="
					+ "&daepyoSiguCd="
					+ "&daepyoDongCd="
					+ "&notifyLoc=on"
					+ "&rd1Cd="
					+ "&rd2Cd="
					+ "&realVowel=35207_45207"
					+ "&rd3Rd4Cd="
					+ "&notifyRealRoad=on"
					+ "&saSer="
					+ "&ipchalGbncd=000331"
//					+ "&notifyMinMgakPrcMin=1000000"
//					+ "&notifyMinMgakPrcMax=200000000"
					+ "&termStartDt="+strToday
					+ "&termEndDt="+strNext90days
					+ "&lclsUtilCd=0000801"
					+ "&mclsUtilCd=000080101"
					+ "&sclsUtilCd="
					+ "&gamEvalAmtGuganMin="
					+ "&gamEvalAmtGuganMax="
					+ "&notifyMinMgakPrcMin="
					+ "&notifyMinMgakPrcMax="
					+ "&areaGuganMin="
					+ "&areaGuganMax="
					+ "&yuchalCntGuganMin="
					+ "&yuchalCntGuganMax="
					+ "&notifyMinMgakPrcRateMin="
					+ "&notifyMinMgakPrcRateMax="
					+ "&srchJogKindcd="
					+ "&mvRealGbncd=00031R"
					+ "&srnID=PNO102001"
					+ "&_NAVI_CMD="
					+ "&_NAVI_SRNID="
					+ "&_SRCH_SRNID=PNO102001"
					+ "&_CUR_CMD=InitMulSrch.laf"
					+ "&_CUR_SRNID=PNO102001"
					+ "&_NEXT_CMD="
					+ "&_NEXT_SRNID=PNO102002"
					+ "&_PRE_SRNID="
					+ "&_LOGOUT_CHK="
					+ "&_FORM_YN=Y"
					+ "&pageSpec=default40"
					+ "&pageSpec=default40"
					+ "&targetRow=1"
					+ "&lafjOrderBy=")
			.method(org.jsoup.Connection.Method.POST)
			.ignoreContentType(true)
			.execute();
	org.jsoup.nodes.Document document = response.parse();
	String totalCountStr = document.select("#search_title > ul > li.txtblue").text();
	// 중복건수가 있어서 2배 정도로 넉넉하게 설정
	int totalCount = Integer.valueOf(totalCountStr.substring(9, totalCountStr.length()-2))*2;
	
	// 크롤링 데이터 배열 선언부
	String [][] courtInfos = new String[totalCount][];
	String [][] itemInfos = new String[totalCount][];
	String [][] locaInfos = new String[totalCount][];
	String [][] bigoInfos = new String[totalCount][];
	String [] gamjeongAmount = new String[totalCount];
	String [][] hyeonjaeAmounts = new String[totalCount][];
	String [][] startDateInfos = new String[totalCount][];
	String [] yuchalInfo = new String[totalCount];
	String [][] arr = new String[totalCount][14];
	
	for(index = 0;index<totalCount;index++) {
		
//		System.out.println("--------------------------------");
//		System.out.println("index: " + index);
//		System.out.println("--------------------------------");
		
		// 대법원 경매정보
	    response = Jsoup.connect("https://www.courtauction.go.kr/RetrieveRealEstMulDetailList.laf")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("315", "Y")
				.cookie("daepyoSidoCd", "")
				.cookie("daepyoSiguCd", "")
				.cookie("rd1Cd", "")
				.cookie("rd2Cd", "")
				.cookie("realVowel", "35207_45207")
				.cookie("327", "Y")
				.cookie("realJiwonNm", "%C0%CE%C3%B5%C1%F6%B9%E6%B9%FD%BF%F8")
				.cookie("343", "Y")
				.cookie("WMONID", "g_lmjg0vGX9")
				.cookie("page", "default40")
				.cookie("JSESSIONID", "4H9Kc34Wzo1AVvKUiyrARpZAHBxI6mjbxpy12vuM8d3HPdafxNaASqoqN29btSpI.amV1c19kb21haW4vYWlzMQ")
				.header("Origin", "https://www.courtauction.go.kr")
				.header("Referer", "https://www.courtauction.go.kr/RetrieveRealEstMulDetailList.laf")
				.header("Sec-Fetch-Dest", "frame")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("page=default40"
						+ "&page=default40"
						+ "&bubwLocGubun=1"
						+ "&jiwonNm=%C0%FC%C3%BC"
						+ "jpDeptCd=000000"
						+ "&daepyoSidoCd="
						+ "&daepyoSiguCd="
						+ "&daepyoDongCd="
						+ "&notifyLoc=on"
						+ "&rd1Cd="
						+ "&rd2Cd="
						+ "&realVowel=35207_45207"
						+ "&rd3Rd4Cd="
						+ "&notifyRealRoad=on"
						+ "&saSer="
						+ "&ipchalGbncd=000331"
//						+ "&notifyMinMgakPrcMin=1000000"
//						+ "&notifyMinMgakPrcMax=200000000"
						+ "&termStartDt="+strToday
						+ "&termEndDt="+strNext90days
						+ "&lclsUtilCd=0000801"
						+ "&mclsUtilCd=000080101"
						+ "&sclsUtilCd="
						+ "&gamEvalAmtGuganMin="
						+ "&gamEvalAmtGuganMax="
						+ "&notifyMinMgakPrcMin="
						+ "&notifyMinMgakPrcMax="
						+ "&areaGuganMin="
						+ "&areaGuganMax="
						+ "&yuchalCntGuganMin="
						+ "&yuchalCntGuganMax="
						+ "&notifyMinMgakPrcRateMin="
						+ "&notifyMinMgakPrcRateMax="
						+ "&srchJogKindcd="
						+ "&mvRealGbncd=00031R"
						+ "&srnID=PNO102001"
						+ "&_NAVI_CMD="
						+ "&_NAVI_SRNID="
						+ "&_SRCH_SRNID=PNO102001"
						+ "&_CUR_CMD=InitMulSrch.laf"
						+ "&_CUR_SRNID=PNO102001"
						+ "&_NEXT_CMD="
						+ "&_NEXT_SRNID=PNO102002"
						+ "&_PRE_SRNID="
						+ "&_LOGOUT_CHK="
						+ "&_FORM_YN=Y"
						+ "&pageSpec=default40"
						+ "&pageSpec=default40"
						+ "&targetRow="+(1+(40*index))
						+ "&lafjOrderBy=")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
	    
	    	document = response.parse();
	    	while(true) {
	    		String courtInfo[] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(2)").text().split(" ");
		    	// 데이터가 없으면 break
	    		if(courtInfo.length == 1) {
		    		break;
		    	}
	    		String itemInfo[] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(3)").text().split(" ");

	    		// 물건용도가 "토지", "단독주택"인 것만 추출
	    		// 단독주택의 경우 토지의 정보만 가져옴
	    		if(itemInfo[1].equals("단독주택")||itemInfo[1].equals("대지")||itemInfo[1].equals("전답")||itemInfo[1].equals("임야")||itemInfo[1].equals("기타")) {

		    		String locaInfo[] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(4) > div").text().split("\\[|\\]");
			    	String bigoInfo[] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(5) > div").text().split("\\[|\\]");
			    	String hyeonjaeAmount[] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td.txtright > div.tbl_btm_line").text().split("\\[|\\]");
			    	String startDateInfo[] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(7) > div.tbl_btm_noline").text().split("\\ |\\[|\\]");
			    	gamjeongAmount[i] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td.txtright > div.tbl_btm_noline").text();
			    	yuchalInfo[i] = document.select("#contents > div.table_contents > form:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(7) > div.tbl_btm_line").text();
			    	
			    	//배열 초기화
		    		courtInfos[i] = new String[courtInfo.length];
		    		itemInfos[i] = new String[itemInfo.length];
		    		locaInfos[i] = new String[locaInfo.length];
		    		bigoInfos[i] = new String[bigoInfo.length];
		    		hyeonjaeAmounts[i] = new String[hyeonjaeAmount.length];
		    		startDateInfos[i] = new String[startDateInfo.length];
		    		
			    	for(int j = 0; j<courtInfo.length; j++) {
			    		courtInfos[i][j] = courtInfo[j];
			    	}
			    	for(int j = 0; j<itemInfo.length; j++) {
			    		itemInfos[i][j] = itemInfo[j];
			    	}
			    	for(int j = 0; j<locaInfo.length; j++) {
			    		locaInfos[i][j] = locaInfo[j];
			    	}
			    	for(int j = 0; j<bigoInfo.length; j++) {
			    		bigoInfos[i][j] = bigoInfo[j];
			    	}
			    	for(int j = 0; j<hyeonjaeAmount.length; j++) {
			    		hyeonjaeAmounts[i][j] = hyeonjaeAmount[j];
			    	}
			    	for(int j = 0; j<startDateInfo.length; j++) {
			    		startDateInfos[i][j] = startDateInfo[j];
			    	}
			    	
			    	//리턴할 최종 배열에 넣어줌
			    	arr[k][0] = courtInfos[i][0]+" "+courtInfos[i][1]+"("+itemInfos[i][0]+")"; //사건번호
			    	arr[k][1] = itemInfos[i][1];							//용도
			    	arr[k][2] = locaInfos[i][0];							//물건소재지
			    	arr[k][3] = startDateInfos[i][1].replaceAll("\\.", "");						//입찰시작일시
			    	arr[k][4] = startDateInfos[i][1].replaceAll("\\.", "");						//입찰종료일시
			    	arr[k][5] = "-";										//물건상태
			    	arr[k][6] = yuchalInfo[i];								//유찰횟수
			    	arr[k][7] = "-";										//조회수
			    	arr[k][8] = locaInfos[i][1];							//물건상세정보
			    	arr[k][9] = "-";										//입찰방식
			    	arr[k][10] = hyeonjaeAmounts[i][0].split(" ")[0].replaceAll("\\,", "").trim();		//최저입찰가
			    	arr[k][11] = hyeonjaeAmounts[i][0].split(" ")[1];		//최저입찰가율
			    	arr[k][12] = gamjeongAmount[i].replaceAll("\\,", "").trim();							//감정가
			    	// 물건이 여러개인 경우 대표필지 하나만 가져오고 다주소여부에 Y를 표시함
			    	if(locaInfo.length <= 2) {
				    	arr[k][13] = "-";										//다주소여부
			    	}else {
				    	arr[k][13] = "Y";										//다주소여부
			    	}
			    	
					// 물건용도가 토지일때만 k++ 시킴
					k++;

					if (k % 1000 == 0) {
						System.out.println("현재 경매정보 크롤링 건수: " + k);
					}
		    	}
				i++;
	    	}
	    	// 불러올 데이터가 없으면 for 종료
	    	if(i==0) {
	    		break;
	    	}
	    	i=0;
    	}
		String[][] reArr = new String[k][14];
		
		for (int i1 = 0; i1 < k; i1++) {
			for (int i2 = 0; i2 < 14; i2++) {
				reArr[i1][i2] = arr[i1][i2];
			}
		}

	return reArr;
	}
}
