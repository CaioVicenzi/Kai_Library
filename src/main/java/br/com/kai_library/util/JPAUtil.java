package br.com.kai_library.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory fac = Persistence.createEntityManagerFactory("kai_library");

	public static EntityManager getEntityManager(){
		return fac.createEntityManager();
	}
}
