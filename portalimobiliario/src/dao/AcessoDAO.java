package dao;

import java.sql.SQLException;

import dominio.Acesso;
import dominio.Contato;
import dominio.NivelUsuario;
import util.MapObject;

public class AcessoDAO extends GenericDAO<Contato> {

	public AcessoDAO() {

	}

	public boolean salvar(com.mysql.jdbc.Connection con, Acesso acesso) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		con.setAutoCommit(false);

		StringBuilder strQuery = new StringBuilder();
		strQuery.append("insert into acesso (acesso, senha, nivelUsuario) values");

		MapObject mapParametros = new MapObject();

		String ac = acesso.getAcesso();
		String senha = acesso.getSenha();
		NivelUsuario nivelUsuario = acesso.getNivelUsuario();
		Integer nivelUsuarioID = null;

		mapParametros.put("STRING", ac);
		strQuery.append(" (?,");

		mapParametros.put("STRING", senha);
		strQuery.append(" ?, ");

		if (nivelUsuario != null) {
			nivelUsuarioID = nivelUsuario.getId();
		}

		inteiroNulo(nivelUsuarioID, mapParametros);
		
		strQuery.append("?)");
		mapParametros.put("STRING", ac);
		strQuery.append(" on duplicate key update acesso = ?,");

		mapParametros.put("STRING", senha);
		strQuery.append(" senha = ?,");

		inteiroNulo(nivelUsuarioID, mapParametros);
		strQuery.append(" nivelUsuario = ?");

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
