package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.CatalogB;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CatalogParserTest {

    @Test
    public void parserTest() {
        CatalogParser parser = new CatalogParser();
        String path = "src/test/resources/catalogmerge/input";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        parser.setInputPath(absolutePath);
        parser.setFileName("catalogB.csv");
        try {
            List<CSVEntity> entities = parser.parse();
            assertEquals(entities.size(), 3);
            assertTrue(entities.get(0) instanceof CatalogB);
            assertEquals(((CatalogB)entities.get(0)).getSku(),"647-vyk-317");
            assertEquals(((CatalogB)entities.get(1)).getDescription(),"Bread - Raisin");
        } catch (IOException ex) {
        }
    }
}