package controle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import dao.StatusConstrutoraDAO;
import dominio.StatusConstrutora;
import filtro.FiltroStatusConstrutora;
import util.ConexaoJDBC;
import util.JPAUtil;
import util.JSFUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StatusConstrutoraBean implements Serializable {

	private FiltroStatusConstrutora filtroStatusConstrutora;
	private List<StatusConstrutora> statusConstrutoras;
	private StatusConstrutora statusConstrutora;

	public StatusConstrutoraBean() {
		filtroStatusConstrutora = new FiltroStatusConstrutora();
		statusConstrutora = new StatusConstrutora();
		listar();
	}

	public void listar() {
		EntityManager em = new JPAUtil().getEntityManager();
		StatusConstrutoraDAO statusConstrutoraDAO = new StatusConstrutoraDAO(em);
		setStatusConstrutoras(statusConstrutoraDAO.listarConstrutoras(filtroStatusConstrutora));
	}

	public String salvar() {
		com.mysql.jdbc.Connection con = ConexaoJDBC.conectar();
		StatusConstrutoraDAO statusConstrutoraDAO = new StatusConstrutoraDAO();
		try {
			statusConstrutoraDAO.salvar(con, statusConstrutora);
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
			return "cadastrostatusconstrutora?faces-redirect=true";
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSFUtil.mostraMensagem(FacesMessage.SEVERITY_INFO, "Parabéns cadastro finalizado com sucesso!");
		return "";
	}

	public FiltroStatusConstrutora getFiltroStatusConstrutora() {
		return filtroStatusConstrutora;
	}

	public void setFiltroStatusConstrutora(FiltroStatusConstrutora filtroStatusConstrutora) {
		this.filtroStatusConstrutora = filtroStatusConstrutora;
	}

	public List<StatusConstrutora> getStatusConstrutoras() {
		return statusConstrutoras;
	}

	public void setStatusConstrutoras(List<StatusConstrutora> statusConstrutoras) {
		this.statusConstrutoras = statusConstrutoras;
	}

	public StatusConstrutora getStatusConstrutora() {
		return statusConstrutora;
	}

	public void setStatusConstrutora(StatusConstrutora statusConstrutora) {
		this.statusConstrutora = statusConstrutora;
	}

}
