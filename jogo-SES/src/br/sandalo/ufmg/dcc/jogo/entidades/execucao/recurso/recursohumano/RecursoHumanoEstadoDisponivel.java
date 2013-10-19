package br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;

public class RecursoHumanoEstadoDisponivel extends RecursoHumanoEstado {
	@Override
	public void recebeDemanda(DemandaVO demandaVO, FuncaoVO funcaoVO, RecursoHumanoVO recursoHumanoVO) {
		ItemDeTrabalhoVO itemDeTrabalhoVO = funcaoVO.getQualificacao().getItemIntance(funcaoVO, demandaVO);
		funcaoVO.recebeDemanda(demandaVO, itemDeTrabalhoVO);
	}

	@Override
	public void iniciarItemDeTrabalho(RecursoHumanoVO recursoHumanoVO, ItemDeTrabalhoVO itemDeTrabalhoVO) {
		recursoHumanoVO.setEstado(RecursoHumanoEstado.getIntance(RecursoHumanoEstadoOcupado.class));
		recursoHumanoVO.setFuncaoAtual(itemDeTrabalhoVO.getFuncaoVO());
		itemDeTrabalhoVO.start();
	}
}
