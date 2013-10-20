package br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço;

public class BugVO extends DemandaVO {
	public BugVO() {
	}
	public BugVO(Integer complexidade,String descricao, Integer tamanho) {
		setComplexidade(complexidade);
		setDescricao("Bug: " + descricao);
		setTamanho(tamanho);
		setEstado(DemandaVO.Estado.ABERTA);
	}
}
