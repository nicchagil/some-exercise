package com.nicchagil.propertiescache;

public enum PropertiesPath {
	
	CONFIG_PROPERTIES ("/resource/config.properties"),
	LOG4J_PROPERTIES ("/log4j.properties");
	
	private String propertiesPath;

	private PropertiesPath(String propertiesPath) {
		this.propertiesPath = propertiesPath;
	}
	
	private String getPropertiesName() {
		return PropertiesCacher.getFileNameByPath(propertiesPath);
	}

	public String getPropertiesPath() {
		return propertiesPath;
	}

	public void setPropertiesPath(String propertiesPath) {
		this.propertiesPath = propertiesPath;
	}
	
}
