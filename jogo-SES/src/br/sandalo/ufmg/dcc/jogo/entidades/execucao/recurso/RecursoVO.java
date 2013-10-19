package br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso;

public class RecursoVO implements Comparable<RecursoVO>{
	private String nome;

	@Override
	public int compareTo(RecursoVO o) {
		return getNome().compareTo(o.getNome());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
