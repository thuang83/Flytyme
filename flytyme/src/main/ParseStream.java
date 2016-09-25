package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.*;

public class ParseStream {
	public static String parse(InputStream input){
		java.util.Scanner temp = new java.util.Scanner(input).useDelimiter("\\A");
		return temp.hasNext() ? temp.next() : "";
		/*String result = new BufferedReader(new InputStreamReader(input))
				  .lines().collect(Collectors.joining("\n"));
		return result;*/
	}

	public static double findDuration(String input){
		int temp = input.indexOf("accumulatedTime") + 19;//quote
		String hours = input.substring(temp+1, temp+3);
		String minutes = input.substring(temp+4, temp+6);
		double time = Integer.parseInt(hours) + (Integer.parseInt(minutes)/60.0);
		return time;
	}
	
	public static boolean findDelayed(String input){
		if(input.contains("On Time")){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static String findArrival(String input){
		int temp = input.indexOf("arrivalLocalTimeScheduled") + 39;//quote
		String hours = input.substring(temp+1, temp+3);
		String minutes = input.substring(temp+4, temp+6);
		return hours + ":" + minutes;
	}
	
	public static String findDeparture(String input){
		int temp = input.indexOf("departureLocalTimeScheduled") + 41;//quote
		String hours = input.substring(temp+1, temp+3);
		String minutes = input.substring(temp+4, temp+6);
		return hours + ":" + minutes;
	}
	
	public static double findWait(String input){
		int temp = input.indexOf("waitTime") + 11; //quote
		String minutes = input.substring(temp+4, temp+6);
		double hours = Integer.parseInt(minutes)/60.0;
		return hours;
	}
	public static double findWeather(String input){
		if(input.contains("Snow")){
			return 1;
		}
		else if(input.contains("Rain") || input.contains("Storm") ||
				input.contains("Thunder") || input.contains("Lightning")){
			return 0.5;
		}
		else{
			return 0;
		}
	}
}

