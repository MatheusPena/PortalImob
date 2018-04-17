package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dominio.Residencia;

@ManagedBean
@ViewScoped
public class ImovelBean {

	private List<Residencia> residencias = new ArrayList<>();
	

	public ImovelBean() {
		Residencia r1 = new Residencia();
		r1.setNome("Plaza Norte Residence");
		r1.setEndereco("MA 202 - Forquilha , Estrada de Ribamar, Maiobinha.");
		r1.setUrlImagem("imagens/scr051.jpg");

		Residencia r2 = new Residencia();
		r2.setNome("Alliance Residence");
		r2.setEndereco("Av. dos Holandeses, Litoral, Calhau");
		r2.setUrlImagem("imagens/scr052.jpg");

		Residencia r3 = new Residencia();
		r3.setNome("Jardins do Turu");
		r3.setEndereco("Avenida General Arthur Carvalho, s/n - Turu, Turu, Turu");
		r3.setUrlImagem("imagens/scr053.jpg");

		Residencia r4 = new Residencia();
		r4.setNome("Taroa Residence");
		r4.setEndereco("R. Santo Inácio de Loiola, LItoral, Olho D´água");
		r4.setUrlImagem("imagens/scr054.jpg");

		Residencia r5 = new Residencia();
		r5.setNome("Plaza das Flores Village");
		r5.setEndereco("Estrada da Guaíba , s/n, Iguaíba, Iguaíba");
		r5.setUrlImagem("imagens/scr055.jpg");
		
		residencias.add(r1);
		residencias.add(r2);
		residencias.add(r3);
		residencias.add(r4);
		residencias.add(r5);
	}
	
	/**
	 * Simula um banco de dados
	 */
	public void preencher() {

	
	}

	public void valores() {
		

	}

	public List<Residencia> getResidencias() {
		return residencias;
	}

	public void setResidencias(List<Residencia> residencias) {
		this.residencias = residencias;
	}

	
}
