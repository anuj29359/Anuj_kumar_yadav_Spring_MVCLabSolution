package com.gl.student.service;

import java.util.List;

import com.gl.student.model.Student;

public interface StudentService {
	//fetch
	public List<Student> getAll();
	public Student getById(int id);
	public List<Student> getByName(String name, String dept, String country);
	//save
	public void saveOrUpdateStudent(Student std);
	//remove
	public void removeStudent(Student std);
	public void removeStudent(int id);

}
