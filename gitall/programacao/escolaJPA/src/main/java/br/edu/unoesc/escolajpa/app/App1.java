package br.edu.unoesc.escolajpa.app;

import br.edu.unoesc.escolajpa.model.Aluno;
import br.edu.unoesc.escolajpa.model.Curso;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class App1 {
	private static EntityManager em;
	
	private void adiciona() {
		
		Curso c1 = new Curso("Educacao Fisica");
		Curso c2 = new Curso("Direito");
		
		Aluno a1 = new Aluno("Maria",c1);
		Aluno a2 = new Aluno("Lucas",c2);
		Aluno a3 = new Aluno("Marcos",c2);
		
		em.getTransaction().begin();
		
		em.persist(c1);
		em.persist(c2);
		
		em.persist(a1);
		em.persist(a2);		
		em.persist(a3);
		em.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		App1 app = new App1();
		em = JPAUtil.getEntityManager();
		
		app.adiciona();
		
		em.close();
		JPAUtil.closeEntityManagerFactory();
	}
}