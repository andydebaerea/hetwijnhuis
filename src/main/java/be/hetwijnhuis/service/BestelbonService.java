package be.hetwijnhuis.service;

import java.util.Map;

import be.hetwijnhuis.dao.BestelbonDAO;
import be.hetwijnhuis.dao.WijnDAO;
import be.hetwijnhuis.entities.Bestelbon;
import be.hetwijnhuis.entities.Wijn;
import be.hetwijnhuis.valueobjects.BestelbonLijn;

public class BestelbonService {
	private final BestelbonDAO bestelbonDAO = new BestelbonDAO();
	private final WijnDAO wijnDAO = new WijnDAO();
	
	public void create(Bestelbon bestelbon, Map<Long, Integer> mandje) {
		bestelbonDAO.beginTransaction();
		Wijn wijn = null;
		int aantal = 0;
		for (Map.Entry<Long, Integer> entry : mandje.entrySet()) {
			wijn = wijnDAO.readWithLock(entry.getKey());
			aantal = entry.getValue();
			bestelbon.addBestelbonlijn(new BestelbonLijn(aantal, wijn));
			wijn.verhoogInBestelling(aantal);
		}
		bestelbonDAO.create(bestelbon);
		bestelbonDAO.commit();
	}
}
