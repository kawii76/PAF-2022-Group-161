package com;

import model.powerusage;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/powerusage")

public class powerusageService {
	
	powerusage powerusageObj = new powerusage();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpowerusage()
	 {
		return powerusageObj.readPowerusage(); 
	 } 
	
	


	// insert Powerusage details
		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPowerusage(
		
		
		@FormParam("pwrusagedataID") String pwrusagedataID,
		@FormParam("username") String username,
		@FormParam("useraddress") String useraddress,
		@FormParam("noofpwrunits") String noofpwrunits,
		@FormParam("unitprice") String unitprice,
		@FormParam("totalprice") String totalprice,
		@FormParam("payedmonth") String payedmonth)
		
		

		
				{
					 String output = powerusageObj.insertPowerusage(pwrusagedataID,username,useraddress,noofpwrunits,unitprice,totalprice,payedmonth);
					 return output;
				}
		
		
		//update user details
				@PUT
				@Path("/update")
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.TEXT_PLAIN)
				public String updatePowerusage(String powerusageData)
				{
				   //Convert the input string to a JSON object
					JsonObject powerusageObject = new JsonParser().parse(powerusageData).getAsJsonObject();
									 
				    //Read the values from the JSON object		
						String recordID = powerusageObject.get("recordID").getAsString();
						String pwrusagedataID = powerusageObject.get("pwrusagedataID").getAsString();
						String username = powerusageObject.get("username").getAsString();
					    String useraddress = powerusageObject.get("useraddress").getAsString();
					    String noofpwrunits = powerusageObject.get("noofpwrunits").getAsString();
					    String unitprice = powerusageObject.get("unitprice").getAsString();
					    String totalprice = powerusageObject.get("totalprice").getAsString();
					    String payedmonth = powerusageObject.get("payedmonth").getAsString();
						
						String output = powerusageObj.updatePowerusage(recordID,pwrusagedataID,username,useraddress,noofpwrunits,unitprice,totalprice,payedmonth);
						return output;
				}
		
		
		//delete user details
		@DELETE
		@Path("/delete")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePowerusage(String powerusageData)
			{
			 //Convert the input string to an XML document
			 Document doc = Jsoup.parse(powerusageData, "", Parser.xmlParser());
						
			//Read the value from the element <idUnit>
			 String recordID = doc.select("recordID").text();
			 String output = powerusageObj.deletePowerusage(recordID);
			 return output;
			 
			}

}
