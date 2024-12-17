package ru.clevertec.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.service.CarShowroomService;
import ru.clevertec.service.exception.ServiceException;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class CarShowroomServiceImpl implements CarShowroomService {

    public void assignCarToShowroom(Car car, CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            carShowroom.addCar(car);
            session.merge(carShowroom);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void save(CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(carShowroom);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            CarShowroom carShowroom = session.find(CarShowroom.class, id);
            if (carShowroom != null) {
                session.remove(carShowroom);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Long id, CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(carShowroom);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public CarShowroom getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            CarShowroom carShowroom = session.find(CarShowroom.class, id);
            return carShowroom;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
