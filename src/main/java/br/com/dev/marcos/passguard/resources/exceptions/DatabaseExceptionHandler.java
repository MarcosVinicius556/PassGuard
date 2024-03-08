package br.com.dev.marcos.passguard.resources.exceptions;

import br.com.dev.marcos.passguard.resources.UserResource;
import br.com.dev.marcos.passguard.services.exception.DatabaseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DatabaseExceptionHandler implements ExceptionMapper<DatabaseException> {

	@Override
	public Response toResponse(DatabaseException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new StandardException.Builder()
						 .setMessage(exception.getMessage())
						 .setPath(UserResource.class.getName())
						 .setRequestStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode()).build())
                .build();
	}

}
