package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.Barcodes;
import au.com.wesfarmers.dao.BarcodesA;
import au.com.wesfarmers.dao.BarcodesB;
import au.com.wesfarmers.dao.CSVEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class BarCodeParser extends AbstractCSVParser {

    enum HEADERS {SupplierID, SKU, Barcode}

    ;

    @Override
    public List<CSVEntity> parse() throws IOException {

        Reader in = new FileReader(getInputPath() + "/" + getFileName());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS.class).withFirstRecordAsHeader().parse(in);
        Barcodes entity;
        List<CSVEntity> entities = new ArrayList<>();

        for (CSVRecord record : records) {
            if (getFileSource().isPresent() && getFileSource().get().equals("A")) {
                entity = new BarcodesA();
                entity.setSource("A");
            } else {
                entity = new BarcodesB();
                entity.setSource("B");
            }
            entity.setSupplierId(Integer.parseInt(record.get(HEADERS.SupplierID)));
            entity.setSku(record.get(HEADERS.SKU));
            entity.setBarcode(record.get(HEADERS.Barcode));
            entities.add(entity);
        }
        in.close();
        return entities;
    }
}
