package src.shower;

public class Hostel {
    // время, shower
    public static DayTime time = DayTime.night;

    public static void hostelWorkDayStart() throws InterruptedException {
        System.out.println("lets go\n");
        Shower shower = new Shower();

        var anton = new People(PersonSex.male, "anton", shower, new DayTime[]{DayTime.morning, DayTime.night});
        var andrew = new People(PersonSex.male, "andrew", shower, new DayTime[]{DayTime.morning, DayTime.night});
        var lida = new People(PersonSex.female, "lida", shower, new DayTime[]{DayTime.morning, DayTime.night});
        var liza = new People(PersonSex.female, "liza", shower, new DayTime[]{DayTime.morning, DayTime.night});
        var Anna = new People(PersonSex.female, "liza", shower, new DayTime[]{DayTime.morning, DayTime.night});

        anton.start();
        andrew.start();
        lida.start();
        liza.start();
        Anna.start();
    }
}
