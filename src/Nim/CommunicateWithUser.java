package Nim;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommunicateWithUser {
	
	private static Scanner scanner = new Scanner(System.in);
	private static PrintStream printer = System.out;
	
	private CommunicateWithUser(){
		
	}
	
	public static void println(String s){
		printer.println(s);
	}
	
	public static void print(String s){
		printer.print(s);
		
	}

	public static int getRangedIntegerAnswer(String prompt, int low, int high){
		int answer = 0;
		boolean hasntAnsweredCorrectly = true;
		print(prompt);
		do {
			Pattern regexNumberConfirmer = Pattern.compile("[\\d]+");
			String input = scanner.next();
			Matcher matchString = regexNumberConfirmer.matcher(input);
			Boolean onlyNumbers = true;
			for(int i = 0; i < input.length(); i++){
				if(onlyNumbers){
					onlyNumbers = matchString.find();
				}
			}
			if(onlyNumbers){
				answer = Integer.parseInt(input);
				if(answer >= low && answer <= high){
					hasntAnsweredCorrectly = false;
				}
				else{
					println("Option not availiable");
				}
			}
			else{
				println("Please input a number");
			}
		} while(hasntAnsweredCorrectly);
		return answer;
	}
	
	/*
	 * 
			try{
				String input = scanner.next();
				answer = Integer.parseInt(input);
				if(answer >= low && answer <= high){
					hasntAnsweredCorrectly = false;
				}
				else{
					println("Option not availiable");
				}
			} catch(NumberFormatException nfe){
				println("Please input a number");
			}
	 * 
	 * 
	 */
}
