package ${packagePath};

public class ${className} {

	<#list fields as field>
	private final String ${field} = "${field}";
	
	</#list>
}