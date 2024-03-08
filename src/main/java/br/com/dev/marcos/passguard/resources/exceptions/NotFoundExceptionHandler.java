package br.com.dev.marcos.passguard.resources.exceptions;

import br.com.dev.marcos.passguard.resources.UserResource;
import br.com.dev.marcos.passguard.services.exception.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND)
                .entity(new StandardException.Builder()
						 .setMessage(exception.getMessage())
						 .setPath(UserResource.class.getName())
						 .setRequestStatus(Status.NOT_FOUND.getStatusCode()).build())
                .build();
	}

}
