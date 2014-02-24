package be.hetwijnhuis.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "soorten")
public class Soort implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long soortNr;
	private String naam;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "landNr")
	private Land land;
	@OneToMany
	@JoinColumn(name = "soortNr")
	@OrderBy("jaar")
	private Set<Wijn> wijnen;
	
	protected Soort() {
	}
	
	public Set<Wijn> getWijnen() {
		return Collections.unmodifiableSet(wijnen);
	}
	
	public Soort(String Naam) {
		setNaam(Naam);
	}

	public Long getSoortNr() {
		return soortNr;
	}

	public String getNaam() {
		return naam;
	}

	public Land getLand() {
		return land;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Soort)) {
			return false;
		}
		return ((Soort)obj).naam.equalsIgnoreCase(this.naam);
	}
	
	
}
