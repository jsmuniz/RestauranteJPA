package dao;

import entities.PedidoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class PedidoItemDao {

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
     * @param pedidoitem
     *
     */
    public void salvar(PedidoItem pedidoitem) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            System.out.println("Salvando o Pedido-Item...");
            // Verifica se a Contato ainda não está salva no banco de dados.
            if (pedidoitem.getPedidoItemPK() == null) {
                // Salva os dados da Contato.
                manager.persist(pedidoitem);
            } else {
                // Atualiza os dados da pessoa.
                pedidoitem = manager.merge(pedidoitem);
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
    public PedidoItem pesquisarPorId(Integer id) {
        EntityManager manager = getEntityManager();
        PedidoItem pedidoitem = null;
        try {
            // Consulta uma pessoa pelo seu ID.
            pedidoitem = manager.find(PedidoItem.class, id);
        } finally {
            manager.close();
        }
        return pedidoitem;
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

            PedidoItem pedidoitem = manager.find(PedidoItem.class, id);
            System.out.println("Excluindo os dados de: " + pedidoitem.getPedidoItemPK().getIdItem());
            // Remove a pessoa da base de dados.
            manager.remove(pedidoitem);
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
    public void atualizar(PedidoItem pedido) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
          
            manager.merge(pedido);

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }

    }

    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Pedido>
     */
    @SuppressWarnings("unchecked")
    public List<PedidoItem> todos() {
        EntityManager manager = getEntityManager();
        List<PedidoItem> pedidos = new ArrayList<>();
        try {
            pedidos = (List<PedidoItem>) manager.createQuery(
                    "Select a from PedidoItem a").getResultList();
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
    public PedidoItem pesq(String query) {
        EntityManager manager = getEntityManager();
        PedidoItem pedido = new PedidoItem();
        try {
            Query q = manager.createQuery(query);
            pedido = (PedidoItem) q.getSingleResult();
        } finally {
            manager.close();
        }
        return pedido;
    }

    @SuppressWarnings("unchecked")
    public List<PedidoItem> listPesq(String query) {
        EntityManager manager = getEntityManager();
        List<PedidoItem> pedidos= new ArrayList<>();
        try {
            Query q = manager.createQuery(query);
            pedidos = (List<PedidoItem>) q.getResultList();
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
    public PedidoItem pesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        PedidoItem pedido = new PedidoItem();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                pedido = (PedidoItem) q.getSingleResult();
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
    public List<PedidoItem> listPesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        List<PedidoItem> pedidos = new ArrayList<>();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                pedidos = (List<PedidoItem>) q.getResultList();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return pedidos;

    }

}
