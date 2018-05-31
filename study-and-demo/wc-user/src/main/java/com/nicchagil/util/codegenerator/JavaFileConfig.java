package com.nicchagil.util.codegenerator;

public class JavaFileConfig {

	private String packagePath = null;
	private String className = null;
	private String fileSuffix = null;

	public JavaFileConfig(String packagePath, String className, String fileSuffix) {
		super();
		this.packagePath = packagePath;
		this.className = className;
		this.fileSuffix = fileSuffix;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

}
