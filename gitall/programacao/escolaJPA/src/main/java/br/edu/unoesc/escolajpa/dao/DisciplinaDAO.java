package br.edu.unoesc.escolajpa.dao;

import java.util.List;

import br.edu.unoesc.escolajpa.model.Disciplina;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DisciplinaDAO {
	private EntityManager em;

	public DisciplinaDAO() {
		em = JPAUtil.getEntityManager();
	}
	// métodos privados
	private DisciplinaDAO abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}
	private DisciplinaDAO fecharTransacao() {
		em.getTransaction().commit();
		return this;
	}
	private DisciplinaDAO incluir(Disciplina d) {
		em.persist(d);
		return this;
	}
	private DisciplinaDAO remover(Disciplina d) {
		em.remove(d);
		return this;
	}
	// métodos públicos
	public DisciplinaDAO salvar(Disciplina d) {
		return this.abrirTransacao().incluir(d).fecharTransacao();
	}
	public DisciplinaDAO excluir(Disciplina d) {
		return this.abrirTransacao().remover(d).fecharTransacao();
	}
	public List<Disciplina> obterTodos() {
		String jpql = "SELECT c FROM Curso c";
		return em.createQuery(jpql, Disciplina.class).getResultList();
	}
	public Disciplina buscarPorId(Integer id) {
		return em.find(Disciplina.class, id);
	}
	public List<Disciplina> buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Curso c WHERE c.nome LIKE :nome";
		TypedQuery<Disciplina> consulta = em.createQuery(jpql, Disciplina.class);
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.getResultList();
	}
	public void fechar() {
		em.close();
	}
}
