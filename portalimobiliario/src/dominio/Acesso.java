package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Classe que guarda o login, senha e perm
 * @author nogf000533
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="acesso")
public class Acesso implements Serializable {
	
	@Id
	@Column (length=20)
	private String acesso;
	@Column (length=50)
	private String senha;
	@OneToOne (mappedBy="acesso")
	private Usuario usuario;
	@OneToOne
	@JoinColumn (name="nivelUsuario")
	private NivelUsuario nivelUsuario;

	public Acesso() {
		
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public NivelUsuario getNivelUsuario() {
		return nivelUsuario;
	}

	public void setNivelUsuario(NivelUsuario nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAcesso() == null) ? 0 : getAcesso().hashCode());
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
		if (!(obj instanceof Acesso)) {
			return false;
		}
		Acesso other = (Acesso) obj;
		if (getAcesso() == null) {
			if (other.getAcesso() != null) {
				return false;
			}
		} else if (!getAcesso().equals(other.getAcesso())) {
			return false;
		}
		return true;
	}

	public String toString() {
		return getAcesso();
	}


}
