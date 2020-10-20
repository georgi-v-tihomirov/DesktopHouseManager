package main;

public abstract class Person implements Comparable<Person> {
    private String personName;
    private int personAge;
    private Building personAddress;
    private final int personIdentityNumber;
    private static int PID = 0;

    public Person(){
        this.personName = "N|A";
        this.personAge = 0;
        //this.personAddress = "N|A";
        PID++;
        this.personIdentityNumber = PID;

    }

    public Person(String personName, int personAge) {
        this.personName = personName;
        this.personAge = personAge;
        //this.personAddress = "N|A";
        PID++;
        this.personIdentityNumber = PID;
    }

    public Person(String personName, int personAge, Building personAddress) {
        this.personName = personName;
        this.personAge = personAge;
        this.personAddress = personAddress;
        PID++;
        this.personIdentityNumber = PID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public Building getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(Building personAddress) {
        this.personAddress = personAddress;
    }

    public int getPersonIdentityNumber() {
        return personIdentityNumber;
    }

    public int compareTo(Person person) {
        return this.getPersonName().compareTo(person.getPersonName());
    }

    @Override
    public String toString() {
        return "\n  PERSON {" +
                "Name='" + personName + "\'" +
                ", Age=" + personAge +
                ", Address='" + personAddress + "\'" +
                ", IdentityNumber=" + personIdentityNumber +
                "}\n";
    }
}
