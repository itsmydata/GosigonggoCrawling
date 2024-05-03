package gosigonggoCrawling;

import java.io.IOException;
import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SilsyGosiCrawling {
	public String [][] GyeonggiCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 크롤링 시작
		System.out.println("경기도청 크롤링 시작");
		try {
		url = "https://www.gg.go.kr/bbs/board.do?bsIdx=469&menuId=1547#page=1#keyfield=SUBJECT#keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D";
		driver.get(url);
			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(driver.findElement(By.cssSelector("#boardList > tbody > tr:nth-child("+i+") > td.subject.text-left.N.N.N.N > a")).getText().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경기도";
				arr[index][1] = driver.findElement(By.cssSelector("#boardList > tbody > tr:nth-child("+i+") > td.subject.text-left.N.N.N.N > a")).getText(); // 제목
				arr[index][2] = driver.findElement(By.cssSelector("#boardList > tbody > tr:nth-child("+i+") > td:nth-child(4)")).getText(); // 부서
				arr[index][3] = driver.findElement(By.cssSelector("#boardList > tbody > tr:nth-child("+i+") > td:nth-child(5)")).getText().replaceAll("(\\/|\\.|\\-)", "").trim(); // 등록일자
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		// 가평군
		System.out.println("가평군 크롤링 시작");
		try {
			
		url="https://www.gp.go.kr/portal/selectGosiList.do?key=2148&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&x=16&y=7";

		driver.get(url);

		for(int i = 1;i<=10;i++)
		{
			// 데이터 있는지 체크
			if (driver.findElement(By.cssSelector("#board > table > tbody > tr:nth-child(" + i + ") > td.subject > a"))
					.getText().equals("")) {
				break;
			}
			// 기본정보 불러오기
			arr[index][0] = "가평군";
			arr[index][1] = driver
					.findElement(By.cssSelector("#board > table > tbody > tr:nth-child(" + i + ") > td.subject > a"))
					.getText(); // 제목
			arr[index][2] = driver
					.findElement(By.cssSelector("#board > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5)"))
					.getText(); // 부서
			arr[index][3] = driver
					.findElement(By.cssSelector("#board > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3)"))
					.getText().replaceAll("(\\/|\\.|\\-)", "").trim(); // 등록일자
			arr[index][4] = driver
					.findElement(By.cssSelector("#board > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2)"))
					.getText(); // 고시번호
			index++;
		}
		}catch(
		Exception e)
		{
				e.printStackTrace();
			}

		// 고양시
		System.out.println("고양시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.goyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.1410285414.1689510292")
					.cookie("SESSION_NTIS", "kg9VTMzg1YT7DdjZ8x7fQrPz1ymJyw27XcS8VHtwvvyHLRCScrT1!-431968878!1762263378!13003!-1")
					.cookie("_gid", "GA1.3.1075413966.1692450278")
					.cookie("_gat", "1")
					.cookie("_ga_FNSBYYTP80", "GS1.3.1692450278.4.1.1692450292.0.0.0")
					.header("Origin", "https://eminwon.goyang.go.kr")
					.header("Referer", "https://eminwon.goyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&not_ancmt_reg_no=&yyyy=&epcCheck=Y&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("body > form > div.table-responsible > div > table > tbody > tr:nth-child(" + i
						+ ") > td.text-left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "고양시";
				arr[index][1] = document.select("body > form > div.table-responsible > div > table > tbody > tr:nth-child("
						+ i + ") > td.text-left > a").text();
				arr[index][2] = document.select("body > form > div.table-responsible > div > table > tbody > tr:nth-child("
						+ i + ") > td:nth-child(4)").text();
				arr[index][3] = document.select("body > form > div.table-responsible > div > table > tbody > tr:nth-child("
						+ i + ") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 과천시
		System.out.println("과천시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.gccity.go.kr/portal/saeol/gosi/list.do?mId=0301040000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("ACEUCI", "1")
					.cookie("WMONID", "ZyVlxxFLIzp")
					.cookie("RSN_JSESSIONID", "aaaUC9gN27tpg5bPsMJNyDxmig3SuPC9-SqvGWlWdc_geGVip61BUaqpMcDq")
					.cookie("ACEFCID", "UID-64E0BEB1FFE3C0EEA0892773")
					.cookie("D_VISITOR_ID", "0546f79d-94e6-4b5a-307b-bb7bd56b81fe")
					.cookie("AUFAI1A38280254709", "1689433200000000000|3|1692450482574733160|1|1689511754928533160")
					.cookie("ACEUACS", "1689511754928533160")
					.cookie("ASAI1A38280254709", "1692450481724327043%7C1692450493844821931%7C1692450481724327043%7C0%7Chttpscafenavercomca-fecafes30698943articles789menuidboardtypeLpage1specialmenutypeuserDisplay15oldPath2FArticleReadnhn3Fclubid3D3069894326menuid3D26boardtype3DL26page3D126specialmenutype3D26userDisplay3D1526articleid3D789")
					.cookie("AUAI1A38280254709", "1692450481724327043%7C3%7C1692450482574733160%7C1%7C1689511754928533160%7C1")
					.cookie("ARAI1A38280254709", "httpswwwgccitygokrportalsaeolgosilistdomId0301040000httpswwwgccitygokrportalsaeolgosilistdomId0301040000")
					.header("Origin", "https://www.gccity.go.kr")
					.header("Referer", "https://www.gccity.go.kr/portal/saeol/gosi/list.do?mId=0301040000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list > table > tbody > tr:nth-child(" + 1 + ") > td.taL.list_tit > a").text()
						.equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "과천시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child(" + 1 + ") > td.taL.list_tit > a")
						.text();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child(" + 1 + ") > td.list_dept").text();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child(" + 1 + ") > td.list_date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 광명시
		System.out.println("광명시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.gm.go.kr/pt/user/nftcBbs/BD_selectNftcBbsList.do?q_nftcBbsCode=1001&q_nftcBbsMgtno=&q_searchKeyTy=1001&q_searchVal=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&q_rowPerPage=&q_currPage=1&q_sortName=&q_sortOrder=")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("anlzUserId", "172.27.0.23|1689512182372")
					.cookie("anlzFirstVisitYn", "N")
					.cookie("anlzFirstVisitHourYn", "N")
					.cookie("anlzFirstVisitDateYn", "N")
					.cookie("anlzFirstVisitWeekYn", "N")
					.cookie("anlzFirstVisitMonthYn", "N")
					.cookie("jipSHk5B5PmXG3zPKs+VXOcH.pt11", "3")
					.cookie("anlzLastVisitDt", "1692450563552")
					.cookie("_ga", "GA1.1.1675496954.1689512182")
					.cookie("JSESSIONID", "jipSHk5B5PmXG3zPKs+VXOcH.pt11")
					.cookie("_ga_SRVNMB3FT7", "GS1.1.1692450550.2.1.1692450563.0.0.0")
					.header("Referer", "https://www.gm.go.kr/pt/user/nftcBbs/BD_selectNftcBbsList.do?q_nftcBbsCode=1001&q_nftcBbsMgtno=&q_searchKeyTy=&q_searchVal=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&q_rowPerPage=&q_currPage=1&q_sortName=&q_sortOrder=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#c-contents > div.sub_content_cont_rt_cont > table > tbody > tr:nth-child(" + i
						+ ") > td.td_lf.td_point").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "광명시";
				arr[index][1] = document.select("#c-contents > div.sub_content_cont_rt_cont > table > tbody > tr:nth-child("
						+ i + ") > td.td_lf.td_point").text();
				arr[index][2] = document.select("#c-contents > div.sub_content_cont_rt_cont > table > tbody > tr:nth-child("
						+ i + ") > td:nth-child(4)").text();
				arr[index][3] = document.select("#c-contents > div.sub_content_cont_rt_cont > table > tbody > tr:nth-child("
						+ i + ") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 경기광주시
		System.out.println("광주시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.gjcity.go.kr/portal/saeol/gosi/list.do?mId=0202010000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "z2QeD5D6NQS")
					.cookie("JSESSIONID", "BADA732E4D18E5BF2BB9CE66C4082D29.web_tomcat12")
					.header("Origin", "https://www.gjcity.go.kr")
					.header("Referer", "https://www.gjcity.go.kr/portal/saeol/gosi/list.do?mId=0202010000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경기광주시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.taL.list_tit > a")
						.text();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.list_dept").text();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.list_date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 구리시
		System.out.println("구리시 크롤링 시작");
		url="https://www.guri.go.kr/www/selectGosiNttList.do?key=387&searchGosiSe=01%2C04%2C06&searchDeptNm=&searchCnd=NTT_SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D";
		try {
			
		driver.get(url);

		for(
		int i = 1;i<=10;i++)
		{
			// 데이터 있는지 체크
			if (driver.findElement(
					By.cssSelector("#bbsNttForm > fieldset > div.table-responsive > table > tbody > tr:nth-child(" + i
							+ ") > td.p-subject > span.tds > a"))
					.getText().equals("")) {
				break;
			}
			// 기본정보 불러오기
			arr[index][0] = "구리시";
			arr[index][1] = driver.findElement(
					By.cssSelector("#bbsNttForm > fieldset > div.table-responsive > table > tbody > tr:nth-child(" + i
							+ ") > td.p-subject > span.tds > a"))
					.getText();
			arr[index][2] = driver.findElement(
					By.cssSelector("#bbsNttForm > fieldset > div.table-responsive > table > tbody > tr:nth-child(" + i
							+ ") > td:nth-child(3) > span.tds"))
					.getText();
			arr[index][3] = driver.findElement(
					By.cssSelector("#bbsNttForm > fieldset > div.table-responsive > table > tbody > tr:nth-child(" + i
							+ ") > td:nth-child(5) > span.tds"))
					.getText().replaceAll("(\\/|\\.|\\-)", "").trim();
			arr[index][4] = "";
			index++;
		}}catch(
		Exception e)
		{
			e.printStackTrace();
		}
		// 군포시
				System.out.println("군포시 크롤링 시작");
				try
				{
					response = Jsoup.connect("http://www.gunpo.go.kr/www/selectEminwonNoticeList.do?notAncmtSeCd=01&key=3907&searchCnd=all&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
							.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
							.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
							.header("Connection", "keep-alive")
							.cookie("_ga_WPM2XNH5WL", "GS1.1.1692452345.1.0.1692452345.0.0.0")
							.cookie("_ga", "GA1.1.1011055831.1692452346")
							.header("Referer", "http://www.gunpo.go.kr/www/selectEminwonNoticeList.do?key=3907&Not_ancmt_se_code=01&list_gubun=N&ofr_pageSize=10&notAncmtSeCd=01")
							.header("Upgrade-Insecure-Requests", "1")
							.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
							.method(org.jsoup.Connection.Method.GET)
							.ignoreContentType(true)
							.execute();
					
					document = response.parse();

					for (int i = 1; i <= 10; i++) {
						// 데이터 있는지 체크
						if (document.select("#contents > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
							break;
						}
						// 기본정보 불러오기
						arr[index][0] = "군포시";
						arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text();
						arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
						arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
						arr[index][4] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
						index++;
					}
				}catch(
				Exception e)
				{
					e.printStackTrace();
				}

		// 김포시
		System.out.println("김포시 크롤링 시작");
		try
		{
			response = Jsoup.connect("https://www.gimpo.go.kr/portal/ntfcPblancList.do?key=1004")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("wcCookie", "125.179.129.136_T_87796_WC")
					.cookie("WMONID", "Rnhpe3ULz2S")
					.cookie("JSESSIONID", "Y13bxKGoplf6znRR16ewUQedoCRdcBqDJFO5ML1SDpmLK1TdxVBrR9AnIWKU8iB6.new-gimpo-was2_servlet_engine1")
					.header("Origin", "https://www.gimpo.go.kr")
					.header("Referer", "https://www.gimpo.go.kr/portal/ntfcPblancList.do?key=1004&cate_cd=1&searchCnd=40900000000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("cate_cd=1&searchCnd=not_ancmt_sj&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3) > a")
						.text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "김포시";
				arr[index][1] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3) > a").text();
				arr[index][2] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4)").text();
				arr[index][3] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5)").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		//남양주시
		System.out.println("남양주시 크롤링 시작");
		try {
			
		url="https://www.nyj.go.kr/main/185?space=main_board_gonggo&search_field=Ti&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D";

		driver.get(url);

		for(
		int i = 1;i<=10;i++)
		{
			// 데이터 있는지 체크
			if (driver.findElement(By.cssSelector(
					"#contents > article > div > div > div.list > div > table > tbody > tr:nth-child("+i+") > td.txtleft"))
					.getText().equals("")) {
				break;
			}
			// 기본정보 불러오기
			arr[index][0] = "남양주시";
			arr[index][1] = driver.findElement(By.cssSelector(
					"#contents > article > div > div > div.list > div > table > tbody > tr:nth-child("+i+") > td.txtleft"))
					.getText();
			arr[index][2] = driver.findElement(By.cssSelector(
					"#contents > article > div > div > div.list > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3)"))
					.getText();
			arr[index][3] = driver.findElement(By.cssSelector(
					"#contents > article > div > div > div.list > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)"))
					.getText().replaceAll("(\\/|\\.|\\-)", "").trim();
			arr[index][4] = "";
			index++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		//////////// 동두천시////////////////////
		System.out.println("동두천시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.ddc.go.kr/ddc/selectGosiList.do?key=107&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "52E361EBCACA39E0743508EB3267BA71")
					.header("Referer", "https://www.ddc.go.kr/ddc/selectGosiList.do?key=107&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#contents > table > tbody > tr:nth-child(" + i + ") > td.subject > a").text()
						.equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "동두천시";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child(" + i + ") > td.subject > a")
						.text();
				arr[index][2] = document.select("#contents > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4)")
						.text();
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5)")
						.text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		//////////// 부천시////////////////////

		System.out.println("부천시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.bucheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.1.239618990.1689686008")
					.cookie("ccsession", "202308192304096fc8a8c05f07ffff8287")
					.cookie("SESSION_NTIS", "kgLSs8W641Jyvxgnjhh6Kt7l3FPL6Wxv65QfxY039pxpTNskD09k!1934666155!1761935686!13002!-1")
					.cookie("_ga_PYW75DBHQ7", "GS1.1.1692453842.4.0.1692453842.0.0.0")
					.cookie("JESS6", "X6TxE5D6GN1AGzNxRaerZ1CaFEX2jcKCJClCwePhyNlC373uxa4HiwjJB5clAlCt.p2_was1_servlet_engine1")
					.header("Origin", "https://eminwon.bucheon.go.kr")
					.header("Referer", "https://eminwon.bucheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%2F%EA%B3%B5%EA%B3%A0%2F%EC%9E%85%EB%B2%95%EC%98%88%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&epcCheck=Y&yyyy=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("body > form > div.default_board > table > tbody > tr:nth-child("+i+") > td.subject > a")
						.text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "부천시";
				arr[index][1] = document
						.select("body > form > div.default_board > table > tbody > tr:nth-child("+i+") > td.subject > a")
						.text();
				arr[index][2] = document
						.select("body > form > div.default_board > table > tbody > tr:nth-child("+i+") > td:nth-child(4)")
						.text();
				arr[index][3] = document
						.select("body > form > div.default_board > table > tbody > tr:nth-child("+i+") > td:nth-child(5)")
						.text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		//////////// 성남시////////////////////
		System.out.println("성남시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.seongnam.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("RC_COLOR", "24")
					.cookie("PCID", "16924539320859318211562")
					.cookie("RC_RESOLUTION", "1368*912")
					.cookie("SESSION_NTIS", "kgMvqsqH54SQYKVRg3pd0218b9mJHXxQvTn7Z5ZTTR6plPqhsKRB!512650692!1761739078!13003!-1")
					.cookie("_ga_8VX371YTYQ", "GS1.1.1692453932.2.0.1692453932.0.0.0")
					.cookie("_ga", "GA1.3.1440632061.1689686214")
					.cookie("_gid", "GA1.3.1893507394.1692453933")
					.header("Origin", "https://eminwon.seongnam.go.kr")
					.header("Referer", "https://eminwon.seongnam.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&cgg_code=&not_ancmt_cn=&dept_nm=&epcCheck=Y&yyyy=&nodate_recent_mm=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#listForm > div > div > table > tbody > tr:nth-child(" + i + ") > td.title > a").text()
						.equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "성남시";
				arr[index][1] = document
						.select("#listForm > div > div > table > tbody > tr:nth-child(" + i + ") > td.title > a").text();
				arr[index][2] = document
						.select("#listForm > div > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5)").text();
				arr[index][3] = document
						.select("#listForm > div > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3)").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		//////////// 수원시////////////////////

		System.out.println("수원시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.suwon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga_MQZC517774", "GS1.3.1687621235.2.0.1687621235.0.0.0")
					.cookie("_ga", "GA1.1.848427629.1687582829")
					.cookie("SWSESSID", "SO1yozchAUWOy6W7ZiE5qC4i7sOKyxajJwaSSJbnuy5MtlV2xII3SuaBCaFh8H6A.WAS_servlet_engine1")
					.cookie("SESSION_NTIS", "kgMVGw8hN2245rJj85x1svy2JMxJ4f8GFlFZ9pc1bL10BcDsptWv!1616101582!1761673768!13002!-1")
					.cookie("_ga_LYP27H7VGF", "GS1.1.1692454038.5.0.1692454038.0.0.0")
					.header("Origin", "https://eminwon.suwon.go.kr")
					.header("Referer", "https://eminwon.suwon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&epcCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&cgg_code=&chk_call=N&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			
			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("body > form > div > div > table > tbody > tr:nth-child(" + i + ") > td.p-subject > a")
						.text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "수원시";
				arr[index][1] = document
						.select("body > form > div > div > table > tbody > tr:nth-child(" + i + ") > td.p-subject > a")
						.text();
				arr[index][2] = document
						.select("body > form > div > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4)")
						.text();
				arr[index][3] = document
						.select("body > form > div > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5)")
						.text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		//////////// 시흥시////////////////////
		System.out.println("시흥시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.siheung.go.kr/main/saeol/gosi/list.do?mId=0401040100")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "-PfrHGlk0yE")
					.cookie("JSESSIONID", "baaKYIOT3h3T04ba7khOyJgKdrOAlD240qgqcKMfRBgwWzjwyGHPAfzYNl9T")
					.cookie("D_VISITOR_ID", "8023ca5d-ef19-40a6-5aa8-cdbce66bb5f6")
					.cookie("wcs_bt", "80b6f2fa317ca8:1692454283")
					.header("Origin", "https://www.siheung.go.kr")
					.header("Referer", "https://www.siheung.go.kr/main/saeol/gosi/list.do?mId=0401040100")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();
			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.taL.tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "시흥시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.taL.tit > a").text();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.dept").text();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2)").text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 안산시////////////////////

		System.out.println("안산시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.iansan.net/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Origin", "https://eminwon.iansan.net")
					.header("Referer", "https://eminwon.iansan.net/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=2020&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&key=%24%7Bbefore_key%7D&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.taL.tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "안산시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.taL.tit > a").text();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.dept").text();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td.date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2)").text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 안성시////////////////////
		System.out.println("안성시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.anseong.go.kr/portal/saeol/gosiList.do?mId=0401040000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "GT45TXw5I6q")
					.cookie("RSN_JSESSIONID", "baa8AvWpg5tV-gN4XBcOyeMLhjCrns_bG_YrPF1_Tn5rhKcvIMY-GaLBcvME")
					.header("Origin", "https://www.anseong.go.kr")
					.header("Referer", "https://www.anseong.go.kr/portal/saeol/gosiList.do?mId=0401040000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=NOT_ANCMT_SJ&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document
						.select("#listForm > div.gosi_wrap > table > tbody > tr:nth-child(" + i + ") > td.taL.gosi_tit > a")
						.text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "안성시";
				arr[index][1] = document
						.select("#listForm > div.gosi_wrap > table > tbody > tr:nth-child(" + i + ") > td.taL.gosi_tit > a")
						.text();
				arr[index][2] = document
						.select("#listForm > div.gosi_wrap > table > tbody > tr:nth-child(" + i + ") > td.gosi_dept")
						.text();
				arr[index][3] = document
						.select("#listForm > div.gosi_wrap > table > tbody > tr:nth-child(" + i + ") > td.gosi_date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document
						.select("#listForm > div.gosi_wrap > table > tbody > tr:nth-child(" + i + ") > td.gosi_num").text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 안양시////////////////////

		System.out.println("안양시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.anyang.go.kr/main/emwsWebList.do?key=4101&searchGosiSe=01%2C03%2C04&pageUnit=10&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("wcCookie", "1cb40cd0c02cc74e9938c7dbb8714d33fd13ae8bf2d670d6348ec02f75e4404b")
					.cookie("_gid", "GA1.3.1709023689.1692378770")
					.cookie("JSESSIONID", "wU7YfduJqjaOAcBRH0xsxdV14vGS6famJWjyi1FE10Qe0mGPxrgfEUJqRv7hipZc.amV1c19kb21haW4vTmVvQ01T")
					.cookie("_gat_gtag_UA_153075621_1", "1")
					.cookie("_ga_WPM2XNH5WL", "GS1.1.1692454475.5.1.1692454492.0.0.0")
					.cookie("_ga_7J38ZPMHG2", "GS1.1.1692454475.5.1.1692454493.0.0.0")
					.cookie("_ga", "GA1.1.1740906434.1689949003")
					.header("Referer", "https://www.anyang.go.kr/main/emwsWebList.do?key=4101&searchGosiSe=01,03,04")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > a")
						.text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "안양시";
				arr[index][1] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > a").text();
				arr[index][2] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3)").text();
				arr[index][3] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4)").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(1)").text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 양주시
		System.out.println("양주시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.yangju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "kgT6vk1lqpGGJBBYxj13n5cp9qdGd4L1N2yvr9XhwQtJGNyBQpc6!289935269!1763082519!13003!-1")
					.cookie("_ga_VEJRX5E3FB", "GS1.1.1692454587.5.0.1692454587.0.0.0")
					.cookie("_ga", "GA1.3.465035938.1684505325")
					.cookie("_gid", "GA1.3.1370984659.1692454587")
					.header("Origin", "https://eminwon.yangju.go.kr")
					.header("Referer", "https://eminwon.yangju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list2 > tbody > tr:nth-child(" + i + ") > td:nth-child(3) > a > p").text()
						.equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "양주시";
				arr[index][1] = document.select("#list2 > tbody > tr:nth-child(" + i + ") > td:nth-child(3) > a > p")
						.text();
				arr[index][2] = document.select("#list2 > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > p").text();
				arr[index][3] = document.select("#list2 > tbody > tr:nth-child(" + i + ") > td:nth-child(5) > p").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list2 > tbody > tr:nth-child(" + i + ") > td:nth-child(2)").text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 양평군
		System.out.println("양평군 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.yp21.go.kr/www/selectBbsNttList.do?key=1119&bbsNo=5&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("YP_JSESSIONID", "CC451236A804DC25680C5982D4D0FDF9.ypwas1")
					.header("Referer", "https://www.yp21.go.kr/www/selectBbsNttList.do?bbsNo=5&key=1119")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td.p-subject > a").text()
						.equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "양평군";
				arr[index][1] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td.p-subject > a").text();
				arr[index][2] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3)").text();
				arr[index][3] = document
						.select("#contents > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > time").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 여주시////////////////////

		System.out.println("여주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yeoju.go.kr/www/jsp/project/gosi/list.jsp?thisPage=1&menuIdx=602&bbsIdx=&searchField=1&searchText=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.3.833093444.1689952856")
					.cookie("JSESSIONID", "mXSj83r0j011vJuaSq7xOT7atnGs1CFpJVxlJVPE3mA7mjlPxuiXiIXec9i9nb1S.amV1c19kb21haW4vaG9tZXBhZ2Vfd3d3")
					.cookie("_gid", "GA1.3.1473497073.1692455742")
					.cookie("_gat", "1")
					.cookie("_ga_QY4KYM331N", "GS1.3.1692455742.3.0.1692455742.0.0.0")
					.header("Referer", "https://www.yeoju.go.kr/www/jsp/project/gosi/list.jsp")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#content_box > div.board_wrap_bbs.boardTop_txt > table > tbody > tr:nth-child(" + i
						+ ") > td.txtL > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "여주시";
				arr[index][1] = document
						.select("#content_box > div.board_wrap_bbs.boardTop_txt > table > tbody > tr:nth-child(" + i
								+ ") > td.txtL > a")
						.text();
				arr[index][2] = document
						.select("#content_box > div.board_wrap_bbs.boardTop_txt > table > tbody > tr:nth-child(" + i
								+ ") > td:nth-child(4)")
						.text();
				arr[index][3] = document
						.select("#content_box > div.board_wrap_bbs.boardTop_txt > table > tbody > tr:nth-child(" + i
								+ ") > td:nth-child(5)")
						.text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document
						.select("#content_box > div.board_wrap_bbs.boardTop_txt > table > tbody > tr:nth-child(" + i
								+ ") > td:nth-child(2)")
						.text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 연천군////////////////////

		System.out.println("연천군 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.yeoncheon.go.kr/www/selectGosiList.do?key=3393&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "CQUUo786ci7PdqQTSVQx0SZHvi1UIKpMJMBZuMeIEUzPRmwIxrEsZVQhOXSn96o8.amV1c19kb21haW4vaG9tZVBhZ2U")
					.cookie("_pk_id.yeoncheon", "f5848d28-ce94-9fc5-2810-689953139541.1689953139541.f65d9888-8b79-3787-2787-692455793378.1692455793378.1692455793378.1")
					.header("Referer", "https://www.yeoncheon.go.kr/www/selectGosiList.do?key=3393&not_ancmt_se_code=01&pageIndex=1")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
			
			document = response.parse();
			
			totNum = document.select("#contents > div > div.row > div > em.em_black").text();
			
			// 데이터 있는지 체크
			if(Integer.valueOf(totNum) == 1) {
				
				// 기본정보 불러오기
				arr[index][0] = "연천군";
				arr[index][1] = document.select("#contents > div > table > tbody > tr > td.subject > a").text();
				arr[index][2] = document.select("#contents > div > table > tbody > tr > td:nth-child(5)").text();
				arr[index][3] = document.select("#contents > div > table > tbody > tr > td:nth-child(3)").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > div > table > tbody > tr > td:nth-child(2)").text();
				index++;
				
			}else if(Integer.valueOf(totNum) > 1){
			
				for (int i = 1; i <= 10; i++) {
					// 데이터 있는지 체크
					if (document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "연천군";
					arr[index][1] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td.subject > a").text();
					arr[index][2] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text();
					arr[index][3] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text()
							.replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
					index++;
				}
				
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		// 오산시
		System.out.println("오산시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.osan.go.kr/portal/saeol/gosi/list.do?mId=0302010000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "VpBUnNom2LK")
					.cookie("JSESSIONID", "baapU9HVnPdXg5MPUjaMylig2XitJjB4d8NkKCxsoF7NLptbnz5pU-5waRYu")
					.header("Origin", "https://www.osan.go.kr")
					.header("Referer", "https://www.osan.go.kr/portal/saeol/gosi/list.do?mId=0302010000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&pbsDivision=&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();
			
			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().equals("")) {
					break;
				}
				
				// 기본정보 불러오기
				arr[index][0] = "오산시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_dept").text();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
			
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 용인시////////////////////
		System.out.println("용인시 크롤링 시작");

		try
		{
			response = Jsoup.connect("http://eminwon.yongin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "kgJwzNl2DNNfl91Kzp7byvcGFQBvCmlkyJFzy2Rv80ny7Y16yxxh!1404377783!1762855504!13004!-1")
					.header("Origin", "http://eminwon.yongin.go.kr")
					.header("Referer", "http://eminwon.yongin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(" + i
						+ ") > tbody > tr:nth-child(6) > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "용인시";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(" + i
						+ ") > tbody > tr:nth-child(6) > td:nth-child(3) > p > a").text();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(" + i
						+ ") > tbody > tr:nth-child(6) > td:nth-child(4) > a").text();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(" + i
						+ ") > tbody > tr:nth-child(6) > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(" + i
						+ ") > tbody > tr:nth-child(6) > td:nth-child(2) > p > a").text();
				index++;
			}
		}catch(
		Exception e)
		{
				e.printStackTrace();
			}

			
			///////////의정부시////////////////////
			
			System.out.println("의정부시 크롤링 시작");
			try
			{
			response = Jsoup.connect("https://www.ui4u.go.kr/portal/saeol/gosiList.do?seCode=01&mId=0301040000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("XTVID", "A230722135947468578")
					.cookie("xloc", "1368X912")
					.cookie("_harry_lang", "ko-KR")
					.cookie("JSESSIONID", "zqsTFgt9yaaSyH9lgVJNRzg1ZeonAsjyJm2yeneSWCWIl5lIx2hW719G7Ch4vKjl.amV1c19kb21haW4vc2VydmVyMjA")
					.cookie("_harry_ref", "https%3A//cafe.naver.com/ca-fe/cafes/30698943/articles/789%3Fmenuid%3D%26boardtype%3DL%26page%3D1%26specialmenutype%3D%26userDisplay%3D15%26oldPath%3D%252FArticleRead.nhn%253Fclubid%253D30698943%2526menuid%253D%2526boardtype%253DL%2526page%253D1%2526specialmenutype%253D%2526userDisplay%253D15%2526articleid%253D789")
					.cookie("_harry_url", "https%3A//www.ui4u.go.kr/portal/saeol/gosiList.do%3FseCode%3D01%26mId%3D0301040000")
					.cookie("_harry_fid", "hh-688537172")
					.cookie("_harry_hsid", "A230819235847844842")
					.cookie("_harry_dsid", "A230819235847844500")
					.cookie("XTSID", "A230819235847845344")
					.header("Origin", "https://www.ui4u.go.kr")
					.header("Referer", "https://www.ui4u.go.kr/portal/saeol/gosiList.do?seCode=01&mId=0301040000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=not_ancmt_sj&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
					
			document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.taL > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "의정부시";
					arr[index][1] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.taL > a").text();
					arr[index][2] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.list_division").text();
					arr[index][3] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
			///////////의왕시////////////////////
			
			System.out.println("의왕시 크롤링 시작");
			
			try {
			response = Jsoup.connect("https://eminwon.uiwang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.1142669577.1688885733")
					.cookie("SESSION_NTIS", "kgZrHQtJNyWn5ryyR82Psz2TXQfnrLTBcQLKJJY0D1JPX5jCb7TZ!-1793797053!1762722128!13002!-1")
					.cookie("_gid", "GA1.3.1826020430.1692457260")
					.cookie("_gat", "1")
					.cookie("_ga_XP56S75PP1", "GS1.3.1692457260.6.0.1692457260.0.0.0")
					.header("Origin", "https://eminwon.uiwang.go.kr")
					.header("Referer", "https://eminwon.uiwang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
					
			document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child("+i+") > tbody > tr:nth-child(8) > td:nth-child(3) > p > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "의왕시";
					arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child("+i+") > tbody > tr:nth-child(8) > td:nth-child(3) > p > a").text();
					arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child("+i+") > tbody > tr:nth-child(8) > td:nth-child(4)").text();
					arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child("+i+") > tbody > tr:nth-child(8) > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child("+i+") > tbody > tr:nth-child(8) > td:nth-child(2) > p > a").text();
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			///////////이천시////////////////////
			
			System.out.println("이천시 크롤링 시작");
			try{
			response = Jsoup.connect("https://www.icheon.go.kr/portal/selectNoticeWebList.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "vaqqpdnakCHK56FR9zVRB09H1dr5hU80JEH9k0cLb9pAxCHdxSHj6s06S1NaYhWu.amV1c19kb21haW4vTkVPQ01T")
					.header("Origin", "https://www.icheon.go.kr")
					.header("Referer", "https://www.icheon.go.kr/portal/selectNoticeWebList.do?key=1606&searchNotAncmtSeCd=01")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("key=1606&searchCnd=NOT_ANCMT_SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
					
			document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "이천시";
					arr[index][1] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text();
					arr[index][2] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
					arr[index][3] = document.select("#contents > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = "";
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			///////////파주시////////////////////
			
			System.out.println("파주시 크롤링 시작");

			try{
				response = Jsoup.connect("https://www.paju.go.kr/user/board/BD_board.list.do")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Cache-Control", "max-age=0")
						.header("Connection", "keep-alive")
						.header("Content-Type", "application/x-www-form-urlencoded")
						.cookie("WMONID", "PqF1gYspp4C")
						.cookie("JSESSIONID", "caa8Rq1vrt6JB-TWPQ9Ny0u_N-dLlBCbik5jqw5BusahAu7SBPNuxgkd_u9v")
						.cookie("_pk_id.pajucity", "5828a6fe-0bfd-5957-8212-688885843547.1688885843547.851056de-2f0b-ab13-db94-692457367832.1692457367832.1692457367832.1")
						.header("Origin", "https://www.paju.go.kr")
						.header("Referer", "https://www.paju.go.kr/user/board/BD_board.list.do?bbsCd=1022&q_ctgCd=4063")
						.header("Sec-Fetch-Dest", "document")
						.header("Sec-Fetch-Mode", "navigate")
						.header("Sec-Fetch-Site", "same-origin")
						.header("Sec-Fetch-User", "?1")
						.header("Upgrade-Insecure-Requests", "1")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
						.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
						.header("sec-ch-ua-mobile", "?0")
						.header("sec-ch-ua-platform", "\"Windows\"")
						.requestBody("seq=&bbsCd=1022&q_ctgCd=4063&q_parentCtgCd=&pageType=&showSummaryYn=N&delDesc=&q_searchKeyType=&q_searchVal=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&q_startDt=&q_endDt=&q_currPage=1&q_sortName=&q_sortOrder=&q_rowPerPage=10")
						.method(org.jsoup.Connection.Method.POST)
						.ignoreContentType(true)
						.execute();
					
					
			document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#dataForm > div.table.table-list > table > tbody > tr:nth-child("+i+") > td.cell-subject > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "파주시";
					arr[index][1] = document.select("#dataForm > div.table.table-list > table > tbody > tr:nth-child("+i+") > td.cell-subject > a").text();
					arr[index][2] = document.select("#dataForm > div.table.table-list > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
					arr[index][3] = document.select("#dataForm > div.table.table-list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = "";
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
			///////////포천시////////////////////
			
			System.out.println("포천시 크롤링 시작");

			try{
				response = Jsoup.connect("https://www.pocheon.go.kr/www/selectEminwonList.do?key=12563&notAncmtSeCode=01&searchCnd=notAncmtSj&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Connection", "keep-alive")
						.cookie("JSESSIONID", "uS8OUsoZKIktY437PqHaSEoI70IwN_kTabaiou2v3IetEvFyp2U8U7VzPAtErA4ArvgGsvEu9aTts5tIQS225dcn6UKYgraPdBZDTNbUc0DZkl0f6NV7veoWa8Ij-iea!-1369755998")
						.header("Referer", "https://www.pocheon.go.kr/www/selectEminwonList.do?key=12563&notAncmtSeCode=01")
						.header("Sec-Fetch-Dest", "document")
						.header("Sec-Fetch-Mode", "navigate")
						.header("Sec-Fetch-Site", "same-origin")
						.header("Sec-Fetch-User", "?1")
						.header("Upgrade-Insecure-Requests", "1")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
						.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
						.header("sec-ch-ua-mobile", "?0")
						.header("sec-ch-ua-platform", "\"Windows\"")
						.method(org.jsoup.Connection.Method.GET)
						.ignoreContentType(true)
						.execute();
					
				document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "포천시";
					arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text();
					arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
					arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = "";
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			///////////평택시////////////////////
			
			System.out.println("평택시 크롤링 시작");

			try{
			response = Jsoup.connect("https://www.pyeongtaek.go.kr/pyeongtaek/saeol/gosiList.do?seCode=01&mId=0401020000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "NQvNgOubuXB")
					.cookie("HOME_JSESSIONID", "uNjObawQl5aa4UGtMiWPV4IhBZ1ixdIjJa8yzK1FPoZBMaagxIWEDarJM9vECQmm.amV1c19kb21haW4vaG9tZXBhZ2VfMg")
					.header("Origin", "https://www.pyeongtaek.go.kr")
					.header("Referer", "https://www.pyeongtaek.go.kr/pyeongtaek/saeol/gosiList.do?seCode=01&mId=0401020000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=NOT_ANCMT_SJ&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();					
					
				document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "평택시";
					arr[index][1] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text();
					arr[index][2] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.list_dept").text();
					arr[index][3] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("#listForm > table > tbody > tr:nth-child("+i+") > td.list_notice").text();
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			///////////하남시////////////////////
			
			System.out.println("하남시 크롤링 시작");

			try{
				response = Jsoup.connect("https://www.hanam.go.kr/www/selectGosiList.do?key=171&not_ancmt_se_code=01%2C04&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Connection", "keep-alive")
						.cookie("WMONID", "mZDSqxTeUmz")
						.cookie("JSESSIONID", "C8AD9355604A6AE567DDF8A29CD9EBDF.was1")
						.header("Referer", "https://www.hanam.go.kr/www/selectGosiList.do?key=171&not_ancmt_se_code=01,04")
						.header("Sec-Fetch-Dest", "document")
						.header("Sec-Fetch-Mode", "navigate")
						.header("Sec-Fetch-Site", "same-origin")
						.header("Sec-Fetch-User", "?1")
						.header("Upgrade-Insecure-Requests", "1")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
						.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
						.header("sec-ch-ua-mobile", "?0")
						.header("sec-ch-ua-platform", "\"Windows\"")
						.method(org.jsoup.Connection.Method.GET)
						.ignoreContentType(true)
						.execute();
					
				document = response.parse();

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "하남시";
					arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text();
					arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text();
					arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			//화성시
			System.out.println("화성시 크롤링 시작");

			try{
				response = Jsoup.connect("https://eminwon.hscity.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Cache-Control", "max-age=0")
						.header("Connection", "keep-alive")
						.header("Content-Type", "application/x-www-form-urlencoded")
						.cookie("SESSION_NTIS", "kgh6GgtQH3dTy68c4GZYQn1V3QJkh8MTZGGH1DGM2dq00HHy6qQ9!1295486224!1763180880!13003!-1::XN131B1B13")
						.header("Origin", "https://eminwon.hscity.go.kr")
						.header("Referer", "https://eminwon.hscity.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do?pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&not_ancmt_sj=&not_ancmt_cn=&dept_nm=&cgg_code=&not_ancmt_reg_no=&cha_dep_code_nm=&yyyy=2023&Key=B_Subject&temp=")
						.header("Sec-Fetch-Dest", "iframe")
						.header("Sec-Fetch-Mode", "navigate")
						.header("Sec-Fetch-Site", "same-origin")
						.header("Sec-Fetch-User", "?1")
						.header("Upgrade-Insecure-Requests", "1")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
						.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
						.header("sec-ch-ua-mobile", "?0")
						.header("sec-ch-ua-platform", "\"Windows\"")
						.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&cgg_code=&not_ancmt_reg_no=&dept_nm=&yyyy=2003&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
						.method(org.jsoup.Connection.Method.POST)
						.ignoreContentType(true)
						.execute();
					
					
				document = response.parse();
			

				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(document.select("#contentDiv > form > table.boardDefalut > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "화성시";
					arr[index][1] = document.select("#contentDiv > form > table.boardDefalut > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
					arr[index][2] = document.select("#contentDiv > form > table.boardDefalut > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
					arr[index][3] = document.select("#contentDiv > form > table.boardDefalut > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("#contentDiv > form > table.boardDefalut > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
					index++;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			driver.quit();
			// 크롬드라이버 task kill
			try
			{
				Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
				Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
				Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
				Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
			}
			catch(IOException e){
				System.out.println("Failed to close one or more driver exe files");
			}
		
		return arr;
	}
	
	public String [][] GangwonCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작
		System.out.println("강원도 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.provin.gangwon.kr/portal/bulletin/notification")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("JSESSIONID", "26FF4CB00A4D22EB8CE64CA6005B5B10")
				.header("Origin", "https://www.provin.gangwon.kr")
				.header("Referer", "https://www.provin.gangwon.kr/portal/bulletin/notification")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mode=&firstYN=N&articleSeq=0&searchCondition=TITLE&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content-bx > div:nth-child(3) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "강원도";
				arr[index][1] = document.select("#content-bx > div:nth-child(3) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text();
				arr[index][2] = document.select("#content-bx > div:nth-child(3) > table > tbody > tr:nth-child("+i+") > td.skinTb-name.skinTxa-center").text();
				arr[index][3] = document.select("#content-bx > div:nth-child(3) > table > tbody > tr:nth-child("+i+") > td.skinTb-date.skinTxa-center").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		
		///////////강릉시////////////////////
		
		System.out.println("강릉시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gn.go.kr/www/selectGosiNttList.do;jsessionid=aoI911d5EH1cP7mq5yisQ8ylDZ0nxm2NJ1qnq3IaQeIwj8p6xSJUgay4Vx4AdLvq.GNWEB_servlet_engine3?key=263&searchGosiSe=01%2C04%2C06&searchCnd=ALL&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.3.1193484480.1688892341")
					.cookie("JSESSIONID", "aoI911d5EH1cP7mq5yisQ8ylDZ0nxm2NJ1qnq3IaQeIwj8p6xSJUgay4Vx4AdLvq.GNWEB_servlet_engine3")
					.cookie("_gid", "GA1.3.156943020.1692465606")
					.cookie("_gat_gtag_UA_84093705_9", "1")
					.header("Referer", "https://www.gn.go.kr/www/selectGosiNttList.do?key=263&searchGosiSe=01,04,06")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
					
					
			document = response.parse();
			

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "강릉시";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text();
				arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.last").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////강원고성군////////////////////
		
		System.out.println("강원고성군 크롤링 시작");

		try{
		response = Jsoup.connect("https://eminwon.gwgs.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("SESSION_NTIS", "kg54TKqkXFmTgpn8spDLTZm3hvRJpH5h4JKJ3pTBQwh3MJ6JdFxQ!-1975034458!1779501621!13003!-1")
				.cookie("_ga_GVWCJZ6YK9", "GS1.1.1692465657.3.0.1692465657.0.0.0")
				.cookie("_ga", "GA1.3.134677767.1686499277")
				.cookie("_gid", "GA1.3.845045056.1692465658")
				.cookie("_gat_gtag_UA_131915820_1", "1")
				.header("Origin", "https://eminwon.gwgs.go.kr")
				.header("Referer", "https://eminwon.gwgs.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&recent_mm=&not_ancmt_reg_no=&mode=listForm&boardCode=BDAABB01&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
		//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}		
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > div > div.tb > table > tbody > tr:nth-child("+i+") > td.td_left > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "강원고성군";
				arr[index][1] = document.select("body > form > div > div.tb > table > tbody > tr:nth-child("+i+") > td.td_left > p > a").text();
				arr[index][2] = document.select("body > form > div > div.tb > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > a").text();
				arr[index][3] = document.select("body > form > div > div.tb > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > div > div.tb > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > p > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//동해시
		System.out.println("동해시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.dh.go.kr/www/selectBbsNttList.do?key=478&bbsNo=87&searchCtgry=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "E0AE34C0B9E59B8F1489175EC2321A73")
					.header("Referer", "https://www.dh.go.kr/www/selectBbsNttList.do?bbsNo=87&key=478")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
		document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "동해시";
				arr[index][1] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text();
				arr[index][2] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
				arr[index][3] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////삼척시////////////////////
		
		System.out.println("삼척시 크롤링 시작");

		try{
			

		response = Jsoup.connect("https://www.samcheok.go.kr/media/00084/00095.web?cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "39677BA8ABED31627CB62C6E86C8D84B")
				.cookie("wcs_bt", "b319c8f12cc5e8:1692465777")
				.header("Referer", "https://www.samcheok.go.kr/media/00084/00095.web")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
				
		document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "삼척시";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text();
				String dept = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(4)").text();
				String regDate = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				String gosiNo = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][2] = dept.substring(7, dept.length());
				arr[index][3] = regDate.substring(6, regDate.length());
				arr[index][4] = gosiNo.substring(7, gosiNo.length());
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////속초시////////////////////
		
		System.out.println("속초시 크롤링 시작");

		try{
		response = Jsoup.connect("https://eminwon.sokcho.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("_ga", "GA1.1.253042840.1690022010")
				.cookie("_ga_BVFB54FB0W", "GS1.1.1692465813.2.0.1692465813.0.0.0")
				.cookie("SESSION_NTIS", "kg6CLGypP3vNM4xrDgdQGMGh54cT1pD91QlLjtMKQhj9zy9TvBzN!1864805944!1778780705!13002!-1")
				.header("Origin", "https://eminwon.sokcho.go.kr")
				.header("Referer", "https://eminwon.sokcho.go.kr/emwp/jsp/ofr/OfrNotAncmtLSub.jsp?not_ancmt_se_code=01,02,03,04,05,06")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&not_ancmt_reg_no=&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=&countYn=Y&searchSelect=not_ancmt_sj&searchInput=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
		document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#eminwon > form > div > table > tbody > tr:nth-child("+i+") > td.css-txa-left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "속초시";
				arr[index][1] = document.select("#eminwon > form > div > table > tbody > tr:nth-child("+i+") > td.css-txa-left > a").text();
				arr[index][2] = document.select("#eminwon > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
				arr[index][3] = document.select("#eminwon > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#eminwon > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////양구군////////////////////
		
		System.out.println("양구군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yanggu.go.kr/user_sub.php?gid=www&mu_idx=215")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("wcCookie", "125.179.129.136_T_39723_WC")
					.cookie("PHPSESSID", "uvtvrvc612p8v997cak4kq4su1")
					.header("Origin", "https://www.yanggu.go.kr")
					.header("Referer", "https://www.yanggu.go.kr/user_sub.php?gid=www&mu_idx=215")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("ptSignature=0j8Het%2B5AuZ%2F4U%2BmxpfdnpwV8MHwU%2FJ4zYtTpzBG98Ngw%2FPRrJ0NzRnY%2FsVDpHCK1ey7OUhqF1IZ2beG4%2BwdsJqEqloRmMKyjC36wzlfzTP1JfRJ%2Bdkfo3%2BWTU51RRmse5dcVD07WlXyucwd2TV1RhN3bLB%2FkL9Ojll5YkalZIp2RTSDaK21G38oU1jCEmI0Jq0qllUulbsXDC8mLGzHKT4kZeQVAhqoBx8hypSXbS8%3D&board_idx=17&bcd=announcement&scid=&sbcateid=&deltemplate=&onmm1=&onsm=&sbfd=boardsubject&pg=1&sbt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&bc_checkbot=Y")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
					
					
			document = response.parse();
		

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("#user_board_list > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "양구군";
				arr[index][1] = document.select("#user_board_list > table > tbody > tr:nth-child("+i+") > td.title > a").text();
				arr[index][2] = "";
				arr[index][3] = document.select("#user_board_list > table > tbody > tr:nth-child("+i+") > td.date.tc").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////양양군////////////////////
		
		System.out.println("양양군 크롤링 시작");

		try{
		response = Jsoup.connect("https://eminwon.yangyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("_ga", "GA1.3.1675534689.1686229023")
				.cookie("SESSION_NTIS", "kg727pS3p6gn09n0F0L0Ll5m5w1SQlJLfqqSh1CFwxnwMYyx2Hyk!-1862348305!1779590217!13003!-1")
				.cookie("_gid", "GA1.3.1630951653.1692465992")
				.cookie("_gat", "1")
				.header("Origin", "https://eminwon.yangyang.go.kr")
				.header("Referer", "https://eminwon.yangyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
		document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#A-Contents > div.skinMb-small > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "양양군";
				arr[index][1] = document.select("#A-Contents > div.skinMb-small > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text();
				arr[index][2] = document.select("#A-Contents > div.skinMb-small > table > tbody > tr:nth-child("+i+") > td.skinTb-name > a").text();
				arr[index][3] = document.select("#A-Contents > div.skinMb-small > table > tbody > tr:nth-child("+i+") > td.skinTb-date > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#A-Contents > div.skinMb-small > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////영월군////////////////////
		
		System.out.println("영월군 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.yw.go.kr/www/selectBbsNttList.do?key=273&bbsNo=17&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "96156456192BFA6987BE5FC22763655A")
				.cookie("wcs_bt", "ebefcf2e1878c0:1692466056")
				.header("Referer", "https://www.yw.go.kr/www/selectBbsNttList.do?bbsNo=17&key=273")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
		document = response.parse();
		
			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영월군";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text();
				arr[index][2] = "";
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.first").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////원주시////////////////////
		
		System.out.println("원주시 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.wonju.go.kr/www/selectBbsNttList.do?key=216&bbsNo=140&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("_ga_Z2XEW1RLB3", "GS1.3.1689602372.1.1.1689602402.0.0.0")
				.cookie("JSESSIONID", "5DBC7A011281B99BBE987897C7BE3EDA")
				.cookie("wcs_bt", "e57f78405a7738:1692466670")
				.cookie("_gid", "GA1.3.800121809.1692466671")
				.cookie("_ga_8CZPV3SN95", "GS1.1.1692466670.11.0.1692466670.60.0.0")
				.cookie("_ga_WPM2XNH5WL", "GS1.1.1692466670.11.0.1692466670.0.0.0")
				.cookie("_ga", "GA1.3.1376130970.1651595880")
				.cookie("_gat_UA-202243619-1", "1")
				.header("Referer", "https://www.wonju.go.kr/www/selectBbsNttList.do?bbsNo=140&key=216&")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
		document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > div > div > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "원주시";
				arr[index][1] = document.select("#contents > div > div > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text();
				arr[index][2] = document.select("#contents > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text();
				arr[index][3] = document.select("#contents > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////인제군////////////////////
		
		System.out.println("인제군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.inje.go.kr/portal/adm/bulletin/notify")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "FFFA3E57C46E50B18A447938601933CB")
					.cookie("Sphere_JSESSIONID", "p+SbDtEDM8qch8uKk/rQH0WQFXA")
					.header("Origin", "https://www.inje.go.kr")
					.header("Referer", "https://www.inje.go.kr/portal/adm/bulletin/notify")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&mode=&firstYN=N&searchCondition=TITLE&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&bc_checkbot=Y")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#section > div > div > div.right_contents_wrap > div > section > div:nth-child(4) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "인제군";
				arr[index][1] = document.select("#section > div > div > div.right_contents_wrap > div > section > div:nth-child(4) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text();
				arr[index][2] = document.select("#section > div > div > div.right_contents_wrap > div > section > div:nth-child(4) > table > tbody > tr:nth-child("+i+") > td.skinTb-name.skinTxa-center").text();
				arr[index][3] = document.select("#section > div > div > div.right_contents_wrap > div > section > div:nth-child(4) > table > tbody > tr:nth-child("+i+") > td.skinTb-date.skinTxa-center").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////정선군////////////////////
		
		System.out.println("정선군 크롤링 시작");

		try{
		response = Jsoup.connect("https://eminwon.jeongseon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("_ga", "GA1.3.2115122549.1690026789")
				.cookie("SESSION_NTIS", "kg21hwbcxGdznMLznYG2HJMpFGlSTDvnynCxQVTpGLV2DCySJL3l!-1405896484!1779173941!13003!-1")
				.cookie("_gid", "GA1.3.401536011.1692466752")
				.cookie("_gat", "1")
				.header("Origin", "https://eminwon.jeongseon.go.kr")
				.header("Referer", "https://eminwon.jeongseon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05%2C06&list_gubun=&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
				
		document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > section > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "정선군";
				arr[index][1] = document.select("body > form > section > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text();
				arr[index][2] = document.select("body > form > section > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.skinTb-name > a").text();
				arr[index][3] = document.select("body > form > section > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.skinTb-date > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > section > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////철원군////////////////////
		
		System.out.println("철원군 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.cwg.go.kr/www/selectBbsNttList.do?key=1226&bbsNo=25&searchCtgry2=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("CWG_JSESSIONID", "2A067995364F4E15FBE584BDC3A84215")
				.header("Referer", "https://www.cwg.go.kr/www/selectBbsNttList.do?bbsNo=25&key=1226")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child(1) > td.p-subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오
				arr[index][0] = "철원군";
				arr[index][1] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text();
				arr[index][2] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
				arr[index][3] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td:nth-child(6)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////춘천시////////////////////
		
		System.out.println("춘천시 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.chuncheon.go.kr/cityhall/administrative-info/notice-info/notice-announcement/?pageIndex=1&searchCnd=SJ&searchWrd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("person3", "%EA%B3%A0%EC%8B%9C%2F%EA%B3%B5%EA%B3%A0@@/cityhall/administrative-info/notice-info/notice-announcement/")
				.cookie("person2", "%EC%A3%BC%EC%9A%94+%EC%97%85%EB%AC%B4%EA%B3%84%ED%9A%8D@@/cityhall/administrative-info/municipal-info/chuncheon-major-administration/major-plan/")
				.cookie("person1", "2023%EB%85%84+%EC%98%88%EC%82%B0%EC%84%9C@@/cityhall/administrative-info/housekeeping/budget-statement/2023/")
				.cookie("JSESSIONID", "F9CA972C5836B9CBCF75B17056D424F8.w1tomcat2")
				.cookie("_gid", "GA1.3.795325322.1692466834")
				.cookie("_dc_gtm_UA-132991296-3", "1")
				.cookie("_gat_UA-132991296-3", "1")
				.cookie("_ga_KZJ1WSGLT6", "GS1.1.1692466834.4.1.1692466848.46.0.0")
				.cookie("_ga", "GA1.3.2122775920.1690078756")
				.header("Referer", "https://www.chuncheon.go.kr/cityhall/administrative-info/notice-info/notice-announcement/")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
				
		document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board > li:nth-child(1) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "춘천시";
				arr[index][1] = document.select("#board > li:nth-child(1) > a").text();
				arr[index][2] = document.select("#board > li:nth-child(1) > div > div > span:nth-child(1)").text();
				arr[index][3] = document.select("#board > li:nth-child(1) > div > div > span.day").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#board > li:nth-child(1) > span.noti-num").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////태백시////////////////////
		
		System.out.println("태백시 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.taebaek.go.kr/www/selectBbsNttList.do?key=352&bbsNo=25&integrDeptCode=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "D9D793920F547BB9715DB0F9090237AE")
				.header("Referer", "https://www.taebaek.go.kr/www/selectBbsNttList.do?bbsNo=25&key=352")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "태백시";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text();
				arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////평창군////////////////////
		
		System.out.println("평창군 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.pc.go.kr/portal/government/government-notification")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("_ga", "GA1.3.641002053.1690079151")
				.cookie("JSESSIONID", "DFD3204663908D29E2E68E1F944CEDAB")
				.cookie("_gid", "GA1.3.1149579472.1692466942")
				.cookie("_gat", "1")
				.header("Origin", "https://www.pc.go.kr")
				.header("Referer", "https://www.pc.go.kr/portal/government/government-notification")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mode=&gubun=&searchCondition=TITLE&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
				
		document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#A-Contents > div:nth-child(5) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "평창군";
				arr[index][1] = document.select("#A-Contents > div:nth-child(5) > table > tbody > tr:nth-child("+i+") > td.skinTb-sbj > a").text();
				arr[index][2] = document.select("#A-Contents > div:nth-child(5) > table > tbody > tr:nth-child("+i+") > td.skinTb-name.skinTxa-center").text();
				arr[index][3] = document.select("#A-Contents > div:nth-child(5) > table > tbody > tr:nth-child("+i+") > td.skinTb-date.skinTxa-center").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////홍천군////////////////////
		
		System.out.println("홍천군 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.hongcheon.go.kr/www/selectEminwonList.do?key=278&pageUnit=10&ofr_pageSize=10&searchCnd=B_Subject&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("_ga", "GA1.3.262137352.1688890786")
				.cookie("JSESSIONID", "600F2CFC0A67CAB1A0858764BBA85F2B")
				.header("Referer", "https://www.hongcheon.go.kr/www/selectEminwonList.do?key=278")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.method(org.jsoup.Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
				
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "홍천군";
				arr[index][1] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text();
				arr[index][2] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
				arr[index][3] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////화천군////////////////////
		
		System.out.println("화천군 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.ihc.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "kg1HT5W8Hy0zLJ0Czy4tTRghJvp1DDTh6Py24QyYhX4lZ2Ly923T!529464359!1779302729!13003!-1")
					.header("Origin", "http://eminwon.ihc.go.kr")
					.header("Referer", "http://eminwon.ihc.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
		document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "화천군";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(3) > p > a").text();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(4) > a").text();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(2) > p > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		///////////횡성군////////////////////
		
		System.out.println("횡성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.hsg.go.kr/www/selectBbsNttList.do?key=821&bbsNo=65&pageUnit=10&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "A01767396F719346441951F8C1AFE6F9")
					.header("Referer", "https://www.hsg.go.kr/www/selectBbsNttList.do?bbsNo=65&key=821&")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "횡성군";
				arr[index][1] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text();
				arr[index][2] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
				arr[index][3] = document.select("#bbsNttForm > fieldset > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}
	
	public String [][] ChungnamCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작
		
		// 충청남도
		System.out.println("충청남도 크롤링 시작");

		try{
			response = Jsoup.connect("http://www.chungnam.go.kr/cnnet/board.do?mnu_cd=CNNMENU02364")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("ACEUCI", "1")
				.cookie("WMONID", "53QRJOTDDVz")
				.cookie("ACEFCID", "UID-64884FD812EF378288EAF333")
				.cookie("ACEUACS", "1662280749848526731")
				.cookie("AUFAM3A33173330568", "1690038000000000000|6|1690900413313433160|1|1662280749848526731")
				.cookie("JSESSIONID", "XSy7B9UPf8JE08sB8HQNeiewLc9gQ8ORpgMsH1MUs6jRk5phx9oTjCZLYHNuGaAn.Y253YXMvY2h1bmduYW00")
				.cookie("MAINLOGCK", "MAINLOG")
				.cookie("__utma", "251505620.1130072441.1686654936.1690900415.1692467666.6")
				.cookie("__utmc", "251505620")
				.cookie("__utmz", "251505620.1692467666.6.6.utmcsr")
				.cookie("ASAM3A33173330568", "1692467665484034497%7C1692467665484034497%7C1692467665484034497%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
				.cookie("AUAM3A33173330568", "1692467665484034497%7C8%7C1686654933403533160%7C1%7C1662280749848526731%7C1")
				.cookie("ARAM3A33173330568", "httpwwwchungnamgokrcnnetboarddomnucdCNNMENU02364httpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
				.header("Origin", "http://www.chungnam.go.kr")
				.header("Referer", "http://www.chungnam.go.kr/cnnet/board.do?mnu_cd=CNNMENU02364")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.requestBody("searchCnd_4=&srtdate=2018-01-01&enddate=2023-08-20&searchCnd=0&searchWrd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&showSplitNo=10")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#count > tbody > tr:nth-child("+i+") > td.title_comm > a:nth-child(1)").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "충청남도";
				arr[index][1] = document.select("#count > tbody > tr:nth-child("+i+") > td.title_comm > a:nth-child(1)").text();
				arr[index][2] = document.select("#count > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
				arr[index][3] = document.select("#count > tbody > tr:nth-child("+i+") > td.tb_day").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		///////////계룡시////////////////////
		
		System.out.println("계룡시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gyeryong.go.kr/kr/html/sub03/030102.html?skey=sj&sval=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "en2rgf8g7s1h40o67h8v2qro4q")
					.cookie("_ga_PNJVVZGNCN", "GS1.1.1692509139.2.0.1692509139.0.0.0")
					.cookie("_ga", "GA1.3.203680485.1690080125")
					.cookie("_gid", "GA1.3.2121293749.1692509140")
					.cookie("_gat_gtag_UA_78355006_1", "1")
					.header("Referer", "https://www.gyeryong.go.kr/kr/html/sub03/030102.html")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
					
					
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td.text-left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "계룡시";
				arr[index][1] = document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td.text-left > a").text();
				arr[index][2] = document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
				arr[index][3] = document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////공주시////////////////////
		
		System.out.println("공주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gongju.go.kr/prog/saeolGosi/GOSI_03/sub04_03_03/list.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "-u2L6N-2s44")
					.cookie("JSESSIONID", "uva6KG1zkVv0PxTKx67HpAPtOAmolciup9oEe7hGaXv6NB5Axl1dewM97ayahr0o.V0FTMjAyMC9namhvbWVwYWdl")
					.cookie("_ga_C4L4YMP0GB", "GS1.1.1692509196.3.0.1692509196.0.0.0")
					.cookie("_ga", "GA1.3.1338365062.1690080349")
					.cookie("_gid", "GA1.3.1513844719.1692509197")
					.cookie("_gat_gtag_UA_167564883_1", "1")
					.header("Origin", "https://www.gongju.go.kr")
					.header("Referer", "https://www.gongju.go.kr/prog/saeolGosi/GOSI_03/sub04_03_03/list.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&searchCondition=all&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&pageUnit=10")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
				
			document = response.parse();
		

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "공주시";
				arr[index][1] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td.subject > a").text();
				arr[index][2] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////금산군////////////////////
		
		System.out.println("금산군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.geumsan.go.kr/kr/html/sub03/030302.html")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.1.106031688.1690080578")
					.cookie("SIDNAME", "ronty")
					.cookie("PHPSESSID", "3s9ese8fq13bj47ohv7h8tpn25")
					.cookie("_ga_9B2M8KEK1F", "GS1.1.1692509246.4.0.1692509246.0.0.0")
					.header("Origin", "https://www.geumsan.go.kr")
					.header("Referer", "https://www.geumsan.go.kr/kr/html/sub03/030302.html")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("sch_post=&skey=title&sval=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			// 데이터 있는지 체크
			if(Integer.valueOf(document.select("fieldset > div > div.float-md-left > div > span > strong").text()) == 1) {
				// 기본정보 불러오기
				arr[index][0] = "금산군";
				arr[index][1] = document.select("table > tbody > tr > td:nth-child(3) > a").text();
				arr[index][2] = document.select("table > tbody > tr > td:nth-child(4)").text();
				
				//금산군의 경우 등록일자가 없어서 추출 필요
				String range = document.select("table > tbody > tr > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				
				if(range.length() > 10) {
					range = range.substring(0, 8);
				}else {
					range = "";
				}
				arr[index][3] = range;
				arr[index][4] = document.select("table > tbody > tr > td:nth-child(2)").text();
				index++;
			}else if(Integer.valueOf(document.select("fieldset > div > div.float-md-left > div > span > strong").text()) > 1){
				// 출력 결과가 여러개인 경우
				for(int i = 1;i<=10;i++) {
					// 기본정보 불러오기
					arr[index][0] = "금산군";
					arr[index][1] = document.select("table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text();
					arr[index][2] = document.select("table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
					//금산군의 경우 등록일자가 없어서 추출 필요
					String range = document.select("table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					
					if(range.length() > 8) {
						range = range.substring(0, 8);
					}else {
						range = "";
					}
					arr[index][3] = range;
					arr[index][4] = document.select("table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text();
					index++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////논산시////////////////////
		
		System.out.println("논산시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.nonsan21.net/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Origin", "https://eminwon.nonsan21.net")
					.header("Referer", "https://eminwon.nonsan21.net/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C05%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&yyyy=2012&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
		document = response.parse();
		
		totNum = document.select("div > div.articles_info > dl > dd:nth-child(2)").text();
		totNum = totNum.substring(0,totNum.length()-1);

		// 데이터 있는지 체크
		if(Integer.valueOf(totNum) == 1) {
			// 기본정보 불러오기
			arr[index][0] = "논산시";
			arr[index][1] = document.select("table > tbody > tr > td.tal > a").text().trim();
			arr[index][2] = document.select("table > tbody > tr > td:nth-child(4)").text().trim();
			arr[index][3] = document.select("table > tbody > tr > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
			arr[index][4] = document.select("table > tbody > tr > td:nth-child(2)").text().trim();
			index++;
		}else if(Integer.valueOf(totNum) > 1){
			// 출력 결과가 여러개인 경우
			for(int i = 1;i<=10;i++) {
				// 기본정보 불러오기
				arr[index][0] = "논산시";
				arr[index][1] = document.select("div > table > tbody > tr:nth-child("+i+") > td.tal > a").text().trim();
				arr[index][2] = document.select("div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		
		//당진시
		
		System.out.println("당진시 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.dangjin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khlGTW2GjdLHFPK5D7RbKFfS2b2QXN822n4RK36m6JnRP1fQLCpn!410566809!1812922375!13003!-1")
					.cookie("_ga_Z4NCNFG5X1", "GS1.1.1692509471.3.0.1692509471.60.0.0")
					.cookie("_ga", "GA1.3.1030290612.1690099502")
					.cookie("_gid", "GA1.3.1705302668.1692509471")
					.cookie("_gat_gtag_UA_90021735_1", "1")
					.header("Origin", "http://eminwon.dangjin.go.kr")
					.header("Referer", "http://eminwon.dangjin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
					document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "당진시";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////보령시////////////////////
		
		System.out.println("보령시 크롤링 시작");

		try{
		response = Jsoup.connect("https://brcn.go.kr/prog/eminwon/kor/BB/sub04_03_01/list.do")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("WMONID", "zVjtBYep6Pl")
				.cookie("JSESSIONID", "K1nqGfLiQKPZTyM0nSya3sWvzA6MZ3JEpqFnD8LBBdk8oWfQxgWJtMdHygyZbYxI.brwas1_servlet_engine1")
				.cookie("_ga_F9REXX2MP6", "GS1.1.1692509506.4.0.1692509506.0.0.0")
				.cookie("_ga", "GA1.3.143759748.1690099761")
				.cookie("_gid", "GA1.3.1906751074.1692509506")
				.cookie("_gat", "1")
				.header("Origin", "https://brcn.go.kr")
				.header("Referer", "https://brcn.go.kr/prog/eminwon/kor/BB/sub04_03_01/list.do")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mno=sub01_01&searchCondition=subject&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&submitTy=%EA%B2%80%EC%83%89")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
		document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.left > div > span > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "보령시";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.left > div > span > a").text();
				arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.problem_name").text();
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////부여군////////////////////
		
		System.out.println("부여군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.buyeo.go.kr/_prog/_board/index.php?code=news_02&site_dvs_cd=kr&menu_dvs_cd=0402&skey=&sval=&site_dvs=&gubun=&GotoPage=")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.1193451668.1690100051")
					.cookie("PHPSESSID", "img4ta40j3ksap9shta3c35e87")
					.cookie("_gid", "GA1.3.368021692.1692509613")
					.cookie("_gat", "1")
					.cookie("_ga_ZVMV0WKDE7", "GS1.3.1692509613.3.0.1692509613.0.0.0")
					.header("Origin", "https://www.buyeo.go.kr")
					.header("Referer", "https://www.buyeo.go.kr/_prog/_board/?code=news_02&site_dvs_cd=kr&menu_dvs_cd=0402")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("skey=title&GotoPage=1&sval=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#txt > div.board_list > div > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "부여군";
				arr[index][1] = document.select("#txt > div.board_list > div > table > tbody > tr:nth-child("+i+") > td.title > a").text();
				arr[index][2] = document.select("#txt > div.board_list > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text();
				arr[index][3] = document.select("#txt > div.board_list > div > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		///////////서산시////////////////////
		
		System.out.println("서산시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.seosan.go.kr/common/program/eminwonView.jsp?pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&mobile_code=00&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "22dnkquUDb60TKhin4Tp5JGKDkXKsnddpCuS2boyAFqix6eGxvVaaMHN7BGStrQA.SS_AP_servlet_engine1")
					.cookie("wcs_bt", "6f45ba0ffec488:1692509655")
					.header("Referer", "https://www.seosan.go.kr/common/program/eminwonView.jsp?pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=&countYn=Y&list_gubun=&not_ancmt_sj=&not_ancmt_cn=&dept_nm=&mobile_code=00&Key=B_Subject&not_ancmt_mgt_no=&temp=")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > form > table.bbs_default.list > tbody > tr:nth-child("+i+") > td.text_left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "서산시";
				arr[index][1] = document.select("#contents > form > table.bbs_default.list > tbody > tr:nth-child("+i+") > td.text_left > a").text();
				arr[index][2] = document.select("#contents > form > table.bbs_default.list > tbody > tr:nth-child("+i+") > td:nth-child(4)").text();
				arr[index][3] = document.select("#contents > form > table.bbs_default.list > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > form > table.bbs_default.list > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 서천군
		System.out.println("서천군 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.seocheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.346139182.1690108480")
					.cookie("SESSION_NTIS", "khl2McWJCRhR3t4hDLZY2KRbL3xv99G77RXxr2ypGyf15wvjJ2R9!-2018272557!1812594995!13003!-1")
					.cookie("_gid", "GA1.3.1378467449.1692509696")
					.cookie("_gat", "1")
					.cookie("_ga_JQD33623FQ", "GS1.3.1692509696.3.0.1692509696.0.0.0")
					.header("Origin", "http://eminwon.seocheon.go.kr")
					.header("Referer", "http://eminwon.seocheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
		

		totNum = document.select("body > form > table:nth-child(12) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(1) > td > font").text();
		totNum = totNum.substring(6, totNum.indexOf("개"));

		
		// 데이터 있는지 체크
		if(Integer.valueOf(totNum) == 1) {// 기본정보 불러오기
			arr[index][0] = "서천군";
			arr[index][1] = document.select("body > form > table:nth-child(12) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(3) > p").text().trim();
			arr[index][2] = document.select("body > form > table:nth-child(12) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(4)").text().trim();
			arr[index][3] = document.select("body > form > table:nth-child(12) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
			arr[index][4] = document.select("body > form > table:nth-child(12) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(6) > td:nth-child(2) > p").text().trim();
			index++;

		}else if(Integer.valueOf(totNum) > 1){
			// 출력 결과가 여러개인 경우
			for(int i = 3;i<=12;i++) {
				// 기본정보 불러오기
				arr[index][0] = "서천군";
				arr[index][1] = document.select("div > table > tbody > tr:nth-child("+(i*2)+") > td.tal > a").text().trim();
				arr[index][2] = document.select("div > table > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("div > table > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("div > table > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2)").text().trim();
				index++;
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////아산시////////////////////
		System.out.println("아산시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.asan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.1531223361.1690110588")
					.cookie("_gid", "GA1.3.1240759022.1692509771")
					.cookie("_gat", "1")
					.cookie("SESSION_NTIS", "khmKkVLlHzNYHKGL8FY9hB7t2zBjWJKylbyK211pH6pT1ZLwT8bw!-937138509!1812204082!13002!-1")
					.cookie("_ga_TB8YLZGNT1", "GS1.3.1692509772.5.0.1692509772.0.0.0")
					.header("Origin", "https://eminwon.asan.go.kr")
					.header("Referer", "https://eminwon.asan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "아산시";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////예산군////////////////////
		
		System.out.println("예산군 크롤링 시작");

		try{
		response = Jsoup.connect("https://eminwon.yesan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("_ga", "GA1.3.312451242.1690111469")
				.cookie("SESSION_NTIS", "khm4mVWp8ML3m2xRLkTLPP8tBwGjxD7J1QbGC2tPnWCprFc1w1Jv!-122512875!1812791592!13002!-1")
				.cookie("_gid", "GA1.3.262393808.1692509819")
				.cookie("_gat", "1")
				.cookie("_ga_KP52R617CV", "GS1.3.1692509820.3.0.1692509820.0.0.0")
				.header("Origin", "https://eminwon.yesan.go.kr")
				.header("Referer", "https://eminwon.yesan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
			document = response.parse();
		
			for(int i = 2;i<=11;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(19) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p").text().equals("")) {
					break;
				}
				
				// 기본정보 불러오기
				arr[index][0] = "예산군";
				arr[index][1] = document.select("body > form > table:nth-child(19) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p").text();
				arr[index][2] = document.select("body > form > table:nth-child(19) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4)").text();
				arr[index][3] = document.select("body > form > table:nth-child(19) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(19) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////천안시////////////////////
		
		System.out.println("천안시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.cheonan.go.kr/kor/sub02_03_01.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "739B48F2B94CD4170F5427D9C23B9F54")
					.cookie("_ga_76JJYG3LJX", "GS1.1.1692509864.6.0.1692509864.0.0.0")
					.cookie("_ga", "GA1.3.1628594974.1690112067")
					.cookie("_gid", "GA1.3.901716038.1692509865")
					.cookie("_gat_gtag_UA_178883293_1", "1")
					.header("Referer", "https://www.cheonan.go.kr/kor/sub02_02_01.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(31) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "천안시";
				arr[index][1] = document.select("body > form > table:nth-child(31) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text();
				arr[index][2] = document.select("body > form > table:nth-child(31) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text();
				arr[index][3] = document.select("body > form > table:nth-child(31) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(31) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		///////////청양군////////////////////
		
		System.out.println("청양군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.cheongyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khmhctPhnS2yZpBjvJHNF16lXNv20xB9hWhFQvYLWn6ZwhHNLgTw!435483032!1812660790!13002!-1")
					.header("Origin", "https://eminwon.cheongyang.go.kr")
					.header("Referer", "https://eminwon.cheongyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "청양군";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		///////////태안군////////////////////
		
		System.out.println("태안군 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.taean.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khnvLrKqCnjZ2Vt8nJgPpcC2LZy2y5W879PkDzhQTVn7Fh1q1C40!1369583644!1812857182!13002!-1")
					.cookie("_gid", "GA1.3.1903271930.1692510001")
					.cookie("_gat", "1")
					.cookie("_gat_gtag_UA_213741573_1", "1")
					.cookie("_ga_7DXYZ7CBBB", "GS1.1.1692510000.5.0.1692510000.0.0.0")
					.cookie("_ga", "GA1.1.1236046233.1686654421")
					.cookie("_ga_2LTEKF7GVC", "GS1.3.1692510000.5.0.1692510000.0.0.0")
					.header("Origin", "http://eminwon.taean.go.kr")
					.header("Referer", "http://eminwon.taean.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "태안군";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		///////////홍성군////////////////////
		
		System.out.println("홍성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.hongseong.go.kr/prog/saeolGosi/kor/sub03_0204/GOSI_ALL/list.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "U8egtAEqLWS1adbjvJDDPNcLcTMVD11PpefvwH6AsyFUmRisxB85toGsjgoFTFP1.Z3JlZW53YXMvSG9uZ3Nlb25nXzE")
					.cookie("wcs_bt", "ec358848efde9:1692510048")
					.cookie("_ga_T0Q1KSP37S", "GS1.1.1692510049.9.0.1692510049.0.0.0")
					.cookie("_ga", "GA1.3.553195479.1690114731")
					.cookie("_gid", "GA1.3.204499755.1692510049")
					.cookie("_gat_gtag_UA_183899114_1", "1")
					.header("Origin", "https://www.hongseong.go.kr")
					.header("Referer", "https://www.hongseong.go.kr/prog/saeolGosi/kor/sub03_0204/GOSI_ALL/list.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&searchCondition=notAncmtSj&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&pageUnit=10")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "홍성군";
				arr[index][1] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().trim();
				arr[index][2] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#txt > div > div.no-more-tables > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}
	
	public String [][] ChungbukCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작
		
		// 충청북도
		System.out.println("충청북도 크롤링 시작");

		try{
			response = Jsoup.connect("https://sido.chungbuk.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "geWyd6uFhr8")
					.cookie("1JSESSIONID1", "kh52bB66OpNihuJbW4yFIEHd4qI1rSk6114eGa5VL15YNaUfmkVv!1618205761!1795190661!7012!-1")
					.header("Origin", "https://sido.chungbuk.go.kr")
					.header("Referer", "https://sido.chungbuk.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&gosiGbn=&sno=&sido=")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=3da683ad2e1789680ee7c426b5ed4026&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BD%C7%BD%C3%B0%E8%C8%B9")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "충청북도";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(1)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			///////////괴산군////////////////////
		
		System.out.println("괴산군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.goesan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("XTVID", "A2307161919470002")
					.cookie("SESSION_NTIS", "kh51f25Rp1Vn0kxlTL2k7QBPKkR4TrzWHJDJhVGQGMRhLqT0dGk5!-1675162573!1795755674!13003!-1")
					.header("Origin", "https://eminwon.goesan.go.kr")
					.header("Referer", "https://eminwon.goesan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01&title=%EA%B3%A0%EC%8B%9C&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&recent_mm=36&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > div.board_list.mCont_scroll.mt_10 > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "괴산군";
				arr[index][1] = document.select("body > form > div.board_list.mCont_scroll.mt_10 > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][2] = document.select("body > form > div.board_list.mCont_scroll.mt_10 > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("body > form > div.board_list.mCont_scroll.mt_10 > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > div.board_list.mCont_scroll.mt_10 > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//단양군
		
		System.out.println("단양군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.danyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "kh6H6LszSGRjvvGScSZQVf1y12HhSRSFLKN2T7c9VgltGQF2tsJN!436391182!1795909626!13003!-1")
					.header("Origin", "https://eminwon.danyang.go.kr")
					.header("Referer", "https://eminwon.danyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "단양군";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		///////////보은군////////////////////
		
		System.out.println("보은군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.boeun.go.kr/www/selectBbsNttList.do?key=2262&bbsNo=66&integrDeptCode=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "cBa58ngZXkMWiBtjf5VrNIzLprUpgu8VpKujqKREj9Mu8BKVx1oEjxjO2qMwWISg.boeunweb_servlet_engine1")
					.header("Referer", "https://www.boeun.go.kr/www/selectBbsNttList.do?bbsNo=66&key=2262")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "보은군";
				arr[index][1] = document.select("#board > table > tbody > tr:nth-child("+i+") > td.subject > a").text().trim();
				arr[index][2] = document.select("#board > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#board > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//영동군 홈페이지 암호화 처리로 제외
		
		//옥천군
		System.out.println("옥천군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.oc.go.kr/www/selectBbsNttList.do?key=236&bbsNo=40&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "8D45EBAC4F835145379CB5744DA6FD23")
					.cookie("_gid", "GA1.3.1160015053.1692514909")
					.cookie("_gat_gtag_UA_145583656_1", "1")
					.cookie("_ga_8PEYLXNZFB", "GS1.1.1692514908.10.1.1692514914.0.0.0")
					.cookie("_ga", "GA1.1.1308941761.1686653659")
					.header("Referer", "https://www.oc.go.kr/www/selectBbsNttList.do?key=236&bbsNo=40&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board > div > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "옥천군";
				arr[index][1] = document.select("#board > div > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().trim();
				arr[index][2] = document.select("#board > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#board > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > time").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//음성군	
		System.out.println("음성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.eumseong.go.kr/www/selectEminwonList.do?ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&key=80&pageUnit=10&searchCnd=B_Subject&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "22D69FD328AB9E3E6C1E36D363E0DFF2")
					.cookie("_ga_6JV43N624N", "GS1.1.1692514952.6.0.1692514952.0.0.0")
					.cookie("_ga", "GA1.3.37417151.1686498368")
					.cookie("_gid", "GA1.3.2105605417.1692514953")
					.cookie("_gat_gtag_UA_179587661_1", "1")
					.header("Referer", "https://www.eumseong.go.kr/www/selectEminwonList.do?pageUnit=10&key=80&ofr_pageSize=10&not_ancmt_se_code=01,02,03,04,05&pageIndex=1")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "음성군";
				arr[index][1] = document.select("#board > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = document.select("#board > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#board > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#board > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(1)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			///////////제천시////////////////////
		
		System.out.println("제천시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jecheon.go.kr/www/selectBbsNttList.do?key=5233&bbsNo=18&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "3WDaAEYQy2htWQ17fLxCSr2TQVO1A6akpCYff1Dt9aDWDnVAxfrwJhIMANI74bYU.okjcwas_servlet_homepage")
					.header("Referer", "https://www.jecheon.go.kr/www/selectBbsNttList.do?bbsNo=18&key=5233")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "제천시";
				arr[index][1] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td.p-subject > a").text().trim();
				arr[index][2] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > time").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > div.p-wrap.bbs.bbs__list > table > tbody > tr:nth-child("+i+") > td:nth-child(7)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//증평군
		
		System.out.println("증평군 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.jp.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "kh6z9LYqS2vxbv4hcvk0y6zTFRsWyhv7kh8WNNBGhRS3VcLtxTW6!130221649!1795949895!13003!-1")
					.cookie("_ga_D79NF86GB8", "GS1.1.1692515062.3.0.1692515062.0.0.0")
					.cookie("_ga", "GA1.3.1244394430.1690120493")
					.cookie("_gid", "GA1.3.1736336818.1692515062")
					.cookie("_gat_gtag_UA_115861715_1", "1")
					.header("Origin", "http://eminwon.jp.go.kr")
					.header("Referer", "http://eminwon.jp.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C03%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%2F%EA%B3%B5%EA%B3%A0%2F%EC%9E%85%EB%B2%95%EC%98%88%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "증평군";
				arr[index][1] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//진천군
		System.out.println("진천군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.jincheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "kh7G3Lp94J2GkQShynNT1yvFvL1v6J2krvWtrLtFGYyLRwQc8p42!-254624202!1795686768!13002!-1")
					.header("Origin", "https://eminwon.jincheon.go.kr")
					.header("Referer", "https://eminwon.jincheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > div.sb_w > div.w100.mCont_scroll_ifr > table > tbody > tr:nth-child("+i+") > titleHit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "진천군";
				arr[index][1] = document.select("body > form > div.sb_w > div.w100.mCont_scroll_ifr > table > tbody > tr:nth-child("+i+") > titleHit > a").text().trim();
				arr[index][2] = document.select("body > form > div.sb_w > div.w100.mCont_scroll_ifr > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > span").text().trim();
				arr[index][3] = document.select("body > form > div.sb_w > div.w100.mCont_scroll_ifr > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > span").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > div.sb_w > div.w100.mCont_scroll_ifr > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > span").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//청주시
		System.out.println("청주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.cheongju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "5hcnt3-wsHF")
					.cookie("JSESSIONID", "WRFQCLejEjKKRl4lmhiaMbd1vX6c3ICjpYTUKulyVtOQzrWaxVZCWOdLZU0Ts9ne.WEB_SERVER2_servlet_engine7")
					.cookie("SESSION_NTIS", "kh72zWhrQFc2VBrjDjKj89whBk5JDmztsJYlBph7gybPvh4VM9Pp!-980131424!1795290901!13004!-1")
					.cookie("_INSIGHT_CK_8301", "aa0d0e167ddb505a629b17e153348a86_21517|2c24141e71698236e25aa5c81b7ba239_15192:1692516992000")
					.header("Origin", "https://eminwon.cheongju.go.kr")
					.header("Referer", "https://eminwon.cheongju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&not_ancmt_reg_no=&epcCheck=Y&yyyy=2018&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "청주시";
				arr[index][1] = document.select("#board > table > tbody > tr:nth-child("+i+") > td.subject > a").text().trim();
				arr[index][2] = document.select("#board > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("#board > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#board > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//충주시
		System.out.println("충주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.chungju.go.kr/www/selectEminwonList.do?list_gubun=&searchKrwd=&ancmt_cn=&methodnm=&countYn=&searchCnd=all&wkly_event_mgt_no=&wkly_se_code=&pageUnit=10&ancmt_se_code=01%2C02%2C04&jndinm=&ofr_pageSize=10&title=&initValue=&homepage_pbs_yn=&subCheck=&context=&cha_dep_code_nm=&method=&countYnAC=&key=510&event_sj=&ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "5UeoYY3XHAY")
					.cookie("JSESSIONID", "zH1cCMmENGClvCVxmQrBDTMruLHxbJ9opW7RxHNaWwaIDWraxgCdMEqu5i5Yah1F.aG9tZXBhZ2UvaG9tZXBhZ2U%3D")
					.header("Referer", "https://www.chungju.go.kr/www/selectEminwonList.do?key=510&ofr_pageSize=10&ancmt_se_code=01,02,04&pageIndex=1")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "충주시";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().trim();
				arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}
	
	public String [][] JeonnamCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작

		// 전라남도
		System.out.println("전라남도 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jeonnam.go.kr/J0203/boardList.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("ACEUCI2", "1")
					.cookie("ACEUCI", "1")
					.cookie("ACEFCID", "UID-6485EDCB5E025C92CFFA13AB")
					.cookie("AUFAH2A44537282909", "1690124400000000000|4|1690802352480933160|1|1686498763799785837")
					.cookie("JSESSIONID", "osOTYBXmL9zNMxbfhq5No11oQJ7cOYpppAMaUkmrPM19UjtZxONDzuVMP4m0cb2o.jnwas1_servlet_engine1")
					.cookie("_ACU106117", "1686498761570121682.1692515378206.4.0.121682MK7BXIB6J6UE2.0.0.0.....")
					.cookie("_ACR0", "845aec5018785ddd5fd05d0aea50a69969970d1f")
					.cookie("ASAH2A44537282909", "1692515380293469434%7C1692515380293469434%7C1692515380293469434%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("AUAH2A44537282909", "1692515380293469434%7C5%7C1686498763312966696%7C1%7C16864987637997US7ZF%7C1")
					.cookie("ACEUACS", "16864987637997US7ZF")
					.cookie("ARAH2A44537282909", "httpswwwjeonnamgokrJ0203boardListdomenuIdjeonnam0203000000httpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("_ACS106117", "1132")
					.header("Origin", "https://www.jeonnam.go.kr")
					.header("Referer", "https://www.jeonnam.go.kr/J0203/boardList.do?menuId=jeonnam0203000000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("menuId=jeonnam0203000000&boardId=J0203&displayHeader=&infoReturn=&searchType=0&searchText=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#frm > div.bbs_basic > table > tbody > tr:nth-child("+i+") > td.title.left.petition > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "전라남도";
				arr[index][1] = document.select("#frm > div.bbs_basic > table > tbody > tr:nth-child("+i+") > td.title.left.petition > a").text().trim();
				arr[index][2] = document.select("#frm > div.bbs_basic > table > tbody > tr:nth-child("+i+") > td.name").text().trim();
				arr[index][3] = document.select("#frm > div.bbs_basic > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//강진군
		System.out.println("강진군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gangjin.go.kr/www/government/notify")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("BROWSER", "YToxNTp7czoxODoiYnJvd3Nlcl9uYW1lX3JlZ2V4IjtzOjY6IqdeLiokpyI7czoyMDoiYnJvd3Nlcl9uYW1lX3BhdHRlcm4iO3M6MToiKiI7czo2OiJwYXJlbnQiO3M6MTc6IkRlZmF1bHRQcm9wZXJ0aWVzIjtzOjc6ImNvbW1lbnQiO3M6MTU6IkRlZmF1bHQgQnJvd3NlciI7czo3OiJicm93c2VyIjtzOjE1OiJEZWZhdWx0IEJyb3dzZXIiO3M6MTQ6ImlzbW9iaWxlZGV2aWNlIjtzOjA6IiI7czo4OiJpc3RhYmxldCI7czowOiIiO3M6NzoidmVyc2lvbiI7czozOiIwLjAiO3M6ODoicGxhdGZvcm0iO3M6NzoidW5rbm93biI7czoxMToiZGV2aWNlX3R5cGUiO3M6NzoidW5rbm93biI7czo0OiJJRTExIjtiOjA7czo0OiJJRTEwIjtiOjA7czozOiJJRTkiO2I6MDtzOjM6IklFOCI7YjowO3M6MzoiSUU3IjtiOjA7fQ%3D%3D")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "a4c91cs78bcn3s5savch2o8m34")
					.cookie("_ga_9EQ1M9XGPF", "GS1.1.1692515435.5.0.1692515435.0.0.0")
					.cookie("_ga", "GA1.3.751647520.1688884646")
					.cookie("_gid", "GA1.3.620019914.1692515436")
					.cookie("_gat_gtag_UA_130583664_1", "1")
					.header("Origin", "https://www.gangjin.go.kr")
					.header("Referer", "https://www.gangjin.go.kr/www/government/notify")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "강진군";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//고흥군
		System.out.println("고흥군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.goheung.go.kr/getEminwon.do")
					.header("Accept", "*/*")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
					.cookie("JSESSIONID", "DBABDDD9232D5B481BD30A45B00BD260")
					.header("Origin", "https://www.goheung.go.kr")
					.header("Referer", "https://www.goheung.go.kr/contentsView.do?pageId=www99")
					.header("Sec-Fetch-Dest", "empty")
					.header("Sec-Fetch-Mode", "cors")
					.header("Sec-Fetch-Site", "same-origin")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("action=L&seq=&notAncmtSeCode=01%2C04%2C05&listGubun=A&recordCnt=10&movePage=1&prevAction=L&prevMovePage=1&eminwonSearch=S&eminwonQuery=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#eminwonWrap > div.eminwonList.board_list.board_type_a > div.list_body_wrap > div:nth-child("+i+") > p.subject.siiru-tl > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "고흥군";
				arr[index][1] = document.select("#eminwonWrap > div.eminwonList.board_list.board_type_a > div.list_body_wrap > div:nth-child("+i+") > p.subject.siiru-tl > a").text().trim();
				arr[index][2] = document.select("#eminwonWrap > div.eminwonList.board_list.board_type_a > div.list_body_wrap > div:nth-child("+i+") > p.writer").text().trim();
				arr[index][3] = document.select("#eminwonWrap > div.eminwonList.board_list.board_type_a > div.list_body_wrap > div:nth-child("+i+") > p.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//곡성군
		System.out.println("곡성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gokseong.go.kr/board/GosiList.do;jsessionid=32FE3FC0009F5C2EA55EF77AEA71F8F0?menuNo=102001003000&pageIndex=1&searchCnd=0&not_ancmt_mgt_no=&not_ancmt_se_code=&list_gubun=&srhCate=title&searchWrd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("wcs_bt", "145da80a0862e30:1688884290")
					.cookie("JSESSIONID", "32FE3FC0009F5C2EA55EF77AEA71F8F0")
					.header("Referer", "https://www.gokseong.go.kr/board/GosiList.do?menuNo=102001003000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > form > div > div.board_list_wrap > table > tbody > tr:nth-child("+i+") > td.tit > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "곡성군";
				arr[index][1] = document.select("#content > form > div > div.board_list_wrap > table > tbody > tr:nth-child("+i+") > td.tit > p > a").text().trim();
				arr[index][2] = document.select("#content > form > div > div.board_list_wrap > table > tbody > tr:nth-child("+i+") > td.writer").text().trim();
				arr[index][3] = document.select("#content > form > div > div.board_list_wrap > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > form > div > div.board_list_wrap > table > tbody > tr:nth-child("+i+") > td.AlignLeft").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//광양시
		System.out.println("광양시 크롤링 시작");

		try{
			response = Jsoup.connect("https://gwangyang.go.kr/saeol/gosi.es?mid=a11005010000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "BD2D0B04F53F2C07AEC3D20CE9D790E9")
					.cookie("HISMENU_a11005010000", "%7B%22mid%22%3A%221005010000%22%2C%22connDt%22%3A%2220230820161428%22%2C%22menuPath%22%3A%22%EB%89%B4%EC%8A%A4%5C%2F%EC%86%8C%EC%8B%9D+%3E+%EA%B3%A0%EC%8B%9C%5C%2F%EA%B3%B5%EA%B3%A0+%3E+%EA%B3%A0%EC%8B%9C%22%2C%22menuUrl%22%3A%22%5C%2Fmenu.es%3Fmid%3Da11005010000%22%2C%22sid%22%3A%22a1%22%7D")
					.header("Origin", "https://gwangyang.go.kr")
					.header("Referer", "https://gwangyang.go.kr/saeol/gosi.es?mid=a11005010000&type_code=01")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("mid=a11005010000&act=list&type_code=01&nPage=1&keyField=T&keyWord=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > div > div.p-wrap.bbs.bbs_list > table > tbody > tr:nth-child("+i+") > td.txt_center > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "광양시";
				arr[index][1] = document.select("#contents > div > div.p-wrap.bbs.bbs_list > table > tbody > tr:nth-child("+i+") > td.txt_center > a").text().trim();
				arr[index][2] = document.select("#contents > div > div.p-wrap.bbs.bbs_list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#contents > div > div.p-wrap.bbs.bbs_list > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > div > div.p-wrap.bbs.bbs_list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//구례군
		System.out.println("구례군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gurye.go.kr/board/GosiList.do;gurye.go.kr=67CC8C95F22079A17D362844EF771768?menuNo=115004002001&pageIndex=1&searchCnd=0&not_ancmt_mgt_no=&not_ancmt_se_code=&list_gubun=&searchWrd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.1.752791166.1690461976")
					.cookie("gurye.go.kr", "67CC8C95F22079A17D362844EF771768::TRB314458B")
					.cookie("_ga_C662XS5S26", "GS1.1.1692515720.3.0.1692515720.0.0.0")
					.header("Referer", "https://www.gurye.go.kr/board/GosiList.do?menuNo=115004002001")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > form:nth-child(5) > div > div.board_list_m > ul > li:nth-child("+i+") > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "구례군";
				arr[index][1] = document.select("#content > form:nth-child(5) > div > div.board_list_m > ul > li:nth-child("+i+") > p > a").text().trim();
				arr[index][2] = document.select("#content > form:nth-child(5) > div > div.board_list_m > ul > li:nth-child("+i+") > div > span.writer").text().trim();
				arr[index][3] = document.select("#content > form:nth-child(5) > div > div.board_list_m > ul > li:nth-child("+i+") > div > span.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > form:nth-child(5) > div > div.board_list_wrap > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 나주시
		System.out.println("나주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.naju.go.kr/www/administration/notice/legislation")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.1.1575349038.1686652755")
					.cookie("session_id", "i770l06q1prmirrd79dnuinm3o")
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "o704gp40mevh55r49g4usgln6t")
					.cookie("topLayer_browser", "no_show")
					.cookie("_ga_B24JZEJV7F", "GS1.1.1692515780.5.1.1692515816.24.0.0")
					.header("Origin", "https://www.naju.go.kr")
					.header("Referer", "https://www.naju.go.kr/www/administration/notice/legislation")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page_scale=15&search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			
			totNum = document.select("#board_sch1 > div.left_box > span").text();
			totNum = totNum.substring(2, totNum.length()-1);

			System.out.println("totNum: "+totNum);
			// 데이터 있는지 체크
			if(Integer.valueOf(totNum) == 1) {
				// 기본정보 불러오기
				arr[index][0] = "나주시";
				arr[index][1] = document.select("#content > div > div > div > div.board_list > table > tbody > tr > td.align_left > a").text().trim();
				arr[index][2] = document.select("#content > div > div > div > div.board_list > table > tbody > tr > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#content > div > div > div > div.board_list > table > tbody > tr > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}else if(Integer.valueOf(totNum) > 1){
				// 출력 결과가 여러개인 경우
				for(int i = 1;i<=10;i++) {
					// 기본정보 불러오기
					arr[index][0] = "나주시";
					arr[index][1] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.align_left > a").text().trim();
					arr[index][2] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
					arr[index][3] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = "";
					index++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 담양군 검색 불가로 인해 제외시킴
		
		// 목포시	
		System.out.println("목포시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.mokpo.go.kr/www/open_administration/city_news/notification/public_notice")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "1bfacgu3nuc9p8f2clpu4qjbu4")
					.cookie("_ga", "GA1.3.1587438361.1690467442")
					.cookie("_gid", "GA1.3.927802177.1692515852")
					.cookie("_gat_gtag_UA_80209313_1", "1")
					.cookie("_ga_M03W3XLVD2", "GS1.1.1692515851.2.0.1692515865.0.0.0")
					.header("Origin", "https://www.mokpo.go.kr")
					.header("Referer", "https://www.mokpo.go.kr/www/open_administration/city_news/notification")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "목포시";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.list_cate").text().trim();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//무안군
		System.out.println("무안군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.muan.go.kr/www/openmuan/new/announcement")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("session_id", "v87hrh152a4bcu77hhsqdskll9")
					.cookie("BROWSER", "YToxNTp7czoxODoiYnJvd3Nlcl9uYW1lX3JlZ2V4IjtzOjY6In5eLiokfiI7czoyMDoiYnJvd3Nlcl9uYW1lX3BhdHRlcm4iO3M6MToiKiI7czo2OiJwYXJlbnQiO3M6MTc6IkRlZmF1bHRQcm9wZXJ0aWVzIjtzOjc6ImNvbW1lbnQiO3M6MTU6IkRlZmF1bHQgQnJvd3NlciI7czo3OiJicm93c2VyIjtzOjE1OiJEZWZhdWx0IEJyb3dzZXIiO3M6MTQ6ImlzbW9iaWxlZGV2aWNlIjtzOjA6IiI7czo4OiJpc3RhYmxldCI7czowOiIiO3M6NzoidmVyc2lvbiI7czozOiIwLjAiO3M6ODoicGxhdGZvcm0iO3M6NzoidW5rbm93biI7czoxMToiZGV2aWNlX3R5cGUiO3M6NzoidW5rbm93biI7czo0OiJJRTExIjtiOjA7czo0OiJJRTEwIjtiOjA7czozOiJJRTkiO2I6MDtzOjM6IklFOCI7YjowO3M6MzoiSUU3IjtiOjA7fQ%3D%3D")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "kg13e82rcrvt02vt5g5rpri24m")
					.cookie("MUAN_SETTING_KEY", "kg13e82rcrvt02vt5g5rpri24m")
					.cookie("_ga_1N278NFZVL", "GS1.1.1692515898.2.0.1692515898.0.0.0")
					.cookie("_ga", "GA1.3.1545872804.1690471541")
					.cookie("_gid", "GA1.3.1111330551.1692515898")
					.cookie("_gat_gtag_UA_80215608_1", "1")
					.header("Origin", "https://www.muan.go.kr")
					.header("Referer", "https://www.muan.go.kr/www/openmuan/new/announcement")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "무안군";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.list_cate").text().trim();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 보성군
		System.out.println("보성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.boseong.go.kr/www/open_administration/city_news/notification")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("BROWSER", "YToxNTp7czoxODoiYnJvd3Nlcl9uYW1lX3JlZ2V4IjtzOjY6IqdeLiokpyI7czoyMDoiYnJvd3Nlcl9uYW1lX3BhdHRlcm4iO3M6MToiKiI7czo2OiJwYXJlbnQiO3M6MTc6IkRlZmF1bHRQcm9wZXJ0aWVzIjtzOjc6ImNvbW1lbnQiO3M6MTU6IkRlZmF1bHQgQnJvd3NlciI7czo3OiJicm93c2VyIjtzOjE1OiJEZWZhdWx0IEJyb3dzZXIiO3M6MTQ6ImlzbW9iaWxlZGV2aWNlIjtzOjA6IiI7czo4OiJpc3RhYmxldCI7czowOiIiO3M6NzoidmVyc2lvbiI7czozOiIwLjAiO3M6ODoicGxhdGZvcm0iO3M6NzoidW5rbm93biI7czoxMToiZGV2aWNlX3R5cGUiO3M6NzoidW5rbm93biI7czo0OiJJRTExIjtiOjA7czo0OiJJRTEwIjtiOjA7czozOiJJRTkiO2I6MDtzOjM6IklFOCI7YjowO3M6MzoiSUU3IjtiOjA7fQ%3D%3D")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "ocked7q55r206ds5ut5jp4jgp2")
					.cookie("_gid", "GA1.3.124029488.1692515934")
					.cookie("_gat_gtag_UA_121573810_1", "1")
					.cookie("_ga_M5HMXN0EDG", "GS1.1.1692515933.4.0.1692515933.0.0.0")
					.cookie("_ga", "GA1.1.1361907056.1690472779")
					.header("Origin", "https://www.boseong.go.kr")
					.header("Referer", "https://www.boseong.go.kr/www/open_administration/city_news/notification")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "보성군";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.list_cate").text().trim();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 순천시
		System.out.println("순천시 크롤링 시작");

		try{
			response = Jsoup.connect("http://www.suncheon.go.kr/kr/news/0004/0005/0001/?searchCondition=title&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "GIbQhItufzq")
					.cookie("JSESSIONID", "2B1750414C16DEC9B2226ABA552A5280")
					.cookie("zoomName", "100")
					.cookie("_gid", "GA1.3.1592668637.1692515984")
					.cookie("_gat_gtag_UA_128361791_1", "1")
					.cookie("_ga_8M2FWNPJRH", "GS1.1.1692515983.3.1.1692515997.0.0.0")
					.cookie("_ga", "GA1.3.2034633571.1690473331")
					.header("Referer", "http://www.suncheon.go.kr/kr/news/0004/0005/0001/")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#sub_content > table > tbody > tr:nth-child("+i+") > td.title_minwon.lefttd > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "순천시";
				arr[index][1] = document.select("#sub_content > table > tbody > tr:nth-child("+i+") > td.title_minwon.lefttd > a").text().trim();
				arr[index][2] = document.select("#sub_content > table > tbody > tr:nth-child("+i+") > td.writer").text().trim();
				arr[index][3] = document.select("#sub_content > table > tbody > tr:nth-child("+i+") > td.created").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 신안군	
		System.out.println("신안군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.shinan.go.kr/home/www/openinfo/participation_07/participation_07_04?search=search_title&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&x=32&y=7")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "75q24squ9gatsafaambmknqem1")
					.cookie("wcs_bt", "15d4b5f8f70ec0:1692516048")
					.header("Referer", "https://www.shinan.go.kr/home/www/openinfo/participation_07/participation_07_04/page.wscms")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "신안군";
				arr[index][1] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().trim();
				arr[index][2] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 여수시	
		System.out.println("여수시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yeosu.go.kr/www/govt/news/notify/new_notify")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "c2bj68vrm6btp3smqu2g6l1uia")
					.cookie("session_id", "c2bj68vrm6btp3smqu2g6l1uia")
					.cookie("HISTORY_SETTING_KEY", "c2bj68vrm6btp3smqu2g6l1uia")
					.cookie("_ga_ZH9MCCCX5N", "GS1.1.1692516091.2.0.1692516091.0.0.0")
					.cookie("_ga", "GA1.3.216701421.1688758665")
					.cookie("_gid", "GA1.3.654058962.1692516092")
					.cookie("_gat_gtag_UA_72553063_1", "1")
					.header("Origin", "https://www.yeosu.go.kr")
					.header("Referer", "https://www.yeosu.go.kr/www/govt/news/notify")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page_scale=15&search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.align_left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "여수시";
				arr[index][1] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.align_left > a").text().trim();
				arr[index][2] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > div > div > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.list_cate").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 영광군
		System.out.println("영광군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yeonggwang.go.kr/bbs/?b_id=gosigonggo&site=headquarter_new&mn=9059&type=lists&sc_cate=&per_page=15&sc_key=subject&sc_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("mydomain_ci_session", "M3I3bs0neF2nhKlr93KPqJk4GHJntbC8bzeyZD97dZLSDLctDCW%2FIR4P10eq%2FElbpGpvTCf8dGbT1iQcp%2F%2BhPfAj0yto%2B3z%2BpuH1QqYgRoNuvreGM4cUW0NkhMd28Z9pxafaHDyY%2Fin%2BKdxFi%2F0lUqGnlB%2BXJMGkaD4%2FWgWnwpXENPWy8LxhZ88z%2Fru0OprFVlHzwDBQwnlvWDauU%2FTs8yAd8fIH21PM6ZmlXTeJAsOrvlSnr8%2BEzLVw8dl8kxijfBRsDi8KZKX6kHf3ijpxUp6D23lvPz38uZrPS3cCyrefkxANscA93XVATgKyQODY8E6emjMOgv38XWkvTOwQk1ALs8wQ7gQVHBHJgTiqABnWkqvfLobDZxoHgkeZJyJrdNXMHGSkDYf9BLxyyve8GkGUi%2FWu58Bh4k4DH6qZFl27IiQOIUycNh8sw01lNwGjrljVKseCBlr14eHU0KOE3FUEmVpc37rm1vXaIZY9UZSsbYbhQ%2Bw5lJSXWVrd0IQ73SezrIKPpaz4GzWgy7dBYQ%3D%3Dc3dd26e59333fcba8b39f71b44c1c50451f24ebd")
					.cookie("_gid", "GA1.3.198108118.1692516144")
					.cookie("_gat_gtag_UA_74047017_1", "1")
					.cookie("wcs_bt", "c15819707ac140:1692516158")
					.cookie("_ga_513L2L1020", "GS1.1.1692516143.2.1.1692516158.0.0.0")
					.cookie("_ga", "GA1.1.1289768550.1690474879")
					.header("Referer", "https://www.yeonggwang.go.kr/bbs/?b_id=gosigonggo&site=headquarter_new&mn=9059")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board_list > table > tbody > tr:nth-child("+i+") > td.txt_l.t_title > div > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영광군";
				arr[index][1] = document.select("#board_list > table > tbody > tr:nth-child("+i+") > td.txt_l.t_title > div > a").text().trim();
				arr[index][2] = document.select("#board_list > table > tbody > tr:nth-child("+i+") > td.t_user").text().trim();
				arr[index][3] = document.select("#board_list > table > tbody > tr:nth-child("+i+") > td.t_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#board_list > table > tbody > tr:nth-child("+i+") > td.txt_l.t_title1 > div").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 영암군
		System.out.println("영암군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yeongam.go.kr/home/www/open_information/yeongam_news/announcement?search=search_title&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "bmn7qb4g6p6180bsrhmsse0610")
					.cookie("wcs_bt", "8b12ea0da61388:1692516214")
					.header("Referer", "https://www.yeongam.go.kr/home/www/open_information/yeongam_news/announcement/yeongam.go")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영암군";
				arr[index][1] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().trim();
				arr[index][2] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//완도군
		System.out.println("완도군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.wando.go.kr/wando/sub.cs?m=318")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "ECD48E0E53B472C231BAFCD0CD83EE84")
					.header("Origin", "https://www.wando.go.kr")
					.header("Referer", "https://www.wando.go.kr/wando/sub.cs?m=318")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.nttSj > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "완도군";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.nttSj > a").text().trim();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 장성군
		System.out.println("장성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jangseong.go.kr/home/www/news/jangseong/announcement?search=search_title&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&x=21&y=10")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WAF", "edad8ae45df2bc3fa21286f25133ff58")
					.cookie("PHPSESSID", "g72se278v4mijpjimp219s7l76")
					.cookie("wcs_bt", "59560989cd6170:1692516733")
					.header("Referer", "https://www.jangseong.go.kr/home/www/news/jangseong/announcement")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "장성군";
				arr[index][1] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().trim();
				arr[index][2] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#board_list_table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 장흥군
		System.out.println("장흥군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jangheung.go.kr/www/organization/news/notification")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("session_id", "5sh871id6m2q6pa1srndn0a3vr")
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
					.cookie("PHPSESSID", "6gm8h4ui6dh16kiidbqij7jkf6")
					.cookie("_ga_NMHEE1TVKF", "GS1.1.1692516768.2.0.1692516768.0.0.0")
					.cookie("_ga", "GA1.3.1849894790.1690475513")
					.cookie("_gid", "GA1.3.1907214575.1692516769")
					.cookie("_gat_gtag_UA_109491346_1", "1")
					.header("Origin", "https://www.jangheung.go.kr")
					.header("Referer", "https://www.jangheung.go.kr/www/organization/news/notification")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "장흥군";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 진도군
		System.out.println("진도군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jindo.go.kr/home/gosi/general.cs")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "6811328E2C4CCFC5B45AE2E59CF81B0E")
					.header("Origin", "https://www.jindo.go.kr")
					.header("Referer", "https://www.jindo.go.kr/home/gosi/general.cs?m=24")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("m=24&searchCondition=notAncmtSj&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contBody > form > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "진도군";
				arr[index][1] = document.select("#contBody > form > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#contBody > form > table > tbody > tr:nth-child("+i+") > td.name").text().trim();
				arr[index][3] = document.select("#contBody > form > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//함평군
		System.out.println("함평군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.hampyeong.go.kr/pg/GosiList.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SiiRU", "SiiRU.www.1894764401.1690475773327")
					.cookie("JSESSIONID", "BF06945969CE0B0809F005D779F1B100")
					.cookie("SiiRU_latestTouch", "1692516900731")
					.cookie("SiiRU_sessionExpiry", "1692518700731")
					.header("Origin", "https://www.hampyeong.go.kr")
					.header("Referer", "https://www.hampyeong.go.kr/pg/GosiList.do?pageId=www273")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&pageId=www273&listGubun=&notAncmtSeCode=01%2C02%2C03%2C04&search_Type=0&searchText=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&btnSearch=")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content_area > div.container > div > div.content > div.board_list > div.board_list_body > div:nth-child("+i+") > div.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "함평군";
				arr[index][1] = document.select("#content_area > div.container > div > div.content > div.board_list > div.board_list_body > div:nth-child("+i+") > div.subject > a").text().trim();
				arr[index][2] = document.select("#content_area > div.container > div > div.content > div.board_list > div.board_list_body > div:nth-child("+i+") > div.damdang").text().trim();
				arr[index][3] = document.select("#content_area > div.container > div > div.content > div.board_list > div.board_list_body > div:nth-child("+i+") > div.regdate").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content_area > div.container > div > div.content > div.board_list > div.board_list_body > div:nth-child("+i+") > div.gosinum").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 해남군
		System.out.println("해남군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.haenam.go.kr/planweb/board/list.9is")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "F2F396E6C5ED51E498E0E18C56D87BAC.was1")
					.header("Origin", "http://www.haenam.go.kr")
					.header("Referer", "http://www.haenam.go.kr/")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "cross-site")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("boardUid=18e3368f644b01470164eeb23d7b30f5&layoutUid=&contentUid=18e3368f5d745106015de961fbbd205e&categoryUid1=0&searchType=dataTitle&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > div.subContainer > div > div.contents > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "해남군";
				arr[index][1] = document.select("body > div.subContainer > div > div.contents > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("body > div.subContainer > div > div.contents > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.user").text().trim();
				arr[index][3] = document.select("body > div.subContainer > div > div.contents > div:nth-child(2) > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 화순군
		System.out.println("화순군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.hwasun.go.kr/board.do?S=S01&M=020102000000&b_code=0000000002")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "EFNwEPnB1HDfo1zyDEbykoAyTgvNgILLpNfjAipzlMYgc1hvxIOpIqleJiYr0gPc.hwasun_servlet_engine1")
					.header("Origin", "https://www.hwasun.go.kr")
					.header("Referer", "https://www.hwasun.go.kr/board.do?S=S01&M=020102000000&b_code=0000000002")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("S=S01&M=020102000000&b_code=0000000002&nPage=1&keyField=TITLE&keyWord=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#printPage > div.contents > form > div > table > tbody > tr:nth-child("+i+") > td.L > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "화순군";
				arr[index][1] = document.select("#printPage > div.contents > form > div > table > tbody > tr:nth-child("+i+") > td.L > a").text().trim();
				arr[index][2] = document.select("#printPage > div.contents > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#printPage > div.contents > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}

	public String [][] JeonbukCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작
		
		// 전라북도
		System.out.println("전라북도 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jeonbuk.go.kr/board/list.jeonbuk?menuCd=DOM_000000103001002001&boardId=BBS_0000012&categoryCode1=jeonbuk&categoryCode2=&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("ACEUCI", "1")
					.cookie("WMONID", "zq1vJ4HmTYI")
					.cookie("ACEFCID", "UID-64A83FE5AC7CEA6BC622B1AF")
					.cookie("_ga", "GA1.3.1892227472.1688748006")
					.cookie("AUFAM5A36031644840", "1690729200000000000|5|1691322417431657340|1|1681540096424648322")
					.cookie("ACEUACS", "1681540096424648322")
					.cookie("JSESSIONID", "2VxrT5U1QQQRoZrAP4yCzzxGgL7ezBjKpvu6zsAGTlhmF746xtvCKonQc5EiAXeP.amV1c19kb21haW4vaG9tZXBhZ2UxLTE")
					.cookie("_gid", "GA1.3.1489219720.1692518039")
					.cookie("_gat", "1")
					.cookie("ASAM5A36031644840", "1692518038664742182%7C1692518038664742182%7C1692518038664742182%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("AUAM5A36031644840", "1692518038664742182%7C8%7C1688748006745933160%7C1%7C1681540096424648322%7C1")
					.cookie("ARAM5A36031644840", "httpswwwjeonbukgokrboardlistjeonbukboardIdBBS0000012menuCdDOM000000103001002001contentsSid841cpathhttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("_ga_6Z08VQ20W3", "GS1.3.1692518039.7.0.1692518039.0.0.0")
					.header("Referer", "https://www.jeonbuk.go.kr/board/list.jeonbuk?boardId=BBS_0000012&menuCd=DOM_000000103001002001&contentsSid=841&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			
			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				
				// 기본정보 불러오기
				arr[index][0] = "전라북도";
				arr[index][1] = document.select("#content > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#content > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 고창군
		System.out.println("고창군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gochang.go.kr/index.gochang?menuCd=DOM_000000102003007000&boardId=BBS_0000180&startPage2=1&schType=DATA_TITLE&schKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "Z6buoC18Rk87QZyZ3aR523cAGutRjQSUpc943C9vaZ5c9bQWxbkxMBgEW1hnoBXm.amV1c19kb21haW4vc2VydmVyMw")
					.header("Referer", "https://www.gochang.go.kr/index.gochang?menuCd=DOM_000000102003007000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			
			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > ul > li:nth-child("+i+") > a > strong").text().equals("")) {
					break;
				}
				
				// 기본정보 불러오기
				String str = document.select("#content > div > ul > li:nth-child("+i+") > a > em").text().trim();
				
				arr[index][0] = "고창군";
				arr[index][1] = document.select("#content > div > ul > li:nth-child("+i+") > a > strong").text().trim();
				arr[index][3] = str.substring(str.indexOf("호")+8, str.indexOf("서")-4).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][2] = str.substring(str.indexOf("서")+4, str.length()).trim();
				arr[index][4] = str.substring(0, str.indexOf("호")+1).trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 군산시
		System.out.println("군산시 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.gunsan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khHZSdTdfLwhyT1D4Tyjgc44GQSjpQGvSJVlQh2l756q4K2Th9JR!759239006!1828847956!13003!-1")
					.header("Origin", "http://eminwon.gunsan.go.kr")
					.header("Referer", "http://eminwon.gunsan.go.kr/emwp/jsp/ofr/OfrNotAncmtLSub.jsp?not_ancmt_se_code=01,02,03,04,05")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&yyyy=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(24) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "군산시";
				arr[index][1] = document.select("body > form > table:nth-child(24) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(24) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(24) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(24) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 김제시
		System.out.println("김제시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gimje.go.kr/board/list.gimje?menuCd=DOM_000000105001003000&boardId=BBS_0000044&categoryCode2=&categoryCode3=&categoryCode1=&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "rc4V1KtdU2gOxk5A3moQzbRnc92vC2A4pGlV13UtwGmtvAsCxfMENIyp5pA6Vzqn.amV1c19kb21haW4vaG9tZXBhZ2UxLTI")
					.cookie("_ga_VVQ0JC7Y7F", "GS1.1.1692518290.2.0.1692518290.60.0.0")
					.cookie("_ga", "GA1.3.665684609.1690986958")
					.cookie("_gid", "GA1.3.1098935893.1692518290")
					.cookie("_gat_gtag_UA_1708370_3", "1")
					.header("Referer", "https://www.gimje.go.kr/board/list.gimje;jsessionid=FLmq5N3oP8tE8MMLiNXnzRSmcIQmjWQJTdmzodeCvLl7RAUYbINuhUH2sOQkmfGi.was02_servlet_engine6?boardId=BBS_0000044&menuCd=DOM_000000105001003000&contentsSid=1433&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#scon > div.bbs_skin > div.bbs_list01 > ul > li:nth-child("+i+") > div.list_tit > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "김제시";
				arr[index][1] = document.select("#scon > div.bbs_skin > div.bbs_list01 > ul > li:nth-child("+i+") > div.list_tit > a > span > strong").text().trim();
				arr[index][2] = document.select("#scon > div.bbs_skin > div.bbs_list01 > ul > li:nth-child("+i+") > div.list_tit > a > span > span.info > em:nth-child(1)").text().trim();
				arr[index][3] = document.select("#scon > div.bbs_skin > div.bbs_list01 > ul > li:nth-child("+i+") > div.list_tit > a > span > span.info > em:nth-child(2)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 남원시
		System.out.println("남원시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.namwon.go.kr/board/list.do?menuCd=DOM_000000202001003000&contentsSid=47&startPage=1&orderBy=REGISTER_DATE+DESC&boardId=BBS_0000005&listCel=1&categoryCode1=&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchStartDt=&searchEndDt=&listRow=10")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "ogsKZvC9fe0k5aItuaRdtUB1TCaFaN1zpWEs9Eda13ofeMvqxpAy10OtSqUfoC39.amV1c19kb21haW4vSE9NRTEx")
					.cookie("wcs_bt", "15d80e4ffd6204:1692518643")
					.cookie("_gid", "GA1.3.1425698060.1692518644")
					.cookie("_gat", "1")
					.cookie("_gat_gtag_UA_1708370_41", "1")
					.cookie("_ga_W3D8XP7KVM", "GS1.1.1692518644.4.0.1692518644.60.0.0")
					.cookie("_ga", "GA1.1.3624506.1690987254")
					.header("Referer", "https://www.namwon.go.kr/board/list.do?boardId=BBS_0000005&menuCd=DOM_000000202001003000&contentsSid=47&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#pdfDiv > div > table > tbody > tr:nth-child("+i+") > td.txt_left.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "남원시";
				arr[index][1] = document.select("#pdfDiv > div > table > tbody > tr:nth-child("+i+") > td.txt_left.title > a").text().trim();
				arr[index][2] = document.select("#pdfDiv > div > table > tbody > tr:nth-child("+i+") > td.assignment").text().trim();
				arr[index][3] = document.select("#pdfDiv > div > table > tbody > tr:nth-child("+i+") > td:nth-child(6)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#pdfDiv > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 무주군
		System.out.println("무주군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.muju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.1.1358774343.1690987451")
					.cookie("SESSION_NTIS", "khJ7zy8L8GLQQxCbRJKR51dTyKPQHChzkFByX1q1nD3hDLcPlFgD!281056087!1829306659!13002!-1")
					.cookie("_ga_7LTDQ8Y89R", "GS1.1.1692518717.4.0.1692518717.0.0.0")
					.header("Origin", "https://eminwon.muju.go.kr")
					.header("Referer", "https://eminwon.muju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "무주군";
				arr[index][1] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 부안군
		System.out.println("부안군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.buan.go.kr/board/list.buan?menuCd=DOM_000000103001003000&contentsSid=84&boardId=BBS_0000054&startPage=1&searchStartDt=&searchEndDt=&searchOperation=AND&orderBy=&categoryCode1=&categoryCode2=&categoryCode3=&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "fIkePKjFzKNuSoJt101yEvt5VhtYblZopDBIu9IZcIXQfkqgxscuVG3fz2NxKz1J.amV1c19kb21haW4vc2VydmVyMQ")
					.header("Referer", "https://www.buan.go.kr/board/list.buan?boardId=BBS_0000054&menuCd=DOM_000000103001003000&contentsSid=84&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 2;i<=11;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "부안군";
				arr[index][1] = document.select("#content > div > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#content > div > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = "20"+document.select("#content > div > div.bbs_skin2 > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//순창군
		System.out.println("순창군 크롤링 시작");

		try{
			response = Jsoup.connect("http://eminwon.sunchang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khJRvb1mZ1hmzmpn5w1wvb4QMtGLGvvkHz9bLLJ2d1nX612Hbh2T!1066893999!1829503869!13002!-1")
					.header("Origin", "http://eminwon.sunchang.go.kr")
					.header("Referer", "http://eminwon.sunchang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&yyyy=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > div > table > tbody > tr:nth-child("+i+") > td.tal > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "순창군";
				arr[index][1] = document.select("body > form > div > table > tbody > tr:nth-child("+i+") > td.tal > a").text().trim();
				arr[index][2] = document.select("body > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 완주군
		System.out.println("완주군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.wanju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khKJY2SjBJKx9kJfjpJ4LjL4GF4VvTYbB21nR2ZqbKRhjRsHwjpp!520861189!1829177900!13002!-1")
					.cookie("_ga_MPW6PYCKH3", "GS1.1.1692518934.2.0.1692518934.60.0.0")
					.cookie("_ga", "GA1.3.159190461.1690987835")
					.cookie("_gid", "GA1.3.792821365.1692518935")
					.cookie("_gat_gtag_UA_1708370_11", "1")
					.header("Origin", "https://eminwon.wanju.go.kr")
					.header("Referer", "https://eminwon.wanju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "완주군";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 익산시
		System.out.println("익산시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.iksan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.2034086889.1690989075")
					.cookie("_gid", "GA1.3.946399185.1692519014")
					.cookie("_gat", "1")
					.cookie("_ga_7YTBD9FLC2", "GS1.3.1692519014.3.1.1692519025.49.0.0")
					.cookie("SESSION_NTIS", "khK6q1Mqn0nLD1SZGsN002fdQGhsBXRmzYl7rPMZ4rTdwrCw1ypy!-52277639!1828914000!13003!-1")
					.header("Origin", "https://eminwon.iksan.go.kr")
					.header("Referer", "https://eminwon.iksan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=2019&recent_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(27) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "익산시";
				arr[index][1] = document.select("body > form > table:nth-child(27) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(27) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(27) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(27) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//임실군
		System.out.println("임실군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.imsil.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khKSwrx2WZ8SnbZT0hh0BGp2KZsG0QKJLpdkLJ1v5nb9WG8Y1q7g!-1565210459!1829437987!13003!-1")
					.header("Origin", "https://eminwon.imsil.go.kr")
					.header("Referer", "https://eminwon.imsil.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(26) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "임실군";
				arr[index][1] = document.select("body > form > table:nth-child(26) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(26) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(26) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(26) > tbody > tr > td > table:nth-child(1) > tbody:nth-child("+i+") > tr > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//장수군
		System.out.println("장수군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.jangsu.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khLJ91BbhNqJLDRmph0GjyL03kWB4DCFxGj0wwPcKcnGhsSWyT29!926601509!1829372441!13003!-1")
					.cookie("_ga_R7XJ840SYK", "GS1.1.1692519191.2.0.1692519191.60.0.0")
					.cookie("_ga", "GA1.3.615014893.1690989580")
					.cookie("_gid", "GA1.3.1745612471.1692519191")
					.cookie("_gat_gtag_UA_1708370_40", "1")
					.header("Origin", "https://eminwon.jangsu.go.kr")
					.header("Referer", "https://eminwon.jangsu.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "장수군";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 전주시
		System.out.println("전주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.jeonju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_ga", "GA1.3.729513804.1690989785")
					.cookie("SESSION_NTIS", "khLn2TvtNPrBG40Mp0y5dY4Fqb8hKQNtyQrvz1H0vWDQppT0y1v2!-262936723!1828782105!13002!-1")
					.cookie("_gid", "GA1.3.689513234.1692519273")
					.cookie("_gat", "1")
					.header("Origin", "https://eminwon.jeonju.go.kr")
					.header("Referer", "https://eminwon.jeonju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=60&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child("+i+") > td.tleft > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "전주시";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child("+i+") > td.tleft > a").text().trim();
				arr[index][2] = document.select("body > form > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > a").text().trim();
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//정읍시
		System.out.println("정읍시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jeongeup.go.kr/board/list.jeongeup?menuCd=DOM_000000101001001000&contentsSid=5&boardId=BBS_0000012&startPage=1&searchStartDt=&searchEndDt=&searchOperation=OR&orderBy=REGISTER_DATE+DESC&categoryCode1=&categoryCode2=&categoryCode3=&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "2P6A1jo6q09DGeF2R1aJnd4cfd8yiNJypca3Z9LkrWHiDZr6xHOauvGiR08ecG6v.amV1c19kb21haW4vc2VydmVyMQ")
					.cookie("_ga_FQTXE01WN8", "GS1.1.1692519318.2.0.1692519318.60.0.0")
					.cookie("_gid", "GA1.3.1927834733.1692519318")
					.cookie("_gat_gtag_UA_1708370_61", "1")
					.cookie("_gat_gtag_UA_118898689_1", "1")
					.cookie("_ga_FDX2M57DR3", "GS1.1.1692519318.2.0.1692519318.0.0.0")
					.cookie("_ga", "GA1.1.845107460.1690989993")
					.header("Referer", "https://www.jeongeup.go.kr/board/list.jeongeup?boardId=BBS_0000012&menuCd=DOM_000000101001001000&contentsSid=5&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#bbs_table > tbody > tr:nth-child("+i+") > td.txt_left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "정읍시";
				arr[index][1] = document.select("#bbs_table > tbody > tr:nth-child("+i+") > td.txt_left > a").text().trim();
				arr[index][2] = document.select("#bbs_table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#bbs_table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//진안군
		System.out.println("진안군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.jinan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khLC3NcQLQ9G7GXhQKyDWNVdTD7hhPjXym6sTJLyhyFLVpGGhnpk!-291827427!1829241385!13003!-1")
					.cookie("_ga_W3D8XP7KVM", "GS1.1.1692519364.1.0.1692519364.60.0.0")
					.cookie("_ga_4826GW6BFV", "GS1.1.1692519364.1.0.1692519364.0.0.0")
					.cookie("_ga", "GA1.3.835193223.1692519364")
					.cookie("_gid", "GA1.3.1885464680.1692519364")
					.cookie("_gat_gtag_UA_131888629_1", "1")
					.cookie("_gat_gtag_UA_1708370_41", "1")
					.header("Origin", "https://eminwon.jinan.go.kr")
					.header("Referer", "https://eminwon.jinan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "진안군";
				arr[index][1] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(3) > p").text().trim();
				arr[index][2] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > table:nth-child(23) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+(i*2)+") > td:nth-child(2) > p").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}
	
	public String [][] GyeongnamCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작
		
		// 경상남도
		System.out.println("경상남도 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gyeongnam.go.kr/index.gyeong?menuCd=DOM_000000135003009001")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("LENA-UID", "b8c140aa.601f264a7145c")
					.cookie("WMONID", "Djg8aFt25hL")
					.cookie("L-VISITOR", "ziv6itlfldce5")
					.cookie("XTVID", "A23080300342942527")
					.cookie("WAF", "e00d75791aa086fcb17da8dbf2adafa8")
					.cookie("JSESSIONID", "5267BB59ED2CD5BE65576E552102A6BF.jvmrouteMAIN21")
					.cookie("_gid", "GA1.3.1487937193.1692521333")
					.cookie("_gat_gtag_UA_193034439_1", "1")
					.cookie("layorClose3002", "Y")
					.cookie("_ga_1J509V2NXT", "GS1.1.1692521333.2.1.1692521345.0.0.0")
					.cookie("_ga", "GA1.1.212104776.1690990472")
					.header("Origin", "https://www.gyeongnam.go.kr")
					.header("Referer", "https://www.gyeongnam.go.kr/index.gyeong?menuCd=DOM_000000135003009000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("conGosiGbn=&conIfmStdt=2023-05-20&conIfmEnddt=2023-08-20&conAnnounceNo=&conDeptNm=&conTitle=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#subCnt > div.board > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경상남도";
				arr[index][1] = document.select("#subCnt > div.board > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#subCnt > div.board > table > tbody > tr:nth-child("+i+") > td.name").text().trim();
				arr[index][3] = document.select("#subCnt > div.board > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#subCnt > div.board > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 거제시
		System.out.println("거제시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.geoje.go.kr/index.geoje?menuCd=DOM_000008902001002001&searchType=NOT_ANCMT_SJ&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "s9HYuIkvhIj")
					.cookie("WAF", "d93e9a0fc3205441a9306723f77e1253")
					.cookie("JSESSIONID", "0QKc2kf3rgfjEAaaSqqJLYC7fmXdDVqUp1p3JmaRREH2f5hNxMOfje8Z7XVgxaj2.UEdKV0FTL01haW4yMw")
					.cookie("wcs_bt", "115f7bbf344c610:1692521437")
					.cookie("_ga_HVJ8G5J4GG", "GS1.1.1692521437.7.0.1692521437.60.0.0")
					.cookie("_ga", "GA1.3.800876126.1686651363")
					.cookie("_gid", "GA1.3.1523827111.1692521437")
					.cookie("_gat_gtag_UA_1708370_27", "1")
					.header("Referer", "https://www.geoje.go.kr/index.geoje?menuCd=DOM_000008902001002001")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#con-wrap > div.board-list > table > tbody > tr:nth-child("+i+") > td.tal.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "거제시";
				arr[index][1] = document.select("#con-wrap > div.board-list > table > tbody > tr:nth-child("+i+") > td.tal.title > a").text().trim();
				arr[index][2] = document.select("#con-wrap > div.board-list > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#con-wrap > div.board-list > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//거창군
		System.out.println("거창군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.geochang.go.kr/saeol/gosi.do?cpage=1&siteGubun=news&pageCd=NW0102000000&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.3.266619710.1690991908")
					.cookie("JSESSIONID", "C2BD0224C0A7971B50695BE742ED5BC9")
					.cookie("_gid", "GA1.3.1769612170.1692521510")
					.cookie("_gat", "1")
					.cookie("wcs_bt", "4a0d05b5cb11f0:1692521534")
					.cookie("_ga_CKL2SDRJ62", "GS1.3.1692521510.2.1.1692521534.0.0.0")
					.header("Referer", "https://www.geochang.go.kr/saeol/gosi.do?cpage=1&siteGubun=news&pageCd=NW0102000000&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#databox > li:nth-child("+i+") > a > span > strong").text().equals("")) {
					break;
				}
				
				// 기본정보 불러오기
				String str1 = document.select("#databox > li:nth-child("+i+") > a > span > i > span:nth-child(1)").text();
				String str2 = document.select("#databox > li:nth-child("+i+") > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#databox > li:nth-child("+i+") > a > span > i > span:nth-child(4)").text();
				
				arr[index][0] = "거창군";
				arr[index][1] = document.select("#databox > li:nth-child("+i+") > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//경남고성군
		System.out.println("경남고성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.goseong.go.kr/board/list.goseong?orderBy=&boardId=BBS_0000015&searchStartDt=&searchEndDt=&startPage=1&menuCd=DOM_000000102002002000&contentsSid=29&searchOperation=AND&startDt=&endDt=&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "D7C5DE4DEE4DCD1322894F3DDDB136CC")
					.cookie("_ga_XXR29FDSZ6", "GS1.1.1692521619.3.0.1692521619.0.0.0")
					.cookie("_ga", "GA1.3.306921217.1686930861")
					.cookie("_gid", "GA1.3.1742017873.1692521620")
					.cookie("_gat_gtag_UA_221898738_1", "1")
					.header("Referer", "https://www.goseong.go.kr/board/list.goseong?boardId=BBS_0000015&menuCd=DOM_000000102002002000&contentsSid=29&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#sub_contentnw > div > div.board-list > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경남고성군";
				arr[index][1] = document.select("#sub_contentnw > div > div.board-list > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = document.select("#sub_contentnw > div > div.board-list > table > tbody > tr:nth-child("+i+") > td.name").text().trim();
				arr[index][3] = document.select("#sub_contentnw > div > div.board-list > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#sub_contentnw > div > div.board-list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//김해시
		System.out.println("김해시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gimhae.go.kr/03360/00023/00029.web?cpage=1&deptCode=&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "R8Q5naJIub2")
					.cookie("WAF", "0a90d0181cc076b9dc5ec2b85a7fba65")
					.cookie("JSESSIONID", "FBYabi9i5HqsHNN5qpyx7nkqLIyAIt0Jp8sFZDfP5854EcsJx3nwU49cABia3dAv.UEdIUE9SVEFML3d3dzIy")
					.cookie("wcs_bt", "595141d8c6fce0:1692521663")
					.cookie("_ga_4C1ERYNMTY", "GS1.1.1692521664.4.0.1692521664.0.0.0")
					.cookie("_ga_0Q5YRDQ7GG", "GS1.1.1692521664.5.0.1692521664.0.0.0")
					.cookie("_ga", "GA1.3.1637057382.1691071831")
					.cookie("_gid", "GA1.3.1236342255.1692521665")
					.cookie("_gat_gtag_UA_127887564_1", "1")
					.header("Referer", "https://www.gimhae.go.kr/03360/00023/00029.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(4)").text();
				String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][0] = "김해시";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//남해군
		System.out.println("남해군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.namhae.go.kr/modules/saeol/gosi.do?cpage=1&siteGubun=socialm&pageCd=SM010110000&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "71AEF571DA6DF1CE5B3C32C5E18B3DEC")
					.header("Referer", "https://www.namhae.go.kr/modules/saeol/gosi.do?cpage=1&siteGubun=socialm&pageCd=SM010110000&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				String str1 = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(5)").text();
				String str2 = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();

				arr[index][0] = "남해군";
				arr[index][1] = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//밀양시
		System.out.println("밀양시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.miryang.go.kr/web/eMinwonList.do?mnNo=20903000000&owd=&pageIndex=1&searchCateId=01%2C04&not_ancmt_se_code1=01&not_ancmt_se_code4=04&searchCnd=0&searchWrd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "13A93892CA5A745F3301A2F02886CBAC.was1")
					.header("Referer", "https://www.miryang.go.kr/web/eMinwonList.do?mnNo=20903000000&owd=&pageIndex=1&searchCateId=01%2C05&not_ancmt_se_code1=01&not_ancmt_se_code5=05&searchCnd=0&searchWrd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 0;i<10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board-wrap > div.board-list-wrap > table > tbody > tr.row"+i+" > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "밀양시";
				arr[index][1] = document.select("#board-wrap > div.board-list-wrap > table > tbody > tr.row"+i+" > td.subject > a").text().trim();
				arr[index][2] = document.select("#board-wrap > div.board-list-wrap > table > tbody > tr.row"+i+" > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#board-wrap > div.board-list-wrap > table > tbody > tr.row"+i+" > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#board-wrap > div.board-list-wrap > table > tbody > tr.row"+i+" > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//사천시
		System.out.println("사천시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.sacheon.go.kr/news/00009/00014.web?gcode=2017&amode=list&wmode=&cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "GbaLJAlddRm")
					.cookie("JSESSIONID", "212DECDF62D422A97FD6F047CEF7BFA7")
					.header("Referer", "https://www.sacheon.go.kr/news/00009/00014.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#listForm > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "사천시";
				arr[index][1] = document.select("#listForm > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
				arr[index][2] = document.select("#listForm > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text().trim();
				arr[index][3] = document.select("#listForm > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//산청군
		System.out.println("산청군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.sancheong.go.kr/www/selectBbsNttList.do?key=158&bbsNo=118&integrDeptCode=&searchCnd=SJ&searchKrwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("TJSESSIONID", "B90C587C75CFF76BC01CA88CA31AA829")
					.header("Referer", "https://www.sancheong.go.kr/www/selectBbsNttList.do?bbsNo=118&key=158")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "산청군";
				arr[index][1] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td.subject > a").text().trim();
				arr[index][2] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 양산시
		System.out.println("양산시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yangsan.go.kr/portal/saeol/gosi/list.do?mId=0401020000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "zbdrrtzUh8B")
					.cookie("JSESSIONID", "1feEINiCwBAp4JaKOauL953NNFyu7c2rpQkPLH1huE43OV1TxHNg1lcAyboNV2Al.UFlTV0FTL1BPUlRBTDEx")
					.header("Origin", "https://www.yangsan.go.kr")
					.header("Referer", "https://www.yangsan.go.kr/portal/saeol/gosi/list.do?mId=0401020000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&pbsDivision=&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "양산시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_point").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//의령군
		System.out.println("의령군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.uiryeong.go.kr/board/list.uiryeong?orderBy=&boardId=BBS_0000070&searchStartDt=&searchEndDt=&startPage=1&menuCd=DOM_000000203003001001&contentsSid=201&searchType=DATA_TITLE&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("L-VISITOR", "x2mintlkd794vd")
					.cookie("LENA-UID", "ff15830f.5fddc096339ad")
					.cookie("L-VISITOR", "z3astlb4v9p434")
					.cookie("JSESSIONID", "A40E4254A7DD9D04A4448B27D17D0A93.skoinfojvmrouter")
					.header("Referer", "https://www.uiryeong.go.kr/board/list.uiryeong?boardId=BBS_0000070&menuCd=DOM_000000203003001001&contentsSid=201")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			totNum = document.select("#contents > div.contents > div.board-list > table > tbody > tr > td:nth-child(1)").text().trim();
			
			// 데이터 있는지 체크
			if(Integer.valueOf(totNum) == 1) {// 기본정보 불러오기
				arr[index][0] = "의령군";
				arr[index][1] = document.select("#contents > div.contents > div.board-list > table > tbody > tr > td.txt_left > a").text().trim();
				arr[index][2] = document.select("#contents > div.contents > div.board-list > table > tbody > tr > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#contents > div.contents > div.board-list > table > tbody > tr > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
			
			totNum = document.select("#contents > div.contents > div.board-list > table > tbody > tr:nth-child(1) > td:nth-child(1)").text().trim();
			if(Integer.valueOf(totNum) >1){
				// 출력 결과가 여러개인 경우
				for(int i = 1;i<=10;i++) {
					// 기본정보 불러오기
					arr[index][0] = "의령군";
					arr[index][1] = document.select("#contents > div.contents > div.board-list > table > tbody > tr:nth-child("+i+") > td.txt_left > a").text().trim();
					arr[index][2] = document.select("#contents > div.contents > div.board-list > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
					arr[index][3] = document.select("#contents > div.contents > div.board-list > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = "";
					index++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//진주시
		System.out.println("진주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.jinju.go.kr/00130/02730/05586.web?cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "NdZO0Dt--5L")
					.cookie("wcCookie", "125.179.129.136_T_25311_WC")
					.cookie("cms.sessionId", "HZFFEA675BAE3B49558CE035ADA04CDCFC")
					.cookie("JSESSIONID", "aKY0odqq8dvnfMyDOuWYaPUxNy8dG8rHp6AL4VOw1OYptiruxbNIFzyBpcaeQaI3.UEpJTkpVL3d3dzI")
					.cookie("wcs_bt", "13923603c59492c:1692522228")
					.header("Referer", "https://www.jinju.go.kr/00130/02730/05586.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(5)").text();
				String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
								
				arr[index][0] = "진주시";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//창녕군
		System.out.println("창녕군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.cng.go.kr/news/00000372/00000375.web;jsessionid=FB327DFB9AC9F6315E843FD8783E78A0.cng2?cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WAF", "bf24d2db1ac6d256aae46ca37b17d9ce")
					.cookie("JSESSIONID", "FB327DFB9AC9F6315E843FD8783E78A0.cng2")
					.cookie("wcs_bt", "1d02ef30f12110:1692522323")
					.cookie("_gid", "GA1.3.2047724174.1692522324")
					.cookie("_gat", "1")
					.cookie("_gat_gtag_UA_109161110_1", "1")
					.cookie("_ga_CE3SJHB28C", "GS1.1.1692522323.3.0.1692522323.0.0.0")
					.cookie("_ga", "GA1.1.191911072.1691220036")
					.cookie("_ga_J7W0ZBYPCF", "GS1.1.1692522324.3.0.1692522324.0.0.0")
					.cookie("_ga_TS9RM0RRKX", "GS1.3.1692522324.3.0.1692522324.0.0.0")
					.cookie("_ga_PMQZ1Z2VRW", "GS1.1.1692522324.3.0.1692522332.0.0.0")
					.header("Referer", "https://www.cng.go.kr/news/00000372/00000375.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.bbs1list1 > ul > li:nth-child("+i+") > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "창녕군";
				arr[index][1] = document.select("#body_content > div.bbs1list1 > ul > li:nth-child("+i+") > a > span > strong").text().trim();
				arr[index][2] = document.select("#body_content > div.bbs1list1 > ul > li:nth-child("+i+") > a > span > i > span:nth-child(2)").text().trim();
				arr[index][3] = document.select("#body_content > div.bbs1list1 > ul > li:nth-child("+i+") > a > span > i > span:nth-child(1)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//창원시
		System.out.println("창원시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.changwon.go.kr/cwportal/10310/10438/10439.web?cpage=1&pbsDivision=&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "y29VW0MiSb5qz2iuQY1vhhOjU6c5vBw5p141ohYE2Ouk757RxwS0n2nCM70lHI2Y.UENXTUFJTi9jd3BvcnRhbDQx")
					.cookie("cms.sessionId", "HZCB4D498757B74AC9A745EA1F71DAD1FF")
					.cookie("WMONID", "sIOEb4ZFkne")
					.cookie("cms.sessionId", "HZCB4D498757B74AC9A745EA1F71DAD1FF")
					.header("Referer", "https://www.changwon.go.kr/cwportal/10310/10438/10439.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div > div.list2table1.rspnsv > table > tbody > tr:nth-child("+i+") > td.tal > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "창원시";
				arr[index][1] = document.select("#body_content > div > div.list2table1.rspnsv > table > tbody > tr:nth-child("+i+") > td.tal > a").text().trim();
				arr[index][2] = document.select("#body_content > div > div.list2table1.rspnsv > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#body_content > div > div.list2table1.rspnsv > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//통영시
		System.out.println("통영시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.tongyeong.go.kr/00852/00853/00858.web?cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "B2F138C2ABA2BC555A1BCB426E9CD655")
					.cookie("wcs_bt", "13595e7e7d91444:1692522506")
					.header("Referer", "https://www.tongyeong.go.kr/00852/00853/00858.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			totNum = document.select("#body_content > div:nth-child(2) > div.left > div > b:nth-child(1)").text().trim();
			
			// 데이터 있는지 체크
			if(Integer.valueOf(totNum) == 1) {

				// 기본정보 불러오기
				String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > i > span:nth-child(4)").text();
				String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][0] = "통영시";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
			}
			
			if(Integer.valueOf(totNum) >1){
				// 출력 결과가 여러개인 경우
				for(int i = 1;i<=10;i++) {
					
					// 기본정보 불러오기
					String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(5)").text();
					String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
					String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
					
					arr[index][0] = "통영시";
					arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
					arr[index][2] = str1.substring(7,str1.length()).trim();
					arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = str3.substring(7,str3.length()).trim();
					index++;
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//하동군
		System.out.println("하동군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.hadong.go.kr/media/00012.web?cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "6B55FE8B6BFB2AD21E9F34CA8E316126")
					.header("Referer", "https://www.hadong.go.kr/media/00012.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(4)").text();
				String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][0] = "하동군";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//함안군
		System.out.println("함안군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.haman.go.kr/02385/02392/05764.web?cpage=1&gubun=present&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "-zKppTMlRAl::::BO5ABF69C2")
					.cookie("JSESSIONID", "yBTVh3eGtmJPnzvtg6HsUXO4mxy2MMmEpQXVfG6yLeE7wrsUx1k1jaIXYwz44oFg.Web-AP_servlet_engine2::::EZ32A4E51C")
					.cookie("wcs_bt", "c3e9e3a48073d0:1692522666")
					.header("Referer", "https://www.haman.go.kr/02385/02392/05764.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();

			document = response.parse();
			
			totNum = document.select("#body_content > div:nth-child(1) > div > div > b:nth-child(1)").text().trim();
			
			// 데이터 있는지 체크
			if(Integer.valueOf(totNum) == 1) {

				// 기본정보 불러오기
				String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > i > span:nth-child(4)").text();
				String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][0] = "함안군";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				index++;
			}
			
			if(Integer.valueOf(totNum) >1){
				// 출력 결과가 여러개인 경우
				for(int i = 1;i<=10;i++) {
					
					// 기본정보 불러오기
					String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(5)").text();
					String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
					String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
					
					arr[index][0] = "함안군";
					arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
					arr[index][2] = str1.substring(7,str1.length()).trim();
					arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = str3.substring(7,str3.length()).trim();
					index++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//함양군
		System.out.println("함양군 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.hygn.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "khYVLChvd17QXGd9BnH1wH1HBTDqKT3q5ZLLnbF13SbrwGDnJbRL!-1329555931!1880260671!13003!-1")
					.header("Origin", "https://eminwon.hygn.go.kr")
					.header("Referer", "https://eminwon.hygn.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > div.board > table > tbody > tr:nth-child("+i+") > td.subject200 > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "함양군";
				arr[index][1] = document.select("body > form > div.board > table > tbody > tr:nth-child("+i+") > td.subject200 > a").text().trim();
				arr[index][2] = document.select("body > form > div.board > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("body > form > div.board > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("body > form > div.board > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 합천군
		System.out.println("합천군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.hc.go.kr/04923/04924/04948.web?dep_nm=&stype=not_ancmt_sj&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("cms.sessionId", "HZ5076E5EAA5414C4FAACD5FE610F3D92D")
					.cookie("JSESSIONID", "m0MGUkZ4O6IJyq3z9QDuyeR8Nf8FirYpp5aeUpmerlBFCU9kx6oiwel2cxrQwA21.SENXRUIvbWFpbjEx")
					.cookie("wcs_bt", "1275ff134609494:1692522816")
					.header("Referer", "https://www.hc.go.kr/04923/04924/04948.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().equals("")) {
					break;
				}
				
				// 기본정보 불러오기
				String str1 = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(4)").text();
				String str2 = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][0] = "합천군";
				arr[index][1] = document.select("#body_content > div > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > strong").text().trim();
				arr[index][2] = str1.substring(7,str1.length()).trim();
				arr[index][3] = str2.substring(6,str2.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str3.substring(7,str3.length()).trim();
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}
	public String [][] GyeongbukCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// 크롤링 시작
		
		// 경상북도
		System.out.println("경상북도 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gb.go.kr/Main/page.do?bdName=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&mnu_uid=6789&p1=0&p2=0&dept_name=&dept_code=&BD_CODE=gosi_notice&B_START=2023-08-20&B_END=2023-08-20&key=2&word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "VtT9MJLK7gA")
					.cookie("JSESSIONID", "99U40fDFvZaOzgdUMXQI8d3PnGubIVGkpTCEvPMKVrWjXCDqxYF3Y2Vkt4f1S16S.www_servlet_engine1")
					.header("Referer", "https://www.gb.go.kr/Main/page.do?bdName=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&mnu_uid=6789&p1=0&p2=0&dept_name=&dept_code=&BD_CODE=gosi_notice&B_START=2023-06-20&B_END=2023-08-20&key=2&word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contwrap > div > div.bodlist_wrap > table > tbody > tr:nth-child("+i+") > td.b_subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경상북도";
				arr[index][1] = document.select("#contwrap > div > div.bodlist_wrap > table > tbody > tr:nth-child("+i+") > td.b_subject > a").text().trim();
				arr[index][2] = document.select("#contwrap > div > div.bodlist_wrap > table > tbody > tr:nth-child("+i+") > td.b_author").text().trim();
				arr[index][3] = "20"+document.select("#contwrap > div > div.bodlist_wrap > table > tbody > tr:nth-child("+i+") > td.b_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//포항시
		System.out.println("포항시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.pohang.go.kr/pohang/10391/subview.do?enc=Zm5jdDF8QEB8JTJGZ29zaU5vdGljZSUyRnBvaGFuZyUyRjAlMkZsaXN0LmRvJTNGc3JjaENvbHVtbiUzRHNqJTI2c3JjaFdyZCUzRCVFQyU4QiVBNCVFQyU4QiU5QyVFQSVCMyU4NCVFRCU5QSU4RCUyNg%3D%3D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "GJgHxYuaBG7")
					.cookie("wcCookie", "125.179.129.136_T_29915_WC")
					.cookie("JSESSIONID", "88B6B2040AAAA389E88BFD0693846CB0")
					.cookie("wcs_bt", "127674644dd4af4:1692523155")
					.cookie("_ga_RFP16YRFCR", "GS1.1.1692523156.6.0.1692523156.0.0.0")
					.cookie("PHPSESSID", "778mlvr0qb3lif6f2dbnljf4u5")
					.cookie("SESS_COUNTER_DENY", "1")
					.cookie("_ga", "GA1.3.2060520613.1688834499")
					.cookie("_gid", "GA1.3.939183855.1692523156")
					.cookie("_gat_gtag_UA_114658245_1", "1")
					.header("Referer", "https://www.pohang.go.kr/pohang/10391/subview.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#board > form > div > table > tbody > tr:nth-child("+i+") > td._artclTdTitle.subject.mobile_show > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "포항시";
				arr[index][1] = document.select("#board > form > div > table > tbody > tr:nth-child("+i+") > td._artclTdTitle.subject.mobile_show > a").text().trim();
				arr[index][2] = document.select("#board > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = "20"+document.select("#board > form > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#board > form > div > table > tbody > tr:nth-child("+i+") > td._artclTdTitle.subject.mobile_show > span").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//경주시
		System.out.println("경주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gyeongju.go.kr/open_content/ko/page.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "x7tvu35jk4lvfk")
					.cookie("scg-lb-id", "GYJ-0-1692523853354")
					.cookie("LENA-UID", "6f29056f.5ffe94359cb30")
					.cookie("JSESSIONID", "AECC58C64D406FCF3064CC760A290592.4bfd261f2b4f00202")
					.cookie("scg-lb-id", "GYJ-5-1692523864534")
					.header("Origin", "https://www.gyeongju.go.kr")
					.header("Referer", "https://www.gyeongju.go.kr/open_content/ko/page.do?mnu_uid=423&")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("step=1&mnu_uid=423&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&sbtn=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div.board_list_v01 > table > tbody > tr:nth-child("+i+") > td.aL.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경주시";
				arr[index][1] = document.select("#content > div.board_list_v01 > table > tbody > tr:nth-child("+i+") > td.aL.title > a").text().trim();
				arr[index][2] = document.select("#content > div.board_list_v01 > table > tbody > tr:nth-child("+i+") > td.w_name").text().trim();
				arr[index][3] = document.select("#content > div.board_list_v01 > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//김천시
		System.out.println("김천시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gc.go.kr/portal/saeol/gosi/list.do?mId=1202090200")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "x2q0m3bh8kq6sb")
					.cookie("LENA-UID", "ef09d0fb.602343d88c59f")
					.cookie("JSESSIONID", "E6C0EFF3AD05478A45E614A46C58724A.eadb9b07019106161")
					.header("Origin", "https://www.gc.go.kr")
					.header("Referer", "https://www.gc.go.kr/portal/saeol/gosi/list.do?mId=1202090200")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&perPageCount=10&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "김천시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_dept").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//안동시
		System.out.println("안동시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.andong.go.kr/portal/saeol/gosi/list.do?mId=0401020100")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "x613mhet1ajbc5")
					.cookie("LENA-UID", "5322df70.6023544a17e78")
					.cookie("WMONID", "PI3nyCL7zOf")
					.cookie("L-VISITOR", "zsmpphfghsrcj")
					.cookie("JSESSIONID", "6CF24EFAA399BEFE97BA8803B545D27B.b115658ce4eb00202")
					.header("Origin", "https://www.andong.go.kr")
					.header("Referer", "https://www.andong.go.kr/portal/saeol/gosi/list.do?mId=0401020100")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "안동시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_dept").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date01").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 구미시
		System.out.println("구미시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gumi.go.kr/portal/saeol/gosiList.do?seCode=01&mId=0401030000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("LENA-UID", "863ab783.602355fb9dddd")
					.cookie("WMONID", "g95Tw1UJqsL")
					.cookie("JSESSIONID", "15E4362CEDFA2739AE269493371501FC.db4d39a508d106161")
					.cookie("D_VISITOR_ID", "83a0a166-7ca3-bcd0-f396-680b6fcb9b45")
					.header("Origin", "https://www.gumi.go.kr")
					.header("Referer", "https://www.gumi.go.kr/portal/saeol/gosiList.do?seCode=01&mId=0401030000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&notAncmtSeNm=&searchType=not_ancmt_sj&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#listForm > div.bod_wrap > div > table > tbody > tr:nth-child("+i+") > td.txtL > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "구미시";
				arr[index][1] = document.select("#listForm > div.bod_wrap > div > table > tbody > tr:nth-child("+i+") > td.txtL > a").text().trim();
				arr[index][2] = document.select("#listForm > div.bod_wrap > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().trim();
				arr[index][3] = document.select("#listForm > div.bod_wrap > div > table > tbody > tr:nth-child("+i+") > td:nth-child(6)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#listForm > div.bod_wrap > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//영주시
		System.out.println("영주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yeongju.go.kr/open_content/main/page.do?mnu_uid=10619&board_code=&srchColumn=title&srchKwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("L-VISITOR", "zl197iorkm9jb")
					.cookie("LENA-UID", "725ec7a1.602357e2485e6")
					.cookie("_ga", "GA1.1.364712492.1691278636")
					.cookie("JSESSIONID", "2422BF06AD91F4CBB7365075C1D61942.52f7dae6b29800202")
					.cookie("_ga_S4K7H79F7E", "GS1.1.1692523540.2.0.1692523540.0.0.0")
					.header("Referer", "https://www.yeongju.go.kr/open_content/main/page.do?mnu_uid=10619&not_ancmt_se_code=01,04")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.taL.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영주시";
				arr[index][1] = document.select("#content > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.taL.subject > a").text().trim();
				arr[index][2] = document.select("#content > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.writer").text().trim();
				arr[index][3] = document.select("#content > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content > div > div.board_list > table > tbody > tr:nth-child("+i+") > td.s_num").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//영천시
		System.out.println("영천시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yc.go.kr/portal/saeol/gosi/list.do?mId=0301040000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "z5ht9ka6d4eeu")
					.cookie("LENA-UID", "2c95399d.602358b9d51ee")
					.cookie("WMONID", "zJDM-0v7q8z")
					.cookie("L-VISITOR", "x10eg9s4tf2o4q")
					.cookie("JSESSIONID", "4499893A933EC50576F584DA6178EEFB.edd98b65e3bd00202")
					.cookie("D_VISITOR_ID", "8a27c14b-e776-5cb6-86dc-893b430b5ab8")
					.header("Origin", "https://www.yc.go.kr")
					.header("Referer", "https://www.yc.go.kr/portal/saeol/gosi/list.do?mId=0301040000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영천시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.dept").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//상주시
		System.out.println("상주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.sangju.go.kr/gosi/list.tc?mn=3016&pageIndex=1&pageSeq=2686&mgtNo=-1&notAncmtSeCode=01%2C02%2C03%2C04%2C05%2C07&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "GOLTxKjM_PC")
					.cookie("JSESSIONID", "5911F4B6383A18C83B47D16A4020A84F")
					.header("Referer", "https://www.sangju.go.kr/gosi/list.tc?mn=3016&pageSeq=2686&paramIdx=&notAncmtSeCode=01%2C02%2C03%2C04%2C05%2C07")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#form1 > div.table_wrap > table > tbody > tr:nth-child("+i+") > td.tal > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "상주시";
				arr[index][1] = document.select("#form1 > div.table_wrap > table > tbody > tr:nth-child("+i+") > td.tal > a").text().trim();
				arr[index][2] = document.select("#form1 > div.table_wrap > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#form1 > div.table_wrap > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#form1 > div.table_wrap > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//문경시
		System.out.println("문경시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gbmg.go.kr/portal/saeol/gosi/list.do?mId=0301060000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "zn2vdsp7eujuk")
					.cookie("LENA-UID", "7b2378b2.6023612726dd9")
					.cookie("JSESSIONID", "83C7DA1915C92EF89971EC9E0AA28FD7.9aaabb18386c06161")
					.cookie("wcs_bt", "80665c57a9663c:1692523824")
					.header("Origin", "https://www.gbmg.go.kr")
					.header("Referer", "https://www.gbmg.go.kr/portal/saeol/gosi/list.do?mId=0301060000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "문경시";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.dept").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 경산시
		System.out.println("경산시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gbgs.go.kr/open_content/ko/page.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "z215rqpv99qo0h")
					.cookie("LENA-UID", "fdd72eaa.602361f0cef6a")
					.cookie("JSESSIONID", "72FD235D16718CB83DC9908E02FD387D.b5f9f3b63a0400262")
					.header("Origin", "https://www.gbgs.go.kr")
					.header("Referer", "https://www.gbgs.go.kr/open_content/ko/page.do?mnu_uid=2160")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("step=1&mnu_uid=2160&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div.table_wrap > table > tbody > tr:nth-child("+i+") > td.aL > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경산시";
				arr[index][1] = document.select("#content > div.table_wrap > table > tbody > tr:nth-child("+i+") > td.aL > a").text().trim();
				arr[index][2] = document.select("#content > div.table_wrap > table > tbody > tr:nth-child("+i+") > td.con_name").text().trim();
				arr[index][3] = document.select("#content > div.table_wrap > table > tbody > tr:nth-child("+i+") > td.con_rgstDateTime").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 군위군
		System.out.println("군위군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gunwi.go.kr/ko/page.do?step=1&mnu_uid=170&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&sbtn=%EA%B2%80%EC%83%89")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "84E00C1B4D8DB0CC2B5EB5369F5F530B")
					.header("Referer", "https://www.gunwi.go.kr/ko/page.do?mnu_uid=170")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#inner_content > div.tbl_wrap > table > tbody > tr:nth-child("+i+") > td.aL.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "군위군";
				arr[index][1] = document.select("#inner_content > div.tbl_wrap > table > tbody > tr:nth-child("+i+") > td.aL.title > a").text().trim();
				arr[index][2] = document.select("#inner_content > div.tbl_wrap > table > tbody > tr:nth-child("+i+") > td.w_name").text().trim();
				arr[index][3] = document.select("#inner_content > div.tbl_wrap > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//의성군
		System.out.println("의성군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.usc.go.kr/gosi/list.tc?mn=1271&pageIndex=1&pageSeq=1217&mgtNo=-1&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "1B074293503A65E8197E4D6B9803CE61")
					.header("Referer", "https://www.usc.go.kr/gosi/list.tc?mn=1271&pageSeq=1217")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#form1 > div.scrollzone.mt30 > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "의성군";
				arr[index][1] = document.select("#form1 > div.scrollzone.mt30 > div > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > a").text().trim();
				arr[index][2] = document.select("#form1 > div.scrollzone.mt30 > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#form1 > div.scrollzone.mt30 > div > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#form1 > div.scrollzone.mt30 > div > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 청송군
		System.out.println("청송군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.cs.go.kr/news/00002679/00006203.web?cpage=1&stype=title&sstring=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "2C1E7D01DB9D3B2DB8DBF5153C5A9D58")
					.cookie("__utma", "122350804.1463765231.1691284406.1691284406.1692524156.2")
					.cookie("__utmc", "122350804")
					.cookie("__utmz", "122350804.1692524156.2.2.utmcsr")
					.cookie("__utmt", "1")
					.cookie("__utmb", "122350804.1.10.1692524156")
					.cookie("wcs_bt", "11dfaadbf7fa70:1692524155")
					.header("Referer", "https://www.cs.go.kr/news/00002679/00006203.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.list1table4 > dl > dd > ul > li.m"+i+" > span.t2 > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "청송군";
				arr[index][1] = document.select("#body_content > div.list1table4 > dl > dd > ul > li.m"+i+" > span.t2 > a").text().trim();
				arr[index][2] = document.select("#body_content > div.list1table4 > dl > dd > ul > li.m"+i+" > span.t4").text().trim();
				arr[index][3] = document.select("#body_content > div.list1table4 > dl > dd > ul > li.m"+i+" > span.t5").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#body_content > div.list1table4 > dl > dd > ul > li.m"+i+" > span.t3").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//영양군
		System.out.println("영양군 크롤링 시작");

		try{
		response = Jsoup.connect("https://www.yyg.go.kr/www/organization/yyg_news/notification")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("LENA-UID", "b20d6677.6011175f006cf")
				.cookie("session_id", "aff5bef268ec25a72654818a6b2bd7f0")
				.cookie("_ga", "GA1.3.715231827.1690024369")
				.cookie("BROWSER", "YToxNTp7czoxODoiYnJvd3Nlcl9uYW1lX3JlZ2V4IjtzOjY6In5eLiokfiI7czoyMDoiYnJvd3Nlcl9uYW1lX3BhdHRlcm4iO3M6MToiKiI7czo2OiJwYXJlbnQiO3M6MTc6IkRlZmF1bHRQcm9wZXJ0aWVzIjtzOjc6ImNvbW1lbnQiO3M6MTU6IkRlZmF1bHQgQnJvd3NlciI7czo3OiJicm93c2VyIjtzOjE1OiJEZWZhdWx0IEJyb3dzZXIiO3M6MTQ6ImlzbW9iaWxlZGV2aWNlIjtzOjA6IiI7czo4OiJpc3RhYmxldCI7czowOiIiO3M6NzoidmVyc2lvbiI7czozOiIwLjAiO3M6ODoicGxhdGZvcm0iO3M6NzoidW5rbm93biI7czoxMToiZGV2aWNlX3R5cGUiO3M6NzoidW5rbm93biI7czo0OiJJRTExIjtiOjA7czo0OiJJRTEwIjtiOjA7czozOiJJRTkiO2I6MDtzOjM6IklFOCI7YjowO3M6MzoiSUU3IjtiOjA7fQ%3D%3D")
				.cookie("HUA", "3357fadb0316939352bbdd4d5360a97f")
				.cookie("PHPSESSID", "a6a3ad872bb2e9ee813e62c530fca187")
				.cookie("HISTORY_SETTING_KEY", "a6a3ad872bb2e9ee813e62c530fca187")
				.cookie("_gid", "GA1.3.877114726.1692465927")
				.cookie("_gat", "1")
				.cookie("_ga_PGB338PMPP", "GS1.3.1692524208.6.0.1692524208.0.0.0")
				.header("Origin", "https://www.yyg.go.kr")
				.header("Referer", "https://www.yyg.go.kr/www/organization/yyg_news/notification")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("search_type=title&search_word=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
				.method(org.jsoup.Connection.Method.POST)
				.ignoreContentType(true)
				.execute();
				
				
		document = response.parse();
		

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영양군";
				arr[index][1] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.title > a").text();
				arr[index][2] = document.select("#content > table > tbody > tr:nth-child("+i+") > td.list_cate").text();
				arr[index][3] = document.select("#content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//영덕군
		System.out.println("영덕군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.yd.go.kr/?page_id=763&pageid=1&mod=list&target=&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.1.1733179278.1691285113")
					.cookie("PHPSESSID", "sduqhqvimot6jpjfjihp95g4no")
					.cookie("_ga_1QZKDCT5GJ", "GS1.1.1692524255.3.1.1692524260.0.0.0")
					.header("Referer", "https://www.yd.go.kr/?page_id=763&pageid=1&mod=list&target=&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#kboard-default-list > div.kboard-list > table > tbody > tr:nth-child("+i+") > td.kboard-list-title > div > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영덕군";
				arr[index][1] = document.select("#kboard-default-list > div.kboard-list > table > tbody > tr:nth-child("+i+") > td.kboard-list-title > div > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#kboard-default-list > div.kboard-list > table > tbody > tr:nth-child("+i+") > td.kboard-list-date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//청도군
		System.out.println("청도군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.cheongdo.go.kr/open.content/ko/administration/news/announcement/?q=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("scg-lb-id", "CD-2-1692524669046")
					.cookie("current-language", "ko")
					.cookie("_gid", "GA1.3.2079722502.1692524313")
					.cookie("scg-lb-id", "CD-2-1692524671396")
					.cookie("_ga_Z1M421YBM6", "GS1.1.1692524312.3.1.1692524323.0.0.0")
					.cookie("_ga", "GA1.1.453151348.1691285388")
					.header("Referer", "https://www.cheongdo.go.kr/open.content/ko/administration/news/announcement/?q=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content-main > table > tbody > tr:nth-child("+i+") > td.td-left.space-letter > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "청도군";
				arr[index][1] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td.td-left.space-letter > a").text().trim();
				arr[index][2] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//고령군
		System.out.println("고령군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.goryeong.go.kr/kor/boardList.do?IDX=154&BRD_ID=1023&searchType=SUBJECT&searchValue=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "A4F41B5784E1A9EE0A31C8820215D0BF")
					.header("Origin", "https://www.goryeong.go.kr")
					.header("Referer", "https://www.goryeong.go.kr/kor/boardList.do?IDX=154&BRD_ID=1023")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("BRD_ID=1023&IDX=154&page=1&searchType=SUBJECT&searchValue=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#frm > table > tbody > tr:nth-child("+i+") > td.bL_tableTitle > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "고령군";
				arr[index][1] = document.select("#frm > table > tbody > tr:nth-child("+i+") > td.bL_tableTitle > a").text().trim();
				arr[index][2] = document.select("#frm > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().trim();
				arr[index][3] = document.select("#frm > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//성주군
		System.out.println("성주군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.sj.go.kr/page.do?mnu_uid=1044&srchDept=&srchColumn=bod_title&srchKwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("LENA-UID", "cd569603.60237317fd648")
					.cookie("L-VISITOR", "z1aokg5823j0mt")
					.cookie("JSESSIONID", "DF22551C45F0C774F5C9FFF0BE419570.c95ddd18bd6500202")
					.header("Referer", "https://www.sj.go.kr/page.do?mnu_uid=1044")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#sub_cont > table > tbody > tr:nth-child("+i+") > td.list_tit.txtL > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "성주군";
				arr[index][1] = document.select("#sub_cont > table > tbody > tr:nth-child("+i+") > td.list_tit.txtL > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#sub_cont > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//칠곡군
		System.out.println("칠곡군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.chilgok.go.kr/portal/saeol/gosi/list.do?mId=0201030000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("L-VISITOR", "x4d51n9gmv60s")
					.cookie("LENA-UID", "a3934e82.6023bd2f63b35")
					.cookie("JSESSIONID", "85592C7B9493B06848953DFCE95621BE.2105305896ec00202")
					.header("Origin", "https://www.chilgok.go.kr")
					.header("Referer", "https://www.chilgok.go.kr/portal/saeol/gosi/list.do?mId=0201030000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			for(int i = 1;i<=10;i++) {				// 데이터 있는지 체크
				if(document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().equals("")) {
					break;
				}
				arr[index][0] = "칠곡군";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_dept").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//예천군 고시 검색 안됨
		
		//봉화군
		System.out.println("봉화군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.bonghwa.go.kr/open.content/ko/news/news/announcement/bonghwa/?q=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.3.306837335.1691306894")
					.cookie("current-language", "ko")
					.cookie("_gid", "GA1.3.1043313645.1692524736")
					.cookie("_gat", "1")
					.cookie("_ga_62BKPKE9DQ", "GS1.3.1692524736.2.0.1692524736.60.0.0")
					.header("Referer", "https://www.bonghwa.go.kr/open.content/ko/news/news/announcement/bonghwa/")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content-main > table > tbody > tr:nth-child("+i+") > td.td-left.space-letter > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "봉화군";
				arr[index][1] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td.td-left.space-letter > a").text().trim();
				arr[index][2] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().trim();
				arr[index][3] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#content-main > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//울진군
		System.out.println("울진군 크롤링 시작");

		try{
			response = Jsoup.connect("http://www.uljin.go.kr/index.uljin?menuCd=DOM_000000103002007001&startPage=1&schDepNm=&searchType=NOT_ANCMT_SJ&keyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("LENA-UID", "1e1690f5.6023eed16b153")
					.cookie("L-VISITOR", "z6vpb5a1jp9v5n")
					.cookie("JSESSIONID", "54416D832C7EC81C6FCAA80991652D81.eda2e9ec52b800202")
					.cookie("_ga_6FG504W6WQ", "GS1.1.1692524775.2.0.1692524775.60.0.0")
					.cookie("_gid", "GA1.3.1852561729.1692524776")
					.cookie("_gat_gtag_UA_110603752_1", "1")
					.cookie("_gat_gtag_UA_109061437_1", "1")
					.cookie("_ga_295HTZSF5S", "GS1.1.1692524776.2.0.1692524776.0.0.0")
					.cookie("_ga", "GA1.1.699252795.1691319153")
					.header("Referer", "http://www.uljin.go.kr/index.uljin?menuCd=DOM_000000103002007000")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			
			totNum = document.select("#s_wrapper > section > div.s_right > article > div.bbs_skin > p.bbs_total > strong").text().trim();

			// 출력 결과가 한개인 경우
			if(Integer.valueOf(totNum) == 1) {
				String str = document.select("#s_wrapper > section > div.s_right > article > div.bbs_skin > ul > li > a > em").text().trim();

				// 기본정보 불러오기
				arr[index][0] = "울진군";
				arr[index][1] = document.select("#s_wrapper > section > div.s_right > article > div.bbs_skin > ul > li > a > strong").text().trim();
				arr[index][2] = "";
				arr[index][3] = str.substring(str.indexOf("게")+5,str.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = str.substring(0,str.indexOf("부")-1);
				index++;
			}

			// 출력 결과가 여러개인 경우
			if(Integer.valueOf(totNum) >1){
				for(int i = 1;i<=10;i++) {
					String str = document.select("#s_wrapper > section > div.s_right > article > div.bbs_skin > ul > li:nth-child("+i+") > a > em").text().trim();

					// 기본정보 불러오기
					arr[index][0] = "울진군";
					arr[index][1] = document.select("#s_wrapper > section > div.s_right > article > div.bbs_skin > ul > li:nth-child("+i+") > a > strong").text().trim();
					arr[index][2] = "";
					arr[index][3] = str.substring(str.indexOf("게")+5,str.length()).replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = str.substring(0,str.indexOf("부")-1);
					index++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//울릉군
		System.out.println("울릉군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.ulleung.go.kr/ko/page.do?mnu_uid=571&srchColumn=title&srchKwd=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "1DAAAA5557D183C4BAFC888E548FF2D3.tomcat01")
					.header("Referer", "https://www.ulleung.go.kr/ko/page.do?mnu_uid=571&boardType=notice")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div.boardWrap > div > div.tbl_area > table > tbody > tr:nth-child("+i+") > td.subject.taL > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "울릉군";
				arr[index][1] = document.select("#content > div.boardWrap > div > div.tbl_area > table > tbody > tr:nth-child("+i+") > td.subject.taL > a").text().trim();
				arr[index][2] = document.select("#content > div.boardWrap > div > div.tbl_area > table > tbody > tr:nth-child("+i+") > td.writer").text().trim();
				arr[index][3] = document.select("#content > div.boardWrap > div > div.tbl_area > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		
		return arr;
	}
	
	public String [][] GwangyeokCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 크롤링 시작
		// 서울특별시
		System.out.println("서울특별시 크롤링 시작");

		try {
			url = "https://www.seoul.go.kr/news/news_notice.do#list/1/cntPerPage=20&srchText=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D";
			driver.get(url);
				for(int i = 1;i<=10;i++) {
					// 데이터 있는지 체크
					if(driver.findElement(By.cssSelector("#seoul-integrated-board > table > tbody > tr:nth-child("+i+") > td.sib-lst-type-basic-subject > a")).getText().equals("")) {
						break;
					}
					// 기본정보 불러오기
					arr[index][0] = "서울특별시";
					arr[index][1] = driver.findElement(By.cssSelector("#seoul-integrated-board > table > tbody > tr:nth-child("+i+") > td.sib-lst-type-basic-subject > a")).getText(); // 제목
					arr[index][2] = driver.findElement(By.cssSelector("#seoul-integrated-board > table > tbody > tr:nth-child("+i+") > td:nth-child(4)")).getText(); // 부서
					arr[index][3] = driver.findElement(By.cssSelector("#seoul-integrated-board > table > tbody > tr:nth-child("+i+") > td:nth-child(5)")).getText().replaceAll("(\\/|\\.|\\-)", "").trim(); // 등록일자
					arr[index][4] = "";
					index++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		

		// 인천광역시
		System.out.println("인천광역시 크롤링 시작");
		try{
			response = Jsoup.connect("http://announce.incheon.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=ic")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("xloc", "1368X912")
					.cookie("_ga", "GA1.3.1704324592.1688052912")
					.cookie("_ga_4FF1G12ZJD", "GS1.3.1688052912.1.1.1688053961.0.0.0")
					.cookie("_ga_QSC5HL7RD2", "GS1.3.1691763341.1.0.1691763341.0.0.0")
					.cookie("_gid", "GA1.3.813458549.1693827103")
					.cookie("WMONID", "MxHGZWKzQ6w")
					.cookie("ICSESSIONID", "k1Ag1LEcq1bWw746JkMV0yx0qW4JNmiYFadpSejUO0ZR19Oofr4B!-2060130034!1694499223!7012!-1")
					.cookie("_ga_F7KMB3ZTZQ", "GS1.3.1693831798.5.0.1693831798.0.0.0")
					.header("Origin", "http://announce.incheon.go.kr")
					.header("Referer", "http://announce.incheon.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=ic")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=9d80e93b237329e393317acb9db0da4a&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BD%C7%BD%C3%B0%E8%C8%B9")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "인천광역시";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		// 대전광역시
		System.out.println("대전광역시 크롤링 시작");
		try{
			response = Jsoup.connect("https://www.daejeon.go.kr/drh/drhGosiList.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "QBJ9X43zNo85YoamqZ9agS46hqfVsJT5ctUK0qNswlHIPhG1x0bgHHh5yGZpKHqH.ZGFlamVvbi9IT01FUEFHRV8y")
					.cookie("JSESSIONID", "QBJ9X43zNo85YoamqZ9agS46hqfVsJT5ctUK0qNswlHIPhG1x0bgHHh5yGZpKHqH.ZGFlamVvbi9IT01FUEFHRV8y")
					.header("Origin", "https://www.daejeon.go.kr")
					.header("Referer", "https://www.daejeon.go.kr/drh/drhGosiList.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&menuSeq=1907&sno=&conifmstdt=&conifmenddt=&gosigbn=N&conannounceno=&deptnm=&title=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "대전광역시";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 대구광역시
		System.out.println("대구광역시 크롤링 시작");
		try{
			response = Jsoup.connect("http://sido.daegu.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("DJSESSIONID", "k1HrVdt9I2Iud9ss2AGmLqT8ooP2KbBF7Akb1DCMV4IIHi61WmtR!-1463535756!1679757185!7012!-1")
					.header("Origin", "http://sido.daegu.go.kr")
					.header("Referer", "http://sido.daegu.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=cf7e35572f974afc1efc216779d99c8a&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BD%C7%BD%C3%B0%E8%C8%B9")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "대구광역시";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 부산광역시
		System.out.println("부산광역시 크롤링 시작");
		try{
			response = Jsoup.connect("https://www.busan.go.kr/nbgosi/list?conIfmStdt=&conIfmEnddt=&conGosiGbn=&schKeyType=A&srchText=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "mSf9ZsAHaan")
					.cookie("_ga", "GA1.3.1089900939.1691418271")
					.cookie("SCSSOAUTH", "")
					.cookie("_gid", "GA1.3.1330014142.1693829238")
					.cookie("BSJSESSIONID", "B7dvNcu6U483sZnz2bBL9PJ1JyFcjT5kcYsSaaZq2dbZfMQzxgHP7SKGlK0FXsWD.QlNXQVMwMS9NYWluMTU")
					.cookie("_EXEN", "1")
					.cookie("_ga_7P3LX98G56", "GS1.3.1693831802.3.0.1693831802.0.0.0")
					.header("Referer", "https://www.busan.go.kr/nbgosi/list?conIfmStdt=&conIfmEnddt=&conGosiGbn=&schKeyType=A&srchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > div.content > table > tbody > tr:nth-child("+i+") > td.pc_Y.ta_Y.mo_Y.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "부산광역시";
				arr[index][1] = document.select("#contents > div.content > table > tbody > tr:nth-child("+i+") > td.pc_Y.ta_Y.mo_Y.title > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#contents > div.content > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		// 광주광역시
		System.out.println("광주광역시 크롤링 시작");
		try{
			response = Jsoup.connect("https://sido.gwangju.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "DKWvn_4SDGj")
					.cookie("1JSESSIONID1", "k1JbPNU1UkWLMat36PcThemF87xMf3Sarsje2yHomAsVDQHCD64i!1603290613!1711347773!7012!-1")
					.header("Origin", "https://sido.gwangju.go.kr")
					.header("Referer", "https://sido.gwangju.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=ac3224117100dd345fdcae6c7cb3794a&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=20220101&conIfmEnddt=&conIfmEnddt_Date=20231231&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BD%C7%BD%C3%B0%E8%C8%B9")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "광주광역시";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		// 울산광역시
		System.out.println("울산광역시 크롤링 시작");
		try{
			response = Jsoup.connect("https://www.ulsan.go.kr/u/rep/transfer/notice/list.ulsan?mId=001004002000000000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "Qu8AsD1oyG0STJMaSc0qPk3jgegSHwircsDaGr4E3U5TNCUjxBee2v7izkDPfJFh.UFVMU0FOL05ld1BvcnRhbDEtMTE")
					.cookie("WMONID", "sOtDUye341j")
					.cookie("storyCmsLogVerification-u-SID_000001", "www.ulsan.go.kr|125.179.129.136|20230904")
					.header("Origin", "https://www.ulsan.go.kr")
					.header("Referer", "https://www.ulsan.go.kr/u/rep/transfer/notice/list.ulsan?mId=001004002000000000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("srchGubun=&srchType=srchSj&srchWord=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents_inner > div.content_box > table > tbody > tr:nth-child("+i+") > td.gosi > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "울산광역시";
				arr[index][1] = document.select("#contents_inner > div.content_box > table > tbody > tr:nth-child("+i+") > td.gosi > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#contents_inner > div.content_box > table > tbody > tr:nth-child("+i+") > td:nth-child(6)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		// 세종시
		System.out.println("세종시 크롤링 시작");
		try{
			response = Jsoup.connect("https://www.sejong.go.kr/prog/publicNotice/kor/sub02_0303/C1/list.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("elevisor_for_j2ee_uid", "c1njynv7gk3gw")
					.cookie("JSESSIONID", "B65ED2F43122994A1821807150064626.portal1")
					.cookie("_harry_ref", "https%3A//cafe.naver.com/ca-fe/cafes/30698943/articles/789%3Fmenuid%3D%26boardtype%3DL%26page%3D1%26specialmenutype%3D%26userDisplay%3D15%26oldPath%3D%252FArticleRead.nhn%253Fclubid%253D30698943%2526menuid%253D%2526boardtype%253DL%2526page%253D1%2526specialmenutype%253D%2526userDisplay%253D15%2526articleid%253D789")
					.cookie("_harry_url", "https%3A//www.sejong.go.kr/prog/publicNotice/kor/sub02_0303/C1/list.do")
					.cookie("_harry_fid", "hh-688537172")
					.cookie("_harry_hsid", "A230904212803851205")
					.cookie("_harry_dsid", "A230904212803852405")
					.cookie("XTVID", "A230904212803852209")
					.cookie("XTSID", "A230904212803852853")
					.cookie("xloc", "1368X912")
					.cookie("_harry_lang", "ko-KR")
					.header("Origin", "https://www.sejong.go.kr")
					.header("Referer", "https://www.sejong.go.kr/prog/publicNotice/kor/sub02_0303/C1/list.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageUnit=10&pageSize=10&pageIndex=1&gubun=C1&searchStartDate=&searchEndDate=&searchCondition=not_ancmt_sj&searchKeyword=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&submitTy=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td.text-left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "세종시";
				arr[index][1] = document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td.text-left > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#txt > div.table-responsive.table-pc > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().substring(0, 10).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		return arr;
	}
	
	public String [][] JejuCrawling() {
		String[][] arr = new String[1000][5];
		String url, totNum;
		int index=1;
		org.jsoup.nodes.Document document;
		Response response; 
		
		// 제목 행 설정
		arr[0][0] = "기관명";
		arr[0][1] = "제목";
		arr[0][2] = "부서";
		arr[0][3] = "등록일자";
		arr[0][4] = "고시번호";
		
		// 크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		// 아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 크롤링 시작
		// 제주도
		System.out.println("제주도 크롤링 시작");

		try{
			response = Jsoup.connect("http://sido.jeju.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("PCID", "16938308802592724055908")
					.cookie("RC_RESOLUTION", "1368*912")
					.cookie("RC_COLOR", "24")
					.cookie("JJSESSIONID", "k1OeMqUPx32Wdi9de03Sg5ANMVAgHx2Dpw3Lb4uiTxTJhJR1EKQ8!285985174!1895825930!7012!-1")
					.cookie("_ga", "GA1.3.531714395.1693830881")
					.cookie("_gid", "GA1.3.826057770.1693830881")
					.header("Origin", "http://sido.jeju.go.kr")
					.header("Referer", "http://sido.jeju.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=c7dc73e0bed6a7356ca76e43701bdd30&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BD%C7%BD%C3%B0%E8%C8%B9")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "제주도";
				arr[index][1] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		// 제주시
		System.out.println("제주시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.jejusi.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "k1PQBVwmz2BQz6MqJprJMQpn61PL2JmZ2X0GLQHv41jpLnssJBRx!-454789422!1895891506!13003!-1")
					.header("Origin", "https://eminwon.jejusi.go.kr")
					.header("Referer", "https://eminwon.jejusi.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("body > div.board > div.board-list-default > table > tbody > tr:nth-child("+i+") > td.align_left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "제주시";
				arr[index][1] = document.select("body > div.board > div.board-list-default > table > tbody > tr:nth-child("+i+") > td.align_left > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > div.board > div.board-list-default > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
				
		// 서귀포시
		System.out.println("서귀포시 크롤링 시작");

		try{
			response = Jsoup.connect("https://eminwon.seogwipo.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS", "k1Qp1ddkt43r1M0JNrWZbNVM8KN94Qv2PTgB10q8yJrLWxtmtv2H!-460621106!1896088244!13003!-1")
					.header("Origin", "https://eminwon.seogwipo.go.kr")
					.header("Referer", "https://eminwon.seogwipo.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EC%8B%A4%EC%8B%9C%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+i*2+") > td:nth-child(3) > p > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "서귀포시";
				arr[index][1] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+i*2+") > td:nth-child(3) > p > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("body > form > table:nth-child(32) > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child("+i*2+") > td:nth-child(5) > a").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		driver.quit();
		// 크롬드라이버 task kill
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
		}
		catch(IOException e){
			System.out.println("Failed to close one or more driver exe files");
		}
		return arr;
	}
}
