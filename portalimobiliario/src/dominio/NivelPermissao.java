package dominio;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="nivel_permissao")
@SuppressWarnings("serial")
@AssociationOverrides({
	@AssociationOverride(name = "nivelPermissaoPK.nivelUsuario", joinColumns = @JoinColumn(name = "nivelUsuario")),
	@AssociationOverride(name = "nivelPermissaoPK.permissaoUsuario", joinColumns = @JoinColumn(name = "permissaoUsuario")) })
public class NivelPermissao implements Serializable {
	
	@EmbeddedId
	private NivelPermissaoPK nivelPermissaoPK = new NivelPermissaoPK();
	
	public NivelPermissao() {
		
	}

	public NivelPermissaoPK getNivelPermissaoPK() {
		return nivelPermissaoPK;
	}

	public void setNivelPermissaoPK(NivelPermissaoPK nivelPermissaoPK) {
		this.nivelPermissaoPK = nivelPermissaoPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nivelPermissaoPK == null) ? 0 : nivelPermissaoPK.hashCode());
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
		if (!(obj instanceof NivelPermissao)) {
			return false;
		}
		NivelPermissao other = (NivelPermissao) obj;
		if (nivelPermissaoPK == null) {
			if (other.nivelPermissaoPK != null) {
				return false;
			}
		} else if (!nivelPermissaoPK.equals(other.nivelPermissaoPK)) {
			return false;
		}
		return true;
	}

}