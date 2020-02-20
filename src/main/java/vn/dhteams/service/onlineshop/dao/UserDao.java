package vn.dhteams.service.onlineshop.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import vn.dhteams.service.onlineshop.domain.Users;
import vn.dhteams.service.onlineshop.utils.Common;

@Repository
public class UserDao {

	@Autowired
	private EntityManager entityManager;
	
	public List<Users> listUser(Pageable pageable, String sortData, String searchData) {
		StringBuilder sql = new StringBuilder();
		Boolean firstClause = true;
		Object userName = null;
		Object fullName = null;
		Object trangThai = null;
		sql.append("FROM Users user");
		if(searchData != null) {
			Map<String, Object> searchDataObject = Common.splitSearchData(searchData);
			userName = searchDataObject.get("userName");
			if(userName != null) {
				if(firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" user.userName = :userName");
			}
			fullName = searchDataObject.get("fullName");
			if(fullName != null) {
				if(firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" LOWER(user.fullName) LIKE :fullName");
			}
			trangThai = searchDataObject.get("trangThai");
			if(trangThai != null) {
				if(firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" user.trangThai = :trangThai");
			}
		}
		sql.append(" ORDER BY ");
		sql.append(sortData);
		TypedQuery<Users> query = entityManager.createQuery(sql.toString(), Users.class);
		if(userName != null) {
			query.setParameter("userName", userName.toString());
		}
		if(fullName != null) {
			query.setParameter("fullName", "%" + fullName.toString().toLowerCase() + "%");
		}
		if(trangThai != null) {
			query.setParameter("trangThai", trangThai.toString());
		}
		if(pageable != null) {
			query.setFirstResult((int) pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		return query.getResultList();
	}
}
