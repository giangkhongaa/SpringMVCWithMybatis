package com.giang.service;

import java.util.List;

import com.giang.model.Student;

public interface StudentService {
	// insert student in database in student table
	void insertStudentService(Student student);

	// update student in database in student table and student_info table
	void updateStudentByIdService(Student student);

	// delete student in database in student table
	void deleteStudentByIdService(int id);

	// show list students
	List<Student> showStudentsService();

	// find student by id
	Student findStudentByIdService(int student_id);

	// count number record of students
	int countStudentsService();

	// show list students with page
	List<Student> showStudentsbyPageService(int start, int limit);

	// count the number of records
	int countStudentsByName(double average_score);

	// find students by name
	List<Student> findStudentsbyNameService(double average_score, int start, int limit);

	// find last id
	int getLastIdStudentService();

	// fin last id by name table
	int getLastIdStudentByNamTable(String nameDatable,String nameTable);
}
