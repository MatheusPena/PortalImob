package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.persistence.EntityManager;

import dao.ConstrutoraDAO;
import dominio.Construtora;
import dominio.Endereco;
import filtro.FiltroConstrutora;
import util.JPAUtil;

@SuppressWarnings("serial")
@ManagedBean
//@javax.faces.bean.SessionScoped
@javax.faces.bean.ViewScoped
public class PortalConstrutoraBean implements Serializable {

	private List<Construtora> construtoras = new ArrayList<>();
	// private List<List<Construtora>> amostras = new
	// ArrayList<List<Construtora>>();
	// private Integer itensVisiveis;
	// private Integer indiceCarrossel;
	private List<SelectItem> filtro1;
	private List<SelectItem> filtro2;
	private List<SelectItem> filtro3;

	private FiltroConstrutora filtroConstrutora;
	private EntityManager em;
	private ConstrutoraDAO construtoraDAO;
	private Construtora construtora;

	//private FacesContext context;
	//private ImagemBean imagemBean;
	
	//private StreamedContent imagemConstrutora;

	public PortalConstrutoraBean() {
		filtroConstrutora = new FiltroConstrutora();
		filtroConstrutora.setAscendente(true);
		em = new JPAUtil().getEntityManager();
		construtoraDAO = new ConstrutoraDAO(em);
		// ITENS POR CARROSSEL
		// indiceCarrossel = 0;
		//context = FacesContext.getCurrentInstance();
		//imagemBean = context.getApplication().evaluateExpressionGet(context, "#{imagemBean}", ImagemBean.class);
		preencher();
	}

	/**
	 * Simula um banco de dados
	 */
	public void preencher() {

		Endereco e=new Endereco();
		e.setRua("Rua Búzios, Lote no 12, Quadra 35 - Calhau, LItoral, Calhau");
		
		Construtora r1 = new Construtora();
		 r1.setNomeFantasia("Plaza Norte Residence");
		// r1.setEndereco("MA 202 - Forquilha , Estrada de Ribamar, Maiobinha.");
		 r1.setUrlImagem("imagens/scr01.jpg");
		 r1.setAnuncio("A partir de R$200.000");
		 r1.setEndereco(e);
		
		 Construtora r2 = new Construtora();
		 r2.setNomeFantasia("Alliance Residence");
		// r2.setEndereco("Av. dos Holandeses, Litoral, Calhau");
		 r2.setUrlImagem("imagens/scr02.jpg");
		 r2.setAnuncio("A partir de R$200.000");
		 r2.setEndereco(e);
		
		 Construtora r3 = new Construtora();
		 r3.setNomeFantasia("Jardins do Turu");
//		 r3.setEndereco("Avenida General Arthur Carvalho, s/n - Turu, Turu, Turu");
		 r3.setUrlImagem("imagens/scr03.jpg");
		 r3.setAnuncio("A partir de R$200.000");
		 r3.setEndereco(e);
		
		 Construtora r4 = new Construtora();
		 r4.setNomeFantasia("Taroa Residence");
	//	 r4.setEndereco("R. Santo Inácio de Loiola, LItoral, Olho D´água");
		 r4.setUrlImagem("imagens/scr04.jpg");
		 r4.setAnuncio("A partir de R$200.000");
		 r4.setEndereco(e);
		
		 Construtora r5 = new Construtora();
		 r5.setNomeFantasia("Plaza das Flores Village");
	//	 r5.setEndereco("Estrada da Guaíba , s/n, Iguaíba, Iguaíba");
		 r5.setUrlImagem("imagens/scr05.jpg");
		 r5.setAnuncio("A partir de R$200.000");
		 r5.setEndereco(e);
		
		 Construtora r6 = new Construtora();
		 r6.setNomeFantasia("Village das Palmeiras Prime");
	//	 r6.setEndereco("Rua Projetada, Cohama, Cohama");
		 r6.setUrlImagem("imagens/scr06.jpg");
		 r6.setAnuncio("A partir de R$200.000");
		 r6.setEndereco(e);
		
		 Construtora r7 = new Construtora();
		 r7.setNomeFantasia("Aquamarine Residence");
		// r7.setEndereco("Rua Cinco, Super Quadra D , Lote 02- Ponta D´Areia,	 Litoral, Ponta do Farol");
		 r7.setUrlImagem("imagens/scr07.jpg");
		 r7.setAnuncio("A partir de R$200.000");
		 r7.setEndereco(e);
		
		 Construtora r8 = new Construtora();
		 r8.setNomeFantasia("Naturam Reserva Rangedor");
		// r8.setEndereco("Rua Búzios, Lote no 12, Quadra 35 - Calhau, LItoral,	 Calhau");
		 r8.setUrlImagem("imagens/scr08.jpg");
		 r8.setAnuncio("A partir de R$200.000");
		 r8.setEndereco(e);
		
		
		
		 Construtora r9 = new Construtora();
		 r9.setNomeFantasia("Plaza Norte Residence");
		// r9.setEndereco("MA 202 - Forquilha , Estrada de Ribamar, Maiobinha.");
		 r9.setUrlImagem("imagens/scr09.jpg");
		 r9.setAnuncio("A partir de R$200.000");
		 r9.setEndereco(e);
		
		 Construtora r10 = new Construtora();
		 r10.setNomeFantasia("Alliance Residence");
		// r10.setEndereco("Av. dos Holandeses, Litoral, Calhau");
		 r10.setUrlImagem("imagens/scr10.jpg");
		 r10.setAnuncio("A partir de R$200.000");
		 r10.setEndereco(e);
		
		 Construtora r11 = new Construtora();
		 r11.setNomeFantasia("Jardins do Turu");
		// r11.setEndereco("Avenida General Arthur Carvalho, s/n - Turu, Turu,Turu");
		 r11.setUrlImagem("imagens/scr11.jpg");
		 r11.setAnuncio("A partir de R$200.000");
		 r11.setEndereco(e);
		
		 Construtora r12 = new Construtora();
		 r12.setNomeFantasia("Taroa Residence");
		// r12.setEndereco("R. Santo Inácio de Loiola, LItoral, Olho D´água");
		 r12.setUrlImagem("imagens/scr12.jpg");
		 r12.setAnuncio("A partir de R$200.000");
		 r12.setEndereco(e);
		
		 Construtora r13 = new Construtora();
		 r13.setNomeFantasia("Plaza das Flores Village");
		// r13.setEndereco("Estrada da Guaíba , s/n, Iguaíba, Iguaíba");
		 r13.setUrlImagem("imagens/scr13.jpg");
		 r13.setAnuncio("A partir de R$200.000");
		 r13.setEndereco(e);
		
		 Construtora r14 = new Construtora();
		 r14.setNomeFantasia("Village das Palmeiras Prime");
		// r14.setEndereco("Rua Projetada, Cohama, Cohama");
		 r14.setUrlImagem("imagens/scr14.jpg");
		 r14.setAnuncio("A partir de R$200.000");
		 r14.setEndereco(e);
		
		 Construtora r15 = new Construtora();
		 r15.setNomeFantasia("Aquamarine Residence");
	//	 r15.setEndereco("Rua Cinco, Super Quadra D , Lote 02- Ponta D´Areia, Litoral, Ponta do Farol");
		 r15.setUrlImagem("imagens/scr15.jpg");
		 r15.setAnuncio("A partir de R$200.000");
		 r15.setEndereco(e);
		
		 Construtora r16 = new Construtora();
		 r16.setNomeFantasia("Naturam Reserva Rangedor");
		 //r16.setEndereco("Rua Búzios, Lote no 12, Quadra 35 - Calhau, LItoral, Calhau");
		 r16.setUrlImagem("imagens/scr16.jpg");
		 r16.setAnuncio("A partir de R$200.000");
		 r16.setEndereco(e);
		
		 construtoras.add(r1);
		 construtoras.add(r2);
		 construtoras.add(r3);
		 construtoras.add(r4);
		
		 construtoras.add(r5);
		 construtoras.add(r6);
		 construtoras.add(r7);
		 construtoras.add(r8);
		
		
		/**
		 * Sempre devem ser duas listas
		 */

		// DE 8 EM 8 - TOTAL 16 ITENS
		// amostras.clear();

//		construtoras.addAll(construtoraDAO.listarConstrutoras(filtroConstrutora));
		
		 construtoras.add(r9);
		 construtoras.add(r10);
		 construtoras.add(r11);
		 construtoras.add(r12);
		
		 construtoras.add(r13);
		 construtoras.add(r14);
		 construtoras.add(r15);
		 construtoras.add(r16);;

		
		// String urlImagem = null;
		// byte[] imgBytes = null;
		//
		// for (Construtora c : construtoras) {
		//
		// urlImagem = c.getUrlImagem();
		//
		// if (StringUtils.isNotEmpty(urlImagem)) {
		// imgBytes = FormatUtil.extrairBytes(urlImagem);
		// if(imgBytes!=null) {
		// c.setStreamImagem(imagemBean.getStreamedImagem(imgBytes));
		// System.out.println("STREAMED "+c.getStreamImagem());
		// }
		// }
		// }

		// for (List<Construtora> lista : FormatUtil.dividir(construtoras, 8)) {
		// amostras.add(lista);// duas partes de 4
		// }

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

	

	public List<Construtora> getConstrutoras() {
		return construtoras;
	}

	public void setConstrutoras(List<Construtora> construtoras) {
		this.construtoras = construtoras;
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

	public Construtora getConstrutora() {
		return construtora;
	}

	public void setConstrutora(Construtora construtora) {
		this.construtora = construtora;
	}
	
//	public StreamedContent getImagemConstrutora() {
//		javax.servlet.http.HttpServletRequest myRequest = (javax.servlet.http.HttpServletRequest) context
//				.getExternalContext().getRequest();
//		String urlImagem = (String) myRequest.getParameter("construtora");
//		byte[] imgBytes = null;
//		System.out.println("STREAMED " + urlImagem);
//
//		if (StringUtils.isNotEmpty(urlImagem)) {
//			imgBytes = FormatUtil.extrairBytes(urlImagem);
//			if (imgBytes != null) {
//				imagemConstrutora = (imagemBean.getStreamedImagem(imgBytes));
//			}
//		}
//
//		return imagemConstrutora;
//	}

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
