package com.generation.garden.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.generation.garden.model.entities.Plant;
import com.generation.garden.model.repository.PlantRepository;


/*Spiegazione IMPRECISIONE, ma pratica, dello stile rest.
 * 1. Ogni verbo di HTTP ha un significato diverso.
 * In una WebApp puoi fare tutto usando il verbo get o il verbo post, con rest no. Ogni operazione di CRUD è mappata da un verbo diverso.
 * 
 * 
 * 2. Ogni URL corrisponde ad una risorsa o collezione di risorse e ora che diavolo è una risorsa? Una entity.
 * 
 * /
 * 
 * 3.Non sempre vero, ma vero nella pratica.
 * Se uso lo stile REST io produco e ricevo JSON.
 * 
 * */





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
	//Chiamo questo indirizzo col verbo HTTP GET. R in CRUD
	
	@GetMapping("/plants")
	public List<Plant> getPlants(){
		return repo.findAll();
	}
	
	
	/**
	 * quando l'utente chiama l'indirizzo /plants col mentodo post io eseguirò non il metodo getPlant() ma il metodo insertPlant il COROPO della request deve contenere un JSON con i dati della pianta.
	 * @param newPlant
	 * @return
	 * */
	
	
	
	//Chiamo l'indirizzo plants conPOST => creo un nuovo oggetto, C in CRUD
	@PostMapping("/plants")
	public Plant insertPlant(@RequestBody Plant newPlant) {
		return repo.save(newPlant);
		
	}
	
	
	// U in CRUD. PUT => update.  Save funzione da insert o da update, a seconda che riconosca o meno l'id.
		@PutMapping("/plants")
		public Plant updatePlant(@RequestBody Plant newPlant) {
			return repo.save(newPlant);
			
		}
	
	
	//MAPPATURA PARAMETRICA. L'id è il paramentro di questa mappatura.
		@DeleteMapping("/plants/{id}")
		public void deletePlant(@PathVariable("id")long id)
		{
			repo.deleteById(id);
		}
		
	
		
		
		
		
		@GetMapping("/plants/bystatus/{s}")
		public List<Plant> getPlantsByStatus(@PathVariable("s") String status) {
			
			return repo.findByStatus(status);
			
		}
		
		@GetMapping("/plants/byname/{n}")
		public List<Plant> getPlantsByName(@PathVariable("n")String namePart){
			return repo.findByNameContaining(namePart);
		}
	
	
	
	
}
