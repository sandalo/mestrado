package br.sandalo.ufmg.dcc.jogo.entidades.projeto;

import java.lang.reflect.InvocationTargetException;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;

public class QualificacaoVO implements Comparable<QualificacaoVO>{
	String descricao = null;
	Integer dificuldadeInerente;
	Integer ordem=0;
	private String urlIcone;
	QualificacaoVO(Integer dificuldadeInerente, String descricao, Integer ordem,String urlIcon){
		this.dificuldadeInerente = dificuldadeInerente;
		this.descricao = descricao;
		this.ordem = ordem;
		this.urlIcone=urlIcon;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getDificuldadeInerente() {
		return dificuldadeInerente;
	}
	public void setDificuldadeInerente(Integer dificuldadeInerente) {
		this.dificuldadeInerente = dificuldadeInerente;
	}
	
	public ItemDeTrabalhoVO getItemIntance(FuncaoVO funcaoVO, DemandaVO demandaVO){
		String pacoteItens = "br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.";
		String nomeClasseItem = "ItemDeTrabalho"+descricao+"VO";
		ItemDeTrabalhoVO newInstance = null;
		try {
			newInstance = (ItemDeTrabalhoVO) Class.forName(pacoteItens+nomeClasseItem).getConstructor(FuncaoVO.class,DemandaVO.class).newInstance(funcaoVO,demandaVO);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException().getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return newInstance;
	}
	@Override
	public int compareTo(QualificacaoVO o) {
		return getOrdem().compareTo(o.getOrdem());
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public String getUrlIcone() {
		return urlIcone;
	}
	public void setUrlIcone(String urlIcone) {
		this.urlIcone = urlIcone;
	}
}
