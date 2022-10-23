package br.edu.unoesc.escolajpa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String nome;

	@OneToMany (mappedBy = "curso")
	private Set<Aluno>alunos = new 
	HashSet<Aluno>();
	
	public void adicionarAluno(Aluno aluno) {
		aluno.setCurso(this);
		this.alunos.add(aluno);
	}
	
	public Curso() {
		super();
	}

	public Curso(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Curso(String nome) {
		super();
		this.nome = nome;
	}

	public Curso(Integer id, String nome, Set<Aluno> alunos) {
		super();
		this.id = id;
		this.nome = nome;
		this.alunos = alunos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}




	
}