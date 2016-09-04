package teste;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.CadastroBean;

public class TesteHibernatePlanilha {
	public static void main(String[] args){
		try{
			SessionFactory fabrica = new Configuration()
            		.configure()
            		.buildSessionFactory();
            Session sessao = fabrica.openSession();

            List<CadastroBean> listar = sessao.createQuery("from CadastroBean").list();
            
//            listar.forEach(c->{
 //           });
            sessao.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao listar: "+e);
        }
	}
}
