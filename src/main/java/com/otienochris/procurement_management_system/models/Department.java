package com.group4.procurement1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
	@Id
	private int departmentId;
	private String departmentName;
	private String facultyName;
	private int departmentContact;
	
	public int getDepartmentId() {return departmentId;}
	
	public void setDepartmentId(int departmentId) {this.departmentId = departmentId;}
	
	public String getDepartmentName() {return departmentName;}
	
	public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}
	
	public String getFacultyName() {return facultyName;}
	
	public void setFacultyName(String facultyName) {this.facultyName = facultyName;}
	
	public int getDepartmentContact() {return departmentContact;}
	
	public void setDepartmentContact(int departmentContact) {this.departmentContact = departmentContact;}
	
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", facultyName="
				+ facultyName + ", departmentContact=" + departmentContact + "]";
	}
	
	
	
}
