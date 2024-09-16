package com.generation.garden.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.garden.model.entities.Plant;

public interface PlantRepository extends JpaRepository <Plant, Long>{

	
	List<Plant> findByStatus(String status);
	
	List<Plant> findByNameContaining (String n);
	
	
	
}
