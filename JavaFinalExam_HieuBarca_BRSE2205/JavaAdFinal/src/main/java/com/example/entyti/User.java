package com.example.entyti;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`User`")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "UserName", length = 50, nullable = false, unique = true)
	private String userName;

	@Column(name = "Password", length = 50, nullable = false)
	private String password;

	@Column(name = "Email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "FullName", length = 100, nullable = false)
	private String fullName;

	@OneToMany(mappedBy = "user")
	private List<GroupUser> groupUsers;
}
