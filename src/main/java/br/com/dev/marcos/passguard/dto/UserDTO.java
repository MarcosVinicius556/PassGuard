package br.com.dev.marcos.passguard.dto;

import br.com.dev.marcos.passguard.entities.User;

public class UserDTO {

	private Long id;
	private String username;
	private String nickName;
	private String password;
	private String createdAt;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.nickName = user.getNickName();
		this.password = user.getPassword();
		this.createdAt = user.getCreatedAt().toString();
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public User fromDTO() {
		return new User.Builder()
					   .setId(id)
					   .setUsername(username)
					   .setNickName(nickName)
					   .setCreatedAt(null) //TODO verify how to parse String to LocalDateTime....	
					   .setPassword(password)
					   .build();
	}
	
}
