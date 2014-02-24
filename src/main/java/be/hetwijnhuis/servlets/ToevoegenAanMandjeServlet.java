package be.hetwijnhuis.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetwijnhuis.entities.Wijn;
import be.hetwijnhuis.service.WijnService;

@WebServlet("/toevoegen.htm")
public class ToevoegenAanMandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/toevoegen.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	private final WijnService wijnService = new WijnService();

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterMap().isEmpty()) {
			try {
				Long wijnNr = Long.parseLong(request.getParameter("wijnNr"));
				Wijn wijn = wijnService.findByWijNr(wijnNr);
				request.setAttribute("wijn", wijn);
				HttpSession session = request.getSession();
				if (session != null) {
					Map<Long, Integer> mandje = (Map<Long, Integer>) session
							.getAttribute("mandje");
					if (mandje != null) {
						if (mandje.get(wijnNr) != null) {
							int aantal = mandje.get(wijnNr);
							request.setAttribute("aantal", aantal);
						}
					}
				}
			} catch (NumberFormatException nfex) {
				throw nfex;
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long wijnNr = Long.parseLong(request.getParameter("wijnNr"));
		Wijn wijn = wijnService.findByWijNr(wijnNr);
		try {
			int aantal = Integer.parseInt(request.getParameter("aantal"));
			if (aantal <= 0) {
				request.setAttribute("fout", "Aantal moet hoger zijn dan 0");
				request.setAttribute("wijn", wijn);
				request.getRequestDispatcher(VIEW).forward(request, response);
			} else {
				HttpSession session = request.getSession();
				Map<Long, Integer> mandje = (Map<Long, Integer>) session
						.getAttribute("mandje");
				if (mandje == null) {
					mandje = new HashMap<Long, Integer>();
				}
				mandje.put(wijn.getWijnNr(), aantal);

				session.setAttribute("mandje", mandje);
				response.sendRedirect(response.encodeRedirectURL(request
						.getContextPath() + REDIRECT_URL));
			}
		} catch (NumberFormatException nfex) {
			request.setAttribute("fout", "Aantal flessen is niet ingevuld");
			request.setAttribute("wijn", wijn);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
