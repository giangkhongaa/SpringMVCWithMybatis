package com.giang.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.giang.mapper.StudentMapper;
import com.giang.model.Student;
import com.giang.util.FactoryUtil;

@Component("StudentService")
public class StudentServiceImpl implements StudentService {
	// insert student in database in student table
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertStudentService(Student student) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			mapper.insertStudent(student);
			mapper.insertStudentInfo(student);
			//student.setAverage_score(Double.parseDouble("aaaa"));
			session.commit();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			session.close();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateStudentByIdService(Student student) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			mapper.updateStudentById(student);
			session.commit();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			session.close();

		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteStudentByIdService(int student_id) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			mapper.deleteStudentInfoById(student_id);
			mapper.deleteStudentById(student_id);
			//test transaction
			int test=Integer.parseInt("aaaaaaa");
			session.commit();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			session.close();
		}

	}
	public List<Student> showStudentsService() {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.showStudents();
		} finally {
			session.close();
		}
	}

	public Student findStudentByIdService(int student_id) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.findStudentById(student_id);
		} finally {
			session.close();
		}
	}

	// count number record of students
	public int countStudentsService() {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.countStudents();
		} finally {
			session.close();
		}
	}

	public List<Student> showStudentsbyPageService(int start, int limit) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.showStudentsbyPage(start, limit);
		} finally {
			session.close();
		}
	}

	public List<Student> findStudentsbyNameService(double average_score, int start, int limit) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.findStudentsbyName(average_score, start, limit);
		} finally {
			session.close();
		}
	}

	public int countStudentsByName(double average_score) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.countStudentsByName(average_score);
		} finally {
			session.close();
		}
	}

	public int getLastIdStudentService() {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.getLastIdStudent();
		} finally {
			session.close();
		}
	}

	public int getLastIdStudentByNamTable(String nameDatabase, String nameTable) {
		SqlSession session = FactoryUtil.openSession();
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			return mapper.getLastIdStudentByNamTable(nameDatabase, nameTable);
		} finally {
			session.close();
		}
	}

}
