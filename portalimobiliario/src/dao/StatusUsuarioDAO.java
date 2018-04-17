package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dominio.StatusUsuario;
import filtro.FiltroStatusUsuario;
import util.MapObject;

public class StatusUsuarioDAO extends GenericDAO<StatusUsuario> {

	private EntityManager em;

	public StatusUsuarioDAO(EntityManager em) {
		this.em = em;
	}
	
	public StatusUsuarioDAO() {
	
	}

	public int quantidadeFiltrados(FiltroStatusUsuario filtro) {
		Criteria criteria = criarCriteria(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	public Criteria criarCriteria(FiltroStatusUsuario filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(StatusUsuario.class);
		String descricao = filtro.getDescricao();
		if (StringUtils.isNotEmpty(descricao)) {
			criteria.add(Restrictions.ilike("descricao", descricao, MatchMode.ANYWHERE));
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List<StatusUsuario> listarUsuarios(FiltroStatusUsuario filtro) {
		Criteria criteria = criarCriteria(filtro);

		if (filtro.getPrimeiroRegistro() != null) {
			criteria.setFirstResult(filtro.getPrimeiroRegistro());
		}

		if (filtro.getQuantidadeRegistros() != null) {
			criteria.setMaxResults(filtro.getQuantidadeRegistros());
		}

		if (filtro.getAscendente() != null && filtro.getAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		return criteria.list();
	}

	public boolean salvar(com.mysql.jdbc.Connection con, StatusUsuario statusUsuario) throws Exception {
		java.sql.PreparedStatement preparedStatement = null;
		con.setAutoCommit(false);
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("insert into status_usuario (descricao) values");
		MapObject mapParametros = new MapObject();
		String descricao = statusUsuario.getDescricao();
		mapParametros.put("STRING", descricao);
		strQuery.append(" (?)");
		strQuery.append(" on duplicate key update");
		mapParametros.put("STRING", descricao);
		strQuery.append(" descricao = ? ");
		String sql = strQuery.toString();
		try {
			preparedStatement = con.prepareStatement(sql);
			setarParametros(preparedStatement, mapParametros);
			preparedStatement.executeUpdate();
		} finally {

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
