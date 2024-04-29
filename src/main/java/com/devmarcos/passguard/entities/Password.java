package com.devmarcos.passguard.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "tb_password", indexes = @Index( columnList = "id", unique = true ) )
public class Password implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "tb_password_id_sequence" )
	@SequenceGenerator( name = "tb_password_id_sequence", sequenceName = "tb_password_id_sequence", allocationSize = 1 )
	private Long id;

    @Column( name = "name", length = 64, nullable = false )
	private String name;
	
	@Column( name = "description", length = 128, nullable = true )
	private String description;
	
	@Column( name = "password", length = 32, nullable = false )
	private String password;
	
	@ManyToOne( fetch =  FetchType.EAGER )
	@JoinColumn( name = "userid" )
	private User user;
	
	public static class Builder {
		
		private Long id;
		private String name;
		private String description;
		private String password;
		private User user;
		
		public Builder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setUser(User user) {
			this.user = user;
			return this;
		}
		
		public Password build() {
			return new Password(this);
		}
		
	}
	
	public Password() {
		
	}
	
	public Password(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.password = builder.password;
		this.user = builder.user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		return Objects.equals(id, other.id);
	}
    
}