package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoInspe��oVO extends ItemDeTrabalhoVO{

	public ItemDeTrabalhoInspe��oVO(FuncaoVO funcaoVO,DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
		ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO = ItemDeTrabalhoCodifica��oVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
		if(itemDeTrabalhoCodifica��oVO==null){
			throw new RuntimeException("N�o h� c�digo para inspecionar!");
		}else if(itemDeTrabalhoCodifica��oVO!=null && !itemDeTrabalhoCodifica��oVO.getDemandaVO().getEstado().equals(DemandaVO.Estado.CONSTRUIDA)){
			throw new RuntimeException("N�o h� c�digo pronto para inspecionar!");
		}
	}
	@Override
	public void fecha() {
		ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO = ItemDeTrabalhoCodifica��oVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
		boolean possuiQualificacao = ItemDeTrabalhoVO.quemConstruiuEsteItemPossuiQualificacao(itemDeTrabalhoCodifica��oVO);
		Integer qualidade = itemDeTrabalhoCodifica��oVO.getQualidade();
		int qualidadeGarantida = 3;
		
		itemDeTrabalhoCodifica��oVO.setQualidade(qualidadeGarantida);//S� por fazer a inspe��o ganha 3 pontos de qualidade
		double qualidadePelaQualificacao = 0;
		if(possuiQualificacao){
			qualidadePelaQualificacao = qualidade*0.7;
		}
		double qualidadeFinalAposInspecao = 0;
		qualidadeFinalAposInspecao = qualidade + qualidadeGarantida + qualidadePelaQualificacao;
		itemDeTrabalhoCodifica��oVO.setQualidade( (int) qualidadeFinalAposInspecao);
		if(ItemDeTrabalhoCodifica��oVO.possuiErros(itemDeTrabalhoCodifica��oVO)){
			String descricao = " - Inpec: "+ itemDeTrabalhoCodifica��oVO.getDemandaVO().getDescricao();
			Integer complexidade = (int) (itemDeTrabalhoCodifica��oVO.getDemandaVO().getComplexidade()*0.3);
			Integer tamanho = (int) (itemDeTrabalhoCodifica��oVO.getDemandaVO().getTamanho()*.3);
			ItemDeTrabalhoCodifica��oVO.registraBug(itemDeTrabalhoCodifica��oVO, complexidade, descricao, tamanho);
		}
	}
}
