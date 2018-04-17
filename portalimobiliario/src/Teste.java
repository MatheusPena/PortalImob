
public class Teste {

	public static void main(String[] args) {
		javax.persistence.EntityManager em = new util.JPAUtil().getEntityManager();
		em.getTransaction().begin();
	}

}
