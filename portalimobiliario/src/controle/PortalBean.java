package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.persistence.EntityManager;

import dao.ConstrutoraDAO;
import dominio.Endereco;
import dominio.GaleriaImovel;
import dominio.Imovel;
import filtro.FiltroConstrutora;
import util.JPAUtil;

@SuppressWarnings("serial")
@ManagedBean
@javax.faces.bean.SessionScoped
// @javax.faces.bean.ViewScoped
public class PortalBean implements Serializable {

	private List<Imovel> imoveis = new ArrayList<>();
	private List<SelectItem> filtro1;
	private List<SelectItem> filtro2;
	private List<SelectItem> filtro3;

	private FiltroConstrutora filtroConstrutora;
	private EntityManager em;
	@SuppressWarnings("unused")
	private ConstrutoraDAO construtoraDAO;
	private Imovel imovel;

	public PortalBean() {
		filtroConstrutora = new FiltroConstrutora();
		filtroConstrutora.setAscendente(true);
		em = new JPAUtil().getEntityManager();
		construtoraDAO = new ConstrutoraDAO(em);
		imovel = new Imovel();
		preencher();
	}
	
	public String mostrarImovel(Imovel imovel) {
		System.out.println(imovel);
		return "compra?faces-redirect=true";
	}

	/**
	 * Simula um banco de dados
	 */
	public void preencher() {

		Endereco e = new Endereco();
		e.setRua("Rua Búzios, Lote no 12, Quadra 35 - Calhau, LItoral, Calhau");

		Imovel i1 = new Imovel();
		i1.setDescricao("Plaza Norte Residence");
		// r1.setEndereco("MA 202 - Forquilha , Estrada de Ribamar, Maiobinha.");
		i1.setUrlImagem("imagens/scr01.jpg");
		i1.setAnuncio("A partir de R$200.000");
		i1.setEndereco(e);

		GaleriaImovel g1 = new GaleriaImovel();
		g1.setDescricao("Imagem 01");
		g1.setUrlImagem("imagens/scr051.jpg");
		g1.setImovel(i1);
		g1.setId(1);

		GaleriaImovel g2 = new GaleriaImovel();
		g2.setDescricao("Imagem 02");
		g2.setUrlImagem("imagens/scr052.jpg");
		g2.setImovel(i1);
		g2.setId(2);

		GaleriaImovel g3 = new GaleriaImovel();
		g3.setDescricao("Imagem 03");
		g3.setUrlImagem("imagens/scr053.jpg");
		g3.setImovel(i1);
		g3.setId(3);

		GaleriaImovel g4 = new GaleriaImovel();
		g4.setDescricao("Imagem 04");
		g4.setUrlImagem("imagens/scr054.jpg");
		g4.setImovel(i1);
		g4.setId(4);

		GaleriaImovel g5 = new GaleriaImovel();
		g5.setDescricao("Imagem 05");
		g5.setUrlImagem("imagens/scr055.jpg");
		g5.setImovel(i1);
		g5.setId(5);
		// r1.setEndereco("MA 202 - Forquilha , Estrada de Ribamar, Maiobinha.");
		// r1.setUrlImagem("imagens/scr051.jpg");

		// Imovel i2 = new Imovel();
		// i2.setDescricao("Alliance Residence");
		// //r2.setEndereco("Av. dos Holandeses, Litoral, Calhau");
		// //r2.setUrlImagem("imagens/scr052.jpg");
		//
		// Imovel i3 = new Imovel();
		// i3.setDescricao("Jardins do Turu");
		// //r3.setEndereco("Avenida General Arthur Carvalho, s/n - Turu, Turu, Turu");
		// //r3.setUrlImagem("imagens/scr053.jpg");
		//
		// Imovel i4 = new Imovel();
		// i4.setDescricao("Taroa Residence");
		// //r4.setEndereco("R. Santo Inácio de Loiola, LItoral, Olho D´água");
		// //r4.setUrlImagem("imagens/scr054.jpg");
		//
		// Imovel i5 = new Imovel();
		// i5.setDescricao("Plaza das Flores Village");
		// r5.setEndereco("Estrada da Guaíba , s/n, Iguaíba, Iguaíba");
		// r5.setUrlImagem("imagens/scr055.jpg");

		// imoveis.add(i1);
		// imoveis.add(i2);
		// imoveis.add(i3);
		// imoveis.add(i4);
		// imoveis.add(i5);

		// c1.setImoveis(imoveis);

		Imovel r2 = new Imovel();
		r2.setDescricao("Alliance Residence");
		// r2.setEndereco("Av. dos Holandeses, Litoral, Calhau");
		r2.setUrlImagem("imagens/scr02.jpg");
		r2.setAnuncio("A partir de R$200.000");
		r2.setEndereco(e);

		Imovel r3 = new Imovel();
		r3.setDescricao("Jardins do Turu");
		// r3.setEndereco("Avenida General Arthur Carvalho, s/n - Turu, Turu, Turu");
		r3.setUrlImagem("imagens/scr03.jpg");
		r3.setAnuncio("A partir de R$200.000");
		r3.setEndereco(e);

		Imovel r4 = new Imovel();
		r4.setDescricao("Taroa Residence");
		// r4.setEndereco("R. Santo Inácio de Loiola, LItoral, Olho D´água");
		r4.setUrlImagem("imagens/scr04.jpg");
		r4.setAnuncio("A partir de R$200.000");
		r4.setEndereco(e);

		Imovel r5 = new Imovel();
		r5.setDescricao("Plaza das Flores Village");
		// r5.setEndereco("Estrada da Guaíba , s/n, Iguaíba, Iguaíba");
		r5.setUrlImagem("imagens/scr05.jpg");
		r5.setAnuncio("A partir de R$200.000");
		r5.setEndereco(e);

		Imovel r6 = new Imovel();
		r6.setDescricao("Village das Palmeiras Prime");
		// r6.setEndereco("Rua Projetada, Cohama, Cohama");
		r6.setUrlImagem("imagens/scr06.jpg");
		r6.setAnuncio("A partir de R$200.000");
		r6.setEndereco(e);

		Imovel r7 = new Imovel();
		r7.setDescricao("Aquamarine Residence");
		// r7.setEndereco("Rua Cinco, Super Quadra D , Lote 02- Ponta D´Areia, Litoral,
		// Ponta do Farol");
		r7.setUrlImagem("imagens/scr07.jpg");
		r7.setAnuncio("A partir de R$200.000");
		r7.setEndereco(e);

		Imovel r8 = new Imovel();
		r8.setDescricao("Naturam Reserva Rangedor");
		// r8.setEndereco("Rua Búzios, Lote no 12, Quadra 35 - Calhau, LItoral,
		// Calhau");
		r8.setUrlImagem("imagens/scr08.jpg");
		r8.setAnuncio("A partir de R$200.000");
		r8.setEndereco(e);

		Imovel r9 = new Imovel();
		r9.setDescricao("Plaza Norte Residence");
		// r9.setEndereco("MA 202 - Forquilha , Estrada de Ribamar, Maiobinha.");
		r9.setUrlImagem("imagens/scr09.jpg");
		r9.setAnuncio("A partir de R$200.000");
		r9.setEndereco(e);

		Imovel r10 = new Imovel();
		r10.setDescricao("Alliance Residence");
		// r10.setEndereco("Av. dos Holandeses, Litoral, Calhau");
		r10.setUrlImagem("imagens/scr10.jpg");
		r10.setAnuncio("A partir de R$200.000");
		r10.setEndereco(e);

		Imovel r11 = new Imovel();
		r11.setDescricao("Jardins do Turu");
		// r11.setEndereco("Avenida General Arthur Carvalho, s/n - Turu, Turu,Turu");
		r11.setUrlImagem("imagens/scr11.jpg");
		r11.setAnuncio("A partir de R$200.000");
		r11.setEndereco(e);

		Imovel r12 = new Imovel();
		r12.setDescricao("Taroa Residence");
		// r12.setEndereco("R. Santo Inácio de Loiola, LItoral, Olho D´água");
		r12.setUrlImagem("imagens/scr12.jpg");
		r12.setAnuncio("A partir de R$200.000");
		r12.setEndereco(e);

		Imovel r13 = new Imovel();
		r13.setDescricao("Plaza das Flores Village");
		// r13.setEndereco("Estrada da Guaíba , s/n, Iguaíba, Iguaíba");
		r13.setUrlImagem("imagens/scr09.jpg");
		r13.setAnuncio("A partir de R$200.000");
		r13.setEndereco(e);

		Imovel r14 = new Imovel();
		r14.setDescricao("Village das Palmeiras Prime");
		// r14.setEndereco("Rua Projetada, Cohama, Cohama");
		r14.setUrlImagem("imagens/scr02.jpg");
		r14.setAnuncio("A partir de R$200.000");
		r14.setEndereco(e);

		Imovel r15 = new Imovel();
		r15.setDescricao("Aquamarine Residence");
		// r15.setEndereco("Rua Cinco, Super Quadra D , Lote 02- Ponta D´Areia, Litoral,
		// Ponta do Farol");
		r15.setUrlImagem("imagens/scr15.jpg");
		r15.setAnuncio("A partir de R$200.000");
		r15.setEndereco(e);

		Imovel r16 = new Imovel();
		r16.setDescricao("Naturam Reserva Rangedor");
		// r16.setEndereco("Rua Búzios, Lote no 12, Quadra 35 - Calhau, LItoral,
		// Calhau");
		r16.setUrlImagem("imagens/scr16.jpg");
		r16.setAnuncio("A partir de R$200.000");
		r16.setEndereco(e);

		getImoveis().add(i1);
		getImoveis().add(r2);
		getImoveis().add(r3);
		getImoveis().add(r4);

		getImoveis().add(r5);
		getImoveis().add(r6);
		getImoveis().add(r7);
		getImoveis().add(r8);

		/**
		 * Sempre devem ser duas listas
		 */
		getImoveis().add(r9);
		getImoveis().add(r10);
		getImoveis().add(r11);
		getImoveis().add(r12);

		getImoveis().add(r13);
		getImoveis().add(r14);
		getImoveis().add(r15);
		getImoveis().add(r16);
		;

		// bairros
		SelectItemGroup g1f1 = new SelectItemGroup("Maranhão");
		g1f1.setSelectItems(new SelectItem[] { new SelectItem("Todos os bairros", "Todos os bairros") });

		SelectItemGroup g2f1 = new SelectItemGroup("São Luís");
		g2f1.setSelectItems(new SelectItem[] { new SelectItem("Cohama", "Cohama"),
				new SelectItem("Renascença", "Renascença"), new SelectItem("Cohab", "Cohab") });

		filtro1 = new ArrayList<SelectItem>();
		filtro1.add(g1f1);
		filtro1.add(g2f1);

		SelectItemGroup g1f2 = new SelectItemGroup("Imóvel");
		g1f2.setSelectItems(new SelectItem[] { new SelectItem("Todos os Imóveis", "Todos os Imóveis") });

		SelectItemGroup g2f2 = new SelectItemGroup("Para Alugar");
		g2f2.setSelectItems(new SelectItem[] { new SelectItem("Todos os Imóveis", "Todos os Imóveis"),
				new SelectItem("Plaza Residence", "Plaza Residence") });

		SelectItemGroup g2f3 = new SelectItemGroup("Para Comprar");
		g2f3.setSelectItems(new SelectItem[] { new SelectItem("Residence Evil", "Residence Evil") });

		filtro2 = new ArrayList<SelectItem>();
		filtro2.add(g1f2);
		filtro2.add(g2f2);
		filtro2.add(g2f3);

		filtro3 = new ArrayList<SelectItem>();
		SelectItemGroup g1f3 = new SelectItemGroup("Tipo de Imóvel");
		g1f3.setSelectItems(new SelectItem[] { new SelectItem("Todos os Tipos", "Todos os Tipos"),
				new SelectItem("Casa", "Casa"), new SelectItem("Apartamento", "Apartamento") });

		filtro3.add(g1f3);

	}

	
	public List<SelectItem> getFiltro1() {
		return filtro1;
	}

	public void setFiltro1(List<SelectItem> filtro1) {
		this.filtro1 = filtro1;
	}

	public List<SelectItem> getFiltro2() {
		return filtro2;
	}

	public void setFiltro2(List<SelectItem> filtro2) {
		this.filtro2 = filtro2;
	}

	public List<SelectItem> getFiltro3() {
		return filtro3;
	}

	public void setFiltro3(List<SelectItem> filtro3) {
		this.filtro3 = filtro3;
	}

	public FiltroConstrutora getFiltroConstrutora() {
		return filtroConstrutora;
	}

	public void setFiltroConstrutora(FiltroConstrutora filtroConstrutora) {
		this.filtroConstrutora = filtroConstrutora;
	}

	

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	// public StreamedContent getImagemConstrutora() {
	// javax.servlet.http.HttpServletRequest myRequest =
	// (javax.servlet.http.HttpServletRequest) context
	// .getExternalContext().getRequest();
	// String urlImagem = (String) myRequest.getParameter("construtora");
	// byte[] imgBytes = null;
	// System.out.println("STREAMED " + urlImagem);
	//
	// if (StringUtils.isNotEmpty(urlImagem)) {
	// imgBytes = FormatUtil.extrairBytes(urlImagem);
	// if (imgBytes != null) {
	// imagemConstrutora = (imagemBean.getStreamedImagem(imgBytes));
	// }
	// }
	//
	// return imagemConstrutora;
	// }

	// public void valores() {
	//
	// itensVisiveis = 4;
	//
	// System.out.println("ÍNDICE " + indiceCarrossel);
	// int i = 1;// i não pode ser zero por causa da multiplicação
	// // amostras.size() equivale ao número de carrosséis
	// // itensVisiveis - número de itens visíveis na tela por carrossel
	// int max = (itensVisiveis * 2);// 2 - número de carrosséis que preciso na
	// // tela
	//
	// if (indiceCarrossel >= itensVisiveis) {
	// //System.out.println("INDICE MAIOR QUE MAX");
	// indiceCarrossel = 0;
	// }
	//
	// //System.out.println("[INDICE] " + indiceCarrossel);
	//
	// // índice começa de zero, mas nesse caso preciso que comece de 1
	// indiceCarrossel += 1;
	//
	// // começa com 1 x 1, se i fosse zero o resultado seria zero
	// i *= indiceCarrossel;
	// if (indiceCarrossel > 1) {
	// i = (max + 1);
	// }
	//
	// max *= indiceCarrossel;
	//
	//
	//// filtroConstrutora.setPrimeiroRegistro(i-1);
	//// filtroConstrutora.setQuantidadeRegistros(4);
	//
	// System.out.println("primeiro registro "+i);
	// System.out.println("quantidade registros
	// "+filtroConstrutora.getQuantidadeRegistros());
	//
	// //construtoras.clear();
	//
	// preencher();
	//
	// for (Construtora c : construtoras) {
	// System.out.println("NOME " +c.getNomeFantasia());
	// //System.out.println("ENDEREÇO " + r.getEndereco());
	// System.out.println("URL " + c.getUrlImagem());
	// }
	//
	// }

}
