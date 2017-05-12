package com.nicchagil.propertiescache;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DebugPropertiesCacheServlet extends HttpServlet {
	
	private final Logger logger = Logger.getLogger(DebugPropertiesCacheServlet.class);
    private static final long serialVersionUID = 1L;
       
    public DebugPropertiesCacheServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pfile = request.getParameter("pfile");
        String key = request.getParameter("key");

        if (pfile == null || key == null) {
        	logger.debug(PropertiesCacher.pMap);
        } 
        
        if (pfile != null && key == null) {
        	logger.debug(PropertiesCacher.getProperties(pfile));
        }
        
        if (pfile != null && key != null) {
        	logger.debug(PropertiesCacher.getPropertiesValue(pfile, key));
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}