package teste;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.ConfExtraBean;
import model.ConfigBean;

public class TesteHibernateExtra {
	public static void main(String[] args){
		try{
			SessionFactory fabrica = new Configuration()
            		.configure()
            		.buildSessionFactory();
            Session sessao = fabrica.openSession();

            List<ConfExtraBean> listar = sessao.createQuery("from ConfExtraBean").list();
            
            listar.forEach(c->{
            	System.out.println(c.getCODIGO()+"-"+c.getDIRETORIO_TEMP()+"-"
                +c.getPLANILHA_LOCALIZACAO()+"-"+c.getPLANILHA_NOME());
            });
            sessao.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao listar: "+e);
        }
	}
}
