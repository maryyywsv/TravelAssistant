package com.cmu.sv.mary.soc.lab2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class FlightAssistant {
	private String year;
	private String month;
	private String day;
	
	private String carrier;
	private String flightNumber;
	private String url;
	
	private String flightID;
	private String depAirportName;
	private String arrAirportName;
	private String depTime;
	private String arrTime;

	public FlightAssistant(String carrier, String flightNumber, String year, String month, String day) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.carrier = carrier;
		this.flightNumber = flightNumber;
		
		
		this.url = String.format("https://api.flightstats.com/flex/flightstatus/rest/v2/xml/flight/status/%s/%s/dep/%s/%s/%s?appId=86ac4894&appKey=63b1ee181c2490be6958ed861c4b127a&utc=false", carrier, flightNumber, year, month, day);
		this.HttpConnection(url);
	}

	private void HttpConnection(String urlStr) {
		String result = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = null;
			conn = (HttpURLConnection) url.openConnection();
			if (conn == null) {
				throw new RuntimeException("Connection failure");
			}
			if (conn.getResponseCode() != 200) {
				throw new IOException(conn.getResponseMessage());
			}
			conn.connect();
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				builder.append(line);
				line = reader.readLine();
			}
			result = builder.toString();
			Element flightStatuses = this.flightStatusesElement(result);
			Element depAirport = this.depAirportNameElement(result);
			Element arrAirport = this.arrAirportNameElement(result);
			this.flightID = flightStatuses.getElementsByTagName("flightId").item(0).getTextContent();
			this.depAirportName = depAirport.getElementsByTagName("name").item(0).getTextContent();
			this.arrAirportName = arrAirport.getElementsByTagName("name").item(0).getTextContent();
			this.depTime = flightStatuses.getElementsByTagName("dateLocal").item(0).getTextContent();
			this.arrTime = flightStatuses.getElementsByTagName("dateLocal").item(1).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Element flightStatusesElement(String result) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new ByteArrayInputStream(result.getBytes()));
		Element flightStatuses = (Element) doc.getElementsByTagName("flightStatus").item(0);
		return flightStatuses;
	}
	
	private Element depAirportNameElement(String result) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new ByteArrayInputStream(result.getBytes()));
		Element depAirportName = (Element) doc.getElementsByTagName("airport").item(0);
		return depAirportName;
	}
	
	private Element arrAirportNameElement(String result) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new ByteArrayInputStream(result.getBytes()));
		Element arrAirportName = (Element) doc.getElementsByTagName("airport").item(1);
		return arrAirportName;
	}
	
	public String getflightID() {
		return this.flightID;
	}
	
	public String getdepAirportName() {
		return this.depAirportName;
	}
	
	public String getarrAirportName() {
		return this.arrAirportName;
	}
	
	public String getdepTime() {
		return this.depTime;
	}
	
	public String getarrTime() {
		return this.arrTime;
	}
}
