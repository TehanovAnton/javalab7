package src.shower;

import src.shower.People;
import src.shower.PersonSex;

public class Shower {
    private boolean showerAccess;
    private PersonSex showeredPersonSex;

    public PersonSex getShoweredPersonSex() {
        return showeredPersonSex;
    }

    public Shower(){
        showerAccess = true;
        showeredPersonSex = PersonSex.none;
    }

    public synchronized boolean getShowerAccess() {
        return showerAccess;
    }

    public synchronized void OccupyShower(PersonSex sex, People people) throws InterruptedException {
        System.out.println(people.name + " occupied shower " + people.showerTaken);

        synchronized (this)
        {
            showerAccess = false;
            showeredPersonSex = sex;
            people.showerTaken++;
        }

        Thread.currentThread().sleep(1000);
    }

    public void FreeUp(People people){
        synchronized (this){
            showerAccess = true;
            showeredPersonSex = PersonSex.none;
            System.out.println(people.name + " free up " + people.showerTaken);
        }
    }
}
