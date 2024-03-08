package br.com.dev.marcos.passguard.resources;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dev.marcos.passguard.dto.UserDTO;
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

@Path("/users")
public class UserResource {

private UserService service;
	
	private UserService getService() {
		if(service == null)
			service = ServiceFactory.createUserService();
		
		return service;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ResponseBuilder response = null;
		try {
			List<User> users = getService().findAll(new User());
			response = Response.ok();
			response.entity(users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		ResponseBuilder response = null;
		try {
			User user = getService().findById(new User.Builder().setId(id).build());
			response = Response.ok();
			response.entity(new UserDTO(user));
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(User user) {
		ResponseBuilder response = null;
		try {
			user = getService().save(user);
			response = Response.ok();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		
		return response.build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		ResponseBuilder response = null;
		try {
			getService().delete(new User.Builder().setId(id).build());
			response = Response.ok();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, User newUser) {
		ResponseBuilder response = null;
		try {
			newUser.setId(id);
			getService().update(newUser);
			response = Response.ok();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		
		return response.build();
	}
	
}
