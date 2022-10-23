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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "disciplinasAluno",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Aluno> alunos;	
	
	             
	public Disciplina(String nome) {
		super();
		this.nome = nome;
	}


		public Disciplina() {
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

		public void setAlunos(List<Aluno> alunos) {
			this.alunos = alunos;
		}

		public void adicionarAluno(Aluno aluno) {
			if (aluno != null && !this.getAlunos().contains(aluno)) {
				this.alunos.add(aluno);

				if (!aluno.getDisciplinas().contains(this)) {
					aluno.getDisciplinas().add(this);
				}
			}
		}

		public Disciplina(String nome, List<Aluno> alunos) {
			super();
			this.nome = nome;
			this.alunos = alunos;
		}

		public List<Aluno> getAlunos() {
			if (this.alunos == null) {
				this.alunos = new ArrayList<>();
			}
			return alunos;
		}
		
}

