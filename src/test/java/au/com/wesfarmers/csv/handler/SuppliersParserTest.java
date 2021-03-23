package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.SuppliersB;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SuppliersParserTest {

   @Test
    public void parserTest() {
        SuppliersParser parser = new SuppliersParser();
        String path = "src/test/resources/catalogmerge/input";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        parser.setInputPath(absolutePath);
        parser.setFileName("suppliersB.csv");
        try {
            List<CSVEntity> entities = parser.parse();
            assertEquals(entities.size(), 2);
            assertTrue(entities.get(0) instanceof SuppliersB);
            assertEquals(((SuppliersB)entities.get(0)).getId(),1);
            assertEquals(((SuppliersB)entities.get(1)).getName(),"Thoughtsphere");
        } catch (IOException ex) {
        }
    }
}