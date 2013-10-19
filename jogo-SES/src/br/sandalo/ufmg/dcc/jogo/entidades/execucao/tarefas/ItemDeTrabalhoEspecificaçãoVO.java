package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoEspecifica��oVO extends ItemDeTrabalhoVO {
	public ItemDeTrabalhoEspecifica��oVO(FuncaoVO funcaoVO, DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	@Override
	public void fecha() {
		Integer complexidade = getDemandaVO().getComplexidade();
		complexidade = (int) ((complexidade) - (complexidade * 0.3));
		getDemandaVO().setComplexidade(complexidade);
		getDemandaVO().setEstado(DemandaVO.Estado.ABERTA);
	}
}
