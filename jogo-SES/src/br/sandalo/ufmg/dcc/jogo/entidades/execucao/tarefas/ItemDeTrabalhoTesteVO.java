package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoTesteVO extends ItemDeTrabalhoVO{

	private ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO;

	public ItemDeTrabalhoTesteVO(FuncaoVO funcaoVO,DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	@Override
	public void fecha() {
		ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO = ItemDeTrabalhoCodifica��oVO.recuperaUltimoCodigoDaDemanda(getDemandaVO());
		this.itemDeTrabalhoCodifica��oVO = itemDeTrabalhoCodifica��oVO;
		String descricao = itemDeTrabalhoCodifica��oVO.getDemandaVO().getDescricao();
		Integer complexidade = itemDeTrabalhoCodifica��oVO.getDemandaVO().getComplexidade();
		Integer tamanho = itemDeTrabalhoCodifica��oVO.getDemandaVO().getTamanho();

		if(ItemDeTrabalhoCodifica��oVO.possuiErros(itemDeTrabalhoCodifica��oVO)){
			ItemDeTrabalhoCodifica��oVO.registraBug(itemDeTrabalhoCodifica��oVO,complexidade,descricao,tamanho);
		}
		System.out.println();
		getDemandaVO().setEstado(DemandaVO.Estado.ENTREGUE);
	}

}
