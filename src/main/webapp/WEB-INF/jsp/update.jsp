<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="com.giang.service.StudentService"%>
<%@page import="com.giang.model.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"><%@ include file="/WEB-INF/css/stylecalendar.css"%> </style>
<style type="text/css">
<%@ include file="/WEB-INF/css/style.css" %>
</style>
<script  src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"> </script>
<script  src="${pageContext.request.contextPath}/js/jquery-ui.js"> </script>
<title>Update student</title>
<script type="text/javascript">
$( function() {
    $( "#datepicker" ).datepicker();
  } );
</script>
</head>
<body>
<h1>SỬA THÔNG TIN SINH VIÊN</h1>
	<div class="insert-block">
		<spring:form method="post" commandName="student"
			action="${pageContext.request.contextPath}/student/update">
		<spring:input class="insert-block--input" path="student_id"
				placeholder="Name" value="${student.getStudent_id()}" readonly="true"/>
			<spring:input class="insert-block--input" path="student_name"
				placeholder="Name" value="${student.getStudent_name()}" />

			<spring:input class="insert-block--input" path="student_code"
				placeholder="Code" value="${student.getStudent_code()}" />

			<spring:input class="insert-block--input" path="address"
				placeholder="Address" value="${student.getAddress()}" />

			<spring:input class="insert-block--input" path="average_score"
				placeholder="Score" value="${student.getAverage_score()}" />
			<fmt:formatDate pattern="dd/MM/yyyy" var="dateformat"
				value="${student.getDate_of_birth() }" />

			<spring:input class="insert-block--input" path="date_of_birth"
				placeholder="Date of birth" value="${dateformat}" id="datepicker" readonly="true"/>

			<input class="insert-block--button" type="submit"
				value="Update student">

		</spring:form>
	</div>

</body>
</html>