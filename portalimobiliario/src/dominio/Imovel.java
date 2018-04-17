package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table (name="imovel")
public class Imovel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=150)
	private String descricao;
	
	@ManyToOne
	@JoinColumn (name="construtora")
	private Construtora construtora;

	@OneToOne
	@JoinColumn (name="endereco")
	private Endereco endereco;
	
	private BigDecimal preco;
	
	@OneToMany (mappedBy="imovel")
	private List<CaracteristicasImovel> caracteristicas;
	
	@OneToOne
	@JoinColumn (name="tipoImovel")
	private TipoImovel tipoImovel;
	
	@OneToOne
	@JoinColumn (name="statusImovel")
	private StatusImovel statusImovel;
	
	@OneToMany (mappedBy="imovel")
	private List<DocumentacaoImovel> documentacao;
	
	@OneToMany (mappedBy="imovel")
	private List<GaleriaImovel> galeria;
	
	public Imovel() {
		
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

	public Construtora getConstrutora() {
		return construtora;
	}

	public void setConstrutora(Construtora construtora) {
		this.construtora = construtora;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	

	public List<CaracteristicasImovel> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicasImovel> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public StatusImovel getStatusImovel() {
		return statusImovel;
	}

	public void setStatusImovel(StatusImovel statusImovel) {
		this.statusImovel = statusImovel;
	}

	public List<DocumentacaoImovel> getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(List<DocumentacaoImovel> documentacao) {
		this.documentacao = documentacao;
	}

	public List<GaleriaImovel> getGaleria() {
		return galeria;
	}

	public void setGaleria(List<GaleriaImovel> galeria) {
		this.galeria = galeria;
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
		if (!(obj instanceof Imovel)) {
			return false;
		}
		Imovel other = (Imovel) obj;
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
