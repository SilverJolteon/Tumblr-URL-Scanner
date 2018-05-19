package com.SilverJolteon.urlscanner;

import java.net.*;
import java.io.*;

class HTTPStatus{	
	public static int code;

	public HTTPStatus(){
		code = -1;
	}
	
	public int getCode(String url)throws MalformedURLException, IOException{
		return getResponseCode(url);
	}
	
	private static int getResponseCode(String urlString)throws MalformedURLException, IOException{
		URL u = new URL(urlString); 
		HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
		huc.setRequestMethod("GET"); 
		huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		huc.connect(); 
		return huc.getResponseCode();
	}
	
	private static boolean getPostNumber(String urlString)throws MalformedURLException, IOException{
		URL u = new URL(urlString);
		int line = 1;
        BufferedReader in = new BufferedReader(
        new InputStreamReader(u.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            if(line == 88){
				return 0 < ((inputLine.charAt(inputLine.indexOf("Posts")-2)) - '0');
			}
			line++;
		}
        in.close();
		return false;
	}
}