package br.sandalo.ufmg.dcc.jogo.entidades.projeto;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ProjetoVO implements Comparable<ProjetoVO>{
	private String descricao = "Teste";
	private Long orcamento = 1000L;
	private Long prazo = 5L;
	private Set<QualificacaoVO> qualificacoesNecessariasNoProjeto= new HashSet<QualificacaoVO>();

	public ProjetoVO (){
		NecessidadeDeNegocioVO necessidadeDeNegocioVO = new NecessidadeDeNegocioVO();
		necessidadeDeNegocioVO.setProjetoVO(this);
		necessidadeDeNegocioVO.setComplexidade(10);
		necessidadeDeNegocioVO.setTamanho(48);
		necessidadeDeNegocioVO.setDescricao("Necessidade 1");
		getNecessidadesDeNegocio().add(necessidadeDeNegocioVO);
		necessidadeDeNegocioVO = new NecessidadeDeNegocioVO();
		necessidadeDeNegocioVO.setProjetoVO(this);
		necessidadeDeNegocioVO.setComplexidade(10);
		necessidadeDeNegocioVO.setTamanho(24);
		necessidadeDeNegocioVO.setDescricao("Necessidade 2");
		getNecessidadesDeNegocio().add(necessidadeDeNegocioVO);
		necessidadeDeNegocioVO = new NecessidadeDeNegocioVO();
		necessidadeDeNegocioVO.setProjetoVO(this);
		necessidadeDeNegocioVO.setComplexidade(10);
		necessidadeDeNegocioVO.setTamanho(100);
		necessidadeDeNegocioVO.setDescricao("Necessidade 3");
		getNecessidadesDeNegocio().add(necessidadeDeNegocioVO);
		qualificacoesNecessariasNoProjeto.addAll(JogoVO.getInstance().getQualificacoesDisponiveisNoJogo());
	}
	
	Set<NecessidadeDeNegocioVO> necessidadesDeNegocio = new TreeSet<NecessidadeDeNegocioVO>();

	public Set<NecessidadeDeNegocioVO> getNecessidadesDeNegocio() {
		return necessidadesDeNegocio;
	}

	public void setNecessidadesDeNegocio(Set<NecessidadeDeNegocioVO> necessidadesDeNegocio) {
		this.necessidadesDeNegocio = necessidadesDeNegocio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Long orcamento) {
		this.orcamento = orcamento;
	}

	public Long getPrazo() {
		return prazo;
	}

	public void setPrazo(Long prazo) {
		this.prazo = prazo;
	}

	public Integer getTamanho() {
		Integer tam = new Integer(0);
		Set<NecessidadeDeNegocioVO> necessidades = getNecessidadesDeNegocio();
		for (NecessidadeDeNegocioVO necessidadeDeNegocioVO : necessidades) {
			tam = necessidadeDeNegocioVO.getTamanho() + tam;
		}
		return tam;
	}

	@Override
	public int compareTo(ProjetoVO o) {
		return getDescricao().compareTo(o.getDescricao());
	}

	public Set<QualificacaoVO> getQualificacoesNecessariasNoProjeto() {
		return qualificacoesNecessariasNoProjeto;
	}

	public void setQualificacoesNecessariasNoProjeto(Set<QualificacaoVO> qualificacoesNecessariasNoProjeto) {
		this.qualificacoesNecessariasNoProjeto = qualificacoesNecessariasNoProjeto;
	}

}
