package be.hetwijnhuis.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import be.hetwijnhuis.entities.Bestelbon;
import be.hetwijnhuis.entities.Wijn;

@Embeddable
public class BestelbonLijn implements Serializable {
	private static final long serialVersionUID = 1L;
	private int aantal;
	@ManyToOne
	@JoinColumn(name = "wijnNr")
	private Wijn wijn;
	@Transient
	private Bestelbon bestelbon;

	protected BestelbonLijn() {
	}

	public BestelbonLijn(int aantal, Wijn wijn) {
		this.aantal = aantal;
		setWijn(wijn);

	}

	public int getAantal() {
		return aantal;
	}

	public Wijn getWijn() {
		return wijn;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public void setWijn(Wijn wijn) {
		this.wijn = wijn;
	}

	public Bestelbon getBestelbon() {
		return bestelbon;
	}

	public void setBestelBon(Bestelbon bestelbon) {
		this.bestelbon = bestelbon;
	}

	public BigDecimal getTotaalPerLijn() {
		return wijn.getPrijs().multiply(new BigDecimal(aantal));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wijn == null) ? 0 : wijn.hashCode());
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
		BestelbonLijn other = (BestelbonLijn) obj;
		if (wijn == null) {
			if (other.wijn != null)
				return false;
		} else if (!wijn.equals(other.wijn))
			return false;
		return true;
	}

	
}
