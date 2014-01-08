package dao;

import entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ItemDao {

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
     * @param item
     *
     */
    public void salvar(Item item) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            System.out.println("Salvando o Item.");
            // Verifica se a Contato ainda não está salva no banco de dados.
            if (item.getId() == null) {
                // Salva os dados da Contato.
                manager.persist(item);
            } else {
                // Atualiza os dados da pessoa.
                item = manager.merge(item);
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
    public Item pesquisarPorId(Integer id) {
        EntityManager manager = getEntityManager();
        Item item = null;
        try {
            // Consulta uma pessoa pelo seu ID.
            item = manager.find(Item.class, id);
        } finally {
            manager.close();
        }
        return item;
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

            Item item = manager.find(Item.class, id);
            System.out.println("Excluindo os dados de: " + item.getNome());
            // Remove a pessoa da base de dados.
            manager.remove(item);
            // Finaliza a transação.
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    /**
     * Método que atualiza um Contato do banco de dados.
     *
     * @param item
     * @return Item
     */
    public Item atualizar(Item item) {
        EntityManager manager = getEntityManager();
        Item item1;
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.

            item1 = manager.find(Item.class, item.getId());

            manager.merge(item);

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
        return item1;
    }

    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Contato>
     */
    @SuppressWarnings("unchecked")
    public List<Item> todos() {
        EntityManager manager = getEntityManager();
        List<Item> itens = new ArrayList<>();
        try {
            itens = (List<Item>) manager.createQuery(
                    "Select a from Item a").getResultList();
        } finally {
            manager.close();
        }
        return itens;
    }

    /**
     * Método pesq que busca todos do banco de dados usa-se uma query.
     *
     * @param query
     * @return List<Contato>
     */
    public Item pesq(String query) {
        EntityManager manager = getEntityManager();
        Item item = new Item();
        try {
            Query q = manager.createQuery(query);
            item = (Item) q.getSingleResult();
        } finally {
            manager.close();
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    public List<Item> listPesq(String query) {
        EntityManager manager = getEntityManager();
        List<Item> itens = new ArrayList<>();
        try {
            Query q = manager.createQuery(query);
            itens = (List<Item>) q.getResultList();
        } finally {
            getEntityManager().close();
        }
        return itens;
    }

    /**
     * Método pesqParam busca do banco de dados passando uma query e uma map de
     * parametros.
     *
     * @param query
     * @param params <String, @ * return List<Contato> @return
     * @return
     */
    public Item pesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        Item item = new Item();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                item = (Item) q.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return item;

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
    public List<Item> listPesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        List<Item> itens = new ArrayList<>();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                itens = (List<Item>) q.getResultList();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return itens;

    }

}
