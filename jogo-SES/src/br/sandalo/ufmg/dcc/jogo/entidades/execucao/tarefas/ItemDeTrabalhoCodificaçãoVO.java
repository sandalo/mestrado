package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.BugVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.QualificacaoVO;

public class ItemDeTrabalhoCodificaçãoVO extends ItemDeTrabalhoVO {


	public ItemDeTrabalhoCodificaçãoVO(FuncaoVO funcaoVO, DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	@Override
	public void fecha() {
		defineQualidade();
		getDemandaVO().setEstado(DemandaVO.Estado.CONSTRUIDA);
	}


	public void defineQualidade() {
		boolean possuiQualificacao = ItemDeTrabalhoVO.recursoPossuiQualificacaoParaExecutarEsteItem(this);
		
		Integer nivelDeEstress = getFuncaoVO().getRecursoHumanoResponsavelVO().getNivelDeStress();// 15
		Integer experiencia = getFuncaoVO().getRecursoHumanoResponsavelVO().getProfissionalDeTIVO().getExperiencia();
		Integer urgencia = getUrgencia();// 15
		Integer complexidade = getDemandaVO().getComplexidade();// 15
		Integer aux = null;
		aux = calculaQualidade(possuiQualificacao, nivelDeEstress, experiencia, urgencia,complexidade);

		if (aux < 0) {
			aux = 1;
		} else if (aux > 15) {
			aux = 15;
		}
		this.setQualidade(aux);
	}

	protected Integer calculaQualidade(boolean possuiQualificacao, Integer nivelDeEstress, Integer experiencia, Integer urgencia, Integer complexidade ) {
		Integer aux = 0;
		if (experiencia > 0)
			aux = (int) (aux + (experiencia+(experiencia*0.5)));
		if (nivelDeEstress > 0)
			aux = (aux - (nivelDeEstress / 2));
		if (urgencia > 0)
			aux = (aux - (urgencia / 2));
		if(complexidade >  0){
			aux = (aux - (complexidade / 2));
		}
		if(!possuiQualificacao){
			aux = (int) (aux-(aux*0.8)); 
		}
		if(aux<0)
			aux=0;
		return aux;
	}

	public static ItemDeTrabalhoCodificaçãoVO recuperaUltimoCodigoDaDemanda(DemandaVO demandaVO) {
		List<ItemDeTrabalhoVO> itens = new ArrayList<ItemDeTrabalhoVO>(demandaVO.getItensDeTrabalho());
		Collections.reverse(itens);
		ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO = null;
		for (ItemDeTrabalhoVO itemDeTrabalhoVO : itens) {
			if (itemDeTrabalhoVO instanceof ItemDeTrabalhoCodificaçãoVO) {
				itemDeTrabalhoCodificaçãoVO = (ItemDeTrabalhoCodificaçãoVO) itemDeTrabalhoVO;
			}
		}
		return itemDeTrabalhoCodificaçãoVO;
	}
	
	public static boolean possuiErros(ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO) {
		if(itemDeTrabalhoCodificaçãoVO.getQualidade()==0){
			return true;
		}
		float i = ((float) itemDeTrabalhoCodificaçãoVO.getQualidade() / 15);
		float qualidadePercentual = (i * 100);
		Integer probalilidade = getProbablidadeDeErros();
		if (probalilidade > qualidadePercentual) {
			return true;
		}else{
			return false;
		}
	}

	private static Integer getProbablidadeDeErros() {
		Random random = new Random();
		Integer numeroRandomico = random.nextInt(100);
		return numeroRandomico;
	}

	public static void registraBug(ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO, Integer complexidade, String descricao, Integer tamanho) {
		BugVO bugVO = new BugVO();
		bugVO.setComplexidade(complexidade);
		bugVO.setDescricao("Bug: " + descricao);
		bugVO.setTamanho(tamanho);
		bugVO.setEstado(DemandaVO.Estado.ABERTA);
		itemDeTrabalhoCodificaçãoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().getGestaoDeProjetoVO().getDemandas().add(bugVO);
	}
}
