<!DOCTYPE html>
<html>
<head>
</head>
<title>Chat Server Online</title>
<body>
	<% response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
       response.setHeader("Refresh", "2; url=webchat.html");%>
        
        <%-- response.setHeader("Location", "webchat.html"); --%>

</body>
</html>