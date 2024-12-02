package Model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class PessoaDAO {
    private EntityManagerFactory emf;

    public PessoaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean inserirPessoa(String nome, char sexo, LocalDate dataNasc, String rg, String cpf, double salario, int tempoContribuicao, String areaTrabalho, Administrador administrador) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Pessoa pessoa = new Pessoa(nome, sexo, dataNasc, rg, cpf, salario, tempoContribuicao, areaTrabalho, administrador);
            em.persist(pessoa);
            transaction.commit();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
			return false;
        } finally {
            em.close();
        }
    }

    public boolean excluirPessoa(String cpf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Pessoa pessoa = em.find(Pessoa.class, cpf);
            em.remove(pessoa);
            transaction.commit();
            return true;
        }catch (Exception e) {
        	e.printStackTrace();
			return false;
        } 
        finally {
            em.close();
        }
    }
    
    public boolean excluirPessoas(Administrador administrador) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
        	Query query = em.createQuery("FROM Pessoa p WHERE p.administrador = :a");
            query.setParameter("a", administrador);

            ArrayList<Pessoa> listaPessoas = (ArrayList<Pessoa>) query.getResultList();
            transaction.begin();
            for (Pessoa pessoa : listaPessoas) {
            	em.remove(pessoa);
            }           
            transaction.commit();
            return true;
        }catch (Exception e) {
        	e.printStackTrace();
			return false;
        } 
        finally {
            em.close();
        }
    }

    public ArrayList<Pessoa> consultarPessoas(Administrador administrador) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();

        try {
            Query query = em.createQuery("FROM Pessoa p WHERE p.administrador = :a");
            query.setParameter("a", administrador);

            listaPessoas = (ArrayList<Pessoa>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return listaPessoas;
    }

    public boolean modificarPessoa(String nome, char sexo, LocalDate dataNasc, String rg, String cpf, double salario, int tempoContribuicao, String areaTrabalho, Administrador administrador) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            Pessoa pessoa = em.find(Pessoa.class, cpf);
            pessoa.setNome(nome);
            pessoa.setSexo(sexo);
            pessoa.setDataNasc(dataNasc);
            pessoa.setRg(rg);
            pessoa.setSalario(salario);
            pessoa.setTempoContribuicao(tempoContribuicao);
            pessoa.setAreaTrabalho(areaTrabalho);
            em.merge(pessoa);
            transaction.commit();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
			return false;
        } finally {
            em.close();
        }
    }

    public Pessoa procurarPessoa(String cpf) {
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;

        try {
            pessoa = em.find(Pessoa.class, cpf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return pessoa;
    }
}