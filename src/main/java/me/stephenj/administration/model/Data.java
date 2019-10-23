package me.stephenj.administration.model;

import java.util.List;

public class Data {
    private List<Employee> employees;
    private int count;

    public Data() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
