package it.mexican.plugin;

import it.mexican.plugin.converter.JAXBConverter;
import it.mexican.plugin.model.Archetype;
import it.mexican.plugin.model.ArchetypeCatalog;
import it.mexican.plugin.util.ArchetypeCatalogFileUtil;
import it.mexican.plugin.util.HttpTemplate;
import it.mexican.plugin.validator.PluginValidator;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @goal deploy-catalog
 * @phase install
 */
public class ArchetypeCatalogUpdatorMojo extends AbstractMojo {
	private final String ARCHETYPE_CATALOG = "/archetype-catalog.xml";

	/**
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * @parameter
	 * @required
	 */
	private String artifactoryUrl;

	/**
	 * @parameter
	 * @required
	 */
	private String username;

	/**
	 * @parameter
	 * @required
	 */
	private String password;

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("*** Maven archetype catalog plugin is running on project : ".concat(project.getName()));
		getLog().info("validating pre-conditions ...");
		getLog().info("pre-conditions are :");
		
		getLog().info("artifactory url ["+artifactoryUrl+"]");
		getLog().info("username ["+username+"]");
		getLog().info("password ["+password+"]");
		
		//[1]
		//validating pre-conditions
		PluginValidator.validatePackage(project.getPackaging());
		
		// [1]
		// create connections
		HttpTemplate httpTemplate = new HttpTemplate("localhost", 433, "admin","password");

		// [2]
		// get the archetype-catalog from Artifactory
		HttpGet get = httpTemplate.prepareGetRequest(artifactoryUrl.concat(ARCHETYPE_CATALOG));
		get.addHeader(BasicScheme.authenticate(httpTemplate.getCredentials(),"US-ASCII",false));
		HttpResponse getResponse = httpTemplate.execute(get);
		
		//[4]
		//modify archetype catalog model
		String archetypeCatalogTxt = httpTemplate.entityToString(getResponse.getEntity());
		
		// to unmarshal object
		ArchetypeCatalog archetypeCatalog = JAXBConverter.stringToObject(archetypeCatalogTxt, ArchetypeCatalog.class);
		archetypeCatalog.getArchetypes().getArchetype().add(buildArchetype());
		
		File temp = ArchetypeCatalogFileUtil.getTempCatalogFile(archetypeCatalog);
		

		// [5]
		// read a file
		InputStreamEntity entity = ArchetypeCatalogFileUtil.fileToEntity(temp);
		
		// [6]
		// deploy archetype-catalog.xml to artifactory
		HttpPut put = httpTemplate.preparePutRequest(artifactoryUrl.concat(ARCHETYPE_CATALOG), entity, "application/json");
		put.addHeader("Accept", "application/json");
		put.addHeader(BasicScheme.authenticate(httpTemplate.getCredentials(),"US-ASCII",false));
				
		HttpResponse putResponse = httpTemplate.execute(put);
		HttpEntity putResultEntity = putResponse.getEntity();
		String textResult = httpTemplate.entityToString(putResultEntity);
		
		System.out.println(textResult);
	}

	private Archetype buildArchetype() {
		Archetype archetype = new Archetype();
		archetype.setGroupId(project.getGroupId());
		getLog().info("GroupId is : " + project.getGroupId());

		archetype.setArtifactId(project.getArtifactId());
		getLog().info("GroupId is : " + project.getArtifact());

		archetype.setVersion(project.getVersion());
		getLog().info("GroupId is : " + project.getVersion());
		return archetype;
	}
}