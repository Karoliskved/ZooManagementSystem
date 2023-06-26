package com.example.demo.models;



public class Enclosure {

    private String name;

    private String size;

    private String location;

    private String[] objects;
    private  String[] assignedAnimals={};
    private String assignedAnimalsDiet;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getObjects() {
        return this.objects;
    }

    public void setObjects(String[] objects) {
        this.objects = objects;
    }


    public String[] getAssignedAnimals() {
        return this.assignedAnimals;
    }

    public void setAssignedAnimals(String[] assignedAnimals) {
        this.assignedAnimals = assignedAnimals;
    }
    
    public String getAssignedAnimalsDiet() {
        return this.assignedAnimalsDiet;
    }

    public void setAssignedAnimalsDiet(String assignedAnimalsDiet) {
        this.assignedAnimalsDiet = assignedAnimalsDiet;
    }

}