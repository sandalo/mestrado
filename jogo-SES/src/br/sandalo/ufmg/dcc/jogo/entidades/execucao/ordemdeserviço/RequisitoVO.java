package br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço;

import br.sandalo.ufmg.dcc.jogo.entidades.projeto.NecessidadeDeNegocioVO;

public class RequisitoVO extends DemandaVO{
	private NecessidadeDeNegocioVO necessidadeDeNegocioVO;

	public NecessidadeDeNegocioVO getNecessidadeDeNegocioVO() {
		return necessidadeDeNegocioVO;
	}

	public void setNecessidadeDeNegocioVO(NecessidadeDeNegocioVO necessidadeDeNegocioVO) {
		this.necessidadeDeNegocioVO = necessidadeDeNegocioVO;
	}

}
