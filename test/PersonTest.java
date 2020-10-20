package test;

import main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {
    private Person person;

    @Before
    public void setup(){
        person = new BuildingResidents();
    }

    @Test
    public void checkPersonNameIsAsExpected() {
        person.setPersonName("Britney");
        Assert.assertEquals("Britney", person.getPersonName());
    }

    @Test
    public void checkPersonAgeIsAsExpected() {
        person.setPersonAge(21);
        Assert.assertEquals(21, person.getPersonAge());
    }

    @Test
    public void checkPersonAddressIsAsExpected() {
        Building testPersonAddressBuilding = new Building("Racoon City's undergrounds", 300, 3, 30000, true);
        person.setPersonAddress(testPersonAddressBuilding);
        Assert.assertEquals(testPersonAddressBuilding, person.getPersonAddress());
    }

    @Test
    //Shouldn't be started as an individual test - an error will occur.
    //Since we have the 'Before' annotation, before each test a new instance
    //of the 'main.BuildingResidents' class is created.
    //Thus, the counter in the PID field is increased four times and the expected identity number should be four.
    public void checkPersonIdentificationNumberIsAsExpected() {
        Assert.assertEquals(4, person.getPersonIdentityNumber());
    }
}
