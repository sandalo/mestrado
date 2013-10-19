package br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano;

import java.util.HashMap;
import java.util.Map;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;

public abstract class RecursoHumanoEstado {
	
	private static Map<Class<? extends RecursoHumanoEstado>, Object>  recursoHumanoEstadoMap = new HashMap<Class<? extends RecursoHumanoEstado>, Object>();
	
	public static RecursoHumanoEstado getIntance(Class<? extends RecursoHumanoEstado> c){
		if(!recursoHumanoEstadoMap.containsKey(c))
			try {
				recursoHumanoEstadoMap.put(c, c.newInstance());
			} catch (Exception e){
				throw new RuntimeException("N�o foi possivel instanciar o estado!");
			}
		return (RecursoHumanoEstado) recursoHumanoEstadoMap.get(c);
	}
	public void recebeDemanda(DemandaVO demandaVO, FuncaoVO funcaoVO, RecursoHumanoVO recursoHumanoVO){
		throw new RuntimeException("Fun��o indisponivel");
	}
	public void iniciarItemDeTrabalho(RecursoHumanoVO recursoHumanoVO, ItemDeTrabalhoVO itemDeTrabalhoVO){
		throw new RuntimeException("Fun��o indisponivel");
	}
	public void pausarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO){
		throw new RuntimeException("Fun��o indisponivel");
	}
	public void cancelarItemDeTrabalho(RecursoHumanoVO recursoHumanoVO, ItemDeTrabalhoVO itemDeTrabalhoVO){
		throw new RuntimeException("Fun��o indisponivel");
	}
	
	public void acelerarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO){
		throw new RuntimeException("Fun��o indisponivel");
	}

	public void fecharItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO){
		throw new RuntimeException("Fun��o indisponivel");
	}
}
