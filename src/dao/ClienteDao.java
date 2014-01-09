package dao;

import entities.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ClienteDao {

    private EntityManager entityManager = null;
    private EntityManagerFactory factory = null;

    /**
     * Método utilizado para obter o entity manager.
     *
     * @return
     */
    private EntityManager getEntityManager() {
        // Obtém o factory a partir da unidade de persistência.
        factory = Persistence.createEntityManagerFactory("Restaurante");
        // Cria um entity manager.
        entityManager = factory.createEntityManager();
        // Fecha o factory para liberar os recursos utilizado.

        return entityManager;
    }

    /**
     * Método utilizado para salvar ou atualizar as informações de uma pessoa.
     *
     * @param cliente
     *
     */
    public void salvar(Cliente cliente) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            System.out.println("Salvando o Cliente.");
            // Verifica se a Contato ainda não está salva no banco de dados.
            if (cliente.getId() == null) {
                // Salva os dados da Contato.
                manager.persist(cliente);
            } else {
                // Atualiza os dados da pessoa.
                cliente = manager.merge(cliente);
            }
            // Finaliza a transação.
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    /**
     * Metodo pesquisarPorId Consulta o Contato pelo ID.
     *
     * @param id
     * @return o objeto Contato.
     */
    public Cliente pesquisarPorId(Integer id) {
        EntityManager manager = getEntityManager();
        Cliente contato = null;
        try {
            // Consulta uma pessoa pelo seu ID.
            contato = manager.find(Cliente.class, id);
        } finally {
            manager.close();
        }
        return contato;
    }

    /**
     * Método que excluir a Contato do banco de dados.
     *
     * @param id
     */
    public void excluir(Integer id) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.

            Cliente cliente = manager.find(Cliente.class, id);
            System.out.println("Excluindo os dados de: " + cliente.getNome());
            // Remove a pessoa da base de dados.
            manager.remove(cliente);
            // Finaliza a transação.
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    /**
     * Método que atualiza um Contato do banco de dados.
     *
     * @param cliente
     * @return Contato
     */
    public Cliente atualizar(Cliente cliente) {
        EntityManager manager = getEntityManager();
        Cliente cliente1;
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.

            cliente1 = manager.find(Cliente.class, cliente.getId());

            manager.merge(cliente);

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
        return cliente1;
    }

    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Contato>
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> todos() {
        EntityManager manager = getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = (List<Cliente>) manager.createQuery(
                    "Select a from Cliente a").getResultList();
        } finally {
            manager.close();
        }
        return clientes;
    }
    
    
    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Contato>
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> todosClientesComDDD(String ddd) {
        EntityManager manager = getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query q = manager.createQuery("Select a from Cliente a where a.telefone like :parametro");
            q.setParameter("parametro", "("+ddd+")%");
            clientes = (List<Cliente>) q.getResultList();
        } finally {
            manager.close();
        }
        return clientes;
    }
    
    
    
    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Contato>
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> todosComDDD(String ddd) {
        EntityManager manager = getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query query = manager.createQuery("Select a from Cliente a where a.telefone like :param ");
            query.setParameter("param", "("+ddd+")%");
            clientes = (List<Cliente>) query.getResultList();
        } finally {
            manager.close();
        }
        return clientes;
    }

    /**
     * Método pesq que busca todos do banco de dados usa-se uma query.
     *
     * @param query
     * @return List<Contato>
     */
    public Cliente pesq(String query) {
        EntityManager manager = getEntityManager();
        Cliente cliente = new Cliente();
        try {
            Query q = manager.createQuery(query);
            cliente = (Cliente) q.getSingleResult();
        } finally {
            manager.close();
        }
        return cliente;
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> listPesq(String query) {
        EntityManager manager = getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query q = manager.createQuery(query);
            clientes = (List<Cliente>) q.getResultList();
        } finally {
            getEntityManager().close();
        }
        return clientes;
    }

    /**
     * Método pesqParam busca do banco de dados passando uma query e uma map de
     * parametros.
     *
     * @param query
     * @param params <String, @ * return List<Contato> @return
     * @return
     */
    public Cliente pesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        Cliente cliente = new Cliente();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                cliente = (Cliente) q.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return cliente;

    }

    /**
     * Método listPesqParam busca do banco de dados passando uma query e uma map
     * de parametros.
     *
     * @param query
     * @param params
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> listPesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                clientes = (List<Cliente>) q.getResultList();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return clientes;

    }

}
