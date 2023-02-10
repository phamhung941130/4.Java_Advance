package com.vti.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;

public class AccountRepository {
	private HibernateUtils hibernateUtils;

	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

	public List<Account> getAllAccount() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

//			// create hql query
			Query<Account> query = session.createQuery("FROM Account order by id");
			List<Account> list = query.getResultList();
			return list;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
