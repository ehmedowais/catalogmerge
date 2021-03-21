package au.com.wesfarmers.csv.handler.factory;

import au.com.wesfarmers.constants.Messages;
import au.com.wesfarmers.csv.handler.AbstractCSVParser;
import au.com.wesfarmers.csv.handler.BarCodeParser;
import au.com.wesfarmers.csv.handler.CatalogParser;
import au.com.wesfarmers.csv.handler.SuppliersParser;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;

public class CSVParserFactory {
    @Autowired
    BarCodeParser barCodeParser;

    @Autowired
    CatalogParser catalogParser;

    @Autowired
    SuppliersParser suppliersParser;

    private CSVParserFactory() {}
    private static CSVParserFactory instance = new CSVParserFactory();

    public AbstractCSVParser getParser(String fileName) throws InvalidFileNameException {
        if(fileName.toLowerCase().matches("catalog[a|b].csv")){
            return catalogParser;
        }
        if(fileName.toLowerCase().matches("barcodes[a|b].csv")) {
            barCodeParser.setFileName(fileName);
            return barCodeParser;
        }
        if(fileName.toLowerCase().matches("suppliers[a|b].csv")) {
            return suppliersParser;
        }
        throw new InvalidFileNameException(Messages.INVALID_FILE_MSG + fileName);
    }
    public static CSVParserFactory getInstance() {
        return instance;
    }
}
