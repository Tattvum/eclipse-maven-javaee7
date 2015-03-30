package org.example.server;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/person")
public class PersonService {

  @EJB
  PersonDao personDao;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> retrieve() {
    return personDao.retrieve();
  }
  
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Person retrieve(@PathParam("id") Integer id) {
	  return personDao.retrieve(id);
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Person create(Person person) {
	  person.setId(0);
	  return personDao.create(person);
  }  

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Person update(@PathParam("id") Integer id, Person person) {
    return personDao.update(person);
  }
  
  @DELETE
  @Path("/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public void delete(@PathParam("id") Integer id) {
    personDao.delete(id);
  }

  private Person copy(Person person) {
	  Person p = new Person();
	  p.setUsername(person.getUsername());
	  p.setPasshash(person.getPasshash());
	  p.setSalt(person.getSalt());
	  return p;
  }
}