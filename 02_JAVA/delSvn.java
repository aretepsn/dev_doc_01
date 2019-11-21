import java.io.*;
import java.util.*;

//-- 설명 ----------------------------------------------------------------------------
//실행시 지정된 디렉토리내에 SVN관련 디렉토리 목록을 "__delDir.txt"에 작성하여 실행폴더에 생성.
//"__delDir.txt"를 "__delDir.cmd"로 변환하여 도스창에서 실행하면 SVN관련 디렉토리 제거

//-- 필요파일 --
//delSvn.java
//__delDir.txt

//-- 실행방법 --
//java delSvn D:\03_work
//---------------------------------------------------------------------------- 설명 --

public class  delSvn{
	     
    private static String targetDirectory;		//삭제할 상위 디렉토리
    private static String delDirName = ".SVN";
    private static String saveDirectory = System.getProperty("user.dir") + "/"; //조사된 목록 저장위치
    private static String createSearchTemp = "__delDir.txt";                        //디렉토리,파일 임시 저장파일
    
	public static void main(String[] args){
		
        delSvn delSvn = new delSvn();
        
        //기록할 파일 생성[임시파일]
        delSvn.createFile(saveDirectory + createSearchTemp);
        
        //파라미터 입력여부
        delSvn.getSearchDir(args);
        
        //디렉토리제거
        delSvn.searchDir(targetDirectory);
	}
	

    /*
        삭제할 상위디렉토리 입력 여부
    */
    public void getSearchDir(String[] args){
        try{
            targetDirectory = args[0];
            for(int i=0;i<targetDirectory.length();i++){
                targetDirectory = targetDirectory.replace('\\','/');
            }
            targetDirectory = targetDirectory + "/";
            System.out.println("완료 : '" + args[0] + "' -> '" + targetDirectory + "'으로 변경");
        }catch(Exception e){
            System.out.println("사용방법(마지막에 '\\,/'를 제외합니다.) .ex>c:\\java z:\test");
            System.out.println("error : \r\n"+e);
            System.exit(0);
        }
    }

    /*
        - 디렉토리,파일 조사/기록
        - 재귀적으로 호출하는 함수
        - searchDir            검사할 디렉토리 풀경로
    */
    public void searchDir(String searchDir){
        File dir = new File(searchDir);
        String[] child = dir.list();
        File underDir;
        String content;
        Boolean dirDelFlag;

        if(child != null){

            //검사 디렉토리내의 작성(디렉토리만)
            for(int i=0;i<child.length;i++){
                underDir = new File(searchDir + child[i]);
                content = child[i];

                //디렉토리인 경우
                if(underDir.isDirectory()){
                    content = searchDir +  content + "/";
                    if(content.toUpperCase().indexOf(delDirName)>0){
                        for(int j=0;j<content.length();j++){
                            content = content.replace('/','\\');
                        }
                        content = "rmdir/s/q " + content;
                    	writer(saveDirectory+createSearchTemp,content);
                    	//System.out.println(content);
                    }else{
                      //searchDir 재귀적으로 호출
                      searchDir(content);
                    }
                }
            }
        }
    }

    /*
        내용쓰기
    */
    public void writer(String fileName, String content){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));
            out.write(content + "\r\n");
            out.close();
        }catch(Exception e){
            System.out.println("error : \r\n" + e);
        }
    }

    /*
        기록할 파일 생성
    */
    public static void createFile(String fileName){
        try{
            File file;
            //디렉토리 조사목록을 저장할 파일 생성
            file = new File(fileName);
            if(file.createNewFile()){
                System.out.println("완료 : " + fileName + "파일 생성");
            }else{
                System.out.println("파일생성시 에러 발생 " + fileName + "이 존재합니다.");
                System.exit(0);
            }

        }catch(Exception e){
            System.out.println("error : \r\n"+e);
            System.exit(0);
        }
    }

}
