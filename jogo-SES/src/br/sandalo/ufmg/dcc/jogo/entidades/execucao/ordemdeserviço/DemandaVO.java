package br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço;

import java.util.Set;
import java.util.TreeSet;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;

public class DemandaVO implements Comparable<DemandaVO> {
	public enum Estado{
		ABERTA, EXECUTANDO, CONSTRUIDA, ENTREGUE
	}
	
	private Estado estado = Estado.ABERTA;
	private Integer tamanho;
	private Integer complexidade;
	private String descricao;
	private Set<ItemDeTrabalhoVO> itensDeTrabalho = new TreeSet<ItemDeTrabalhoVO>();
	public Set<ItemDeTrabalhoVO> getItensDeTrabalho() {
		return itensDeTrabalho;
	}

	public void setItensDeTrabalho(Set<ItemDeTrabalhoVO> itensDeTrabalho) {
		this.itensDeTrabalho = itensDeTrabalho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int compareTo(DemandaVO o) {
		return getDescricao().compareTo(o.getDescricao());
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
