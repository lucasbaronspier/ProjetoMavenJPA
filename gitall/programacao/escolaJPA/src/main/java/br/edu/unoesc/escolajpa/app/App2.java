package br.edu.unoesc.escolajpa.app;

import java.util.List;

import br.edu.unoesc.escolajpa.dao.AlunoDAO;
import br.edu.unoesc.escolajpa.dao.CursoDAO;
import br.edu.unoesc.escolajpa.model.Aluno;
import br.edu.unoesc.escolajpa.model.Curso;

public class App2 {
	public static void main(String[] args) {
		CursoDAO CursoDAO = new CursoDAO();
		AlunoDAO AlunoDAO = new AlunoDAO();

		Curso curso = new Curso("Design");

		Aluno aluno = new Aluno("Jose", curso);

		System.out.println(CursoDAO.salvar(curso).obterTodos());
		List<Curso> cursos = CursoDAO.obterTodos();
		System.out.println("Curso e Aluno");
		for (Curso c : cursos) {
			System.out.println(c.getNome());
			for (Aluno a : c.getAlunos()) {
				System.out.println("\t" + a.getNome());
			}
		}
		
		CursoDAO.fechar();

		AlunoDAO.salvar(aluno);
		List<Aluno> alunos = AlunoDAO.obterTodos();
		System.out.println("Aluno e Curso");
		for (Aluno a : alunos) {
			System.out.println(a.getNome() + " - " + a.getCurso().getNome());
		}
		AlunoDAO.fechar();
		
		
	}
}
