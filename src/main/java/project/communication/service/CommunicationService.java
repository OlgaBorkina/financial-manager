package project.communication.service;

import project.communication.dto.BriefcaseDto;

public interface CommunicationService {
	
	boolean createBriefcase (BriefcaseDto briefcaseDto );
	
	BriefcaseDto findBriefcaseByNameCase(String nameCase );
	

}
