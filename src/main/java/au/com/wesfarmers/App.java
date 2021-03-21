package au.com.wesfarmers;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import static au.com.wesfarmers.csv.handler.monitor.CatalogMonitor.startFileMonitor;
import java.io.File;

@SpringBootApplication
@Component
public class App {
    @Value("${catalogmerge.input.path}")
    private String inputFolder;

    private static String STATIC_FOLDER_PATH;
    public static void main(String[] args) {


        SpringApplication.run(App.class, args);
        startMonitor();
    }

    public static void startMonitor() {

        try {
            File folder = FileUtils.getFile(STATIC_FOLDER_PATH);
            startFileMonitor(folder);
        }catch(Exception ex) {
            Logger.getLogger(App.class).error(ex.getMessage());
        }
    }
    @Value("${catalogmerge.input.path}")
    public void setStaticFolderPath(String inputFolder) {
        App.STATIC_FOLDER_PATH = inputFolder;
        System.out.println("************" +App.STATIC_FOLDER_PATH);
    }
}
