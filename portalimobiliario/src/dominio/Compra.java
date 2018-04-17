package dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="compra")
public class Compra {

	// private Integer idTrasacao;X
	// private String documentoCliente;X
	// private String documentoImovel;X
	// private String cpfCliente;X
	// private String status;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name = "cliente")
	private Usuario cliente;
	@OneToOne
	@JoinColumn(name = "imovel")
	private Imovel imovel;
	@OneToOne
	@JoinColumn(name = "statusCompra")
	private StatusCompra statusCompra; 
	@OneToMany (mappedBy = "compra")
	private List<HistoricoStatusCompra> historicoStatus;

	public Compra() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public void setStatusCompra(StatusCompra statusCompra) {
		this.statusCompra = statusCompra;
	}

	public List<HistoricoStatusCompra> getHistoricoStatus() {
		return historicoStatus;
	}

	public void setHistoricoStatus(List<HistoricoStatusCompra> historicoStatus) {
		this.historicoStatus = historicoStatus;
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
		if (!(obj instanceof Compra)) {
			return false;
		}
		Compra other = (Compra) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
}
