package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dominio.Acesso;
import dominio.Construtora;
import dominio.Contato;
import dominio.Endereco;
import dominio.StatusConstrutora;
import filtro.FiltroConstrutora;
import util.FormatUtil;
import util.MapObject;

public class ConstrutoraDAO extends GenericDAO<Construtora> {
	
	private EntityManager em;

	public ConstrutoraDAO(EntityManager em) {
		this.em = em;
	}
	
	public ConstrutoraDAO() {
		
	}
	
	public int quantidadeFiltrados(FiltroConstrutora filtro) {
		Criteria criteria = criarCriteria(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	public Criteria criarCriteria(FiltroConstrutora filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Construtora.class);
		
		String numeroPessoa = filtro.getNumeroPessoa();
		String nomeFantasia = filtro.getNomeFantasia();
		
		if(StringUtils.isNotEmpty(numeroPessoa)) {
			criteria.add(Restrictions.idEq(numeroPessoa));
		}
		
		if(StringUtils.isNotEmpty(nomeFantasia)) {
			criteria.add(Restrictions.ilike("nomeFantasia", nomeFantasia, MatchMode.ANYWHERE));
		}

		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	public List<Construtora> listarConstrutoras(FiltroConstrutora filtro) {
		Criteria criteria = criarCriteria(filtro);
		
		Integer primeiroRegistro = filtro.getPrimeiroRegistro();
		Integer quantidadeRegistros = filtro.getQuantidadeRegistros();
		
		if(primeiroRegistro!=null) {
			criteria.setFirstResult(primeiroRegistro);
		}
		
		if(quantidadeRegistros!=null) {
			criteria.setMaxResults(quantidadeRegistros);
		}
		
		Boolean ascendente = filtro.getAscendente();
		String propriedadeOrdenacao = filtro.getPropriedadeOrdenacao();
					
		if (ascendente!=null && ascendente && StringUtils.isNotEmpty(propriedadeOrdenacao)) {
			criteria.addOrder(Order.asc(propriedadeOrdenacao));
		} else if (StringUtils.isNotEmpty(propriedadeOrdenacao)) {
			criteria.addOrder(Order.desc(propriedadeOrdenacao));
		}
		return criteria.list();
	}
	
	
	public boolean salvar(java.sql.Connection con, Construtora construtora) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		con.setAutoCommit(false);

		StringBuilder strQuery = new StringBuilder();
		strQuery.append("insert into construtora (numeroPessoa, nomeFantasia, urlImagem, dataCadastro,");
		strQuery.append(" acesso, contato, endereco, statusConstrutora) values");
			
		MapObject mapParametros = new MapObject();
		
		String numeroPessoa = construtora.getNumeroPessoa();
		String nomeFantasia = construtora.getNomeFantasia();
		String urlImagem = construtora.getUrlImagem();
		Date dataCadastro = construtora.getDataCadastro();
		Acesso acesso = construtora.getAcesso();
		String acessoID = null;
		Contato contato = construtora.getContato();
		String contatoID = null;
		Endereco endereco = construtora.getEndereco();
		String enderecoID = null;
		
		StatusConstrutora statusConstrutora = construtora.getStatusConstrutora();
		Integer statusConstrutoraID = null;
				
		mapParametros.put("STRING", numeroPessoa);
		strQuery.append(" (?,");
				
		mapParametros.put("STRING", nomeFantasia);
		strQuery.append(" ?,");
		
		mapParametros.put("STRING", urlImagem);
		strQuery.append(" ?,");
		
		mapParametros.put("DATA", FormatUtil.converteDataSQL(dataCadastro));
		strQuery.append(" ?,");
		
		acessoID = acesso.getAcesso();
		mapParametros.put("STRING", acessoID);
		strQuery.append(" ?,");	
			
		contatoID = contato.getId();
		mapParametros.put("STRING", contatoID);
		strQuery.append(" ?,");	
				
		enderecoID = endereco.getId();
		
		mapParametros.put("STRING", enderecoID);
		strQuery.append(" ?,");
		
		if(statusConstrutora!=null) {
			statusConstrutoraID = statusConstrutora.getId();
		}
		
		inteiroNulo(statusConstrutoraID, mapParametros);
		strQuery.append(" ?)");
	
		strQuery.append(" on duplicate key update");
		
		mapParametros.put("STRING", numeroPessoa);
		strQuery.append(" numeroPessoa = ?,");
				
		mapParametros.put("STRING", nomeFantasia);
		strQuery.append(" nomeFantasia = ?,");
		
		mapParametros.put("STRING", urlImagem);
		strQuery.append(" urlImagem = ?,");
		
		mapParametros.put("DATA", FormatUtil.converteDataSQL(dataCadastro));
		strQuery.append(" dataCadastro = ?,");
		
		mapParametros.put("STRING", contatoID);
		strQuery.append(" contato = ?,");
		
		mapParametros.put("STRING", enderecoID);
		strQuery.append(" endereco = ?,");	
		
		inteiroNulo(statusConstrutoraID, mapParametros);
		strQuery.append(" statusConstrutora = ?");
			
		String sql = strQuery.toString();

		try {
			preparedStatement = con.prepareStatement(sql);
			setarParametros(preparedStatement, mapParametros);
			preparedStatement.executeUpdate();
		}  finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

}
