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
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

@SuppressWarnings("serial")
@Entity
@Table(name = "construtora")
public class Construtora implements Serializable {

	@Id
	@Column(length = 20)
	private String numeroPessoa;

	@Column(length = 100)
	private String nomeFantasia;
	
	private Date dataCadastro;

	@OneToOne
	@JoinColumn(name = "acesso")
	private Acesso acesso;

	@OneToOne
	@JoinColumn(name = "contato")
	private Contato contato;
	
	@OneToOne
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	@Column(length = 50)
	private String anuncio;
	
	@OneToOne
	@JoinColumn(name = "statusConstrutora")
	private StatusConstrutora statusConstrutora;

	@Column(length = 500)
	private String urlImagem;

	@OneToMany(mappedBy = "construtora")
	private List<Imovel> imoveis;
		
	@Transient
	private StreamedContent streamImagem;
	
	@Transient
	private byte[] bytesImagem;
	
	@Transient
	private String descricaoImagem;

	public Construtora() {

	}

	public String getNumeroPessoa() {
		return numeroPessoa;
	}

	public void setNumeroPessoa(String numeroPessoa) {
		this.numeroPessoa = numeroPessoa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(String anuncio) {
		this.anuncio = anuncio;
	}

	public StatusConstrutora getStatusConstrutora() {
		return statusConstrutora;
	}

	public void setStatusConstrutora(StatusConstrutora statusConstrutora) {
		this.statusConstrutora = statusConstrutora;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public StreamedContent getStreamImagem() {
		return streamImagem;
	}

	public void setStreamImagem(StreamedContent streamImagem) {
		this.streamImagem = streamImagem;
	}

	public byte[] getBytesImagem() {
		return bytesImagem;
	}

	public void setBytesImagem(byte[] bytesImagem) {
		this.bytesImagem = bytesImagem;
	}

	public String getDescricaoImagem() {
		return descricaoImagem;
	}

	public void setDescricaoImagem(String descricaoImagem) {
		this.descricaoImagem = descricaoImagem;
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
		if (!(obj instanceof Construtora)) {
			return false;
		}
		Construtora other = (Construtora) obj;
		if (numeroPessoa == null) {
			if (other.numeroPessoa != null) {
				return false;
			}
		} else if (!numeroPessoa.equals(other.numeroPessoa)) {
			return false;
		}
		return true;
	}

	

}
