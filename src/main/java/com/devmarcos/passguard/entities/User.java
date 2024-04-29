package com.devmarcos.passguard.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "tb_user", indexes = @Index( columnList = "id", unique = true ) )
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "tb_user_sequnce" )
    @SequenceGenerator( name = "tb_user_sequnce", sequenceName = "tb_user_sequnce", allocationSize = 1 )
    private Long id;

    @Column( name = "username", length = 32, nullable =  false )
    @Getter
    @Setter
	private String username;
	
	@Column( name = "nickname", length = 64, nullable = true )
    @Getter
    @Setter
	private String nickName;
	
	@Column( name = "password", length = 32, nullable = false )
    @Getter
    @Setter
	private String password;
	
	@Column( name = "createdat", columnDefinition = "TIMESTAMP", nullable = false )
    @Getter
	private LocalDateTime createdAt;
	
	@OneToMany( mappedBy = "user", fetch = FetchType.EAGER )
    @Getter
	private List<Password> savedPasswords = new ArrayList<>();
	
	public static class Builder {
		private Long id;
		private String username;
		private String nickName;
		private String password;
		private List<Password> savedPasswords;
		
		public Builder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public Builder setNickName(String nickName) {
			this.nickName = nickName;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setSavedPasswords(List<Password> savedPasswords) {
			this.savedPasswords = savedPasswords;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
		
	}

	public User() {
		
	}
	
	public User(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.nickName = builder.nickName;
		this.password = builder.password;
		this.createdAt = LocalDateTime.now();
		this.savedPasswords = builder.savedPasswords;
	}

	public void addPassword(Password password) {
		this.savedPasswords.add(password);
	}
	
	public void removePassword(Password password) {
		this.savedPasswords.remove(password);
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
