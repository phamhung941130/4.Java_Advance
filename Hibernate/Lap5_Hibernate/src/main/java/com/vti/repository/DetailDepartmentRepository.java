package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.DetailDepartment;
import com.vti.utils.HibernateUtils;

public class DetailDepartmentRepository {

	private HibernateUtils hibernateUtils;

	public DetailDepartmentRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

	public List<DetailDepartment> getAllDetailDepartment() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

//			// create hql query
			Query<DetailDepartment> query = session.createQuery("FROM DetailDepartment order by id");

			return query.list();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public DetailDepartment getDetailDepartmentByID(short id) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();

//			CriteriaBuilder builder = session.getCriteriaBuilder();
//
//			CriteriaQuery<DetailDepartment> query = builder.createQuery(DetailDepartment.class);
//			Root<DetailDepartment> root = query.from(DetailDepartment.class); // FROM Account
//			query.select(root); // SELECT
//			query.where(builder.equal(root.get("id"), id));
//			DetailDepartment detailDepartment = session.createQuery(query).uniqueResult();
			DetailDepartment detailDepartment = session.get(DetailDepartment.class, id);
			return detailDepartment;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
