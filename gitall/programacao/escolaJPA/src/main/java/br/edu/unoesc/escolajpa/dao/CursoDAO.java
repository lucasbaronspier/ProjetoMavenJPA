package br.edu.unoesc.escolajpa.dao;

import java.util.List;

import br.edu.unoesc.escolajpa.model.Curso;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CursoDAO {
	private EntityManager em;

	public CursoDAO() {
		em = JPAUtil.getEntityManager();
	}
	// métodos privados
	private CursoDAO abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}
	private CursoDAO fecharTransacao() {
		em.getTransaction().commit();
		return this;
	}
	private CursoDAO incluir(Curso c) {
		em.persist(c);
		return this;
	}
	private CursoDAO remover(Curso c) {
		em.remove(c);
		return this;
	}
	// métodos públicos
	public CursoDAO salvar(Curso c) {
		return this.abrirTransacao().incluir(c).fecharTransacao();
	}
	public CursoDAO excluir(Curso c) {
		return this.abrirTransacao().remover(c).fecharTransacao();
	}
	public List<Curso> obterTodos() {
		String jpql = "SELECT c FROM Curso c";
		return em.createQuery(jpql, Curso.class).getResultList();
	}
	public Curso buscarPorId(Integer id) {
		return em.find(Curso.class, id);
	}
	public List<Curso> buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Curso c WHERE c.nome LIKE :nome";
		TypedQuery<Curso> consulta = em.createQuery(jpql, Curso.class);
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.getResultList();
	}
	public void fechar() {
		em.close();
	}
}
