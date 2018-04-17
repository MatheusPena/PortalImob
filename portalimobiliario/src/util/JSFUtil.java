package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class JSFUtil {
	
	public static void mostraMensagem(Severity tipo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo, mensagem, null));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static void mostraMensagemSemFlash(Severity tipo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo, mensagem, null));
	}
	
	public static String getRealPath(FacesContext aFacesContext) {
		//FacesContext aFacesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
		return context.getRealPath("/");
	}

}
