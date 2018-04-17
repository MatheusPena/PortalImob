package controle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import dao.AcessoDAO;
import dao.ContatoDAO;
import dao.EnderecoDAO;
import dao.UsuarioDAO;
import dominio.Acesso;
import dominio.Contato;
import dominio.Convenio;
import dominio.Endereco;
import dominio.Usuario;
import util.ConexaoJDBC;
import util.FormatUtil;
import util.JSFUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private Usuario usuario;
	private Acesso acesso;
	private Contato contato;
	private String senha;
	private String confirmarSenha;
	private Convenio convenio;
	private Endereco endereco;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
		acesso = new Acesso();
		contato = new Contato();
		convenio = new Convenio();
		endereco = new Endereco();
	}

	/**
	 * Primeiro step
	 */
	public void dadosIniciais() {

		if (!StringUtils.isEmpty(senha) && !StringUtils.isEmpty(confirmarSenha)) {
			if (!senha.equalsIgnoreCase(confirmarSenha)) {
				JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_ERROR, "Senhas não conferem!");
				return;
			}
		}
		acesso.setSenha(senha);
		JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_INFO,
				"Ótimo, você completou a primeira parte de seu cadastro!");
	}

	/**
	 * Segundo step
	 */
	public void dadosPessoais() {
		System.out.println("CONVENIO " + convenio.getDescricao());
		JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_INFO,
				"Excelente, você completou a segunda etapa, resta apenas uma!");
	}

	/**
	 * Terceiro e último step - finalizando cadastro
	 */
	public void finalizarCadastro() {
		com.mysql.jdbc.Connection con = ConexaoJDBC.conectar();
		ContatoDAO contatoDAO = new ContatoDAO();
		AcessoDAO acessoDAO = new AcessoDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			String numPessoa = usuario.getNumeroPessoa();
			String id = FormatUtil.apenasLetrasENumeros(numPessoa);
			acesso.setAcesso(id);
			contato.setId(id);
			endereco.setId(id);
			acessoDAO.salvar(con, acesso);
			contatoDAO.salvar(con, contato);
			enderecoDAO.salvar(con, endereco);
			usuario.setAcesso(this.acesso);
			usuario.setContato(this.contato);
			usuario.setEndereco(this.endereco);
			usuarioDAO.salvar(con, usuario);
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
			return;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_INFO, "Parabéns cadastro finalizado com sucesso!");
	}

	public List<String> estados() {
		List<String> ufs = new ArrayList<>();
		ufs.add("AC");
		ufs.add("AL");
		ufs.add("AM");
		ufs.add("AP");
		ufs.add("BA");
		ufs.add("CE");
		ufs.add("DF");
		ufs.add("ES");
		ufs.add("GO");
		ufs.add("MA");
		ufs.add("MG");
		ufs.add("MS");
		ufs.add("MT");
		ufs.add("PA");
		ufs.add("PB");
		ufs.add("PE");
		ufs.add("PI");
		ufs.add("PR");
		ufs.add("RJ");
		ufs.add("RN");
		ufs.add("RO");
		ufs.add("RR");
		ufs.add("RS");
		ufs.add("SC");
		ufs.add("SE");
		ufs.add("SP");
		ufs.add("TO");
		return ufs;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
