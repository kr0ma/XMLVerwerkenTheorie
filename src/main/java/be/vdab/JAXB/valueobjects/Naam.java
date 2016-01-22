package be.vdab.JAXB.valueobjects;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"familienaam", "voornaam", "bijnamen"})
public class Naam {
	private String voornaam;
	private String familienaam;
	@XmlElement(name = "bijnaam")
	@XmlElementWrapper(name = "bijnamen")
	private List<String> bijnamen;

	public Naam(String voornaam, String familienaam,List<String> bijnamen) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.bijnamen = bijnamen;
	}

	protected Naam() { // default constructor is verplicht
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", voornaam, familienaam, bijnamen);
	}
}