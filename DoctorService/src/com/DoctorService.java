package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Doctor;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/doctors")
public class DoctorService
{
Doctor doctorObj = new Doctor();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readDoctor()
{
return doctorObj.readDoctor();
} 

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("doctorCode") String doctorCode,
 @FormParam("doctorNic") String doctorNic,
 @FormParam("doctorName") String doctorName,
 @FormParam("doctorDesc") String doctorDesc)
{
 String output = doctorObj.insertdoctor(doctorCode,doctorNic,doctorName,doctorDesc);
return output;
} 


@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatedoctor(String DoctorData)
{
//Convert the input string to a JSON object
 JsonObject itemObject = new JsonParser().parse(DoctorData).getAsJsonObject();
//Read the values from the JSON object
 String doctorID = itemObject.get("doctorID").getAsString();
 String doctorCode = itemObject.get("doctorCode").getAsString();
 String doctorNic = itemObject.get("doctorNic").getAsString();
 String doctorName = itemObject.get("doctorName").getAsString();
 String doctorDesc = itemObject.get("doctorDesc").getAsString();
 String output = doctorObj.updatedoctor(doctorID,doctorCode,doctorNic,doctorName,doctorDesc);
return output;
} 

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletedoctor(String doctorData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String doctorID = doc.select("doctorID").text();
 String output = doctorObj.deletedoctor(doctorID);
return output;
}
}
	