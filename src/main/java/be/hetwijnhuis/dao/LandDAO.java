package be.hetwijnhuis.dao;

import javax.persistence.TypedQuery;

import be.hetwijnhuis.entities.Land;

public class LandDAO extends AbstractDAO {
	
	public Iterable<Land> findAll() {
		TypedQuery<Land> query = getEntityManager().createNamedQuery(
				"land.findAll", Land.class);
		return query.getResultList();
	}
	
	public Land read(Long landNr) {
		return getEntityManager().find(Land.class, landNr);
	}
}
