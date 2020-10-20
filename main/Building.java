package main;

import java.util.Map;
import java.util.TreeMap;

public class Building implements Comparable<Building>{
    private String buildingAddress;
    private int buildingFloorsCount;
    private int buildingApartmentsCount;
    private int freeBuildingApartmentsCount;
    private double buildingBuiltUpArea;
    private boolean availableElevator;
    //private List<main.Person> buildingResidents;
    private Map<Person, Map<Integer, Integer>> buildingResidents;//floors, apartments

    /*public main.Building() {
        buildingResidents = new ArrayList<>();
    }*/

    public Building() {
        buildingResidents = new TreeMap<>();
    }

    public Building(String buildingAddress, int buildingFloorsCount, int buildingApartmentsCount, double buildingBuiltUpArea, boolean availableElevator) {
        this.buildingAddress = buildingAddress;
        this.buildingFloorsCount = buildingFloorsCount;
        this.freeBuildingApartmentsCount = buildingApartmentsCount;
        this.buildingApartmentsCount = buildingApartmentsCount;
        this.buildingBuiltUpArea = buildingBuiltUpArea;
        //buildingResidents = new ArrayList<>();
        buildingResidents = new TreeMap<>();
        this.availableElevator = availableElevator;
    }

    public Building(String buildingAddress, int buildingFloorsCount, int buildingApartmentsCount, double buildingBuiltUpArea, Map<Person, Map<Integer, Integer>> buildingResidents, boolean availableElevator) { //List<main.Person> buildingResidents) {
        this.buildingAddress = buildingAddress;
        this.buildingFloorsCount = buildingFloorsCount;
        this.freeBuildingApartmentsCount = buildingApartmentsCount;
        this.buildingApartmentsCount = buildingApartmentsCount;
        this.buildingBuiltUpArea = buildingBuiltUpArea;
        //this.buildingResidents = buildingResidents;
        this.buildingResidents = buildingResidents;
        this.availableElevator = availableElevator;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public int getBuildingFloorsCount() {
        return buildingFloorsCount;
    }

    public void setBuildingFloorsCount(int buildingFloorsCount) {
        this.buildingFloorsCount = buildingFloorsCount;
    }

    public int getBuildingApartmentsCount() {
        return buildingApartmentsCount;
    }

    public void setBuildingApartmentsCount(int buildingApartmentsCount) {
        this.buildingApartmentsCount = buildingApartmentsCount;
    }

    public int getFreeBuildingApartmentsCount() {
        return freeBuildingApartmentsCount;
    }

    public void setFreeBuildingApartmentsCount(int freeBuildingApartmentsCount) {
        this.freeBuildingApartmentsCount = freeBuildingApartmentsCount;
    }

    /*public void setFreeBuildingApartmentsCount(int n) {
        if(freeBuildingApartmentsCount > 0) {
            this.freeBuildingApartmentsCount -= 1;
        }
    }*/

    public double getBuildingBuiltUpArea() {
        return buildingBuiltUpArea;
    }

    public void setBuildingBuiltUpArea(double buildingBuiltUpArea) {
        this.buildingBuiltUpArea = buildingBuiltUpArea;
    }

    public Map<Person, Map<Integer, Integer>> getBuildingResidents() {
        return buildingResidents;
    }

    public void setBuildingResidents(Map<Person, Map<Integer, Integer>> buildingResidents) {
        this.buildingResidents = buildingResidents;
    }

    /*public List<main.Person> getBuildingResidents() {
        return buildingResidents;
    }

    private void setBuildingResidents(List<main.Person> buildingResidents) {
        this.buildingResidents = buildingResidents;
    }*/

    public boolean isAvailableElevator() {
        return availableElevator;
    }

    public void setAvailableElevator(boolean availableElevator) {
        this.availableElevator = availableElevator;
    }

    public String showBuildingResidents(){
        String result= "  RESIDENT LIST OF --" + buildingAddress + "--\n";
        for (Person person : buildingResidents.keySet()) {
            Map<Integer, Integer> livingDetails = buildingResidents.get(person);
            for (int floor : livingDetails.keySet()) {
                result += "   Name: " + person.getPersonName() + ", floor: " + floor + ", apartment: " + livingDetails.get(floor) + "\n";
            }
        }
        return result;
    }

    @Override
    public int compareTo(Building building) {
        if(this.getBuildingFloorsCount() > building.getBuildingFloorsCount()){
            return 1;
        }
        if(this.getBuildingFloorsCount() == building.getBuildingFloorsCount()){
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\n  BUILDING {" +
                "Address'" + buildingAddress + "\'" +
                ", Floors: " + buildingFloorsCount +
                ", Apartments: " + buildingApartmentsCount +
                ", BuiltUpArea: " + buildingBuiltUpArea + "}\n" +
                showBuildingResidents();
    }
}
