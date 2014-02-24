<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='nl'>
<head>

<title>Het Wijnhuis - wijnen</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css' />
</head>

<body>
<c:import url="/WEB-INF/JSP/menu.jsp"></c:import>
	<h1>Het wijnhuis</h1>

	<h2>Kies een land</h2>

	<c:forEach items="${Landen}" var="land">
		<c:url value="/welkom.htm" var="welkomURLmetLandNr">
			<c:param name="landNr" value="${land.landNr}" />
		</c:url>
		<a href="${welkomURLmetLandNr}" title="${land.naam}"><img alt="${land.naam}"
			src="${contextPath}/images/${land.landNr}.png"></a>
	</c:forEach>
	<c:if test="${not empty soorten}">
		<div>
			<h2>Kies een soort uit ${land.naam}</h2>
			<ul>
				<c:forEach items="${soorten}" var="soort">
					<c:url value="/welkom.htm" var="welkomURLmetSoortNr">
						<c:param name="landNr" value="${soort.land.landNr}" />
						<c:param name="soortNr" value="${soort.soortNr}" />
					</c:url>
					<li><a href="${welkomURLmetSoortNr}">${soort.naam}</a></li>
				</c:forEach>
			</ul>
		</div>

	</c:if>
	<c:if test="${not empty wijnen}">
		<div>
			<h2>Kies een wijn uit ${soort.naam}</h2>
			<ul>
				<c:forEach items="${wijnen}" var="wijn">
					<c:url value="/toevoegen.htm" var="URLmetWijnNr">
						<c:param name="wijnNr" value="${wijn.wijnNr}" />
					</c:url>
					<li><a href="${URLmetWijnNr}">${wijn.jaar}</a>
					<c:forEach begin="1" end="${wijn.beoordeling}">&#9733;</c:forEach>
					</li>
				</c:forEach>
			</ul>
		</div>

	</c:if>
</body>

</html>