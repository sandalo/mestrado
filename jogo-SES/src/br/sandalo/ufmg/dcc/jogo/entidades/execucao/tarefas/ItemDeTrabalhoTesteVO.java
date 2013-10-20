package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoTesteVO extends ItemDeTrabalhoVO {

	private ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO;

	public ItemDeTrabalhoTesteVO(FuncaoVO funcaoVO, DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	@Override
	public void fecha() {
		if (quemConstruiuEsteItemPossuiQualificacao(this)) {
			this.itemDeTrabalhoCodificaçãoVO = ItemDeTrabalhoCodificaçãoVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
			Integer qualidade = itemDeTrabalhoCodificaçãoVO.getQualidade();
			int qualidadeGarantida = 3;//já ganha 3 pontos só por testar
			this.itemDeTrabalhoCodificaçãoVO.setQualidade(qualidadeGarantida);
			double qualidadePelaQualificacao = 0;
			if (quemConstruiuEsteItemPossuiQualificacao(this.itemDeTrabalhoCodificaçãoVO)) {
				qualidadePelaQualificacao = qualidade * 0.7;
			}
			double qualidadeFinalAposInspecao = 0;
			qualidadeFinalAposInspecao = qualidade + qualidadeGarantida + qualidadePelaQualificacao;
			this.itemDeTrabalhoCodificaçãoVO.setQualidade((int) qualidadeFinalAposInspecao);
		}
		if (ItemDeTrabalhoCodificaçãoVO.possuiErros(this.itemDeTrabalhoCodificaçãoVO)) {
			String descricao = " - Inpec: " + this.itemDeTrabalhoCodificaçãoVO.getDemandaVO().getDescricao();
			Integer complexidade = (int) (this.itemDeTrabalhoCodificaçãoVO.getDemandaVO().getComplexidade() * 0.3);
			Integer tamanho = (int) (this.itemDeTrabalhoCodificaçãoVO.getDemandaVO().getTamanho() * .3);
			ItemDeTrabalhoCodificaçãoVO.registraBug(this.itemDeTrabalhoCodificaçãoVO, complexidade, descricao, tamanho);
		}
	}

}
