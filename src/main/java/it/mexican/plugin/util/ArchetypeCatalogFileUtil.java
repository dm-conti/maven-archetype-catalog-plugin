package it.mexican.plugin.util;

import it.mexican.plugin.converter.JAXBConverter;
import it.mexican.plugin.model.ArchetypeCatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.entity.InputStreamEntity;

public class ArchetypeCatalogFileUtil {
	
	public static File getTempCatalogFile(String filename, String exstention, ArchetypeCatalog archetypeCatalog){
		File temp = null;
		try {
			temp = File.createTempFile(filename, ".".concat(exstention));
			FileUtils.writeByteArrayToFile(temp, JAXBConverter.objectToBytes(archetypeCatalog, ArchetypeCatalog.class)); } 
		catch (IOException ioe) {
			throw new RuntimeException("an error occurred while creating archetype-catalog.xml temp file", ioe); }
		return temp;
	}
	
	public static File getTempCatalogFile(ArchetypeCatalog archetypeCatalog){
		return getTempCatalogFile("archetype-catalog", "xml", archetypeCatalog);
	}
	
	public static InputStreamEntity fileToEntity(File file){
		InputStreamEntity entity = null;
		try {
			entity = new InputStreamEntity(new FileInputStream(file), file.length()); } 
		catch (FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe); }
		return entity;
	}
}