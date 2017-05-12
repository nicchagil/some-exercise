package com.nicchagil.propertiescache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesCacher {
    
    private static final Logger logger = Logger.getLogger(PropertiesCacher.class);
    
    // Properties Cache
    public static Map<String, Properties> pMap = new Hashtable<String, Properties> ();
    
    /**
     * Set properties to properties cache
     * @param propertiesPath
     * @throws IOException
     */
    public static void setProperties(String propertiesPath) throws IOException {
        
        Properties properties = new Properties();
        InputStream is = null;
        
        try {
            is = PropertiesCacher.class.getResourceAsStream(propertiesPath);
            properties.load(is);
            
        } finally {
            
            if (is != null) {
                is.close();
            }
            
        }
        
        logger.info("Load to properties cache : " + propertiesPath);
        pMap.put(getFileNameByPath(propertiesPath), properties);
    }
    
    /**
     * Get properties
     * @param pName
     * @return
     */
    public static Properties getProperties(String pName) {
        return pMap.get(pName);
    }
    
    /**
     * Get properties value by properties path & key
     * @param pName
     * @param key
     * @return
     */
    public static String getPropertiesValue(String pName, String key) {
        if (pMap.get(pName) == null) {
            return "";
        }
        
        return pMap.get(pName).getProperty(key);
    }
    
    /**
     * Get file name by splitting the path
     * For example, input "/resource/config.properties", file name "config.properties" will be reurn
     * @param path
     * @return
     */
    public static String getFileNameByPath(String path) {
    	return path.substring(path.lastIndexOf("/") + 1);
    }

}
