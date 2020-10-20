package main;

public class Main {
    public static void main(String[] args) {
        BuildingResidents sarah = new BuildingResidents("Sarah", 21);
        BuildingResidents nicky = new BuildingResidents("Nicky", 13);
        BuildingResidents leon = new BuildingResidents("Leon", 33);
        BuildingResidents cherry = new BuildingResidents("Cherry", 18);

        CompanyEmployee alice = new CompanyEmployee("Alice", 70);
        CompanyEmployee redqueen = new CompanyEmployee("Red Queen HBIC", 700);
        CompanyEmployee albertwesker = new CompanyEmployee("Albert Weskar", 32);

        Building RacoonCityHive = new Building("Racoon City's undergrounds", 300, 15, 30000, true);
        Building ArcadiaShip = new Building("SS Arcadia", 15, 300, 600, true);
        Building UmbrellaUnderwaterPrimeTestingFacility = new Building("Soviet naval outpost, Russia, Kamchatka Peninsula", 100, 200, 130000, true);

        Company UmbrellaC = new Company("Umbrella Corp. Unlimited", "WorldWide T-Vir 21 blvd.");

        //Hiring employees
        UmbrellaC.hireEmployee(alice, Position.DIRECTOR, 13333000.00);
        UmbrellaC.hireEmployee(redqueen, Position.MANAGER, 567681.00);
        UmbrellaC.hireEmployee(albertwesker, Position.MANAGER, 30.00);

        //Assigning building to manager
        UmbrellaC.assignBuildingToManager(redqueen, RacoonCityHive);
        UmbrellaC.assignBuildingToManager(redqueen, UmbrellaUnderwaterPrimeTestingFacility);
        UmbrellaC.assignBuildingToManager(albertwesker, ArcadiaShip);
        UmbrellaC.releaseEmployeeFromPosition(redqueen); //Has active buildings
        //UmbrellaC.assignBuildingToManager(alice, UmbrellaUnderwaterPrimeTestingFacility); // Alice is not a manager

        //UmbrellaC.showEmployeeManagedBuildings(redqueen);
        //Moving in residents
        UmbrellaC.MoveInBuilding(RacoonCityHive, sarah, 1, 1);
        UmbrellaC.MoveInBuilding(RacoonCityHive, nicky, 4, 10);
        UmbrellaC.MoveInBuilding(UmbrellaUnderwaterPrimeTestingFacility, leon, 1, 1);
        UmbrellaC.MoveInBuilding(UmbrellaUnderwaterPrimeTestingFacility, cherry, 4, 10);

        //Taxes
        UmbrellaC.calculateAndAssignTaxes(RacoonCityHive, 20.0, 15.0);
        UmbrellaC.calculateAndAssignTaxes(UmbrellaUnderwaterPrimeTestingFacility, 300.0, 25.0);
        UmbrellaC.payingTaxes(RacoonCityHive, sarah);
        //UmbrellaC.payingTaxes(RacoonCityHive, nicky);
        System.out.println(UmbrellaC.showSpecifiedBuildingTaxes(RacoonCityHive));
        System.out.println(UmbrellaC.showSpecifiedBuildingTaxes(ArcadiaShip));
        System.out.println(UmbrellaC.showSpecifiedBuildingTaxes(UmbrellaUnderwaterPrimeTestingFacility));
        System.out.println(UmbrellaC.showSpecifiedBuildingTaxesPaid(RacoonCityHive));

        System.out.println(UmbrellaC.showAllBuildingResidentsTaxes());
        System.out.println(UmbrellaC.showAllBuildingResidentsTaxesPaid());

        System.out.println(UmbrellaC.toString());
    }
}
