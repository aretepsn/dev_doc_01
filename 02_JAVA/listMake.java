import java.io.*;
import java.util.*;

/*
    - java 1.4.2_09���� ����
    - ���� : http://www.exampledepot.com
*/
//-- ���� ----------------------------------------------------------------------------
//����� ������ ���丮���� ���丮�� ���� ����� "__search.txt"�� �ۼ��Ͽ� ���������� ����.
//"__search.txt" ������ �����ڰ� TAB���� �Ǿ� �־� EXCEL�� �ٿ� ������ ���� ���ϰ� �����Ҽ� �ִ�.

//-- �ʿ����� --
//__search.txt
//listMake.java

//-- ������ --
//java listMake D:\05_my\01_PROC\myTools
//---------------------------------------------------------------------------- ���� --
public class  listMake{
    private static int dirCnt = 1;                                                                  //�˻�� ���丮 ����
    private static int fileCnt = 0;                                                                 //�˻�� ���� ����
    private static String targetDirectory;                                                      //������ ���丮
    private static String saveDirectory = System.getProperty("user.dir") + "/"; //����� ��� ������ġ
    private static String createSearchTemp = "__search.txt";                        //���丮,���� �ӽ� ��������

	public static void main(String[] args){
        listMake listMake = new listMake();

        //������ ���丮 �Է¿��� üũ/ ��ȯ
        listMake.getSearchDir(args);

        //����� ���� ����[�ӽ�����]
        listMake.createFile(saveDirectory + createSearchTemp);

        //���丮/���� ����ۼ�
        System.out.println("ó�� : ���丮/���� ��� �ۼ���");
        writer(saveDirectory+createSearchTemp,"[����]\t" + targetDirectory);    //�ʱ�˻���丮 �ۼ�
        searchDir(targetDirectory);
        System.out.println("�Ϸ� : �˻� ���丮 : '" + targetDirectory + "'");
        System.out.println("�Ϸ� : ���丮 ����(�˻���丮����):"+dirCnt);
        System.out.println("�Ϸ� : ���� ����:"+fileCnt);
        System.out.println("�ȳ� : excel�� ���� �����ڸ� '��'���� �Ͽ� '�ʴ�����'�ϸ� ���� ���մϴ�.");
        writer(saveDirectory+createSearchTemp,"�˻���:" + listMake.nowDate());    //���丮/���� �˻��� �ۼ�
        writer(saveDirectory+createSearchTemp,"���丮 ����(�˻���丮����):"+dirCnt);
        writer(saveDirectory+createSearchTemp,"���� ����:"+fileCnt);
        writer(saveDirectory+createSearchTemp,"excel�� ���� �����ڸ� '��'���� �Ͽ� '�ʴ�����'�ϸ� ���� ���մϴ�.");
	}

    /*
        ������ ���丮 �Է¿��� üũ/ ��ȯ
    */
    public void getSearchDir(String[] args){
        try{
            targetDirectory = args[0];
            for(int i=0;i<targetDirectory.length();i++){
                targetDirectory = targetDirectory.replace('\\','/');
            }
            targetDirectory = targetDirectory + "/";
            System.out.println("�Ϸ� : '" + args[0] + "' -> '" + targetDirectory + "'���� ����");
        }catch(Exception e){
            System.out.println("�����(�������� '\\,/'�� �����մϴ�.) .ex>c:\\java listMake z:");
            System.out.println("error : \r\n"+e);
            System.exit(0);
        }
    }

    /*
        ����� ���� ����
    */
    public void createFile(String fileName){
        try{
            File file;
            //���丮 �������� ������ ���� ����
            file = new File(fileName);
            if(file.createNewFile()){
                System.out.println("�Ϸ� : " + fileName + "���� ����");
            }else{
                System.out.println("���ϻ����� ���� �߻� " + fileName + "�� �����մϴ�.");
                System.exit(0);
            }

        }catch(Exception e){
            System.out.println("error : \r\n"+e);
            System.exit(0);
        }
    }

    /*
        - ���丮,���� ����/���
        - ��������� ȣ���ϴ� �Լ�
        - searchDir            �˻��� ���丮 Ǯ���
    */
    public static void searchDir(String searchDir){
        File dir = new File(searchDir);
        String[] child = dir.list();
        File underDir;
        String content;
        String blank = "        ";

        if(child != null){

            //�˻� ���丮���� �ۼ�(���ϸ�)
            for(int i=0;i<child.length;i++){
                underDir = new File(searchDir + child[i]);
                content = child[i];

                //������ ���
                if(!underDir.isDirectory()){
                    fileCnt++;
                    content = "[����]\t" + searchDir + "\t" + content + "\t" + (underDir.length()/1024) + " Kbyte";
                    writer(saveDirectory+createSearchTemp,content);
                }
            }

            //�˻� ���丮���� �ۼ�(���丮��)
            for(int i=0;i<child.length;i++){
                underDir = new File(searchDir + child[i]);
                content = child[i];

                //���丮�� ���
                if(underDir.isDirectory()){
                    dirCnt++;
                    content = searchDir +  content + "/";
                    writer(saveDirectory+createSearchTemp,"[����]\t" + content);

                    //searchDir ��������� ȣ��
                    searchDir(content);
                }
            }
        }
    }

    /*
        ���뾲��
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
        ���糯¥ ��������
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
