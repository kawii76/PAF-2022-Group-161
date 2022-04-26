<%@page import="model.powerusage" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 
	    
	//Insert Feedback----------------------------------
	if (request.getParameter("pwrusagedataID") != null)
	 {
	 powerusage powerusageObj = new powerusage();
	 String stsMsg = powerusageObj.insertPowerusage(request.getParameter("pwrusagedataID"),
	
	 
	 request.getParameter("username"),
	 request.getParameter("useraddress"),
	 request.getParameter("noofpwrunits"),
	 request.getParameter("unitprice"),
	 request.getParameter("totalprice"),
	 request.getParameter("payedmonth"));
	 session.setAttribute("statusMsg", stsMsg);
	 }
	
	//Delete Feedback----------------------------------
	if (request.getParameter("recordID") != null)
	{
	powerusage powerusageObj = new powerusage();
	String stsMsg = powerusageObj.deletePowerusage(request.getParameter("recordID"));
	session.setAttribute("statusMsg", stsMsg);
	}   
	
	%>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Power Usage Management</title>
	</head>
	
	<body>
	
	<h1>Power Usage Management</h1>
	
	
	<form method="post" action="powerusage.jsp">
	 
	 Power Usage ID: <input name="pwrusagedataID" type="text"><br><br> 
	 Customer Name:   <input name="username" type="text"><br><br> 
	 Customer Address:     <input name="useraddress" type="text"><br><br> 
	 Number Of Power Units:     <input name="noofpwrunits" type="text"><br><br>
	 A One Unit Price:     <input name="unitprice" type="text"><br><br>
	 Total Bill Amount:     <input name="totalprice" type="text"><br><br>
	 Bill Payed Month:     <input name="payedmonth" type="text"><br><br>
	 
	  
	 
	 <input name="btnSubmit" type="submit" value="Submit"><br><br>
	</form>
	
	<%
	 out.print(session.getAttribute("statusMsg"));
	%>
	
	<br>
	
	<%
	powerusage powerusageObj = new powerusage();
	out.print(powerusageObj.readPowerusage());
	%>



</body>
</html>