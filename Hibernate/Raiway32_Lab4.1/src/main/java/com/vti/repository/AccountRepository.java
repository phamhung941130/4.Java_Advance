package com.vti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.Position.PositionName;
import com.vti.utils.HibernateUtils;

public class AccountRepository {
	private HibernateUtils hibernateUtils;

	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

	public List<Object[]> getCountAccountIncurrentmonth() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();

			Query<Object[]> query = session.createQuery(
					"SELECT month(createDate) as Month,COUNT(*) as SL FROM Account WHERE year(createDate) = year(sysdate()) GROUP BY month(createDate)");
			List<Object[]> result = query.list();
			return result;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Account> getAccountIncurrentmonth() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();

//			Query<Account> query = session.createQuery(
//					"FROM Account WHERE month(createDate) = month(sysdate()) AND year(createDate) = year(sysdate())");

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM Account
			query.select(root);
//			month(createDate) = month(sysdate())
			Expression<Integer> monthCreateDate = builder.function("month", Integer.class, root.get("createDate"));
			Expression<Integer> monthCurent = builder.function("month", Integer.class, builder.currentDate());
			query.where(builder.equal(monthCreateDate, monthCurent));
			query.orderBy(builder.desc(root.get("fullname")));
			List<Account> listAccount = session.createQuery(query).list();
			return listAccount;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public String getFullnameByID(short id) {

		Session session = null;
		try {
			session = hibernateUtils.openSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<String> query = builder.createQuery(String.class);
			Root<Account> root = query.from(Account.class); // FROM Account

			query.multiselect(root.get("fullname"));
			query.where(builder.equal(root.get("id"), id));
			String fullname = session.createQuery(query).uniqueResult();
			return fullname;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Account> getAllAccount() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

//			// create hql query
////			Query<Account> query = session.createQuery("FROM Account order by id");
//
//			// Build ra câu query, build các điều kiện sau Where
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			// Tạo ra các câu query
//			CriteriaQuery<Account> query = builder.createQuery(Account.class);
//			// Nắm giữ thông tin bảng dữ liệu gốc cần truy vấn
//			Root<Account> root = query.from(Account.class); // FROM Account
//
//			query.select(root); // SELECT
//			List<Account> listAccounts = session.createQuery(query).list();

			String sqlNative = "SELECT a.AccountID,a.Email, a.Username, a.FullName, d.DepartmentName,p.PositionName,a.CreateDate FROM account a \r\n"
					+ "INNER JOIN department d ON a.DepartmentID = d.DepartmentID\r\n"
					+ "INNER JOIN position p ON a.PositionID = p.PositionID";
			NativeQuery query = session.createNativeQuery(sqlNative);
			List<Object[]> listObject = query.getResultList();
// List<Object[]> ==> listAccounts
			List<Account> listAccounts = new ArrayList<Account>();
			for (Object[] objects : listObject) {
				Account account = new Account();
				account.setId(Short.parseShort(objects[0].toString()));
				account.setEmail(objects[1].toString());
				account.setUsername(objects[2].toString());
				account.setFullname(objects[3].toString());
//				Dep: object[4].toString()
//				Pos: object[5].toString()
				Department department = new Department();
				department.setName(objects[4].toString());
				account.setDepartment(department);

				Position position = new Position();
				position.setName(PositionName.valueOf(objects[5].toString()));
				account.setPosition(position);

				account.setCreateDate(null);
				listAccounts.add(account);
			}
			return listAccounts;

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

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM Account
			query.select(root); // SELECT
			query.where(builder.equal(root.get("id"), id));
			Account account = session.createQuery(query).uniqueResult();
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