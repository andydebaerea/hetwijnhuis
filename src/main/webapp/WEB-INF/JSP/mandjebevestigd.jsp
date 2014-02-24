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
	<h1>Je mandje is bevestigd als bestelbon ${param.bonNr}</h1>
</body>

</html>