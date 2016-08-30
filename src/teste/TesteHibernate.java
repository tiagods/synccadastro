package teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

            List<ConfigBean> listar = new ArrayList();
            listar = sessao.createQuery("from ConfigBean").getResultList();
            
            for(int i=0; i<listar.size(); i++){
            	ConfigBean conf = listar.get(i);
                System.out.println(conf.getCODIGO()+"-"+conf.getSEGUNDO()+"-"
                +conf.getMINUTO()+"-"+conf.getHORA()+"-"+conf.getMES());
            }
            sessao.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao listar: "+e);
        }
	}
}
