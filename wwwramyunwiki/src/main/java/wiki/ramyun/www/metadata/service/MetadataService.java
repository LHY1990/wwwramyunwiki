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
	public int addLike(String memberId, String ramyunName) {
		int nowLikes=0;
		System.out.println(memberId);
		System.out.println(ramyunName);
		
		try {
	
			// 이전에 같은 라면이름 같은 아이디 등록한적이 없다면, 라이크를 추가할것
			if ((mapper.hasPrev(memberId, ramyunName) == 0)) {
				// 라면이 있다면 추가할것
				mapper.addLike(ramyunName,memberId);//순서를 확인하자
			}

			nowLikes = mapper.getLikes(ramyunName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowLikes;
	}

}
