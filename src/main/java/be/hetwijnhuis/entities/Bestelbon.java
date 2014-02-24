package be.hetwijnhuis.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.hetwijnhuis.enums.Bestelwijze;
import be.hetwijnhuis.valueobjects.Adres;
import be.hetwijnhuis.valueobjects.BestelbonLijn;

@Entity
@Table(name="bestelbonnen")
public class Bestelbon implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long bonNr;
	@Temporal(TemporalType.TIMESTAMP)
	private Date bestelDatum;
	private String naam;
	@Embedded
	private Adres adres;
	private Bestelwijze bestelwijze;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonNr"))
	private Set<BestelbonLijn> bestelbonLijnen;
	
	protected Bestelbon () {
	}

	public Bestelbon(String naam, Adres adres, Bestelwijze bestelwijze) {
		setNaam(naam);
		setAdres(adres);
		setBestelDatum();
		setBestelwijze(bestelwijze);
		bestelbonLijnen = new HashSet<>();
	}
	
	public Bestelbon(Set<BestelbonLijn> bestelbonLijnen) {
		this.bestelbonLijnen = bestelbonLijnen;
	}
	
	public Bestelwijze getBestelwijze() {
		return bestelwijze;
	}

	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze;
	}

	public Set<BestelbonLijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonLijnen);
	}
	
	public void addBestelbonlijn(BestelbonLijn bestelbonLijn) {
		bestelbonLijnen.add(bestelbonLijn);
		if (bestelbonLijn.getBestelbon() != this) 
			bestelbonLijn.setBestelBon(this);
	}

	public Long getBonNr() {
		return bonNr;
	}

	public Date getBestelDatum() {
		return (Date) bestelDatum.clone();
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setBestelDatum() {
		this.bestelDatum = (Date) new Date().clone();
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public BigDecimal getTotaalVanBestelBon() {
		BigDecimal totaalVanBestelBon = BigDecimal.ZERO;
		for (BestelbonLijn bestelbonLijn : bestelbonLijnen) {
			totaalVanBestelBon = totaalVanBestelBon.add(bestelbonLijn.getWijn().getPrijs().multiply(new BigDecimal(bestelbonLijn.getAantal())));
		}
		return totaalVanBestelBon;
	}
	
}
