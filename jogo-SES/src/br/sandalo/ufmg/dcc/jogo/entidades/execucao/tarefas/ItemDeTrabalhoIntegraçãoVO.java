package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoIntegra��oVO extends ItemDeTrabalhoVO {

	private ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO;

	public ItemDeTrabalhoIntegra��oVO(FuncaoVO funcaoVO, DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
		if (!demandaVO.getEstado().equals(DemandaVO.Estado.CONSTRUIDA)) {
			if (demandaVO.getEstado().equals(DemandaVO.Estado.ABERTA) || demandaVO.getEstado().equals(DemandaVO.Estado.EXECUTANDO)){
				throw new RuntimeException("A demanda ainda n�o foi constru�da, voc� precisa codific�-la.");
			}else if (demandaVO.getEstado().equals(DemandaVO.Estado.ENTREGUE)) {
				throw new RuntimeException("Essa demanda j� foi entregue.");
			}
		}
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





	public ItemDeTrabalhoCodifica��oVO getItemDeTrabalhoCodifica��oVO() {
		return itemDeTrabalhoCodifica��oVO;
	}

	public void setItemDeTrabalhoCodifica��oVO(ItemDeTrabalhoCodifica��oVO itemDeTrabalhoCodifica��oVO) {
		// this.itemDeTrabalhoCodifica��oVO = itemDeTrabalhoCodifica��oVO;
	}
}
