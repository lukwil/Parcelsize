package main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("parcelsize")
public class MessageRessource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response message(Parcelsize parcelsize) {
		Parcelsize p = new Parcelsize();
		p.setHeight(parcelsize.getHeight()); 
		p.setLength(parcelsize.getLength());
		p.setWidth(parcelsize.getWidth());
		p.calculateSize();
		System.out.println(p.toString());
		return Response.status(202).entity(p).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers",
						"origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods",
						"GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	    //return parcelsize;
	}
}
