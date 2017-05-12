package com.nicchagil.propertiescache;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class PropertiesListener implements ServletContextListener {
    
    private final Logger logger = Logger.getLogger(PropertiesListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
        	
        	/* Load  the properties files */
        	for (PropertiesPath pp : PropertiesPath.values()) {
        		PropertiesCacher.setProperties(pp.getPropertiesPath());
            }
        	
        } catch (IOException e) {
            // TODO Auto-generated catch block
            
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
    
}