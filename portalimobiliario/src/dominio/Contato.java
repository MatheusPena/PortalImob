package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="contato")
public class Contato implements Serializable {
	
	@Id
	@Column (length=20)
	private String id;
	
	@Column (length=100)
	private String email;
	
	@Column (length=100)
	private String outroEmail;
	
	@Column (length=15)
	private String telCelular;
	
	@Column (length=15)
	private String telResidencial;
	
	@Column (length=15)
	private String telOutro;
	
	public Contato() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOutroEmail() {
		return outroEmail;
	}

	public void setOutroEmail(String outroEmail) {
		this.outroEmail = outroEmail;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getTelOutro() {
		return telOutro;
	}

	public void setTelOutro(String telOutro) {
		this.telOutro = telOutro;
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
		if (!(obj instanceof Contato)) {
			return false;
		}
		Contato other = (Contato) obj;
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
