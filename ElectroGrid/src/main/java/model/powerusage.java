package model;

import java.sql.*;

public class powerusage {
	
	//A common method to connect to the DB
	
			private Connection connect()
			 {
				 Connection con = null;
				 try
				 {
					 Class.forName("com.mysql.jdbc.Driver");
				
					 //Provide the correct details: DBServer/DBName, username, password
					 
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "ndksliit");
				 }
					 catch (Exception e)
					 {e.printStackTrace();}
					 return con;
			 }
					public String insertPowerusage(String pwrusagedataID, String username, String useraddress, String noofpwrunits, String unitprice, String totalprice, String payedmonth)
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for inserting."; }
					 
					 // create a prepared statement
					 
					 String query = " insert into pwrusagedata (`pwrusagedataID`,`username`,`useraddress`,`noofpwrunits`,`unitprice`,`totalprice`,`payedmonth`)" + " values (?, ?, ?, ?, ?, ?, ?)";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, pwrusagedataID);
					 preparedStmt.setString(3, username);
					 preparedStmt.setString(4, useraddress);
					 preparedStmt.setString(5, noofpwrunits);
					 preparedStmt.setString(6, unitprice);
					 preparedStmt.setString(7, totalprice);
					 preparedStmt.setString(8, payedmonth);
					 
					 
					 
					 // execute the statement
					 
					 preparedStmt.execute();
					 con.close();
					 output = "Inserted successfully";
				 }
				 catch (Exception e)
				 {
					 output = "Inserted not successfully";
					 System.err.println(e.getMessage());
				 }
				 return output;
			}
				public String readPowerusage()
				 {
					String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for reading."; }
						 
						 // Prepare the html table to be displayed
						 
						 output = "<table border='1'><tr><th>Power Usage Data ID</th>" +"<th>Customer Name</th>" +
								 "<th>Customer Address</th>" +"<th>Number Of Power Units Used</th>" +"<th>One Unit Price (RS)</th>" +
								 "<th>Total Bill Amount</th>" +"<th>Bill Payed Month</th>" +"<th>Update</th>"+"<th>Delete</th></tr>";
					
						 String query = "select * from pwrusagedata";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 
						 // iterate through the rows in the result set
						 
						 while (rs.next())
						 {
							 String recordID = Integer.toString(rs.getInt("recordID"));
							 String KpwrusagedataID = rs.getString("pwrusagedataID");
							 String Kusername = rs.getString("username");
							 String Kuseraddress = rs.getString("useraddress");
							 String Knoofpwrunits = rs.getString("noofpwrunits");
							 String Kunitprice = rs.getString("unitprice");
							 String Ktotalprice = rs.getString("totalprice");
							 String Kpayedmonth = rs.getString("payedmonth");
							 
				
							 
							 // Add into the html table
							 
							 
							 output += "<td>" + KpwrusagedataID + "</td>";
							 output += "<td>" + Kusername + "</td>";
							 output += "<td>" + Kuseraddress + "</td>";
							 output += "<td>" + Knoofpwrunits + "</td>";
							 output += "<td>" + Kunitprice + "</td>";
							 output += "<td>" + Ktotalprice + "</td>";
							 output += "<td>" + Kpayedmonth + "</td>";
							 
							 
							 
							 // buttons
							 
							 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='powerusage.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='id' type='hidden' value='" + recordID + "'>" + "</form></td></tr>";
						 }
						 con.close();
						 
						 // Complete the html table
						 
						 output += "</table>";
					 }
					 catch (Exception e)
					 {
						 output = "Error while reading the .";
						 System.err.println(e.getMessage());
					 }
					 return output;
				 }
			
			//update section
				public String updatePowerusage(String recordID, String pwrusagedataID, String username, String useraddress, String noofpwrunits, String unitprice, String totalprice, String payedmonth)
				 {
					 String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for updating."; }
						 
						 // create a prepared statement
						 
						 String query = "UPDATE pwrusagedata SET KpwrusagedataID=?,Kusername=?,Kuseraddress=?,Knoofpwrunits=?,Kunitprice=?,Ktotalprice=?,Kpayedmonth=?WHERE recordID=?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 
						 preparedStmt.setInt(1, Integer.parseInt(recordID));
						 preparedStmt.setString(2, pwrusagedataID);
						 preparedStmt.setString(3, username);
						 preparedStmt.setString(4, useraddress);
						 preparedStmt.setString(5, noofpwrunits);
						 preparedStmt.setString(6, unitprice);
						 preparedStmt.setString(7, totalprice);
						 preparedStmt.setString(8, payedmonth);
						 
						 
						 // execute the statement
						 
						 preparedStmt.execute();
						 con.close();
						 output = "Updated successfully";
					 }
					 catch (Exception e)
					 {
						 output = "Error while updating the payment.";
						 System.err.println(e.getMessage());
					 }
					 return output;
				}
				
			// delete operation
				public String deletePowerusage(String recordID)
				{
					 String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for deleting."; }
						 // create a prepared statement
						 String query = "delete from pwrusagedata where recordID=?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 // binding values
						 preparedStmt.setInt(1, Integer.parseInt(recordID));
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 output = "Deleted successfully";
					 }
					 catch (Exception e)
					 {
						 output = "Error while deleting the item.";
						 System.err.println(e.getMessage());
					 }
					 return output;
				 }

}
