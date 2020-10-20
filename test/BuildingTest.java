package test;

import main.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
import static org.junit.Assert.*;
public class BuildingTest<testResident> {

    private final String EXPECTED_ADDRESS = "Racoon City's undergrounds";
    private final int EXPECTED_FLOORS_COUNT = 300;
    private final int EXPECTED_APARTMENTS_COUNT = 3;
    private final int EXPECTED_FREE_APARTMENTS_COUNT = 3;
    private final double EXPECTED_BUILTUPAREA = 45.560;
    private final boolean EXPECTED_AVAILABLE_ELEVATOR = true;
    // private Map<main.Person, Map<Integer, Integer>>
    private BuildingResidents testResident;// = new main.BuildingResidents("Sarah", 21);
    private Map<Person, Map<Integer, Integer>> buildingResidentsMap;
    private Building building;

    @Before
    public void setup() {
        //building = new main.Building(EXPECTED_ADDRESS, EXPECTED_FLOORS_COUNT, EXPECTED_APARTMENTS_COUNT, EXPECTED_BUILTUPAREA, EXPECTED_AVAILABLE_ELEVATOR);
        building = new Building();
    }

    @Test
    public void checkBuildingAddressIsAsExpected() {
        building.setBuildingAddress(EXPECTED_ADDRESS);
        Assert.assertEquals(EXPECTED_ADDRESS, building.getBuildingAddress());
    }

    @Test
    public void checkBuildingFloorsCountIsAsExpected() {
        building.setBuildingFloorsCount(EXPECTED_FLOORS_COUNT);
        Assert.assertEquals(EXPECTED_FLOORS_COUNT, building.getBuildingFloorsCount());
    }

    @Test
    public void checkBuildingApartmentsCountIsAsExpected() {
        building.setBuildingApartmentsCount(EXPECTED_APARTMENTS_COUNT);
        Assert.assertEquals(EXPECTED_APARTMENTS_COUNT, building.getBuildingApartmentsCount());
    }

    @Test
    public void checkBuildingFreeApartmentsCountIsAsExpected() {
        building.setFreeBuildingApartmentsCount(EXPECTED_FREE_APARTMENTS_COUNT);
        Assert.assertEquals(EXPECTED_FREE_APARTMENTS_COUNT, building.getFreeBuildingApartmentsCount());
    }

    @Test
    public void checkBuildingBuiltUpAreaIsAsExpected() {
        building.setBuildingBuiltUpArea(EXPECTED_BUILTUPAREA);
        Assert.assertEquals(EXPECTED_BUILTUPAREA, building.getBuildingBuiltUpArea(), 0.0001);
    }

    @Test
    public void checkBuildingElevatorAvailabilityIsTrue() {
        building.setAvailableElevator(EXPECTED_AVAILABLE_ELEVATOR);
        Assert.assertEquals(EXPECTED_AVAILABLE_ELEVATOR, building.isAvailableElevator());
    }

    @Test
    public void checkIfBuildingContainsTestResident() {
        testResident = new BuildingResidents("Sarah", 21);
        buildingResidentsMap = new TreeMap<>();
        buildingResidentsMap.put(testResident, new TreeMap<>());
        buildingResidentsMap.get(testResident).put(1, 1);

        building.setBuildingResidents(buildingResidentsMap);
        Assert.assertEquals(buildingResidentsMap.size(), building.getBuildingResidents().size());
        assertTrue(buildingResidentsMap.containsKey(testResident));
    }
}
