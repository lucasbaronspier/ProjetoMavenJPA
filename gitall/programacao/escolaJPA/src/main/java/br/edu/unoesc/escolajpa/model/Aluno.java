package br.edu.unoesc.escolajpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + "]";
	}

	@Column(length = 50, nullable = false)
	private String nome;

	@ManyToOne
	private Curso curso;
	
	@ManyToMany (cascade = CascadeType.ALL, fetch =   FetchType.EAGER)
	@JoinTable(name = "alunos_disciplina",
	joinColumns = @JoinColumn(name = "id_aluno"),
   	inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
	private List<Disciplina> disciplinasAluno;
	
	public List<Disciplina> getDisciplinasAluno() {
		return disciplinasAluno;
	}

	// construtores
	public Aluno() {
		super();
	}
	
	public Aluno(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Aluno(String nome) {
		super();
		this.nome = nome;
	}
	
	public Aluno(Integer id, String nome, Curso curso) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
	}
	
	public Aluno(String nome, Curso curso) {
		super();
		this.nome = nome;
		this.curso = curso;
	}

	// getters / setters
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	// toString
	
	public void adicionarDisciplina(Disciplina disciplina) {
		if (disciplina != null && !this.getDisciplinas().contains(disciplina)) {
			this.disciplinasAluno.add(disciplina);
			if (!disciplina.getAlunos().contains(this)) {
				disciplina.getAlunos().add(this);
			}
		}
	}

	public List<Disciplina> getDisciplinas() {
		if (disciplinasAluno == null) {
			disciplinasAluno = new ArrayList<>();
		}
		return disciplinasAluno;
	}

	public void setDisciplinasAluno(List<Disciplina> disciplinasAluno) {
		this.disciplinasAluno = disciplinasAluno;
	}

	public Aluno(Integer id, String nome, Curso curso, List<Disciplina> disciplinasAluno) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.disciplinasAluno = disciplinasAluno;
	}

}