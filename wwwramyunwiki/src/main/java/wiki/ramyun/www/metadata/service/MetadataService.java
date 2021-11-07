package wiki.ramyun.www.metadata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.metadata.mapper.MetadataMapper;

@Service
public class MetadataService {
	@Autowired
	@Qualifier("metadataMapper")
	MetadataMapper mapper;
	
	

	// 메타데이터에 멤버이름과 라면이름을 확인하여 좋아요를 등록한다. 중복확인 필수
	public int addLikeRamyun(String memberId, String ramyunName) {
		int nowLikes=0;
		
		try {
	
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrevLikes(memberId, ramyunName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addLike(ramyunName,memberId);//순서를 확인하자
			}

			nowLikes = mapper.getLikesByName(ramyunName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowLikes;
	}

	
	// 메타데이터에 멤버이름과 라면 이름을 확인하여 신고를 등록한다. 중복확인 필수. 10개 넘어가면 글삭제
	public int addReportRamyun(String memberId, String ramyunName) {
		int nowReport=0;
		try {
			
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrevReport(memberId, ramyunName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addReport(ramyunName,memberId);//순서를 확인하자
			}

			nowReport = mapper.getReportByName(ramyunName);
			
			int likesCount=mapper.likesCount(ramyunName);
			int reportsCount=mapper.reportingCount(ramyunName);
//			신고가 좋아요보다 10개 많다면 지워진다.
			if((reportsCount-likesCount)>=10) {
				mapper.removeRamyun(ramyunName);
				mapper.removeAllMetadata(ramyunName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowReport;
	}

	//영양성분 추천 구현
	public int addLikeIngredient(String memberId, String ingredientName) {
		int nowLikes=0;
		
		try {
	
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrevLikes(memberId, ingredientName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addLike(ingredientName,memberId);//순서를 확인하자
			}

			nowLikes = mapper.getLikesByName(ingredientName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowLikes;
	}

	//영양성분 신고구현
	public int addReportIngredient(String memberId, String ingredientName) {
		int nowReport=0;
		try {
			
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrevReport(memberId, ingredientName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addReport(ingredientName,memberId);//순서를 확인하자
			}

			nowReport = mapper.getReportByName(ingredientName);
			
			int likesCount=mapper.likesCount(ingredientName);
			int reportsCount=mapper.reportingCount(ingredientName);

			//신고가 좋아요보다 10개 많다면 지워진다.여기만 서비스를 다르게 쓰면된다.
			if((reportsCount-likesCount)>=10) {
				mapper.removeIngredient(ingredientName);
				mapper.removeAllMetadata(ingredientName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowReport;
	}
	//영양성분 추천 구현
	public int addLikeManufactory(String memberId, String ingredientName) {
		int nowLikes=0;
		
		try {
	
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrevLikes(memberId, ingredientName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addLike(ingredientName,memberId);//순서를 확인하자
			}

			nowLikes = mapper.getLikesByName(ingredientName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowLikes;
	}

	//영양성분 신고구현
	public int addReportManufactory(String memberId, String ingredientName) {
		int nowReport=0;
		try {
			
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrevReport(memberId, ingredientName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addReport(ingredientName,memberId);//순서를 확인하자
			}

			nowReport = mapper.getReportByName(ingredientName);
			
			int likesCount=mapper.likesCount(ingredientName);
			int reportsCount=mapper.reportingCount(ingredientName);

			//신고가 좋아요보다 10개 많다면 지워진다.여기만 서비스를 다르게 쓰면된다.
			if((reportsCount-likesCount)>=10) {
				mapper.removeManufactory(ingredientName);
				mapper.removeAllMetadata(ingredientName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowReport;
	}

}
