package au.com.wesfarmers.csv.handler.factory;

import au.com.wesfarmers.constants.Messages;
import au.com.wesfarmers.csv.handler.AbstractCSVParser;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVParserFactoryTest {

    @Test
    public void getParserShouldThrowExceptionIfFilenameIsWrong() {
        CSVParserFactory factory = CSVParserFactory.getInstance();
        InvalidFileNameException exception = assertThrows(InvalidFileNameException.class, () -> {
            AbstractCSVParser parser = factory.getParser("barcodesX.csv");
        });
        assertTrue(exception.getMessage().contains(Messages.INVALID_FILE_MSG + "barcodesX.csv"));


    }


}