package be.vdab.JAXB;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import be.vdab.entities.Koersen;
import be.vdab.entities.Munt;

public class Main {
	private final static Path PATH = Paths.get("/dataXML/koersen.xml");

	public static void main(String[] args) {
		try (Reader reader = Files.newBufferedReader(PATH)) {
			JAXBContext context = JAXBContext.newInstance(Koersen.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Koersen koersen = (Koersen) unmarshaller.unmarshal(reader);
			for (Munt munt : koersen.getMunt()) {
				System.out.printf("%s %f%n", munt.getCode(), munt.getKoers());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 7.5 Unmarshall + validation
	/*
	 * private final static Path PATH = Paths.get("/dataXML/werknemer.xml"); //
	 * validation private final static Path SCHEMA_PATH =
	 * Paths.get("/dataXML/werknemer.xsd");
	 * 
	 * public static void main(String[] args) { try (Reader reader =
	 * Files.newBufferedReader(PATH)) { JAXBContext context =
	 * JAXBContext.newInstance(Werknemer.class); Unmarshaller unmarshaller =
	 * context.createUnmarshaller(); SchemaFactory factory =
	 * SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); Schema
	 * schema = factory.newSchema(SCHEMA_PATH.toFile());
	 * unmarshaller.setSchema(schema); Werknemer werknemer = (Werknemer)
	 * unmarshaller.unmarshal(reader); System.out.println(werknemer); } catch
	 * (Exception ex) { ex.printStackTrace(); } }
	 */
	// 7.3 Marshall
	/*
	 * private final static Path PATH = Paths.get("/dataXML/werknemer.xml");
	 * 
	 * public static void main(String[] args) { Werknemer werknemer = new
	 * Werknemer(1, "Joe", "Dalton", Arrays.asList("The Boss", "Heatedly"),
	 * BigDecimal.valueOf(100)); try { JAXBContext context =
	 * JAXBContext.newInstance(Werknemer.class); Marshaller marshaller =
	 * context.createMarshaller();
	 * marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); try
	 * (Writer writer = Files.newBufferedWriter(PATH)) {
	 * marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
	 * "http://www.vdab.be/werknemer werknemer.xsd");
	 * marshaller.marshal(werknemer, writer); } } catch (Exception ex) {
	 * ex.printStackTrace(); } }
	 */
}
