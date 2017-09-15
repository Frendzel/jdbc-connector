package pl.lodz.sda;

import org.junit.Test;
import pl.lodz.sda.io.FileReader;

public class FileReaderTest {

    @Test
    public void testFileReader() {

        FileReader fileReader = new FileReader();
        fileReader.readFile();
    }

}