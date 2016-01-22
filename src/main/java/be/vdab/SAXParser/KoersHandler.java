package be.vdab.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class KoersHandler extends DefaultHandler {
	// ------------------------------------------------------------------------------
	// 4.4 VALIDEREN
	// ------------------------------------------------------------------------------
	private int aantalMunten;
	private boolean valid = true;

	@Override
	public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException {
		if ("munt".equals(qName)) {
			aantalMunten++;
		}
	}

	@Override
	public void error(SAXParseException ex) throws SAXException {
		System.out.printf("%d %d %s%n", ex.getLineNumber(), ex.getColumnNumber(), ex.getMessage());
		valid = false;
	}

	public int getAantalMunten() {
		return aantalMunten;
	}

	public boolean isValid() {
		return valid;
	}

	// ------------------------------------------------------------------------------
	// VOORBEELD 2
	// ------------------------------------------------------------------------------
	/*
	 * @Override public void startElement(String namespaceURI, String sName,
	 * String qName, Attributes attrs) throws SAXException { if
	 * ("munt".equals(qName)) { System.out.printf("%s %s%n",
	 * attrs.getValue("code"), attrs.getValue("koers")); } }
	 */

	// ------------------------------------------------------------------------------
	// VOORBEELD 1
	// ------------------------------------------------------------------------------
	/*
	 * @Override public void startElement(String namespaceURI, String sName,
	 * String qName, Attributes attrs) throws SAXException { System.out.printf(
	 * "begintag %s%n", qName); for (int index = 0; index != attrs.getLength();
	 * index++) { System.out.printf("\t%s:%s%n", attrs.getLocalName(index),
	 * attrs.getValue(index)); } }
	 * 
	 * @Override public void characters(char buf[], int offset, int len) throws
	 * SAXException { System.out.printf("tekst %s%n", new String(buf, offset,
	 * len)); }
	 * 
	 * @Override public void endElement(String namespaceURI, String sName,
	 * String qName) throws SAXException { System.out.printf("eindtag %s%n",
	 * qName); }
	 */
}
