package ru.clevertec.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class CarShowroomServiceImpl {

    public void assignCarToShowroom(Car car, CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            carShowroom.addCar(car);
            session.merge(carShowroom);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(carShowroom);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            CarShowroom carShowroom = session.find(CarShowroom.class, id);
            if (carShowroom != null) {
                session.remove(carShowroom);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(carShowroom);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CarShowroom get(long id) {
        CarShowroom carShowroom = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            carShowroom = session.find(CarShowroom.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carShowroom;
    }

}
