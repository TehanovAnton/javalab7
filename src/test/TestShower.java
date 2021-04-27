package test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import src.shower.DayTime;
import src.shower.People;
import src.shower.PersonSex;
import src.shower.Shower;

import org.junit.Assert;
import org.junit.Test;

@RunWith(DataProviderRunner.class)
public class TestShower {
    @DataProvider
    public static Object[][] createData(){
        return new Object[][]{
                {new Shower(), new People(PersonSex.male, "anton", shower, new DayTime[]{DayTime.morning, DayTime.night})}
        };
    }

    static Shower shower;
    static People people;

    @BeforeClass
    public static void Init(){
        shower = new Shower();
        people = new People(PersonSex.male, "anton", shower, new DayTime[]{DayTime.morning, DayTime.night});
    }

    @Test
    @UseDataProvider("createData")
    public void TestOccupyShower(Shower shower, People people) throws InterruptedException {
        shower.OccupyShower(people.sex, people);

        Assert.assertFalse(shower.getShowerAccess());
        Assert.assertEquals(people.sex, shower.getShoweredPersonSex());
    }
}
