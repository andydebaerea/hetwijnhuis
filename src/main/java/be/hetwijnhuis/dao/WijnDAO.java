package be.hetwijnhuis.dao;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import be.hetwijnhuis.entities.Wijn;

public class WijnDAO extends AbstractDAO {

	public Wijn read(long wijnNr) {
		return getEntityManager().find(Wijn.class, wijnNr);
	}

	public Wijn findByWijNr(Long wijnNr) {
		TypedQuery<Wijn> query = getEntityManager().createNamedQuery(
				"wijn.findByWijnNr", Wijn.class);
		query.setParameter("wijnNr", wijnNr);
		try {
			return query.getSingleResult();
		} catch (NoResultException nrex) {
			return null;
		}
	}

	public Wijn readWithLock(Long wijnNr) {
		return getEntityManager().find(Wijn.class, wijnNr,
				LockModeType.PESSIMISTIC_WRITE);
	}
}
