package dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.MapObject;

public class GenericDAO<T> {
	
	private EntityManager em;
	private Class<T> classe;
	private Map<String, Method> mapFiltros = new HashMap<>();
	
	public GenericDAO(EntityManager em, Class<T> classe) {
		super();
		this.em = em;
		this.classe = classe;
	}
	
	/**
	 * Construtor vazio para herança
	 */
	public GenericDAO() {
		
	}
	
	public void salvar(T c) throws Exception{
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		//em.close();
	}
	
	public void deletar(T c) throws Exception {
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}
	
	public void atualizar(T c) throws Exception {
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		//em.close();
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(String jpql) throws Exception {
		Query query = em.createQuery(jpql);
		return (ArrayList<T>)query.getResultList();
	}

	public T buscarPorID(Object id) throws Exception {
		T obj = null;
		em.getTransaction().begin();
		obj = em.find(classe, id);
		em.getTransaction().commit();
		//em.close();
		return obj;
	}
	
	/**
	 * Método com reflection
	 * 
	 * @param pstm
	 * @param chave
	 * @return
	 */
	public void setMetodo(java.sql.PreparedStatement pstm) {

		try {
			Method metodoInteiro = pstm.getClass().getMethod("setInt", Integer.TYPE, Integer.TYPE);			
			Method metodoString = pstm.getClass().getMethod("setString", Integer.TYPE, String.class);
			Method metodoDate = pstm.getClass().getMethod("setDate", Integer.TYPE, java.sql.Date.class);
			Method metodoNullInteiro = pstm.getClass().getMethod("setNull", Integer.TYPE, Integer.TYPE);
			
			//pstm.setNull(arg0, arg1);
			//preparedStatement.setNull(34, Types.INTEGER);
			// Method metodoArray = pstm.getClass().getMethod("setArray",
			// Integer.TYPE, java.sql.Array.class);
			mapFiltros.put("INTEIRO", metodoInteiro);
			mapFiltros.put("STRING", metodoString);
			mapFiltros.put("DATA", metodoDate);
			mapFiltros.put("NULL_INTEIRO", metodoNullInteiro);
			// mapFiltros.put("ARRAY", metodoArray);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

	public Method getMetodo(String chave) {
		return mapFiltros.get(chave);
	}

	public void setarParametros(java.sql.PreparedStatement preparedStatement, MapObject mapFiltros) {
		setMetodo(preparedStatement);
		// Collections.sort(mapFiltros.getAll());
		int i = 1;
		for (MapObject mo : mapFiltros.getAll()) {
			String chave = (String) mo.getChave();
			Object valor = mo.getValor();
			Method metodo = getMetodo(chave);
			try {
				if (preparedStatement != null) {
				
					System.out.println("INDICE "+i+" VALOR "+valor);
					metodo.invoke(preparedStatement, i, valor);
					i++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Método para tratamento JDBC de chave estrangeira
	 * inteira e nula
	 * @param obj - Entrada com a chave estrangeira
	 * @param mo - Especialista que ordena as entradas do 
	 * preparedstatement
	 */
	public static MapObject inteiroNulo(Integer obj, final MapObject mo) {
		if(obj!=null) {
			mo.put("INTEIRO", obj);
		}else{
			mo.put("NULL_INTEIRO", Types.INTEGER);
		}
		return mo;
	}

}
