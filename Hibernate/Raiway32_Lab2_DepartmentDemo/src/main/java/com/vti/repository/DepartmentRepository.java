package com.vti.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entity.Department;

public class DepartmentRepository {
	private SessionFactory sessionFactory;

	public DepartmentRepository() {
		sessionFactory = buildSessionFactory();

	}

	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		configuration.addAnnotatedClass(Department.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public List<Department> getListDep() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hqlSelect = "FROM Department";
			Query<Department> query = session.createQuery(hqlSelect);
			List<Department> list = query.list();
			return list;
		} finally {
			session.close();
		}
	}

	public Department getDepartmentByID(short id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Department department = session.get(Department.class, id);
			return department;
		} finally {
			session.close();
		}
	}

	public Department getDepartmentByName(String nameDepFind) {
		Session session = null;
		try {
			// get session
			session = sessionFactory.openSession();

			// create hql query
			Query<Department> query = session.createQuery("FROM Department WHERE name = :nameParameter");

			// set parameter
			query.setParameter("nameParameter", nameDepFind);

			// get result
			Department department = query.uniqueResult();
			return department;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
