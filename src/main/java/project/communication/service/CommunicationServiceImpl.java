package project.communication.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
//import project.communication.dao.BriefcaseRepository;
import project.communication.dto.BriefcaseDto;
//import project.communication.model.Briefcase;


@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {
	
//	final BriefcaseRepository briefcaseRepository;
	final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public boolean createBriefcase(BriefcaseDto briefcaseDto) {
//		if(briefcaseRepository.existsById(briefcaseDto.getNameCase())) {
//			return false;
//		}
//		Briefcase  briefcase = modelMapper.map(briefcaseDto, Briefcase.class);
//		briefcaseRepository.save(briefcase);
		return true;
	}

	@Override
	public BriefcaseDto findBriefcaseByNameCase(String nameCase) {
		// TODO Auto-generated method stub
		return null;
	}

}
