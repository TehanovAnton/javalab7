package src.shower;

import java.util.Arrays;

public class People extends Thread {
    public PersonSex sex;
    public String name;
    public int showerTaken;
    public DayTime showerTime[];
    private Shower instance;

    public People(PersonSex sex, String name, Shower shower, DayTime showerTime[]){
        this.sex = sex;
        this.name = name;
        this.showerTaken = 0;
        this.showerTime = showerTime;
        this.instance = shower;
    }

    public boolean ShoweredForToday(){
        return showerTaken == 2;
    }

    public void FreeUp() throws InterruptedException {
        instance.FreeUp(this);
        if (ShoweredForToday())
            interrupt();
        else
            Thread.currentThread().sleep(1000);
    }

    public void TakeShower() throws InterruptedException {
        instance.OccupyShower(sex, this);
    }

    public void IsTime(){
        if (!Arrays.asList(showerTime).contains(Hostel.time))
            interrupt();
    }

    @Override
    public void run() {
        try{
            IsTime();

            for (; !isInterrupted();){
                if (instance.getShowerAccess() || (!instance.getShowerAccess() && instance.getShoweredPersonSex() == sex)){
                    TakeShower();
                    FreeUp();
                }
                else {
                    Thread.currentThread().sleep(500);
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
