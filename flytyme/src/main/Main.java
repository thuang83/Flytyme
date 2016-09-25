package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.InputStream; 

/**
 * Servlet implementation class main
 */
@WebServlet("/Main")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		InputStream test = APIAccess.fStatus("1234", "1234");
		String test2 = ParseStream.parse(test);
		out.println(test2);
		/*
		//InputStream test3 = APIAccess.fSchedule("1234", "1234", "atl", "lax");
		//String test4 = ParseStream.parse(test3);
		//out.println(test4);
		
		InputStream test5 = APIAccess.tsa("atl");
		String test6 = ParseStream.parse(test5);
		out.println(test6);
		
		//InputStream test7 = APIAccess.weather("atl");
		//String test8 = ParseStream.parse(test7);
		//out.println(test8);*/
		
		//retrieving for data
		String todaydate = request.getParameter("todaydate");
		String fNumber = request.getParameter("flight");
		String departureDate = request.getParameter("departdate");
		String dCode = request.getParameter("departcode");
		String aCode = request.getParameter("arrivecode");
		Integer people = Integer.parseInt(request.getParameter("people"));
		Integer bags = Integer.parseInt(request.getParameter("bags"));
		
		//calling all the APIs
		InputStream fSchedule = APIAccess.fSchedule(todaydate, departureDate, dCode, aCode);
		InputStream fStatus = APIAccess.fStatus(fNumber, departureDate);
		InputStream wait = APIAccess.tsa("atl");
		InputStream weather = APIAccess.weather(dCode);
		
		//converting InputStreams to Strings
		String fScheduleString = ParseStream.parse(fSchedule);
		String fStatusString = ParseStream.parse(fStatus);
		String waitString = ParseStream.parse(wait);
		String weatherString = ParseStream.parse(weather);
		
		if(!(fScheduleString.contains("error") || fStatusString.contains("error")
				|| waitString.contains("error") || weatherString.contains("error"))){//input validation
			//Parsing Strings
			double duration = ParseStream.findDuration(fScheduleString);
			boolean delayed = ParseStream.findDelayed(fStatusString);
			String arrival = ParseStream.findArrival(fStatusString);
			String departure = ParseStream.findDeparture(fStatusString);
			double waitTime = ParseStream.findWait(waitString);
			double weatherTime = ParseStream.findWeather(weatherString);
			
			//calculating prep times
			double planning = 0; //amount of time in hours before depart time you should leave house
			if(delayed){
				planning += 1;
			}
			planning += waitTime;
			planning += weatherTime;
			planning += (people*10)/60.0;
			planning += 1.5; //difference between boarding and take off
			
			//calculating absolute times
			double absDepart = (Integer.parseInt(departure.substring(0, 2))*60)
					+ Integer.parseInt(departure.substring(3,5));
			double tempFinal = absDepart - planning;
			//converting back into time format
			int finalHour = (int)(tempFinal/60);
			int finalMinutes = (int)(tempFinal-(finalHour*60));
			String finalTime = finalHour + ":" + finalMinutes;
			//outputting to the user
			
		}
		else{ //print error page
			out.println("<!DOCTYPE HTML>"
			+"<HTML LANG=\"EN\">"
			+"<HEAD>"
			+"<TITLE> Error Page </TITLE>"
			+"</HEAD>"
			+"<BODY STYLE=\"font-family:Courier\">"
			+"<H2 ALIGN=\"center\">The information that you input was not valid. Please try again.</H2><BR>"
			+"<A HREF=\"FlytymeHTML.html\">Previous Page</A>"
			+"</BODY>"
			+"</HTML>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

