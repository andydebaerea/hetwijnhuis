package be.hetwijnhuis.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.hetwijnhuis.valueobjects.BestelbonLijn;

@Entity
@Table(name = "wijnen")
public class Wijn implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long wijnNr;
	private int jaar;
	private short beoordeling;
	private BigDecimal prijs;
	private int inBestelling;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soortNr")
	private Soort soort;
	@Transient
	private BestelbonLijn bestelbonLijn;
	

	protected Wijn() {
	}

	public Wijn(int jaar, short beoordeling, BigDecimal prijs,
			int inBestelling, Soort soort) {
		this.jaar = jaar;
		this.beoordeling = beoordeling;
		this.prijs = prijs;
		this.inBestelling = inBestelling;
		this.soort = soort;
	}
	
	public BestelbonLijn getBestelbonLijn() {
		return bestelbonLijn;
	}

	public Long getWijnNr() {
		return wijnNr;
	}

	public int getJaar() {
		return jaar;
	}

	public short getBeoordeling() {
		return beoordeling;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public int getInBestelling() {
		return inBestelling;
	}

	public Soort getSoort() {
		return soort;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setBeoordeling(short beoordeling) {
		this.beoordeling = beoordeling;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public void setInBestelling(int inBestelling) {
		this.inBestelling += inBestelling;
	}

	public void setSoort(Soort soort) {
		this.soort = soort;
	}

	public void verhoogInBestelling(int aantal) {
		inBestelling += aantal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jaar;
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wijn other = (Wijn) obj;
		if (jaar != other.jaar)
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;
		return true;
	}
	
	
}
