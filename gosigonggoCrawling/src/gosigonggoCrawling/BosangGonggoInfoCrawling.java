package gosigonggoCrawling;

import java.io.IOException;
import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BosangGonggoInfoCrawling {
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
		url = "https://www.gg.go.kr/bbs/board.do?bsIdx=469&menuId=1547#page=1#keyfield=SUBJECT#keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D";
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
			
		url="https://www.gp.go.kr/portal/selectGosiList.do?key=2148&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=0&y=0";

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
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS",
							"kzhTRBy6z3khcmnhDVnlt530Pytv6hYSxbY14JdwhgnZp2qYg0TR!620421748!1762263378!13003!-1")
					.cookie("_ga", "GA1.3.1410285414.1689510292").cookie("_gid", "GA1.3.1101803159.1689510292")
					.cookie("_ga_FNSBYYTP80", "GS1.3.1689510292.1.0.1689510292.0.0.0")
					.header("Origin", "https://eminwon.goyang.go.kr")
					.header("Referer", "https://eminwon.goyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&not_ancmt_reg_no=&yyyy=&epcCheck=Y&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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
			response = Jsoup.connect("https://www.gccity.go.kr/portal/saeol/gosi/list.do?mId=0301040000").header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("ACEUCI", "1").cookie("WMONID", "soejTwawrRR")
					.cookie("RSN_JSESSIONID", "aaaof8vztloXW5rhZMdLyv38Whdp6ysdXXq7rOBGvLdEV09bF3n3Vf2ED2N0")
					.cookie("ACEFCID", "UID-64B3E749A3CFCE0994A17605")
					.cookie("D_VISITOR_ID", "a3a27a07-c888-484e-f4b2-4c9e9b640082")
					.cookie("AUFAI1A38280254709", "1689511754883133160|2|1689511754883133160|1|1689511754928533160")
					.cookie("ACEUACS", "1689511754928533160")
					.cookie("ASAI1A38280254709",
							"1689511753260360167%7C1689511762164567232%7C1689511753260360167%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("AUAI1A38280254709",
							"1689511753260360167%7C2%7C1689511754883133160%7C1%7C1689511754928533160%7C1")
					.cookie("ARAI1A38280254709",
							"httpswwwgccitygokrportalsaeolgosilistdomId0301040000httpswwwgccitygokrportalsaeolgosilistdomId0301040000")
					.header("Origin", "https://www.gccity.go.kr")
					.header("Referer", "https://www.gccity.go.kr/portal/saeol/gosi/list.do?mId=0301040000")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"page=1&seCode=01&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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
			response = Jsoup.connect(
					"https://www.gm.go.kr/pt/user/nftcBbs/BD_selectNftcBbsList.do?q_nftcBbsCode=1001&q_nftcBbsMgtno=&q_searchKeyTy=&q_searchVal=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&q_rowPerPage=&q_currPage=1&q_sortName=&q_sortOrder=")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("anlzUserId", "172.27.0.23|1689512182372").cookie("anlzFirstVisitYn", "N")
					.cookie("k8WEeAP3eGANxEMZnI3krM2k.pt21", "2").cookie("anlzFirstVisitHourYn", "N")
					.cookie("anlzFirstVisitDateYn", "N").cookie("anlzFirstVisitWeekYn", "N")
					.cookie("anlzFirstVisitMonthYn", "N").cookie("anlzLastVisitDt", "1689512182380")
					.cookie("JSESSIONID", "k8WEeAP3eGANxEMZnI3krM2k.pt21")
					.cookie("_ga_SRVNMB3FT7", "GS1.1.1689512182.1.0.1689512182.0.0.0")
					.cookie("_ga", "GA1.1.1675496954.1689512182")
					.header("Referer", "https://www.gm.go.kr/pt/user/nftcBbs/BD_selectNftcBbsList.do?q_nftcBbsCode=1001")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

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
			response = Jsoup.connect("https://www.gjcity.go.kr/portal/saeol/gosi/list.do?mId=0202010000").header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "z2QeD5D6NQS").cookie("JSESSIONID", "7E34CF45DEFAC3EB725AF9AB8472492A.web_tomcat11")
					.header("Origin", "https://www.gjcity.go.kr")
					.header("Referer", "https://www.gjcity.go.kr/portal/saeol/gosi/list.do?mId=0202010000")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"page=1&seCode=01&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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
		System.out.println("구리시 크롤링 시작");url="https://www.guri.go.kr/www/selectBbsNttList.do?key=380&bbsNo=36&searchCtgry=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D";
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

		// 김포시
		System.out.println("김포시 크롤링 시작");
		try
		{
			response = Jsoup.connect("https://www.gimpo.go.kr/portal/ntfcPblancList.do?key=1004").header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "sRyhAY0NaTb").cookie("wcCookie", "125.179.129.136_T_87796_WC")
					.cookie("JSESSIONID",
							"pKrNianU2JTuTwvWuUwNszpaj47mq2yodeBNa4PDaa40cj7ZxqNQSOzIGPIcBY1j.new-gimpo-was2_servlet_engine1")
					.header("Origin", "https://www.gimpo.go.kr")
					.header("Referer", "https://www.gimpo.go.kr/portal/ntfcPblancList.do?key=1004")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("cate_cd=1&searchCnd=not_ancmt_sj&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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

		//////////// 남양주시////////////////////

		System.out.println("남양주시 크롤링 시작");
		try {
			
		url="https://www.nyj.go.kr/main/185?space=main_board_gonggo&page_size=10&search_field=TiCoEdFl&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D";

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
			response = Jsoup.connect(
					"https://www.ddc.go.kr/ddc/selectGosiList.do?key=340&not_ancmt_se_code=04&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("JSESSIONID", "C81C390820E7569501DA2FFE78A1EB53")
					.header("Referer",
							"https://www.ddc.go.kr/ddc/selectGosiList.do?key=340&not_ancmt_se_code=04&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

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
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("ccsession", "202307182213256fc8a8c05f076fb1")
					.cookie("SESSION_NTIS",
							"k2P2G9XqdhcLLYw9j2GNhtxQ0vz7BR702rkJFKJp1wvjgXwrCtvh!-215633713!1761935686!13003!-1")
					.cookie("_ga", "GA1.1.239618990.1689686008")
					.cookie("JESS6",
							"LLk8Ej51vUovkqLIerU8SdXUXxW5rOF6BYhzay7VWXIV5A1QxLYaqoniV8yZ3tYb.p2_was2_servlet_engine1")
					.cookie("_ga_PYW75DBHQ7", "GS1.1.1689686008.1.1.1689686032.0.0.0")
					.header("Origin", "https://eminwon.bucheon.go.kr")
					.header("Referer", "https://eminwon.bucheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&epcCheck=Y&yyyy=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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
			response = Jsoup.connect(
					"https://www.seongnam.go.kr/city/1000052/30001/bbsList.do?currentPage=1&idx=&searchCategory=&searchSelect=title&searchWord=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("WMONID", "5i6G9-Mbhdk").cookie("JSESSIONID", "aaavqkOlanSYc4Q3q5nLy")
					.cookie("PCID", "16896862088338337087747").cookie("RC_RESOLUTION", "1368*912").cookie("RC_COLOR", "24")
					.cookie("_gid", "GA1.3.75557279.1689686214")
					.cookie("_ga_8VX371YTYQ", "GS1.1.1689686214.1.1.1689686324.0.0.0")
					.cookie("_ga", "GA1.1.1440632061.1689686214")
					.cookie("_pk_id.seongnam",
							"cae73517-3569-fe35-1f97-689686209178.1689686209178.82f791a2-d348-47c0-616f-689686209178.1689686209178.1689686324404.9")
					.header("Referer",
							"https://www.seongnam.go.kr/city/1000052/30001/bbsList.do?currentPage=1&idx=&searchCategory=&searchSelect=title&searchWord=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

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
					.cookie("SWSESSID", "VyMOZU6aAnt9aXxcXG7gflhohdB9JMZamMm0O9zsYT1Lm3ChxDCjJEpQGwjQOYx0.WAS2_servlet_engine1")
					.cookie("SESSION_NTIS", "k7RymVM1FV7S0RqvJyrT5FfKGvZV1bL0xhCwGgVzyNyVqHdG1cY2!69574837!1761673768!13002!-1")
					.cookie("_ga_LYP27H7VGF", "GS1.1.1690014069.4.0.1690014069.0.0.0")
					.header("Origin", "https://eminwon.suwon.go.kr")
					.header("Referer", "https://eminwon.suwon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&epcCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&cgg_code=&chk_call=N&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.siheung.go.kr/main/saeol/gosi/list.do?mId=0401040100").header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "-PfrHGlk0yE")
					.cookie("JSESSIONID", "caamlZV5yCQ968VYNxiLyrRiloh8li_hmxyXwg_zrB4CrNGfflUP-4ZnmYZE")
					.cookie("D_VISITOR_ID", "ae56bd1e-4562-769a-9c43-6a328679470d")
					.cookie("wcs_bt", "80b6f2fa317ca8:1689688774").header("Origin", "https://www.siheung.go.kr")
					.header("Referer", "https://www.siheung.go.kr/main/saeol/gosi/list.do?mId=0401040100")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"page=1&seCode=01&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();
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
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=2020&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&key=%24%7Bbefore_key%7D&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			document = response.parse();

			Thread.sleep(10000);
			
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
			response = Jsoup.connect("https://www.anseong.go.kr/portal/saeol/gosiList.do?mId=0401040000").header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "GT45TXw5I6q")
					.cookie("RSN_JSESSIONID", "caakfRz9SR8HWhd36DTLy6ACCoF4g6GbKRqiBZ_kZM-7FgQdaY2r6_fpa0b1")
					.header("Origin", "https://www.anseong.go.kr")
					.header("Referer", "https://www.anseong.go.kr/portal/saeol/gosiList.do?mId=0401040000")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=NOT_ANCMT_SJ&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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
			response = Jsoup.connect(
					"https://www.anyang.go.kr/main/emwsWebList.do?key=4101&searchGosiSe=01%2C03%2C04&pageUnit=10&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("JSESSIONID",
							"EIxfQybOXGI5kEurS2zNVNlfXDnhatI3fm5SzaLjflrGdV1BxfFzT0ts8HTvyzB7.amV1c19kb21haW4vTmVvQ01T")
					.cookie("_gid", "GA1.3.862078023.1689949003").cookie("_gat_gtag_UA_153075621_1", "1")
					.cookie("wcCookie", "1cb40cd0c02cc74e9938c7dbb8714d33fd13ae8bf2d670d6348ec02f75e4404b")
					.cookie("_ga_WPM2XNH5WL", "GS1.1.1689949002.1.0.1689949005.0.0.0")
					.cookie("_ga_7J38ZPMHG2", "GS1.1.1689949002.1.1.1689949011.0.0.0")
					.cookie("_ga", "GA1.3.1740906434.1689949003")
					.header("Referer",
							"https://www.anyang.go.kr/main/emwsWebList.do?key=4101&searchGosiSe=01%2C03%2C04&pageUnit=10&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

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

		/////////// 양주시////////////////////

		System.out.println("양주시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://eminwon.yangju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS",
							"k6dPBhyp1Zm7yjB912zLyW4WXwT2QQR1K32K2VTvR2xZwnTT8W2G!1709286316!1763082519!13002!-1")
					.cookie("_gid", "GA1.3.1799001018.1689951697")
					.cookie("_ga_VEJRX5E3FB", "GS1.1.1689951697.2.1.1689951741.0.0.0")
					.cookie("_ga", "GA1.1.465035938.1684505325").header("Origin", "https://eminwon.yangju.go.kr")
					.header("Referer", "https://eminwon.yangju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();
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

		/////////// 양평군////////////////////

		System.out.println("양평군 크롤링 시작");

		try
		{
			response = Jsoup.connect(
					"https://www.yp21.go.kr/www/selectBbsNttList.do?key=1119&bbsNo=5&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("YP_JSESSIONID", "72D353B2CDC98B09E8DE31FE56A7A9F5.ypwas1")
					.header("Referer",
							"https://www.yp21.go.kr/www/selectBbsNttList.do?key=1119&bbsNo=5&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

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
			response = Jsoup.connect(
					"https://www.yeoju.go.kr/www/jsp/project/gosi/list.jsp?thisPage=1&menuIdx=602&bbsIdx=&searchField=1&searchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("JSESSIONID",
							"NQaaGp90g8dDoEqS7xWd8ELNH5W6lvJDfcMEc3jf7nFar6sOxaDKowaTv8LiraKr.amV1c19kb21haW4vaG9tZXBhZ2Vfd3d3")
					.cookie("_ga", "GA1.3.833093444.1689952856").cookie("_gid", "GA1.3.1366243004.1689952856")
					.cookie("_gat", "1").cookie("_ga_QY4KYM331N", "GS1.3.1689952857.1.1.1689952862.0.0.0")
					.header("Referer",
							"https://www.yeoju.go.kr/www/jsp/project/gosi/list.jsp?thisPage=1&menuIdx=602&bbsIdx=&searchField=1&searchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

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
			response = Jsoup.connect(
					"https://www.yeoncheon.go.kr/www/selectGosiList.do?key=3393&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Connection", "keep-alive")
					.cookie("JSESSIONID",
							"C9Kqds4pfuL9NhacbZaDbKNnh29odP4Ff06qUz2zc6mW2ZxCx46S03vr0VNFkyJ9.amV1c19kb21haW4vaG9tZVBhZ2U")
					.cookie("_pk_id.yeoncheon",
							"f5848d28-ce94-9fc5-2810-689953139541.1689953139541.d24c6c2c-c8ba-a13c-fb49-689953139541.1689953139541.1689953142874.1")
					.header("Referer",
							"https://www.yeoncheon.go.kr/www/selectGosiList.do?key=3393&not_ancmt_se_code=01&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#contents > div > table > tbody > tr > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "연천군";
				arr[index][1] = document.select("#contents > div > table > tbody > tr > td.subject > a").text();
				arr[index][2] = document.select("#contents > div > table > tbody > tr > td:nth-child(5)").text();
				arr[index][3] = document.select("#contents > div > table > tbody > tr > td:nth-child(3)").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#contents > div > table > tbody > tr > td:nth-child(2)").text();
				index++;
			}
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}

		/////////// 오산시////////////////////

		System.out.println("오산시 크롤링 시작");

		try
		{
			response = Jsoup.connect("https://www.osan.go.kr/portal/saeol/gosi/list.do?mId=0302010000").header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "VpBUnNom2LK")
					.cookie("JSESSIONID", "caaSzbzfHrb9We_RbOmLyc-0hDE-aZ3cTgLq51jUJZ-yTecpbyrIp6XROBpC")
					.header("Origin", "https://www.osan.go.kr")
					.header("Referer", "https://www.osan.go.kr/portal/saeol/gosi/list.do?mId=0302010000")
					.header("Sec-Fetch-Dest", "document").header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin").header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0").header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody(
							"page=1&seCode=01&pbsDivision=&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

			document = response.parse();

			for (int i = 1; i <= 10; i++) {
				// 데이터 있는지 체크
				if (document.select("#list > table > tbody > tr > td.taL.list_tit > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "오산시";
				arr[index][1] = document.select("#list > table > tbody > tr > td.taL.list_tit > a").text();
				arr[index][2] = document.select("#list > table > tbody > tr > td.list_dept").text();
				arr[index][3] = document.select("#list > table > tbody > tr > td.list_date").text()
						.replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr > td:nth-child(2)").text();
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
			response = Jsoup.connect("http://eminwon.yongin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do").header(
					"Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive").header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("SESSION_NTIS",
							"k6mrWl6y5VvnnyGGLwv132X4DpkZShZNsnCRQdvfJllqNjbQMYvS!-910095939!1762855504!13004!-1")
					.header("Origin", "http://eminwon.yongin.go.kr")
					.header("Referer", "http://eminwon.yongin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.requestBody(
							"epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST).ignoreContentType(true).execute();

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
					.cookie("JSESSIONID", "Km8XlrkfnJkQD8Px7nAWGW1aXgBUv1oVmWzS4AILsxmkfVf7xpe7lax1HesQJUaU.amV1c19kb21haW4vc2VydmVyMjA")
					.cookie("_harry_ref", "https%3A//cafe.naver.com/ca-fe/cafes/30698943/articles/789%3Fmenuid%3D%26boardtype%3DL%26page%3D1%26specialmenutype%3D%26userDisplay%3D15%26oldPath%3D%252FArticleRead.nhn%253Fclubid%253D30698943%2526menuid%253D%2526boardtype%253DL%2526page%253D1%2526specialmenutype%253D%2526userDisplay%253D15%2526articleid%253D789")
					.cookie("_harry_url", "https%3A//www.ui4u.go.kr/portal/saeol/gosiList.do%3FseCode%3D01%26mId%3D0301040000")
					.cookie("_harry_fid", "hh-1348068694")
					.cookie("_harry_hsid", "A230722135947467304")
					.cookie("_harry_dsid", "A230722135947467014")
					.cookie("XTVID", "A230722135947468578")
					.cookie("XTSID", "A230722135947468486")
					.cookie("xloc", "1368X912")
					.cookie("_harry_lang", "ko-KR")
					.header("Origin", "https://www.ui4u.go.kr")
					.header("Referer", "https://www.ui4u.go.kr/portal/saeol/gosiList.do?seCode=01&mId=0301040000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=not_ancmt_sj&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "kJG1h59fxgzpvgJNlKh8hydTmSKVVzF7MZQnn8vXVNRy1xNy7F1W!-1109327468!1762722128!13003!-1")
					.cookie("_gid", "GA1.3.1009972050.1690895948")
					.cookie("_gat", "1")
					.cookie("_ga_XP56S75PP1", "GS1.3.1690895948.5.1.1690895960.0.0.0")
					.header("Origin", "https://eminwon.uiwang.go.kr")
					.header("Referer", "https://eminwon.uiwang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "eYmcewQaNZj4ltr0zRZVQeyMDMWoJJSxmOwbGaOorXZLLS67xwaqUk1SaXQyusUo.amV1c19kb21haW4vTkVPQ01T")
					.header("Origin", "https://www.icheon.go.kr")
					.header("Referer", "https://www.icheon.go.kr/portal/selectNoticeWebList.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("key=1606&searchCnd=ALL&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "baaLCZQVjkbXk-wWHBTLyp_SnqArNz09O4SeNZEB1egbbd3FjG1DeeiIK4rJ")
					.cookie("_pk_id.pajucity", "5828a6fe-0bfd-5957-8212-688885843547.1688885843547.139b151f-cc0e-b073-c5bc-690003407084.1690003407084.1690003407084.1")
					.header("Origin", "https://www.paju.go.kr")
					.header("Referer", "https://www.paju.go.kr/user/board/BD_board.list.do?bbsCd=1022&q_ctgCd=4063")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("seq=&bbsCd=1022&q_ctgCd=4063&q_parentCtgCd=&pageType=&showSummaryYn=N&delDesc=&q_searchKeyType=&q_searchVal=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&q_startDt=&q_endDt=&q_currPage=1&q_sortName=&q_sortOrder=&q_rowPerPage=10")
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
				response = Jsoup.connect("https://www.pocheon.go.kr/www/selectEminwonList.do?key=12563&notAncmtSeCode=01&searchCnd=notAncmtSj&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "Gel8ICsfVgza5qht2kit-oUSMqvaYpGgHmxom5gB9myjM_c1kqmslvesyrcHSDEnDdM2lze-GSfF8rDcL5Bet7gpTPadxVPQeAAKNvYIHG1J7KrqcPVRlI2f4JDAkqKT!-1369755998")
					.header("Referer", "https://www.pocheon.go.kr/www/selectEminwonList.do?key=12563&notAncmtSeCode=01&searchCnd=notAncmtSj&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("HOME_JSESSIONID", "iJOAgzaQncRvBqPwLBrNDkTMMxCLwJcam924IDttxxRyKRJnxSiSB9M4IcRhXVp7.amV1c19kb21haW4vaG9tZXBhZ2VfMw")
					.cookie("SEARCH_JSESSIONID", "4Tai0aXz1TaME82pLQJrAOWUVKWaVjPSmuZSlQBAPZn7EtPyxKgSXmDdNX86iWpD.amV1c19kb21haW4vc2VhcmNoXzM")
					.header("Origin", "https://www.pyeongtaek.go.kr")
					.header("Referer", "https://www.pyeongtaek.go.kr/pyeongtaek/saeol/gosiList.do?seCode=01&mId=0401020000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=NOT_ANCMT_SJ&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
				response = Jsoup.connect("https://www.hanam.go.kr/www/selectGosiList.do?key=171&not_ancmt_se_code=01%2C04&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Connection", "keep-alive")
						.cookie("WMONID", "mZDSqxTeUmz")
						.cookie("JSESSIONID", "5F24E972E5893A3CB4FE93B74B61F6E5.was1")
						.header("Referer", "https://www.hanam.go.kr/www/selectGosiList.do?key=171&not_ancmt_se_code=01%2C04&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
						.header("Sec-Fetch-Dest", "document")
						.header("Sec-Fetch-Mode", "navigate")
						.header("Sec-Fetch-Site", "same-origin")
						.header("Sec-Fetch-User", "?1")
						.header("Upgrade-Insecure-Requests", "1")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
						.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
			
			
			
			///////////화성시////////////////////
			
			System.out.println("화성시 크롤링 시작");

			try{
				response = Jsoup.connect("https://eminwon.hscity.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Cache-Control", "max-age=0")
						.header("Connection", "keep-alive")
						.header("Content-Type", "application/x-www-form-urlencoded")
						.cookie("SESSION_NTIS", "k72yvd27y7J1PTy5mJ1SthHdH0PtNGKfP0TSSpnR7TxWZ1CX2lQJ!117000700!1763180880!13002!-1::UNB507FE0A")
						.header("Origin", "https://eminwon.hscity.go.kr")
						.header("Referer", "https://eminwon.hscity.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
						.header("Sec-Fetch-Dest", "iframe")
						.header("Sec-Fetch-Mode", "navigate")
						.header("Sec-Fetch-Site", "same-origin")
						.header("Sec-Fetch-User", "?1")
						.header("Upgrade-Insecure-Requests", "1")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
						.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
						.header("sec-ch-ua-mobile", "?0")
						.header("sec-ch-ua-platform", "\"Windows\"")
						.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&cgg_code=&not_ancmt_reg_no=&dept_nm=&yyyy=2023&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
		// 강원도
		System.out.println("강원도 크롤링 시작");

		try{
		response = Jsoup.connect("https://state.gwd.go.kr/portal/bulletin/notification")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("_ga", "GA1.3.838673666.1686474609")
				.cookie("JSESSIONID", "38FB0046C02821596EEC054461E535DF")
				.header("Origin", "https://state.gwd.go.kr")
				.header("Referer", "https://state.gwd.go.kr/portal/bulletin/notification")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mode=&firstYN=N&articleSeq=0&searchCondition=TITLE&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.gn.go.kr/www/selectGosiNttList.do?key=263&searchGosiSe=01%2C04%2C06&searchCnd=ALL&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("_ga", "GA1.3.1193484480.1688892341")
					.cookie("JSESSIONID", "Ou125QwNdRpZAjjGUaAeKWJjG4YamYaOmrj3tyAc9A7lK11txFMtSgqCkXHRTMB2.GNWEB_servlet_engine2")
					.cookie("_gid", "GA1.3.1837553527.1690016993")
					.header("Referer", "https://www.gn.go.kr/www/selectGosiNttList.do;jsessionid=Ou125QwNdRpZAjjGUaAeKWJjG4YamYaOmrj3tyAc9A7lK11txFMtSgqCkXHRTMB2.GNWEB_servlet_engine2?key=263&searchGosiSe=01%2C04%2C06&searchCnd=ALL&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("SESSION_NTIS", "k7pyG9kL5L2nXGQV2TKQ6WcpDy3gSsTNh0PLvnyGJ7T2TRPVyHTF!218410467!1779501621!13003!-1")
				.cookie("_ga_GVWCJZ6YK9", "GS1.1.1690017458.2.0.1690017458.0.0.0")
				.cookie("_ga", "GA1.3.134677767.1686499277")
				.cookie("_gid", "GA1.3.2128267421.1690017458")
				.cookie("_gat_gtag_UA_131915820_1", "1")
				.header("Origin", "https://eminwon.gwgs.go.kr")
				.header("Referer", "https://eminwon.gwgs.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&recent_mm=&not_ancmt_reg_no=&mode=listForm&boardCode=BDAABB01&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
		
		///////////동해시////////////////////
		
		System.out.println("동해시 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.dh.go.kr/www/selectBbsNttList.do?key=478&bbsNo=87&searchCtgry=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "C952B379482F9118DDC2E03725425D3C")
				.header("Referer", "https://www.dh.go.kr/www/selectBbsNttList.do?key=478&bbsNo=87&searchCtgry=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
			

		response = Jsoup.connect("https://www.samcheok.go.kr/media/00084/00095.web?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "B8989D2BB08626CF7E93CE251330D89D")
				.cookie("wcs_bt", "b319c8f12cc5e8:1690018440")
				.header("Referer", "https://www.samcheok.go.kr/media/00084/00095.web")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("SESSION_NTIS", "k7wqXTq2WTXwM3PKkhBs39w9WyxGWlxncG5r6J4nmsQbqW5YzsRK!1867195569!1778780705!13002!-1")
				.cookie("_ga_BVFB54FB0W", "GS1.1.1690022009.1.1.1690022062.0.0.0")
				.header("Origin", "https://eminwon.sokcho.go.kr")
				.header("Referer", "https://eminwon.sokcho.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&not_ancmt_reg_no=&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&searchSelect=not_ancmt_sj&searchInput=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("PHPSESSID", "5calr3cfe6b8dluopr1suqueg4")
					.cookie("wcCookie", "125.179.129.136_T_39723_WC")
					.header("Origin", "https://www.yanggu.go.kr")
					.header("Referer", "https://www.yanggu.go.kr/user_sub.php?gid=www&mu_idx=215")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("ptSignature=0j8Het%2B5AuZ%2F4U%2BmxpfdnpwV8MHwU%2FJ4zYtTpzBG98Ngw%2FPRrJ0NzRnY%2FsVDpHCK1ey7OUhqF1IZ2beG4%2BwdsJqEqloRmMKyjC36wzlfzTP1JfRJ%2Bdkfo3%2BWTU51RRmse5dcVD07WlXyucwd2TV1RhN3bLB%2FkL9Ojll5YkalZIp2RTSDaK21G38oU1jCEmI0Jq0qllUulbsXDC8mLGzHKY9iAieZeIPCfgRrY8ze9A4%3D&board_idx=17&bcd=announcement&scid=&sbcateid=&deltemplate=&onmm1=&onsm=&sbfd=boardsubject&pg=1&sbt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&bc_checkbot=Y")
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
				.cookie("SESSION_NTIS", "k77p5y38Lb8JkYjwSKcytHLGfj1B0blrNCkcSZ9Fc71nd79v204n!-1485968456!1779590217!13003!-1")
				.cookie("_gid", "GA1.3.1624878122.1690024841")
				.header("Origin", "https://eminwon.yangyang.go.kr")
				.header("Referer", "https://eminwon.yangyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
		response = Jsoup.connect("https://www.yw.go.kr/www/selectBbsNttList.do?key=273&bbsNo=17&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "4A47B8E80665E3BB985A9EE52BE25C5D")
				.cookie("wcs_bt", "ebefcf2e1878c0:1690025250")
				.header("Referer", "https://www.yw.go.kr/www/selectBbsNttList.do?key=273&bbsNo=17&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
		response = Jsoup.connect("https://www.wonju.go.kr/www/selectBbsNttList.do?key=216&bbsNo=140&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("_ga_Z2XEW1RLB3", "GS1.3.1689602372.1.1.1689602402.0.0.0")
				.cookie("JSESSIONID", "A158A87976F7BC0734E920BC9546BEF2")
				.cookie("_gid", "GA1.3.524255290.1690025493")
				.cookie("_gat_UA-202243619-1", "1")
				.cookie("wcs_bt", "e57f78405a7738:1690025515")
				.cookie("_ga_8CZPV3SN95", "GS1.1.1690025492.3.1.1690025517.35.0.0")
				.cookie("_ga_WPM2XNH5WL", "GS1.1.1690025492.3.1.1690025517.0.0.0")
				.cookie("_ga", "GA1.3.1376130970.1651595880")
				.header("Referer", "https://www.wonju.go.kr/www/selectBbsNttList.do?key=216&bbsNo=140&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("JSESSIONID", "1DFC1E07F5F9861B33ABFCFAC01D6541")
				.cookie("Sphere_JSESSIONID", "8jpJmTVFyMi3DuwQF0Efehorg4I")
				.header("Origin", "https://www.inje.go.kr")
				.header("Referer", "https://www.inje.go.kr/portal/adm/bulletin/notify")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mode=&firstYN=N&searchCondition=TITLE&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&bc_checkbot=Y")
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
				.cookie("SESSION_NTIS", "k7DkY2Gwx26bbvdpTJfTsnCq82SSYCDQhjWW5STXDDWJs4syH3p6!1412142515!1779173941!13003!-1")
				.cookie("_ga", "GA1.3.2115122549.1690026789")
				.cookie("_gid", "GA1.3.447941935.1690026789")
				.header("Origin", "https://eminwon.jeongseon.go.kr")
				.header("Referer", "https://eminwon.jeongseon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05%2C06&list_gubun=&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
		response = Jsoup.connect("https://www.cwg.go.kr/www/selectBbsNttList.do?key=1226&bbsNo=25&searchCtgry2=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.cookie("CWG_JSESSIONID", "171CA93296DA6D82B1C9DBE08DC80A83")
				.header("Referer", "https://www.cwg.go.kr/www/selectBbsNttList.do?key=1226&bbsNo=25&searchCtgry2=&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
		response = Jsoup.connect("https://www.chuncheon.go.kr/cityhall/administrative-info/notice-info/notice-announcement/?pageIndex=1&searchCnd=SJ&searchWrd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "7CE690D737601D5810262A9C99921352.w1tomcat1")
				.cookie("_gid", "GA1.3.1591180831.1690078756")
				.cookie("_dc_gtm_UA-132991296-3", "1")
				.cookie("_gat_UA-132991296-3", "1")
				.cookie("person1", "%EA%B3%A0%EC%8B%9C%2F%EA%B3%B5%EA%B3%A0@@/cityhall/administrative-info/notice-info/notice-announcement/")
				.cookie("_ga_KZJ1WSGLT6", "GS1.1.1690078756.1.1.1690078762.54.0.0")
				.cookie("_ga", "GA1.3.2122775920.1690078756")
				.header("Referer", "https://www.chuncheon.go.kr/cityhall/administrative-info/notice-info/notice-announcement/")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
		response = Jsoup.connect("https://www.taebaek.go.kr/www/selectBbsNttList.do?key=352&bbsNo=25&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "F7C013AB536361650C5B30B04CEF3567")
				.header("Referer", "https://www.taebaek.go.kr/www/selectBbsNttList.do?bbsNo=25&key=352")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("JSESSIONID", "759080785A5265AAC007F55BF20CEA28")
				.cookie("_ga", "GA1.3.641002053.1690079151")
				.cookie("_gid", "GA1.3.1324027890.1690079151")
				.header("Origin", "https://www.pc.go.kr")
				.header("Referer", "https://www.pc.go.kr/portal/government/government-notification")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mode=&gubun=&searchCondition=TITLE&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
		response = Jsoup.connect("https://www.hongcheon.go.kr/www/selectEminwonList.do?key=278&pageUnit=10&ofr_pageSize=10&searchCnd=B_Subject&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("_ga", "GA1.3.262137352.1688890786")
				.cookie("JSESSIONID", "E818F674E95D8612175157A7CE84E1A9")
				.header("Referer", "https://www.hongcheon.go.kr/www/selectEminwonList.do?key=278")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("SESSION_NTIS", "k8RLggQWgmvpgJWJZJMhQRS2mwDX5Cb0pFYBg4GQQN111VGvpbDK!-585209491!1779302729!13002!-1")
				.header("Origin", "http://eminwon.ihc.go.kr")
				.header("Referer", "http://eminwon.ihc.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.hsg.go.kr/www/selectBbsNttList.do?key=821&bbsNo=65&pageUnit=10&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Connection", "keep-alive")
				.cookie("JSESSIONID", "EB4DDBF24B4C46616B27600D18C2AAAB")
				.header("Referer", "https://www.hsg.go.kr/www/selectBbsNttList.do?bbsNo=65&key=821&")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("MAINLOGCK", "MAINLOG")
				.cookie("WMONID", "53QRJOTDDVz")
				.cookie("ACEFCID", "UID-64884FD812EF378288EAF333")
				.cookie("ACEUACS", "1662280749848526731")
				.cookie("MAINLOGCK", "MAINLOG")
				.cookie("__utmc", "251505620")
				.cookie("AUFAM3A33173330568", "1688050800000000000|5|1690079935668548322|1|1662280749848526731")
				.cookie("JSESSIONID", "B3AOHOY4GrRQVwO34C1IL9PoXkgaafXtFJHKLovV1HmXBCXFxnzxfWJGGbMOLmFz.Y253YXMvY2h1bmduYW00")
				.cookie("__utma", "251505620.1130072441.1686654936.1690079932.1690115705.4")
				.cookie("__utmz", "251505620.1690115705.4.4.utmcsr")
				.cookie("__utmt", "1")
				.cookie("__utmb", "251505620.1.10.1690115705")
				.cookie("ASAM3A33173330568", "1690115704712542556%7C1690115704712542556%7C1690115704712542556%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
				.cookie("AUAM3A33173330568", "1690115704712542556%7C6%7C1686654933403533160%7C1%7C1662280749848526731%7C1")
				.cookie("ARAM3A33173330568", "httpwwwchungnamgokrcnnetboarddomnucdCNNMENU02364httpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
				.header("Origin", "http://www.chungnam.go.kr")
				.header("Referer", "http://www.chungnam.go.kr/cnnet/board.do?mnu_cd=CNNMENU02364")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.requestBody("searchCnd_4=&srtdate=2018-01-01&enddate=2023-07-23&searchCnd=0&searchWrd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&showSplitNo=10")
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
			response = Jsoup.connect("https://www.gyeryong.go.kr/kr/html/sub03/030102.html?skey=sj&sval=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "vfhf1f4kflboh4tgdk5vnbglie")
					.cookie("_ga_PNJVVZGNCN", "GS1.1.1690080125.1.0.1690080125.0.0.0")
					.cookie("_ga", "GA1.3.203680485.1690080125")
					.cookie("_gid", "GA1.3.1137321851.1690080126")
					.cookie("_gat_gtag_UA_78355006_1", "1")
					.header("Referer", "https://www.gyeryong.go.kr/kr/html/sub03/030102.html")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("JSESSIONID", "QQTFeu9iQHS15RvjqC5wMa8rtwxA290YhjZKdglPzRTOlAoFxSCe83uWPb0R2SXQ.V0FTMjAyMC9namhvbWVwYWdl")
				.cookie("_ga_C4L4YMP0GB", "GS1.1.1690080348.1.0.1690080348.0.0.0")
				.cookie("_ga", "GA1.3.1338365062.1690080349")
				.cookie("_gid", "GA1.3.83877290.1690080349")
				.cookie("_gat_gtag_UA_167564883_1", "1")
				.header("Origin", "https://www.gongju.go.kr")
				.header("Referer", "https://www.gongju.go.kr/prog/saeolGosi/GOSI_03/sub04_03_03/list.do")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&searchCondition=all&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&pageUnit=10")
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
					.cookie("SIDNAME", "ronty")
					.cookie("PHPSESSID", "622isb0j0e9p1pv8o8gb34nh16")
					.cookie("_ga", "GA1.1.106031688.1690080578")
					.cookie("_ga_9B2M8KEK1F", "GS1.1.1690080577.1.1.1690085052.0.0.0")
					.header("Origin", "https://www.geumsan.go.kr")
					.header("Referer", "https://www.geumsan.go.kr/kr/html/sub03/030302.html?")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("sch_post=&skey=title&sval=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C05%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&yyyy=2012&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "k8fVHs1xT6vVvn4HdJp9p85PSXnP2K8yWv7bznLsnLjNBrR2lZsJ!-1493928417!1812922375!13002!-1")
					.cookie("_gid", "GA1.3.1694759651.1690099502")
					.cookie("_gat_gtag_UA_90021735_1", "1")
					.cookie("_ga_Z4NCNFG5X1", "GS1.1.1690099502.1.0.1690099502.60.0.0")
					.cookie("_ga", "GA1.1.1030290612.1690099502")
					.header("Origin", "http://eminwon.dangjin.go.kr")
					.header("Referer", "http://eminwon.dangjin.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
				.cookie("JSESSIONID", "TidKBwo6fNYPM1hYJl0GJUYA0NCWnIYshUY2PYwvnf3faBRuxTx5RgNhfMVRQyCo.brwas2_servlet_engine1")
				.cookie("_gid", "GA1.3.640245452.1690099761")
				.cookie("_ga_F9REXX2MP6", "GS1.1.1690099761.1.1.1690099842.0.0.0")
				.cookie("_ga", "GA1.3.143759748.1690099761")
				.header("Origin", "https://brcn.go.kr")
				.header("Referer", "https://brcn.go.kr/prog/eminwon/kor/BB/sub04_03_01/list.do")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=1&mno=sub01_01&searchCondition=subject&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&submitTy=%EA%B2%80%EC%83%89")
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
			response = Jsoup.connect("https://www.buyeo.go.kr/_prog/_board/index.php?code=news_02&site_dvs_cd=kr&menu_dvs_cd=0402&skey=title&sval=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&site_dvs=&gubun=&GotoPage=1")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.cookie("PHPSESSID", "ovr4id8t1ofo0hg7plksi3k8u1")
				.cookie("_ga", "GA1.3.1193451668.1690100051")
				.cookie("_gid", "GA1.3.552595521.1690100051")
				.cookie("_gat", "1")
				.cookie("_ga_ZVMV0WKDE7", "GS1.3.1690100051.1.1.1690100082.0.0.0")
				.header("Origin", "https://www.buyeo.go.kr")
				.header("Referer", "https://www.buyeo.go.kr/_prog/_board/index.php?code=news_02&site_dvs_cd=kr&menu_dvs_cd=0402&skey=&sval=&site_dvs=&gubun=&GotoPage=")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("skey=title&GotoPage=1&sval=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.seosan.go.kr/common/program/eminwonView.jsp?pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&mobile_code=00&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "0GBP1kyBPG8U9Ivj8RwECVIudz7BxvdYF1EFp7QrtaiBOUZzxFFRI6D4aNW01pje.SS_AP_servlet_engine1")
					.cookie("wcs_bt", "6f45ba0ffec488:1690108190")
					.header("Referer", "https://www.seosan.go.kr/common/program/eminwonView.jsp?pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=&countYn=Y&list_gubun=&not_ancmt_sj=&not_ancmt_cn=&dept_nm=&mobile_code=00&Key=B_Subject&not_ancmt_mgt_no=&temp=")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
				.cookie("_gid", "GA1.3.1464327478.1690108480")
				.cookie("_ga_JQD33623FQ", "GS1.3.1690108480.1.0.1690108480.0.0.0")
				.cookie("SESSION_NTIS", "k9CQ1vnWpGFjy04qdD6sx82lqRq4T4VSVlvv3f3zBhFLJv9L1bvn!-558956151!1812594995!13002!-1")
				.header("Origin", "http://eminwon.seocheon.go.kr")
				.header("Referer", "http://eminwon.seocheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
				.cookie("SESSION_NTIS", "k9K6yvTDCQt5tmv2tYyKyfLZh12KY512y6Wc1ydd2L109ym8pT23!1254620246!1812204082!13002!-1")
				.cookie("_ga", "GA1.3.1531223361.1690110588")
				.cookie("_gid", "GA1.3.1955123235.1690110588")
				.cookie("_gat", "1")
				.cookie("_ga_TB8YLZGNT1", "GS1.3.1690110589.1.0.1690110589.0.0.0")
				.header("Origin", "https://eminwon.asan.go.kr")
				.header("Referer", "https://eminwon.asan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
				.cookie("SESSION_NTIS", "k9Nr0tj2LLJQn9y6GQZGM2ppr2yS6TRQGQpdX6RFyTfHw5cvnCp1!-2146147697!1812791592!13003!-1")
				.cookie("_ga", "GA1.3.312451242.1690111469")
				.cookie("_gid", "GA1.3.470221414.1690111469")
				.cookie("_ga_KP52R617CV", "GS1.3.1690111470.1.0.1690111470.0.0.0")
				.header("Origin", "https://eminwon.yesan.go.kr")
				.header("Referer", "https://eminwon.yesan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
				.header("Sec-Fetch-Dest", "iframe")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://eminwon.cheonan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("_gid", "GA1.3.816197956.1690112067")
					.cookie("_gat_gtag_UA_178883293_1", "1")
					.cookie("_ga_76JJYG3LJX", "GS1.1.1690112067.1.1.1690112073.0.0.0")
					.cookie("_ga", "GA1.1.1628594974.1690112067")
					.cookie("SESSION_NTIS", "k9QHwM1L5TFpprMQyzFfDH2NpFhsm44CjQcly2G8pSk2zx9v2LWv!-1066239555!1812005180!13002!-1")
					.header("Origin", "https://eminwon.cheonan.go.kr")
					.header("Referer", "https://eminwon.cheonan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=60&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&list_gubun=A&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
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
					.cookie("SESSION_NTIS", "k9WbRssh1hyGlmG64nvwQ1Y6j7n9G5TwPlqKJRn0lxhzxJlSQj06!-739131809!1812660790!13002!-1")
					.header("Origin", "https://eminwon.cheongyang.go.kr")
					.header("Referer", "https://eminwon.cheongyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "k9Z1kyv3TQrPN6ThH3H2JlywpttTTZ2fpnvmZJ2vVx2jGP8QPsrp!-52315328!1812857182!13002!-1")
					.cookie("_gid", "GA1.3.505759406.1690114360")
					.cookie("_gat", "1")
					.cookie("_gat_gtag_UA_213741573_1", "1")
					.cookie("_ga_7DXYZ7CBBB", "GS1.1.1690114359.2.0.1690114359.0.0.0")
					.cookie("_ga", "GA1.1.1236046233.1686654421")
					.cookie("_ga_2LTEKF7GVC", "GS1.3.1690114359.2.0.1690114359.0.0.0")
					.header("Origin", "http://eminwon.taean.go.kr")
					.header("Referer", "http://eminwon.taean.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "z8IF01nPKOjdx18kMucwbaiFJ5zcykHfFTS1nFvkx3d9LSFcxKX1xXw6w8Z8oE1U.Z3JlZW53YXMvSG9uZ3Nlb25nXzE")
					.cookie("wcs_bt", "ec358848efde9:1690114730")
					.cookie("_ga_T0Q1KSP37S", "GS1.1.1690114731.1.0.1690114731.0.0.0")
					.cookie("_ga", "GA1.3.553195479.1690114731")
					.cookie("_gid", "GA1.3.1953727485.1690114731")
					.cookie("_gat_gtag_UA_183899114_1", "1")
					.header("Origin", "https://www.hongseong.go.kr")
					.header("Referer", "https://www.hongseong.go.kr/prog/saeolGosi/kor/sub03_0204/GOSI_ALL/list.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&searchCondition=all&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&pageUnit=10")
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
					.cookie("1JSESSIONID1", "k9j4cL6PZacRgZrKzucFNqACGv2d0k1aScp9wF43ok18Gxlx4g6M!-288832539!1795190661!7012!-1")
					.header("Origin", "https://sido.chungbuk.go.kr")
					.header("Referer", "https://sido.chungbuk.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&gosiGbn=&sno=&sido=")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=8f99e8792b16d319f42cbf4a03197574&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=2022-01-01&conIfmStdt_Date=20220101&conIfmEnddt=2023-12-31&conIfmEnddt_Date=20231231&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BA%B8%BB%F3%B0%E8%C8%B9")
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
			response = Jsoup.connect("https://www.goesan.go.kr/www/contents.do?key=1438")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("XTVID", "A2307161919470002")
					.cookie("xloc", "1368X912")
					.cookie("JSESSIONID", "1403EF3DC76150B1C4F7AE737C5E6B6E")
					.cookie("XTREFERER", "https%3A//cafe.naver.com/ca-fe/cafes/30698943/articles/789%3FreferrerAllArticles%3Dtrue%26oldPath%3D%252FArticleRead.nhn%253Fclubid%253D30698943%2526articleid%253D789%2526referrerAllArticles%253Dtrue")
					.header("Referer", "https://www.goesan.go.kr/www/contents.do?key=1438")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
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
					.cookie("SESSION_NTIS", "k9n14L10PwyK7jpzZvsJm4xXRJ32qHN0n3RcndgjTmNTDbF8y1DD!666058944!1795909626!13002!-1")
					.header("Origin", "https://eminwon.danyang.go.kr")
					.header("Referer", "https://eminwon.danyang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.boeun.go.kr/www/selectBbsNttList.do?key=2262&bbsNo=66&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "7Op1AWj0Jl5bIQkzNfvOF1rh0A7abtzaQ7aQr1NBMnzZkMdGnuYgLMROnINrdwz0.boeunweb_servlet_engine1")
					.header("Referer", "https://www.boeun.go.kr/www/selectBbsNttList.do?key=2262&bbsNo=66&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Google Chrome\";v=\"117\", \"Not;A=Brand\";v=\"8\", \"Chromium\";v=\"117\"")
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
			response = Jsoup.connect("https://www.oc.go.kr/www/selectBbsNttList.do?key=236&bbsNo=40&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "87833E734260D2D10D64D717F48A2937")
					.cookie("_gid", "GA1.3.170799822.1690119594")
					.cookie("_gat_gtag_UA_145583656_1", "1")
					.cookie("_ga_8PEYLXNZFB", "GS1.1.1690119593.3.0.1690119593.0.0.0")
					.cookie("_ga", "GA1.1.1308941761.1686653659")
					.header("Referer", "https://www.oc.go.kr/www/selectBbsNttList.do?bbsNo=40&key=236&")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
			response = Jsoup.connect("https://www.eumseong.go.kr/www/selectEminwonList.do?ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&key=80&pageUnit=10&searchCnd=B_Subject&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "DF35462A0C34868C28AE73092437C0E9")
					.cookie("_ga_6JV43N624N", "GS1.1.1690119955.3.0.1690119955.0.0.0")
					.cookie("_ga", "GA1.3.37417151.1686498368")
					.cookie("_gid", "GA1.3.691472569.1690119956")
					.cookie("_gat_gtag_UA_179587661_1", "1")
					.header("Referer", "https://www.eumseong.go.kr/www/selectEminwonList.do?pageUnit=10&key=80&ofr_pageSize=10&not_ancmt_se_code=01,02,03,04,05&pageIndex=1")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
			response = Jsoup.connect("https://www.jecheon.go.kr/www/selectBbsNttList.do?key=5233&bbsNo=18&integrDeptCode=&searchCtgry=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "uYunW8fH5p4Xraf5oWwKkYNnRes8bx6LFNucxSG61mBJXTCAxsicRax2m45LVDiG.okjcwas_servlet_homepage")
					.header("Referer", "https://www.jecheon.go.kr/www/selectBbsNttList.do?bbsNo=18&key=5233")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("SESSION_NTIS", "k9xq0PR7p8pj1yYw2pQvjw4VnwBFX4MN1R8yvzv2mNpGH16h4Qrz!1959996421!1795949895!13003!-1")
					.cookie("_ga_D79NF86GB8", "GS1.1.1690120492.1.0.1690120492.0.0.0")
					.cookie("_ga", "GA1.3.1244394430.1690120493")
					.cookie("_gid", "GA1.3.1282654062.1690120493")
					.cookie("_gat_gtag_UA_115861715_1", "1")
					.header("Origin", "http://eminwon.jp.go.kr")
					.header("Referer", "http://eminwon.jp.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C03%2C04%2C06&title=%EA%B3%A0%EC%8B%9C%2F%EA%B3%B5%EA%B3%A0%2F%EC%9E%85%EB%B2%95%EC%98%88%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "k9y84XsPt77GvB5bVNlCwbht8sgMGQrd3nM7089ZmDh1nRGRK2JL!1920256033!1795686768!13003!-1")
					.header("Origin", "https://eminwon.jincheon.go.kr")
					.header("Referer", "https://eminwon.jincheon.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "pfp9e2nRUoONqSiOc1IOZIuu11Foa0R6FQBcVULfWVc7EiXWx7YIN9wep0NySBvJ.WEB_SERVER2_servlet_engine1")
					.cookie("SESSION_NTIS", "k91qWnrTPn1VJNYnJhv37MTDx1Q8v8jrwFGC1pdjJJ1fLnY723zT!1949962730!1795290901!13004!-1")
					.cookie("_INSIGHT_CK_8301", "aa0d0e167ddb505a629b17e153348a86_21517|3a993e0b410b4196229717e153348a86_21517:1690123317000")
					.cookie("WMONID", "5hcnt3-wsHF")
					.header("Origin", "https://eminwon.cheongju.go.kr")
					.header("Referer", "https://eminwon.cheongju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&not_ancmt_reg_no=&epcCheck=Y&yyyy=2018&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.chungju.go.kr/www/selectEminwonList.do?list_gubun=&searchKrwd=&ancmt_cn=&methodnm=&countYn=&searchCnd=all&wkly_event_mgt_no=&wkly_se_code=&pageUnit=10&ancmt_se_code=01%2C02%2C04&jndinm=&ofr_pageSize=10&title=&initValue=&homepage_pbs_yn=&subCheck=&context=&cha_dep_code_nm=&method=&countYnAC=&key=510&event_sj=&ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "5UeoYY3XHAY")
					.cookie("JSESSIONID", "AeN50TXI8HG6fFKSB1Y1IBFeiuqwommFFxJQfFLM8Ol9xagQxqXRiP8TU5VUK1FR.aG9tZXBhZ2UvaG9tZXBhZ2U%3D")
					.header("Referer", "https://www.chungju.go.kr/www/selectEminwonList.do?key=510&ofr_pageSize=10&ancmt_se_code=01,02,04&pageIndex=1")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("JSESSIONID", "f5oe15D17PAUIGfEEyArQBFIlECsgHV6KVRV7H3TYom95Yfmx1NNCahWKaruxqj3.jnwas1_servlet_engine1")
					.cookie("AUAH2A44537282909", "1690207164380554830%7C3%7C1686498763312966696%7C1%7C16864987637997US7ZF%7C1")
					.cookie("AUFAH2A44537282909", "1686495600000000000|3|1690207165621433160|1|1686498762155833160")
					.cookie("ACEUACS", "1686498762155833160")
					.cookie("_ACU106117", "1686498761570121682.1690207429297.2.0.121682MK7BXIB6J6UE2.0.0.0.....")
					.cookie("_ACR0", "e1c9367cfc6aed0afdcbb44cd4c87f43c2bbf20c")
					.cookie("ASAH2A44537282909", "1690207164380554830%7C1690207430413148027%7C1690207164380554830%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("ARAH2A44537282909", "httpswwwjeonnamgokrJ0203boardListdohttpswwwjeonnamgokrJ0203boardListdomenuIdjeonnam0203000000")
					.cookie("_ACS106117", "6343")
					.header("Origin", "https://www.jeonnam.go.kr")
					.header("Referer", "https://www.jeonnam.go.kr/J0203/boardList.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("menuId=jeonnam0203000000&boardId=J0203&displayHeader=&infoReturn=&searchType=0&searchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "7n5uurdcot2v5dq6m6mkbu58v2")
					.cookie("_gid", "GA1.3.1837479372.1690207780")
					.cookie("_gat_gtag_UA_130583664_1", "1")
					.cookie("_ga_9EQ1M9XGPF", "GS1.1.1690207779.2.1.1690207787.0.0.0")
					.cookie("_ga", "GA1.1.751647520.1688884646")
					.header("Origin", "https://www.gangjin.go.kr")
					.header("Referer", "https://www.gangjin.go.kr/www/government/notify")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "F0E00BF0377608FFA2590EEE3872FE59")
					.header("Origin", "https://www.goheung.go.kr")
					.header("Referer", "https://www.goheung.go.kr/contentsView.do?pageId=www99")
					.header("Sec-Fetch-Dest", "empty")
					.header("Sec-Fetch-Mode", "cors")
					.header("Sec-Fetch-Site", "same-origin")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("action=L&seq=&notAncmtSeCode=01%2C04%2C05&listGubun=A&recordCnt=10&movePage=1&prevAction=L&prevMovePage=1&eminwonSearch=S&eminwonQuery=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.gokseong.go.kr/board/GosiList.do?menuNo=102001003000&pageIndex=1&searchCnd=0&not_ancmt_mgt_no=&not_ancmt_se_code=&list_gubun=&srhCate=title&searchWrd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("wcs_bt", "145da80a0862e30:1688884290")
					.cookie("JSESSIONID", "6A38508F0DE133501652A0D47B5325C1")
					.header("Referer", "https://www.gokseong.go.kr/board/GosiList.do?menuNo=102001003000&pageIndex=1&searchCnd=0&not_ancmt_mgt_no=&not_ancmt_se_code=&list_gubun=&srhCate=title&searchWrd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
			response = Jsoup.connect("https://gwangyang.go.kr/saeol/gosi.es?mid=a11005020000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "3E850D59CEC525DEA95F4903E8D8E509")
					.cookie("HISMENU_a11005010000", "%7B%22mid%22%3A%221005010000%22%2C%22connDt%22%3A%2220230727214000%22%2C%22menuPath%22%3A%22%EB%89%B4%EC%8A%A4%5C%2F%EC%86%8C%EC%8B%9D+%3E+%EA%B3%A0%EC%8B%9C%5C%2F%EA%B3%B5%EA%B3%A0+%3E+%EA%B3%A0%EC%8B%9C%22%2C%22menuUrl%22%3A%22%5C%2Fmenu.es%3Fmid%3Da11005010000%22%2C%22sid%22%3A%22a1%22%7D")
					.cookie("HISMENU_a11005020000", "%7B%22mid%22%3A%221005020000%22%2C%22connDt%22%3A%2220230727214007%22%2C%22menuPath%22%3A%22%EB%89%B4%EC%8A%A4%5C%2F%EC%86%8C%EC%8B%9D+%3E+%EA%B3%A0%EC%8B%9C%5C%2F%EA%B3%B5%EA%B3%A0+%3E+%EA%B3%B5%EA%B3%A0%22%2C%22menuUrl%22%3A%22%5C%2Fmenu.es%3Fmid%3Da11005020000%22%2C%22sid%22%3A%22a1%22%7D")
					.header("Origin", "https://gwangyang.go.kr")
					.header("Referer", "https://gwangyang.go.kr/saeol/gosi.es?mid=a11005020000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("mid=a11005020000&act=list&type_code=02%2C04&nPage=1&keyField=T&keyWord=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.gurye.go.kr/board/GosiList.do?menuNo=115004002001&pageIndex=1&searchCnd=0&not_ancmt_mgt_no=&not_ancmt_se_code=01%2C04%2C06%2C07&list_gubun=&searchWrd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("gurye.go.kr", "B56379F77B63B3BFBCE6384C5D878644::GTFC823D8B")
					.cookie("_ga", "GA1.1.752791166.1690461976")
					.cookie("_ga_C662XS5S26", "GS1.1.1690461975.1.1.1690462133.0.0.0")
					.header("Referer", "https://www.gurye.go.kr/board/GosiList.do?menuNo=115004002001&pageIndex=1&searchCnd=0&not_ancmt_mgt_no=&not_ancmt_se_code=01%2C04%2C06%2C07&list_gubun=&searchWrd=%EB%B3%B4%EC%83%81")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "i770l06q1prmirrd79dnuinm3o")
					.cookie("session_id", "i770l06q1prmirrd79dnuinm3o")
					.cookie("topLayer_browser", "no_show")
					.cookie("_ga_B24JZEJV7F", "GS1.1.1690462415.4.1.1690462925.26.0.0")
					.header("Origin", "https://www.naju.go.kr")
					.header("Referer", "https://www.naju.go.kr/www/administration/notice")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page_scale=15&search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "uet30osobg6c43vrfne4c4r6q6")
					.cookie("_gid", "GA1.3.1966276416.1690467442")
					.cookie("_ga", "GA1.1.1587438361.1690467442")
					.cookie("_ga_M03W3XLVD2", "GS1.1.1690467441.1.1.1690467924.0.0.0")
					.header("Origin", "https://www.mokpo.go.kr")
					.header("Referer", "https://www.mokpo.go.kr/www/open_administration/city_news/notification/public_notice")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("BROWSER", "YToxNTp7czoxODoiYnJvd3Nlcl9uYW1lX3JlZ2V4IjtzOjY6In5eLiokfiI7czoyMDoiYnJvd3Nlcl9uYW1lX3BhdHRlcm4iO3M6MToiKiI7czo2OiJwYXJlbnQiO3M6MTc6IkRlZmF1bHRQcm9wZXJ0aWVzIjtzOjc6ImNvbW1lbnQiO3M6MTU6IkRlZmF1bHQgQnJvd3NlciI7czo3OiJicm93c2VyIjtzOjE1OiJEZWZhdWx0IEJyb3dzZXIiO3M6MTQ6ImlzbW9iaWxlZGV2aWNlIjtzOjA6IiI7czo4OiJpc3RhYmxldCI7czowOiIiO3M6NzoidmVyc2lvbiI7czozOiIwLjAiO3M6ODoicGxhdGZvcm0iO3M6NzoidW5rbm93biI7czoxMToiZGV2aWNlX3R5cGUiO3M6NzoidW5rbm93biI7czo0OiJJRTExIjtiOjA7czo0OiJJRTEwIjtiOjA7czozOiJJRTkiO2I6MDtzOjM6IklFOCI7YjowO3M6MzoiSUU3IjtiOjA7fQ%3D%3D")
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "v87hrh152a4bcu77hhsqdskll9")
					.cookie("session_id", "v87hrh152a4bcu77hhsqdskll9")
					.cookie("MUAN_SETTING_KEY", "v87hrh152a4bcu77hhsqdskll9")
					.cookie("_gid", "GA1.3.101566094.1690471541")
					.cookie("_gat_gtag_UA_80215608_1", "1")
					.cookie("_ga_1N278NFZVL", "GS1.1.1690471540.1.1.1690471543.0.0.0")
					.cookie("_ga", "GA1.1.1545872804.1690471541")
					.header("Origin", "https://www.muan.go.kr")
					.header("Referer", "https://www.muan.go.kr/www/openmuan/new/announcement")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "idddj5u87pl333jbceio2jugb1")
					.cookie("_ga_M5HMXN0EDG", "GS1.1.1690472778.1.0.1690472778.0.0.0")
					.cookie("_ga", "GA1.3.1361907056.1690472779")
					.cookie("_gid", "GA1.3.842649602.1690472779")
					.header("Origin", "https://www.boseong.go.kr")
					.header("Referer", "https://www.boseong.go.kr/www/open_administration/city_news/notification")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("http://www.suncheon.go.kr/kr/news/0004/0005/0001/?searchCondition=title&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "Pv_q__jCWRL")
					.cookie("JSESSIONID", "DAFD876C73B2B6BC3EF53A8C025ACCD6")
					.cookie("zoomName", "100")
					.cookie("_gid", "GA1.3.213780893.1690473331")
					.cookie("_ga_8M2FWNPJRH", "GS1.1.1690473331.1.1.1690474199.0.0.0")
					.cookie("_ga", "GA1.3.2034633571.1690473331")
					.header("Referer", "http://www.suncheon.go.kr/kr/news/0004/0005/0001/?searchCondition=title&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
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
			response = Jsoup.connect("https://www.shinan.go.kr/home/www/openinfo/participation_07/participation_07_04?search=search_title&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=8&y=12")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "22mrh0f4gkikgiuo2945cf3in1")
					.cookie("wcs_bt", "15d4b5f8f70ec0:1690474654")
					.header("Referer", "https://www.shinan.go.kr/home/www/openinfo/participation_07/participation_07_04?search=search_title&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=28&y=12")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("session_id", "nvbbi844gdfm3chssmngudvo94")
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "s2fqocg3l9cgm0eheso1c5oge9")
					.cookie("HISTORY_SETTING_KEY", "s2fqocg3l9cgm0eheso1c5oge9")
					.cookie("_ga_ZH9MCCCX5N", "GS1.1.1690474737.1.0.1690474737.0.0.0")
					.cookie("_ga", "GA1.3.216701421.1688758665")
					.cookie("_gid", "GA1.3.652740096.1690474737")
					.cookie("_gat_gtag_UA_72553063_1", "1")
					.header("Origin", "https://www.yeosu.go.kr")
					.header("Referer", "https://www.yeosu.go.kr/www/govt/news/notify")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page_scale=15&search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.yeonggwang.go.kr/bbs/?b_id=gosigonggo&site=headquarter_new&mn=9059&type=lists&sc_cate=&per_page=15&sc_key=subject&sc_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("mydomain_ci_session", "Oej4C3m4vVeUkGCaUEhMlk%2BCb9smG4t4LYUKgQSBY5utYjRFRyRXJkIn00KrXWKlxBsnm5O%2FDM%2BItyMgcUgHVbwNrLAIjHAqLWPdzsPgrQz%2BVBHehjtKO9dEnv0fTJKyV1oxvR5Q3Hch6R9R%2BvWYJjiduBkVLwvxB2QTZ9KSSFbq7fCObQWCWLAEciSrXA5jqP%2BqhCAQo%2F4OLcuIMf1XbywbDCEpkzqu9Ku%2B5mwh9LWHnGMXUpy8%2F9rpx6vDkxpHoS2%2B%2F9EW9yFImVh8y1EVT%2FWgFULyEj%2BsUIr5z2PquUJKfObKTzhFb%2F9thtjGj9GBNQ4q3gDBGUQKJq%2B%2BbitSbtpsce5CqxKNRQyKLga5kzay5GgbvPvJ%2FF3r41wL4R7fbCTOkARozF%2BjAArZXLrXyAzePBWE%2BkxTX%2B7cDW86TFiaZ2vb6h5l%2FgkEkPvz31juiyjLl9iUcu2%2Fd%2BU1Cw%2BcZDuXCTeec9jS1GcvsD%2FMjxhpOOUOU18tKAhVByEWHH3HyPD6ydu22rnAKF%2BWPP7MDA%3D%3D822a7adf4f98287c498857b17e9598a1994a10cf")
					.cookie("_gid", "GA1.3.2037189416.1690474879")
					.cookie("_gat_gtag_UA_74047017_1", "1")
					.cookie("wcs_bt", "c15819707ac140:1690474932")
					.cookie("_ga_513L2L1020", "GS1.1.1690474879.1.1.1690474932.0.0.0")
					.cookie("_ga", "GA1.3.1289768550.1690474879")
					.header("If-Modified-Since", "Thu, 27 Jul 2023 16:22:11 GMT")
					.header("Referer", "https://www.yeonggwang.go.kr/bbs/?b_id=gosigonggo&site=headquarter_new&mn=9059&type=lists&sc_cate=&per_page=15&sc_key=subject&sc_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
			response = Jsoup.connect("https://www.yeongam.go.kr/home/www/open_information/yeongam_news/announcement?search=search_title&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "bc2aihcstlsmrmuktkp686cmk9")
					.cookie("wcs_bt", "8b12ea0da61388:1690475083")
					.header("If-Modified-Since", "Thu, 27 Jul 2023 16:24:41 GMT")
					.header("Referer", "https://www.yeongam.go.kr/home/www/open_information/yeongam_news/announcement?search=search_title&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("JSESSIONID", "19A478A3F51EC9A27E4A560EB8CE9FD7")
					.header("Origin", "https://www.wando.go.kr")
					.header("Referer", "https://www.wando.go.kr/wando/sub.cs?m=318")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.jangseong.go.kr/home/www/news/jangseong/announcement?search=search_title&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=15&y=3")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WAF", "edad8ae45df2bc3fa21286f25133ff58")
					.cookie("PHPSESSID", "91inr7jd2ggkirr74lquus6m57")
					.cookie("wcs_bt", "59560989cd6170:1690475393")
					.header("Referer", "https://www.jangseong.go.kr/home/www/news/jangseong/announcement")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("BROWSER", "YTo1OntzOjQ6IklFMTEiO2I6MDtzOjQ6IklFMTAiO2I6MDtzOjM6IklFOSI7YjowO3M6MzoiSUU4IjtiOjA7czozOiJJRTciO2I6MDt9")
					.cookie("HUA", "14d58a1ba286f087d9736249ec785314")
					.cookie("PHPSESSID", "5sh871id6m2q6pa1srndn0a3vr")
					.cookie("session_id", "5sh871id6m2q6pa1srndn0a3vr")
					.cookie("_ga_NMHEE1TVKF", "GS1.1.1690475512.1.0.1690475512.0.0.0")
					.cookie("_ga", "GA1.3.1849894790.1690475513")
					.cookie("_gid", "GA1.3.1756324200.1690475513")
					.cookie("_gat_gtag_UA_109491346_1", "1")
					.header("Origin", "https://www.jangheung.go.kr")
					.header("Referer", "https://www.jangheung.go.kr/www/organization/news/notification")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "E4457AA0FF72C163475F8800EDA1D508")
					.header("Origin", "https://www.jindo.go.kr")
					.header("Referer", "https://www.jindo.go.kr/home/gosi/general.cs")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("m=24&searchCondition=notAncmtSj&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "B1B295CAF7BD4CEEAD1E41DB352454A9")
					.cookie("SiiRU", "SiiRU.www.1894764401.1690475773327")
					.cookie("SiiRU_latestTouch", "1690475801408")
					.cookie("SiiRU_sessionExpiry", "1690477601408")
					.header("Origin", "https://www.hampyeong.go.kr")
					.header("Referer", "https://www.hampyeong.go.kr/pg/GosiList.do?pageId=www273")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&pageId=www273&listGubun=&notAncmtSeCode=01%2C02%2C03%2C04&search_Type=0&searchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&btnSearch=")
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
					.cookie("JSESSIONID", "63DB5FF4EC3F9BAC8E594487DB7D39B8.was1")
					.header("Origin", "http://www.haenam.go.kr")
					.header("Referer", "http://www.haenam.go.kr/")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "cross-site")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("boardUid=18e3368f644b01470164eeb23d7b30f5&layoutUid=&contentUid=18e3368f5d745106015de961fbbd205e&categoryUid1=0&searchType=dataTitle&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("JSESSIONID", "GxjP82WIumDiBfRHVNfwvXbHQTZPqmSZDOTkJtca4Av8q6K3xK9IAbqG5l1QinRV.hwasun_servlet_engine1")
					.header("Origin", "https://www.hwasun.go.kr")
					.header("Referer", "https://www.hwasun.go.kr/board.do?S=S01&M=020102000000&b_code=0000000002")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("S=S01&M=020102000000&b_code=0000000002&nPage=1&keyField=&keyWord=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.jeonbuk.go.kr/board/list.jeonbuk?menuCd=DOM_000000103001002001&boardId=BBS_0000012&categoryCode1=jeonbuk&categoryCode2=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("ACEUCI", "1")
					.cookie("WMONID", "zq1vJ4HmTYI")
					.cookie("ACEFCID", "UID-64A83FE5AC7CEA6BC622B1AF")
					.cookie("_ga", "GA1.3.1892227472.1688748006")
					.cookie("JSESSIONID", "wcHbsOo1zxdg6RqYupgYfBm8XoR3AGMBeCRLd0DXKZeWw1wex4kUzIC15sSuQkhi.amV1c19kb21haW4vaG9tZXBhZ2UyLTE")
					.cookie("_gid", "GA1.3.2003714769.1691322418")
					.cookie("_gat", "1")
					.cookie("AUAM5A36031644840", "1691322418683993994%7C7%7C1688748006745933160%7C1%7C1681540096424648322%7C1")
					.cookie("_ga_6Z08VQ20W3", "GS1.3.1691322418.6.1.1691322424.0.0.0")
					.cookie("AUFAM5A36031644840", "1690729200000000000|5|1691322417431657340|1|1681540096424648322")
					.cookie("ACEUACS", "1681540096424648322")
					.cookie("ASAM5A36031644840", "1691322418683993994%7C1691322424567156310%7C1691322418683993994%7C0%7Chttpscafenavercomca-fecafes30698943articles789referrerAllArticlestrueoldPath2FArticleReadnhn3Fclubid3D3069894326articleid3D78926referrerAllArticles3Dtrue")
					.cookie("ARAM5A36031644840", "httpswwwjeonbukgokrboardlistjeonbukboardIdBBS0000012menuCdDOM000000103001002001contentsSid841cpathhttpswwwjeonbukgokrboardlistjeonbukboardIdBBS0000012menuCdDOM000000103001002001contentsSid841cpath")
					.header("Referer", "https://www.jeonbuk.go.kr/board/list.jeonbuk?boardId=BBS_0000012&menuCd=DOM_000000103001002001&contentsSid=841&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.gochang.go.kr/index.gochang?menuCd=DOM_000000102003007000&boardId=BBS_0000180&startPage2=1&schType=DATA_TITLE&schKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "nlPWHvqUx45cbRpib3qIaYudjBozGBMnND1UlQ386ZFkt6JixaEq1HZg1bJ1hhqm.amV1c19kb21haW4vc2VydmVyMw")
					.header("Referer", "https://www.gochang.go.kr/index.gochang?menuCd=DOM_000000102003007000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("SESSION_NTIS", "kKy4yMh6mhhDLgj0ydpn8KFhjfRTLj9N0k4h9zN1vM3P0KvsZv12!-1147586331!1828847956!13003!-1")
					.header("Origin", "http://eminwon.gunsan.go.kr")
					.header("Referer", "http://eminwon.gunsan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&yyyy=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.gimje.go.kr/board/list.gimje?menuCd=DOM_000000105001003000&boardId=BBS_0000044&categoryCode2=&categoryCode3=&categoryCode1=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "1GDjQqG4aclEYynKtE0kQ3KA2CR1bUkONrBu1Tkxrou1l4nvxB31An32Nlaa01Pu.amV1c19kb21haW4vaG9tZXBhZ2UyLTE")
					.cookie("_gid", "GA1.3.266892829.1690986958")
					.cookie("_gat_gtag_UA_1708370_3", "1")
					.cookie("_ga_VVQ0JC7Y7F", "GS1.1.1690986957.1.1.1690987069.27.0.0")
					.cookie("_ga", "GA1.1.665684609.1690986958")
					.header("Referer", "https://www.gimje.go.kr/board/list.gimje?boardId=BBS_0000044&menuCd=DOM_000000105001003000&contentsSid=1433&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.namwon.go.kr/board/list.do?menuCd=DOM_000000202001003000&contentsSid=47&startPage=1&orderBy=REGISTER_DATE+DESC&boardId=BBS_0000005&listCel=1&categoryCode1=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchStartDt=&searchEndDt=&listRow=10")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "omc1MQbsz5zBPL2OT9UmBAY6Wt1nOHwKNg0sUBs0iEqtHjlnxAl428xJJkLbtvpc.amV1c19kb21haW4vSE9NRTEx")
					.cookie("_gid", "GA1.3.874442415.1690987254")
					.cookie("_gat", "1")
					.cookie("_gat_gtag_UA_1708370_41", "1")
					.cookie("_ga_W3D8XP7KVM", "GS1.1.1690987254.1.1.1690987267.47.0.0")
					.cookie("_ga", "GA1.3.3624506.1690987254")
					.cookie("wcs_bt", "15d80e4ffd6204:1690987267")
					.header("Referer", "https://www.namwon.go.kr/board/list.do?menuCd=DOM_000000202001003000&contentsSid=47&startPage=1&orderBy=REGISTER_DATE+DESC&boardId=BBS_0000005&listCel=1&categoryCode1=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81&searchStartDt=&searchEndDt=&listRow=10")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("SESSION_NTIS", "kKr4vnPrsLnQsQ7nxpdphQ9qFpmWTg0S75r7h33ltlZFwKWGvVym!-1911772879!1829306659!13002!-1")
					.cookie("_ga_7LTDQ8Y89R", "GS1.1.1690987450.1.0.1690987450.0.0.0")
					.cookie("_ga", "GA1.1.1358774343.1690987451")
					.header("Origin", "https://eminwon.muju.go.kr")
					.header("Referer", "https://eminwon.muju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.buan.go.kr/board/list.buan?menuCd=DOM_000000103001003000&contentsSid=84&boardId=BBS_0000054&startPage=1&searchStartDt=&searchEndDt=&searchOperation=AND&orderBy=&categoryCode1=&categoryCode2=&categoryCode3=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "lM2GJxqAbwALxuNgHChxiGtCdg4a8QPADqHSN3TkUqL1Z46mxixX6JSXRwq11cNr.amV1c19kb21haW4vc2VydmVyMQ")
					.header("Referer", "https://www.buan.go.kr/board/list.buan?menuCd=DOM_000000103001003000&contentsSid=84&boardId=BBS_0000054&startPage=1&searchStartDt=&searchEndDt=&searchOperation=AND&orderBy=&categoryCode1=&categoryCode2=&categoryCode3=&searchType=DATA_TITLE&keyword=%EC%A7%80%EB%B0%A9")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
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
					.cookie("SESSION_NTIS", "kKsftgx50cCj93p27NPJGXSsPGLjJkRvvbVbMGJ1ykqdGp06V4vG!-1837727426!1829503869!13002!-1")
					.header("Origin", "http://eminwon.sunchang.go.kr")
					.header("Referer", "http://eminwon.sunchang.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&yyyy=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "kKt5npkb8g6nTTkfNMBKdNb8yx9vj4ZcyCRYqHbS9B2hZG5FCVF0!-1000315300!1829177900!13003!-1")
					.cookie("_ga_MPW6PYCKH3", "GS1.1.1690987835.1.0.1690987835.60.0.0")
					.cookie("_ga", "GA1.3.159190461.1690987835")
					.cookie("_gid", "GA1.3.1645737848.1690987835")
					.cookie("_gat_gtag_UA_1708370_11", "1")
					.header("Origin", "https://eminwon.wanju.go.kr")
					.header("Referer", "https://eminwon.wanju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("_gid", "GA1.3.1571514037.1690989075")
					.cookie("_gat", "1")
					.cookie("_ga_7YTBD9FLC2", "GS1.3.1690989075.1.0.1690989075.60.0.0")
					.cookie("SESSION_NTIS", "kKyTm65chL025YxkNphpgT6cCLLZ7n1rDpLgJxRP2kRxGnSdxsdl!-2033462518!1828914000!13003!-1")
					.header("Origin", "https://eminwon.iksan.go.kr")
					.header("Referer", "https://eminwon.iksan.go.kr/emwp/jsp/ofr/OfrNotAncmtLSub.jsp?not_ancmt_se_code=01,02,03,04,05&subcheck=Y&list_gubun=A&yyyy=2019&epccheck=y&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=2019&recent_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();

			Thread.sleep(10000);
			
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
					.cookie("SESSION_NTIS", "kKyh4mXvd2hv9B9f7dPRjx1L3RmC6QlY7sH61LqKgvcTGRLDjQ3y!38368505!1829437987!13002!-1")
					.header("Origin", "https://eminwon.imsil.go.kr")
					.header("Referer", "https://eminwon.imsil.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=A&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "kK0LQhTKWTfpnfdmT22nfc77jvsrV9yWvYBCHrMpyHzxLpC5kVLG!-408351234!1829372441!13002!-1")
					.cookie("_ga_R7XJ840SYK", "GS1.1.1690989579.1.0.1690989579.60.0.0")
					.cookie("_ga", "GA1.3.615014893.1690989580")
					.cookie("_gid", "GA1.3.1164857565.1690989580")
					.cookie("_gat_gtag_UA_1708370_40", "1")
					.header("Origin", "https://eminwon.jangsu.go.kr")
					.header("Referer", "https://eminwon.jangsu.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C04%2C05&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("SESSION_NTIS", "kK0WQvQCjYprpQSKhttC9L0VWV9RB4y6DhpJN1vxR2KpRGBL0q2d!-1543736464!1828782105!13003!-1")
					.cookie("_ga", "GA1.3.729513804.1690989785")
					.cookie("_gid", "GA1.3.732260267.1690989785")
					.header("Origin", "https://eminwon.jeonju.go.kr")
					.header("Referer", "https://eminwon.jeonju.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("epcCheck=Y&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.jeongeup.go.kr/board/list.jeongeup?menuCd=DOM_000000101001001000&contentsSid=5&boardId=BBS_0000012&startPage=1&searchStartDt=&searchEndDt=&searchOperation=OR&orderBy=REGISTER_DATE+DESC&categoryCode1=&categoryCode2=&categoryCode3=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "pSUKDgBjj1gADONBcsBkzAAFriq9FRaFNjpsqDMKJnPvCXX3xLwQjabI2ajZJpMl.amV1c19kb21haW4vc2VydmVyMQ")
					.cookie("_ga_FQTXE01WN8", "GS1.1.1690989988.1.0.1690989988.60.0.0")
					.cookie("_gid", "GA1.3.1275884764.1690989994")
					.cookie("_gat_gtag_UA_1708370_61", "1")
					.cookie("_gat_gtag_UA_118898689_1", "1")
					.cookie("_ga_FDX2M57DR3", "GS1.1.1690989993.1.0.1690989993.0.0.0")
					.cookie("_ga", "GA1.1.845107460.1690989993")
					.header("Referer", "https://www.jeongeup.go.kr/board/list.jeongeup?boardId=BBS_0000012&menuCd=DOM_000000101001001000&contentsSid=5&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("SESSION_NTIS", "kK2L6Tg2GvQ58cWnyZs1h4vjVhG50Mtzx0hcCScG5p2vFmv4rszY!1701638808!1829241385!13003!-1")
					.cookie("_ga_4826GW6BFV", "GS1.1.1690990222.1.0.1690990222.0.0.0")
					.cookie("_ga_W3D8XP7KVM", "GS1.1.1690990222.1.0.1690990222.60.0.0")
					.cookie("_ga", "GA1.3.822424250.1690990223")
					.cookie("_gid", "GA1.3.1482307239.1690990223")
					.cookie("_gat_gtag_UA_131888629_1", "1")
					.cookie("_gat_gtag_UA_1708370_41", "1")
					.header("Origin", "https://eminwon.jinan.go.kr")
					.header("Referer", "https://eminwon.jinan.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.cookie("WAF", "568639b3059374556fb0029782ed4546")
					.cookie("LENA-UID", "b8c140aa.601f264a7145c")
					.cookie("JSESSIONID", "1508C72BEB27B842587467E5B506A9C1.jvmrouteMAIN23")
					.cookie("WMONID", "Djg8aFt25hL")
					.cookie("L-VISITOR", "ziv6itlfldce5")
					.cookie("XTVID", "A23080300342942527")
					.cookie("_gid", "GA1.3.1430714227.1690990472")
					.cookie("_ga_1J509V2NXT", "GS1.1.1690990472.1.1.1690991364.0.0.0")
					.cookie("_ga", "GA1.3.212104776.1690990472")
					.cookie("_gat_gtag_UA_193034439_1", "1")
					.header("Origin", "https://www.gyeongnam.go.kr")
					.header("Referer", "https://www.gyeongnam.go.kr/index.gyeong?menuCd=DOM_000000135003009001")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("conGosiGbn=&conIfmStdt=2020-01-01&conIfmEnddt=2033-08-31&conAnnounceNo=&conDeptNm=&conTitle=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.geoje.go.kr/index.geoje?menuCd=DOM_000008902001002001&searchType=NOT_ANCMT_SJ&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "s9HYuIkvhIj")
					.cookie("WAF", "01ca69ef78223c7ee00e6c73d0bc3c85")
					.cookie("JSESSIONID", "Rjj8uwqq1Rcdin2nhSDfEiFBCrJexaNZNLUy6bfjYMDhq48sxZmb7GwHJIrbl8pH.UEdKV0FTL01haW4yMw")
					.cookie("wcs_bt", "115f7bbf344c610:1690991694")
					.cookie("_ga_HVJ8G5J4GG", "GS1.1.1690991694.3.0.1690991694.60.0.0")
					.cookie("_ga", "GA1.3.800876126.1686651363")
					.cookie("_gid", "GA1.3.681156060.1690991695")
					.cookie("_gat_gtag_UA_1708370_27", "1")
					.header("Referer", "https://www.geoje.go.kr/index.geoje?menuCd=DOM_000008902001002001")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.geochang.go.kr/saeol/gosi.do?cpage=1&siteGubun=news&pageCd=NW0102000000&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "AF62C0F9B03254ADB053422621544F66")
					.cookie("_ga", "GA1.3.266619710.1690991908")
					.cookie("_gid", "GA1.3.1811510089.1690991908")
					.cookie("wcs_bt", "4a0d05b5cb11f0:1690992378")
					.cookie("_ga_CKL2SDRJ62", "GS1.3.1690991909.1.1.1690992379.0.0.0")
					.header("Referer", "https://www.geochang.go.kr/saeol/gosi.do?cpage=1&siteGubun=news&pageCd=NW0102000000&stype=title&sstring=%EB%B3%B4")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.goseong.go.kr/board/list.goseong?orderBy=&boardId=BBS_0000015&searchStartDt=&searchEndDt=&startPage=1&menuCd=DOM_000000102002002000&contentsSid=29&searchOperation=AND&startDt=&endDt=&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "1A92D3E2C447CD4D3CB11BF326770437")
					.cookie("_ga_XXR29FDSZ6", "GS1.1.1691070773.2.0.1691070773.0.0.0")
					.cookie("_ga", "GA1.3.306921217.1686930861")
					.cookie("_gid", "GA1.3.1040139198.1691070774")
					.cookie("_gat_gtag_UA_221898738_1", "1")
					.header("Referer", "https://www.goseong.go.kr/board/list.goseong?boardId=BBS_0000015&menuCd=DOM_000000102002002000&contentsSid=29&cpath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.gimhae.go.kr/03360/00023/00029.web?cpage=1&deptCode=&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WAF", "0a90d0181cc076b9dc5ec2b85a7fba65")
					.cookie("JSESSIONID", "2aniqRA0puErKkTxcZ4y4uCXD6akT8PuRmvQq6UQMoIfK5jgxwdMf2NkvZtzgPFT.UEdIUE9SVEFML3d3dzEx")
					.cookie("WMONID", "R8Q5naJIub2")
					.cookie("wcs_bt", "595141d8c6fce0:1691071830")
					.cookie("_ga_4C1ERYNMTY", "GS1.1.1691071831.1.0.1691071831.0.0.0")
					.cookie("_gid", "GA1.3.688014844.1691071832")
					.cookie("_gat_gtag_UA_127887564_1", "1")
					.cookie("_ga_0Q5YRDQ7GG", "GS1.1.1691071831.1.0.1691071831.0.0.0")
					.cookie("_ga", "GA1.1.1637057382.1691071831")
					.header("Referer", "https://www.gimhae.go.kr/03360/00023/00029.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.namhae.go.kr/modules/saeol/gosi.do?cpage=1&siteGubun=socialm&pageCd=SM010110000&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "B734F727530A296116406939DA1B9288")
					.header("Referer", "https://www.namhae.go.kr/modules/saeol/gosi.do?&pageCd=SM010110000&siteGubun=socialm")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.miryang.go.kr/web/eMinwonList.do?mnNo=20903000000&owd=&pageIndex=1&searchCateId=01%2C02%2C03%2C04%2C05&not_ancmt_se_code1=01&not_ancmt_se_code2=02&not_ancmt_se_code3=03&not_ancmt_se_code4=04&not_ancmt_se_code5=05&searchCnd=0&searchWrd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "9EF53AC72C4DC3AB0EDF934997008B03.was1")
					.header("Referer", "https://www.miryang.go.kr/web/eMinwonList.do?mnNo=20903000000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.sacheon.go.kr/news/00009/00014.web?gcode=2017&amode=list&wmode=&cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "867E58422E021C8E48EA3460613B8184")
					.cookie("WMONID", "GbaLJAlddRm")
					.header("Referer", "https://www.sacheon.go.kr/news/00009/00014.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.sancheong.go.kr/www/selectBbsNttList.do?key=158&bbsNo=118&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("TJSESSIONID", "23E260C96E52FC44BC39D3711651AE2E")
					.header("Referer", "https://www.sancheong.go.kr/www/selectBbsNttList.do?bbsNo=118&key=158")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("JSESSIONID", "NqsU0CI95HdCY4aAvFJ80oe10cyhB3wqrwNHFagdCwmIMBaHxSFQJc87MQleH8zV.UFlTV0FTL1BPUlRBTDIx")
					.header("Origin", "https://www.yangsan.go.kr")
					.header("Referer", "https://www.yangsan.go.kr/portal/saeol/gosi/list.do?mId=0401020000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&pbsDivision=&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
			response = Jsoup.connect("https://www.uiryeong.go.kr/board/list.uiryeong?orderBy=&boardId=BBS_0000070&searchStartDt=&searchEndDt=&startPage=1&menuCd=DOM_000000203003001001&contentsSid=201&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("L-VISITOR", "x2mintlkd794vd")
					.cookie("LENA-UID", "ff15830f.5fddc096339ad")
					.cookie("L-VISITOR", "z3astlb4v9p434")
					.cookie("JSESSIONID", "E176247878C7DCA542DD42DFDBB927E9.skoinfojvmrouter")
					.header("Referer", "https://www.uiryeong.go.kr/board/list.uiryeong?orderBy=&boardId=BBS_0000070&searchStartDt=&searchEndDt=&startPage=1&menuCd=DOM_000000203003001001&contentsSid=201&searchType=DATA_TITLE&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.jinju.go.kr/00130/02730/05586.web?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("cms.sessionId", "HZ7D29CEA58E6E4854ABEE09795026DDA1")
					.cookie("WMONID", "NdZO0Dt--5L")
					.cookie("JSESSIONID", "26g3ukauK1alcoLhAfHnAYAf8VDTksH3rhELF9NaVar6tK4MxKTgvqu16YLD1IGx.UEpJTkpVL3d3dzE")
					.cookie("wcCookie", "125.179.129.136_T_25311_WC")
					.cookie("wcs_bt", "13923603c59492c:1691219757")
					.header("Referer", "https://www.jinju.go.kr/00130/02730/05586.web?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
				arr[index][2] = "";
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
			response = Jsoup.connect("https://www.cng.go.kr/news/00000372/00000375.web;jsessionid=E11D0A25DB61F1ECA60824B014BF811B.cng1?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WAF", "9245309c017a1f516d61c83b5399b774")
					.cookie("JSESSIONID", "E11D0A25DB61F1ECA60824B014BF811B.cng1")
					.cookie("wcs_bt", "1d02ef30f12110:1691220035")
					.cookie("_gid", "GA1.3.334447512.1691220036")
					.cookie("_gat", "1")
					.cookie("_gat_gtag_UA_109161110_1", "1")
					.cookie("_ga_CE3SJHB28C", "GS1.1.1691220036.1.0.1691220036.0.0.0")
					.cookie("_ga", "GA1.1.191911072.1691220036")
					.cookie("_ga_TS9RM0RRKX", "GS1.3.1691220036.1.0.1691220036.0.0.0")
					.cookie("_ga_J7W0ZBYPCF", "GS1.1.1691220036.1.0.1691220036.0.0.0")
					.cookie("_ga_PMQZ1Z2VRW", "GS1.1.1691220036.1.1.1691220074.0.0.0")
					.header("Referer", "https://www.cng.go.kr/news/00000372/00000375.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.changwon.go.kr/cwportal/10310/10438/10439.web?cpage=1&pbsDivision=&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "8RnQJ2uSHd4aq500mv2iooRWWRItENGbrQtMd5jQzoj8RhydxOa7FxoxuSBZ0P2H.UENXTUFJTi9jd3BvcnRhbDMx")
					.cookie("cms.sessionId", "HZ7A73E04A50524A36883FB49986531D00")
					.cookie("WMONID", "sIOEb4ZFkne")
					.cookie("cms.sessionId", "HZ7A73E04A50524A36883FB49986531D00")
					.header("Referer", "https://www.changwon.go.kr/cwportal/10310/10438/10439.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.tongyeong.go.kr/00852/00853/00858.web?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "C1265CDA5D55AA75CC93CFA1966E10A2")
					.cookie("wcs_bt", "13595e7e7d91444:1691221235")
					.header("Referer", "https://www.tongyeong.go.kr/00852/00853/00858.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.hadong.go.kr/media/00012.web?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "6799E41A9C14FD876CC6813ACE289FEA")
					.header("Referer", "https://www.hadong.go.kr/media/00012.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				String str1 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(4)").text();
				String str2 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(2)").text();
				String str3 = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text();
				
				arr[index][0] = "하동군";
				arr[index][1] = document.select("#body_content > div.list1f1t3i1 > ul > li:nth-child("+i+") > div > a > span > i > span:nth-child(1)").text().trim();
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
			response = Jsoup.connect("https://www.haman.go.kr/02385/02392/05764.web;jsessionid=P6vduLh1MIwQUiiqDa3maGdKDakzCTo5HPjVyFSoN140NKhTxaV1r1KyvtDZ5caD.Web-AP_servlet_engine2?cpage=1&gubun=present&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "-zKppTMlRAl::::BO5ABF69C2")
					.cookie("JSESSIONID", "P6vduLh1MIwQUiiqDa3maGdKDakzCTo5HPjVyFSoN140NKhTxaV1r1KyvtDZ5caD.Web-AP_servlet_engine2::::UO50846A67")
					.cookie("wcs_bt", "c3e9e3a48073d0:1691222764")
					.header("Referer", "https://www.haman.go.kr/02385/02392/05764.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("SESSION_NTIS", "kTJ8VpLVJmLf4qjJ2cfwL2J0Zdcpmqj88FQYMmJRWhHcTJmzJdLw!1455280335!1880260671!13002!-1")
					.header("Origin", "https://eminwon.hygn.go.kr")
					.header("Referer", "https://eminwon.hygn.go.kr/emwp/gov/mogaha/ntis/web/ofr/action/OfrAction.do")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C02%2C03%2C04%2C05%2C06%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.hc.go.kr/04923/04924/04948.web?dep_nm=&stype=not_ancmt_sj&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("cms.sessionId", "HZC452742BE74140DA8E6E5174C363EEB6")
					.cookie("JSESSIONID", "hJZGdjoXUxkjsgxO6kaNsv8aopWTT1awHuF2Sf2bVsivlJVrxulXmdls8UZHdWSo.SENXRUIvbWFpbjIy")
					.cookie("wcs_bt", "1275ff134609494:1691227520")
					.header("Referer", "https://www.hc.go.kr/04923/04924/04948.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.gb.go.kr/Main/page.do?bdName=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&mnu_uid=6789&p1=0&p2=0&dept_name=&dept_code=&BD_CODE=gosi_notice&B_START=2023-01-01&B_END=2030-08-05&key=2&word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "VtT9MJLK7gA")
					.cookie("JSESSIONID", "XVXVTXC3YlhaabigQp8gmdwSBQO8sV83HGgw1VviwhoRh8msxCqHmnE3uKUIkmhq.www_servlet_engine10")
					.header("Referer", "https://www.gb.go.kr/Main/page.do?bdName=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&mnu_uid=6789&p1=0&p2=0&dept_name=&dept_code=&BD_CODE=gosi_notice&B_START=2023-06-05&B_END=2023-08-05&key=2&word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.pohang.go.kr/pohang/10391/subview.do?enc=Zm5jdDF8QEB8JTJGZ29zaU5vdGljZSUyRnBvaGFuZyUyRjAlMkZsaXN0LmRvJTNGc3JjaENvbHVtbiUzRHNqJTI2c3JjaFdyZCUzRCVFQiVCMyVCNCVFQyU4MyU4MSVFQSVCMyU4NCVFRCU5QSU4RCUyNg%3D%3D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "GJgHxYuaBG7")
					.cookie("wcCookie", "125.179.129.136_T_29915_WC")
					.cookie("JSESSIONID", "5F68110C67E75935AE16122960118C46")
					.cookie("PHPSESSID", "p1oidfud3rblb73q4jkmajen70")
					.cookie("SESS_COUNTER_DENY", "1")
					.cookie("_gid", "GA1.3.1692623171.1691229071")
					.cookie("_gat_gtag_UA_114658245_1", "1")
					.cookie("wcs_bt", "127674644dd4af4:1691229099")
					.cookie("_ga_RFP16YRFCR", "GS1.1.1691229071.2.1.1691229099.0.0.0")
					.cookie("_ga", "GA1.3.2060520613.1688834499")
					.header("Referer", "https://www.pohang.go.kr/pohang/10391/subview.do?enc=Zm5jdDF8QEB8JTJGZ29zaU5vdGljZSUyRnBvaGFuZyUyRjAlMkZsaXN0LmRvJTNGc3JjaENvbHVtbiUzRHNqJTI2c3JjaFdyZCUzRCVFQiVCMyVCNCVFQyU4MyU4MSVFQSVCMyU4NCVFRCU5QSU4RCUyNg%3D%3D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("scg-lb-id", "GYJ-7-1691239028214")
					.cookie("LENA-UID", "6f29056f.5ffe94359cb30")
					.cookie("JSESSIONID", "656ABFD25AB2719AF4D2305DF8721964.f7b2399441ba00202")
					.cookie("scg-lb-id", "GYJ-5-1691239031138")
					.header("Origin", "https://www.gyeongju.go.kr")
					.header("Referer", "https://www.gyeongju.go.kr/open_content/ko/page.do?mnu_uid=423&")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("step=1&mnu_uid=423&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&sbtn=%EA%B2%80%EC%83%89")
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
					.cookie("JSESSIONID", "F76591CEE82545C319F0E5F31CEF0180.9c6ff5ba991206161")
					.header("Origin", "https://www.gc.go.kr")
					.header("Referer", "https://www.gc.go.kr/portal/saeol/gosi/list.do?mId=1202090200")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&perPageCount=10&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
					.cookie("JSESSIONID", "DAD22276D3D90BDAE712A7DF902D777B.6d29b244690400202")
					.cookie("L-VISITOR", "zsmpphfghsrcj")
					.header("Origin", "https://www.andong.go.kr")
					.header("Referer", "https://www.andong.go.kr/portal/saeol/gosi/list.do?mId=0401020100")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
					.cookie("JSESSIONID", "8ED448C79CA6604F8C391B8A9E99456F.e341321013f806161")
					.cookie("D_VISITOR_ID", "7d864fe4-cc97-bbb7-626e-b8a7ac0f258d")
					.header("Origin", "https://www.gumi.go.kr")
					.header("Referer", "https://www.gumi.go.kr/portal/saeol/gosiList.do?seCode=01&mId=0401030000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&notAncmtSeNm=&searchType=not_ancmt_sj&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
			response = Jsoup.connect("https://www.yeongju.go.kr/open_content/main/page.do?mnu_uid=10619&board_code=&srchColumn=title&srchKwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("L-VISITOR", "zl197iorkm9jb")
					.cookie("LENA-UID", "725ec7a1.602357e2485e6")
					.cookie("JSESSIONID", "4C17C0DB091DF10BE6D5E1AFBDC187E1.52f7dae6b29800202")
					.cookie("_ga_S4K7H79F7E", "GS1.1.1691278635.1.0.1691278635.0.0.0")
					.cookie("_ga", "GA1.1.364712492.1691278636")
					.header("Referer", "https://www.yeongju.go.kr/open_content/main/page.do?mnu_uid=10619&not_ancmt_se_code=01,04")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("JSESSIONID", "26CD7C2CC10B56864217E3C59232E72F.1cf85122e3d900202")
					.cookie("D_VISITOR_ID", "c6a45304-ad98-f039-dbe9-37a52d1a7894")
					.cookie("L-VISITOR", "x10eg9s4tf2o4q")
					.header("Origin", "https://www.yc.go.kr")
					.header("Referer", "https://www.yc.go.kr/portal/saeol/gosi/list.do?mId=0301040000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
			response = Jsoup.connect("https://www.sangju.go.kr/gosi/list.tc?mn=3016&pageIndex=1&pageSeq=2686&mgtNo=-1&notAncmtSeCode=01%2C02%2C03%2C04%2C05%2C07&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "GOLTxKjM_PC")
					.cookie("JSESSIONID", "F85025DC127CE40556638145F6C8B044")
					.header("Referer", "https://www.sangju.go.kr/gosi/list.tc?mn=3016&pageSeq=2686&paramIdx=&notAncmtSeCode=01%2C02%2C03%2C04%2C05%2C07")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("JSESSIONID", "5F094D1B004CFD22A1173838095C9327.9aaabb18386c06161")
					.cookie("wcs_bt", "80665c57a9663c:1691281124")
					.header("Origin", "https://www.gbmg.go.kr")
					.header("Referer", "https://www.gbmg.go.kr/portal/saeol/gosi/list.do?mId=0301060000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
					.cookie("JSESSIONID", "4DA402464B51445AA232A234808B2630.b5f9f3b63a0400262")
					.header("Origin", "https://www.gbgs.go.kr")
					.header("Referer", "https://www.gbgs.go.kr/open_content/ko/page.do?mnu_uid=2160")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("step=1&mnu_uid=2160&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.gunwi.go.kr/ko/page.do?step=1&mnu_uid=170&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&sbtn=%EA%B2%80%EC%83%89")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "AC74BBD5A188A51E849CF0A057487545")
					.header("Referer", "https://www.gunwi.go.kr/ko/page.do?step=1&mnu_uid=170&parm_mnu_uid=0&srchColumn=bod_title&srchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&sbtn=%EA%B2%80%EC%83%89")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.usc.go.kr/gosi/list.tc?mn=1271&pageIndex=1&pageSeq=1217&mgtNo=-1&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "42E7233A9F02D9E9E366C285BF50D3EE")
					.header("Referer", "https://www.usc.go.kr/gosi/list.tc?mn=1271&pageSeq=1217")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.cs.go.kr/news/00002679/00006203.web?cpage=1&stype=title&sstring=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "6ABB78F893ECF33FAC531C9F73CA37F8")
					.cookie("wcs_bt", "11dfaadbf7fa70:1691284405")
					.cookie("__utma", "122350804.1463765231.1691284406.1691284406.1691284406.1")
					.cookie("__utmc", "122350804")
					.cookie("__utmz", "122350804.1691284406.1.1.utmcsr")
					.cookie("__utmt", "1")
					.cookie("__utmb", "122350804.1.10.1691284406")
					.header("Referer", "https://www.cs.go.kr/news/00002679/00006203.web")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
				.cookie("HISTORY_SETTING_KEY", "a7623d4dec5902cd85c55e0ef2438b9d")
				.cookie("BROWSER", "YToxNTp7czoxODoiYnJvd3Nlcl9uYW1lX3JlZ2V4IjtzOjY6In5eLiokfiI7czoyMDoiYnJvd3Nlcl9uYW1lX3BhdHRlcm4iO3M6MToiKiI7czo2OiJwYXJlbnQiO3M6MTc6IkRlZmF1bHRQcm9wZXJ0aWVzIjtzOjc6ImNvbW1lbnQiO3M6MTU6IkRlZmF1bHQgQnJvd3NlciI7czo3OiJicm93c2VyIjtzOjE1OiJEZWZhdWx0IEJyb3dzZXIiO3M6MTQ6ImlzbW9iaWxlZGV2aWNlIjtzOjA6IiI7czo4OiJpc3RhYmxldCI7czowOiIiO3M6NzoidmVyc2lvbiI7czozOiIwLjAiO3M6ODoicGxhdGZvcm0iO3M6NzoidW5rbm93biI7czoxMToiZGV2aWNlX3R5cGUiO3M6NzoidW5rbm93biI7czo0OiJJRTExIjtiOjA7czo0OiJJRTEwIjtiOjA7czozOiJJRTkiO2I6MDtzOjM6IklFOCI7YjowO3M6MzoiSUU3IjtiOjA7fQ%3D%3D")
				.cookie("HUA", "15c2f6f9416d00cec8b4f729460293c0")
				.cookie("PHPSESSID", "717ca2105b7d149dae84bc2f8baf18e5")
				.cookie("_gid", "GA1.3.458802808.1691284770")
				.cookie("_gat", "1")
				.cookie("_ga_PGB338PMPP", "GS1.3.1691284770.3.0.1691284770.0.0.0")
				.header("Origin", "https://www.yyg.go.kr")
				.header("Referer", "https://www.yyg.go.kr/www/organization/yyg_news/notification")
				.header("Sec-Fetch-Dest", "document")
				.header("Sec-Fetch-Mode", "navigate")
				.header("Sec-Fetch-Site", "same-origin")
				.header("Sec-Fetch-User", "?1")
				.header("Upgrade-Insecure-Requests", "1")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
				.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"Windows\"")
				.requestBody("search_type=title&search_word=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.yd.go.kr/?page_id=763&pageid=1&mod=list&target=&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("PHPSESSID", "keh0unt20fapcfbp98rael9rng")
					.cookie("_ga_1QZKDCT5GJ", "GS1.1.1691285112.1.0.1691285112.0.0.0")
					.cookie("_ga", "GA1.1.1733179278.1691285113")
					.header("Referer", "https://www.yd.go.kr/?page_id=763")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.cheongdo.go.kr/open.content/ko/administration/news/announcement/?q=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("scg-lb-id", "CD-5-1691285745899")
					.cookie("current-language", "ko")
					.cookie("_gid", "GA1.3.1001491670.1691285388")
					.cookie("_gat_gtag_UA_34304715_5", "1")
					.cookie("_ga_Z1M421YBM6", "GS1.1.1691285387.1.0.1691285387.0.0.0")
					.cookie("_ga", "GA1.1.453151348.1691285388")
					.cookie("scg-lb-id", "CD-0-1691285748026")
					.header("Referer", "https://www.cheongdo.go.kr/open.content/ko/administration/news/announcement/")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("https://www.goryeong.go.kr/kor/boardList.do?IDX=154&BRD_ID=1023&searchType=ALL&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "BC77AD99C900999AEAAA1E6842FB6524")
					.header("Origin", "https://www.goryeong.go.kr")
					.header("Referer", "https://www.goryeong.go.kr/kor/boardList.do?IDX=154&BRD_ID=1023")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("BRD_ID=1023&IDX=154&page=1&searchType=ALL&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
			response = Jsoup.connect("https://www.sj.go.kr/page.do?mnu_uid=1044&srchDept=&srchColumn=bod_title&srchKwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("LENA-UID", "cd569603.60237317fd648")
					.cookie("L-VISITOR", "z1aokg5823j0mt")
					.cookie("JSESSIONID", "6089899957524DE72B15875DCCD37DA2.c95ddd18bd6500202")
					.header("Referer", "https://www.sj.go.kr/page.do?mnu_uid=1044")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
					.cookie("JSESSIONID", "FF73863519A630C312C515FF97EE7D4D.4d81888a0e8800202")
					.header("Origin", "https://www.chilgok.go.kr")
					.header("Referer", "https://www.chilgok.go.kr/portal/saeol/gosi/list.do?mId=0201030000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("page=1&seCode=01&searchType=tit&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();
			totNum = document.select("#list > table > tbody > tr > td.list_num").text().trim();
			
			// 데이터 있는지 체크
			if(Integer.valueOf(totNum) == 1) {
				arr[index][0] = "칠곡군";
				arr[index][1] = document.select("#list > table > tbody > tr > td.taL.list_tit > a").text().trim();
				arr[index][2] = document.select("#list > table > tbody > tr > td.list_dept").text().trim();
				arr[index][3] = document.select("#list > table > tbody > tr > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = document.select("#list > table > tbody > tr > td:nth-child(2)").text().trim();
				index++;
			}

			totNum = document.select("#list > table > tbody > tr:nth-child(1) > td.taL.list_tit > a").text().trim();
			
			if(Integer.valueOf(totNum) >1){
				// 출력 결과가 여러개인 경우
				for(int i = 1;i<=10;i++) {
					arr[index][0] = "칠곡군";
					arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.taL.list_tit > a").text().trim();
					arr[index][2] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_dept").text().trim();
					arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
					arr[index][4] = document.select("#list > table > tbody > tr:nth-child("+i+") > td:nth-child(2)").text().trim();
					index++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//예천군 고시 검색 안됨
		
		//봉화군
		System.out.println("봉화군 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.bonghwa.go.kr/open.content/ko/news/news/announcement/bonghwa/?q=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("current-language", "ko")
					.cookie("_ga", "GA1.3.306837335.1691306894")
					.cookie("_gid", "GA1.3.829975885.1691306894")
					.cookie("_gat", "1")
					.cookie("_ga_62BKPKE9DQ", "GS1.3.1691306896.1.0.1691306896.60.0.0")
					.header("Referer", "https://www.bonghwa.go.kr/open.content/ko/news/news/announcement/bonghwa/")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			response = Jsoup.connect("http://www.uljin.go.kr/index.uljin?menuCd=DOM_000000103002007001&startPage=1&schDepNm=&searchType=NOT_ANCMT_SJ&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("LENA-UID", "1e1690f5.6023eed16b153")
					.cookie("L-VISITOR", "z6vpb5a1jp9v5n")
					.cookie("JSESSIONID", "C35A33E9CB48990D347679D3D71B342D.eda2e9ec52b800202")
					.cookie("_ga_6FG504W6WQ", "GS1.1.1691319153.1.0.1691319153.60.0.0")
					.cookie("_gid", "GA1.3.903838536.1691319153")
					.cookie("_gat_gtag_UA_110603752_1", "1")
					.cookie("_gat_gtag_UA_109061437_1", "1")
					.cookie("_ga_295HTZSF5S", "GS1.1.1691319153.1.0.1691319153.0.0.0")
					.cookie("_ga", "GA1.1.699252795.1691319153")
					.header("Referer", "http://www.uljin.go.kr/index.uljin?menuCd=DOM_000000103002007000")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
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
			response = Jsoup.connect("https://www.ulleung.go.kr/ko/page.do?mnu_uid=571&srchColumn=title&srchKwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "EE3151AC76270B4FFAD68B27CDED30BF.tomcat01")
					.cookie("popup01", "done")
					.header("Referer", "https://www.ulleung.go.kr/ko/page.do?mnu_uid=571&srchColumn=title&srchKwd=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
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
			url = "https://www.seoul.go.kr/news/news_notice.do#list/1/cntPerPage=20&srchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D";
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
					.cookie("_ga_F7KMB3ZTZQ", "GS1.3.1693827103.4.0.1693827103.0.0.0")
					.cookie("WMONID", "MxHGZWKzQ6w")
					.cookie("ICSESSIONID", "k1Ag1LEcq1bWw746JkMV0yx0qW4JNmiYFadpSejUO0ZR19Oofr4B!-2060130034!1694499223!7012!-1")
					.header("Origin", "http://announce.incheon.go.kr")
					.header("Referer", "http://announce.incheon.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y&sido=ic")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=d55481d34760f86f8b6989e3c391475b&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BA%B8%BB%F3%B0%E8%C8%B9")
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
			response =  Jsoup.connect("https://www.daejeon.go.kr/drh/drhGosiList.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "QBJ9X43zNo85YoamqZ9agS46hqfVsJT5ctUK0qNswlHIPhG1x0bgHHh5yGZpKHqH.ZGFlamVvbi9IT01FUEFHRV8y")
					.cookie("JSESSIONID", "QBJ9X43zNo85YoamqZ9agS46hqfVsJT5ctUK0qNswlHIPhG1x0bgHHh5yGZpKHqH.ZGFlamVvbi9IT01FUEFHRV8y")
					.header("Origin", "https://www.daejeon.go.kr")
					.header("Referer", "https://www.daejeon.go.kr/drh/drhGosiList.do?gosigbn=A&menuSeq=1908")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("pageIndex=1&menuSeq=1908&sno=&conifmstdt=&conifmenddt=&gosigbn=A&conannounceno=&deptnm=&title=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.requestBody("appCode=&rescCode=&TOKEN_SAB=742386bb2c08a14ee075ab361541775c&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BA%B8%BB%F3%B0%E8%C8%B9")
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
			response = Jsoup.connect("https://www.busan.go.kr/nbgosi/list?conIfmStdt=&conIfmEnddt=&conGosiGbn=&schKeyType=A&srchText=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("WMONID", "mSf9ZsAHaan")
					.cookie("_ga", "GA1.3.1089900939.1691418271")
					.cookie("BSJSESSIONID", "IB5gChtIvDoGwdF5uWVp3UEDauytRXh4cdNtQHFVXHpngFL5xw12TKctRG4rawBR.QlNXQVMwMS9NYWluMTU")
					.cookie("SCSSOAUTH", "")
					.cookie("_EXEN", "1")
					.cookie("_gid", "GA1.3.1330014142.1693829238")
					.cookie("_gat", "1")
					.cookie("_ga_7P3LX98G56", "GS1.3.1693829240.2.0.1693829240.0.0.0")
					.header("Referer", "https://www.busan.go.kr/nbgosi")
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
					.header("Referer", "https://sido.gwangju.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y")
					.header("Sec-Fetch-Dest", "iframe")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=5d2fc2d5eb3bf6ef9a5424747e9b8497&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BA%B8%BB%F3%B0%E8%C8%B9")
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
					.cookie("JSESSIONID", "sd4zwkevBn5MFr1zRlHRQ1jJV1y2XXXycnpA54XpzUO49ALJxOG1RAsPgS6MaXfD.UFVMU0FOL05ld1BvcnRhbDEtMTE")
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
					.requestBody("srchGubun=&srchType=srchSj&srchWord=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.requestBody("pageUnit=10&pageSize=10&pageIndex=1&gubun=C1&searchStartDate=&searchEndDate=&searchCondition=not_ancmt_sj&searchKeyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&submitTy=%EA%B2%80%EC%83%89")
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
					.cookie("_gat", "1")
					.header("Origin", "http://sido.jeju.go.kr")
					.header("Referer", "http://sido.jeju.go.kr/citynet/jsp/sap/SAPGosiBizProcess.do?command=searchList&flag=gosiGL&svp=Y")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
					.requestBody("appCode=&rescCode=&TOKEN_SAB=bd6d5bf0354ea784854e3f9ab1a45ba3&sno=&flag=gosiGL&gosiGbn=&currPageNo=1&conDeptCode=&conIfmStdt=&conIfmStdt_Date=&conIfmEnddt=&conIfmEnddt_Date=&conAnnounceNo=&conGosiGbn=&conDeptNm=&conTitle=%BA%B8%BB%F3%B0%E8%C8%B9")
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
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=Y&ofr_pageSize=10&not_ancmt_se_code=01%2C04&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
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
					.requestBody("epcCheck=&pageIndex=&jndinm=OfrNotAncmtEJB&context=NTIS&method=selectListOfrNotAncmt&methodnm=selectListOfrNotAncmtHomepage&not_ancmt_mgt_no=&homepage_pbs_yn=Y&subCheck=N&ofr_pageSize=10&not_ancmt_se_code=01%2C04%2C07&title=%EA%B3%A0%EC%8B%9C%EA%B3%B5%EA%B3%A0&cha_dep_code_nm=&initValue=Y&countYn=Y&list_gubun=&not_ancmt_sj=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&not_ancmt_cn=&dept_nm=&cgg_code=&yyyy=&yyyymmdd=&recent_mm=&last_mm=&nodate_recent_mm=&nodate_last_mm=&not_ancmt_reg_no=&Key=B_Subject&temp=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			
			Thread.sleep(10000);
			
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
	
	
	public String [][] GitaCrawling() {
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
		// 한국부동산원
		System.out.println("한국부동산원 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.reb.or.kr/reb/na/ntt/selectNttList.do?mi=9583&bbsId=1143")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.cookie("SCOUTER", "z35gi77337gg8p")
					.cookie("XTVID", "A220521234935052572")
					.cookie("xloc", "1368X912")
					.cookie("_harry_lang", "ko-KR")
					.cookie("WMONID", "NLElGzN7CLF")
					.cookie("_ga", "GA1.1.723776318.1653144575")
					.cookie("_ga_RPHXQ06B2R", "GS1.1.1687962933.2.1.1687963625.60.0.0")
					.cookie("JSESSIONID", "FB9FBFD0A34A670D0A3217177A567E4C")
					.cookie("_harry_fid", "hh-1018302933")
					.cookie("_harry_hsid", "A230813200640045101")
					.cookie("_harry_dsid", "A230813200640046672")
					.cookie("_ga_0WH3G11899", "GS1.1.1691924800.16.1.1691925424.0.0.0")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "none")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contView > div.BD_list > table > tbody > tr:nth-child("+i+") > td.al.mBlock > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "한국부동산원";
				arr[index][1] = document.select("#contView > div.BD_list > table > tbody > tr:nth-child("+i+") > td.al.mBlock > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#contView > div.BD_list > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 한국토지주택공사
		System.out.println("한국토지주택공사 크롤링 시작");

		try{
			response = Jsoup.connect("http://bosang.lh.or.kr/notice/notice.asp?page=1&board_id=board_one")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("ASPSESSIONIDCSDSBRQA", "KFBKIBEAMHJOIAHHBECMDPHN")
					.cookie("_gid", "GA1.3.1818200885.1691925961")
					.cookie("_gat_gtag_UA_133417796_7", "1")
					.cookie("_ga_JV35K3NGVY", "GS1.1.1691925961.1.1.1691926124.0.0.0")
					.cookie("_ga", "GA1.1.935373356.1687948099")
					.header("Referer", "http://bosang.lh.or.kr/notice/notice.asp?page=1&board_id=board_one")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#wrap > div > div.content_wrap.border_none > table > tbody > tr:nth-child("+i+") > td.td01 > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "한국토지주택공사";
				arr[index][1] = document.select("#wrap > div > div.content_wrap.border_none > table > tbody > tr:nth-child("+i+") > td.td01 > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#wrap > div > div.content_wrap.border_none > table > tbody > tr:nth-child("+i+") > td:nth-child(3)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		// 경기주택공사
		System.out.println("경기주택공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.gh.or.kr/gh/reward-announcement.do?mode=list&srSearchKey=&srSearchVal=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("gh-month", "Y")
					.cookie("gh-day", "Y")
					.cookie("gh-30min", "Y")
					.cookie("WMONID", "RcYFuhlLOCE")
					.cookie("JSESSIONID", "u5pNEZ9tv2xfHYJjKf2Jb86SFn8vwtKJgWtisSjQrDmxTI0gxha8lp37qb0f79e4.amV1c19kb21haW4vZ2hfaG9tZXBhZ2Vfd2FzXzE")
					.cookie("locale", "ko")
					.header("Referer", "https://www.gh.or.kr/gh/reward-announcement.do?mode=list&srSearchKey=&srSearchVal=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#cms-content > div > div > div > div.board-list-table-wrap > table > tbody > tr:nth-child("+i+") > td.title > div > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "경기주택공사";
				arr[index][1] = document.select("#cms-content > div > div > div > div.board-list-table-wrap > table > tbody > tr:nth-child("+i+") > td.title > div > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = ("20"+document.select("#cms-content > div > div > div > div.board-list-table-wrap > table > tbody > tr:nth-child("+i+") > td.date").text()).replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		// 인천도시공사
		System.out.println("인천도시공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.ih.co.kr/open_content/bbs.do?act=list&bcd=notice&bgcd=site1&cate_cd=&keyfield=bbs_title&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=37&y=24")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "03cmIaxPxgbyE2gf1hFhHpj7I9X0ZqG9gMXjBr4GcUWcPab1xFjjIih3aQZUbra1.amV1c19kb21haW4vc2VydmVyMQ")
					.cookie("_gid", "GA1.3.1941251421.1691926770")
					.cookie("_gat_gtag_UA_97192481_1", "1")
					.cookie("_ga_ZZQ7BQ5XBB", "GS1.1.1691926769.1.1.1691927012.0.0.0")
					.cookie("_ga", "GA1.1.1328232229.1691926770")
					.header("Referer", "https://www.ih.co.kr/open_content/bbs.do?act=list&bcd=notice&bgcd=site1&cate_cd=&keyfield=bbs_title&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=41&y=4")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#detail_con > div.bbs_con > div > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "인천도시공사";
				arr[index][1] = document.select("#detail_con > div.bbs_con > div > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#detail_con > div.bbs_con > div > table > tbody > tr:nth-child("+i+") > td:nth-child(6)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 부산도시공사
		System.out.println("부산도시공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.bmc.busan.kr/bmc/bbs/list.do?ptIdx=770&mId=0703000000")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("close", "Y")
					.cookie("close4", "Y")
					.cookie("JSESSIONID", "6F04343659E252DFACBDCB02E14856F4")
					.cookie("NetFunnel_ID", "")
					.header("Origin", "https://www.bmc.busan.kr")
					.header("Referer", "https://www.bmc.busan.kr/bmc/bbs/list.do?ptIdx=770&mId=0703000000")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("cancelUrl=%2Fbmc%2Fbbs%2Flist.do%3FptIdx%3D770%26mId%3D0703000000&page=1&searchType=b_title&searchTxt=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&searchBt=%EA%B2%80%EC%83%89")
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
				arr[index][0] = "부산도시공사";
				arr[index][1] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_tit > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#list > table > tbody > tr:nth-child("+i+") > td.list_date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 대구도시개발공사
		System.out.println("대구도시개발공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.dudc.or.kr/customer/notice")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "BE1DF65F452B367F35834C2C58A16830")
					.header("Origin", "https://www.dudc.or.kr")
					.header("Referer", "https://www.dudc.or.kr/customer/notice")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("_csrf=c547e962-f49f-453e-a7ab-c80bf5985bdd&currentPage=1&searchKind=BOARD_TOTAL&searchStr=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
			Thread.sleep(10000);
			
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#cont_body > div.board_s1 > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "대구도시개발공사";
				arr[index][1] = document.select("#cont_body > div.board_s1 > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#cont_body > div.board_s1 > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		// 대전도시공사
		System.out.println("대전도시공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.dcco.kr/web/board/list.do?mId=113&ts_noticecode=5&keyField=subject&key=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "bebdGnFQYP1rvFCaN1VbWpKsn4R1aI1cgQrTxAjkQ8g6Nd11xUetWLUa2uLEBWMQ.bmV3ZGNjb3dhcy9kY2NvX3dlYg")
					.header("Referer", "https://www.dcco.kr/web/board/list.do?mId=113&ts_noticecode=5&keyField=subject&key=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#cms_board_article > table > tbody > tr:nth-child("+i+") > td.subject > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "대전도시공사";
				arr[index][1] = document.select("#cms_board_article > table > tbody > tr:nth-child("+i+") > td.subject > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#cms_board_article > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		// 한강유역환경청
		System.out.println("한강유역환경청 크롤링 시작");

		try{
			response = Jsoup.connect("https://me.go.kr/hg/web/board/list.do?menuId=1256&boardMasterId=342&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=20&y=10")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("elevisor_for_j2ee_uid", "9pa3t9t9jdyd8")
					.cookie("userCookieId", "0c05d5ebbec14edeae63b9002b7c787d")
					.cookie("JSESSIONID", "VeMgqV0Y8yzsz-+lLJV73ZsN.mehome1")
					.cookie("hg_notice", "hg_notice")
					.header("Referer", "https://me.go.kr/hg/web/board/list.do?menuId=1256&boardMasterId=342&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=0&y=0")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "한강유역환경청";
				arr[index][1] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 낙동강유역환경청
		System.out.println("낙동강유역환경청 크롤링 시작");

		try{
			response = Jsoup.connect("https://me.go.kr/ndg/web/board/list.do?menuId=3284&boardMasterId=156&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=10&y=8")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("elevisor_for_j2ee_uid", "9pa3t9t9jdyd8")
					.cookie("userCookieId", "0c05d5ebbec14edeae63b9002b7c787d")
					.cookie("JSESSIONID", "VeMgqV0Y8yzsz-+lLJV73ZsN.mehome1")
					.cookie("hg_notice", "hg_notice")
					.header("Referer", "https://me.go.kr/ndg/web/board/list.do?menuId=3284&boardMasterId=156&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=17&y=7")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 3;i<=12;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "낙동강유역환경청";
				arr[index][1] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		// 금강유역환경청
		System.out.println("금강유역환경청 크롤링 시작");

		try{
			response = Jsoup.connect("https://me.go.kr/gg/web/board/list.do?menuId=2284&boardMasterId=235&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=4&y=13")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("elevisor_for_j2ee_uid", "9pa3t9t9jdyd8")
					.cookie("userCookieId", "0c05d5ebbec14edeae63b9002b7c787d")
					.cookie("JSESSIONID", "VeMgqV0Y8yzsz-+lLJV73ZsN.mehome1")
					.cookie("hg_notice", "hg_notice")
					.header("Referer", "https://me.go.kr/gg/web/index.do?menuId=2283")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 2;i<=11;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "금강유역환경청";
				arr[index][1] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		// 영산강유역환경청
		System.out.println("영산강유역환경청 크롤링 시작");

		try{
			response = Jsoup.connect("https://me.go.kr/ysg/web/board/list.do?menuId=4284&boardMasterId=295&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=26&y=21")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("elevisor_for_j2ee_uid", "9pa3t9t9jdyd8")
					.cookie("userCookieId", "0c05d5ebbec14edeae63b9002b7c787d")
					.cookie("JSESSIONID", "VeMgqV0Y8yzsz-+lLJV73ZsN.mehome1")
					.cookie("hg_notice", "hg_notice")
					.header("Referer", "https://me.go.kr/ysg/web/board/list.do?menuId=4284&boardMasterId=295&boardCategoryId=&maxIndexPages=10&condition.hideCate=&condition.createId=&decorator=&condition.proxyParam1=&condition.proxyParam2=&condition.proxyParam3=&proxyListPath=&proxyReadPath=&searchKey=title&searchValue=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&condition.fromDate=&picker_year=2023&picker_month=8&condition.toDate=&picker_year=2023&picker_month=8&x=6&y=7")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "영산강유역환경청";
				arr[index][1] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td.left_td > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#content > div > div > table > tbody > tr:nth-child("+i+") > td:nth-child(4)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		// 한국농어촌공사
		System.out.println("한국농어촌공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.ekr.or.kr/planweb/board/list.krc")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("JSESSIONID", "fyzXEeL0cCVIkIYCO6rWJeZOvasPkrswgGsZ6FRXkCoBwRW2xO8amVsKKiwACnAC.amV1c19kb21haW4vbmV3X3d3dw")
					.cookie("_BS_GUUID", "BY9lWrrXFvuuzVoprfFemmfe64fMpJP0IZlErNC7")
					.cookie("_TRK_UID", "06d85b130c6b023c20d0efb3a2dd0503:1:0:1691932351087")
					.cookie("_TRK_SID", "0d2aaf09bb20eb73a06a9d7389000403")
					.cookie("_TRK_CR", "https%3A%2F%2Fwww.google.com%2F")
					.cookie("_TRK_EX", "4")
					.header("Origin", "https://www.ekr.or.kr")
					.header("Referer", "https://www.ekr.or.kr/planweb/board/list.krc?contentUid=402880317cc0644a017cc0c9da9f0120&boardUid=402880317cc0644a017cc5e8000f06b7&contentUid=402880317cc0644a017cc0c9da9f0120&subPath=")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("boardUid=402880317cc0644a017cc5e8000f06b7&contentUid=402880317cc0644a017cc0c9da9f0120&categoryUid1=&searchType=dataTitle&keyword=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#boardWrap > div.list_group > table > tbody > tr:nth-child("+i+") > td.title > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "한국농어촌공사";
				arr[index][1] = document.select("#boardWrap > div.list_group > table > tbody > tr:nth-child("+i+") > td.title > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#boardWrap > div.list_group > table > tbody > tr:nth-child("+i+") > td.date").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		// 한국수자원공사
		System.out.println("한국수자원공사 크롤링 시작");

		try{
			response = Jsoup.connect("https://www.kwater.or.kr/news/sub01/noti01List.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Cache-Control", "max-age=0")
					.header("Connection", "keep-alive")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.cookie("WMONID", "5xP9JpLMowh")
					.cookie("JSESSIONID", "LzRMWZz1C7vHyaNDgy6N5kg5r6jR3xT7gUnGfRZL0xz1sOSNxzoT90aT609QPSZv.komwaso2_servlet_engine1")
					.cookie("_gid", "GA1.3.80521719.1691932913")
					.cookie("_gat", "1")
					.cookie("_ga", "GA1.1.1865274141.1691932913")
					.cookie("_ga_8QDK70588W", "GS1.1.1691932912.1.1.1691932954.0.0.0")
					.header("Origin", "https://www.kwater.or.kr")
					.header("Referer", "https://www.kwater.or.kr/news/sub01/noti01List.do?")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.requestBody("s_mid=105&brdId=KO27&orderByField=SEQ&orderByDirection=DESC&s_type=all&type=A&s_text=%EB%B3%B4%EC%83%81%EA%B3%84%ED%9A%8D&x=12&y=23")
					.method(org.jsoup.Connection.Method.POST)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#contents > div.conInner > table > tbody > tr:nth-child("+i+") > td.taLeft28 > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "한국수자원공사";
				arr[index][1] = document.select("#contents > div.conInner > table > tbody > tr:nth-child("+i+") > td.taLeft28 > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#contents > div.conInner > table > tbody > tr:nth-child("+i+") > td:nth-child(7)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
				arr[index][4] = "";
				index++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		// 국가철도공단
		System.out.println("국가철도공단 크롤링 시작");

		try{
			response = Jsoup.connect("https://land.kr.or.kr/ntfc/sltCmpnstnPlnListVw.do")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Connection", "keep-alive")
					.cookie("JSESSIONID", "3RkdESgO1WNhZ7QCV1xRy63L1fTDWYZjgutZy0r4qdg2SiCuxlLT8Inx08GwLLJK.amV1c19kb21haW4vbGFuZA")
					.header("Referer", "https://land.kr.or.kr/ntfc/sltCmpnstnPlnListVw.do")
					.header("Sec-Fetch-Dest", "document")
					.header("Sec-Fetch-Mode", "navigate")
					.header("Sec-Fetch-Site", "same-origin")
					.header("Sec-Fetch-User", "?1")
					.header("Upgrade-Insecure-Requests", "1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
					.header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
					.header("sec-ch-ua-mobile", "?0")
					.header("sec-ch-ua-platform", "\"Windows\"")
					.method(org.jsoup.Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
				
			document = response.parse();

			for(int i = 1;i<=10;i++) {
				// 데이터 있는지 체크
				if(document.select("#content > div.con_wrap > form > table > tbody > tr:nth-child("+i+") > td.txt_left > a").text().equals("")) {
					break;
				}
				// 기본정보 불러오기
				arr[index][0] = "국가철도공단";
				arr[index][1] = document.select("#content > div.con_wrap > form > table > tbody > tr:nth-child("+i+") > td.txt_left > a").text().trim();
				arr[index][2] = "";
				arr[index][3] = document.select("#content > div.con_wrap > form > table > tbody > tr:nth-child("+i+") > td:nth-child(5)").text().replaceAll("(\\/|\\.|\\-)", "").trim();
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
