//package main2;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        return Response.ok().entity(p).header("Access-Control-Allow-Origin", "*")
                .build();
	    //return parcelsize;
	}
}
