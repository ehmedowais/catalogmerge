package au.com.wesfarmers.csv.handler.service;

import au.com.wesfarmers.constants.Messages;
import au.com.wesfarmers.csv.handler.AbstractCSVParser;
import au.com.wesfarmers.csv.handler.factory.CSVParserFactory;
import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.Catalogs;
import au.com.wesfarmers.dao.repository.CatalogmergeDAO;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.text.MessageFormat.format;


@Component
public class CatalogMergeService {
    /**
     * This file handles following operations
     * 1.   Check if there are six files in input directory
     * 2.   if yes then create appropriate parser and get a list of entities
     * 3.   Save the entities to database
     */
    @Autowired
    private CatalogmergeDAO dao;

    @Autowired
    private CSVParserFactory factory;

    private static final Logger LOGGER = Logger.getLogger(CatalogMergeService.class);

    public void processCSVFiles(File file) throws InvalidFileNameException, IOException {
        if (!file.isFile()) {
           return;
        }


        String fileName = file.getName();
        //matches word and ,- with spaces and .csv
        if (fileName.matches("[\\w,\\s-]+\\.[csv]{3}")) {
            AbstractCSVParser parser = factory.getParser(fileName);
            List<CSVEntity> entities = parser.parse();
            dao.addAll(entities);
            LOGGER.info(format(Messages.FILE_PROCESSED_SUCCESSFULLY,fileName));
        }


    }

    public List<Catalogs> generateResults() {
        List<Catalogs> catalogs = dao.getFinalResult();
        return catalogs;
    }
}
