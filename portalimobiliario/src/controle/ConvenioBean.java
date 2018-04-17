package controle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import dao.ConvenioDAO;
import dominio.Convenio;
import filtro.FiltroConvenio;
import util.ConexaoJDBC;
import util.JPAUtil;
import util.JSFUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ConvenioBean implements Serializable {
	
	private FiltroConvenio filtroConvenio;
	private List<Convenio> convenios;
	private Convenio convenio;
	
	public ConvenioBean() {
		filtroConvenio = new FiltroConvenio();	
		convenio = new Convenio();
		listar();
	}
	
	public void listar() {
		EntityManager em = new JPAUtil().getEntityManager();
		ConvenioDAO ConvenioDAO = new ConvenioDAO(em);
		setConvenios(ConvenioDAO.listarConstrutoras(filtroConvenio));
	}
	
	public String salvar() {
		com.mysql.jdbc.Connection con = ConexaoJDBC.conectar();
		ConvenioDAO ConvenioDAO = new ConvenioDAO();
		try {
			ConvenioDAO.salvar(con, convenio);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_ERROR, "Houveram problemas nas inserções dos dados!");
			return "cadastroconvenio?faces-redirect=true";
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSFUtil.mostraMensagem(FacesMessage.SEVERITY_INFO, "Parabéns cadastro finalizado com sucesso!");
		return "";
	}

	public FiltroConvenio getFiltroConvenio() {
		return filtroConvenio;
	}

	public void setFiltroConvenio(FiltroConvenio filtroConvenio) {
		this.filtroConvenio = filtroConvenio;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

}
