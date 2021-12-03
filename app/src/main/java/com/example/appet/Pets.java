package com.example.appet;

public class Pets {

    private String age;
    private String animal;
    private String breed;
    private String conditions;
    private String datebirth;
    private String id;
    private String name;


    public Pets() {
    }

    public Pets(String age, String animal, String breed, String conditions, String datebirth, String id, String name) {
        this.age = age;
        this.animal = animal;
        this.breed = breed;
        this.conditions = conditions;
        this.datebirth = datebirth;
        this.id = id;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(String datebirth) {
        this.datebirth = datebirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
