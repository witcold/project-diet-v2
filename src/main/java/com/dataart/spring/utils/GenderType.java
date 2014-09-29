package com.dataart.spring.utils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import com.dataart.spring.model.Gender;

/**
 * @author vmeshcheryakov
 *
 */
public class GenderType implements UserType {

	private Class<Gender> clazz = Gender.class;

	private static final int[] SQL_TYPES = {Types.CHAR};

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<Gender> returnedClass() {
		return clazz;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		if (x == null || y == null)
			return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		char name = rs.getString(names[0]).charAt(0);
		Gender result = null;
		if (!rs.wasNull()) {
			result = Gender.get(name);
		}
		return result;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (null == value) {
			st.setNull(index, Types.CHAR);
		} else {
			st.setString(index, String.valueOf(((Gender) value).getValue()));
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
