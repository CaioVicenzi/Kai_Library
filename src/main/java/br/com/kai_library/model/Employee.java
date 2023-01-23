package br.com.kai_library.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empregados")
public class Employee {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String number;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	public Employee() {}
	
	public Employee(String firstName, String lastName, Gender gender, Integer age, String number) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.number = number;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return String.format("[Nome: %s %s, Idade: %d]", firstName, lastName, age);
	}
	
	
}
