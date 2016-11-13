package data.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by amitkumar on 12/11/16.
 */
public class GeneratorConfiguration {

    private final String fileName = "config.properties";
    private InputStream inputStream;
    Properties properties;

    public GeneratorConfiguration() throws Exception {
        init();
    }

    private void init() throws Exception {
        try {
            properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            throw new Exception("property file '" + fileName + "' not found in the classpath");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public int getThreadpoolsize() {
        return Integer.parseInt(properties.getProperty("threadpoolsize"));
    }

    public String[] getDataFiles() {
        return properties.getProperty("datafiles").split(",");
    }

    public String getEncoding() {
        return properties.getProperty("encoding");
    }
}
