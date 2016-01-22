package be.vdab.DOM;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class KoersenErrorHandler extends DefaultHandler {
	@Override
	public void error(SAXParseException ex) throws SAXException {
		System.out.printf("%d %d %s%n", ex.getLineNumber(), ex.getColumnNumber(), ex.getMessage());
	}
}
