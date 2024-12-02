package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class AdministradorDAO {
    private EntityManagerFactory entityManagerFactory;

    public AdministradorDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Administrador inserirAdministrador(String login, String senha, String nome, String cpf, String email, String telefone) {
        EntityManager em = null;
        Administrador administrador = new Administrador();
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            administrador = new Administrador(login, senha, nome, cpf, email, telefone);
            em.persist(administrador);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                em.close();
        }
        return administrador;
    }

    public Administrador procurarAdministrador(String login) {
        EntityManager em = null;
        Administrador administrador = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("SELECT a FROM Administrador a WHERE a.login = :login");
            query.setParameter("login", login);
            administrador = (Administrador) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                em.close();
        }
        return administrador;
    }

    public void excluirAdm(String login) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Administrador administrador = em.find(Administrador.class, login);

            if (administrador != null) {
                em.remove(administrador);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  
            }
        } finally {
            if (em != null) {
                em.close();  
            }
        }
    }
}
