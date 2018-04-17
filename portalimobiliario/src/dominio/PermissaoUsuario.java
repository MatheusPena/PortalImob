package dominio;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * Lista de permissões de usuários
 * @author nogf000533
 *
 */
@Entity
@Table(name="permissao_usuario")
public class PermissaoUsuario implements Serializable, Comparator<PermissaoUsuario> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length=150)
	private String descricao;
	
	@Column(length=150, nullable=false)
	@Index(name="chave")
	private String chave;
	
	@OneToMany(mappedBy = "nivelPermissaoPK.permissaoUsuario", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<NivelPermissao> niveisPermissoes;
	
	public PermissaoUsuario() {
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

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
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
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		PermissaoUsuario other = (PermissaoUsuario) obj;
		if (chave == null) {
			if (other.chave != null) {
				return false;
			}
		} else if (!chave.equals(other.chave)) {
			return false;
		}
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return descricao;
	}

	@Override
	public int compare(PermissaoUsuario o1, PermissaoUsuario o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}
}