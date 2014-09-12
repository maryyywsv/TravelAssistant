<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel Assistant</title>
</head>
<body>
	<div class="col-sm-offset-2 col-sm-8">
		<br/>
		<p>
			Date:<%=request.getAttribute("date")%></p>

		<table class="table table-hover">
			<tr class="success">
				<td class="warning"></td>
				<td>From</td>
				<td>To</td>
			</tr>
			<tr>
				<td class="warning">City</td>
				<td><%=request.getAttribute("from")%></td>
				<td><%=request.getAttribute("to")%></td>
			</tr>
			<tr>
				<td class="warning">Maximum Temperature (C)</td>
				<td><%=request.getAttribute("fromTempMax")%></td>
				<td><%=request.getAttribute("toTempMax")%></td>
			</tr>
			<tr>
				<td class="warning">Minimum Temperature (C)</td>
				<td><%=request.getAttribute("fromTempMin")%></td>
				<td><%=request.getAttribute("toTempMin")%></td>
			</tr>
			<tr>
				<td class="warning">Wind Speed Miles</td>
				<td><%=request.getAttribute("fromWindSpeedMiles")%></td>
				<td><%=request.getAttribute("toWindSpeedMiles")%></td>
			</tr>
			<tr>
				<td class="warning">Wind Direction</td>
				<td><%=request.getAttribute("fromWindDirection")%></td>
				<td><%=request.getAttribute("toWindDirection")%></td>
			</tr>
		</table>

		<br/>
		<p>
			Date: <%=request.getAttribute("year")%>/<%=request.getAttribute("month")%>/<%=request.getAttribute("day")%></p>

			<table class="table table-hover">
				<tr>
					<td class="warning">Flight ID</td>
					<td><%=request.getAttribute("flightID")%></td>
				</tr>
				<tr>
					<td class="warning">Departure Airport</td>
					<td><%=request.getAttribute("depAirportName")%></td>
				</tr>
				<tr>
					<td class="warning">Arrival Airport</td>
					<td><%=request.getAttribute("arrAirportName")%></td>
				</tr>
				<tr>
					<td class="warning">Departure Time</td>
					<td><%=request.getAttribute("depTime")%></td>
				</tr>
				<tr>
					<td class="warning">Arrival Time</td>
					<td><%=request.getAttribute("arrTime")%></td>
				</tr>
			</table>
			<p>Travel Assistant WebApp is made by Yuwei Yang.</p>
	</div>
</body>
</html>