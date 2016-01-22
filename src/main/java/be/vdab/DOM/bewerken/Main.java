package be.vdab.DOM.bewerken;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Main {
	// 6.4 Een nieuw xml document aanmaken
	private static final Path PATH = Paths.get("/dataXML/nieuw.xml");

	public static void main(String args[]) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element element = document.createElement("nieuw");
			document.appendChild(element);
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("LS");
			LSSerializer serializer = implementation.createLSSerializer();
			serializer.getDomConfig().setParameter("format-pretty-print", true);
			LSOutput output = implementation.createLSOutput();
			try (Writer bufferedWriter = Files.newBufferedWriter(PATH)) {
				output.setCharacterStream(bufferedWriter);
				serializer.write(document, output);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 6.3 Een element verwijderen
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koersen.xml");
	 * private static final String XPATH_EXPR = "//munt[@code='%s']";
	 * 
	 * public static void main(String args[]) { DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); try { DocumentBuilder builder =
	 * factory.newDocumentBuilder(); Document document =
	 * builder.parse(PATH.toString()); System.out.print("Code:"); try (Scanner
	 * scanner = new Scanner(System.in)) { String code =
	 * scanner.next().toUpperCase(); XPathFactory xPathFactory =
	 * XPathFactory.newInstance(); XPath xPath = xPathFactory.newXPath();
	 * Element element = (Element) xPath.evaluate(String.format(XPATH_EXPR,
	 * code), document, XPathConstants.NODE); if (element == null) {
	 * System.out.println("munt niet gevonden"); } else {
	 * element.getParentNode().removeChild(element); DOMImplementationRegistry
	 * registry = DOMImplementationRegistry.newInstance(); DOMImplementationLS
	 * implementation = (DOMImplementationLS)
	 * registry.getDOMImplementation("LS"); LSSerializer serializer =
	 * implementation.createLSSerializer();
	 * serializer.getDomConfig().setParameter("format-pretty-print", true); try
	 * (Writer bufferedWriter = Files.newBufferedWriter(PATH)) { LSOutput output
	 * = implementation.createLSOutput();
	 * output.setCharacterStream(bufferedWriter); serializer.write(document,
	 * output); } } } } catch (Exception ex) { ex.printStackTrace(); } }
	 */

	// 6.2 Een element toevoegen
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koersen.xml");
	 * 
	 * public static void main(String args[]) { DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); try { DocumentBuilder builder =
	 * factory.newDocumentBuilder(); Document document =
	 * builder.parse(PATH.toString()); try (Scanner scanner = new
	 * Scanner(System.in)) { System.out.print("Code:"); String code =
	 * scanner.next().toUpperCase(); System.out.print("Koers:"); BigDecimal
	 * koers = scanner.nextBigDecimal(); Element element =
	 * document.createElement("munt"); element.setAttribute("code", code);
	 * element.setAttribute("koers", koers.toString());
	 * document.getDocumentElement().appendChild(element);
	 * DOMImplementationRegistry registry =
	 * DOMImplementationRegistry.newInstance(); DOMImplementationLS
	 * implementation = (DOMImplementationLS)
	 * registry.getDOMImplementation("LS"); LSSerializer serializer =
	 * implementation.createLSSerializer();
	 * serializer.getDomConfig().setParameter("format-pretty-print", true);
	 * LSOutput output = implementation.createLSOutput(); try (Writer
	 * bufferedWriter = Files.newBufferedWriter(PATH)) {
	 * output.setCharacterStream(bufferedWriter); serializer.write(document,
	 * output); } } } catch (Exception ex) { ex.printStackTrace(); }
	 * System.out.println("end"); }
	 */

	// 6.1 Een element bewerken
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koersen.xml");
	 * private static final String XPATH_EXPR_DATUM = "//datum/text()"; private
	 * static final String XPATH_EXPR_USD_KOERS = "//munt[@code='USD']/@koers ";
	 * 
	 * public static void main(String[] args) { DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); try { DocumentBuilder builder =
	 * factory.newDocumentBuilder(); Document document =
	 * builder.parse(PATH.toString()); XPathFactory xPathFactory =
	 * XPathFactory.newInstance(); XPath xPath = xPathFactory.newXPath(); Text
	 * text = (Text) xPath.evaluate(XPATH_EXPR_DATUM, document,
	 * XPathConstants.NODE); SimpleDateFormat format = new
	 * SimpleDateFormat("yyyy-MM-dd"); text.setData(format.format(new Date()));
	 * Attr attr = (Attr) xPath.evaluate(XPATH_EXPR_USD_KOERS, document,
	 * XPathConstants.NODE); if (attr == null) { System.out.println(
	 * "USD niet gevonden"); } else { System.out.printf("Koers:%f%n", new
	 * BigDecimal(attr.getValue())); System.out.print("Nieuwe koers:"); try
	 * (Scanner scanner = new Scanner(System.in)) { BigDecimal nieuweKoers =
	 * scanner.nextBigDecimal(); attr.setValue(nieuweKoers.toString());
	 * DOMImplementationRegistry registry =
	 * DOMImplementationRegistry.newInstance(); DOMImplementationLS
	 * implementation = (DOMImplementationLS)
	 * registry.getDOMImplementation("LS"); LSSerializer serializer =
	 * implementation.createLSSerializer(); LSOutput output =
	 * implementation.createLSOutput(); try (Writer bufferedWriter =
	 * Files.newBufferedWriter(PATH)) {
	 * output.setCharacterStream(bufferedWriter); serializer.write(document,
	 * output); } } } } catch (Exception ex) { ex.printStackTrace(); }}
	 */

}
