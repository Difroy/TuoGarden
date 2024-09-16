package com.generation.garden.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.garden.model.entities.Plant;
import com.generation.garden.model.repository.PlantRepository;


//1. ACCETTO RICHIESTE VIA WEB (essere un controller)
//2. PRODUCO JSON. Quando i miei metodi produranno oggetti questi verranno convertiti in JSON.

@RestController
public class PlantAPI {

	
	/*Prima facevamo:
	 * PlantRepository repo = PlantRepositoryFactory.make();
	 * e avrei ottenuto una PlantRepositorySQL()
	 * si traduce in:*/
	
	
	@Autowired 				// Iniezione della factory.
	PlantRepository repo;
	
	
	
	//quando il mio utente si collegherà all'indirizzo /plants del mio sito vedra una lista di piante. Vedrà il risultato di questo metodo in FORMATO JSON.
	
	
	//Noi qui sposteremo lo stato dell'oggetto in formato JSON.
	//JSON è letteralmente lo STATO di un oggetto in JS.
	//da provare tramite Postman.
	
	@GetMapping("/plants")
	public List<Plant> getPlants(){
		return repo.findAll();
	}
	
	
	/**
	 * quando l'utente chiama l'indirizzo /plants col mentodo post io eseguirò non il metodo getPlant() ma il metodo insertPlant il COROPO della request deve contenere un JSON con i dati della pianta.
	 * @param newPlant
	 * @return
	 * */
	
	
	
	
	@PostMapping("/plants")
	public Plant insertPlant(@RequestBody Plant newPlant) {
		return repo.save(newPlant);
		
	}
}
