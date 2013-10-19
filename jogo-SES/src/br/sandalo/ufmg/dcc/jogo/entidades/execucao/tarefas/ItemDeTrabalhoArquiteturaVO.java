package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;

public class ItemDeTrabalhoArquiteturaVO extends ItemDeTrabalhoVO{

	public ItemDeTrabalhoArquiteturaVO(FuncaoVO funcaoVO,DemandaVO demandaVO) {
		super(funcaoVO, demandaVO);
	}

	
	@Override
	public void fecha() {
		Integer complexidade = getDemandaVO().getComplexidade();
		complexidade = (int) ((complexidade) - (complexidade*0.6)); 
		getDemandaVO().setComplexidade(complexidade);
		getDemandaVO().setEstado(DemandaVO.Estado.ABERTA);
	}

}
