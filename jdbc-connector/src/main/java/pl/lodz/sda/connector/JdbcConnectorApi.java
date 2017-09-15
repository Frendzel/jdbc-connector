package pl.lodz.sda.connector;

import pl.lodz.sda.dao.Employee;

import java.util.List;

public interface JdbcConnectorApi {

    public void batchInsert(
            List<Employee> employees);

}
