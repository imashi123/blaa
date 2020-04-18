package com.healthCare.project.hospitalApiProject;

import java.sql.*;
public class Hospital {
	
	public Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("Jdbc:mysql://127.0.0.1:3306/hospital_management","root","");
			System.out.print("Successfully connected"); 
			
		}
		 catch(Exception e)
		 {
		 e.printStackTrace();
		 }

		 return con; 
			
		}
	public String insertHospital(String hospital_name, String hospital_address, String hospital_phoneNo)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into hospitals(`Hospital_Id`,`Hospital_name`,`Hospital_address`,`Hospital_phoneNo`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, hospital_name);
	 preparedStmt.setString(3, hospital_address);
	 preparedStmt.setString(4, hospital_phoneNo);
	
	
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }


	public String readHospital()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr>Hospital ID<th> Hospital Name </th><th> Hospital Address </th><th> Hospital Phone No </th></tr>";
	 String query = "select * from hospitals";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String Hospital_Id = Integer.toString(rs.getInt("Hospital_Id"));
		 String Hospital_name = rs.getString("Hospital_name");
		 String Hospital_address = rs.getString("Hospital_address");
		 String Hospital_phoneNo = rs.getString("");		
		
		
	 // Add into the html table
		 output += "<tr><td>" + Hospital_name + "</td>";
		 output += "<td>" + Hospital_address + "</td>";
		 output += "<td>" + Hospital_phoneNo + "</td>";
		
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"AppointmentID\" type=\"hidden\" value=\"" + Hospital_Id
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String updateHospital(String Hospital_Id, String Hospital_name, String Hospital_address, String Hospital_phoneNo)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE Hospital SET Hospital Name=?,Hospital Address=?,Hospital Phone No=?,Type=?WHERE Hospital ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Hospital_name);
	 preparedStmt.setString(2, Hospital_address);
	 preparedStmt.setString(3, Hospital_phoneNo);
	
	 preparedStmt.setInt(6,Integer.parseInt(Hospital_Id));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String deleteHospital(String Hospital_Id)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from hospitals where Hospital ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(Hospital_Id));
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


