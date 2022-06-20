package StacksAndQueues;

public class AnimalShelter {
    private class Animal {
        String name = "name";
    }

    private class Dog extends Animal {}
    private class Cat extends  Animal {}

    private class ShelterSpot {
        Animal adoptMe;
        int serialNum;

        ShelterSpot(Animal a, int serial) {
            this.adoptMe = a;
            this.serialNum = serial;
        }
    }
}
