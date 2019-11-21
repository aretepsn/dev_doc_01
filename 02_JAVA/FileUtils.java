package ttlabc.cms.com;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;

/**
 * 파일관련 유틸
 * @author aretett
 *
 */
public class FileUtils {
	public static void main(String args[]){
		FileUtils fUtil = new FileUtils();
		
		//파일사이즈 구하기
//		String fileSize = fUtil.getFileSize("D:/download/VOD/악녀.mp4","Bytes");
//		System.out.println(fileSize);
		//파일이동
//		boolean aa = fUtil.fileMove("D:/workspace/uploads/temp/20170912/20170912_00993", "D:/workspace/uploads/20170912/20170912_00993");
//		System.out.println(aa);
		///파일복사
//		String sourceFile	= "D:/download/VOD/옥주현_요가.mp4";
//		String targetFile	= "D:/download/VOD/옥주현_요가2.mp4";
//		try{	
//			boolean result = fUtil.fileCopy( sourceFile, targetFile);
//			System.out.println(result);
//		}catch(Exception e){
//			System.out.println(e);
//		}
		
		//파일타입추출[VOD,MUSIC]
//		System.out.println( fUtil.getFileType("옥주현_요가.mp4") );
//		//파일재생시간추출
//		System.out.println( fUtil.getFileDuration("옥주현_요가.mp4") );
		//디렉토리 생성
//		System.out.println( fUtil.makeDir("D:/workspace/uploads/temp/20170831") );
		
		//이미지 추출
//		String fileFullPath = "D:/workspace/uploads/temp/20170912/20170912_00993";
//		System.out.println( fUtil.getImageSize(fileFullPath,"W") );
//		System.out.println( fUtil.getImageSize(fileFullPath,"H") );
		//파일확장자 추출
//		String aaa = "ea35_35_i2.jpg";
//		System.out.println( fUtil.getFileExtension(aaa) );
		
	}
	
	/**
	 * 파일확장자 추출
	 * @param fileName
	 * @return
	 */
	public String getFileExtension(String fileName){
		return fileName.split("\\.")[1].toUpperCase();
	}
	/**
	 * 이미지 크기 추출
	 * @param fileFullPath
	 * @return
	 */
	public String getImageSize(String fileFullPath, String gubun){
		Image img = new ImageIcon(fileFullPath).getImage();
		if( gubun.equals("W") ){
			return "" + img.getWidth(null);
		}else{
			return "" + img.getHeight(null);
		}
	}
	/**
	 * 파일복사
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws Exception
	 */
	public boolean fileCopy(String sourceFile, String targetFile) throws Exception{
//		출처: http://fruitdev.tistory.com/87 [과일가게 개발자]
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		  
		FileChannel fcin =  inputStream.getChannel();
		FileChannel fcout = outputStream.getChannel();
		  
		long size = fcin.size();
		fcin.transferTo(0, size, fcout);
		  
		fcout.close();
		fcin.close();
		  
		outputStream.close();
		inputStream.close();
		return true;
	}
	/**
	 * 파일 사이즈 구하기
	 * @param fileFullPath 파일풀경로
	 * @param sizeUnit 사이즈단위[Byte,KB,MB,GB]
	 * @return String
	 */
	public String getFileSize(String fileFullPath, String sizeUnit){
		String returnVal = "";
		File file = new File(fileFullPath);
		if( file.exists() ){
			long fileSize = file.length();
			if( sizeUnit.equals("KB") ){
				fileSize = fileSize / 1024;
			}else if( sizeUnit.equals("MB") ){
				fileSize = fileSize / 1024 / 1024;
			}else if( sizeUnit.equals("GB") ){
				fileSize = fileSize / 1024 / 1024 / 1024;
			}
			returnVal = ""+fileSize;
			
		}else{
			returnVal = "notFound";
		}
		return returnVal;
	}
	
	/**
	 * 파일이동
	 * @param sourceFileNm
	 * @param targetFileNm
	 * @return
	 */
	public boolean fileMove(String sourceFileNm, String targetFileNm) {
		File file = new File(sourceFileNm);
		File fileNew= new File(targetFileNm);
		
		//이동할 디렉토리 유무 체크
		String temp = targetFileNm.substring(0,targetFileNm.lastIndexOf("/") );
		if( !new File(temp).exists() ){
			return false;
		}
		
		if( file.exists() ) {
			file.renameTo(fileNew);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 파일타입 추출
	 * @param fileName
	 * @return
	 */
	public String getFileType(String fileName){
		String returnVal ="VOD";
		return returnVal;
	}
	/**
	 * 파일 재생시간
	 * @param fileName
	 * @return 초단위
	 */
	public String getFileDuration(String fileName){
		String returnVal ="3600";
		return returnVal;
	}
	
	/**
	 * 디렉토리 생성
	 * @param dirName
	 * @return
	 */
	public boolean makeDir(String dirName){
		File chkDir = new File(dirName);
		boolean makeBoolean	= false;
		if( !chkDir.exists() ){
			chkDir.mkdirs();
			makeBoolean = true;
		}
		return makeBoolean;
	}
	
}
