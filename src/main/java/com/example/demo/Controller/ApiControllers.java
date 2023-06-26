package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.lang3.ArrayUtils;
import com.example.demo.models.Animal;

import com.example.demo.models.Enclosure;

import com.example.demo.repo.AnimalRepo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private AnimalRepo animalRepo;

    @GetMapping(value = "/animals")
    public List<Animal> getAnimals() {
        return animalRepo.findAll();
    }

    @PostMapping(value = "/saveAnimals")
    public String saveAnimal(@RequestBody String animaljson) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(animaljson, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray animalsArray = jsonObject.getAsJsonArray("animals");
        Animal[] animals = gson.fromJson(animalsArray, Animal[].class);
        for (Animal animal : animals) {
            animalRepo.save(animal);
        }
        return "saved";

    }
    @PutMapping(value="update/{id}")
    public String updateAnimal(@PathVariable long id, @RequestBody Animal animal){
        Animal updatedAnimal=animalRepo.findById(id).get();
        updatedAnimal.setAssignedEnclosure(animal.getAssignedEnclosure());
        updatedAnimal.setFood(animal.getFood());
        updatedAnimal.setNumberOf(animal.getNumberOf());
        updatedAnimal.setSpecies(animal.getSpecies());
        animalRepo.save(updatedAnimal);
        return "updated";
}
    
    @DeleteMapping(value = "deleteAnimal/{id}")
    public String deleteAnimal(@PathVariable long id) {
        Animal deleteAnimal = animalRepo.findById(id).get();
        animalRepo.delete(deleteAnimal);
        return "deleted animal id:" + id;
    }

    @PostMapping(value = "/assignAnimals")
    public String assignAnimals(@RequestBody String enclosureJson) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(enclosureJson, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray enclosuresArray = jsonObject.getAsJsonArray("enclosures");
        Enclosure[] enclosures = gson.fromJson(enclosuresArray, Enclosure[].class);
        List<Animal> animals = animalRepo.findAll();
        for (Animal animal : animals) {
            int minAnimalCount = 0;
            Boolean assignedStatus = false;
            for (int i = 0; i < enclosures.length; i++) {
                if (enclosures[i].getAssignedAnimals().length == 0) {
                    String[] assignedArray = { animal.getSpecies() };
                    String assignedString = animal.getFood();
                    enclosures[i].setAssignedAnimals(assignedArray);
                    enclosures[i].setAssignedAnimalsDiet(assignedString);
                    animal.setAssignedEnclosure(enclosures[i].getName());
                    assignedStatus = true;
                    break;
                }
            }
            if (!assignedStatus) {
                Boolean successStatus=recursiveAssginment(animal, enclosures, minAnimalCount);
                if(!successStatus){
                    System.out.println("could not assign animals");
                }
            }
        }
        for (Animal animal : animals){
            animalRepo.save(animal);
        }
        return "all animals   assigned";
    }

    public Boolean recursiveAssginment(Animal animal, Enclosure[] enclosures, int minAnimalCount) {
        minAnimalCount++;
        //a hard limit on recursion in case of an infinant loop
        if (minAnimalCount > 100) {
            return false;
        }  
        for (Enclosure enclosure : enclosures) {
      
            if (enclosure.getSize().equals("Huge")
                    && enclosure.getAssignedAnimalsDiet().equals(animal.getFood())
                    && enclosure.getAssignedAnimals().length == minAnimalCount) {
                String[] appendedArray = ArrayUtils.add(enclosure.getAssignedAnimals(), animal.getSpecies());
                enclosure.setAssignedAnimals(appendedArray);
                animal.setAssignedEnclosure(enclosure.getName());
                return true;

            }
            else if (enclosure.getSize().equals("Large")
                    && enclosure.getAssignedAnimalsDiet().equals(animal.getFood())
                    && enclosure.getAssignedAnimals().length == minAnimalCount) {
                String[] appendedArray = ArrayUtils.add(enclosure.getAssignedAnimals(), animal.getSpecies());
                enclosure.setAssignedAnimals(appendedArray);
                animal.setAssignedEnclosure(enclosure.getName());
  
                return true;
            }
            else if (enclosure.getSize().equals("Medium")
                    && enclosure.getAssignedAnimalsDiet().equals(animal.getFood())
                    && enclosure.getAssignedAnimals().length == minAnimalCount) {
                String[] appendedArray = ArrayUtils.add(enclosure.getAssignedAnimals(), animal.getSpecies());
                enclosure.setAssignedAnimals(appendedArray);
                animal.setAssignedEnclosure(enclosure.getName());
                
                return true;
            }
            else if (enclosure.getSize().equals("Small")
                    && enclosure.getAssignedAnimalsDiet().equals(animal.getFood())
                    && enclosure.getAssignedAnimals().length == minAnimalCount) {
                String[] appendedArray = ArrayUtils.add(enclosure.getAssignedAnimals(), animal.getSpecies());
                enclosure.setAssignedAnimals(appendedArray);
                animal.setAssignedEnclosure(enclosure.getName());
              
                return true;
            } 
        }
        return recursiveAssginment(animal, enclosures, minAnimalCount);
    }

}
