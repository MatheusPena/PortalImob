package util;

import java.util.ArrayList;
import java.util.List;

public class MapObject  {

	public Object chave;
	private Object valor;
	private List<MapObject> listaObjetos;

	public MapObject() {
		listaObjetos = new ArrayList<>();
	}

	public void put(Object chave, Object valor) {
		MapObject obj1 = new MapObject();
		obj1.setChave(chave);
		obj1.setValor(valor);
		listaObjetos.add(obj1);
		//System.out.println("TAM LISTA " + listaObjetos.size());
	}

	public List<MapObject> getAll() {
		return listaObjetos;
	}

	public Object getChave() {
		return chave;
	}

	public void setChave(Object chave) {
		this.chave = chave;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	
}
