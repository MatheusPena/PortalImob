package dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="nivel_usuario")
public class NivelUsuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=150)
	private String descricao;
		
	@OneToMany(mappedBy = "nivelPermissaoPK.nivelUsuario", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<NivelPermissao> niveisPermissoes;
	
	public NivelUsuario() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<NivelPermissao> getNiveisPermissoes() {
		return niveisPermissoes;
	}

	public void setNiveisPermissoes(List<NivelPermissao> niveisPermissoes) {
		this.niveisPermissoes = niveisPermissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof NivelUsuario)) {
			return false;
		}
		NivelUsuario other = (NivelUsuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
