package Adapters.OwnerRepositories;

import Ports.Owners.IOwnerRepository;
import ResultTypes.IDeleteResult;
import Service.Cats.Cat;
import Service.Owners.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class OwnerRepository implements IOwnerRepository {

    private final EntityManagerFactory entityManagerFactory;

    public OwnerRepository(EntityManagerFactory emf) {

        this.entityManagerFactory = emf;
    }

    @Override
    public Owner save(Owner owner) {

        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.persist(owner);

            entityManager.getTransaction().commit();

            entityManager.close();

            return owner;

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
        try {

            var owner = getById(id);

            return deleteByEntity(owner);
        } catch (Exception ex) {
            return new IDeleteResult.Unsuccess(ex.getMessage());
        }

    }

    @Override
    public IDeleteResult deleteByEntity(Owner owner) {
        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();


            Owner ownerFromDb = (Owner) entityManager.createQuery("select o from Owner o" +
                            "                                   WHERE o.id=:ownerId")
                    .setParameter("ownerId", owner.getId()).getSingleResult();


            entityManager.remove(ownerFromDb);


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
    public IDeleteResult deleteAll() {
        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            var allOwner=getAll();

            for (var owner : allOwner) {
                deleteByEntity(owner);
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
    public List<Cat> getAllCatsById(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            var owner = entityManager.find(Owner.class, id);

            entityManager.close();

            return getAllCatsByEntity(owner);

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Cat> getAllCatsByEntity(Owner owner) {
        EntityManager entityManager = null;
        try {

            entityManager = entityManagerFactory.createEntityManager();

            var cats = entityManager.createQuery("select o.cat from Owner o" +
                            "                                   WHERE o.id=:ownerId")
                    .setParameter("ownerId", owner.getId())
                    .getResultList();
            entityManager.close();
            return cats;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.close();
            }

        }
        return new ArrayList<>();
    }


    @Override
    public Owner update(Owner owner) {
        EntityManager entityManager = null;
        try {

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Owner managedOwner = entityManager.merge(owner);

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
    public Owner getById(long id) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            var owner = entityManager.find(Owner.class, id);
            entityManager.close();

            return owner;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.close();
            }
            return null;
        }

    }

    @Override
    public List<Owner> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            var owners = entityManager.createQuery("select o from Owner o").getResultList();
            entityManager.close();
            return owners;

        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        }
        return null;
    }
}
