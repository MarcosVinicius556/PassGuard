package br.com.dev.marcos.passguard.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.dev.marcos.passguard.entities.interfaces.BaseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table( name = "tb_user", indexes = @Index( columnList = "id", unique = true ) )
public class User implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "tb_user_id_sequence" )
	@SequenceGenerator( name = "tb_user_id_sequence", sequenceName = "tb_user_id_sequence", allocationSize = 1 )
	private Long id;
	
	@Column( name = "username", length = 32, nullable =  false )
	@NotBlank( message = "O nome de usuário não pode ser vazio." )
	@Min( value = 3, message = "O nome de usuário deve conter no mínimo 3 caracteres" )
	@Max( value = 32, message = "O nome de usuário não pode ultrapassar 32 caractéres" )
	private String username;
	
	@Column( name = "nickname", length = 64, nullable = true )
	@NotBlank( message = "O nickname não pode ser vazio." )
	@Max( value = 64, message = "O nickname não pode ultrapassar 64 caractéres" )
	private String nickName;
	
	@Column( name = "password", length = 32, nullable = false )
	@NotBlank( message = "A senha não pode ser vazia." )
	@Min( value = 6, message = "A senha deve conter no mínimo 6 caracteres" )
	@Max( value = 32, message = "A senha não pode ultrapassar 32 caractéres" )
	private String password;
	
	@Column( name = "createdat", columnDefinition = "TIMESTAMP", nullable = false )
	private LocalDateTime createdAt;
	
	@OneToMany( mappedBy = "user", fetch = FetchType.EAGER )
	private List<Password> savedPasswords = new ArrayList<>();
	
	public static class Builder {
		private Long id;
		private String username;
		private String nickName;
		private String password;
		private LocalDateTime createdAt;
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
		
		public Builder setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
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
		this.createdAt = builder.createdAt;
		this.savedPasswords = builder.savedPasswords;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	@Override
	public String getEntityName() {
		return this.getClass().getName();
	}

	@Override
	public Class<? extends BaseEntity> getEntityClass() {
		return User.class;
	}
	
}
