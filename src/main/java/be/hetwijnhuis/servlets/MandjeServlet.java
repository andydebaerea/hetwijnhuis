package be.hetwijnhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetwijnhuis.entities.Bestelbon;
import be.hetwijnhuis.entities.Wijn;
import be.hetwijnhuis.enums.Bestelwijze;
import be.hetwijnhuis.service.BestelbonService;
import be.hetwijnhuis.service.WijnService;
import be.hetwijnhuis.valueobjects.Adres;
import be.hetwijnhuis.valueobjects.BestelbonLijn;

@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "/mandjebevestigd.htm";
	private WijnService wijnService = new WijnService();
	private BestelbonService bestelbonService = new BestelbonService();

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			Map<Long, Integer> mandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (mandje != null) {
				Map<Wijn, BestelbonLijn> wijnen = new HashMap<>();
				Set<Long> wijnNrs = mandje.keySet();
				Set<BestelbonLijn> bestelbonLijnen = new HashSet<>();
				BestelbonLijn bestelbonLijn = null;
				for (Long wijnNr : wijnNrs) {
					Wijn wijn = wijnService.findByWijNr(wijnNr);
					bestelbonLijn = new BestelbonLijn(mandje.get(wijnNr), wijn);
					wijnen.put(wijn, bestelbonLijn);
					bestelbonLijnen.add(bestelbonLijn);
					
				}
				Bestelbon bestelbon = new Bestelbon(bestelbonLijnen);
				request.setAttribute("bestelbon", bestelbon);
			}	
		}
		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> fouten = new ArrayList<String>();

		String naam = request.getParameter("naam");
		if (naam == null || naam.isEmpty()) {
			fouten.add("naam niet ingevuld");
		}

		String straat = request.getParameter("straat");
		if (straat == null || straat.isEmpty()) {
			fouten.add("Straat niet ingevuld");
		}

		String huisNr = request.getParameter("huisNr");
		if (huisNr == null || huisNr.isEmpty()) {
			fouten.add("Huisnr. niet ingevuld");
		}

		String postcode = request.getParameter("postcode");
		if (postcode == null || postcode.isEmpty()) {
			fouten.add("Postcode niet ingevuld");
		}

		String gemeente = request.getParameter("gemeente");
		if (gemeente == null || gemeente.isEmpty()) {
			fouten.add("Gemeente niet ingevuld");
		}

		String bestelwijzeAlsString = request.getParameter("bestelwijze");
		if (bestelwijzeAlsString == null || bestelwijzeAlsString.isEmpty()) {
			fouten.add("Gelieve een bestelwijze aan te duiden");
		}
		HttpSession session = request.getSession();
		Map<Long, Integer> mandje = (Map<Long, Integer>) session
				.getAttribute("mandje");

		if (fouten.isEmpty()) {

			Adres adres = new Adres(straat, huisNr, postcode, gemeente);

			Bestelbon bestelbon = new Bestelbon(naam, adres,
					Bestelwijze.valueOf(request.getParameter("bestelwijze")));

			bestelbonService.create(bestelbon, mandje);
			session = request.getSession(false);
			if (session != null)
				session.invalidate();
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath()
					+ REDIRECT_URL
					+ "?bonNr="
					+ bestelbon.getBonNr()));
		} else {
			if (session != null) {
				if (mandje != null) {
					Map<Wijn, Integer> wijnen = new HashMap<>();
					for (Entry<Long, Integer> entry : mandje.entrySet()) {
						wijnen.put(wijnService.findByWijNr(entry.getKey()), entry.getValue());
					}
					request.setAttribute("wijnen", wijnen);
				}
			}
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
