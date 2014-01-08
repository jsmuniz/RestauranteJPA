package dao;

import entities.Mesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class MesaDao {

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
     * @param mesa
     *
     */
    public void salvar(Mesa mesa) {
        EntityManager manager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            System.out.println("Salvando a Mesa.");
            // Verifica se a Contato ainda não está salva no banco de dados.
            if (mesa.getId() == null) {
                // Salva os dados da Contato.
                manager.persist(mesa);
            } else {
                // Atualiza os dados da pessoa.
                mesa = manager.merge(mesa);
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
    public Mesa pesquisarPorId(Integer id) {
        EntityManager manager = getEntityManager();
        Mesa mesa = null;
        try {
            // Consulta uma pessoa pelo seu ID.
            mesa = manager.find(Mesa.class, id);
        } finally {
            manager.close();
        }
        return mesa;
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

            Mesa mesa = manager.find(Mesa.class, id);
            System.out.println("Excluindo os dados de: " + mesa.getId());
            // Remove a pessoa da base de dados.
            manager.remove(mesa);
            // Finaliza a transação.
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    /**
     * Método que atualiza um Contato do banco de dados.
     *
     * @param mesa
     * @return Contato
     */
    public Mesa atualizar(Mesa mesa) {
        EntityManager manager = getEntityManager();
        Mesa mesa1;
        try {
            // Inicia uma transação com o banco de dados.
            manager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.

            mesa1 = manager.find(Mesa.class, mesa.getId());

            manager.merge(mesa);

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
        return mesa1;
    }

    /**
     * Método que busca todos do banco de dados.
     *
     * @return List<Contato>
     */
    @SuppressWarnings("unchecked")
    public List<Mesa> todos() {
        EntityManager manager = getEntityManager();
        List<Mesa> mesas = new ArrayList<>();
        try {
            mesas = (List<Mesa>) manager.createQuery(
                    "Select a from Mesa a").getResultList();
        } finally {
            manager.close();
        }
        return mesas;
    }

    /**
     * Método pesq que busca todos do banco de dados usa-se uma query.
     *
     * @param query
     * @return List<Contato>
     */
    public Mesa pesq(String query) {
        EntityManager manager = getEntityManager();
        Mesa mesa = new Mesa();
        try {
            Query q = manager.createQuery(query);
            mesa = (Mesa) q.getSingleResult();
        } finally {
            manager.close();
        }
        return mesa;
    }

    @SuppressWarnings("unchecked")
    public List<Mesa> listPesq(String query) {
        EntityManager manager = getEntityManager();
        List<Mesa> mesas= new ArrayList<>();
        try {
            Query q = manager.createQuery(query);
            mesas = (List<Mesa>) q.getResultList();
        } finally {
            getEntityManager().close();
        }
        return mesas;
    }

    /**
     * Método pesqParam busca do banco de dados passando uma query e uma map de
     * parametros.
     *
     * @param query
     * @param params <String, @ * return List<Contato> @return
     * @return
     */
    public Mesa pesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        Mesa mesa = new Mesa();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                mesa = (Mesa) q.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return mesa;

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
    public List<Mesa> listPesqParam(String query, Map<String, Object> params) {
        EntityManager manager = getEntityManager();
        List<Mesa> mesas = new ArrayList<>();
        try {

            Query q = manager.createQuery(query);
            for (String chave : params.keySet()) {
                q.setParameter(chave, params.get(chave));

            }
            try {
                mesas = (List<Mesa>) q.getResultList();
            } catch (NoResultException nre) {
                return null;
            }

        } finally {
            manager.close();
        }
        return mesas;

    }

}
