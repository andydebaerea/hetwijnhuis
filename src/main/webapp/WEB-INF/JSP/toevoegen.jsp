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
	<h1>Wijn toevoegen aan mandje</h1>

	Land
	<h4>${wijn.soort.land.naam}</h4>
	Soort
	<h4>${wijn.soort.naam}</h4>
	Jaar
	<h4>${wijn.jaar}</h4>
	Beoordeling
	<h4>
		<c:forEach begin="1" end="${wijn.beoordeling}">&#9733;</c:forEach>
	</h4>
	Prijs
	<h4>
		<fmt:formatNumber value="${wijn.prijs}" minFractionDigits="2"
			maxFractionDigits="2" />
		&euro;
	</h4>

	<c:url value="/toevoegen.htm?wijnNr=${wijn.wijnNr}" var="toevoegenURL"></c:url>
	<form action="${toevoegenURL}" method="post">
		<label>Aantal flessen <c:if test="${not empty aantal}">
				<c:set var="reedsInMandje" value="${aantal}" />
			</c:if> <input type="number" name="aantal" autofocus
			value="${reedsInMandje}" /></label><span class="fout">${fout}</span> <input
			type="submit" value="Toevoegen">
	</form>

</body>

</html>