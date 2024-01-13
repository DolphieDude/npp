package ua.kpi.npp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UnitOfWorkImpl implements UnitOfWork {
    private final EntityManagerFactory entityManagerFactory = null;
    @Override
    public void begin() {
        EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory);
        if (emHolder == null || emHolder.isSynchronizedWithTransaction()) {
            EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
            TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(entityManager));
        }
    }

    @Override
    public void save() {
        EntityTransaction transaction = getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
            TransactionSynchronizationManager.unbindResource(entityManagerFactory);
        }
    }

    @Override
    public void dispose() {
        EntityTransaction transaction = getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
            TransactionSynchronizationManager.unbindResource(entityManagerFactory);
        }
    }

    @Override
    public void registerNew(Object entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void registerDirty(Object entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void registerRemoved(Object entity) {
        getEntityManager().remove(entity);
    }

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
    }

    private EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }
}
