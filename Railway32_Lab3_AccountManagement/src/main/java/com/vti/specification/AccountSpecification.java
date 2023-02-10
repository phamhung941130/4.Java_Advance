package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Account;

public class AccountSpecification implements Specification<Account> {

	private String field;
	private String opeartor;
	private Object value;

	public AccountSpecification(String field, String opeartor, Object value) {
		super();
		this.field = field;
		this.opeartor = opeartor;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (opeartor.equalsIgnoreCase("LIKE")) {
			if (field.equalsIgnoreCase("department")) {
				return criteriaBuilder.like(root.get("department").get("name"), "%" + value.toString() + "%");
			}
			return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
		}

		return null;
	}

}
