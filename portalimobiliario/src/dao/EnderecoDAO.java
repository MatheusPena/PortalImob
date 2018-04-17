package dao;

import java.sql.SQLException;

import dominio.Endereco;
import util.MapObject;

public class EnderecoDAO extends GenericDAO<Endereco> {

	public EnderecoDAO() {

	}

	public boolean salvar(java.sql.Connection con, Endereco endereco) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		con.setAutoCommit(false);

		StringBuilder strQuery = new StringBuilder();
		strQuery.append("insert into endereco (id, rua, bairro, cidade, uf, cep, complemento) values");

		MapObject mapParametros = new MapObject();
	
		String id = endereco.getId();
		String rua = endereco.getRua();
		String bairro = endereco.getBairro();
		String cidade = endereco.getCidade();
		String estado = endereco.getUf();
		String cep = endereco.getCep();
		String complemento = endereco.getComplemento();
		
		mapParametros.put("STRING", id);
		strQuery.append(" (?,");

		mapParametros.put("STRING", rua);
		strQuery.append(" ?,");

		mapParametros.put("STRING", bairro);
		strQuery.append(" ?,");

		mapParametros.put("STRING", cidade);
		strQuery.append(" ?,");

		mapParametros.put("STRING", estado);
		strQuery.append(" ?,");
		
		mapParametros.put("STRING", cep);
		strQuery.append(" ?,");

		mapParametros.put("STRING", complemento);
		strQuery.append(" ?)");
		
		strQuery.append(" on duplicate key update");
		
		mapParametros.put("STRING", id);
		strQuery.append(" id = ?,");

		mapParametros.put("STRING", rua);
		strQuery.append(" rua = ?,");

		mapParametros.put("STRING", bairro);
		strQuery.append(" bairro = ?,");

		mapParametros.put("STRING", cidade);
		strQuery.append(" cidade = ?,");

		mapParametros.put("STRING", estado);
		strQuery.append(" uf = ?,");

		mapParametros.put("STRING", cep);
		strQuery.append(" cep = ?,");

		mapParametros.put("STRING", complemento);
		strQuery.append(" complemento = ?");

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
