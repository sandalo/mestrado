package br.sandalo.ufmg.dcc.jogo.visualizacao.aspect;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.AnnotateBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.BugVO;
import br.sandalo.ufmg.dcc.jogo.visualizacao.GestaoDeProjetoMVVM;
import br.sandalo.ufmg.dcc.jogo.visualizacao.componente.GridJogo;
import br.sandalo.ufmg.dcc.jogo.visualizacao.componente.ListboxJogo;
import br.sandalo.ufmg.dcc.jogo.visualizacao.componente.ProgressmeterJogo;

public aspect GestaoDeProjetoAspect {
	private GestaoDeProjetoMVVM gestaoDeProjetoMVVM;
    private List<Component> observadoresParaItensDeTrabalho = new ArrayList<Component>();
	pointcut iniciaJogo(GestaoDeProjetoMVVM gestaoDeProjetoMVVM): execution(void br.sandalo.ufmg.dcc.jogo.visualizacao.GestaoDeProjetoMVVM.iniciar(AnnotateBinder)) && target(gestaoDeProjetoMVVM);
	pointcut criaListBoxJogo(ListboxJogo listboxjogo): execution(br.sandalo.ufmg.dcc.jogo.visualizacao.componente.ListboxJogo.new())&&this(listboxjogo);
	pointcut criaGridJogo(GridJogo gridjogo): execution(br.sandalo.ufmg.dcc.jogo.visualizacao.componente.GridJogo.new())&&this(gridjogo);
	pointcut criaProgressmeterJogo(ProgressmeterJogo progressmeterJogo): execution(br.sandalo.ufmg.dcc.jogo.visualizacao.componente.ProgressmeterJogo.new())&&this(progressmeterJogo);
	pointcut fechaItem(): execution(* br.sandalo.ufmg.dcc.jogo.entidades.execucao.tarefas.ItemDeTrabalhoVO+.fecha());
	pointcut criaBug(BugVO bugVO): execution(br.sandalo.ufmg.dcc.jogo.entidades.execucao.ordemdeserviço.BugVO.new(Integer,String , Integer))&&this(bugVO);

	after(GestaoDeProjetoMVVM gestaoDeProjetoMVVM): iniciaJogo( gestaoDeProjetoMVVM){
		this.gestaoDeProjetoMVVM = gestaoDeProjetoMVVM;
		System.out.println("Iniciou jogo");
	}
	
	after(ListboxJogo listboxjogo): criaListBoxJogo(listboxjogo){
		observadoresParaItensDeTrabalho.add(listboxjogo);
		System.out.println("Criou ListboxJogo");
	}

	after(GridJogo gridjogo): criaGridJogo(gridjogo){
		observadoresParaItensDeTrabalho.add(gridjogo);
		System.out.println("Criou ListboxJogo");
	}

	after(ProgressmeterJogo progressmeterJogo): criaProgressmeterJogo(progressmeterJogo){
		observadoresParaItensDeTrabalho.add(progressmeterJogo);
		System.out.println("Criou ListboxJogo");
	}
	
	after(): fechaItem(){
		for (Component observer : observadoresParaItensDeTrabalho) {
			gestaoDeProjetoMVVM.getComponentsToReloadOnlyOneTime().add(observer);
			System.out.println("Notificando observadores após fechamento de item");
		}
	}
	after(BugVO bugVO): criaBug(bugVO){
		List<Component> components = gestaoDeProjetoMVVM.getComponentsToMessageNotify();
		for (Component component : components) {
			if(component.getId().equals("equipe")){
				component.setAttribute("notificacao", bugVO.getDescricao(),Component.COMPONENT_SCOPE);
				System.out.println();
			}
		}
	}
}
