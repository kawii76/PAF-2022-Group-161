package com;

import model.User; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserService {

	User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	 {
		return userObj.readUsers(); 
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("name") String name, 
			
	 @FormParam("address") String address, 
	 @FormParam("account") String account, 
	 @FormParam("phone") String phone) 
	{ 
		String output = userObj.insertUser( name, address, account,phone ); 
	 	return output; 
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String id = userObject.get("id").getAsString();
	 String name = userObject.get("name").getAsString();
	 String address = userObject.get("address").getAsString();
	 String account = userObject.get("account").getAsString();
	 String phone = userObject.get("phone").getAsString();
	 String output = userObj.updateUser(id, name, address, account, phone);
	 return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = userObj.deleteUser(id);
	return output;
	}
}
