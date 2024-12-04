package ru.clevertec.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.util.HibernateUtil;

public class ClientServiceImpl {

    public void buyCar(Client client, Car car) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            client.addCar(car);
            car.setCarShowroom(null);
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Client client) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.find(Client.class, id);
            if (client != null) {
                session.remove(client);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Client client) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client get(long id) {
        Client client = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.find(Client.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

}
