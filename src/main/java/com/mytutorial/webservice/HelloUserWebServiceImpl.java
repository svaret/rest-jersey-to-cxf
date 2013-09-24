package com.mytutorial.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.Response.status;

@Path("userservice")
@Produces({APPLICATION_JSON, APPLICATION_XML})
public class HelloUserWebServiceImpl {
    @GET
    @Path("/user/{userName}")
    public UserXml greetUser(@PathParam("userName") String userName) {
        UserXml user = new UserXml();
        user.setName(userName);
        return user;
    }

    @PUT
    @Path("/user")
    @Consumes({APPLICATION_JSON, APPLICATION_XML})
    public Response createUser(User user) {
        String result = "PUT User created : " + user.getName();
        return status(201).entity(result).build();
    }

    @POST
    @Path("/user")
    @Consumes(APPLICATION_JSON)
    public Response updateUser(User user) {
        String result = "POST User updated : " + user.getName();
        return status(201).entity(result).build();
    }

    @PUT
    @Path("/track")
    @Consumes(APPLICATION_JSON)
    public Response updateTrackInJSON(Track track) {
        String result = "PUT Track created : " + track;
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/track")
    @Consumes(APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {
        String result = "POST Track updated : " + track;
        return Response.status(201).entity(result).build();
    }
}