package it.mexican.plugins;

import it.mexican.plugin.converter.JAXBConverter;
import it.mexican.plugin.model.ArchetypeCatalog;
import it.mexican.plugin.util.HttpTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.junit.Test;

public class HttpTemplateTest {
	private String uri = "http://localhost:8081/artifactory/libs-release-local/archetype-catalog.xml";


	@Test
	public void executeGET() {
		// [1]
		// create connections
		HttpTemplate httpTemplate = new HttpTemplate("localhost", 433, "admin","password");

		// [2]
		// get the archetype-catalog from Artifactory
		HttpGet get = httpTemplate.prepareGetRequest(uri);
//		get.addHeader(BasicScheme.authenticate(httpTemplate.getCredentials(),"US-ASCII",false));
		HttpResponse response = httpTemplate.execute(get);

		String archetypeCatalogTxt = httpTemplate.entityToString(response.getEntity());
		System.out.println(archetypeCatalogTxt);
		
		ArchetypeCatalog archetypeCatalog = JAXBConverter.stringToObject(archetypeCatalogTxt, ArchetypeCatalog.class);

		// [3]
		// Validation
		Assert.assertNotNull(archetypeCatalog);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
	}

	@Test
	public void executePUT() {
		// [1]
		// read a file
		File file = new File("src/test/resources/archetype-catalog.xml");
		InputStreamEntity entity = null;
		try {
			entity = new InputStreamEntity(new FileInputStream(file), file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// [2]
		// create connections
		HttpTemplate httpTemplate = new HttpTemplate("localhost", 433, "admin","password");
		
		// [3]
		// get the archetype-catalog from Artifactory
		HttpPut put = httpTemplate.preparePutRequest(uri, entity, "application/json");
		put.addHeader("Accept", "application/json");
		put.addHeader(BasicScheme.authenticate(httpTemplate.getCredentials(),"US-ASCII",false));
		
		HttpResponse response = httpTemplate.execute(put);
		
		// [4]
		// Validation
		Assert.assertNotNull(response);
		printEntityContent(response);
		Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());
	}

	private void printEntityContent(HttpResponse response) {
		try {
			System.out.println(IOUtils.readLines(response.getEntity()
					.getContent()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}