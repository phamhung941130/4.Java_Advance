package com.example.entyti;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Group`")
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "GroupName", length = 50, nullable = false)
	private String groupName;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date createDate;

	@Column(name = "Member")
	@ColumnDefault("0")
	private int member;

	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
	private List<GroupUser> groupUsers;

	public Group(String name, int member) {
		this.member = member;
		this.groupName = name;
	}
}
