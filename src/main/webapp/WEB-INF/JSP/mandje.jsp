<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='nl'>
<head>

<title>Het Wijnhuis - wijnen toevoegen</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css' />
</head>

<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"></c:import>
	<h1>Mandje</h1>
	<table>
		<tr>
			<th>Wijn</th>
			<th>Prijs</th>
			<th>Aantal</th>
			<th>Te Betalen</th>
		</tr>
		<c:forEach items="${bestelbon.bestelbonlijnen}" var="bestelbonlijn">
			<tr>
				<td>${bestelbonlijn.wijn.soort.land.naam}
					${bestelbonlijn.wijn.soort.naam} ${bestelbonlijn.wijn.jaar}</td>
				<td>${bestelbonlijn.wijn.prijs}
				<td class="right">${bestelbonlijn.aantal}</td>
				<td class="right"><fmt:formatNumber
						value="${bestelbonlijn.totaalPerLijn}" minFractionDigits="2"
						maxFractionDigits="2" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td class="right" colspan="4">Totaal: <fmt:formatNumber
					value="${bestelbon.totaalVanBestelBon}" minFractionDigits="2"
					maxFractionDigits="2" /></td>
	</table>


	<c:url value="/mandje.htm" var="mandjeURL" />
	<form action="${bevestigdURL}" method="post">
		<label>Naam<input name="naam" value="${param.naam}"></label> <label>Straat<input
			name="straat" value="${param.straat}"></label> <label>Huisnr<input
			name="huisNr" value="${param.huisNr}"></label> <label>Postcode<input
			name="postcode" value="${param.postcode}"></label> <label>Gemeente<input
			name="gemeente" value="${param.gemeente}"></label> <label> <input
			value="AFHALEN" ${param.bestelwijze == "AFHALEN" ? "cheked" : ""}
			type="radio" name="bestelwijze">Afhalen
		</label> <label> <input value="OPSTUREN"
			${param.bestelwijze == "OPSTUREN" ? "cheked" : ""} type="radio"
			name="bestelwijze">Opsturen
		</label> <input type="submit" value="Toevoegen" />
	</form>
	<c:if test="${not empty fouten}">
		<ul>
			<c:forEach var="fout" items="${fouten}">
				<li class="fout">${fout}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>

</html>