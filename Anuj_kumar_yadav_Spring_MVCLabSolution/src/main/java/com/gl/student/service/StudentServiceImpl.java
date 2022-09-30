package com.gl.student.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.student.model.Student;

@Repository
public class StudentServiceImpl implements StudentService {
	
	private SessionFactory sessionFactory;
	
	private Session session;
	
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			this.session = this.sessionFactory.getCurrentSession();
			}
		catch(HibernateException hiberException){
			this.session = this.sessionFactory.openSession();
		}
		
	}

	@Override
	@Transactional
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		
		List<Student> stds = session.createQuery("from Student").list();
		
		tx.commit();
				
		return stds;
	}

	@Override
	@Transactional
	public Student getById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Student std = session.get(Student.class, id);
		tx.commit();
		return std;
	}

	@Override
	@Transactional
	public List<Student> getByName(String name, String dept, String country) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		String qry;
		if(!name.isEmpty() && !dept.isEmpty() && !country.isEmpty()) { 
			//all ok
			qry = "from Student where name like '%"+name 
					+ "%' or department like '%" + dept 
					+ "%' or country like '%" + country + "%'";
		}
		else if(!name.isEmpty() && !dept.isEmpty() && country.isEmpty()) { 
			//country is empty
			qry = "from Student where name like '%"+name 
					+ "%' or department like '%" + dept 
					//+ "%' or country like '%" + country + 
					+ "%'";
		}
		else if(!name.isEmpty() && dept.isEmpty() && !country.isEmpty()) { 
			// dept is empty
			qry = "from Student where name like '%"+name 
					//+ "%' or department like '%" + dept 
					+ "%' or country like '%" + country 
					+ "%'";
		}
		else if(name.isEmpty() && !dept.isEmpty() && !country.isEmpty()) { 
			//name is empty
			qry = "from Student where department like '%" + dept 
					+ "%' or country like '%" + country 
					+ "%'";
		}
		
		else if(!name.isEmpty() && dept.isEmpty() && country.isEmpty()) { 
			// dept and country is empty
			qry = "from Student where name like '%"+name 
					// + "%' or department like '%" + dept 
					//+ "%' or country like '%" + country + 
					+ "%'";
		}
		else if(name.isEmpty() && dept.isEmpty() && !country.isEmpty()) {
			// dept and name is empty
			qry = "from Student where country like '%" + country 
					+ "%'";
		}
		else if(name.isEmpty() && !dept.isEmpty() && country.isEmpty()) { 
			// name and country is empty
			qry = "from Student where department like '%" + dept 
					//+ "%' or country like '%" + country + 
					+ "%'";
		}
		else {
			qry = "from Student where name like '%"+name 
					+ "%' or department like '%" + dept 
					+ "%' or country like '%" + country 
					+ "%'";
		}
		
		
		List<Student> students = session.createQuery(qry).list();
		System.out.println("Search results--");
		for(Student student:students) {
			System.out.println(student.toString());
		}
		
		tx.commit();
		return students;
	}

	@Override
	@Transactional
	public void saveOrUpdateStudent(Student std) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(std);
		tx.commit();
	}

	@Override
	@Transactional
	public void removeStudent(Student std) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void removeStudent(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.getTransaction();
		Student delStd =session.get(Student.class,id);
		session.delete(delStd);
		if(tx.isActive()) {
			tx.commit();
		}
		
	}

	
}
