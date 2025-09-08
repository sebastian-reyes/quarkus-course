package com.sreyes;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

@Path("/extension-info")
public class ExtensionInfoApi {

  @Inject
  ExtensionInfo extensionInfo;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Set<JsonObject> extensionInfo() {
    return extensionInfo.doSomething().stream()
        .map(extension -> Json.createObjectBuilder()
            .add("id", extension.id)
            .add("name", extension.name)
            .add("shortName", extension.shortName)
            .add("keywords", String.join(", ", extension.keywords))
            .build())
        .collect(java.util.stream.Collectors.toSet());
  }
}
