package au.com.wesfarmers.dao;

import java.io.Serializable;

public class CSVEntity implements Serializable {
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
