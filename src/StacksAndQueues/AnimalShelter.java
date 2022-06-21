package StacksAndQueues;

import java.util.LinkedList;
import java.util.List;

/*
3.1 Three in one
pg. 98
Create dynamically resizing triple stack using one array
 */
public class AnimalShelter {
    /*********************
     * Private sub-classes
     **********************/
    private interface Animal {
        String name = null;
    }

    private class Dog implements Animal {}
    private class Cat implements   Animal {}

    private class ShelterSpot  {
        Animal adoptMe;
        int serialNum;

        ShelterSpot(Animal a, int serial) {
            this.adoptMe = a;
            this.serialNum = serial;
        }
    }

    /***********************
     * Class objects
     **********************/
    List<ShelterSpot> dogs = new LinkedList<>();
    List<ShelterSpot> cats = new LinkedList<>();
    int serialNumber = 0;

    /***********************
     * Class methods
     **********************/
    public void enqueue (Dog pup) { dogs.add(new ShelterSpot(pup, serialNumber++)); }

    public void enqueue (Cat kit) { cats.add(new ShelterSpot(kit, serialNumber++)); }

    public Animal dequeueDog() {
        return dogs.remove(0).adoptMe;
    }

    public Animal dequeueCat() {
        return cats.remove(0).adoptMe;
    }

    public Animal dequeueAny() {
        if (dogs.get(0).serialNum < cats.get(0).serialNum)
            return dequeueDog();
        else
            return dequeueCat();
    }
}
