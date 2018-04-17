package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Contato;
import util.MapObject;

public class ContatoDAO extends GenericDAO<Contato> {

	
	public ContatoDAO() {
		
	}
	
	public boolean salvar(java.sql.Connection con, Contato contato) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		con.setAutoCommit(false);

		StringBuilder strQuery = new StringBuilder();
		strQuery.append("insert into contato (id, email, outroEmail, telCelular, telOutro, telResidencial) values ");
			
		MapObject mapParametros = new MapObject();
		
		String id = contato.getId();
		String email = contato.getEmail();
		String outroEmail = contato.getOutroEmail();
		String telCelular = contato.getTelCelular();
		String telOutro = contato.getTelOutro();
		String telResidencial = contato.getTelResidencial();
		
		mapParametros.put("STRING", id);
		strQuery.append("(?,");
		
		mapParametros.put("STRING", email);
		strQuery.append(" ?,");
		
		mapParametros.put("STRING", outroEmail);
		strQuery.append(" ?, ");
		
		mapParametros.put("STRING", telCelular);
		strQuery.append(" ?, ");
		
		mapParametros.put("STRING", telOutro);
		strQuery.append(" ?, ");
		
		mapParametros.put("STRING", telResidencial);
		strQuery.append("?)");
				
		strQuery.append(" on duplicate key update");
		
		mapParametros.put("STRING", id);
		strQuery.append(" id = ?,");
		
		mapParametros.put("STRING", email);
		strQuery.append(" email = ?,");
		
		mapParametros.put("STRING", outroEmail);
		strQuery.append(" outroEmail = ?,");
				
		mapParametros.put("STRING", telCelular);
		strQuery.append(" telCelular = ?,");
		
		mapParametros.put("STRING", telOutro);
		strQuery.append(" telOutro = ?,");
				
		mapParametros.put("STRING", telResidencial);
		strQuery.append(" telResidencial = ?");
		
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

	public Integer ultimoID(java.sql.Connection con) throws Exception {

		List<Integer> lista = new ArrayList<>();
		java.sql.PreparedStatement preparedStatement = null;
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("select max(id) as id from contato");
		String selectSQL = strQuery.toString();
		try {

			preparedStatement = con.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				lista.add(rs.getInt("id"));
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(!lista.isEmpty()) {
			return lista.get(0) + 1;
		}
		return 1;
	}
	

}
