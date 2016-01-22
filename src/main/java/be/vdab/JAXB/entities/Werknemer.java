package be.vdab.JAXB.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import be.vdab.JAXB.valueobjects.Naam;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Werknemer {
	@XmlAttribute(name = "id")
	private long nummer;
	private Naam naam;
	@XmlTransient
	private BigDecimal wedde;

	protected Werknemer() {
	}

	public Werknemer(long nummer, String voornaam, String familienaam,List<String> bijnamen, BigDecimal wedde) {
		this.nummer = nummer;
		this.naam = new Naam(voornaam, familienaam,bijnamen);
		this.wedde = wedde;
	}

	@Override
	public String toString() {
		return String.format("%d %s %f", nummer, naam, wedde);
	}
}
