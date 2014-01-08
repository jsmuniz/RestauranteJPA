package dao;

import entities.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class PedidoDao {

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
     * @param pedido
     *
     */
    public void salvar(Pedido pedido) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            System.out.println("Salvando o Pedido...");
            // Verifica se a Contato ainda não está salva no banco de dados.
            if (pedido.getId() == null) {
                // Salva os dados da Contato.
                manager.persist(pedido);
            } else {
                // Atualiza os dados da pessoa.
                pedido = manager.merge(pedido);
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
    public Pedido pesquisarPorId(Integer id) {
        EntityManager manager = getEntityManager();
        Pedido pedido = null;
        try {
            // Consulta uma pessoa pelo seu ID.
            pedido = manager.find(Pedido.class, id);
        } finally {
            manager.close();
        }
        return pedido;
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

            Pedido pedido = manager.find(Pedido.class, id);
            System.out.println("Excluindo os dados de: " + pedido.getId());
            // Remove a pessoa da base de dados.
            manager.remove(pedido);
            // Finaliza a transação.
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    /**
     * Método que atualiza um Contato do banco de dados.
     *
     * @param pedido
     * @return Contato
     */
    public Pedido atualizar(Pedido pedido) {
        EntityManager manager = getEntityManager();
        Pedido pedido1;
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.

            pedido1 = manager.find(Pedido.class, pedido.getId());

            manager.merge(pedido);

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
        return pedido1;
    }

    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Pedido>
     */
    @SuppressWarnings("unchecked")
    public List<Pedido> todos() {
        EntityManager manager = getEntityManager();
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = (List<Pedido>) manager.createQuery(
                    "Select a from Pedido a").getResultList();
        } finally {
            manager.close();
        }
        return pedidos;
    }

    /**
     * Método pesq que busca todos do banco de dados usa-se uma query.
     *
     * @param query
     * @return List<Contato>
     */
    public Pedido pesq(String query) {
        EntityManager manager = getEntityManager();
        Pedido pedido = new Pedido();
        try {
            Query q = manager.createQuery(query);
            pedido = (Pedido) q.getSingleResult();
        } finally {
            manager.close();
        }
        return pedido;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> listPesq(String query) {
        EntityManager manager = getEntityManager();
        List<Pedido> pedidos= new ArrayList<>();
        try {
            Query q = manager.createQuery(query);
            pedidos = (List<Pedido>) q.getResultList();
        } finally {
            getEntityManager().close();
        }
        return pedidos;
    }

    /**
     * Método pesqParam busca do banco de dados passando uma query e uma map de
     * parametros.
     *
     * @param query
     * @param params <String, @ * return List<Contato> @return
     * @return
     */
    public Pedido pesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        Pedido pedido = new Pedido();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                pedido = (Pedido) q.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return pedido;

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
    public List<Pedido> listPesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        List<Pedido> pedidos = new ArrayList<>();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                pedidos = (List<Pedido>) q.getResultList();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return pedidos;

    }

}
