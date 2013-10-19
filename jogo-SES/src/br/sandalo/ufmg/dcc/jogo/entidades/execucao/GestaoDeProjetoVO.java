package br.sandalo.ufmg.dcc.jogo.entidades.execucao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.RequisitoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.RecursoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano.RecursoHumanoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.NecessidadeDeNegocioVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.ProjetoVO;

public class GestaoDeProjetoVO {
	public int UM_MILISEGUNDO = 1;
	public int UM_SEGUNDO = UM_MILISEGUNDO * 1000;
	public int UM_MINUTO = UM_SEGUNDO * 60;

	public enum Estado {
		AGUARDANDO_INICIO, INICIADO, FINALIZADO
	}

	private ThreadGroup threadGroup = new ThreadGroup("GestaoDeProjetoVO");

	private Estado estado = Estado.AGUARDANDO_INICIO;

	private ProjetoVO projetoVO = new ProjetoVO();

	private Set<DemandaVO> demandas = new TreeSet<DemandaVO>();

	private Set<ContratempoOcorridoVO> contratempoOcorridoVOs = new TreeSet<ContratempoOcorridoVO>();

	private Set<RecursoVO> recursos = new TreeSet<RecursoVO>();

	private PartidaVO partidaVO = new PartidaVO();

	private GerenteVO gerenteVO;

	private Date dataInicio;

	private Date dataFim;

	public GestaoDeProjetoVO() {
		gerenteVO = new GerenteVO();
		gerenteVO.setExecuçãoDeProjetoVO(this);
		Set<NecessidadeDeNegocioVO> necessidadeDeNegocioVOs = getProjetoVO().getNecessidadesDeNegocio();
		for (NecessidadeDeNegocioVO necessidadeDeNegocioVO : necessidadeDeNegocioVOs) {
			RequisitoVO requisitoVO = new RequisitoVO();
			requisitoVO.setNecessidadeDeNegocioVO(necessidadeDeNegocioVO);
			requisitoVO.setDescricao("Requisito para: " + necessidadeDeNegocioVO.getDescricao());
			requisitoVO.setComplexidade(necessidadeDeNegocioVO.getComplexidade());
			requisitoVO.setTamanho(necessidadeDeNegocioVO.getTamanho());
			demandas.add(requisitoVO);
		}
	}

	public PartidaVO getPartidaVO() {
		return partidaVO;
	}

	public Set<ContratempoOcorridoVO> getContratempoOcorridoVOs() {
		return contratempoOcorridoVOs;
	}

	public void setContratempoOcorridoVOs(Set<ContratempoOcorridoVO> contratempoOcorridoVOs) {
		this.contratempoOcorridoVOs = contratempoOcorridoVOs;
	}

	public void setPartidaVO(PartidaVO partidaVO) {
		this.partidaVO = partidaVO;
	}

	public Set<DemandaVO> getDemandas() {
		return demandas;
	}

	public void setDemandas(Set<DemandaVO> demandas) {
		this.demandas = demandas;
	}

	public Set<DemandaVO> getDemandasAbertasOuConstruidas() {
		br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO.Estado estado;
		Set<DemandaVO> demandasAbertasOuConstruidas;
		estado = DemandaVO.Estado.ABERTA;
		demandasAbertasOuConstruidas = recuperaDemandaPorEstado(estado);
		estado = DemandaVO.Estado.CONSTRUIDA;
		demandasAbertasOuConstruidas.addAll(recuperaDemandaPorEstado(estado));
		return demandasAbertasOuConstruidas;
	}

	public Set<DemandaVO> getDemandasEntregues() {
		br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO.Estado estado = DemandaVO.Estado.ENTREGUE;
		Set<DemandaVO> demandasAbertas = recuperaDemandaPorEstado(estado);
		return demandasAbertas;
	}

	public void getDemandasEntregues(Set<DemandaVO> demandas) {
	}

	protected Set<DemandaVO> recuperaDemandaPorEstado(br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO.Estado estado) {
		Set<DemandaVO> demandasAbertas = new HashSet<DemandaVO>();
		for (DemandaVO demanda : demandas) {
			if (demanda.getEstado().equals(estado))
				demandasAbertas.add(demanda);
		}
		return demandasAbertas;
	}

	public void setDemandasAbertas(Set<DemandaVO> demandas) {
	}

	public ProjetoVO getProjetoVO() {
		return projetoVO;
	}

	public void setProjetoVO(ProjetoVO projetoVO) {
		this.projetoVO = projetoVO;
	}

	public Set<RecursoVO> getRecursos() {
		return recursos;
	}

	public void setRecursos(Set<RecursoVO> recursos) {
		this.recursos = recursos;
	}

	public Set<RecursoHumanoVO> getEquipe() {
		Set<RecursoHumanoVO> equipe = new TreeSet<RecursoHumanoVO>();
		for (RecursoVO recursoVO : recursos) {
			if (recursoVO instanceof RecursoHumanoVO) {
				RecursoHumanoVO recursoVO2 = (RecursoHumanoVO) recursoVO;
				equipe.add(recursoVO2);
			}
		}
		return equipe;
	}

	public GerenteVO getGerenteVO() {
		return gerenteVO;
	}

	public void setGerenteVO(GerenteVO gerenteVO) {
		this.gerenteVO = gerenteVO;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ThreadGroup getThreadGroup() {
		return threadGroup;
	}

	public Integer getPercentualDeConclusao() {
		Integer tamanho = getTamanho();
		Integer realizado = getRealizado();
		Integer percentualDeConclusao = (int) ((float) (realizado * 100 / tamanho));
		if (realizado == 0) {
			return 0;
		} else if (realizado > tamanho) {
			return 100;
		}
		return percentualDeConclusao;
	}

	public Integer getTamanho() {
		Integer tamanho = new Integer(0);
		Set<DemandaVO> demandas = getDemandas();
		for (DemandaVO demandaVO : demandas) {
			tamanho = demandaVO.getTamanho() + tamanho;
		}
		return tamanho;
	}

	public Integer getRealizado() {
		Integer realizado = new Integer(0);
		Set<DemandaVO> demandas = getDemandas();
		for (DemandaVO demandaVO : demandas) {
			if (demandaVO.getEstado().equals(DemandaVO.Estado.ENTREGUE))
				realizado = demandaVO.getTamanho() + realizado;
		}
		return realizado;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
		long MESES_DE_DURACAO_DO_PROJETO = getProjetoVO().getPrazo() * UM_MINUTO;// Conceitual.
																					// Um
																					// minuto
																					// no
																					// jogo
																					// equivale
																					// a
																					// um
																					// mês
																					// de
																					// projeto
		this.dataFim = new Date(dataInicio.getTime() + MESES_DE_DURACAO_DO_PROJETO);
		System.out.println("Data inicio: " + this.dataInicio);
		System.out.println("Data fim: " + this.dataFim);
	}

	public Date getDataFim() {
		return dataFim;
	}

	public Long getCronograma() {
		if (dataInicio != null) {
			Long todoTempo = dataFim.getTime() - dataInicio.getTime();
			Long agora = (new Date()).getTime();
			Long tempoDecorrido = agora - dataInicio.getTime();
			Long aux = null;
			aux = ((tempoDecorrido * 100) / todoTempo);
			if (aux > 100)
				return 100L;
			return aux;
		}
		return 0L;
	}

	public Long getCustoComFolhaDePagamento() {
		Long valorDaFolha = 0L;
		for (RecursoVO recursoVO : getRecursos()) {
			if(recursoVO instanceof RecursoHumanoVO){
				valorDaFolha = (((RecursoHumanoVO)recursoVO).getProfissionalDeTIVO().getSalario()*2)+valorDaFolha;
			}
		}
		return valorDaFolha;
	}

	public Long getMesesDecorrios() {
		if (dataInicio != null) {
			Long agora = (new Date()).getTime();
			Long tempoDecorrido = agora - dataInicio.getTime();
			Long aux = (tempoDecorrido/1000)/60;
			return aux;
		}
		return 0L;
	}
	
	public Long getCusto(){
		return getCustoComFolhaDePagamento()*getMesesDecorrios();
	}
	
	public Long getPercentualGasto(){
		Long custo = getCusto();
		if(custo==0)
			return 0L;
		long percentualGasto = custo*100/getProjetoVO().getOrcamento();
		if(percentualGasto<=0)
			return 0L;
		if(percentualGasto >100)
			return 100L;
		return percentualGasto;
	}
}
