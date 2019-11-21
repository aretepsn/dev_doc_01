package ttlabc.cms.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 시간관련 유틸
 * @author aretett
 *
 */
public class DateTimeUtils {

	public static void main(String args[]){
		DateTimeUtils util = new DateTimeUtils();
		
		String returnVal = "";
//		//현재시간 구하기
//		returnVal = util.getSystemTime("yyyy-MM-dd HH:mm:ss");
//		System.out.println(returnVal);
//		//현재요일 구하기
//		returnVal = util.getDayOfWeek("EN");
//		System.out.println(returnVal);
//		//일수 더하기/빼기
		try{
			returnVal = util.getAddDay("2017-08-25", -30);
		}catch(Exception e){}
		System.out.println(returnVal);
//		//날짜와 날짜사이의 일수 구하기
//		try{
//			returnVal = util.getTermDay("2017-08-29", "2017-08-28");
//		}catch(Exception e){}
//		System.out.println(returnVal);
		

		returnVal = util.getSystemTime("yyyyMMdd");
		System.out.println(returnVal);
	}

	/**
	 * 현재시간 구하기 [시스템의 타이머를 이용하는 방법]	
	 * @param getTimeType
	 * @return String
	 */
	public String getSystemTime(String getTimeType){
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat(getTimeType);
		String returnVal = dayTime.format(new Date(time));
		return returnVal;
	}
	
	/**
	 * 현재 요일구하기
	 * @param lang (KO,EN)
	 * @return String
	 */
	public String getDayOfWeek(String lang){
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("E", Locale.KOREAN);
		if( lang.equals("EN") ) {
			dayTime = new SimpleDateFormat("E", Locale.ENGLISH);
		}
		String returnVal = dayTime.format(new Date(time));
		return returnVal;
	}
	
	/**
	 * 일수 더하기/빼기
	 * @param date
	 * @param day
	 * @return
	 */
	public String getAddDay(String date, int day) throws Exception{
		String returnVal = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date tdate = formatter.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(tdate);
		cal.add(Calendar.DATE, day);
		returnVal = formatter.format(cal.getTime());
		return returnVal;
	}
	
	/**
	 * 날짜와 날짜사이의 일수 구하기
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getTermDay(String startDate, String endDate) throws Exception{
		String returnVal = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = formatter.parse(startDate);
		Date eDate = formatter.parse(endDate);
		long diff = eDate.getTime() - sDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		returnVal = Long.toString(diffDays);
		
		return returnVal;
	}
}
