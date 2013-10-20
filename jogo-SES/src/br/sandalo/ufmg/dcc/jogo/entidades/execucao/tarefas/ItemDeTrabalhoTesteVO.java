package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoTesteVO extends ItemDeTrabalhoVO {

	private ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO;

	public ItemDeTrabalhoTesteVO(FuncaoVO funcaoVO, DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	@Override
	public void fecha() {
		if (quemConstruiuEsteItemPossuiQualificacao(this)) {
			this.itemDeTrabalhoCodifica��oVO = ItemDeTrabalhoCodifica��oVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
			Integer qualidade = itemDeTrabalhoCodifica��oVO.getQualidade();
			int qualidadeGarantida = 3;//j� ganha 3 pontos s� por testar
			this.itemDeTrabalhoCodifica��oVO.setQualidade(qualidadeGarantida);
			double qualidadePelaQualificacao = 0;
			if (quemConstruiuEsteItemPossuiQualificacao(this.itemDeTrabalhoCodifica��oVO)) {
				qualidadePelaQualificacao = qualidade * 0.7;
			}
			double qualidadeFinalAposInspecao = 0;
			qualidadeFinalAposInspecao = qualidade + qualidadeGarantida + qualidadePelaQualificacao;
			this.itemDeTrabalhoCodifica��oVO.setQualidade((int) qualidadeFinalAposInspecao);
		}
		if (ItemDeTrabalhoCodifica��oVO.possuiErros(this.itemDeTrabalhoCodifica��oVO)) {
			String descricao = " - Inpec: " + this.itemDeTrabalhoCodifica��oVO.getDemandaVO().getDescricao();
			Integer complexidade = (int) (this.itemDeTrabalhoCodifica��oVO.getDemandaVO().getComplexidade() * 0.3);
			Integer tamanho = (int) (this.itemDeTrabalhoCodifica��oVO.getDemandaVO().getTamanho() * .3);
			ItemDeTrabalhoCodifica��oVO.registraBug(this.itemDeTrabalhoCodifica��oVO, complexidade, descricao, tamanho);
		}
	}

}
