package com.joinventure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joinventure.entities.Sector;
import com.joinventure.repositories.SectorRepository;

@Service
public class SectorService {
	
	@Autowired
	private SectorRepository sectorRepo;
	
	public List<Sector> findAllSectors(){
		return sectorRepo.findAll();
	}
	
	public Optional<Sector> findById(Long id){
		return sectorRepo.findById(id);
	}
	
	public List<Sector> findByStringInName(String searchString) {
		List<Sector> sectorList1 = sectorRepo.findAll().stream()
				.filter(sector -> sector.getName().contains(searchString)).toList();
		return sectorList1;
	}
	
	public ResponseEntity<Object> createNewSector(Sector sector){
		if(sectorRepo.existsByName(sector.getName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El nombre ya existe en la base de datos");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(sectorRepo.save(sector));
		}
	}
	
	public boolean updateSectorByName(String name, Sector updatedSector) {
		if(sectorRepo.existsByName(name)) {
			Sector existingSector = sectorRepo.findByName(name);
			
			existingSector.setName(updatedSector.getName());
			
			sectorRepo.save(existingSector);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteSectorById(Long id) {
		if(sectorRepo.existsById(id)) {
			sectorRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
