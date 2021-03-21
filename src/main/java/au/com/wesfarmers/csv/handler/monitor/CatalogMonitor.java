package au.com.wesfarmers.csv.handler.monitor;



import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Level;


import java.io.File;
import org.apache.log4j.Logger;


public class CatalogMonitor {
    private static Logger logger = Logger.getLogger("CatalogMonitor");
    public static void startFileMonitor(File folder) throws Exception {
        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000);

        FileAlterationListener fal = new FileAlterationListenerAdaptor() {

            @Override
            public void onFileCreate(File file) {logger.log(Level.ERROR, file.getAbsolutePath());
            }


        };

        observer.addListener(fal);
        monitor.addObserver(observer);
        monitor.start();
    }
}
