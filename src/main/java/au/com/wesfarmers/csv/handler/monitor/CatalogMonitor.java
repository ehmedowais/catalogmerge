package au.com.wesfarmers.csv.handler.monitor;


import au.com.wesfarmers.constants.Messages;
import au.com.wesfarmers.csv.handler.service.CatalogMergeService;
import au.com.wesfarmers.exception.InvalidFileNameException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import static java.text.MessageFormat.format;

@Component
public class CatalogMonitor {
    @Autowired
    private CatalogMergeService service;
    private static Logger logger = Logger.getLogger(CatalogMonitor.class);

    public void startFileMonitor(File folder) throws Exception {
        //CatalogMergeService service = new CatalogMergeService();
        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000);

        FileAlterationListener fal = new FileAlterationListenerAdaptor() {

            @Override
            public void onFileCreate(File file) {
                logger.log(Level.INFO, file.getAbsolutePath());
                try {
                    service.processCSVFiles(file);
                    logger.log(Level.INFO, format(Messages.FILE_PROCESSED_SUCCESSFULLY, file.getAbsolutePath()));
                    Path destPath = Files.createDirectories(Paths.get(file.getAbsolutePath() + "/processed"));
                    FileUtils.moveFile(file, destPath.toFile());
                } catch (InvalidFileNameException e) {
                    logger.log(Level.ERROR, e.getMessage());
                } catch (IOException e) {
                    logger.log(Level.ERROR, e.getMessage());
                }
            }

        };

        observer.addListener(fal);
        monitor.addObserver(observer);
        monitor.start();
    }
}
