package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.CatalogA;
import au.com.wesfarmers.dao.CatalogB;
import au.com.wesfarmers.dao.Catalogs;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CatalogParser extends AbstractCSVParser {

    enum HEADERS {SKU, Description}

    @Override
    public List<CSVEntity> parse() throws IOException {

        Reader in = new FileReader(getInputPath() + "/" + getFileName());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS.class).withFirstRecordAsHeader().parse(in);
        Catalogs entity;
        List<CSVEntity> entities = new ArrayList<>();

        for (CSVRecord record : records) {
            if (getFileSource().isPresent() && getFileSource().get().equals("A")) {
                entity = new CatalogA();
                entity.setSource("A");
            } else {
                entity = new CatalogB();
                entity.setSource("B");
            }
            entity.setSku(record.get(HEADERS.SKU));
            entity.setDescription(record.get(HEADERS.Description));

            entities.add(entity);
        }
        in.close();
        return entities;
    }
}
