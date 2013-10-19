package br.sandalo.ufmg.dcc.jogo.entidades.execucao;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeservi�o.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano.RecursoHumanoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.ProfissionalDeTIVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.QualificacaoVO;

public class GerenteVO {
	private GestaoDeProjetoVO execu��oDeProjetoVO;

	public GestaoDeProjetoVO getExecu��oDeProjetoVO() {
		return execu��oDeProjetoVO;
	}

	public void setExecu��oDeProjetoVO(GestaoDeProjetoVO execu��oDeProjetoVO) {
		this.execu��oDeProjetoVO = execu��oDeProjetoVO;
	}
	
	public void contratar(ProfissionalDeTIVO profissional){
		RecursoHumanoVO recursoHumanoVO = new RecursoHumanoVO(profissional,execu��oDeProjetoVO);
		getExecu��oDeProjetoVO().getRecursos().add(recursoHumanoVO);
	}

	public void atribuirFuncao(RecursoHumanoVO recursoHumanoVO, QualificacaoVO qualificacaoVO){
		FuncaoVO funcaoVO = new FuncaoVO();
		funcaoVO.setQualificacao(qualificacaoVO);
		funcaoVO.setRecursoHumanoResponsavelVO(recursoHumanoVO);
		recursoHumanoVO.getFuncoes().add(funcaoVO);
	}

	public void atribuiDemanda(DemandaVO demandaVO, RecursoHumanoVO recursoHumanoResponsavelVO, FuncaoVO funcaoVO){
		recursoHumanoResponsavelVO.recebeDemanda(demandaVO,funcaoVO);
	}
	
	public void iniciarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO){
		itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().iniciarItemDeTrabalho(itemDeTrabalhoVO);
	}

	public void pausarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO){
		itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().pausarItemDeTrabalho(itemDeTrabalhoVO);
	}

	public void acelerarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO){
		try {
			itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().acelerarItemDeTrabalho(itemDeTrabalhoVO);
		} catch (Exception e) {
			System.out.println("");
		}
	}
	
}
