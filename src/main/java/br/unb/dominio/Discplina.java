package br.unb.dominio;

public class Discplina {

	private int id; // gerado pelo banco
	private String nome; // nome da disciplina
	private String curso; // nome do curso: civil, medicina, direito
	private String turma; // 20231 anoperiodo
	private String local; // Bloco sala

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Discplina(String nome, String curso, String turma, String local) {
		super();
		this.nome = nome;
		this.curso = curso;
		this.turma = turma;
		this.local = local;
	}
	
	public Discplina() {
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Discplina [id=" + id + ", nome=" + nome + ", curso=" + curso + ", turma=" + turma + ", local=" + local
				+ "]";
	}

}
