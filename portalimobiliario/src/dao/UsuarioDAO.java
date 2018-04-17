package dao;

import java.sql.SQLException;
import java.util.Date;

import dominio.Acesso;
import dominio.Contato;
import dominio.Convenio;
import dominio.Endereco;
import dominio.StatusUsuario;
import dominio.Usuario;
import util.FormatUtil;
import util.MapObject;

public class UsuarioDAO extends GenericDAO<Usuario> {

	
	public UsuarioDAO() {
		
	}
	
	public boolean salvar(java.sql.Connection con, Usuario usuario) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		con.setAutoCommit(false);

		StringBuilder strQuery = new StringBuilder();
		strQuery.append("insert into usuario (numeroPessoa, tipoPessoa, nome, dataCadastro,");
		strQuery.append(" acesso, contato, endereco, convenio, statusUsuario) values");
			
		MapObject mapParametros = new MapObject();
		
		String numeroPessoa = usuario.getNumeroPessoa();
		String tipoPessoa = usuario.getTipoPessoa();
		String nome = usuario.getNome();
		Date dataCadastro = usuario.getDataCadastro();
		Acesso acesso = usuario.getAcesso();
		String acessoID = null;
		Contato contato = usuario.getContato();
		String contatoID = null;
		Endereco endereco = usuario.getEndereco();
		String enderecoID = null;
		Convenio convenio = usuario.getConvenio();
		Integer convenioID = null;
		StatusUsuario statusUsuario = usuario.getStatusUsuario();
		Integer statusUsuarioID = null;
				
		mapParametros.put("STRING", numeroPessoa);
		strQuery.append(" (?,");
		
		mapParametros.put("STRING", tipoPessoa);
		strQuery.append(" ?,");
		
		mapParametros.put("STRING", nome);
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
		
		if(convenio!=null) {
			convenioID = convenio.getId();
		}
		
		inteiroNulo(convenioID, mapParametros);
		strQuery.append(" ?,");
		
		if(statusUsuario!=null) {
			statusUsuarioID = statusUsuario.getId();
		}
		
		inteiroNulo(statusUsuarioID, mapParametros);
		strQuery.append(" ?)");
	
		strQuery.append(" on duplicate key update");
		
		mapParametros.put("STRING", numeroPessoa);
		strQuery.append(" numeroPessoa = ?,");
		
		mapParametros.put("STRING", tipoPessoa);
		strQuery.append(" tipoPessoa = ?,");
		
		mapParametros.put("STRING", nome);
		strQuery.append(" nome = ?,");
		
		//inteiroNulo(contatoID, mapParametros);
		mapParametros.put("STRING", contatoID);
		strQuery.append(" contato = ?,");
		
		//inteiroNulo(enderecoID, mapParametros);
		mapParametros.put("STRING", enderecoID);
		strQuery.append(" endereco = ?,");	
		
		inteiroNulo(convenioID, mapParametros);
		strQuery.append(" convenio = ?,");
		
		inteiroNulo(statusUsuarioID, mapParametros);
		strQuery.append(" statusUsuario = ?");
			
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
