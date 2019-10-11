package me.stephenj.administration.model;

import java.util.Date;

public class Salary {
    private String salarySerialNo;
    private Date year;
    private double amount;
    private String employeeNo;

    public Salary() {}

    public String getSalarySerialNo() {
        return salarySerialNo;
    }

    public void setSalarySerialNo(String salarySerialNo) {
        this.salarySerialNo = salarySerialNo;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
