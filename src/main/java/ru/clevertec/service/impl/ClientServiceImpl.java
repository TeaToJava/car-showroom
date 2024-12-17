package ru.clevertec.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.exception.ServiceException;
import ru.clevertec.util.HibernateUtil;

public class ClientServiceImpl implements ClientService {

    public void buyCar(Client client, Car car) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            client.addCar(car);
            car.setCarShowroom(null);
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void save(Client client) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.find(Client.class, id);
            if (client != null) {
                session.remove(client);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Client client) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Client getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Client client = session.find(Client.class, id);
            return client;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
