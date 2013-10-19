package br.sandalo.ufmg.dcc.jogo.entidades.projeto;

import java.util.Set;
import java.util.TreeSet;


public class ProfissionalDeTIVO implements Comparable<ProfissionalDeTIVO>{

	private Set<QualificacaoVO> qualificacoes = new TreeSet<QualificacaoVO>();
	private String nome;
	private String urlFoto;
	private Integer experiencia;
	private Integer agilidade;
	private Long salario;
	private boolean boaIndicacao;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}
	public Long getSalario() {
		return salario;
	}
	public void setSalario(Long salario) {
		this.salario = salario;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public Set<QualificacaoVO> getQualificacoes() {
		return qualificacoes;
	}
	public void setQualificacoes(Set<QualificacaoVO> qualificacoes) {
		this.qualificacoes = qualificacoes;
	}
	
	@Override
	public int compareTo(ProfissionalDeTIVO o) {
		return getNome().compareTo(o.getNome());
	}
	public Integer getAgilidade() {
		return agilidade;
	}
	public void setAgilidade(Integer agilidade) {
		this.agilidade = agilidade;
	}
	public boolean isBoaIndicacao() {
		return boaIndicacao;
	}
	public void setBoaIndicacao(boolean boaIndicacao) {
		this.boaIndicacao = boaIndicacao;
	}
}
