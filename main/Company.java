package main;

import java.util.*;

public class Company {
    private String companyName;
    private String companyHeadquartersAddress;
    private List<Building> companyManagedBuildingsList;
    private List<CompanyEmployee> pastCompanyEmployees;
    //private Map<main.Person, Map<main.Position, Double>> companyEmployees;                      //Map: main.Company employee -> main.Position | Salary
    private Map<CompanyEmployee, Position> companyEmployeesAndPosition;                 //Map: main.Company employee -> main.Position
    private Map<CompanyEmployee, Double> companyEmployeesAndSalary;                     //Map: main.Company employee -> Salary
    private Map<CompanyEmployee, List<Building>> employeeAndTheirManagedBuildings;      //Map: main.Company employee -> List of managed buildings
    private Map<Building, Map<Person, Double>> buildingResidentsTaxes;                  //Map: main.Building -> main.Building resident | service tax
    private Map<Building, Map<Person, Boolean>> areBuildingResidentsTaxesPaid;          //Map: main.Building -> main.Building resident | has service tax been paid?

    //Default constructor
    public Company() {
        //companyEmployees = new TreeMap<>();
        companyEmployeesAndPosition = new TreeMap<>();
        companyEmployeesAndSalary = new TreeMap<>();
        pastCompanyEmployees = new ArrayList<>();
        companyManagedBuildingsList = new ArrayList<>();
        //employeeSalary = new TreeMap<>();
        employeeAndTheirManagedBuildings = new TreeMap<>();
        buildingResidentsTaxes = new TreeMap<>();
        areBuildingResidentsTaxesPaid = new TreeMap<>();
        //buildingResidentsHasPayedTaxes = new ArrayList<>();
    }


    //Constructor for establishing a company and later manipulating it
    public Company(String companyName, String companyHeadquartersAddress) {
        this.companyName = companyName;
        this.companyHeadquartersAddress = companyHeadquartersAddress;
        //companyEmployees = new TreeMap<>();
        companyEmployeesAndPosition = new TreeMap<>();
        companyEmployeesAndSalary = new TreeMap<>();
        pastCompanyEmployees = new ArrayList<>();
        companyManagedBuildingsList = new ArrayList<>();
        //employeeSalary = new TreeMap<>();
        employeeAndTheirManagedBuildings = new TreeMap<>();
        buildingResidentsTaxes = new TreeMap<>();
        areBuildingResidentsTaxesPaid = new TreeMap<>();
        //buildingResidentsHasPayedTaxes = new ArrayList<>();
    }

    //Constructor for building a whole company
    public Company(String companyName,
                   String companyHeadquartersAddress,
                   ArrayList<Building> companyManagedBuildings,
                   //Map<main.Person, Map<main.Position, Double>> companyEmployees,
                   Map<CompanyEmployee, Position> companyEmployeePosition,
                   Map<CompanyEmployee, Double> companyEmployeeSalary,
                   Map<CompanyEmployee, List<Building>> employeeManagedBuildings,
                   Map<Building, Map<Person, Double>> buildingResidentsTaxes,
                   Map<Building, Map<Person, Boolean>> areBuildingResidentsTaxesPaid) {
        this.companyName = companyName;
        this.companyHeadquartersAddress = companyHeadquartersAddress;
        //this.companyEmployees = companyEmployees;
        this.companyEmployeesAndPosition = companyEmployeePosition;
        this.companyEmployeesAndSalary = companyEmployeeSalary;
        pastCompanyEmployees = new ArrayList<>();
        this.companyManagedBuildingsList = companyManagedBuildings;
        //this.employeeSalary = employeeSalary;
        this.employeeAndTheirManagedBuildings = employeeManagedBuildings;
        this.buildingResidentsTaxes = buildingResidentsTaxes;
        this.areBuildingResidentsTaxesPaid = areBuildingResidentsTaxesPaid;
        //this.buildingResidentsHasPayedTaxes = buildingResidentsHasPayedTaxes;
    }

    //Method for adding managed buildings
        /*
        Unnecessary to be called alone. The building will be added when a manager has been assigned to them.
        If the building is already created we just pass it as a variable to this function.
            If the building is not already managed
            we add it to the managed building's list
        */
    public void addToManagedBuildings(Building building) {
        if (!companyManagedBuildingsList.contains(building)) {
            companyManagedBuildingsList.add(building);
        } else {
            System.out.println("The building is already managed");
        }
    }

    /*public void addToManagedBuildings(String buildingAddress, int buildingFloorsCount, int buildingApartmentsCount, double buildingBuiltUpArea, boolean hasElevator){
        main.Building tmpBuilding = new main.Building(buildingAddress, buildingFloorsCount, buildingApartmentsCount, buildingBuiltUpArea, hasElevator);
        if(!companyManagedBuildings.contains(tmpBuilding)){
            companyManagedBuildings.add(tmpBuilding);
        }
        else{
            System.out.println("The building is already managed");
        }
    }*/

    //Method for adding residents to the building
        /*
        We shouldn't do anything if the building is not managed by the company.
        Otherwise, if the person doesn't already live in the building, if there are free apartments
        or the person is moving in with a roommate we add the person in the building residents map with the floor and the apartment.
        We decrease the free apartment count and update the person's address.
        Otherwise, an message for unsuccessful moving in is being displayed.
         */
    public void MoveInBuilding(Building building, Person person, int floor, int apartment) {                               //supposed to be a main.Person - a company employee CAN live in the building that he manages
        if (companyManagedBuildingsList.contains(building)) {                                                                   //If the building is managed by the company
            if (!building.getBuildingResidents().containsKey(person)) {                                                    //If the person doesn't already live in the building
                if (building.getFreeBuildingApartmentsCount() > 0 || apartment <= building.getBuildingApartmentsCount()) { //If there are free apartments or the person wants to live with another person
                    building.getBuildingResidents().put(person, new TreeMap<>());                                          //Move person in building
                    building.getBuildingResidents().get(person).put(floor, apartment);                                     //On floor and apartment
                    person.setPersonAddress(building);                                                                     //Update the address
                    if (building.getFreeBuildingApartmentsCount() > 0) {
                        building.setFreeBuildingApartmentsCount(building.getFreeBuildingApartmentsCount() - 1);            //Decrease the free apartments count if the newcomer doesn't live with a roommate
                    }
                    System.out.println("\nWelcome, " + person.getPersonName() + " to " + building.getBuildingAddress());
                } else {
                    System.out.println("There are no free apartments left in this building!");
                }
            } else {
                System.out.println("This person already lives in the building!");
            }
        } else {
            System.out.println("This building is not managed by this company!");
        }
    }

    //Method to kick out a person from the building
    /*
    public void KickAPersonOutOfBuilding(main.Building building, main.Person person) {
        if (companyManagedBuildingsList.contains(building) && building.getBuildingResidents().containsKey(person)) {
            //Increase the free apartments in the building
            building.setFreeBuildingApartmentsCount(building.getFreeBuildingApartmentsCount() + 1);
            //Kick out the person
            building.getBuildingResidents().remove(person);
            person.setPersonAddress(null);//********************************************************************************
        } else {
            System.out.println("This person doesn't live in the building!");
        }
    }
     */

    //Method for registering company employees
        /*
        If the person isn't currently working here, we hire them.
         */
    public void hireEmployee(CompanyEmployee companyEmployee, Position position, Double salary) {
        //if (!pastCompanyEmployees.contains(companyEmployee)) {
        if (isPersonAnEmployee(companyEmployee)) {
            System.out.println("This person already works here!");
        } else {
            //companyEmployees.put(companyEmployee, new TreeMap<>());
            //companyEmployees.get(companyEmployee).put(position, salary);
            companyEmployeesAndPosition.put(companyEmployee, position);
            companyEmployeesAndSalary.put(companyEmployee, salary);
        }
        //}
        //else{
        //    System.out.println("This person cannot be hired, because he was fired!!!");
        //}

    }

    //Method for firing employees
        /*
        The company employee, that is going to be fired, is passed to the parameter.
        If they have an active buildings, they cannot be fired. Otherwise, we release them from work.
         */
    public void releaseEmployeeFromPosition(CompanyEmployee companyEmployee) {
        if (!pastCompanyEmployees.contains(companyEmployee)) {
            if (isPersonAnEmployee(companyEmployee)) {
                if (!employeeAndTheirManagedBuildings.containsKey(companyEmployee)) {
                    pastCompanyEmployees.add(companyEmployee);
                    //companyEmployees.remove(companyEmployee);
                    companyEmployeesAndPosition.remove(companyEmployee);
                    companyEmployeesAndSalary.remove(companyEmployee);
                    System.out.println("\n" + companyEmployee.getPersonName() + " has been released from work!");
                } else {
                    System.out.println("This person has active buildings and cannot be released from work!");
                }
            } else {
                System.out.println("This person doesn't work here");
            }
        } else {
            System.out.println("This person has already been released from work!!!");
        }
    }

    //Method for checking if a person is a company employee
    public boolean isPersonAnEmployee(CompanyEmployee person) {
        if (companyEmployeesAndPosition.containsKey(person)) {
            //System.out.println("\n" + person.getPersonName() + " works here!");
            return true;
        }
        return false;
    }

    //Method for showing employee's data
    public void showEmployeeData(CompanyEmployee companyEmployee) {
        if (isPersonAnEmployee(companyEmployee)) {
            System.out.println("\nEMPLOYEE: " + companyEmployee.getPersonName()
                    + " | main.Position: " + companyEmployeesAndPosition.get(companyEmployee)
                    + " | Salary: " + companyEmployeesAndSalary.get(companyEmployee));
        }
    }

    //Methods for assigning managers to buildings
        /*
        We pass the manager and the building, to which we are going to assign them.
        We check if the building isn't already managed by someone else
        and if this building is going to be the employee's first building to manage.
        Eventually, the building is starting to be managed.
         */
    public void assignBuildingToManager(CompanyEmployee companyEmployee, Building building) {
        //if(companyManagedBuildings.contains(building)) {                                                      //If the building is managed by this company
        if (isPersonAnEmployee(companyEmployee)) {                                                          //If the person works for the company
            if (companyEmployeesAndPosition.get(companyEmployee) == Position.MANAGER) {                      //If the employee is a manager
                if (!employeeAndTheirManagedBuildings.containsKey(companyEmployee)) {                       //If this is the employee's first building to manage
                    employeeAndTheirManagedBuildings.put(companyEmployee, new ArrayList<>());               //Add the employee to the manager's list
                    employeeAndTheirManagedBuildings.get(companyEmployee).add(building);                    //Starting to manage the building
                    addToManagedBuildings(building);                                                        //Add the building to the managed building list
                } else {                                                                                    //The employee is already a manager
                    if (employeeAndTheirManagedBuildings.get(companyEmployee).contains(building)) {         //The employee manages the building
                        System.out.println("Manager " + companyEmployee.getPersonName()
                                + " is already assigned to this building " + building.getBuildingAddress());
                    } else {                                                                                   //The employee is a manager, but does not manage the buildng yet
                        employeeAndTheirManagedBuildings.get(companyEmployee).add(building);                //Starting to manage the building
                        addToManagedBuildings(building);                                                    //Add the building to the managed building list
                    }
                }
            } else {
                System.out.println(companyEmployee.getPersonName() + " is not a manager!");
            }
        } else {
            System.out.println("\n" + companyEmployee.getPersonName() + " doesn't work here anymore!");
        }
        //}
    }


    //Method for calculating tax
        /*
        We pass the initial tax to the method and the building, to which we are assigning it.
        If the person is above the third floor, we add the elevator tax. The tax payment is being set to false,
        since no taxes have being paid.
         */

    public void calculateAndAssignTaxes(Building building, double initialTax, double elevatorTax) {
        if (companyManagedBuildingsList.contains(building)) {                                                    //If the building is already managed
            buildingResidentsTaxes.put(building, new TreeMap<>());                                              //We create the map with the building as a key
            areBuildingResidentsTaxesPaid.put(building, new TreeMap<>());
            double tax = initialTax;                                                                            //Create the tax
            for (Person resident : building.getBuildingResidents().keySet()) {                                  //Foreach resident in the building
                Map<Integer, Integer> livingDetails = building.getBuildingResidents().get(resident);
                if ((Integer) livingDetails.keySet().toArray()[0] > 3 && building.isAvailableElevator()) {
                    tax = initialTax + elevatorTax;
                }
                buildingResidentsTaxes.get(building).put(resident, tax);                                       //We fill the map with the person and the tax that he has to pay
                areBuildingResidentsTaxesPaid.get(building).put(resident, false);
                tax = initialTax;
            }
        }
    }

    //Method for paying the taxes individually
    public void payingTaxes(Building building, Person person) {
        if (buildingResidentsTaxes.containsKey(building)) {                                                       //If a tax has been assigned to the managed building
            if (building.getBuildingResidents().containsKey(person)) {                                           //If a person is a building resident
                areBuildingResidentsTaxesPaid.get(building).put(person, true);                                   //Pay the tax
            } else {
                System.out.println(person.getPersonName() + " doesn't live in " + building.getBuildingAddress());
            }
        }
    }

    //Method for displaying the taxes of a building
    public String showSpecifiedBuildingTaxes(Building building) {
        String result = "";
        if (companyManagedBuildingsList.contains(building)) {
            if (buildingResidentsTaxes.containsKey(building)) {
                Map<Person, Double> taxInfo = buildingResidentsTaxes.get(building);
                result += "\nTAX INFO OF " + building.getBuildingAddress() + "\n";
                for (Person person : taxInfo.keySet()) {
                    result += "  Resident: " + person.getPersonName()
                            + ", Tax: " + taxInfo.get(person) + "\n";
                }
            } else {
                result += "There are no taxes assigned to " + building.getBuildingAddress() + " by " + companyName + "\n";
            }
        } else {
            result += " The building " + building.getBuildingAddress() + " is not managed by " + companyName + "\n";
        }
        return result;
    }

    public String showSpecifiedBuildingTaxesPaid(Building building) {
        String result = "";
        if (companyManagedBuildingsList.contains(building)) {
            if (areBuildingResidentsTaxesPaid.containsKey(building)) {
                Map<Person, Boolean> taxInfo = areBuildingResidentsTaxesPaid.get(building);
                result += "\nPAID TAX INFO OF " + building.getBuildingAddress() + "\n";
                for (Person person : taxInfo.keySet()) {
                    result += "  Resident: " + person.getPersonName()
                            + ", Paid: " + taxInfo.get(person) + "\n";
                }
            } else {
                result += "There are no taxes assigned to " + building.getBuildingAddress() + " by " + companyName + "\n";
            }
        } else {
            result += " The building " + building.getBuildingAddress() + " is not managed by " + companyName + "\n";
        }
        return result;
    }

    //Method for displaying employee's managed buildings
    public int showEmployeeManagedBuildings(CompanyEmployee companyEmployee) {
        if (isPersonAnEmployee(companyEmployee)) {
            if (employeeAndTheirManagedBuildings.containsKey(companyEmployee)) {
                System.out.println("\n" + companyEmployee.getPersonName() + " MANAGES");
                employeeAndTheirManagedBuildings.get(companyEmployee).stream().map(Building::getBuildingAddress).forEach(System.out::println);
                return employeeAndTheirManagedBuildings.get(companyEmployee).size();
            } else {
                System.out.println(companyEmployee.getPersonName() + " doesn't manage any buildings at the moment!");
            }
        } else {
            System.out.println(companyEmployee.getPersonName() + " doesn't work here and doesn't manage any buildings!");
        }
        return 0;
    }

    //Getters and setters


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyHeadquartersAddress() {
        return companyHeadquartersAddress;
    }

    public void setCompanyHeadquartersAddress(String companyHeadquartersAddress) {
        this.companyHeadquartersAddress = companyHeadquartersAddress;
    }

    public List<Building> getCompanyManagedBuildingsList() {
        return companyManagedBuildingsList;
    }

    public void setCompanyManagedBuildingsList(List<Building> companyManagedBuildingsList) {
        this.companyManagedBuildingsList = companyManagedBuildingsList;
    }

    public List<CompanyEmployee> getPastCompanyEmployees() {
        return pastCompanyEmployees;
    }

    public void setPastCompanyEmployees(List<CompanyEmployee> pastCompanyEmployees) {
        this.pastCompanyEmployees = pastCompanyEmployees;
    }

    public Map<CompanyEmployee, Position> getCompanyEmployeesAndPosition() {
        return companyEmployeesAndPosition;
    }

    public void setCompanyEmployeesAndPosition(Map<CompanyEmployee, Position> companyEmployeesAndPosition) {
        this.companyEmployeesAndPosition = companyEmployeesAndPosition;
    }

    public Map<CompanyEmployee, Double> getCompanyEmployeesAndSalary() {
        return companyEmployeesAndSalary;
    }

    public void setCompanyEmployeesAndSalary(Map<CompanyEmployee, Double> companyEmployeesAndSalary) {
        this.companyEmployeesAndSalary = companyEmployeesAndSalary;
    }

    public Map<CompanyEmployee, List<Building>> getEmployeeAndTheirManagedBuildings() {
        return employeeAndTheirManagedBuildings;
    }

    public void setEmployeeAndTheirManagedBuildings(Map<CompanyEmployee, List<Building>> employeeAndTheirManagedBuildings) {
        this.employeeAndTheirManagedBuildings = employeeAndTheirManagedBuildings;
    }

    public Map<Building, Map<Person, Double>> getBuildingResidentsTaxes() {
        return buildingResidentsTaxes;
    }

    public void setBuildingResidentsTaxes(Map<Building, Map<Person, Double>> buildingResidentsTaxes) {
        this.buildingResidentsTaxes = buildingResidentsTaxes;
    }

    public Map<Building, Map<Person, Boolean>> getAreBuildingResidentsTaxesPaid() {
        return areBuildingResidentsTaxesPaid;
    }

    public void setAreBuildingResidentsTaxesPaid(Map<Building, Map<Person, Boolean>> areBuildingResidentsTaxesPaid) {
        this.areBuildingResidentsTaxesPaid = areBuildingResidentsTaxesPaid;
    }

    //Method for displaying all buildings and the resident taxes for each building
    public String showAllBuildingResidentsTaxes() {
        String result = "\nSTATUS OF ASSIGNED TAXES from \n" + companyName + "\n";
        for (Building building : buildingResidentsTaxes.keySet()) {
            result += " BUILDING: " + building.getBuildingAddress() + "\n";
            Map<Person, Double> taxDetails = buildingResidentsTaxes.get(building);
            for (Person person : taxDetails.keySet()) {
                result += "   Resident: " + person.getPersonName()
                        + ", Tax: " + taxDetails.get(person) + "\n";
            }
        }
        return result;
    }

    //Method for displaying all buildings and if the resident taxes has being paid
    public String showAllBuildingResidentsTaxesPaid() {
        String result = "\nSTATUS OF ASSIGNED and PAID TAXES from \n" + companyName + "\n";
        for (Building building : areBuildingResidentsTaxesPaid.keySet()) {
            result += " BUILDING: " + building.getBuildingAddress() + "\n";
            Map<Person, Boolean> taxList = areBuildingResidentsTaxesPaid.get(building);
            for (Person person : taxList.keySet()) {
                result += "  RESIDENT: " + person.getPersonName()
                        + ", Is it payed: " + taxList.get(person) + "\n";
            }
        }
        return result;
    }

    //Method for displaying all managers and the managed buildings by them
    public String showAllEmployeeManagedBuildings() {
        String result = "";
        for (Person person : employeeAndTheirManagedBuildings.keySet()) {
            result += "\n" + person.getPersonName() + " manages\n";
            for (Building building : employeeAndTheirManagedBuildings.get(person)) {
                result += " " + building.getBuildingAddress() + " |";
            }
        }
        return result + "\n";
    }

    //Method for displaying all company employees
    public String showCompanyEmployees() {
        String result = "\nCOMPANY EMPLOYEES\n";
        for (Person employee : companyEmployeesAndPosition.keySet()) {
            result += "  " + employee.getPersonName()
                    + ", main.Position: " + companyEmployeesAndPosition.get(employee)
                    + ", Salary: " + companyEmployeesAndSalary.get(employee) + "\n";
        }
        return result;
    }

    //Method for displaying all buildings that are managed by the company
    public String showCompanyManagedBuildings() {
        String result = "\nList of company managed buildings by " + companyName;
        for (Building building : companyManagedBuildingsList) {
            result += building.toString();
        }
        return result;
    }

    @Override
    public String toString() {
        return "\n-----'" + companyName + "-----\'" +
                ", Headquarters Address='" + companyHeadquartersAddress + "\'\n" +
                //"List of managed buildings=" + companyManagedBuildings +
                showCompanyManagedBuildings() +
                "\nList of past company employees: " + pastCompanyEmployees +
                //", \nCOMPANY EMPLOYEES: \n" + companyEmployees +
                showCompanyEmployees() +
                //", employeeManagedBuildings=" +
                showAllEmployeeManagedBuildings() +
                //", buildingResidentsTaxes=" +
                showAllBuildingResidentsTaxes() +
                //", areBuildingResidentsTaxesPaid=" +
                showAllBuildingResidentsTaxesPaid();
    }
}
