package be.vdab.XMLStreamReader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

class Main {

	// ------------------------------------------------------------------------------
	// VOORBEELD 2
	// ------------------------------------------------------------------------------
	private static final String URL = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

	public static void main(String[] args) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try (InputStream stream = new URL(URL).openStream();
				InputStream bufferedStream = new BufferedInputStream(stream)) {
			XMLStreamReader reader = null;
			try {
				reader = factory.createXMLStreamReader(bufferedStream);
				while (reader.hasNext()) {
					if (reader.next() == XMLStreamConstants.START_ELEMENT && "Cube".equals(reader.getLocalName())
							&& reader.getAttributeCount() == 2) {
						System.out.printf("%s:%s%n", reader.getAttributeValue(null, "currency"),
								reader.getAttributeValue(null, "rate"));
					}
				}
			} finally {
				if (reader != null) {
					try {
						reader.close();
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
	// VOORBEELD 2
	// ------------------------------------------------------------------------------
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koersen.xml");
	 * 
	 * public static void main(String[] args) { XMLInputFactory factory =
	 * XMLInputFactory.newInstance(); try (Reader bufferedReader =
	 * Files.newBufferedReader(PATH)) { XMLStreamReader reader = null; try {
	 * reader = factory.createXMLStreamReader(bufferedReader); while
	 * (reader.hasNext()) { if (reader.next() ==
	 * XMLStreamConstants.START_ELEMENT && "munt".equals(reader.getLocalName())
	 * && "USD".equals(reader.getAttributeValue(null, "code"))) {
	 * System.out.println(reader.getAttributeValue(null, "koers")); break; } } }
	 * finally { if (reader != null) { try { reader.close(); } catch
	 * (XMLStreamException ex) { ex.printStackTrace(); } } } } catch (Exception
	 * ex) { ex.printStackTrace(); } }
	 */
	// ------------------------------------------------------------------------------
	// VOORBEELD 1
	// ------------------------------------------------------------------------------
	/*
	 * private static final Path PATH = Paths.get("/dataXML/koers.xml");
	 * 
	 * public static void main(String[] args) { XMLInputFactory factory =
	 * XMLInputFactory.newInstance(); try (Reader bufferedReader =
	 * Files.newBufferedReader(PATH)) { XMLStreamReader reader = null; try {
	 * reader = factory.createXMLStreamReader(bufferedReader); while
	 * (reader.hasNext()) { switch (reader.next()) { case
	 * XMLStreamConstants.START_ELEMENT: System.out.printf("begintag %s%n",
	 * reader.getLocalName()); for (int index = 0; index !=
	 * reader.getAttributeCount(); index++) { System.out.printf("\t%s:%s%n",
	 * reader.getAttributeLocalName(index), reader.getAttributeValue(index)); }
	 * break; case XMLStreamConstants.CHARACTERS: System.out.printf("tekst %s%n"
	 * , reader.getText()); break; case XMLStreamConstants.END_ELEMENT:
	 * System.out.printf("eindtag %s%n", reader.getLocalName()); break; } } }
	 * finally { if (reader != null) { try { reader.close(); } catch
	 * (XMLStreamException ex) { ex.printStackTrace(); } } } } catch (Exception
	 * ex) { ex.printStackTrace(); } System.out.println(); }
	 */
}
