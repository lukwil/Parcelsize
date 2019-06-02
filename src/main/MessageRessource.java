package main;//package main2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("parcelsize")
public class MessageRessource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("length/{length}/width/{width}/height/{height}")
	public Response message(@PathParam("length") String length,
							@PathParam("width") String width,
							@PathParam("height") String height) {
		Parcelsize parcelsize = new Parcelsize(length, width, height, "");
		parcelsize.calculateSize();
		return Response.ok().entity(parcelsize)
                .build();
	}
}
