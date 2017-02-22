package com.giang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.giang.model.Page;
import com.giang.model.Student;
import com.giang.model.User;
import com.giang.service.StudentServiceImpl;
import com.giang.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Controller
@ComponentScan("com.giang.service")
// keySearch : the search key
// user: account when logging
// pageCurrent: Current page in performing the function delete
@SessionAttributes({ "keySearch", "user", "pageCurrent" })
public class StudentController {

	private StudentServiceImpl studentService = new StudentServiceImpl();
	@Autowired
	private UserService userService;

	/**
	 * receive a request from "/student/logout" with method = get check your
	 * current account is active if active then show else Account entry into
	 * 
	 * @param ModelMap,
	 * @param HttpSession
	 *            return login
	 */
	@RequestMapping(value = "/student/login", method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpSession session) {

		if ((User) session.getAttribute("user") == null) {
			modelMap.put("user", new User());
			return "login";
		} else if (userService.checkLoginService((User) session.getAttribute("user"))) {
			System.out.println(userService.checkLoginService((User) session.getAttribute("user")));
			return "redirect:/student/show";
		} else {
			modelMap.put("user", new User());
			return "login";
		}

	}

	/**
	 * check login then submit login. if successful, will turn the show else
	 * show message
	 * 
	 * @param ModelMap
	 * @param User
	 *            return login
	 */
	@RequestMapping(value = "/student/login", method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "user") User user, ModelMap modelMap) {
		if (userService.checkLoginService(user)) {
			modelMap.addAttribute("user", user);
			return "redirect:/student/show";
		} else {
			modelMap.addAttribute("message", "failse login");
			return "login";
		}

	}

	/**
	 * receive a request from "/student/logout" with method = get, Delete
	 * session user
	 * 
	 * @param ModelMap
	 * @param SessionStatus
	 *            return redirect/student/login
	 */
	@RequestMapping(value = "/student/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, SessionStatus status) {
		status.setComplete();
		return "redirect:/student/login";
	}

	/**
	 * receive a request from "/student/show" with method = get,show students
	 * 
	 * @param ModelMap
	 *            return show
	 */
	@RequestMapping(value = "/student/show", method = RequestMethod.GET)
	public String showStudents(ModelMap modelMap) {
		showStudentList(modelMap, 1);
		return "show";

	}

	/**
	 * receive a request from "/student/insert" with method = get
	 * 
	 * @param ModelMap
	 *            return insert
	 */
	@RequestMapping(value = "/student/insert", method = RequestMethod.GET)
	public String insertStudent(ModelMap modelMap) {
		modelMap.put("student", new Student());
		return "insert";
	}

	/**
	 * receive a request from "/student/insert" with method = post. test data is
	 * valid (if error return insert) insert student
	 * 
	 * @param ModelMap
	 * @param Student
	 * @param BindingResult
	 */
	@RequestMapping(value = "/student/insert", method = RequestMethod.POST)
	public String insertStudent(ModelMap modelMap, @ModelAttribute(value = "student") @Valid Student student,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Error. Returning userForm page");
			modelMap.addAttribute("student", student);
			return "insert";
		}
		// insert data
		studentService.insertStudentService(student);

		// cách 1
		// int lastId=student.getStudent_id();
		// cách 2 sử dụng last_insert_id
		int lastId = studentService.getLastIdStudentService();

		modelMap.addAttribute("lastId", lastId);

		modelMap.addAttribute("lastId", studentService.getLastIdStudentByNamTable("dbstudentmanage", "student") - 1);
		// studentService.insertStudentInfoService(student);
		modelMap.addAttribute("lastId2",
				studentService.getLastIdStudentByNamTable("dbstudentmanage", "student_info") - 1);

		// System.out.println(studentService.getLastIdStudentByNamTable("student")-1);
		// System.out.println(studentService.getLastIdStudentByNamTable("student_info")-1);
		return "redirect:/student/show";

	}

	/**
	 * receive a request from "/student/update/{student_id}" with method = get,
	 * with student_id receive view get student by student id, sent over view
	 * 
	 * @param int
	 * @param ModelMap
	 *            return update
	 */
	@RequestMapping(value = "/student/update/{student_id}", method = RequestMethod.GET)
	public String updateStudent(@PathVariable("student_id") int student_id, ModelMap modelMap) {
		Student studentFindById = studentService.findStudentByIdService(student_id);
		modelMap.put("student", studentFindById);
		return "update";
	}

	/**
	 * receive a request from "/student/update" with method = post, update
	 * student from request and check data is valid
	 * 
	 * @param ModelMap
	 * @param Student
	 * @param BindingResult
	 */
	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	public String updateStudent(ModelMap modelMap, @ModelAttribute(value = "student") Student student,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("student", student);
			return "insert";
		}
		studentService.updateStudentByIdService(student);
		return "redirect:/student/show";
	}

	/**
	 * receive a request from "/student/delete/{student_id} with method = get,
	 * delete student
	 * 
	 * @param int
	 * @param ModelMap
	 * @param HttpSession
	 */
	@RequestMapping(value = "/student/delete/{student_id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("student_id") int student_id, ModelMap modelMap, HttpSession session) {
		studentService.deleteStudentByIdService(student_id);

		// standing the current page afer delete
		if (session.getAttribute("keySearch") == null) {
			return "redirect:/student/page/" + session.getAttribute("pageCurrent");
		} else {
			return "redirect:/student/search/" + session.getAttribute("pageCurrent");
		}
	}

	/**
	 * receive a request from "/student/page/{page_id}" with method = get,
	 * displays a list of students moving to other pages
	 * 
	 * @param int
	 * @param ModelMap
	 *            return "show"
	 */
	@RequestMapping(value = "/student/page/{page_id}", method = RequestMethod.GET)
	public String showStudentWithPage(@PathVariable("page_id") int id, ModelMap modelMap) {
		showStudentList(modelMap, id);
		return "show";
	}

	/**
	 * receive a request from "/student/page/{page_id}" with method = get,
	 * displays a list of students then search
	 * 
	 * @param modelMap
	 * @param Student
	 *            return "show"
	 */
	@RequestMapping(value = "/student/search", method = RequestMethod.POST)
	public String searchStudent(ModelMap modelMap, @ModelAttribute("student") Student student) {
		showStudentListWithSearch(modelMap, student.getAverage_score(), 1);
		return "show";
	}

	/**
	 * receive a request from "/student/page/{page_id}" with method = get,
	 * displays a list of students moving to other pages state search
	 * 
	 * @param int
	 * @param modelMap
	 * @param HttpSession
	 *            return "show"
	 */
	@RequestMapping(value = "/student/search/{page_id}", method = RequestMethod.GET)
	public String searchStudentWithPage(@PathVariable("page_id") int id, ModelMap modelMap, HttpSession session) {

		showStudentListWithSearch(modelMap, (Double)session.getAttribute("keySearch"), id);
		return "show";
	}

	/**
	 * receive a request from "/student/page/{page_id}" with method = get,
	 * cancel the search status
	 * 
	 * @param ModelMap
	 * @param SessionStatus
	 *            return "redirect:/student/show"
	 */
	@RequestMapping(value = "/student/removeSession", method = RequestMethod.GET)
	public String removeSession(ModelMap modelMap, SessionStatus status) {
		status.setComplete();
		return "redirect:/student/show";
	}

	/**
	 * display a list of student with paging. update session
	 * keySearch,pageCurrent use an object Page to show number of pages and
	 * perform specialized functions page.
	 * 
	 * @param modelMap
	 * @param int
	 */
	public void showStudentList(ModelMap modelMap, int id) {
		// number record of page
		int recordPage = 10;
		List<Student> students = new ArrayList<Student>();

		if (id == 1) {
			// a list students index
			students = studentService.showStudentsbyPageService(0, recordPage);

		} else {
			// a list students when turning pages
			students = studentService.showStudentsbyPageService((int) (recordPage * (id - 1)), recordPage);
		}

		// init page
		int countStudent = studentService.countStudentsService();
		Page page = new Page(countStudent, recordPage, id);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("studentList", students);
		modelMap.addAttribute("student", new Student());
		// Check record last record there is of one page if true update session
		// pageCurrent
		if (countStudent % 10 == 1 && countStudent != 1) {
			modelMap.addAttribute("pageCurrent", id - 1);
		} else {
			modelMap.addAttribute("pageCurrent", id);
		}
	}

	/**
	 * display a list of student with paging state search. update session
	 * keySearch,pageCurrent use an object Page to show number of pages and
	 * perform specialized functions page.
	 * 
	 * @param ModelMap
	 * @param keySearch
	 * @param int
	 */
	public void showStudentListWithSearch(ModelMap modelMap, double keySearch, int id) {
		// number record of page
		int recordPage = 10;
		List<Student> students = new ArrayList<Student>();

		if (id == 1) {
			// a list students index
			students = studentService.findStudentsbyNameService(keySearch, 0, recordPage);

		} else {
			// a list students when turning pages
			students = studentService.findStudentsbyNameService(keySearch, (int) (recordPage * (id - 1)), recordPage);
		}

		int countStudent = studentService.countStudentsByName(keySearch);
		Page page = new Page(countStudent, recordPage, id);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("studentList", students);
		modelMap.addAttribute("student", new Student());
		modelMap.addAttribute("keySearch", keySearch);
		// Check record last record there is of one page if true update session
		// pageCurrent
		if (countStudent % 10 == 1 && countStudent != 1) {
			modelMap.addAttribute("pageCurrent", id - 1);
		} else {
			modelMap.addAttribute("pageCurrent", id);
		}
	}

}
