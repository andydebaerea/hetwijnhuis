package be.hetwijnhuis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.hetwijnhuis.entities.Land;
import be.hetwijnhuis.entities.Soort;
import be.hetwijnhuis.service.LandService;
import be.hetwijnhuis.service.SoortService;

/**
 * Servlet implementation class Welkom
 */
@WebServlet("/welkom.htm")
public class Welkom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/welkom.jsp";
	private final LandService landService = new LandService();
	private final SoortService soortService = new SoortService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * als methode wordt aangeroepen om een lijst met verschillende objecten
		 * te verkrijgen en men later nogmaals 1 van deze objecten nodig heeft
		 * methode EERST uitvoeren. Hibernate zoekt dan in intern geheugen naar
		 * object, zoals hier met Landen waar hibernate land zelf opzoekt zonder
		 * opnieuw database aan te spreken
		 */
		request.setAttribute("Landen", landService.findAll());
		if (!request.getParameterMap().isEmpty()) {
			String landNrAlsString = request.getParameter("landNr");
			String soortNrAlsString = request.getParameter("soortNr");
			if (landNrAlsString != null) {
				try {
					Long landNr = Long.valueOf(request.getParameter("landNr"));
					Land land = landService.read(landNr);
					request.setAttribute("land", land);
					request.setAttribute("soorten",
							land.getSoorten());
					if (soortNrAlsString != null) {
						try {
							Long soortNr = Long.valueOf(request
									.getParameter("soortNr"));
							Soort soort = soortService.read(soortNr);
							request.setAttribute("soort",
									soort);
							request.setAttribute("wijnen", soort
									.getWijnen());
						} catch (NumberFormatException nfex) {
							throw nfex;
						}
					}
				} catch (NumberFormatException nfex) {
					throw nfex;
				}
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
