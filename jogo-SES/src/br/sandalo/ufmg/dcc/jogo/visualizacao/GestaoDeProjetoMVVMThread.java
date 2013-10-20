package br.sandalo.ufmg.dcc.jogo.visualizacao;

import org.zkoss.bind.AnnotateBinder;
import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.DesktopUnavailableException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;

public class GestaoDeProjetoMVVMThread extends Thread {
    private final Desktop _desktop;
    private GestaoDeProjetoMVVM _gestaoDeProjetoMVVM;
    private AnnotateBinder _binder;
    public GestaoDeProjetoMVVMThread(GestaoDeProjetoMVVM gestaoDeProjetoMVVM, Desktop desktop, AnnotateBinder binder) {
    	super(gestaoDeProjetoMVVM.getGestaoDeProjetoVO().getThreadGroup(),"Thread - Atualização");
    	Executions.getCurrent().getDesktop().enableServerPush(true);
    	_desktop = desktop;
    	_gestaoDeProjetoMVVM = gestaoDeProjetoMVVM;
        _binder = binder;
    }
 
    public void run() {
        try {
            while (true) {
                if (!_desktop.isServerPushEnabled()) {
                    _desktop.enableServerPush(false);
                    return;
                }
                Executions.activate(_desktop);
                try {
                	for (Component component : _gestaoDeProjetoMVVM.getComponentsToMessageNotify()) {
                		if(component.getAttribute("notificacao",Component.COMPONENT_SCOPE)!=null){
                			 Clients.showNotification((String) component.getAttribute("notificacao",Component.COMPONENT_SCOPE), "middle_center", component, "middle_center", 2000);
                			 component.setAttribute("notificacao",null,Component.COMPONENT_SCOPE);
                		}
					}
                	for (Component component : _gestaoDeProjetoMVVM.getComponentsToReload()) {
						_binder.loadComponent(component, true);
					}
                	for (Component component : _gestaoDeProjetoMVVM.getComponentsToReloadOnlyOneTime()) {
						_binder.loadComponent(component, true);
					}
                	_gestaoDeProjetoMVVM.getComponentsToReloadOnlyOneTime().clear();
                } catch (Exception e) {
                	e.printStackTrace();
				}
                finally {
                    Executions.deactivate(_desktop);
                }
                Threads.sleep(1000);
				System.out.println("Id: "+Thread.currentThread().getId()+" Name: "+Thread.currentThread().getName());
            }
        } catch (DesktopUnavailableException ex) {
            System.out.println("The server push thread interrupted");
        } catch (InterruptedException ex) {
            System.out.println("The server push thread interrupted");
        }
    }
}
