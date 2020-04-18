package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayments(String code, String type, String amount, String desc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into items(`paymentID`,`paymentCode`,`paymentType`,`paymentAmount`,`paymentDesc`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, type);
	 preparedStmt.setDouble(4, Double.parseDouble(amount));
	 preparedStmt.setString(5, desc);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String readPayments() 
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Payment Code</th><th>Payment Type</th><th>Payment Amount</th><th>PaymentDescription</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from payments";
	 Statement stmt = (Statement) con.createStatement();
	 ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String paymentID = Integer.toString(rs.getInt("paymentID"));
	 String paymentCode = rs.getString("paymentCode");
	 String paymentType = rs.getString("paymentType");
	 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
	 String paymentDesc = rs.getString("paymentDesc");
	 // Add into the html table
	 output += "<tr><td>" + paymentCode + "</td>";
	 output += "<td>" + paymentType + "</td>";
	 output += "<td>" + paymentType+ "</td>";
	 output += "<td>" + paymentDesc + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
	 + "<td><form method=\"post\" action=\"payments.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"paymentID\" type=\"hidden\" value=\"" + paymentID
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
	public String updatePayments(String ID, String code, String type, String amount, String desc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE items SET paymentCode=?,paymentType=?,paymentAmount=?,paymentDesc=? WHERE paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setString(1, code);
	 preparedStmt.setString(2, type);
	 preparedStmt.setDouble(3, Double.parseDouble(amount));
	 preparedStmt.setString(4, desc);
	 preparedStmt.setInt(5, Integer.parseInt(ID));
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
	public String deletePayment(String paymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from items where paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID));
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
