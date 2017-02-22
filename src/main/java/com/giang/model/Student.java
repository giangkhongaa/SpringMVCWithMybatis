package com.giang.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	@NotEmpty
	private String student_name;
	@Size(min = 2, max = 100)
	private String student_code;
	@Size(min = 2, max = 200)
	private String address;
	@NotNull
	@Min(0)
	@Max(10)
	private double average_score;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@NotNull(message="Please enter a date")
	@Past (message="Only the past is valid")
	private Date date_of_birth;

	public Student() {
	}

	public Student(int student_id, String student_name, String student_code, String address, double average_score,
			Date date_of_birth) {
		this.student_id = student_id;
		this.student_name = student_name;
		this.student_code = student_code;
		this.address = address;
		this.average_score = average_score;
		this.date_of_birth = date_of_birth;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAverage_score() {
		return average_score;
	}

	public void setAverage_score(double average_score) {
		this.average_score = average_score;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
}
