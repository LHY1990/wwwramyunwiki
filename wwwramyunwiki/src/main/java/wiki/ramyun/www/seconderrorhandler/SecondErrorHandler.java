package wiki.ramyun.www.seconderrorhandler;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;


//jsp에서 LocalDateTime이 0초로 끝나면 화면이 깨져서 만듦. db에 입력되는건 0초가 되지만 읽는건 0초가 되면안된다.
@Component("secondErrorHandler")
public class SecondErrorHandler {
	
	public LocalDateTime checkSecond(LocalDateTime input) {
		if(input.getSecond()==0) {
			input=input.plusSeconds(1);
			return input;
		}else {
			return input;
		}
		 
	}
}
