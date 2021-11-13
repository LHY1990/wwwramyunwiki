package wiki.ramyun.www.wikistringresolver;

import org.springframework.stereotype.Component;

public class WikiStringResolver {
	
	
	
	public static String encodeContents(String input) {
		
		input=WikiStringResolver.encodeSmall(input);
		input=WikiStringResolver.encodeBig(input);
		input=WikiStringResolver.encodeIncline(input);
		input=WikiStringResolver.encodeLinks(input);
		input=WikiStringResolver.encodeNextline(input);
		input=WikiStringResolver.encodeTextColor(input);
		input=WikiStringResolver.encodeBackgroundColor(input);
		input=WikiStringResolver.encodeStrike(input);
		input=WikiStringResolver.encodeStrong(input);
		input=WikiStringResolver.encodeSubString(input);
		input=WikiStringResolver.encodeUnderline(input);
		input=WikiStringResolver.encodeUpperString(input);
		
		return input;
	}
	
	
   
   //컨텐츠에서 링크를 찾아서 파싱한다. linkParser를 반복한다.
   public static String encodeLinks(String input) {
      while(input.contains("(링크)")){
         
         int startIndex=input.indexOf("(링크)");
         int   endIndex=input.indexOf("(/링크)")+5;
          
         String singleLinkingString=(String)input.subSequence(startIndex, endIndex);
         String afterParsingSingleString=WikiStringResolver.linkParser(singleLinkingString);
         input=input.replace(singleLinkingString, afterParsingSingleString);
         System.out.println("안되나");
      }

      
      return input;
   }
   
   
   //한줄을 바꾸는 메서드 링크를 처리. linkParser에서 가져다 쓴다.
   public static String linkParser(String input) {
      
      int restPosition = input.indexOf(",");
      int endPosition = input.indexOf("(/");
      //내용물은 띄어 쓰기를 그대로 가져온다
      String contents=(String)input.subSequence(restPosition+1, endPosition);
      
      //링크같은 경우 빈틈이 있으면안된다.
      String link =(String)input.subSequence(4, restPosition);
      link=link.trim();
      link=link.replace(" ", "");
      
      String output ="<a id=\"userlinks\" href="+"\""+link+"\""+">"+contents+"</a>";
      
      return output;
   }
   
   //이거는 숫자따로 받아 구현할것 encodeTextColor에서 호출해서 쓴다.
   public static String colorParser(String input) {
      int startPosition = input.indexOf("[#")+2;
      String sixNumber= (String)input.subSequence(startPosition, startPosition+6);
      //컬러로 등록되는 #6글자는 없애는과정이 필요하다
      input=input.replaceAll(".#\\w\\w\\w\\w\\w\\w.", "");
      
      input=input.replace("(글자색)", "<span style=\"color : #"+sixNumber+"\";>");
      input=input.replace("(/글자색)", "</span>");
      
      return input;
   }
   //컨텐츠에서 링크를 찾아서 colorParser를 반복한다.
   public static String encodeTextColor(String input) {
      while(input.contains("(글자색)")){
         
         int startIndex=input.indexOf("(글자색)");
         int   endIndex=input.indexOf("(/글자색)")+6;
         String singleColorString=(String)input.subSequence(startIndex, endIndex);
         String afterParsingSingleString=WikiStringResolver.colorParser(singleColorString);
         input=input.replace(singleColorString, afterParsingSingleString);
      }
      return input;
   }
   
   
   //백그라운드 컬러 파싱encodeBackgroundColor에서 반복됌
   public static String backgroundColorParser(String input) {
      int startPosition = input.indexOf("[#")+2;
      String sixNumber= (String)input.subSequence(startPosition, startPosition+6);
      //컬러로 등록되는 #6글자는 없애는과정이 필요하다
      input=input.replaceAll(".#\\w\\w\\w\\w\\w\\w.", "");
      
      input=input.replace("(배경색)", "<span style=\"background-color : #"+sixNumber+"\";>");
      input=input.replace("(/배경색)", "</span>");
      
      return input;
   }
   //컨텐츠에서 링크를 찾아서 backgroundColorParser를 반복한다.
   public static String encodeBackgroundColor(String input) {
      while(input.contains("(배경색)")){
         
         int startIndex=input.indexOf("(배경색)");
         int   endIndex=input.indexOf("(/배경색)")+6;
         String singleColorString=(String)input.subSequence(startIndex, endIndex);
         String afterParsingSingleString=WikiStringResolver.backgroundColorParser(singleColorString);
         input=input.replace(singleColorString, afterParsingSingleString);
      }
      return input;
   }

   
   
   
   
   
   
   //굵은글자 처리 매서드
   public static String encodeStrong(String input) {
      input=input.replace("(굵은글자)", "<strong>");
      input=input.replace("(/굵은글자)", "</strong>");
      
      return input;
   }
   public static String encodeBig(String input) {
      input=input.replace("(큰글자)", "<b>");
      input=input.replace("(/큰글자)", "</b>");
      
      return input;
   }
   
   public static String encodeSmall(String input) {
      
      input=input.replace("(작은글자)", "<small");
      input=input.replace("(/작은글자)", "</small>");
      
      return input;
   }
   
   public static String encodeUnderline(String input) {
      input=input.replace("(밑줄)", "<u>");
      input=input.replace("(/밑줄)", "</u>");
      
      return input;
   }
   
   public static String encodeIncline(String input) {
      input=input.replace("(기울임)", "<i>");
      input=input.replace("(/기울임)", "</i>");
      return input;
   }
   
   public static String encodeStrike(String input) {
      input=input.replace("(취소선)", "<strike>");
      input=input.replace("(/취소선)", "</strike>");
      
      return input;
   }
   
   public static String encodeUpperString(String input) {
      input=input.replace("(윗글자)", "<sup>");
      input=input.replace("(/윗글자)", "</sup>");
      
      return input;
   }
   
   public static String encodeSubString(String input) {
      input=input.replace("(아래글자)", "<sub>");
      input=input.replace("(/아래글자)", "</sub>");
      
      return input;
   }
   
   
   
   
   //줄넘김은 맨 마지막에 할것. 그래야 안깨진다.
   public static String encodeNextline(String input) {
      input=input.replace("(줄넘김)", "<br>");
      
      return input;
   }
   
   
   //이걸한번에 처리하는 클래스가 있어야할듯
   
   //스트링 버퍼로 교체할것. 스트링은 느려
   //비디오 추가
}