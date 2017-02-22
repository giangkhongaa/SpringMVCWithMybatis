package com.giang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.giang.model.Student;

public interface StudentMapper {
	// insert student in database in student table
	void insertStudent(Student student);

	// insert student info in student_info table
	void insertStudentInfo(Student student);

	// update student in database in student table and student_info table
	void updateStudentById(Student student);

	// delete student in database in student table
	void deleteStudentById(@Param("student_id") int student_id);

	// delete student in database in student info table
	void deleteStudentInfoById(@Param("student_id") int student_id);

	// show list students
	List<Student> showStudents();

	// find student by id
	Student findStudentById(@Param("student_id") int student_id);

	// count the number of records
	int countStudents();

	// show list students with page
	List<Student> showStudentsbyPage(@Param("start") int start, @Param("limit") int limit);

	// function find name
//	// count the number of records
//	int countStudentsByName(@Param("student_name") String student_name);
//
//	// find students by name
//	List<Student> findStudentsbyName(@Param("student_name") String student_name, @Param("start") int start,
//			@Param("limit") int limit);

	// find last id
	int getLastIdStudent();

	// fin last id by name table
	int getLastIdStudentByNamTable(@Param("nameDatabase") String nameDatabase, @Param("nameTable") String nameTable);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// count the number of records
	int countStudentsByName(@Param("average_score") double average_score);

	// find students by name
	List<Student> findStudentsbyName(@Param("average_score") double average_score, @Param("start") int start,
			@Param("limit") int limit);

}
