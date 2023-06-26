# ZooManagementSystem

A Simple zoo management system which takes data about animals and zoo enclosures and automaticly assigns animals to enclosures and saves it to a database

Uses rest api calls to input data

saves output in a mysql database

Made with Java and the spring framework

Currently takes into acounmt these aspects:

- Does not put carnivores and herbivores together
- Tries to populate the bigger enclosures first



Usage

first you need to save animals to the database by calling this api call localhost:8080/saveAnimals, with the animal JSON data provided in the body

then you need to call localhost:8080/assignAnimals, with the enclosure JSON data to assing animals to a specific enclosures. The data is automatically saved to the database.

You can also view, delete and update animals with these api calls: localhost:8080/animals, localhost:8080/deleteAnimal/{id} and localhost:8080/update/{id}
