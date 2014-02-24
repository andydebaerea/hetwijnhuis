package be.hetwijnhuis.dao;


import be.hetwijnhuis.entities.Soort;

public class SoortDAO extends AbstractDAO {
	
	public Soort read(Long soortNr) {
		return getEntityManager().find(Soort.class, soortNr);
	}
}
