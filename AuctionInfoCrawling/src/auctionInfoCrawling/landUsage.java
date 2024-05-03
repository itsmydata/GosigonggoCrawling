package auctionInfoCrawling;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class landUsage{
	
	public String[] landUsage(String addr){
		String id = "webdriver.chrome.driver";
		String path = "C:\\Users\\lemon\\eclipse-workspace\\chromedriver_win32\\chromedriver.exe"; //드라이버 경로

		String[] arr = new String[5];
		
			//크롬 드라이버 사용하기 위해 로딩
			System.setProperty(id, path);
			
			//크롬 브라우저를 열어줄 때 사용할 옵션들 설정할 수 있는 객체 만들기
			ChromeOptions options = new ChromeOptions();
			
			String url = "http://www.eum.go.kr/web/am/amMain.jsp";
			
			//아까 설정해준 옵션들을 기준으로 크롬 드라이버 실행하기
			WebDriver driver = new ChromeDriver(options);
			
			//실행된 드라이버로 주어진 url 접속시키기
			driver.get(url);
			//실행할 땐, 자바어플리케이션으로 실행하기
			
			//url이 실행이 아래 코드보다 느리게 실행될 수도 있으니까 Thread 써주기
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
				return arr;
			}
			
			//페이지내에서.요소찾기("addrTxt_back"라는 아이디로); 라는 뜻
			WebElement searchInput = driver.findElement(By.className("addrTxt_back"));
			
			//검색창 클릭
			searchInput.click();
			
			//검색창에 주소 문자열 전송하기
			searchInput.sendKeys(addr);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
				driver.quit();
				return arr;
			}
			
			//driver에서 "ico01"라는 id를 가지고 있는 웹 요소 찾기
			WebElement searchBtn;
			
			try {
				searchBtn = driver.findElement(By.className("ico01"));
				//검색 버튼 클릭
				searchBtn.click();
			} catch (Exception e) {
				e.printStackTrace();
				driver.quit();
				return arr;
			}
			
			try {
				// 토지이용계획 사이트 문제로 인해 바로 불러오지 못할 때가 있어 sleep 추가
				Thread.sleep(1000);
				//데이터 불러오기
				String jimok = driver.findElement(By.id("present_class_val")).getAttribute("value"); //지목
				String area = driver.findElement(By.id("present_area")).getText(); //면적
				String loca1 = driver.findElement(By.id("present_mark1")).getText(); //국계법에 따른 지역지구
				String loca2 = driver.findElement(By.id("present_mark2")).getText(); //기타법에 따른 지역지구
				String loca3 = driver.findElement(By.id("present_mark3")).getText(); //「토지이용규제 기본법 시행령」제9조 제4항 각 호에 해당되는 사항
	
				loca1 = loca1.replace("\n", "");
				loca2 = loca2.replace("\n", "");
				loca3 = loca3.replace("\n", "");
				
				arr[0] = jimok;
				arr[1] = area;
				arr[2] = loca1;
				arr[3] = loca2;
				arr[4] = loca3;
			} catch (Exception e) {
				e.printStackTrace();
			}
            try {
                Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
                Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
                Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
                Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
            } catch (IOException e) {
                System.out.println("Failed to close one or more driver exe files");
            }
			driver.quit();
			return arr;
	}
}
