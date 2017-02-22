<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.giang.service.StudentService"%>
<%@page import="com.giang.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<%@
include file="/WEB-INF/css/style.css"%>
</style>
<title>There is page show</title>
</head>
<body>
	<h1>DANH SÁCH SINH VIÊN</h1>
	<spring:form method="get" commandName="student"
		action="${pageContext.request.contextPath}/student/insert">
		<input class="add-block--button" type="submit" value="Add new student">
	</spring:form>
	<c:if test='<%= request.getParameter("lastId")!=null%>'>
	<p class="show-last-id">Last id insert(student):<%= request.getParameter("lastId")%></p>
	<p class="show-last-id2">Last id insert(info):<%= request.getParameter("lastId2")%></p>
	</c:if>
	<spring:form method="post" commandName="student"
		action="${pageContext.request.contextPath}/student/search">
		<spring:input class="search-block--input" path="average_score"
			placeholder="Search Name"
			value='<%=session.getAttribute("keySearch")%>' />
		<input class="search-block--button" type="submit" value="Search">
	</spring:form>
	<spring:form method="get" commandName="student"
		action="${pageContext.request.contextPath}/student/removeSession">
		<input class="cancel-block--button" type="submit" value="Cancel">
	</spring:form>
	<a class="logout" href="<c:url value='/student/logout'></c:url>">Log out</a>	
	<c:set scope="request" var="i" value="1" />
	<div class="divTable">
		<div class="divTableBody">
			<div class="divTableRow">
				<div class="divTableTitle">ID</div>
				<div class="divTableTitle">User name</div>
				<div class="divTableTitle">Code</div>
				<div class="divTableTitle">Address</div>
				<div class="divTableTitle">Score</div>
				<div class="divTableTitle">Date of birth</div>
				<div class="divTableTitle">Function</div>
				<div class="divTableTitle">Function</div>
			</div>
			<c:forEach items="${studentList}" var="student">
				<div class="divTableRow">
					<div class="divTableCell">${student.getStudent_id()}</div>
					<div class="divTableCell text-left">${student.getStudent_name()}</div>
					<div class="divTableCell">${student.getStudent_code()}</div>
					<div class="divTableCell">${student.getAddress()}</div>
					<div class="divTableCell">${student.getAverage_score()}</div>
					<div class="divTableCell">
						<fmt:formatDate pattern="dd/MM/yyyy"
							value="${student.getDate_of_birth()}" />
					</div>
					<div class="divTableCell">
						<a
							href="<c:url value='/student/delete/${student.getStudent_id() }'></c:url>">Delete</a>
					</div>
					<div class="divTableCell">
						<a
							href="<c:url value='/student/update/${student.getStudent_id() }'></c:url>">Edit</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="container-page">
		<c:choose>
			<c:when test='<%=session.getAttribute("keySearch")==null%>'>
				<%-- begin v1 --%>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/page/${page.getFirstPage() }'></c:url>">
						<< </a>
				</div>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/page/${page.getPrevPage() }'></c:url>">
						< </a>
				</div>
				<c:forEach var="page_id" begin="1" end="${page.getSize()}">
					<c:choose>
						<c:when test="${page_id == page.getCurrentPosition()}">
							<div class="container-page-record container-page-record--current">
								<a href="<c:url value='/student/page/${page_id}'></c:url>">
									<c:out value="${page_id}" />
								</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="container-page-record">
								<a href="<c:url value='/student/page/${page_id}'></c:url>">
									<c:out value="${page_id}" />
								</a>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%-- end v1 --%>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/page/${page.getNextPage() }'></c:url>">
						> </a>
				</div>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/page/${page.getEndPage() }'></c:url>">
						>> </a>
				</div>
			</c:when>
			<c:otherwise>
				<%-- begin v1 --%>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/search/${page.getFirstPage() }'></c:url>">
						<< </a>
				</div>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/search/${page.getPrevPage() }'></c:url>">
						< </a>
				</div>
				<c:forEach var="page_id" begin="1" end="${page.getSize()}">
					<c:choose>
						<c:when test="${page_id == page.getCurrentPosition()}">
							<div class="container-page-record container-page-record--current">
								<a href="<c:url value='/student/search/${page_id}'></c:url>">
									<c:out value="${page_id}" />
								</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="container-page-record">
								<a href="<c:url value='/student/search/${page_id}'></c:url>">
									<c:out value="${page_id}" />
								</a>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%-- end v1 --%>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/search/${page.getNextPage() }'></c:url>">
						> </a>
				</div>
				<div class="container-page-record">
					<a
						href="<c:url value='/student/search/${page.getEndPage() }'></c:url>">
						>> </a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>