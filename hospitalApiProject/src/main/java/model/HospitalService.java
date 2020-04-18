package model;

import javax.json.JsonObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import com.healthCare.project.hospitalApiProject.Hospital;
import com.mysql.cj.xdevapi.JsonParser;

public class HospitalService {
	Hospital hospitalobj = new Hospital();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospital()
	 {
	 return hospitalobj.readHospital();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Hospital_name") String hospital_name,
	 @FormParam("Hospital_address") String hospital_address,
	 @FormParam("Hospital_phoneNo") String hospital_phoneNo)
	 
	
	{
	 String output = hospitalobj.insertHospital(hospital_name, hospital_address, hospital_phoneNo);
	return output;
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String hospitalData)
	{
	//Convert the input string to a JSON object
	 JsonObject hospitalobject = new JsonParser().parse(hospitalData).getAsJsonObject();
	//Read the values from the JSON object
	 String AppointmentID = hospitalobject.get("Hospital ID").getAsString();
	 String Number = hospitalobject.get("Number").getAsString();
	 String Date = hospitalobject.get("Date").getAsString();
	 String description = hospitalobject.get("description").getAsString();
	 String Time = hospitalobject.get("Time").getAsString();
	 String Type = hospitalobject.get("Type").getAsString();
	
	 
	 String output = hospitalobj.updateHospital(Hospital_Id,Hospital_name,Hospital_address,Hospital_phoneNo);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(hospitalData, " ", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String AppointmentID = doc.select("Hospital ID").text();
	 String output = hospitalobj.deleteHospital(Hospital_Id);
	return output;
	}
	
	
}
