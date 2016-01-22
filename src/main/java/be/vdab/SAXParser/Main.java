package be.vdab.SAXParser;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Main {
	// ------------------------------------------------------------------------------
	// 4.4 VALIDEREN
	// ------------------------------------------------------------------------------
	private static final Path PATH = Paths.get("/dataXML/koersenMetSchema.xml");
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	public static void main(String args[]) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		try {
			SAXParser parser = factory.newSAXParser();
			parser.setProperty(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
			KoersHandler handler = new KoersHandler();
			parser.parse(PATH.toString(), handler);
			if (handler.isValid()) {
				System.out.printf("%d munt(en)", handler.getAantalMunten());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------
	// VOORBEELD 1 & 2
	// ------------------------------------------------------------------------------
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koers.xml");
	 * 
	 * public static void main(String args[]) { SAXParserFactory factory =
	 * SAXParserFactory.newInstance(); try { SAXParser parser =
	 * factory.newSAXParser(); parser.parse(PATH.toString(), new
	 * KoersHandler()); } catch (Exception ex) { ex.printStackTrace(); } }
	 */
}
