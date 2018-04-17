package dominio;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
public class NivelPermissaoPK implements Serializable {

	@ManyToOne
	@JoinColumn (name="nivelUsuario")
	private NivelUsuario nivelUsuario;
	@ManyToOne
	@JoinColumn (name="permissaoUsuario")
	private PermissaoUsuario permissaoUsuario;
	
	public NivelPermissaoPK() {
		
	}

	public NivelUsuario getNivelUsuario() {
		return nivelUsuario;
	}

	public void setNivelUsuario(NivelUsuario nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}

	public PermissaoUsuario getPermissaoUsuario() {
		return permissaoUsuario;
	}

	public void setPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
		this.permissaoUsuario = permissaoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nivelUsuario == null) ? 0 : nivelUsuario.hashCode());
		result = prime * result + ((permissaoUsuario == null) ? 0 : permissaoUsuario.hashCode());
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
		if (!(obj instanceof NivelPermissaoPK)) {
			return false;
		}
		NivelPermissaoPK other = (NivelPermissaoPK) obj;
		if (nivelUsuario == null) {
			if (other.nivelUsuario != null) {
				return false;
			}
		} else if (!nivelUsuario.equals(other.nivelUsuario)) {
			return false;
		}
		if (permissaoUsuario == null) {
			if (other.permissaoUsuario != null) {
				return false;
			}
		} else if (!permissaoUsuario.equals(other.permissaoUsuario)) {
			return false;
		}
		return true;
	}
	
	

	
}