package be.hetwijnhuis.service;

import be.hetwijnhuis.dao.WijnDAO;
import be.hetwijnhuis.entities.Wijn;

public class WijnService {
	WijnDAO wijnDAO = new WijnDAO();
	
	
	public Wijn read(Long wijnNr) {
		return wijnDAO.read(wijnNr);
	}
	public Wijn findByWijNr(Long wijnNr) {
		return wijnDAO.findByWijNr(wijnNr);
	}
}
