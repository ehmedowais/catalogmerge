package au.com.wesfarmers.csv.handler.factory;

import au.com.wesfarmers.constants.Messages;
import au.com.wesfarmers.csv.handler.AbstractCSVParser;
import au.com.wesfarmers.csv.handler.BarCodeParser;
import au.com.wesfarmers.csv.handler.CatalogParser;
import au.com.wesfarmers.csv.handler.SuppliersParser;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSVParserFactory {
    @Autowired
    private BarCodeParser barCodeParser;

    @Autowired
    private CatalogParser catalogParser;

    @Autowired
    private SuppliersParser suppliersParser;

     public AbstractCSVParser getParser(String fileName) throws InvalidFileNameException {
        if(fileName.toLowerCase().matches("catalog[a|b].csv")){
            catalogParser.setFileName(fileName);
            return catalogParser;
        }
        if(fileName.toLowerCase().matches("barcodes[a|b].csv")) {
            barCodeParser.setFileName(fileName);
            return barCodeParser;
        }
        if(fileName.toLowerCase().matches("suppliers[a|b].csv")) {
            suppliersParser.setFileName(fileName);
            return suppliersParser;
        }
        throw new InvalidFileNameException(Messages.INVALID_FILE_MSG + fileName);
    }

}
