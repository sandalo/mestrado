package br.sandalo.ufmg.dcc.jogo.visualizacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.AnnotateBinder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.GerenteVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.GestaoDeProjetoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.DemandaVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.FuncaoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.recurso.recursohumano.RecursoHumanoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.ProfissionalDeTIVO;
import br.sandalo.ufmg.dcc.jogo.entidades.projeto.QualificacaoVO;

public class GestaoDeProjetoMVVM {
	private GestaoDeProjetoVO gestaoDeProjetoVO = new GestaoDeProjetoVO();
    private List<Component> componentsToReload = new ArrayList<Component>();
    private List<Component> componentsToReloadOnlyOne = new ArrayList<Component>();
    private List<Component> componentsToMessageNotify = new ArrayList<Component>();
	private DemandaVO demandaSelecionadaVO = null;
	
	public GestaoDeProjetoVO getGestaoDeProjetoVO() {
		return gestaoDeProjetoVO;
	}

	public void setGestaoDeProjetoVO(GestaoDeProjetoVO execuçãoDeProjetoVO) {
		this.gestaoDeProjetoVO = execuçãoDeProjetoVO;
	}


	private GerenteVO gerente() {
		return gestaoDeProjetoVO.getGerenteVO();
	}

	@Command
	@NotifyChange(value="gestaoDeProjetoVO")
	public void iniciar(@ContextParam(ContextType.BINDER) AnnotateBinder binder){
		getGestaoDeProjetoVO().setEstado(GestaoDeProjetoVO.Estado.INICIADO);
		getGestaoDeProjetoVO().setDataInicio(new Date());
		Desktop desktop = Executions.getCurrent().getDesktop();
		GestaoDeProjetoMVVMThread gestaoDeProjetoMVVMThread = new GestaoDeProjetoMVVMThread(this,desktop,binder);
		gestaoDeProjetoMVVMThread.setDaemon(true);
		gestaoDeProjetoMVVMThread.setPriority(Thread.MIN_PRIORITY);
		gestaoDeProjetoMVVMThread.start();
	}

	@NotifyChange(value="equipe")
	@Command
	public void adicionaProfissionalNaEquipe(@BindingParam("profissional") Listitem listitem) {
		if (listitem.getListbox().getId().equals("profissionais")) {
			ProfissionalDeTIVO profissionalDeTIVO = listitem.getValue();
			gerente().contratar(profissionalDeTIVO);
		}
	}
	@NotifyChange(value="equipe")
	@Command
	public void adicionaFuncaoParaProficional(@BindingParam("qualificacao") Listitem listitem, @BindingParam("recursoHumano") RecursoHumanoVO recursoHumanoVO) {
		if (listitem.getValue()!=null && listitem.getValue() instanceof QualificacaoVO) {
			QualificacaoVO qualificacaoVO = listitem.getValue();
			gerente().atribuirFuncao(recursoHumanoVO, qualificacaoVO);
		}
	}
	
	@NotifyChange(value="gestaoDeProjetoVO")
	@Command
	public void atribuirDemandaParaFuncao(@BindingParam("itemDragged") Listitem listitem, @BindingParam("recursoHumanoResponsavel") RecursoHumanoVO recursoHumanoResponsavel, @BindingParam("funcao") FuncaoVO funcaoVO) {
		try {
			Object value = listitem.getValue();
			DemandaVO demandaVO = null;
			if(value instanceof DemandaVO){
				demandaVO = (DemandaVO) value;
			}else if(value instanceof ItemDeTrabalhoVO){
				ItemDeTrabalhoVO value2 = (ItemDeTrabalhoVO) value;
				demandaVO = value2.getDemandaVO();
			}
			gerente().atribuiDemanda(demandaVO, recursoHumanoResponsavel, funcaoVO);
		} catch (RuntimeException e) {
			Messagebox.show(e.getMessage());
		}
	}

	@NotifyChange(value="equipe")
	@Command
	public void iniciarItemDeTrabalho(@BindingParam("itemDeTrabalhoAtual") ItemDeTrabalhoVO itemDeTrabalhoVO) {
		gerente().iniciarItemDeTrabalho(itemDeTrabalhoVO);
	} 

	@NotifyChange(value="equipe")
	@Command
	public void pausarItemDeTrabalho(@BindingParam("itemDeTrabalhoAtual") ItemDeTrabalhoVO itemDeTrabalhoVO) {
		gerente().iniciarItemDeTrabalho(itemDeTrabalhoVO);
	} 

	@Command
	public void acelerarItemDeTrabalho(@BindingParam("itemDeTrabalhoAtual") ItemDeTrabalhoVO itemDeTrabalhoVO) {
		if(itemDeTrabalhoVO!=null)
			gerente().acelerarItemDeTrabalho(itemDeTrabalhoVO);
	} 

	@Command(value = "registraEmComponentsToReload")
	public void registraEmComponentsToReload(@BindingParam("component") Component component) {
		componentsToReload.add(component);
	}

	@Command(value = "registraEmComponentsToReloadNotify")
	public void registraEmComponentsToReload(@BindingParam("component") Component component, boolean messageNotify) {
		componentsToReload.add(component);
		if(messageNotify){
			componentsToMessageNotify.add(component);
		}
	}
	@Command(value = "registraEmComponentsToMessageNotify")
	public void registraEmComponentsToMessageNotify(@BindingParam("component") Component component) {
		componentsToMessageNotify.add(component);
	}
	public Set<RecursoHumanoVO> getEquipe() {
		return gestaoDeProjetoVO.getEquipe();
	}

	public List<Component> getComponentsToReload() {
		return componentsToReload;
	}

	public List<Component> getComponentsToReloadOnlyOneTime() {
		return componentsToReloadOnlyOne;
	}

	public void setComponentsToReloadOnlyOne(List<Component> componentsToReloadOnlyOne) {
		this.componentsToReloadOnlyOne = componentsToReloadOnlyOne;
	}

	public DemandaVO getDemandaSelecionadaVO() {
		return demandaSelecionadaVO;
	}

	public void setDemandaSelecionadaVO(DemandaVO demandaSelecionadaVO) {
		this.demandaSelecionadaVO = demandaSelecionadaVO;
	}

	public List<Component> getComponentsToMessageNotify() {
		return componentsToMessageNotify;
	}
	
}
