package br.sandalo.ufmg.dcc.jogo.entidades.projeto;

import java.util.Set;

public class ContratemposPossiveisVO {
	private JogoVO jogoVO = JogoVO.getInstance();
	private Set<ProfissionalDeTIVO> profissionaisDeTI;

	public JogoVO getJogoVO() {
		return jogoVO;
	}

	public void setJogoVO(JogoVO jogoVO) {
		this.jogoVO = jogoVO;
	}

	public Set<ProfissionalDeTIVO> getProfissionaisDeTI() {
		return profissionaisDeTI;
	}

	public void setProfissionaisDeTI(Set<ProfissionalDeTIVO> profissionaisDeTI) {
		this.profissionaisDeTI = profissionaisDeTI;
	}
}
