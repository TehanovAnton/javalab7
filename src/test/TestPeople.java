package test;

import org.junit.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import src.shower.DayTime;
import src.shower.People;
import src.shower.PersonSex;
import src.shower.Shower;

public class TestPeople{
    private static Shower shower = new Shower();
    private static People people = new People(PersonSex.male, "anton", shower, new DayTime[]{DayTime.morning, DayTime.night});;

//        @BeforeClass
    @Before
    public void Before(){
        System.out.println("START");
    }

//    @AfterClass
    @After
    public void After(){
        System.out.println("END");
    }

    @Ignore()
    @Test
    public void TestShoweredForToday(){
        Assert.assertFalse(people.ShoweredForToday());
    }

    @Test(timeout = 2000)
    public void TestFreeUp() throws InterruptedException {
        people.FreeUp();
        Assert.assertTrue(shower.getShowerAccess());
        Assert.assertEquals(PersonSex.none, shower.getShoweredPersonSex());
    }

    @ParameterizedTest
    @EnumSource(value = PersonSex.class, names = {"male"})
    public void TestTakeShower(PersonSex sex) throws InterruptedException {
        people.TakeShower();
        Assert.assertEquals(sex, shower.getShoweredPersonSex());
        Assert.assertEquals(false, shower.getShowerAccess());
    }

    @Test
    public void TestisTime(){
        Assert.assertEquals(0, people.showerTaken);
    }

}
