package com.sreyes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;

@ApplicationScoped
public class ExtensionInfo {

  @Inject
  @RestClient
  PersonRestClient personRestClient;

  public Set<PersonRestClient.Extension> doSomething() {
    Set<PersonRestClient.Extension> restClientExtensions = personRestClient.getExtensionsById("io.quarkus:quarkus-hibernate-validator");
    restClientExtensions.forEach(extension -> {
      System.out.println("Extension ID: " + extension.id);
      System.out.println("Extension Name: " + extension.name);
      System.out.println("Extension Short Name: " + extension.shortName);
      System.out.println("Extension Keywords: " + String.join(", ", extension.keywords));
    });
    return restClientExtensions;
  }
}
