package institutions;

import base.Person;

public class Employee extends Person {

    private String position;

    public Employee(String employeeName, String position) {
        super(employeeName, employeeName + "@company.com");
        this.position = position;
    }

    @Override
    public String getRole() {
        return "Employee";
    }

    public String getEmployeeName() {
        return getFullName();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}