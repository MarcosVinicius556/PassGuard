package br.com.dev.marcos.passguard.resources;

import java.net.URI;
import java.util.List;

import br.com.dev.marcos.passguard.entities.User;
import br.com.dev.marcos.passguard.services.factory.ServiceFactory;
import br.com.dev.marcos.passguard.services.interfaces.UserService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

@Path("/users")
public class UserResource {

private UserService service;
	
	private UserService getService() {
		if(service == null)
			service = ServiceFactory.createUserService();
		
		return service;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ResponseBuilder response = null;
		List<User> users = getService().findAll(new User());
		if(users != null && users.size() > 0) {
			response = Response.ok();
			response.entity(users);
		} else {
			response = Response.noContent();
			response.status(Status.NOT_FOUND);
		}
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		ResponseBuilder response = null;
		User user = getService().findById(new User.Builder().setId(id).build());
		if(user != null) {
			response = Response.ok();
			response.entity(user);
		} else {
			response = Response.noContent();
			response.status(Status.NOT_FOUND);
		}
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid User user) {
		ResponseBuilder response = null;
		user = getService().save(user);
		if(user != null) {
			final URI userURI = UriBuilder.fromResource(UserResource.class).path("/users/{id}").build(user.getId());
			response = Response.created(userURI);
			response.entity(user);
		} else {
			response = Response.noContent();
			response.status(Status.INTERNAL_SERVER_ERROR);
		}
		return response.build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		ResponseBuilder response = null;
		User userToDelete = getService().findById(new User.Builder().setId(id).build());
		if(userToDelete != null) {
			try {
				getService().delete(userToDelete);
				response = Response.noContent();
			} catch (Exception e) {
				e.printStackTrace();
				response = Response.noContent();
				response.status(Status.INTERNAL_SERVER_ERROR);
			}
		} else {
			response = Response.noContent();
			response.status(Status.NOT_FOUND);
		}
		
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, @Valid User newUser) {
		ResponseBuilder response = null;
		User oldUser = getService().findById(new User.Builder().setId(id).build());
		if(oldUser != null) {
			try {
				newUser.setId(oldUser.getId());
				getService().update(newUser);
				response = Response.noContent();
			} catch (Exception e) {
				e.printStackTrace();
				response = Response.noContent();
				response.status(Status.INTERNAL_SERVER_ERROR);
			}
		} else {
			response = Response.noContent();
			response.status(Status.NOT_FOUND);
		}
		
		return response.build();
	}
	
}
