<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="com.giang.service.StudentService"%>
<%@page import="com.giang.model.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css"><%@ include file="/WEB-INF/css/style.css"%> </style>
<style type="text/css"><%@ include file="/WEB-INF/css/stylecalendar.css"%> </style>
<script  src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"> </script>
<script  src="${pageContext.request.contextPath}/js/jquery-ui.js"> </script>
<title>Insert Student</title>
<script type="text/javascript">
$( function() {
    $( "#datepicker" ).datepicker();
  } );
</script>
</head>
<body>
<h1>THÊM SINH VIÊN MỚI</h1>
	<div class="insert-block">
		<spring:form  method="post" modelAttribute="student" action="${pageContext.request.contextPath}/student/insert" >

			<spring:input class="insert-block--input" path="student_name" placeholder="Name" />
			<br/><spring:errors path="student_name" cssClass="error"/>

			<spring:input class="insert-block--input" path="student_code" placeholder="Code" />
			<br/><spring:errors path="student_code" cssClass="error"/>
			
			<spring:input class="insert-block--input" path="address" placeholder="Address"/>
			<br/><spring:errors path="address" cssClass="error"/>
			
			<spring:input class="insert-block--input" path="average_score" placeholder="Score"/>
			<br/><spring:errors path="average_score" cssClass="error"/>

			<spring:input class="insert-block--input" path="date_of_birth" placeholder="Date of birth" id="datepicker" readonly="true"/>
			<br/><spring:errors path="date_of_birth" cssClass="error"/>
			<input class="insert-block--button" type="submit" value="insert new student">
			
		</spring:form>
	</div>	

</body>
</html>