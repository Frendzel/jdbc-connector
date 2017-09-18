package pl.lodz.sda.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.csv.CSVFormat.DEFAULT;

/**
 * Klasa odpowiedzialna za zaczytywanie pliku CSV i i zwracanie listy obiektów CSVRecord.
 */
public class FileReader {

    // namiar na naszą csvke:
    String FILE_NAME = "src/main/resources/employee.csv"; //
    // format danych łącznie ze znakiem, który odgradza od siebie kolumny (delimiter) z informacją o tym, że
    // pierwszy rekord to będzie nagłówek
    CSVFormat format = DEFAULT.withDelimiter(',').withFirstRecordAsHeader();

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
