/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reliers.Crawlers.Java;

/**
 *
 * @author reliers
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.*;
import java.io.*;

public class WikiScraper {
    public static void main(String args[]){
        scrapeTopic("/wiki/Python");
    }
    public static void scrapeTopic(String url){
        String html =getUrl("https://en.wikipedia.org"+url);
        Document doc = Jsoup.parse(html);
        String contentText = doc.select("title").first().text();
        System.out.println(contentText);
    }
    public static String getUrl(String url){
        URL urlObj = null;
        try{
            urlObj = new URL(url);
        } catch (MalformedURLException e){
            System.out.println("The URL was malformed");
            return "";
        }
        
        URLConnection urlCon = null;
        BufferedReader in = null;
        String outputText = "";
        try{
            urlCon = urlObj.openConnection();
            in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            
            String line = "";
            while((line = in.readLine())!= null){
                outputText += line;
            }
            in.close();
        }catch(IOException e){
            System.out.println("There was an error connecting to the URL.");
            return "";
        }
        return outputText;
               
        
    }
}
