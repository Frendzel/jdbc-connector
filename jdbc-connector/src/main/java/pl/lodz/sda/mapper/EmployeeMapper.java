package pl.lodz.sda.mapper;

import org.apache.commons.csv.CSVRecord;
import pl.lodz.sda.dao.Employee;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeeMapper {

    List<Employee> toEmployeeList(List<CSVRecord> records) {
        return records.stream().map(
                this::toEmployee).collect(toList());

    }

    private Employee toEmployee(CSVRecord record) {
        return new Employee(Integer.parseInt(record.get(0)),
                new Date(record.get(1)),
                record.get(2),
                record.get(3),
                record.get(4).toCharArray()[0],
                new Date(record.get(5)));
    }

}
