package br.sandalo.ufmg.dcc.jogo.entidades.execucao;

import java.util.Set;
import java.util.TreeSet;

import br.sandalo.ufmg.dcc.jogo.entidades.projeto.JogoVO;

public class PartidaVO {
	private JogoVO jogoVO = JogoVO.getInstance();
	
	private Set<GestaoDeProjetoVO> execu��oDeProjetoVOs = new TreeSet<GestaoDeProjetoVO>();

	public Set<GestaoDeProjetoVO> getExecu��oDeProjetoVOs() {
		return execu��oDeProjetoVOs;
	}

	public void setExecu��oDeProjetoVOs(Set<GestaoDeProjetoVO> execu��oDeProjetoVOs) {
		this.execu��oDeProjetoVOs = execu��oDeProjetoVOs;
	}

	public JogoVO getJogoVO() {
		return jogoVO;
	}

	public void setJogoVO(JogoVO jogoVO) {
		this.jogoVO = jogoVO;
	}
}
