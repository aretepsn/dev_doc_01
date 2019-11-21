package ttlabc.api;
import java.util.ArrayList;

//-- 설명 ----------------------------------------------------------------------------
//00_edit_list.txt내에 작성된 파일을 실행한 폴더로 복사하는 기능

//-- 필요파일 --
//ShortTime.java

//-- 실행방법 --
//java ShortTime VO SPEC_SEQ_NO,MPRSC_SEQ_NO				-> private String specSeqNo;
//java ShortTime ENTITY SPEC_SEQ_NO,MPRSC_SEQ_NO		-> tempVO.setSpecSeqNo(rset.getString("spec_seq_no"));
//java ShortTime GRID_LIST SPEC_SEQ_NO,MPRSC_SEQ_NO	-> specSeqNo=StringUtil.nvl(nonCylSvcVO.getSpecSeqNo());
//java ShortTime STRING SPEC_SEQ_NO,MPRSC_SEQ_NO		-> String specSeqNo;
//java ShortTime TABLE SPEC_SEQ_NO,MPRSC_SEQ_NO			-> <td class="tablebg_i"><span style='width:1000;overflow:hidden' onmouseover=showTip(this)><%=specSeqNo%></span></td>
//java ShortTime HIDDEN SPEC_SEQ_NO,MPRSC_SEQ_NO		-> <input type="hidden" name="specSeqNo_<%=cnt%>" value="<%=specSeqNo%>">
//---------------------------------------------------------------------------- 설명 --

public  class  ShortTime{
    public static void main(String[] args) {
        try{
            String type= args[0].toUpperCase();           //원하는 추출정보
            String colNm = args[1];         //컬럼정보
            String temp = "";               //임시 변수
            ShortTime st = new ShortTime();
            
            
            ArrayList orgColNm = new ArrayList();
            ArrayList cvtColNm = new ArrayList();

            //변환
            st.cvtString(colNm.toLowerCase(),orgColNm,cvtColNm);

            if(type.equals("RESULTMAP")){
                for(int i=0;i<cvtColNm.size();i++){
                    System.out.println( "<result property=\"" + orgColNm.get(i) + "\" 		column=\""	+ orgColNm.get(i) + "\" />");
                }
            	
            }
            /*
            //DATASET에서 사용
            if(type.equals("VO")){
                for(int i=0;i<cvtColNm.size();i++){
                    System.out.println("private String "+cvtColNm.get(i)+";");
                }
                
            //ENTITY에서 사용
            }else if(type.equals("ENTITY")){
                for(int i=0;i<cvtColNm.size();i++){
                    temp = (String)cvtColNm.get(i);
                    System.out.println("tempVO.set"+temp.substring(0, 1).toUpperCase()+temp.substring(1,temp.length())+"(rset.getString(\""+orgColNm.get(i)+"\"));");
                }
            //GRID의 LIST를 뿌려주는 설정
            }else if(type.equals("GRID_LIST")){
                for(int i=0;i<orgColNm.size();i++){
                    temp = (String)cvtColNm.get(i);
                    System.out.println(temp+"=StringUtil.nvl(nonCylSvcVO.get"+temp.substring(0, 1).toUpperCase()+temp.substring(1,temp.length())+"());");
                }

            //String 변수 설정 
            }else if(type.equals("STRING")){
                for(int i=0;i<orgColNm.size();i++){
                    temp = (String)cvtColNm.get(i);
                    System.out.println("String "+ temp + ";");
                }
            }else if(type.equals("TABLE")){
                for(int i=0;i<orgColNm.size();i++){
                    temp = (String)cvtColNm.get(i);
                    temp = "<td class=\"tablebg_i\"><span style='width:1000;overflow:hidden' onmouseover=showTip(this)><%="+temp+"%></span></td>";
                    System.out.println(temp);
                }
            }else if(type.equals("HIDDEN")){
                for(int i=0;i<orgColNm.size();i++){
                    temp = (String)cvtColNm.get(i);
                    temp = "<input type=\"hidden\" name=\""+temp+"_<%=cnt%>\" value=\""+"<%="+temp +"%>\">";
                    System.out.println(temp);
                }
            }
            */
        }catch(Exception e){
            System.out.println("첫번째 args : [VO,ENTITY,GRID_LIST,STRING]");
            System.out.println("두번째 args : 컬럼정보");
            System.out.println("ex:>java ShortTime VO A,B,C");
            System.out.println("Exception : "+e);
        }
    }
    
    //컬럼의 '_'를 제거하고, '_'뒷문자를 대문자로변경하고, String변수와 '_'으로 구분된 값을 추출한다.
    public void cvtString(String colNm, ArrayList orgColNm, ArrayList cvtColNm){
        String temp = "";

        while(colNm.indexOf(",") != -1){
            temp = colNm.substring(0,colNm.indexOf(","));
            orgColNm.add(temp);
            colNm = colNm.substring(colNm.indexOf(",")+1, colNm.length());
            
            //마지막인 경우 추가한다.
            if(colNm.indexOf(",") == -1){
                orgColNm.add(colNm);
            }
        }

        
        for(int i=0;i<orgColNm.size();i++){
            temp = (String)orgColNm.get(i);
            temp = temp.toLowerCase();
            
            while(temp.indexOf("_") != -1){
                temp = temp.substring(0,temp.indexOf("_")) 
                         + temp.substring(temp.indexOf("_")+1,temp.indexOf("_")+2).toUpperCase() 
                         + temp.substring(temp.indexOf("_")+2, temp.length());
            }
            cvtColNm.add(temp);
        }
        
    }
}
