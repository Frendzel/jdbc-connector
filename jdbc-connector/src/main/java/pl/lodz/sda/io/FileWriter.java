package pl.lodz.sda.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import pl.lodz.sda.dao.Employee;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.apache.commons.csv.CSVFormat.DEFAULT;

/**
 * Klasa odpowiedzilna za przepisywanie pobranego modelu do pliku csv.
 */
public class FileWriter {

    // nazwa pliku do którego będziemy chcieli zapisać dane
    String fileName = "employees-out.csv";
    // domyślny format csv z ustawionym delimiterem
    CSVFormat csvFileFormat = DEFAULT.withDelimiter(',');

    public void saveData(List<Employee> employees) {
        java.io.FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        try {
            // Tworzymy obiekt FileWriter od naszego pliku, aby móc zapisywać strumienie danych
            fileWriter = new java.io.FileWriter(fileName);
            // Tworzymy obiekt odpowiedzialny za dukowanie danych z określonym formatem do ustawionego pliku
            // gdzie fileWriter to nasz strumień wyjściowy
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            CSVPrinter finalCsvFilePrinter = csvFilePrinter;
            employees.forEach(employee -> {
                try {
                    // ustawiamy format dany jaki chcemy mieć w pliku csv.
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
                // pamiętamy, żeby użyć metody flush, która natychmiast pozbędzie się danych,
                // które mogły zostać w strumieniu przed zamknięciem strumienia.
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
