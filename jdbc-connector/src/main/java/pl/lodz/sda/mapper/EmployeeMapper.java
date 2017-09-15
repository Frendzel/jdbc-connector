package pl.lodz.sda.mapper;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import pl.lodz.sda.dao.Employee;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeeMapper {

    public static List<Employee> toEmployeeList(List<CSVRecord> records) {
        return records.stream().map(
                EmployeeMapper::toEmployee).collect(toList());

    }

    private static Employee toEmployee(CSVRecord record) {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormat.forPattern("yyyy-MM-dd");
        return new Employee(Integer.parseInt(record.get(0)),
                dateTimeFormatter.parseDateTime(record.get(1)).toDate(),
                record.get(2),
                record.get(3),
                record.get(4).toCharArray()[0],
                dateTimeFormatter.parseDateTime(record.get(5)).toDate());
    }

}
