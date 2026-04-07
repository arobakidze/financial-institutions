package com.financial.financialinstitutions.institutions;

import com.financial.financialinstitutions.base.Person;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFullName(), employee.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName());
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