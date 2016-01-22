package be.vdab.XMLStreamWriter;

import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

class Main {

	private static final Path XML_PATH = Paths.get("/dataXML/koersen.xml");
	private static final Path SCHEMA_PATH = Paths.get("koersen.xsd");
	private static final String DEFAULT_NAMESPACE = "http://www.vdab.be/koersen";

	public static void main(String[] args) {

		// ------------------------------------------------------------------------------
		// VOORBEELD 2 -> with namespaces and user input
		// ------------------------------------------------------------------------------
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try (Scanner scanner = new Scanner(System.in); Writer bufferedWriter = Files.newBufferedWriter(XML_PATH)) {
			XMLStreamWriter writer = null;
			try {
				factory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
				writer = factory.createXMLStreamWriter(bufferedWriter);
				writer.setDefaultNamespace(DEFAULT_NAMESPACE);
				writer.writeStartDocument();
				writer.writeStartElement("koersen");
				writer.writeNamespace("xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
				writer.writeAttribute(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "schemaLocation",
						DEFAULT_NAMESPACE + ' ' + SCHEMA_PATH.toString());
				writer.writeStartElement("datum");
				SimpleDateFormat formaat = new SimpleDateFormat("yyyy-MM-dd");
				writer.writeCharacters(formaat.format(new Date()));
				writer.writeEndElement();
				System.out.print("code:");
				for (String code; !"stop".equalsIgnoreCase(code = scanner.next());) {
					writer.writeEmptyElement("munt");
					writer.writeAttribute("code", code.toUpperCase());
					System.out.print("koers:");
					BigDecimal koers = scanner.nextBigDecimal();
					writer.writeAttribute("koers", koers.toString());
					System.out.print("code:");
				}
				writer.writeEndElement();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (XMLStreamException ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------
	// VOORBEELD 1
	// ------------------------------------------------------------------------------
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koers.xml");
	 * 
	 * public static void main(String[] args) { XMLOutputFactory factory =
	 * XMLOutputFactory.newInstance(); try (Writer bufferedWriter =
	 * Files.newBufferedWriter(PATH)) { XMLStreamWriter writer = null; try {
	 * writer = factory.createXMLStreamWriter(bufferedWriter);
	 * writer.writeStartDocument(); writer.writeStartElement("koersen");
	 * writer.writeStartElement("datum"); SimpleDateFormat format = new
	 * SimpleDateFormat("yyyy-MM-dd"); writer.writeCharacters(format.format(new
	 * Date())); writer.writeEndElement(); writer.writeEmptyElement("munt");
	 * writer.writeAttribute("code", "USD"); writer.writeAttribute("koers",
	 * BigDecimal.valueOf(1.1).toString()); writer.writeEndElement(); } finally
	 * { if (writer != null) { try { writer.close(); } catch (XMLStreamException
	 * ex) { ex.printStackTrace(); } } } } catch (Exception ex) {
	 * ex.printStackTrace(); } }
	 */
}
