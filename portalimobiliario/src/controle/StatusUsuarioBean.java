package controle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import dao.StatusUsuarioDAO;
import dominio.StatusUsuario;
import filtro.FiltroStatusUsuario;
import util.ConexaoJDBC;
import util.JPAUtil;
import util.JSFUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StatusUsuarioBean implements Serializable {
	
	private FiltroStatusUsuario filtroStatusUsuario;
	private List<StatusUsuario> statusUsuarios;
	private StatusUsuario statusUsuario;
	
	public StatusUsuarioBean() {
		filtroStatusUsuario = new FiltroStatusUsuario();	
		statusUsuario = new StatusUsuario();
		listar();
	}
	
	public void listar() {
		EntityManager em = new JPAUtil().getEntityManager();
		StatusUsuarioDAO statusUsuarioDAO = new StatusUsuarioDAO(em);
		setStatusUsuarios(statusUsuarioDAO.listarUsuarios(filtroStatusUsuario));
	}
	
	public String salvar() {
		com.mysql.jdbc.Connection con = ConexaoJDBC.conectar();
		StatusUsuarioDAO statusUsuarioDAO = new StatusUsuarioDAO();
		try {
			statusUsuarioDAO.salvar(con, statusUsuario);
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
			return "cadastrostatususuario?faces-redirect=true";
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSFUtil.mostraMensagem(FacesMessage.SEVERITY_INFO, "Parabéns cadastro finalizado com sucesso!");
		return "";
	}

	public FiltroStatusUsuario getFiltroStatusUsuario() {
		return filtroStatusUsuario;
	}

	public void setFiltroStatusUsuario(FiltroStatusUsuario filtroStatusUsuario) {
		this.filtroStatusUsuario = filtroStatusUsuario;
	}

	public List<StatusUsuario> getStatusUsuarios() {
		return statusUsuarios;
	}

	public void setStatusUsuarios(List<StatusUsuario> statusUsuarios) {
		this.statusUsuarios = statusUsuarios;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

}
