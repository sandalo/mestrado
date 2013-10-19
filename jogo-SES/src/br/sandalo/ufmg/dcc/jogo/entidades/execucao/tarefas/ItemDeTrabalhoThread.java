package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import java.util.Date;


public class ItemDeTrabalhoThread implements Runnable {
	ItemDeTrabalhoVO itemDeTrabalhoVO = null;

	public ItemDeTrabalhoThread(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		this.itemDeTrabalhoVO = itemDeTrabalhoVO;
	}

	@Override
	public void run() {
		try {
			Integer tamanho = itemDeTrabalhoVO.getDemandaVO().getTamanho();
			int realizado = 0;
			itemDeTrabalhoVO.setInicio(new Date());
			for (; realizado <= tamanho; realizado++) {
				Integer velocidade = itemDeTrabalhoVO.velocidade();
				Thread.sleep(velocidade);
				itemDeTrabalhoVO.setRealizado(realizado);
				System.out.println("Id: "+Thread.currentThread().getId()+" Name: "+Thread.currentThread().getName()+ "velocidade: "+velocidade);
			}
			itemDeTrabalhoVO.setFim(new Date());
			itemDeTrabalhoVO.setEstadoItem(ItemDeTrabalhoVO.EstadoItem.FECHADO);
			itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().getEstado().fecharItemDeTrabalho(itemDeTrabalhoVO);
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
