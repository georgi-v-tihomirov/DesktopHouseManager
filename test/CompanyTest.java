package test;

import main.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class CompanyTest {
    private Company company;

    @Before
    public void setup(){
        company = new Company();
    }

    @Test
    public void checkCompanyNameIsAsExpected() {
        company.setCompanyName("TestCompanyName");
        Assert.assertEquals("TestCompanyName", company.getCompanyName());
    }

    @Test
    public void checkCompanyHeadquartersAddressIsAsExpected() {
        company.setCompanyHeadquartersAddress("Racoon City's undergrounds");
        Assert.assertEquals("Racoon City's undergrounds", company.getCompanyHeadquartersAddress());
    }

    @Test
    public void checkCompanyManagedBuildingsListIsAsExpected() {
        Building testBuilding = new Building("Vertigo Tower", 100, 1000, 35.60, true);
        company.addToManagedBuildings(testBuilding);
        Assert.assertEquals(1, company.getCompanyManagedBuildingsList().size());
        Building testBuilding1 = new Building("Infinity Tower", 10, 10000, 24.57, true);
        ArrayList testBuildingList = new ArrayList<Building>();
        testBuildingList.add(testBuilding);
        testBuildingList.add(testBuilding1);
        company.setCompanyManagedBuildingsList(testBuildingList);
        Assert.assertEquals(testBuildingList, company.getCompanyManagedBuildingsList());
    }

    @Test
    public void checkCompanyEmployeesHireAndReleaseIsAsExpected() {
        CompanyEmployee testEmployee = new CompanyEmployee("Beatrice", 23);
        company.hireEmployee(testEmployee, Position.MANAGER, 350.00);
        Assert.assertEquals(1, company.getCompanyEmployeesAndPosition().size());
        Assert.assertEquals(true, company.isPersonAnEmployee(testEmployee));
        company.releaseEmployeeFromPosition(testEmployee);
        Assert.assertEquals(0, company.getCompanyEmployeesAndPosition().size());  //There should be no employees in the company
        Assert.assertEquals(1, company.getPastCompanyEmployees().size());         //There should be one past employee
    }

    @Test
    public void checkCompanyEmployeesPositionAndSalaryIsAsExpected() {
        CompanyEmployee testEmployee = new CompanyEmployee("Beatrice", 23);
        company.hireEmployee(testEmployee, Position.MANAGER, 350.00);
        Assert.assertEquals(Position.MANAGER, company.getCompanyEmployeesAndPosition().get(testEmployee));
        Assert.assertEquals(350.00, company.getCompanyEmployeesAndSalary().get(testEmployee), 0.001);
    }

    @Test
    public void checkEmployeeAndTheirManagedBuildingsIsAsExpected() {
        CompanyEmployee testEmployee = new CompanyEmployee("Gabriel", 32);
        company.hireEmployee(testEmployee, Position.MANAGER, 350.00);
        Building testBuilding1 = new Building("Infinity Tower", 10, 10000, 24.57, true);
        company.assignBuildingToManager(testEmployee, testBuilding1);
        Assert.assertEquals(1, company.showEmployeeManagedBuildings(testEmployee));    //The employee should only manage one building for now
        assertTrue(company.getEmployeeAndTheirManagedBuildings().get(testEmployee).contains(testBuilding1));
    }

    @Test
    public void checkBuildingResidentsTaxesAnPayingIsAsExpected() {
        BuildingResidents sarah = new BuildingResidents("Sarah", 21);
        BuildingResidents nicky = new BuildingResidents("Nicky", 13);
        Building testBuilding1 = new Building("Infinity Tower", 10, 10000, 24.57, true);
        CompanyEmployee testEmployee = new CompanyEmployee("Gabriel", 32);
        company.hireEmployee(testEmployee, Position.MANAGER, 350.00);
        company.assignBuildingToManager(testEmployee, testBuilding1);
        company.MoveInBuilding(testBuilding1, sarah, 1, 1);     //Moving sarah in building
        company.MoveInBuilding(testBuilding1, nicky, 2, 1);     //Moving nicky in building
        company.calculateAndAssignTaxes(testBuilding1, 10.0, 20.0);     //Assigning the tax
        Assert.assertEquals(2, company.getBuildingResidentsTaxes().get(testBuilding1).size());     //Two taxes should be assigned
        assertTrue(company.getBuildingResidentsTaxes().get(testBuilding1).containsKey(sarah));      //Sarah should have an assigned tax
        assertTrue(company.getBuildingResidentsTaxes().get(testBuilding1).containsKey(nicky));      //Nicky should have an assigned tax
        company.payingTaxes(testBuilding1, sarah);      //Sarah is paying her tax
        assertTrue(company.getAreBuildingResidentsTaxesPaid().get(testBuilding1).get(sarah));       //Sarah has paid her tax
        assertFalse(company.getAreBuildingResidentsTaxesPaid().get(testBuilding1).get(nicky));      //Nicky hasn't paid his tax
    }
}
