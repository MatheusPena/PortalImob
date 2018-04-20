package controle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dao.AcessoDAO;
import dao.ConstrutoraDAO;
import dao.ContatoDAO;
import dao.EnderecoDAO;
import dao.GenericDAO;
import dominio.Acesso;
import dominio.Construtora;
import dominio.Contato;
import dominio.Endereco;
import filtro.FiltroConstrutora;
import util.ConexaoJDBC;
import util.FormatUtil;
import util.JPAUtil;
import util.JSFUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ConstrutoraBean implements Serializable {

	private FiltroConstrutora filtroConstrutora;
	private EntityManager em;
	private ConstrutoraDAO construtoraDAO;
	private Construtora construtora;
	private Acesso acesso;
	private Contato contato;
	private Endereco endereco;
	private String urlImagem;
	private ImagemBean imagemBean;
	private LazyDataModel<Construtora> dataModelConstrutora;
	private int quantidadeConstrutoras;

	public ConstrutoraBean() {
		filtroConstrutora = new FiltroConstrutora();
		filtroConstrutora.setAscendente(true);
		em = new JPAUtil().getEntityManager();
		construtoraDAO = new ConstrutoraDAO(em);
		FacesContext context = FacesContext.getCurrentInstance();
		imagemBean = context.getApplication().evaluateExpressionGet(context, "#{imagemBean}", ImagemBean.class);
		novo();
		iniciaLazyConstrutora();
	}

	public void iniciaLazyConstrutora() {
		setDataModelConstrutora(new LazyDataModel<Construtora>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Construtora> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					java.util.Map<String, Object> filters) {
				filtroConstrutora.setPrimeiroRegistro(first);
				filtroConstrutora.setQuantidadeRegistros(pageSize);
				filtroConstrutora.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtroConstrutora.setPropriedadeOrdenacao(sortField);
				quantidadeConstrutoras = construtoraDAO.quantidadeFiltrados(filtroConstrutora);
				
				System.out.println("q "+quantidadeConstrutoras);
				
				setRowCount(quantidadeConstrutoras);
				return construtoraDAO.listarConstrutoras(filtroConstrutora);
			}
		});
	}

	public void preencherAtualizacao(Construtora c) {
		if (c != null) {
			this.construtora = c;
			this.acesso = c.getAcesso();
			this.endereco = c.getEndereco();
			this.contato = c.getContato();

			if (acesso == null) {
				acesso = new Acesso();
			}

			if (endereco == null) {
				endereco = new Endereco();
			}

			if (contato == null) {
				contato = new Contato();
			}

			/**
			 * Setando a imagem para o canvas
			 */
			String urlImagem = c.getUrlImagem();
			byte[] imgBytes = null;
			if (StringUtils.isNotEmpty(urlImagem)) {
				imgBytes = FormatUtil.extrairBytes(urlImagem);
				if (imgBytes != null) {
					this.construtora.setStreamImagem(imagemBean.getStreamedImagem(imgBytes));
					this.construtora.setBytesImagem(imgBytes);
				}

			}
		}
	}

	public void carregarImagem(FileUploadEvent event) {

		byte[] bytesArquivo = event.getFile().getContents();

		String nomeArquivo = event.getFile().getFileName();
		StringBuilder strPath = new StringBuilder();

		if (nomeArquivo != null && nomeArquivo.contains("temp")) {
			JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_WARN, "Nome de arquivo inválido: palavra 'temp'!");
			return;
		}

		/**
		 * CRIAR CAMINHO TEMPORÁRIO
		 */
		strPath.append("..");// sai do diretorio raiz (onde está a aplicação
		strPath.append(File.separator);// "/" raiz
		strPath.append("portalImob");// pasta para uploads genérica
		strPath.append(File.separator);
		strPath.append("uploads");// pasta para uploads genérica
		strPath.append(File.separator);// separador
		strPath.append("imagens");// pasta específica para documentos
		strPath.append(File.separator);// separador
		strPath.append("construtora");
		strPath.append(File.separator);
		String numPessoa = construtora.getNumeroPessoa();

		if (StringUtils.isEmpty(numPessoa)) {
			JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_WARN,
					"CNPJ obrigatório para identificação da imagem!");
			return;
		}

		strPath.append(FormatUtil.removerMascara(numPessoa));
		strPath.append(File.separator);

		// String extensao =
		// org.apache.commons.io.FilenameUtils.getExtension(nomeArquivo);
		// nomeArquivo = nomeArquivo + "." + extensao;

		strPath.append(nomeArquivo);

		String caminhoRelativo = strPath.toString();
		String caminhoArquivo = construtora.getUrlImagem();

		if (caminhoArquivo == null) {
			caminhoArquivo = JSFUtil.getRealPath(FacesContext.getCurrentInstance()) + caminhoRelativo;
		}

		File f = new File(caminhoArquivo);

		try {
			/**
			 * O canonical path "conserta" o caminho, ou seja, retira a saída de
			 * pasta (../) e de quebra não mostra o nome do projeto
			 */
			caminhoArquivo = f.getCanonicalPath();

			construtora.setStreamImagem(imagemBean.getStreamedImagem(bytesArquivo));
			construtora.setUrlImagem(caminhoArquivo);
			construtora.setBytesImagem(bytesArquivo);
			construtora.setDescricaoImagem(nomeArquivo);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void excluirImagem() {
		construtora.setUrlImagem(null);
		if (getUrlImagem() != null) {
			File f = new File(getUrlImagem());
			if (f.exists()) {
				f.delete();
				String numPessoa = construtora.getNumeroPessoa();
				if (numPessoa != null) {
					Construtora c = em.find(Construtora.class, numPessoa);
					if (c != null) {
						GenericDAO<Construtora> gDAO = new GenericDAO<>(em, Construtora.class);
						c.setUrlImagem(null);
						try {
							gDAO.atualizar(c);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public boolean salvarImagem() {

		String caminhoAntigo = construtora.getUrlImagem();
		Construtora c = em.find(Construtora.class, construtora.getNumeroPessoa());

		if (c != null) {

			String urlAntiga = c.getUrlImagem();
			String urlNova = construtora.getUrlImagem();

			/**
			 * Os dois ifs abaixo retiram as extensões, pois pode ser que outro
			 * arquivo venha com outra extensão, o resto seria igual
			 */
			if (urlAntiga != null) {

				if (urlAntiga.indexOf(".") > 0) {
					urlAntiga = urlAntiga.substring(0, urlAntiga.lastIndexOf("."));
				}

				if (urlNova != null && urlNova.indexOf(".") > 0) {
					urlNova = urlNova.substring(0, urlNova.lastIndexOf("."));
				}

				System.out.println("URL ANTIGA " + urlAntiga);
				System.out.println("URL NOVA " + urlNova);

				/**
				 * Depois das urls tratadas, caso exista uma url no banco essa
				 * será deletada
				 */
				if (urlAntiga.equals(urlNova)) {
					String urlAntigaAux = c.getUrlImagem();
					File arquivoAntigo = new File(urlAntigaAux);
					if (arquivoAntigo.exists()) {
						arquivoAntigo.delete();
					}
				}
			}
			
			try {
				// salva o arquivo definitivo
				byte[] b = construtora.getBytesImagem();
				if(StringUtils.isNotEmpty(caminhoAntigo) && b!=null) {
					FormatUtil.salvarArquivo(caminhoAntigo, b);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}

	public String salvar() {
		// System.out.println("STATUS "+construtora.getStatusConstrutora());
		
		
		
		com.mysql.jdbc.Connection con = ConexaoJDBC.conectar();
		ContatoDAO contatoDAO = new ContatoDAO();
		AcessoDAO acessoDAO = new AcessoDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		ConstrutoraDAO construtoraDAO = new ConstrutoraDAO();
		try {
			String numPessoa = construtora.getNumeroPessoa();
			
			if(StringUtils.isNotEmpty(numPessoa)) {
				
				String id = FormatUtil.apenasLetrasENumeros(numPessoa);
				acesso.setAcesso(id);
				contato.setId(id);
				endereco.setId(id);
				acessoDAO.salvar(con, acesso);
				contatoDAO.salvar(con, contato);
				enderecoDAO.salvar(con, endereco);
				construtora.setAcesso(this.acesso);
				construtora.setContato(this.contato);
				construtora.setEndereco(this.endereco);

				construtora.setDataCadastro(new Date());
				construtoraDAO.salvar(con, construtora);
				con.commit();
				// Salvar a imagem definitiva
				salvarImagem();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			JSFUtil.mostraMensagemSemFlash(FacesMessage.SEVERITY_ERROR, "Houveram problemas nas inserções dos dados!");
			return "";
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSFUtil.mostraMensagem(FacesMessage.SEVERITY_INFO, "Parabéns cadastro finalizado com sucesso!");
		return "cadastroconstrutora?faces-redirect=true";
	}

	public void novo() {
		construtora = new Construtora();
		acesso = new Acesso();
		contato = new Contato();
		endereco = new Endereco();
	}

	public Construtora getConstrutora() {
		return construtora;
	}

	public void setConstrutora(Construtora construtora) {
		this.construtora = construtora;
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

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public LazyDataModel<Construtora> getDataModelConstrutora() {
		return dataModelConstrutora;
	}

	public void setDataModelConstrutora(LazyDataModel<Construtora> dataModelConstrutora) {
		this.dataModelConstrutora = dataModelConstrutora;
	}

	public int getQuantidadeConstrutoras() {
		return quantidadeConstrutoras;
	}

	public void setQuantidadeConstrutoras(int quantidadeConstrutoras) {
		this.quantidadeConstrutoras = quantidadeConstrutoras;
	}

}
