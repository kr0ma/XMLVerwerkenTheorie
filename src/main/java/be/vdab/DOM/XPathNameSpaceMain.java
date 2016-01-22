package be.vdab.DOM;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

class XPathNameSpaceMain {
	private static final Path PATH = Paths.get("/dataXML/reis.xml");
	private static final String XPATH_EXPR_LUCHTHAVEN = "//vliegtuig:luchthaven";
	private static final String XPATH_EXPR_HOTEL = "//hotel:aantaldagen";

	public static void main(String args[]) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(PATH.toString());			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			xPath.setNamespaceContext(new MyNamespaceContext());
			String luchthaven = xPath.evaluate(XPATH_EXPR_LUCHTHAVEN, document);
			System.out.println(luchthaven.isEmpty() ? "Luchthaven niet gevonden" : luchthaven);
			String aantalDagen = xPath.evaluate(XPATH_EXPR_HOTEL, document);
			System.out.println(aantalDagen.isEmpty() ? "Hotel niet gevonden" : aantalDagen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
