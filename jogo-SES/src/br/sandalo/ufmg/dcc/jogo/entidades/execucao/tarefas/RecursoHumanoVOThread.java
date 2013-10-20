package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import java.util.Date;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano.RecursoHumanoVO;

public class RecursoHumanoVOThread implements Runnable {
	RecursoHumanoVO recursoHumanoVO = null;

	public RecursoHumanoVOThread(RecursoHumanoVO recursoHumanoVO) {
		this.recursoHumanoVO = recursoHumanoVO;
	}

	@Override
	public void run() {
		try {
			while (true) {
				this.recursoHumanoVO.veirificaSeExisteItemParaTrabalhar();
				FuncaoVO funcaoAtual = this.recursoHumanoVO.getFuncaoAtual();
				ItemDeTrabalhoVO itemDeTrabalhoVO = funcaoAtual.getItemDeTrabalhoAtual();
				itemDeTrabalhoVO.setEstadoItem(ItemDeTrabalhoVO.EstadoItem.EM_EXECUÇÃO);
				Integer tamanho = itemDeTrabalhoVO.getDemandaVO().getTamanho();
				int realizado = 0;
				itemDeTrabalhoVO.setInicio(new Date());
				for (; realizado <= tamanho; realizado++) {
					Integer velocidade = itemDeTrabalhoVO.velocidade();
					Thread.sleep(velocidade);
					itemDeTrabalhoVO.setRealizado(realizado);
					System.out.println("Id: " + Thread.currentThread().getId() + " Name: " + Thread.currentThread().getName() + "velocidade: " + velocidade);
				}
				itemDeTrabalhoVO.setFim(new Date());
				itemDeTrabalhoVO.setEstadoItem(ItemDeTrabalhoVO.EstadoItem.FECHADO);
				recursoHumanoVO.getEstado().fecharItemDeTrabalho(itemDeTrabalhoVO);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
