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
	
	
}
