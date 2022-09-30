package com.gl.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.student.model.Student;
import com.gl.student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/show")
	public String showStudents(Model std) {
		
		List<Student> students = new ArrayList<>();
		students = studentService.getAll();
		System.out.println("Created empty studentService object" + studentService);
		
		std.addAttribute("students", students);
		System.out.println("Added list<Student> students object to the model atribute");
		
		return "students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model std) {
		
		Student newStudent = new Student();
		std.addAttribute("student",newStudent);
		
		return "student-form";
	}
	
	@RequestMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("department") String department,
			@RequestParam("country") String country) {
		Student theStudent;
		if(id == 0) {
		// add new student
		theStudent = new Student(name, department, country);

		
		}
		else {
			// update the student
			theStudent = studentService.getById(id);
			System.out.println("Student to be updated-" + theStudent.toString());
			theStudent.setName(name);
			theStudent.setCountry(country);
			theStudent.setDepartment(department);
		}
		System.out.println("received the Student from the student form for upsert");
		System.out.println(theStudent.toString());
		studentService.saveOrUpdateStudent(theStudent);
		System.out.println("Student persisted!");
		return "redirect:/student/show";
	}
	
	@RequestMapping("/search")
	public String searchStudent(@RequestParam("name") String name, @RequestParam("department") String department,
			@RequestParam("country") String country, Model theModel) {
		//if search fields are empty
		if(name.isEmpty() && department.isEmpty() && country.isEmpty()) {
			return "redirect:/student/show";
		}
		else {
			List<Student> students = studentService.getByName(name, department, country);
			theModel.addAttribute("students", students);
			return "students";
		}
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormFroUpdate(@RequestParam("studentId") int id, Model theModel) {
		Student std = studentService.getById(id);
		System.out.println("Going to update the student- " + std.toString());
		theModel.addAttribute("student", std);
		
		return "student-form";
	}
	
	
	@RequestMapping("/delete")
	public String removeStudent(@RequestParam("studentId") int id, Model theModel) {
		Student std = studentService.getById(id);
		studentService.removeStudent(id);
		System.out.println("Going to remove the student- " + std.toString());
		theModel.addAttribute("student", std);
		
		return "redirect:/student/show";
	}
	
	
}
