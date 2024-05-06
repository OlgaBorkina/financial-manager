package project.communication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.communication.dao.BriefcaseRepository;
import project.communication.dto.BriefcaseDto;
import project.communication.service.CommunicationService;


@RestController
@RequiredArgsConstructor
public class CommunicationController {
	
	final CommunicationService communicationService;
	
	@PostMapping("/{username}/briefcase")
	public boolean addBriefcase( @RequestBody BriefcaseDto briefcaseDto) {
		return communicationService.createBriefcase(briefcaseDto);
	}
	

}
