package it.mexican.plugin.converter;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBConverter {

	public static String objectToString(Object object, Class clazz) {
		StringWriter writer = new StringWriter();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			context.createMarshaller().marshal(object, writer); } 
		catch (JAXBException e) {
			throw new RuntimeException("an error occurred while marshalling"+clazz.getCanonicalName()+" object", e);
		}
		return writer.toString();
	}

	public static byte[] objectToBytes(Object object, Class clazz) {
		return objectToString(object, clazz).getBytes();
	}

	public static <T> T stringToObject(String textClazz, Class<T> clazz) {
		T returnedValue = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(textClazz);
			returnedValue = (T) unmarshaller.unmarshal(reader); } 
		catch (JAXBException e) {
			throw new RuntimeException("an error occurred while unmarshalling xml into "+clazz.getCanonicalName()+" object", e); }
		return returnedValue;
	}
	
	public static <T> T bytesToObject(byte[] bytes, Class<T> clazz) {
		return stringToObject(new String(bytes), clazz);
	}	
}