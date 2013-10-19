package br.sandalo.ufmg.dcc.jogo.entidades.projeto;

public class NecessidadeDeNegocioVO implements Comparable<NecessidadeDeNegocioVO>{
	private ProjetoVO projetoVO;
	private Integer tamanho;
	private Integer complexidade;
	private String descricao;
	public ProjetoVO getProjetoVO() {
		return projetoVO;
	}

	public void setProjetoVO(ProjetoVO projetoVO) {
		this.projetoVO = projetoVO;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(Integer complexidade) {
		this.complexidade = complexidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int compareTo(NecessidadeDeNegocioVO o) {
		return this.getDescricao().compareTo(o.getDescricao());
	}

}
