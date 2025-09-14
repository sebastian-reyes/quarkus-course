package com.sreyes.controller;

import com.sreyes.model.Contact;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.util.List;

@Path("/api/contacts")
@Consumes("application/json")
@Produces("application/json")
public class ContactController {

  @Inject
  EntityManager em;

  @GET
  public Response getAllContacts() {
    List<Contact> contacts = em.createQuery("from Contact", Contact.class).getResultList();
    return Response.ok(contacts).build();
  }

  @GET
  @Path("/{id}")
  public Response getContact(@PathParam("id") Long id) {
    Contact contact = em.find(Contact.class, id);
    if (contact == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(contact).build();
  }

  @POST
  @Transactional
  public Response createContact(Contact contact) {
    em.persist(contact);
    return Response.status(Response.Status.CREATED).entity(contact).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateContact(@PathParam("id") Long id, Contact contact) {
    Contact existing = em.find(Contact.class, id);
    if (existing == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existing.setName(contact.getName());
    existing.setEmail(contact.getEmail());
    em.merge(existing);
    return Response.ok(existing).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteContact(@PathParam("id") Long id) {
    Contact contact = em.find(Contact.class, id);
    if (contact == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    em.remove(contact);
    return Response.noContent().build();
  }
}