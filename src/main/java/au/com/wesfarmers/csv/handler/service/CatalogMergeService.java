package au.com.wesfarmers.csv.handler.service;

import au.com.wesfarmers.csv.handler.AbstractCSVParser;
import au.com.wesfarmers.csv.handler.factory.CSVParserFactory;
import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.repository.CatalogmergeDAO;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


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

    private static Logger logger = Logger.getLogger(CatalogMergeService.class);

    public void processCSVFiles(File inputDirLocation) throws InvalidFileNameException, IOException {
        if (!inputDirLocation.isFile()) {
           return;
        }


        String file = inputDirLocation.getName();
        if (file.matches("[\\w,\\s-]+\\.[csv]{3}")) {
            AbstractCSVParser parser = factory.getParser(file);
            List<CSVEntity> entities = parser.parse();
            dao.addAllSuppliers(entities);
        }


    }
}
