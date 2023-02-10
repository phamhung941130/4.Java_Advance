package com.vti.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.vti.entity.Position;
import com.vti.utils.HibernateUtils;

public class PositionRepository {
	private HibernateUtils hibernateUtils;

	public PositionRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

	public Position getPositionByID(short id) {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// get department by id
			Position position = session.get(Position.class, id);

			return position;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Position> getAllPosition() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// create hql query
			Query<Position> query = session.createQuery("FROM Position order by id");

			return query.list();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
