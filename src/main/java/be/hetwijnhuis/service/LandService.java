package be.hetwijnhuis.service;

import be.hetwijnhuis.dao.LandDAO;
import be.hetwijnhuis.entities.Land;

public class LandService {
	LandDAO landDAO = new LandDAO();
	
	public Iterable<Land> findAll() {
		return landDAO.findAll();
	}
	
	public Land read(Long landNr) {
		return landDAO.read(landNr);
	}
}
