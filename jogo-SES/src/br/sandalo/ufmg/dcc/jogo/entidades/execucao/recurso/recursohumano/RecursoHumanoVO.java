package br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano;

import java.util.Set;
import java.util.TreeSet;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.GestaoDeProjetoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.RecursoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.ProfissionalDeTIVO;

public class RecursoHumanoVO extends RecursoVO {

	private GestaoDeProjetoVO gestaoDeProjetoVO;
	private ProfissionalDeTIVO profissionalDeTIVO;
	private Set<FuncaoVO> funcoes = new TreeSet<FuncaoVO>();
	private FuncaoVO funcaoAtual;
	private RecursoHumanoEstado estado;
	private Integer nivelDeStress = new Integer(0);

	public RecursoHumanoVO() {
	}

	public RecursoHumanoVO(ProfissionalDeTIVO profissionalDeTIVO, GestaoDeProjetoVO gestaoDeProjetoVO) {
		this.gestaoDeProjetoVO = gestaoDeProjetoVO;
		this.profissionalDeTIVO = profissionalDeTIVO;
/*		Set<QualificacaoVO> qualificacoes = getGestaoDeProjetoVO().getProjetoVO().getQualificacoesNecessariasNoProjeto();
		for (QualificacaoVO qualificacao : qualificacoes) {
			FuncaoVO funcaoVO = new FuncaoVO();
			funcaoVO.setQualificacao(qualificacao);
			funcaoVO.setRecursoHumanoResponsavelVO(this);
			this.getFuncoes().add(funcaoVO);
		}
*/		estado = RecursoHumanoEstado.getIntance(RecursoHumanoEstadoDisponivel.class);
	}

	public ProfissionalDeTIVO getProfissionalDeTIVO() {
		return profissionalDeTIVO;
	}

	@Override
	public String getNome() {
		return "Funcionário: " + profissionalDeTIVO.getNome();
	}

	public Set<FuncaoVO> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Set<FuncaoVO> funcoes) {
		this.funcoes = funcoes;
	}

	public FuncaoVO getFuncaoAtual() {
		return funcaoAtual;
	}

	public void setFuncaoAtual(FuncaoVO funcaoAtual) {
		this.funcaoAtual = funcaoAtual;
	}

	public void recebeDemanda(DemandaVO demandaVO, FuncaoVO funcaoVO) {
		estado.recebeDemanda(demandaVO, funcaoVO, this);
	}

	public void iniciarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		estado.iniciarItemDeTrabalho(this, itemDeTrabalhoVO);
	}

	public void pausarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		estado.pausarItemDeTrabalho(itemDeTrabalhoVO);
	}

	public void acelerarItemDeTrabalho(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		stressar();
		estado.acelerarItemDeTrabalho(itemDeTrabalhoVO);
	}

	public RecursoHumanoEstado getEstado() {
		return estado;
	}

	public void setEstado(RecursoHumanoEstado estado) {
		this.estado = estado;
	}

	public GestaoDeProjetoVO getGestaoDeProjetoVO() {
		return gestaoDeProjetoVO;
	}

	public void setGestaoDeProjetoVO(GestaoDeProjetoVO gestaoDeProjetoVO) {
		this.gestaoDeProjetoVO = gestaoDeProjetoVO;
	}

	public void stressar() {
		if (nivelDeStress < 15)
			nivelDeStress = nivelDeStress + 1;
	}

	public String getUrlFotoStress() {
		if(nivelDeStress >= 0 && nivelDeStress < 5)
			return "/imagens/estress01.png";
		if(nivelDeStress >=5 && nivelDeStress < 10)
			return "/imagens/estress02.png";
		if(nivelDeStress >=10)
			return "/imagens/estress03.png";
		return "";
	}

	public void setUrlFotoStress(String urlFotoStress) {
		//this.urlFotoStress = urlFotoStress;
	}

	public Integer getNivelDeStress() {
		return nivelDeStress;
	}
}
