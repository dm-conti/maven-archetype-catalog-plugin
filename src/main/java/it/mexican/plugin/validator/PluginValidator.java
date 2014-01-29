package it.mexican.plugin.validator;



public class PluginValidator {
	private static final String PACKAGING_MAVEN_PLUGIN = "maven-plugin";
	
	public static void validatePackage(String packaging) {
		assert(PACKAGING_MAVEN_PLUGIN.equalsIgnoreCase(packaging)) : "the project is not an archetype project. To use the plugin have no sense";
	}
}