package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class APIAccess {
	
	private static final String APIKey = "M1lq2uLfNrdSGBAOVOuZrELwSq6Mmnek";

	protected static InputStream airport(String code){
		String url = "https://demo30-test.apigee.net/v1/hack/search/airport";
		String charset = "UTF-8";
		String req = "";
		try {
			req = String.format("code=%s&apikey=%s", 
			     URLEncoder.encode(code, charset), 
			     URLEncoder.encode(APIKey, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = new URL(url + "?" + req).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = null;
		try {
			response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	protected static InputStream fSchedule(String todayDate, String departureDate, String departureAirportCode,
			String arrivalAirportCode){
		String url = "https://demo30-test.apigee.net/v1/hack/schedule";
		String charset = "UTF-8";
		String req = "todayDate="+todayDate+"&departureDate="+departureDate+"%departureAiportCode="+departureAirportCode
					+ "&arrivalAirportCode="+arrivalAirportCode+"&apikey="+APIKey;
		/*try {
			req = String.format("todayDate=%s&departureDate=%s%departureAiportCode=%s"
					+ "&arrivalAirportCode=%s&apikey=%s", 
			     URLEncoder.encode(todayDate, charset),
			     URLEncoder.encode(departureDate, charset),
			     URLEncoder.encode(departureAirportCode, charset),
			     URLEncoder.encode(arrivalAirportCode, charset),
			     URLEncoder.encode(APIKey, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		URLConnection connection = null;
		try {
			connection = new URL(url + "?" + req).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = null;
		try {
			response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	protected static InputStream fStatus(String fNumber, String fOrigin){
		String url = "https://demo30-test.apigee.net/v1/hack/status";
		String charset = "UTF-8";
		String req = "flightNumber="+fNumber+"&flightOriginDate="+fOrigin+"&apikey="+APIKey;
		/*try {
			req = String.format("flightNumber=%s&flightOriginDate=%s&apikey=%s", 
			     URLEncoder.encode(fNumber, charset),
			     URLEncoder.encode(fOrigin, charset),
			     URLEncoder.encode(APIKey, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		URLConnection connection = null;
		try {
			connection = new URL(url + "?" + req).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = null;
		try {
			response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	protected static InputStream tsa(String airport){
		String url = "https://demo30-test.apigee.net/v1/hack/tsa";
		String charset = "UTF-8";
		String req = "airport="+airport+"&apikey="+APIKey;
		/*try {
			req = String.format("airport=%s&apikey=%s", 
			     URLEncoder.encode(airport, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		URLConnection connection = null;
		try {
			connection = new URL(url + "?" + req).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = null;
		try {
			response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	protected static InputStream weather(String code){
		String url = "https://demo30-test.apigee.net/v1/hack/tsa";
		String charset = "UTF-8";
		String req = "code="+code+"&apikey="+APIKey;
		/*try {
			req = String.format("code=%s&apikey=%s", 
			     URLEncoder.encode(code, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		URLConnection connection = null;
		try {
			connection = new URL(url + "?" + req).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = null;
		try {
			response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
