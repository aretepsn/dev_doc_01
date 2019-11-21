package ttlabc.cms.com;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 자바 유틸
 * @author aretett
 *
 */
public class Utils {

	public static void main(String args[]){
		Utils util = new Utils();
		//암호화
//		System.out.println( "1111 -->" + new Utils().encrypt("1111"));
		//난수 발생
//		System.out.println(util.landom(10));
		//널체크
		String abc = "aa";
		System.out.println( util.chkNull(abc) );
		
	}
	
	/**
	 * 암호화 SHA-256
	 * @param planText
	 * @return String
	 * ref : http://18281818.tistory.com/83
	 */
	public String encrypt(String planText) { 
		try{ 
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			md.update(planText.getBytes()); 
			byte byteData[] = md.digest(); 
			StringBuffer sb = new StringBuffer(); 
			for (int i = 0; i < byteData.length; i++) { 
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); 
			}
			StringBuffer hexString = new StringBuffer(); 
			for (int i=0;i<byteData.length;i++) { 
				String hex=Integer.toHexString(0xff & byteData[i]); 
				if(hex.length()==1){ 
					hexString.append('0'); 
				}
				hexString.append(hex);
			}
			return hexString.toString(); 
		}catch(Exception e){ 
			e.printStackTrace(); throw new RuntimeException(); 
		}
	}
	
	/**
	 * 난수 발생
	 * @param size
	 * @return
	 */
	public String landom(long size){
		String returnVal = "";
		for(int i=0;i<size;i++){
			returnVal += "" + (int)(Math.random() * 10);
		}
		return returnVal;
	}
	/**
	 * null 체크
	 * @param paramName
	 * @return
	 */
	public boolean chkNull(String paramName){
		boolean returnVal	= false;
		if( paramName == null ){
			returnVal	= true;
		}else if( paramName.equals("") ){
			returnVal	= true;
		}
		return returnVal;
	}

}
