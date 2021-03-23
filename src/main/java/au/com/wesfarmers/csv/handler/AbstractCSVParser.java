package au.com.wesfarmers.csv.handler;

import au.com.wesfarmers.dao.CSVEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCSVParser {
    @Value("${catalogmerge.input.path}")
    private String inputPath;

    private String fileName;

    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Optional<String> getFileSource() {
        String fileSource = null;
        if(StringUtils.isNotBlank(fileName)) {
            fileSource = Character.toString(fileName.substring(fileName.length()-5).charAt(0));
        }
        return Optional.of(fileSource);
    }
    public String getInputPath() {
        return inputPath;
    }
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }
    public abstract List<CSVEntity> parse() throws IOException;
}
