package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.BarcodesA;
import au.com.wesfarmers.dao.CSVEntity;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BarCodeParserTest {

    @Test
    public void parse() {
        BarCodeParser parser = new BarCodeParser();
        String path = "src/test/resources/catalogmerge/input";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        parser.setInputPath(absolutePath);
        parser.setFileName("barcodesA.csv");
        try {
            List<CSVEntity> entities = parser.parse();
            assertEquals(entities.size(), 3);
            assertTrue(entities.get(0) instanceof BarcodesA);
        } catch (IOException ex) {
        }
    }
}