package be.vdab.DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class Main {
	// ------------------------------------------------------------------------------
	// 5.6 XPATH
	// ------------------------------------------------------------------------------
	private static final String URL = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	// 1 ELEMENT
	// private static final String XPATH_EXPR = "//Cube[@currency='USD']";
	// 1 WAARDE
	// private static final String XPATH_EXPR = "//Cube[@currency='USD']/@rate";
	// VERZAMELING ELEMENTEN
	private static final String XPATH_EXPR = "//Cube[@rate<1]";

	public static void main(String args[]) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(URL);			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			// 1 ELEMENT
			/*
			 * Element element = (Element) xPath.evaluate(XPATH_EXPR, document,
			 * XPathConstants.NODE); if (element == null) { System.out.println(
			 * "Koers USD niet gevonden"); } else {
			 * System.out.println(element.getAttribute("rate")); }
			 */

			// 1 WAARDE
			/*
			 * // standaard versie // String rate = (String)
			 * xPath.evaluate(XPATH_EXPR, document, // XPathConstants.STRING);
			 * // overloaded versie : String rate = xPath.evaluate(XPATH_EXPR,
			 * document);
			 * 
			 * if (rate.isEmpty()) { System.out.println(
			 * "Koers USD niet gevonden"); } else { System.out.println(rate); }
			 */

			// VERZAMELING ELEMENTEN
			NodeList nodeList = (NodeList) xPath.evaluate(XPATH_EXPR, document, XPathConstants.NODESET);
			for (int index = 0; index != nodeList.getLength(); index++) {
				Element element = (Element) nodeList.item(index);				
				// System.out.printf("%s %s%n",
				// element.getAttribute("currency"),
				// element.getAttribute("rate"));
				// XPATH ON A NODE
				System.out.printf("%s %s%n", xPath.evaluate("@currency", element), xPath.evaluate("@rate", element));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------
	// 5.5 VALIDEREN
	// ------------------------------------------------------------------------------
	/*
	 * private static final Path PATH =
	 * Paths.get("/dataXML/koersenMetSchema.xml");; private static final String
	 * JAXP_SCHEMA_LANGUAGE =
	 * "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	 * 
	 * public static void main(String args[]) { DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); try { factory.setValidating(true);
	 * factory.setNamespaceAware(true);
	 * factory.setAttribute(JAXP_SCHEMA_LANGUAGE,
	 * XMLConstants.W3C_XML_SCHEMA_NS_URI); DocumentBuilder builder =
	 * factory.newDocumentBuilder(); builder.setErrorHandler(new
	 * KoersenErrorHandler()); builder.parse(PATH.toString()); } catch
	 * (Exception ex) { ex.printStackTrace(); } }
	 */
	// ------------------------------------------------------------------------------
	// VOORBEELD 2
	// ------------------------------------------------------------------------------
	/*
	 * private static final String URL =
	 * "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	 * 
	 * public static void main(String args[]) { DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); try { DocumentBuilder builder =
	 * factory.newDocumentBuilder(); Document document = builder.parse(URL);
	 * NodeList nodeList = document.getElementsByTagName("Cube"); for (int index
	 * = 0; index != nodeList.getLength(); index++) { Element element =
	 * (Element) nodeList.item(index); String currency =
	 * element.getAttribute("currency"); if (!currency.isEmpty()) {
	 * System.out.printf("%s %s%n", currency, element.getAttribute("rate")); } }
	 * } catch (Exception ex) { ex.printStackTrace(); } }
	 */
	// ------------------------------------------------------------------------------
	// VOORBEELD 1
	// ------------------------------------------------------------------------------
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koers.xml");;
	 * 
	 * public static void main(String args[]) { DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); try { DocumentBuilder builder =
	 * factory.newDocumentBuilder(); Document document =
	 * builder.parse(PATH.toString()); Element documentElement =
	 * document.getDocumentElement(); verwerkNode(documentElement); } catch
	 * (Exception ex) { ex.printStackTrace(); } }
	 * 
	 * private static void verwerkNode(Node node) { switch (node.getNodeType())
	 * { case Node.ELEMENT_NODE: System.out.println(node.getNodeName());
	 * NamedNodeMap attributen = node.getAttributes(); for (int index = 0; index
	 * != attributen.getLength(); index++) { Node attribuut =
	 * attributen.item(index); System.out.printf("\t%s %s%n",
	 * attribuut.getNodeName(), attribuut.getNodeValue()); } NodeList childNodes
	 * = node.getChildNodes(); for (int index = 0; index !=
	 * childNodes.getLength(); index++) { verwerkNode(childNodes.item(index)); }
	 * break; case Node.TEXT_NODE: System.out.println(node.getNodeValue());
	 * break; } }
	 */
}
