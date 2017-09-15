package pl.lodz.sda.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import pl.lodz.sda.dao.Employee;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileWriter {

    String fileName = "employees-out.csv";
    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withDelimiter(',');

    public void saveData(List<Employee> employees) {
        java.io.FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        try {
            fileWriter = new java.io.FileWriter(fileName);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            CSVPrinter finalCsvFilePrinter = csvFilePrinter;
            employees.forEach(employee -> {
                try {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    finalCsvFilePrinter.printRecord(
                            employee.getId(),
                            df.format(employee.getBirth_date()),
                            employee.getFirst_name(),
                            employee.getLast_name(),
                            employee.getGender(),
                            df.format(employee.getHire_date())
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
