package be.hetwijnhuis.dao;

import be.hetwijnhuis.entities.Bestelbon;

public class BestelbonDAO extends AbstractDAO{
	
	public void create(Bestelbon bestelbon) {
		getEntityManager().persist(bestelbon);
	}
}
