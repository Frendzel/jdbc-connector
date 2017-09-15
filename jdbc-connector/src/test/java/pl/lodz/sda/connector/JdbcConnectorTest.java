package pl.lodz.sda.connector;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.sda.FileReader;
import pl.lodz.sda.mapper.EmployeeMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static pl.lodz.sda.mapper.EmployeeMapper.toEmployeeList;

public class JdbcConnectorTest {

    JdbcConnector jdbcConnector = new JdbcConnector();
    FileReader fileReader = new FileReader();

    String createQuery = "CREATE TABLE EMPLOYEE" +
            "(id int primary key auto_increment, " +
            "birth_date date, " +
            "first_name varchar(50), " +
            "last_name varchar(50), " +
            "gender char(1), " +
            "hire_date date)";

    @Before
    public void initDb(){
        try (Connection dbConnection = JdbcConnector.getDBConnection(DB.H2)) {
            Statement statement = dbConnection.createStatement();
            statement.execute(createQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void batchInsert() throws Exception {
        List<CSVRecord> csvRecords = fileReader.readFile();
        jdbcConnector.batchInsert(toEmployeeList(csvRecords));
    }

    @Test
    public void parseDate() throws ParseException {
        String date = "1953-09-02";
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormat.forPattern("yyyy-MM-dd");
        LocalDate localDate =
                dateTimeFormatter.parseLocalDate(date);
        System.out.println(localDate.toDate());

        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date parse = df.parse(date);
        String format = df.format(parse);
        System.out.println(parse);
        System.out.println(format);
    }

}