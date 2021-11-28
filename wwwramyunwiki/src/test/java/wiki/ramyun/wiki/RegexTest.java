package wiki.ramyun.wiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

public class RegexTest {
	@Test
	public void regexTemp() {
		Pattern pattern = Pattern.compile("/<?[a-z]/g");
		String input ="<div   한글";
		
		Matcher result =pattern.matcher(input);
		System.out.println(result.matches());
	}
}
