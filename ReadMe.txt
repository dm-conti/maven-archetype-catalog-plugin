(1) Customizzare il plugin prefix aggiungendo al settings.xml sotto .m2:
<pluginGroups>
	<pluginGroup>org.codehouse.mojo</pluginGroup>
</pluginGroups>

quando maven esegue la scansione identifica il plugin custom ed associa, per convenzione, 
il prefix dall'artifactId, secondo questo pattern:
	- ${prefix}-maven-plugin
	- maven-${prefix}-plugin

nel nostro caso:
	artifactId = first-maven-plugin => prefix = first 
	
senza tale modifica avremmo potuto comunque lanciare il plugin in questo modo:
	- mvn it.mexican.plugins:first-maven-plugin:1.0-SNAPSHOT:echo

x ulteriore approfondimenti consultare:
http://books.sonatype.com/mvnref-book/reference/writing-plugins-sect-custom-plugin.html

(2) Aggiungere il plugin ad un progetto
(3) Dalla root del progetto lanciare il plugin con uno dei seguenti comandi: 
	-	mvn first:echo		=>	visualizza un messaggio
	-	mvn first:touch		=>	crea un file touch.txt
	-	mvn first:read		=>	visualizza le informazioni delle dipendenze presenti nel pom

nota:
possiamo generare le classi JAXB dall'XML usando un tuile strumento online
http://www.xmlforasp.net/CodeBank/System_Xml_Schema/BuildSchema/BuildXMLSchema.aspx

Per testare il reperimento del catalog dall'artifactory
curl -X GET "http://localhost:8081/artifactory/libs-release-local/archetype-catalog.xml"

curl -X PUT --user admin:password  --upload-file archetype-catalog.xml "http://localhost:8081/artifactory/libs-release-local/archetype-catalog.xml"

TODO:
eseguire il plugin in automatico all'install
migliorare la validazione
gestire le eccezioni

REQUISITI
Version:  3.1.0 - Revision: 30062 

URL PayBay
http://artifactory.rmdc.internal.quigroup.it/libs-releases-local

PROBLEMA
Il model non prevede la presenza di xsi ed xmlns quindi non riesco a deserializzare.

xsi:schemaLocation="http://maven.apache.org/plugins/maven-archetype-plugin/archetype-catalog/1.0.0 
					http://maven.apache.org/xsd/archetype-catalog-1.0.0.xsd"
xmlns="http://maven.apache.org/plugins/maven-archetype-plugin/archetype-catalog/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"