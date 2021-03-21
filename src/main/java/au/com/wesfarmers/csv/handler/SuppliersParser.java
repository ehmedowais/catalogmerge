package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.Suppliers;
import au.com.wesfarmers.dao.SuppliersA;
import au.com.wesfarmers.dao.SuppliersB;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SuppliersParser extends AbstractCSVParser {
    enum HEADERS {ID, Name}
    @Override
    public List<CSVEntity> parse() throws IOException {

        Reader in = new FileReader(getInputPath() + "/" + getFileName());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS.class).withFirstRecordAsHeader().parse(in);
        Suppliers entity;
        List<CSVEntity> entities = new ArrayList<>();

        for (CSVRecord record : records) {
            if (getFileSource().isPresent() && getFileSource().get().equals("A")) {
                entity = new SuppliersA();
                entity.setSource("A");
            } else {
                entity = new SuppliersB();
                entity.setSource("B");
            }
            entity.setId(Integer.parseInt(record.get(HEADERS.ID)));
            entity.setName(record.get(HEADERS.Name));

            entities.add(entity);
        }
        in.close();
        return entities;
    }
}
