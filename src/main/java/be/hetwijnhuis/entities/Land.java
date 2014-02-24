package be.hetwijnhuis.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "landen")
public class Land implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long landNr;
	private String naam;
	@OneToMany
	@JoinColumn(name = "landNr")
	@OrderBy("naam")
	private Set<Soort> soorten;
	
	protected Land() {
	}


	public Long getLandNr() {
		return landNr;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public String toString() {
		return landNr + ": " + naam;
	}
	
	public Set<Soort> getSoorten() {
		return Collections.unmodifiableSet(soorten);
	}
}
