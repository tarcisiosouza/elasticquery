package de.l3s.elasticquery;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

  private static final String DEFAULT_INPUT_FILE_LOCATION = "input.properties";

  private String inputFileLocation;

  private Properties properties;

  public PropertyUtil() throws IOException {

    this(DEFAULT_INPUT_FILE_LOCATION);
  }

  public PropertyUtil(String configFileLocation) throws IOException {

    this.inputFileLocation = configFileLocation;
    this.properties = new Properties();
    init();
  }

  private void init() throws IOException {

    properties.load(new FileInputStream(this.inputFileLocation));

    for (Object key : this.properties.keySet()) {

      String override = System.getProperty((String) key.toString());

      if (override != null) {

        properties.put(key, override);
      }
    }
  }

  public int getIntProperty(String key) {

    return this.properties.contains(key) ? Integer.parseInt(properties.get(key).toString()) : null;
  }

  public String getStringProperty(String key) {

    return (String) this.properties.get(key);
  }
}
