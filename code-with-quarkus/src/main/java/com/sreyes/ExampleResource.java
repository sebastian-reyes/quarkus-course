package com.sreyes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class ExampleResource {

  @ConfigProperty(name = "greeting")
  String greeting;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from Quarkus REST";
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/custom/{name}")
  public String helloCustom(@PathParam("name") String name) {
    return String.format("%s %s from Quarkus, testing Hot Reload!", greeting, name);
  }
}
