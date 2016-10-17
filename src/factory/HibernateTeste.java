package factory;
import java.util.List;
import org.hibernate.Session;
import model.CadastroBean;
public class HibernateTeste {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List lista = factory.getList(session, "CadastroBean");
		lista.forEach(c->{
			System.out.println(""+((CadastroBean)c).getCOD()+"\t"+((CadastroBean)c).getEMPRESA());
			
		});
		factory.closeSession(session);		
	}

}
