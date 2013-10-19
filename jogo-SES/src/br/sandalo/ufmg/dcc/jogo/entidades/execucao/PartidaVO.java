package br.sandalo.ufmg.dcc.jogo.entidades.execucao;

import java.util.Set;
import java.util.TreeSet;

import br.sandalo.ufmg.dcc.jogo.entidades.projeto.JogoVO;

public class PartidaVO {
	private JogoVO jogoVO = JogoVO.getInstance();
	
	private Set<GestaoDeProjetoVO> execuçãoDeProjetoVOs = new TreeSet<GestaoDeProjetoVO>();

	public Set<GestaoDeProjetoVO> getExecuçãoDeProjetoVOs() {
		return execuçãoDeProjetoVOs;
	}

	public void setExecuçãoDeProjetoVOs(Set<GestaoDeProjetoVO> execuçãoDeProjetoVOs) {
		this.execuçãoDeProjetoVOs = execuçãoDeProjetoVOs;
	}

	public JogoVO getJogoVO() {
		return jogoVO;
	}

	public void setJogoVO(JogoVO jogoVO) {
		this.jogoVO = jogoVO;
	}
}
