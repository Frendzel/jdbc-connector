package pl.lodz.sda.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    private int id;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private char gender;
    private Date hire_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Employee() {
    }

    public Employee(int id,
                    Date birth_date,
                    String first_name,
                    String last_name,
                    char gender,
                    Date hire_date) {
        this.id = id;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (gender != employee.gender) return false;
        if (birth_date != null ? !birth_date.equals(employee.birth_date) : employee.birth_date != null) return false;
        if (!first_name.equals(employee.first_name)) return false;
        if (!last_name.equals(employee.last_name)) return false;
        return hire_date != null ? hire_date.equals(employee.hire_date) : employee.hire_date == null;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (birth_date != null ? birth_date.hashCode() : 0);
        result = 31 * result + first_name.hashCode();
        result = 31 * result + last_name.hashCode();
        result = 31 * result + (int) gender;
        result = 31 * result + (hire_date != null ? hire_date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", birth_date=" + birth_date +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender=" + gender +
                ", hire_date=" + hire_date +
                '}';
    }

    public String toStatementInsertQuery() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "INSERT INTO " +
                "EMPLOYEE (birth_date, first_name, last_name, " +
                "gender, hire_date) VALUES (" +
                "'" + df.format(getBirth_date()) + "', '" +
                getFirst_name() + "', '" +
                getLast_name() + "', '" + getGender() + "', '"
                + df.format(getHire_date()) + "')";
    }

    public static String selectQuery() {
        return "SELECT * FROM EMPLOYEE";
    }

    public String toPreparedStatementInsertQuery() {
        return "INSERT INTO " +
                "EMPLOYEE (birth_date, first_name, last_name, " +
                "gender, hire_date) VALUES(?, ?, ?, ?, ?)";
    }
}
