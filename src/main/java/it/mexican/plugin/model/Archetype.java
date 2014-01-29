package it.mexican.plugin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="repository" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "groupId", "artifactId", "version",
		"repository", "description" })
public class Archetype {

	@XmlElement(required = true)
	protected String groupId;
	@XmlElement(required = true)
	protected String artifactId;
	@XmlElement(required = true)
	protected String version;
	@XmlElement(required = true)
	protected String repository;
	@XmlElement(required = true)
	protected String description;

	/**
	 * Gets the value of the groupId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Sets the value of the groupId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGroupId(String value) {
		this.groupId = value;
	}

	/**
	 * Gets the value of the artifactId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * Sets the value of the artifactId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArtifactId(String value) {
		this.artifactId = value;
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVersion(String value) {
		this.version = value;
	}

	/**
	 * Gets the value of the repository property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRepository() {
		return repository;
	}

	/**
	 * Sets the value of the repository property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRepository(String value) {
		this.repository = value;
	}

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

}