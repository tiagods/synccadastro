package teste;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.ConfigBean;

public class TesteHibernate {
	public static void main(String[] args){
		try{
			SessionFactory fabrica = new Configuration()
            		.configure()
            		.buildSessionFactory();
            Session sessao = fabrica.openSession();

            List<ConfigBean> listar = sessao.createQuery("from ConfigBean").list();
            
            listar.forEach(c->{
            	System.out.println(c.getCODIGO()+"-"+c.getSEGUNDO()+"-"
                +c.getMINUTO()+"-"+c.getHORA()+"-"+c.getMES()+"-"+c.getDIA_DA_SEMANA());
            });
            sessao.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao listar: "+e);
        }
	}
}
