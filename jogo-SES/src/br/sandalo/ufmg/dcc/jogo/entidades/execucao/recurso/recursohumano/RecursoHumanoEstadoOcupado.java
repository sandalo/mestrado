package br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;

public class RecursoHumanoEstadoOcupado extends RecursoHumanoEstado {

	@Override
	public void iniciarItemDeTrabalho(RecursoHumanoVO recursoHumanoVO, ItemDeTrabalhoVO itemDeTrabalhoVO) {
		throw new RuntimeException("Estou ocupado. Não posso receber demanda!");
	}
	
	@Override
	public void pausarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		itemDeTrabalhoVO.stop();
	}
	
	@Override
	public void acelerarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		itemDeTrabalhoVO.aumentaUrgencia();
	}
	
	@Override
	public void fecharItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().setEstado(RecursoHumanoEstado.getIntance(RecursoHumanoEstadoDisponivel.class));
		itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().getFuncaoAtual().setItemDeTrabalhoAtual(null);
		itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().setFuncaoAtual(null);
		itemDeTrabalhoVO.fecha();
	}
	
}
