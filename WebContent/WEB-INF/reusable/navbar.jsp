<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>


	<c:choose>
		
		<c:when test = "${user_type == 'ADMIN' or user_type == 'LIBSTAFF' or user_type == 'LIBMNGR' }">
			<jsp:include page="admin-navbar.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="user-navbar.jsp"/>
		</c:otherwise>
	</c:choose>

</body>
</html>