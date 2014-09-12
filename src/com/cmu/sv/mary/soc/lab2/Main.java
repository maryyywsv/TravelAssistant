package com.cmu.sv.mary.soc.lab2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String carrier = request.getParameter("carrier");
		String flightNumber = request.getParameter("flightNumber");

		WeatherAssistant fromWeather = new WeatherAssistant(from, date);
		WeatherAssistant toWeather = new WeatherAssistant(to, date);

		FlightAssistant flightStatus = new FlightAssistant(carrier,
				flightNumber, year, month, day);

		request.setAttribute("fromTempMax", fromWeather.getTempMaxC());
		request.setAttribute("fromTempMin", fromWeather.getTempMinC());
		request.setAttribute("fromWindSpeedMiles",
				fromWeather.getWindSpeedMiles());
		request.setAttribute("fromWindDirection",
				fromWeather.getWindDirection());

		request.setAttribute("from", from);
		request.setAttribute("to", to);
		request.setAttribute("date", date);

		request.setAttribute("toTempMax", toWeather.getTempMaxC());
		request.setAttribute("toTempMin", toWeather.getTempMinC());
		request.setAttribute("toWindSpeedMiles", toWeather.getWindSpeedMiles());
		request.setAttribute("toWindDirection", toWeather.getWindDirection());

		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);

		request.setAttribute("flightID", flightStatus.getflightID());
		request.setAttribute("depAirportName", flightStatus.getdepAirportName());
		request.setAttribute("arrAirportName", flightStatus.getarrAirportName());
		request.setAttribute("depTime", flightStatus.getdepTime());
		request.setAttribute("arrTime", flightStatus.getarrTime());

		request.getRequestDispatcher("info.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
