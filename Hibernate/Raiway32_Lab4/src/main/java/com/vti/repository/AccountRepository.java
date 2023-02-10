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

			// create hql query
			Query<Account> query = session.createQuery("FROM Account order by id");

			return query.list();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Account getAccountByID(Short id) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();

			Account account = session.get(Account.class, id);

			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Account getAccountByName(String name) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			Query<Account> query = session.createQuery("FROM Account WHERE fullname = :nameParameter");
			query.setParameter("nameParameter", name);

			Account account = query.getSingleResult();
			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void createAccount(Account account) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateAccount_Collum(short id, String newName) {
		Session session = null;
		try {
//			Get session
			session = hibernateUtils.openSession();
			session.beginTransaction();
//			get Account
			Account account = session.load(Account.class, id);
//			Update
			account.setFullname(newName);

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateAccount(Account account) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			session.beginTransaction();

			session.update(account);
			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void deleteAccount(short id) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			session.beginTransaction();

			Account account = session.load(Account.class, id);

			session.delete(account);
			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public boolean isAccountExistsByID(short id) {
		Account account = getAccountByID(id);
		if (account == null) {
			return false;
		}
		return true;
	}

	public boolean isAccountExistsByName(String name) {
		Account account = getAccountByName(name);
		if (account == null) {
			return false;
		}
		return true;
	}
}