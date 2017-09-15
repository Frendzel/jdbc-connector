package pl.lodz.sda.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

public class FileReader {

    String FILE_NAME = "src/main/resources/employee.csv"; //
    CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();

    public List<CSVRecord> readFile() {
        List<CSVRecord> records = null;
        try {
            java.io.FileReader fileReader = new java.io.FileReader(FILE_NAME);
            CSVParser parser = new CSVParser(fileReader, format);
            records = parser.getRecords();
            System.out.println("Ilość rekordów: " + records.size());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
