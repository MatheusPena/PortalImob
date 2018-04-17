package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	@Id
	@Column (length=20)
	private String numeroPessoa;
	@Column (length=2)
	private String tipoPessoa;
	@Column (length=100)
	private String nome;
	@OneToOne
	@JoinColumn (name="acesso")
	private Acesso acesso;
	
	@OneToOne
	@JoinColumn (name="contato")
	private Contato contato;
	
	@OneToOne
	@JoinColumn (name="endereco")
	private Endereco endereco;

	private Date dataCadastro;
	
	@OneToOne
	@JoinColumn (name="convenio")
	private Convenio convenio;
	
	@OneToOne
	@JoinColumn (name="statusUsuario")
	private StatusUsuario statusUsuario;
	
	@OneToMany (mappedBy = "usuario")
	private List<Mensagem> mensagens;
	
	@OneToMany (mappedBy="usuario")
	private List<DocumentacaoUsuario> documentacao;
		
	public Usuario() {
		
	}

	public String getNumeroPessoa() {
		return numeroPessoa;
	}

	public void setNumeroPessoa(String numeroPessoa) {
		this.numeroPessoa = numeroPessoa;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public List<DocumentacaoUsuario> getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(List<DocumentacaoUsuario> documentacao) {
		this.documentacao = documentacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroPessoa == null) ? 0 : numeroPessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (numeroPessoa == null) {
			if (other.numeroPessoa != null) {
				return false;
			}
		} else if (!numeroPessoa.equals(other.numeroPessoa)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
