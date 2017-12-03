# A-Weather-Client-Using-XML

A client application that will connect to the National Weather Service web site using HTTP and XML and/or SOAP and display current weather conditions.
The National Weather Service specifies the location using latitude and longitude instead of zip code. The user should be able to enter coordinates into the client program and get a current update for that location. This service is only updated hourly, so you should not request updates at short intervals. You should have a manual refresh button that will reconnect and retrieve the information again
Include at least four of the following variables in your display: Maximum Temperature, Minimum Temperature, Dew point Temperature, 12 Hour Probability of Precipitation, Cloud Cover Amount, Wind Speed, Wind Direction, Weather Icons, Wave Height.

Setup:
1)	Create “Java Project” in eclipse.
2)	Right click on project, create new “Web Service Client”. In Service definition enter wsdl url – “https://graphical.weather.gov/xml/SOAP_server/ndfdXMLserver.php?wsdl”. Click Finish.
3)	In the project, under src under Java Resources, “gov.weather.graphical.xml.DWMLgen.wsdl.ndfdXML_wsdl” package is created. In this package, few classes are created.
4)	Right click on project, create new class (DWPClass) in default package. (This contains the java code that is submitted) 
5)	Run the DWPClass class.

How to run:
1)	Run the DWPClass.java.
2)	Enter the latitude(eg.38.99) and longitude(eg.-77.01) in console.
3)	The values of 12 hour probability of precipitation, cloud cover amount, wind speed and wind direction are displayed in console.
4)	Enter “yes” if you want to continue else enter “no”.


References:
http://www.rgagnon.com/javadetails/java-0573.html
http://wiki.eclipse.org/Creating_a_Top-Down_Java_Web_Service_Skeleton_from_a_WSDL_Document
https://graphical.weather.gov/xml/
