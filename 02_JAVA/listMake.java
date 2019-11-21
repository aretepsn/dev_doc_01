import java.io.*;
import java.util.*;

/*
    - java 1.4.2_09에서 개발
    - 참조 : http://www.exampledepot.com
*/
//-- 설명 ----------------------------------------------------------------------------
//실행시 지정된 디렉토리내에 디렉토리와 파일 목록을 "__search.txt"에 작성하여 실행폴더에 생성.
//"__search.txt" 생성시 구분자가 TAB으로 되어 있어 EXCEL에 붙여 넣으면 보기 편하게 편집할수 있다.

//-- 필요파일 --
//__search.txt
//listMake.java

//-- 실행방법 --
//java listMake D:\05_my\01_PROC\myTools
//---------------------------------------------------------------------------- 설명 --
public class  listMake{
    private static int dirCnt = 1;                                                                  //검사된 디렉토리 개수
    private static int fileCnt = 0;                                                                 //검사된 파일 개수
    private static String targetDirectory;                                                      //조사할 디렉토리
    private static String saveDirectory = System.getProperty("user.dir") + "/"; //조사된 목록 저장위치
    private static String createSearchTemp = "__search.txt";                        //디렉토리,파일 임시 저장파일

	public static void main(String[] args){
        listMake listMake = new listMake();

        //조사할 디렉토리 입력여부 체크/ 변환
        listMake.getSearchDir(args);

        //기록할 파일 생성[임시파일]
        listMake.createFile(saveDirectory + createSearchTemp);

        //디렉토리/파일 목록작성
        System.out.println("처리 : 디렉토리/파일 목록 작성중");
        writer(saveDirectory+createSearchTemp,"[폴더]\t" + targetDirectory);    //초기검사디렉토리 작성
        searchDir(targetDirectory);
        System.out.println("완료 : 검사 디렉토리 : '" + targetDirectory + "'");
        System.out.println("완료 : 디렉토리 개수(검사디렉토리포함):"+dirCnt);
        System.out.println("완료 : 파일 개수:"+fileCnt);
        System.out.println("안내 : excel로 열어 구분자를 '탭'으로 하여 '필더정렬'하면 보기 편합니다.");
        writer(saveDirectory+createSearchTemp,"검사일:" + listMake.nowDate());    //디렉토리/파일 검사일 작성
        writer(saveDirectory+createSearchTemp,"디렉토리 개수(검사디렉토리포함):"+dirCnt);
        writer(saveDirectory+createSearchTemp,"파일 개수:"+fileCnt);
        writer(saveDirectory+createSearchTemp,"excel로 열어 구분자를 '탭'으로 하여 '필더정렬'하면 보기 편합니다.");
	}

    /*
        조사할 디렉토리 입력여부 체크/ 변환
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
            System.out.println("사용방법(마지막에 '\\,/'를 제외합니다.) .ex>c:\\java listMake z:");
            System.out.println("error : \r\n"+e);
            System.exit(0);
        }
    }

    /*
        기록할 파일 생성
    */
    public void createFile(String fileName){
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

    /*
        - 디렉토리,파일 조사/기록
        - 재귀적으로 호출하는 함수
        - searchDir            검사할 디렉토리 풀경로
    */
    public static void searchDir(String searchDir){
        File dir = new File(searchDir);
        String[] child = dir.list();
        File underDir;
        String content;
        String blank = "        ";

        if(child != null){

            //검사 디렉토리내의 작성(파일만)
            for(int i=0;i<child.length;i++){
                underDir = new File(searchDir + child[i]);
                content = child[i];

                //파일인 경우
                if(!underDir.isDirectory()){
                    fileCnt++;
                    content = "[파일]\t" + searchDir + "\t" + content + "\t" + (underDir.length()/1024) + " Kbyte";
                    writer(saveDirectory+createSearchTemp,content);
                }
            }

            //검사 디렉토리내의 작성(디렉토리만)
            for(int i=0;i<child.length;i++){
                underDir = new File(searchDir + child[i]);
                content = child[i];

                //디렉토리인 경우
                if(underDir.isDirectory()){
                    dirCnt++;
                    content = searchDir +  content + "/";
                    writer(saveDirectory+createSearchTemp,"[폴더]\t" + content);

                    //searchDir 재귀적으로 호출
                    searchDir(content);
                }
            }
        }
    }

    /*
        내용쓰기
    */
    public static void writer(String fileName, String content){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));
            out.write(content + "\r\n");
            out.close();
        }catch(Exception e){
            System.out.println("error : \r\n" + e);
        }
    }

    /*
        현재날짜 가져오기
    */
    public String nowDate(){
        Calendar cal = new GregorianCalendar();
        String yyyymmdd = "";
        yyyymmdd = yyyymmdd + (cal.get(Calendar.MONTH)+1);
        if(yyyymmdd.length() == 1) yyyymmdd = "0" + yyyymmdd;
        yyyymmdd = cal.get(Calendar.YEAR) + "-" + yyyymmdd;
        yyyymmdd = yyyymmdd + "-" + cal.get(Calendar.DAY_OF_MONTH);
		return yyyymmdd;
    }
}
