package pl.lodz.sda.dao;

import org.junit.Test;

import java.util.Date;

public class EmployeeTest {

    @Test
    public void hashCodeCompare() {
        Employee employee =
                new Employee(1,new Date(),
                        "Janusz",
                        "Nowak",
                        "M".toCharArray()[0],
                        new Date());
        Employee employee2 =
                new Employee(1,new Date(),
                        "Janusz",
                        "Nowak",
                        "M".toCharArray()[0],
                        new Date());
        System.out.println(employee.hashCode());
        System.out.println(employee2.hashCode());
    }

}