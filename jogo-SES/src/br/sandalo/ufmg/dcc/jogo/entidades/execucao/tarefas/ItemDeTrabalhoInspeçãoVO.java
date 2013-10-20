package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoInspeçãoVO extends ItemDeTrabalhoVO{

	public ItemDeTrabalhoInspeçãoVO(FuncaoVO funcaoVO,DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
		ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO = ItemDeTrabalhoCodificaçãoVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
		if(itemDeTrabalhoCodificaçãoVO==null){
			throw new RuntimeException("Não há código para inspecionar!");
		}else if(itemDeTrabalhoCodificaçãoVO!=null && !itemDeTrabalhoCodificaçãoVO.getDemandaVO().getEstado().equals(DemandaVO.Estado.CONSTRUIDA)){
			throw new RuntimeException("Não há código pronto para inspecionar!");
		}
	}
	@Override
	public void fecha() {
		ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO = ItemDeTrabalhoCodificaçãoVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
		boolean possuiQualificacao = ItemDeTrabalhoVO.quemConstruiuEsteItemPossuiQualificacao(itemDeTrabalhoCodificaçãoVO);
		Integer qualidade = itemDeTrabalhoCodificaçãoVO.getQualidade();
		int qualidadeGarantida = 3;
		
		itemDeTrabalhoCodificaçãoVO.setQualidade(qualidadeGarantida);//Só por fazer a inspeção ganha 3 pontos de qualidade
		double qualidadePelaQualificacao = 0;
		if(possuiQualificacao){
			qualidadePelaQualificacao = qualidade*0.7;
		}
		double qualidadeFinalAposInspecao = 0;
		qualidadeFinalAposInspecao = qualidade + qualidadeGarantida + qualidadePelaQualificacao;
		itemDeTrabalhoCodificaçãoVO.setQualidade( (int) qualidadeFinalAposInspecao);
		if(ItemDeTrabalhoCodificaçãoVO.possuiErros(itemDeTrabalhoCodificaçãoVO)){
			String descricao = " - Inpec: "+ itemDeTrabalhoCodificaçãoVO.getDemandaVO().getDescricao();
			Integer complexidade = (int) (itemDeTrabalhoCodificaçãoVO.getDemandaVO().getComplexidade()*0.3);
			Integer tamanho = (int) (itemDeTrabalhoCodificaçãoVO.getDemandaVO().getTamanho()*.3);
			ItemDeTrabalhoCodificaçãoVO.registraBug(itemDeTrabalhoCodificaçãoVO, complexidade, descricao, tamanho);
		}
	}
}
