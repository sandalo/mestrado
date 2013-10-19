package br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas;

import java.util.Date;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.QualificacaoVO;
public class ItemDeTrabalhoVO implements Comparable<ItemDeTrabalhoVO> {

	private String descricao;
	private DemandaVO demandaVO;
	private FuncaoVO funcaoVO;
	private Integer realizado=0;
	private Integer urgencia = 1;
	private Thread theadDeExecução;
	private Date criacao;
	private Date inicio;
	private Date fim;
	private Integer qualidade = 7;

	public ItemDeTrabalhoVO(FuncaoVO funcaoVO,DemandaVO demandaVO) {
		ThreadGroup threadGroup = funcaoVO.getRecursoHumanoResponsavelVO().getGestaoDeProjetoVO().getThreadGroup();
		this.criacao = new Date();
		this.demandaVO=demandaVO;
		this.funcaoVO = funcaoVO;
		this.theadDeExecução = new Thread(threadGroup,new ItemDeTrabalhoThread(this),this.demandaVO.getDescricao());
		this.theadDeExecução.setPriority(Thread.MIN_PRIORITY);
	}

	public enum EstadoItem {
		ABERTO, EM_EXECUÇÃO, FECHADO;
	}

	EstadoItem estadoItem = EstadoItem.ABERTO;

	@Override
	public int compareTo(ItemDeTrabalhoVO o) {
		return getCriacao().compareTo(o.getCriacao());
	}

	public DemandaVO getDemandaVO() {
		return demandaVO;
	}

	public void setDemandaVO(DemandaVO demandaVO) {
		this.demandaVO = demandaVO;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FuncaoVO getFuncaoVO() {
		return funcaoVO;
	}

	public void setFuncaoVO(FuncaoVO funcaoVO) {
		this.funcaoVO = funcaoVO;
	}

	public Integer getPercentualDeConclusao() {
		if(realizado==0){
			return 0;
		}else if(realizado==demandaVO.getTamanho()){
			return 100;
		}
		Integer percentualDeConclusao = (int) ((float)(realizado * 100/getDemandaVO().getTamanho()));
		return percentualDeConclusao;
	}

	public Integer velocidade() {
		double vel = 0;

		Integer exeperienciaDoProfissional = getFuncaoVO().getRecursoHumanoResponsavelVO().getProfissionalDeTIVO().getExperiencia();
		Integer agilidadeDoProfissional = getFuncaoVO().getRecursoHumanoResponsavelVO().getProfissionalDeTIVO().getAgilidade();
		Integer dificuldadeInnerenteAFuncao = getFuncaoVO().dificuldadeInerente();
		Integer complexidade = getDemandaVO().getComplexidade();
		Integer prioridade = getUrgencia();
		
		vel = calcularVelcidade(exeperienciaDoProfissional, agilidadeDoProfissional, dificuldadeInnerenteAFuncao, complexidade, prioridade);

		return (int) vel;
	}

	protected double calcularVelcidade(Integer exeperienciaDoProfissional, Integer agilidadeDoProfissional, Integer dificuldadeInerenteAFuncao, Integer complexidade, Integer prioridade) {
		double vel;
		double fatorDeProdutividade = (Math.pow(exeperienciaDoProfissional, 3) + Math.pow(agilidadeDoProfissional, 2));
		double fatorDeAtraso = dificuldadeInerenteAFuncao + (complexidade*5);
		vel = ((fatorDeAtraso / fatorDeProdutividade) * 10000)/prioridade;
		return vel;
	}

	public void fecha() {
		throw new RuntimeException("Esse item não pode ser encerrado: Tipo de item não definido!");
	}

	public EstadoItem getEstadoItem() {
		return estadoItem;
	}

	public void setEstadoItem(EstadoItem estadoItem) {
		this.estadoItem = estadoItem;
	}

	public void start() {
		this.setEstadoItem(ItemDeTrabalhoVO.EstadoItem.EM_EXECUÇÃO);
		theadDeExecução.start();
	}

	public void stop() {
		this.setEstadoItem(ItemDeTrabalhoVO.EstadoItem.ABERTO);
		theadDeExecução.stop();// TODO: Rever
	}

	public void aumentaUrgencia() {
		if (urgencia < 10)
			urgencia = urgencia + 1;
	}
	

	public void setRealizado(Integer realizado) {
		this.realizado = realizado;
	}

	public Integer getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(Integer urgencia) {
		this.urgencia = urgencia;
	}


	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	public Long getDuracao(){
		return (getFim().getTime() - getInicio().getTime())/1000;
	}
	
	public void setDuracao(Long l){
		
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}
	public static void main(String[] args) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension("js");
		Integer exeperienciaDoProfissional = 10;
		engine.put("exeperienciaDoProfissional", exeperienciaDoProfissional);
		Integer agilidadeDoProfissional = 10;
		engine.put("agilidadeDoProfissional", agilidadeDoProfissional);
		Integer dificuldadeInnerenteAFuncao = 15;
		engine.put("dificuldadeInnerenteAFuncao", dificuldadeInnerenteAFuncao);
		Integer complexidade = 15;
		engine.put("complexidade", complexidade);
		Integer prioridade = 1;
		engine.put("prioridade", prioridade);
		String script = "vel = 0;" + 
		"fatorDeProdutividade = (Math.pow(exeperienciaDoProfissional, 2) + Math.pow(agilidadeDoProfissional, 2));" + 
		"fatorDeAtraso = dificuldadeInnerenteAFuncao + (complexidade*2);" + 
		"vel = ((fatorDeAtraso / fatorDeProdutividade) * 10000)/prioridade;";
		engine.eval(script);
		System.out.println("Resul: " + engine.get("vel"));
	}
	
	public static boolean recursoPossuiQualificacaoParaExecutarEsteItem(ItemDeTrabalhoVO itemDeTrabalhoVO) {
		boolean recursoPossuiQualificacaoParaExecutarEsteItem = false;
		Set<QualificacaoVO> qualificacoes = itemDeTrabalhoVO.getFuncaoVO().getRecursoHumanoResponsavelVO().getProfissionalDeTIVO().getQualificacoes();
		for (QualificacaoVO qualificacaoVO : qualificacoes) {
			if(qualificacaoVO.equals(itemDeTrabalhoVO.getFuncaoVO().getQualificacao())){
				recursoPossuiQualificacaoParaExecutarEsteItem = true;
				break;
			}
		}
		return recursoPossuiQualificacaoParaExecutarEsteItem;
	}

	public Integer getQualidade() {
		return qualidade;
	}

	public void setQualidade(Integer qualidade) {
		if(qualidade>15)
			qualidade = 15;
		this.qualidade = qualidade;
	}
}
