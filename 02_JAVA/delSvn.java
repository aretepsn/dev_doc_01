import java.io.*;
import java.util.*;

//-- ���� ----------------------------------------------------------------------------
//����� ������ ���丮���� SVN���� ���丮 ����� "__delDir.txt"�� �ۼ��Ͽ� ���������� ����.
//"__delDir.txt"�� "__delDir.cmd"�� ��ȯ�Ͽ� ����â���� �����ϸ� SVN���� ���丮 ����

//-- �ʿ����� --
//delSvn.java
//__delDir.txt

//-- ������ --
//java delSvn D:\03_work
//---------------------------------------------------------------------------- ���� --

public class  delSvn{
	     
    private static String targetDirectory;		//������ ���� ���丮
    private static String delDirName = ".SVN";
    private static String saveDirectory = System.getProperty("user.dir") + "/"; //����� ��� ������ġ
    private static String createSearchTemp = "__delDir.txt";                        //���丮,���� �ӽ� ��������
    
	public static void main(String[] args){
		
        delSvn delSvn = new delSvn();
        
        //����� ���� ����[�ӽ�����]
        delSvn.createFile(saveDirectory + createSearchTemp);
        
        //�Ķ���� �Է¿���
        delSvn.getSearchDir(args);
        
        //���丮����
        delSvn.searchDir(targetDirectory);
	}
	

    /*
        ������ �������丮 �Է� ����
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
            System.out.println("�����(�������� '\\,/'�� �����մϴ�.) .ex>c:\\java z:\test");
            System.out.println("error : \r\n"+e);
            System.exit(0);
        }
    }

    /*
        - ���丮,���� ����/���
        - ��������� ȣ���ϴ� �Լ�
        - searchDir            �˻��� ���丮 Ǯ���
    */
    public void searchDir(String searchDir){
        File dir = new File(searchDir);
        String[] child = dir.list();
        File underDir;
        String content;
        Boolean dirDelFlag;

        if(child != null){

            //�˻� ���丮���� �ۼ�(���丮��)
            for(int i=0;i<child.length;i++){
                underDir = new File(searchDir + child[i]);
                content = child[i];

                //���丮�� ���
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
                      //searchDir ��������� ȣ��
                      searchDir(content);
                    }
                }
            }
        }
    }

    /*
        ���뾲��
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
        ����� ���� ����
    */
    public static void createFile(String fileName){
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

}
