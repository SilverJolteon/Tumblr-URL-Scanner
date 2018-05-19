package com.SilverJolteon.urlscanner;

import java.net.*;
import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class URLScanner{	

	public static int scrollAmount = 30, dV = 25; //Set spacing
	public static String title = "URL Finder v2.2.1";
	public static String[] urls = new String[0];
	public static String[] times = new String[0];
	public static int[] found = new int[0];
	
	public static void main(String[] args)throws MalformedURLException, IOException{
		DateFormat df = new SimpleDateFormat("MM/dd/YY - h:mm a");
		Date dateobj = new Date();
		HTTPStatus httpCheck = new HTTPStatus();
		GUI ui = new GUI(title, 400, 400);
		readList();
		
		boolean newUrl = false, noneFound = true;
		
		//Display
		ui.header(title, "center", 18, 18);
		ui.header("By SilverJolteon", "center", 42, 14);
		ui.header("Searching...", "center", 400-32, 14);
		
		//Main Loop
		while(true){
			if(!checkList()){
				readList();
				noneFound = true;
				ui.cls();
			}
			int vPos = -8, sum = 0;
			if(newUrl){
				ui.cls();
				for(int i = 0; i < urls.length; i++){
					if(found[i] == 1){
						vPos += dV;
						String name = String.format(" - %s", urls[i]);
						ui.print(name, 10, vPos, 16);
						ui.print(times[i], "right", vPos, 16);
						ui.scroll(vPos + scrollAmount);
						noneFound = false;
					}
					sum++;
				}
				if(sum == 0){
					noneFound = true;
				}
				newUrl = false;
				vPos += dV;
				ui.print("-------------------------------End of List------------------------------", 13, vPos, 12);
				ui.scroll(vPos + scrollAmount);
				ui.scroll(vPos + scrollAmount - 10);
			}
			for(int i = 0; i < urls.length; i++){
				int code = httpCheck.getCode("https://"+urls[i]+".tumblr.com");
				if(code == 404 && found[i] == 0){
					vPos += dV;
					found[i] = 1;
					times[i] = df.format(dateobj);
					String name = String.format(" - %s", urls[i]);
					ui.print(name, 10, vPos, 16);
					ui.print(times[i], "right", vPos, 16);
					ui.scroll(vPos + scrollAmount);
					newUrl = true;
					noneFound = false;
				}
				else if(code != 404 && found[i] == 1){
					found[i] = 0;
					times[i] = "";
					newUrl = true;
					noneFound = false;
				}
			}
			if(noneFound){
				vPos += dV;
				ui.print("None found!", 120, vPos, 16);
				ui.scroll(vPos + scrollAmount);
				noneFound = false;
			}
		}
		
	}
	
	public static void readList()throws MalformedURLException, IOException{
		//Read from list.txt
		File fp = new File("list.txt");
		fp.createNewFile();
		Scanner sc = new Scanner(fp);
		List<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()){
			String temp = sc.nextLine();
			temp = temp.toLowerCase();
			temp = Normalizer.normalize(temp, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			temp = temp.replaceAll("[^a-zA-Z0-9-]+","");
			temp = temp.replace(System.getProperty("line.separator"), "");
			if(!temp.equals("")){
				lines.add(temp);
			}
		}
		urls = lines.toArray(new String[0]);
		found = new int[urls.length];
		for(int i = 0; i < urls.length; i++){
			found[i] = 0;
		}
		times = new String[urls.length];
		for(int i = 0; i < urls.length; i++){
			times[i] = "";
		}
	}
	
	public static boolean checkList()throws MalformedURLException, IOException{
		File fp = new File("list.txt");
		fp.createNewFile();
		Scanner sc = new Scanner(fp);
		List<String> lines = new ArrayList<String>();
		while(sc.hasNextLine()){
			String temp = sc.nextLine();
			temp = temp.toLowerCase();
			temp = Normalizer.normalize(temp, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			temp = temp.replaceAll("[^a-zA-Z0-9-]+","");
			temp = temp.replace(System.getProperty("line.separator"), "");
			if(!temp.equals("")){
				lines.add(temp);
			}
		}
		String[] urlsTemp = lines.toArray(new String[0]);
		if(urls.length == urlsTemp.length){
			for(int i = 0; i < urls.length; i++){
				if(!urls[i].equals(urlsTemp[i])){
					return false;
				}
			}
			return true;
		} 
		return false;
	}
}