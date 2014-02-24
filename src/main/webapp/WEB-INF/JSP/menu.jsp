<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='nl'>
<head>
<title>JPA website</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css' />
</head>
<body>
		<ul class="zonderbolletjes">
			<li><a href="<c:url value='/welkom.htm'/>">Wijnen</a></li>
			<li><a href="<c:url value='/mandje.htm'/>">Mandje</a></li>
		</ul>
</body>
</html>