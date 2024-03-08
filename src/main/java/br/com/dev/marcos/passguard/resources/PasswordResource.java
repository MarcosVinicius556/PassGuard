package br.com.dev.marcos.passguard.resources;

import java.util.List;

import br.com.dev.marcos.passguard.entities.Password;
import br.com.dev.marcos.passguard.services.factory.ServiceFactory;
import br.com.dev.marcos.passguard.services.interfaces.PasswordService;
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

@Path("/passwords")
public class PasswordResource {

	private PasswordService service;
	
	private PasswordService getService() {
		if(service == null)
			service = ServiceFactory.createPassordService();
		
		return service;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ResponseBuilder response = null;
		try {
			List<Password> passwords = getService().findAll(new Password());
			response = Response.ok();
			response.entity(passwords);
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
			Password password = getService().findById(new Password.Builder().setId(id).build());
			response = Response.ok();
			response.entity(password);
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid Password password) {
		ResponseBuilder response = null;
		try {
			password = getService().save(password);
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
			getService().delete(new Password.Builder().setId(id).build());
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
	public Response update(@PathParam("id") Long id, @Valid Password newPassword) {
		ResponseBuilder response = null;
		try {
			newPassword.setId(id);
			getService().update(newPassword);
			response = Response.ok();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; //Joga a exceção para ser tratado pelos exceptions Handlers....
		}
		
		return response.build();
	}
	
}
