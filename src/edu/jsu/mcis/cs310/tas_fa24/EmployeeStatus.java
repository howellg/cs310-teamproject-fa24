package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalDateTime;

public class EmployeeStatus {
    private final Employee employee;
    private final boolean isIn;
    private final LocalDateTime arrivalTime;
    private final String firstName;
    private final String lastName;
    private final String badgeId;
    private final String employeeType;
    private final int employeeTypeOrder;
    private final String shiftDescription;


    public EmployeeStatus(Employee employee, boolean isIn, LocalDateTime arrivalTime) {
        this.employee = employee;
        this.isIn = isIn;
        this.arrivalTime = arrivalTime;
        this.firstName = employee.getFirstname();
        this.lastName = employee.getLastname();
        this.badgeId = employee.getBadge().getId();
        this.shiftDescription = employee.getShift().getDescription();

        if (employee.getEmployeetype() == EmployeeType.FULL_TIME) {
            this.employeeType = "Full-Time Employee";
            this.employeeTypeOrder = 1;
        } else {
            this.employeeType = "Temporary Employee";
            this.employeeTypeOrder = 2;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getShiftDescription() {
        return shiftDescription;
    }

    public boolean isIn() {
        return isIn;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getEmployeeTypeOrder() {
        return employeeTypeOrder;
    }
}