package br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso;

import java.util.HashSet;
import java.util.Set;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano.RecursoHumanoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO.EstadoItem;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.QualificacaoVO;
/**
 * Papel
 * 
 * */
public class FuncaoVO implements Comparable<FuncaoVO>{

	private Set<ItemDeTrabalhoVO> itensDeTrabalho = new HashSet<ItemDeTrabalhoVO>();
	private RecursoHumanoVO recursoHumanoResponsavelVO;
	private QualificacaoVO qualificacao;
	private ItemDeTrabalhoVO itemDeTrabalhoAtual;
	
	public Set<ItemDeTrabalhoVO> getItensDeTrabalhoAbertosExecucao() {
		Set<ItemDeTrabalhoVO> itensDeTrabalhoAbertos = new HashSet<ItemDeTrabalhoVO>();
		for (ItemDeTrabalhoVO itemDeTrabalhoVO : itensDeTrabalho) {
			if(itemDeTrabalhoVO.getEstadoItem().equals(EstadoItem.ABERTO)||
					itemDeTrabalhoVO.getEstadoItem().equals(EstadoItem.EM_EXECUÇÃO)){
				itensDeTrabalhoAbertos.add(itemDeTrabalhoVO);
			}
		}
		return itensDeTrabalhoAbertos;
	}

	public Set<ItemDeTrabalhoVO> getItensDeTrabalhoFechados() {
		Set<ItemDeTrabalhoVO> itensDeTrabalhoFechados = new HashSet<ItemDeTrabalhoVO>();
		for (ItemDeTrabalhoVO itemDeTrabalhoVO : itensDeTrabalho) {
			if(itemDeTrabalhoVO.getEstadoItem().equals(EstadoItem.FECHADO)){
				itensDeTrabalhoFechados.add(itemDeTrabalhoVO);
			}
		}
		return itensDeTrabalhoFechados;
	}	
	public void setItensDeTrabalhoFechados(Set<ItemDeTrabalhoVO> itensDeTrabalho) {
	
	}

	
	public Set<ItemDeTrabalhoVO> getItensDeTrabalho() {
		return itensDeTrabalho;
	}


	public void setItensDeTrabalho(Set<ItemDeTrabalhoVO> itensDeTrabalho) {
		this.itensDeTrabalho = itensDeTrabalho;
	}

	public String getNome() {
		return "Função: "+getQualificacao().getDescricao();
	}


	public RecursoHumanoVO getRecursoHumanoResponsavelVO() {
		return recursoHumanoResponsavelVO;
	}


	public void setRecursoHumanoResponsavelVO(RecursoHumanoVO recursoHumanoResponsavelVO) {
		this.recursoHumanoResponsavelVO = recursoHumanoResponsavelVO;
	}


	public QualificacaoVO getQualificacao() {
		return qualificacao;
	}


	public void setQualificacao(QualificacaoVO qualificacao) {
		this.qualificacao = qualificacao;
	}

	public ItemDeTrabalhoVO getItemDeTrabalhoAtual() {
		return itemDeTrabalhoAtual;
	}


	public void setItemDeTrabalhoAtual(ItemDeTrabalhoVO itemDeTrabalhoAtual) {
		this.itemDeTrabalhoAtual = itemDeTrabalhoAtual;
	}


	@Override
	public int compareTo(FuncaoVO o) {
		return getQualificacao().getOrdem().compareTo(o.getQualificacao().getOrdem());
	}
	
	public void recebeDemanda(DemandaVO demandaVO, ItemDeTrabalhoVO itemDeTrabalhoVO){
		
		liberaDemandaAnterior();//Se estiver trabalhando em alguma demanda, libera a demanda e recebe a nova demanda
		
		itemDeTrabalhoVO.setDemandaVO(demandaVO);
		getItensDeTrabalho().add(itemDeTrabalhoVO);
		demandaVO.getItensDeTrabalho().add(itemDeTrabalhoVO);
		itemDeTrabalhoVO.setFuncaoVO(this);
		demandaVO.setEstado(DemandaVO.Estado.EXECUTANDO);
		this.setItemDeTrabalhoAtual(itemDeTrabalhoVO);
	}

	protected void liberaDemandaAnterior() {
		ItemDeTrabalhoVO itemDeTrabalhoAtual = this.getItemDeTrabalhoAtual();
		if(itemDeTrabalhoAtual!=null){
			DemandaVO demandaAnteriorVO2 = itemDeTrabalhoAtual.getDemandaVO();
			demandaAnteriorVO2.setEstado(DemandaVO.Estado.ABERTA);
			demandaAnteriorVO2.getItensDeTrabalho().remove(itemDeTrabalhoAtual);
		}
	}
	
	public Integer dificuldadeInerente(){
		return qualificacao.getDificuldadeInerente();
	}
}
