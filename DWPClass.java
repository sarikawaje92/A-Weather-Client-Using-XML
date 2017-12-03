/*
Name: Sarika Mangesh Waje
UTA id: 1001266527
*/

import java.io.*;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Scanner;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.CharacterData;

import javax.xml.rpc.ServiceException;

import gov.weather.graphical.xml.DWMLgen.wsdl.ndfdXML_wsdl.*;
public class DWPClass {

	public static void main(String[] args) throws RemoteException, ServiceException {
		// TODO Auto-generated method stub
		
		//Scanner object to take input from user
		Scanner sc = new Scanner(System.in);
		
		//Creating object of class NdfdXMLPortType to access method NDFDgen()
		NdfdXMLPortType ndfdXMLPortType = new NdfdXMLLocator().getndfdXMLPort();
		
		//Taking user input for latitude and longitude
		System.out.println("Enter latitude:");
		double lat=sc.nextDouble();
		System.out.println("Enter longitude:");
		double lon=sc.nextDouble();
		
		//Converting latitude and longitude to type BigDecimal to be passed as parameter in method NDFDgen()
		BigDecimal latitude= new BigDecimal(lat);
		BigDecimal longitude= new BigDecimal(lon);
		
		//Setting product string to "time-series" to be passes as argument in method NDFDgen()
		String product = "time-series";
		
		//startTime and endTime are of type Calendar as to be passed as arguments in method NDFDgen()
		Calendar startTime = Calendar.getInstance();
		startTime.set(2014, 01,01);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2021, 05,02);
		
		//setting unit string to "e" to be passed as argument in method NDFDgen()
		String unit ="e";
		
		//The 4 parameters to be retrieved in xml are set as true. i.e. 12 Hour Probability of Precipitation, Cloud Cover Amount, Wind Speed, Wind Direction
		WeatherParametersType weatherParameters= new WeatherParametersType(false,false,false,false,true,false,true,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
		
				
		String refresh="yes";
		
		//do-while loop for reconnecting on refresh
		do
		{	
		//Calling the NDFSgen() method by passing above parameters in it
		//The XML returned in stored in String res.
		String res=ndfdXMLPortType.NDFDgen(latitude, longitude, product , startTime  , endTime, unit , weatherParameters);
		
		//--------XML parsing ---------------
		//Reference: http://www.rgagnon.com/javadetails/java-0573.html
		
		try {
			//creating object of DocumentBuilderFactory to obtain parser for producing DOM object trees from xml
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(res));

	        Document doc = db.parse(is);
	        //get the xml tag "wind-speed" in NodeList 
	        NodeList nodes = doc.getElementsByTagName("wind-speed");

	        // iterate the wind speed
	        for (int i = 0; i < nodes.getLength(); i++) 
	        {
	           Element element = (Element) nodes.item(i);
	           System.out.println("\nCurrent Element :" + element.getNodeName());
	           NodeList name = element.getElementsByTagName("value");
	           //iterate through all the values having tag "value" under "wind-speed" tag
	           for(int j=0;j<name.getLength();j++)
	           {
	        	   Element line = (Element) name.item(j);
	        	   System.out.println(getCharacterDataFromElement(line));
	           }
	        }
	        
	        //get the xml tag "probability-of-precipitation" in NodeList
	        nodes = doc.getElementsByTagName("probability-of-precipitation");
	        
	        //iterate the probability-of-precipitation
	        for (int i = 0; i < nodes.getLength(); i++) 
	        {
	           Element element = (Element) nodes.item(i);
	           System.out.println("\nCurrent Element :" + element.getNodeName());
	           //iterate through all the values having tag "value" under "probability-of-precipitation" tag
	           NodeList name = element.getElementsByTagName("value");
	           for(int j=0;j<name.getLength();j++)
	           {
	        	   Element line = (Element) name.item(j);
	        	   System.out.println(getCharacterDataFromElement(line));
	           }
	        }
	        
	        //get the xml tag "direction" in NodeList
	        nodes = doc.getElementsByTagName("direction");
	        
	        //iterate the direction
	        for (int i = 0; i < nodes.getLength(); i++) 
	        {
	           Element element = (Element) nodes.item(i);
	           System.out.println("\nCurrent Element :" + element.getNodeName());
	           NodeList name = element.getElementsByTagName("value");
	           //iterate through all the values having tag "value" under "direction" tag
	           for(int j=0;j<name.getLength();j++)
	           {
	        	   Element line = (Element) name.item(j);
	        	   System.out.println(getCharacterDataFromElement(line));
	           }
	        }
	        
	        //get the xml tag "cloud-amount" in NodeList
	        nodes = doc.getElementsByTagName("cloud-amount");
	        
	        //iterate the cloud-amount
	        for (int i = 0; i < nodes.getLength(); i++) 
	        {
	           Element element = (Element) nodes.item(i);
	           System.out.println("\nCurrent Element :" + element.getNodeName());
	           NodeList name = element.getElementsByTagName("value");
	           //iterate through all the values having tag "value" under "coud-amount" tag
	           for(int j=0;j<name.getLength();j++)
	           {
	        	   Element line = (Element) name.item(j);
	        	   System.out.println(getCharacterDataFromElement(line));
	           }
	        }
	        
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		//ask if user wants to refresh
		System.out.println("Do you want to refresh?(yes/no)");
		refresh=sc.next();
		}
		while(refresh.equals("yes"));
		
	}
	
	//converts Element type and returns String
	public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return "?";
	  }


}
