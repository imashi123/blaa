package com;


	
	import model.Payment;
	//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
	//For XML 
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;

	@Path("/Payments")
	public class PaymentService {
		Payment paymentObj = new Payment();

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPayments() {
			//return paymentObj.readPayments();
			return "hello";
		}
		
	/*	@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("paymentCode") String paymentCode,
		 @FormParam("paymentType") String paymentType,
		 @FormParam("paymentAmount") String paymentAmount,
		 @FormParam("paymentDesc") String paymentDesc)
		{
		 String output = paymentObj.insertPayments(paymentCode, paymentType, paymentAmount, paymentDesc);
		return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
		 String paymentID = paymentObject.get("paymentID").getAsString();
		 String paymentCode = paymentObject.get("paymentCode").getAsString();
		 String paymentType = paymentObject.get("paymentType").getAsString();
		 String paymentAmount = paymentObject.get("paymentAmount").getAsString();
		 String paymentDesc = paymentObject.get("paymentDesc").getAsString();
		 String output = paymentObj.updatePayments(paymentID, paymentCode, paymentType, paymentAmount, paymentDesc);
		return output;
		} 
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		//Read the value from the element <paymentID>
		 String paymentID = doc.select("paymentID").text();
		 String output = paymentObj.deletePayment(paymentID);
		return output;
		}


	*/


}
