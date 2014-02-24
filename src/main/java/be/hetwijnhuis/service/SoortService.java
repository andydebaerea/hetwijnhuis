package be.hetwijnhuis.service;

import be.hetwijnhuis.dao.SoortDAO;
import be.hetwijnhuis.entities.Soort;

public class SoortService {
	private SoortDAO soortDAO = new SoortDAO();
	
	public Soort read(Long soortNr) {
		return soortDAO.read(soortNr);
	}
}
