package controle;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ImagemBean implements Serializable {
	
	private StreamedContent imagem;

	public ImagemBean() {
		
	}
		
	public StreamedContent getStreamedImagem(byte[] b) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		StreamedContent sc = null;

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        return new DefaultStreamedContent();
	    }
	    else {
	        sc = new DefaultStreamedContent(new ByteArrayInputStream(b));
	    }
	    setImagem(sc);
	    return sc;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	

}
