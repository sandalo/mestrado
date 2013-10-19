package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoIntegraçãoVO extends ItemDeTrabalhoVO {

	private ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO;

	public ItemDeTrabalhoIntegraçãoVO(FuncaoVO funcaoVO, DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
		if (!demandaVO.getEstado().equals(DemandaVO.Estado.CONSTRUIDA)) {
			if (demandaVO.getEstado().equals(DemandaVO.Estado.ABERTA) || demandaVO.getEstado().equals(DemandaVO.Estado.EXECUTANDO)){
				throw new RuntimeException("A demanda ainda não foi construída, você precisa codificá-la.");
			}else if (demandaVO.getEstado().equals(DemandaVO.Estado.ENTREGUE)) {
				throw new RuntimeException("Essa demanda já foi entregue.");
			}
		}
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





	public ItemDeTrabalhoCodificaçãoVO getItemDeTrabalhoCodificaçãoVO() {
		return itemDeTrabalhoCodificaçãoVO;
	}

	public void setItemDeTrabalhoCodificaçãoVO(ItemDeTrabalhoCodificaçãoVO itemDeTrabalhoCodificaçãoVO) {
		// this.itemDeTrabalhoCodificaçãoVO = itemDeTrabalhoCodificaçãoVO;
	}
}
