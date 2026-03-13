package institutions;   // Employee working at a financial institution

public class Employee {

    private String employeeName;
    private String position;

    public Employee(String employeeName, String position) {
        this.employeeName = employeeName;
        this.position = position;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}