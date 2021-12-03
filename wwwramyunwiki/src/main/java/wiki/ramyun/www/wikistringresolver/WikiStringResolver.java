package wiki.ramyun.www.wikistringresolver;

import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

public class WikiStringResolver {
	
	//이미지 태그 한문장을 분석한다.
	public static String imageParser(String input) throws Exception {
		try {
			int startIndex = input.indexOf("(이미지)");
			int endIndex = input.indexOf("(/이미지)");
			String contents=(String) input.subSequence(startIndex+5, endIndex);
			String output= "<img src=\""+contents+"\" alt=\"image\">";
			
			
			return output;
			
		} catch (Exception e) {
			throw new Exception("(이미지)(/이미지)에 문제가 있습니다. (type 01)");
		}
	}
	
	
	//이미지 태그를 반복해서 파싱한다.
	public static String encodeImage(String input) throws Exception{
		try {
			while (input.contains("(이미지)")) {
	
				int startIndex = input.indexOf("(이미지)");
				int endIndex = input.indexOf("(/이미지)") + 6;
	
				String singleLinkingString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.imageParser(singleLinkingString);
				input = input.replace(singleLinkingString, afterParsingSingleString);
			}
		}catch(Exception e) {
			throw new Exception("(이미지)(/이미지)에 문제가 있습니다. (type 02)");
		}
		
		return input;
	}
	
	
	//목차를 찾아 변경한다.
	public static String indexParser(String input) throws Exception {
		try {
			//목차 번호 자르기 위치
			int restPositionComment = input.indexOf("[");
			int endPositionComment = input.indexOf("]");
			// 내용물은 띄어 쓰기를 그대로 가져온다
			String indexNumber = (String) input.subSequence(restPositionComment + 1, endPositionComment);
			
			//목차는 빈칸이 있으면 안된다.
			indexNumber=indexNumber.trim();
			indexNumber=indexNumber.replace(" ", "");

			String output = "<a href=\"#index"+indexNumber+"\" style=\"vertical-align: unset;\">"+indexNumber+".</a>";
			return output;
		} catch (Exception e) {
			throw new Exception("목차에 문제가 있습니다. (type 01)");
		}
	}
	
	
	//목차를 반복해서 적용한다.
	public static String encodeIndex(String input) throws Exception {
		try {
			while (input.contains("(목차)")) {

				int startIndex = input.indexOf("(목차)");
				int endIndex = input.indexOf("(/목차)") + 5;

				String singleLinkingString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.indexParser(singleLinkingString);
				input = input.replace(singleLinkingString, afterParsingSingleString);
			}

			return input;
		} catch (Exception e) {
			throw new Exception("목차에 문제가 있습니다. (type 02)");
		}
	}
	
	
	// 목차내용을 찾아 변경한다.
	public static String indexContentParser(String input) throws Exception {
		try {
			//목차 번호 자르기 위치
			int startPositionComment = input.indexOf("[");
			int endPositionComment = input.indexOf("]");
			
			//각주 내용 자르기 위치
			//int endPositionConents = input.indexOf("(/목차내용)");
			// 내용물은 띄어 쓰기를 그대로 가져온다
			String indexNumber = (String) input.subSequence(startPositionComment + 1, endPositionComment);

			
			//목차는 빈칸이 있으면 안된다.
			indexNumber=indexNumber.trim();
			indexNumber=indexNumber.replace(" ", "");
			

			String output = "<span id=\"index"+indexNumber+"\">"+indexNumber+".</span>";
			return output;
		} catch (Exception e) {
			throw new Exception("목차내용에 문제가 있습니다. (type 01)");
		}
		
		
		
	}
	
	// 목차내용을 반복해서 적용한다.
	public static String encodeindexContents(String input) throws Exception {
		try {
			while (input.contains("(목차내용)")) {

				int startIndex = input.indexOf("(목차내용)");
				int endIndex = input.indexOf("(/목차내용)") + 7;

				String singleLinkingString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.indexContentParser(singleLinkingString);
				input = input.replace(singleLinkingString, afterParsingSingleString);
			}

			return input;
		} catch (Exception e) {
			throw new Exception("목차내용에 문제가 있습니다. (type 02)");
		}
		
	}
	
	
	// 각주를 찾아 변경한다.
	public static String commentParser(String input) throws Exception {
		try {
			//각주 번호 자르기 위치
			int restPositionComment = input.indexOf("[");
			int endPositionComment = input.indexOf("]");
			// 내용물은 띄어 쓰기를 그대로 가져온다
			String commentNumber = (String) input.subSequence(restPositionComment + 1, endPositionComment);

			
			//각주는 빈칸이 있으면 안된다.
			commentNumber=commentNumber.trim();
			commentNumber=commentNumber.replace(" ", "");
			

			String output = "<a href=\"#comment"+commentNumber+"\"><sup>["+commentNumber+"]</sup></a>";
			return output;
		} catch (Exception e) {
			throw new Exception("각주에 문제가 있습니다. (type 01)");
		}
		
		
		
		
		
	}
	// 각주를 반복해서 적용한다.
	public static String encodeComments(String input) throws Exception {
		try {
			while (input.contains("(각주)")) {

				int startIndex = input.indexOf("(각주)");
				int endIndex = input.indexOf("(/각주)") + 5;

				String singleLinkingString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.commentParser(singleLinkingString);
				input = input.replace(singleLinkingString, afterParsingSingleString);
			}

			return input;
		} catch (Exception e) {
			throw new Exception("각주에 문제가 있습니다. (type 02)");
		}
	}
	

	
	// 각주내용을 찾아 변경한다.
	public static String commentContentParser(String input) throws Exception {
		try {
			//각주 번호 자르기 위치
			int startPositionComment = input.indexOf("[");
			int endPositionComment = input.indexOf("]");
			
			//각주 내용 자르기 위치
			int endPositionConents = input.indexOf("(/각주내용)");
			// 내용물은 띄어 쓰기를 그대로 가져온다
			String commentNumber = (String) input.subSequence(startPositionComment + 1, endPositionComment);
			String contents = (String) input.subSequence(endPositionComment + 1, endPositionConents);

			
			//각주는 빈칸이 있으면 안된다.
			commentNumber=commentNumber.trim();
			commentNumber=commentNumber.replace(" ", "");
			

			String output = "<span id=\"comment"+commentNumber+"\">["+commentNumber+"] "+contents+"</span>";
			return output;
		} catch (Exception e) {
			throw new Exception("각주내용에 문제가 있습니다. (type 01)");
		}
	}
	
	
	// 각주내용을 반복해서 적용한다.
	public static String encodeCommentContents(String input) throws Exception {
		try {
			while (input.contains("(각주내용)")) {

				int startIndex = input.indexOf("(각주내용)");
				int endIndex = input.indexOf("(/각주내용)") + 7;

				String singleLinkingString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.commentContentParser(singleLinkingString);
				input = input.replace(singleLinkingString, afterParsingSingleString);
			}

			return input;
		} catch (Exception e) {
			throw new Exception("각주내용에 문제가 있습니다. (type 02)");
		}
		
	}
	
	
	// 컨텐츠에서 링크를 찾아서 파싱한다. linkParser를 반복한다.
	public static String encodeLinks(String input) throws Exception {
		
		try {
			while (input.contains("(링크)")) {

				int startIndex = input.indexOf("(링크)");
				int endIndex = input.indexOf("(/링크)") + 5;

				String singleLinkingString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.linkParser(singleLinkingString);
				input = input.replace(singleLinkingString, afterParsingSingleString);
			}

			return input;
		} catch (Exception e) {
			throw new Exception("(링크)(/링크)에 문제가 있습니다. (type 01)");
		}
		
		
	}

	
	// 한줄을 바꾸는 메서드 링크를 처리. linkParser에서 가져다 쓴다.
	public static String linkParser(String input) throws Exception {
		try {
			int restPosition = input.indexOf(",");
			int endPosition = input.indexOf("(/");
			// 내용물은 띄어 쓰기를 그대로 가져온다
			String contents = (String) input.subSequence(restPosition + 1, endPosition);

			// 링크같은 경우 빈틈이 있으면안된다.
			String link = (String) input.subSequence(4, restPosition);
			link = link.trim();
			link = link.replace(" ", "");

			String output = "<a id=\"userlinks\" href=" + "\"" + link + "\"" + ">" + contents + "</a>";

			return output;
		} catch (Exception e) {
			throw new Exception("(링크)(/링크)에 문제가 있습니다. (type 02)");
		}
		
	}

	
	// 이거는 숫자따로 받아 구현할것 encodeTextColor에서 호출해서 쓴다.
	public static String colorParser(String input) throws Exception {
		try {
			int startPosition = input.indexOf("[#") + 2;
			String sixNumber = (String) input.subSequence(startPosition, startPosition + 6);
			// 컬러로 등록되는 #6글자는 없애는과정이 필요하다
			input = input.replaceAll(".#\\w\\w\\w\\w\\w\\w.", "");

			input = input.replace("(글자색)", "<span style=\"color : #" + sixNumber + "\";>");
			input = input.replace("(/글자색)", "</span>");

			return input;
		} catch (Exception e) {
			throw new Exception("(글자색)(/글자색)에 문제가 있습니다. (type 01)");
		}
	}

	
	// 컨텐츠에서 링크를 찾아서 colorParser를 반복한다.
	public static String encodeTextColor(String input) throws Exception {
		try {
			while (input.contains("(글자색)")) {

				int startIndex = input.indexOf("(글자색)");
				int endIndex = input.indexOf("(/글자색)") + 6;
				String singleColorString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.colorParser(singleColorString);
				input = input.replace(singleColorString, afterParsingSingleString);
			}
			return input;
		} catch (Exception e) {
			throw new Exception("(글자색)(/글자색)에 문제가 있습니다. (type 02)");
		}
	}

	
	// 백그라운드 컬러 파싱encodeBackgroundColor에서 반복됌
	public static String backgroundColorParser(String input) throws Exception {
		try {
			int startPosition = input.indexOf("[#") + 2;
			String sixNumber = (String) input.subSequence(startPosition, startPosition + 6);
			// 컬러로 등록되는 #6글자는 없애는과정이 필요하다
			input = input.replaceAll(".#\\w\\w\\w\\w\\w\\w.", "");

			input = input.replace("(배경색)", "<span style=\"background-color : #" + sixNumber + "\";>");
			input = input.replace("(/배경색)", "</span>");

			return input;
		} catch (Exception e) {
			throw new Exception("(배경색)(/배경색)에 문제가 있습니다. (type 01)");
		}
	}

	
	// 컨텐츠에서 링크를 찾아서 backgroundColorParser를 반복한다.
	public static String encodeBackgroundColor(String input) throws Exception {
		try {
			while (input.contains("(배경색)")) {

				int startIndex = input.indexOf("(배경색)");
				int endIndex = input.indexOf("(/배경색)") + 6;
				String singleColorString = (String) input.subSequence(startIndex, endIndex);
				String afterParsingSingleString = WikiStringResolver.backgroundColorParser(singleColorString);
				input = input.replace(singleColorString, afterParsingSingleString);
			}
			return input;
		} catch (Exception e) {
			throw new Exception("(배경색)(/배경색)에 문제가 있습니다. (type 02)");
		}
		
		
	}

	
	// 굵은글자 처리 매서드
	public static String encodeStrong(String input) {
		input = input.replace("(굵은글자)", "<strong>");
		input = input.replace("(/굵은글자)", "</strong>");

		return input;
	}

	
	public static String encodeBig(String input) {
		input = input.replace("(큰글자)", "<b>");
		input = input.replace("(/큰글자)", "</b>");

		return input;
	}

	
	public static String encodeSmall(String input) {

		input = input.replace("(작은글자)", "<small>");
		input = input.replace("(/작은글자)", "</small>");

		return input;
	}

	
	public static String encodeH1(String input) {
		input = input.replace("(큰글자1)", "<h1>");
		input = input.replace("(/큰글자1)", "</h1>");

		return input;
	}
	
	
	public static String encodeH2(String input) {
		input = input.replace("(큰글자2)", "<h2>");
		input = input.replace("(/큰글자2)", "</h2>");

		return input;
	}
	
	
	public static String encodeH3(String input) {
		input = input.replace("(큰글자3)", "<h3>");
		input = input.replace("(/큰글자3)", "</h3>");

		return input;
	}
	
	
	public static String encodeH4(String input) {
		input = input.replace("(큰글자4)", "<h4>");
		input = input.replace("(/큰글자4)", "</h4>");

		return input;
	}
	
	
	public static String encodeUnderline(String input) {
		input = input.replace("(밑줄)", "<u>");
		input = input.replace("(/밑줄)", "</u>");

		return input;
	}

	
	public static String encodeIncline(String input) {
		input = input.replace("(기울임)", "<i>");
		input = input.replace("(/기울임)", "</i>");
		return input;
	}

	
	public static String encodeStrike(String input) {
		input = input.replace("(취소선)", "<strike>");
		input = input.replace("(/취소선)", "</strike>");

		return input;
	}

	
	public static String encodeUpperString(String input) {
		input = input.replace("(윗글자)", "<sup>");
		input = input.replace("(/윗글자)", "</sup>");

		return input;
	}

	
	public static String encodeSubString(String input) {
		input = input.replace("(아래글자)", "<sub>");
		input = input.replace("(/아래글자)", "</sub>");

		return input;
	}

	
	// 줄넘김은 맨 마지막에 할것. 그래야 안깨진다.
	public static String encodeNextline(String input) {
		input = input.replace("(줄넘김)", "<br>");

		return input;
	}

	
	public static String encodeChangeline(String input) {
		input = input.replace("(줄바꿈)", "<br>");

		return input;
	}
	
	
	//구분선을 넣는다
	public static String encodeLine(String input) {
		input =input.replace("(구분선)", "<div style=\"outline: 1px dotted rgba(128, 128, 128, 0.8);margin-top:5px; width: 98%; margin-left: 1%;\"></div>");
		return input;
	}
	
	
	//인용구 처리
	public static String encodeQuote(String input) {
		//추후 작업시 여기서부터 이어서 한다.
		return input;
	}

	
	//외곽선을 넣는다.
	public static String encodeOutLine(String input) {
		input = input.replace("(외곽선)",  "<div style=\"outline: 1px dotted rgba(128, 128, 128,1); margin:5px; padding:5px;width:fit-content;\">");
		input = input.replace("(/외곽선)", "</div>");

		return input;
	}
	
	
	//사용자가 직접 div를 넣으려 하는경우 
	public static void checkHtmlEditingError(String input) throws Exception {
		//금지할 태그는 여기에 남긴다. 모두 메세지 처리된다.
		input=input.replace(" ", "");
		if(input.contains("<d") || input.contains("<di")|| input.contains("<div")||input.contains("div>")){
			throw new Exception("div 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<div>") || input.contains("</div>")|| input.contains("<div")||input.contains("div>")){
			throw new Exception("div 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<span>") || input.contains("</span>")||input.contains("<span") || input.contains("span>")){
			throw new Exception("span 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<button>") || input.contains("</button>")||input.contains("<button") || input.contains("button>")){
			throw new Exception("button 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<body>") || input.contains("</body>")||input.contains("<body") || input.contains("body>")){
			throw new Exception("body 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<header>") || input.contains("</header>")||input.contains("<header") || input.contains("header>")){
			throw new Exception("header 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<input>") || input.contains("</input>")||input.contains("<input") || input.contains("input>")){
			throw new Exception("input 태그를 직접 편집하면 안됩니다.");
		}
		if(input.contains("<textarea>") || input.contains("</textarea>")||input.contains("<textarea") || input.contains("textarea>")){
			throw new Exception("textarea 태그를 직접 편집하면 안됩니다.");
		}
		
		
		
	}
	
	
	public static String encodeContents(String input) throws Exception{
		
		//널체크를 해서 널이면 그냥 보낸다.DB를 바꾸는것보다 이게 안전하다.
		if(input==null) {return input;}
		WikiStringResolver.checkHtmlEditingError(input);
		
		input = WikiStringResolver.encodeH1(input);
		input = WikiStringResolver.encodeH2(input);
		input = WikiStringResolver.encodeH3(input);
		input = WikiStringResolver.encodeH4(input);
		input = WikiStringResolver.encodeSmall(input);
		input = WikiStringResolver.encodeBig(input);
		input = WikiStringResolver.encodeIncline(input);
		input = WikiStringResolver.encodeLinks(input);
		input = WikiStringResolver.encodeNextline(input);
		input = WikiStringResolver.encodeChangeline(input);
		input = WikiStringResolver.encodeTextColor(input);
		input = WikiStringResolver.encodeBackgroundColor(input);
		input = WikiStringResolver.encodeStrike(input);
		input = WikiStringResolver.encodeStrong(input);
		input = WikiStringResolver.encodeSubString(input);
		input = WikiStringResolver.encodeUnderline(input);
		input = WikiStringResolver.encodeUpperString(input);
		input = WikiStringResolver.encodeComments(input);
		input = WikiStringResolver.encodeCommentContents(input);
		input = WikiStringResolver.encodeIndex(input);
		input = WikiStringResolver.encodeindexContents(input);
		input = WikiStringResolver.encodeLine(input);
		input = WikiStringResolver.encodeImage(input);
		input = WikiStringResolver.encodeOutLine(input);

		return input;
	}

	// 스트링 버퍼로 교체할것. 스트링은 느려
	// 비디오 추가
}