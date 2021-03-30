package au.com.wesfarmers.csv.handler.monitor;

import au.com.wesfarmers.constants.Messages;
import au.com.wesfarmers.csv.handler.service.CatalogMergeService;
import au.com.wesfarmers.dao.Catalogs;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static java.text.MessageFormat.format;

@Configuration
@EnableScheduling
public class ScheduledMonitor {
    private static final Logger LOGGER = Logger.getLogger(ScheduledMonitor.class);
    private String[] HEADERS = {"SKU", "Description", "Source"};
    @Autowired
    private CatalogMergeService service;

    @Value("${catalogmerge.input.path}")
    private String inputDir;

    @Value("${catalogmerge.output.path}")
    private String outputDir;

    @Scheduled(fixedDelay = 5000)
    public void monitorInputFolder() {
        File folder = Paths.get(inputDir).toFile();
        File[] files = folder.listFiles();
        if (files.length < 6) {
            LOGGER.info(Messages.INCOMPLETE_BATCH);
            return;
        }
        for (File file : files) {
            processFile(file);
        }
        generateOutputFile();
    }

    private void processFile(File file) {
        LOGGER.info(file.getAbsolutePath());
        try {
            service.processCSVFiles(file);
            LOGGER.info(format(Messages.FILE_PROCESSED_SUCCESSFULLY, file.getAbsolutePath()));
            FileUtils.forceDelete(file);

        } catch (InvalidFileNameException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void generateOutputFile() {
        String fileName = "result_output.csv";
        List<Catalogs> catalogs = service.generateResults();
        catalogs.forEach(LOGGER::info);
        try {
            FileWriter writer = new FileWriter(outputDir + "/" + fileName);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS));
            catalogs.forEach(catalog -> {
                try {
                    csvPrinter.printRecord(catalog.getSku(), catalog.getDescription(), catalog.getSource());
                } catch (IOException ex) {
                    LOGGER.error(Messages.FILE_GENERATION_FAILED, ex);
                }
            });

            csvPrinter.flush();
            writer.close();
        } catch (
                IOException ex) {
            LOGGER.error(Messages.FILE_GENERATION_FAILED, ex);
        }
        LOGGER.info(Messages.File_GENERATED_SUCCESSFULLY);
    }

}
