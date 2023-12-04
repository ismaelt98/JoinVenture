package com.joinventure.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinventure.entities.SetEntity;
import com.joinventure.entities.DTOs.SetDTO;
import com.joinventure.repositories.SetRepository;

@Service
public class SetService {
	@Autowired
	private SetRepository setRepo;

	public List<SetDTO> findAllRoleSetUser() {

		List<SetEntity> users = setRepo.findAll();

		List<SetDTO> setDTOList = new ArrayList<SetDTO>();
		for (SetEntity setAll : users) {
			SetDTO setDTO = new SetDTO();
			setDTO.setName_project(setAll.getProject().getName());
			
			setDTO.setNumMaxMembers(setAll.getProject().getNumMembers());
			setDTO.setName_Sector(setAll.getSector().getName());
			setDTO.setName_Demand(setAll.getDemand().getName());
			setDTOList.add(setDTO);
		}
		return setDTOList;
	}
}
