package com.cmu.sv.mary.soc.lab2;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class WeatherAssistant {
	private String city;
	private String date;
	private String url;
	private int tempMaxC;
	private int tempMinC;
	private int windSpeedMiles;
	private String windDirection;
	
	public WeatherAssistant(String city, String date) {
		this.city = city;
		this.date = date;
		this.url = String.format("http://api.worldweatheronline.com/free/v1/weather.ashx?q=%s&format=XML&date=%s&key=71fbccb9c0e4bef74a45a39749993479aaaab9ed", city, date );
		this.url = this.url.replace(" ","%20");	
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
			Element weather = this.weatherElement(result);
			this.tempMaxC = Integer.parseInt(weather.getElementsByTagName("tempMaxC").item(0).getTextContent());
			this.tempMinC = Integer.parseInt(weather.getElementsByTagName("tempMinC").item(0).getTextContent());
			this.windSpeedMiles = Integer.parseInt(weather.getElementsByTagName("windspeedMiles").item(0).getTextContent());
			this.windDirection = weather.getElementsByTagName("winddirection").item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Element weatherElement(String result) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new ByteArrayInputStream(result.getBytes()));
		Element weather = (Element) doc.getElementsByTagName("weather").item(0);
		return weather;
	}
	
	public int getTempMaxC() {
		return this.tempMaxC;
	}
	public int getTempMinC(){
		return this.tempMinC;
	}
	public int getWindSpeedMiles(){
		return this.windSpeedMiles;
	}
	public String getWindDirection(){
		return this.windDirection;
	}
	
}
