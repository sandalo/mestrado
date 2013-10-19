package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoTesteVO extends ItemDeTrabalhoVO{

	private ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO;

	public ItemDeTrabalhoTesteVO(FuncaoVO funcaoVO,DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	@Override
	public void fecha() {
		ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO = ItemDeTrabalhoCodificaçãoVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
		this.itemDeTrabalhoCodificaçãoVO = itemDeTrabalhoCodificaçãoVO;
		String descricao = itemDeTrabalhoCodificaçãoVO.getDemandaVO().getDescricao();
		Integer complexidade = itemDeTrabalhoCodificaçãoVO.getDemandaVO().getComplexidade();
		Integer tamanho = itemDeTrabalhoCodificaçãoVO.getDemandaVO().getTamanho();

		if(ItemDeTrabalhoCodificaçãoVO.possuiErros(itemDeTrabalhoCodificaçãoVO)){
			ItemDeTrabalhoCodificaçãoVO.registraBug(itemDeTrabalhoCodificaçãoVO,complexidade,descricao,tamanho);
		}
		System.out.println();
		getDemandaVO().setEstado(DemandaVO.Estado.ENTREGUE);
	}

}
