
package Adapters.CatRepositories;

import Ports.Cats.ICatRepository;
import ResultTypes.IDeleteResult;
import Service.Cats.Cat;
import Service.Owners.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class CatRepository implements ICatRepository {

    private final EntityManagerFactory entityManagerFactory;

    public CatRepository(EntityManagerFactory emf) {

        this.entityManagerFactory = emf;
    }

    @Override
    public void AddFriend(long firstFriendId, long secondFriendId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            // if 1 is friend of 2 it mean that 2 friend of 1. so we need two queries to add to DB

            entityManager.getTransaction().begin();

            entityManager.createNativeQuery("insert into cats_friends (cat_id, friend_id) values (:firstId,:secondId)").
                    setParameter("firstId", firstFriendId).setParameter("secondId", secondFriendId).executeUpdate();

            entityManager.createNativeQuery("insert into cats_friends (cat_id, friend_id) values (:firstId,:secondId)").
                    setParameter("firstId", secondFriendId).setParameter("secondId", firstFriendId).executeUpdate();

            entityManager.getTransaction().commit();

            entityManager.close();

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
                entityManager.close();
            }
        }
    }

    @Override
    public Cat save(Cat cat) {

        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.persist(cat);

            entityManager.getTransaction().commit();

            entityManager.close();

            return cat;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
                entityManager.close();
            }
            return null;
        }
    }

    @Override
    public IDeleteResult deleteById(long id) {

        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.remove(entityManager.getReference(Cat.class, id));


            entityManager.getTransaction().commit();

            entityManager.close();

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
                entityManager.close();
            }
            return new IDeleteResult.Unsuccess(ex.getMessage());
        }
        return new IDeleteResult.Success();

    }

    @Override
    public IDeleteResult deleteByEntity(Cat cat) {
        return deleteById(cat.getId());
    }

    @Override
    public IDeleteResult deleteAll() {
        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();


            var allCats = getAll();


            for (var cat : allCats) {
                deleteByEntity(cat);
            }

            entityManager.close();

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
                entityManager.close();
            }
            return new IDeleteResult.Unsuccess(ex.getMessage());
        }
        return new IDeleteResult.Success();
    }

    @Override
    public Cat update(Cat cat) {
        EntityManager entityManager = null;
        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Cat managedOwner = entityManager.merge(cat);

            entityManager.getTransaction().commit();

            entityManager.close();

            return managedOwner;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.close();
            }
            return null;

        }
    }

    @Override
    public Cat getById(long id) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            var cat = entityManager.find(Cat.class, id);
            entityManager.close();

            return cat;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.close();
            }
            return null;
        }

    }

    @Override
    public List<Cat> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            var cats = entityManager.createQuery("select c from Cat c").getResultList();
            entityManager.close();
            return cats;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        }
        return null;
    }
}
