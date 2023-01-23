package br.com.kai_library.dao;

import javax.persistence.EntityManager;

import br.com.kai_library.model.Employee;

public class EmployeeDao {

	private EntityManager em;
		
	public EmployeeDao(EntityManager em) {
		this.em = em;	
	}
		
	public void salvar(Employee p){
		em.persist(p);
	}
		
	public void removerPorId(Long id) {
		Employee p = em.find(Employee.class, id);
		em.remove(p);
	}
}
