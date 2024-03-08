package br.com.dev.marcos.passguard.resources.exceptions;

import java.time.LocalDateTime;

public class StandardException {

	private String instant;
	private String path;
	private String message;
	private int requestStatus;
	
	public static class Builder {
	
		private String path;
		private String message;
		private int requestStatus;
		
		public Builder setPath(String path) {
			this.path = path;
			return this;
		}
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}
		public Builder setRequestStatus(int requestStatus) {
			this.requestStatus = requestStatus;
			return this;
		}
		
		public StandardException build() {
			return new StandardException(this);
		}
		
	}
	
	public StandardException(Builder builder) {
		this.instant = LocalDateTime.now().toString();
		this.path = builder.path;
		this.message = builder.message;
		this.requestStatus = builder.requestStatus;
	}
	public String getInstant() {
		return instant;
	}
	public void setInstant(String instant) {
		this.instant = instant;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	
}
