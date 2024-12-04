package ru.clevertec.service;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Car_;
import ru.clevertec.entity.Category;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class CarServiceImpl {

    public void create(Car car) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(car);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Car car = session.find(Car.class, id);
            if (car != null) {
                session.remove(car);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Car car) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(car);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Car get(long id) {
        Car car = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            car = session.find(Car.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> findCarsByFilters(String carMake, String year, Category category, String minPrice, String maxPrice) {
        Session session = HibernateUtil.getSession();
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> car = criteriaQuery.from(Car.class);
//        car.fetch("category", JoinType.LEFT);

        Predicate carMakePredicate = criteriaBuilder.equal(car.get(Car_.CAR_MAKE), carMake);

        Predicate greaterThanPrice = criteriaBuilder.greaterThan(car.get(Car_.PRICE), minPrice);
        Predicate lessThanPrice = criteriaBuilder.lessThan(car.get(Car_.PRICE), maxPrice);

        Predicate yearOfProduction = criteriaBuilder.equal(car.get(Car_.YEAR_OF_PRODUCTION), Integer.valueOf(year));
        // Predicate categoryPredicate = criteriaBuilder.equal(car.get("category"), category.getName());
        criteriaQuery.select(car).where(criteriaBuilder.or(carMakePredicate, greaterThanPrice, lessThanPrice, yearOfProduction));

        Query<Car> query = session.createQuery(criteriaQuery);
        List<Car> carsByCriteria = query.getResultList();

        return carsByCriteria;
    }

    public List<Car> listCarsByPrice(String order) {
        Session session = HibernateUtil.getSession();
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> car = criteriaQuery.from(Car.class);
        if (order.equals("asc")) {
            criteriaQuery.orderBy(
                    criteriaBuilder.asc(car.get(Car_.PRICE)));
        }
        if (order.equals("desc")) {
            criteriaQuery.orderBy(
                    criteriaBuilder.desc(car.get(Car_.PRICE)));
        }
        Query<Car> query = session.createQuery(criteriaQuery);
        List<Car> carsByCriteria = query.getResultList();
        return carsByCriteria;
    }

    public List<Car> pagination(int first, int numberPerPage) {
        Session session = HibernateUtil.getSession();
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> car = criteriaQuery.from(Car.class);
        SelectionQuery<Car> selectionQuery = session.createQuery(criteriaQuery);
        selectionQuery.setFirstResult(first);
        selectionQuery.setMaxResults(first + numberPerPage);
        List<Car> carsByCriteria = selectionQuery.list();
        return carsByCriteria;
    }

    public List<Car> getAllCarsWithShowrooms() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Car> query = session.createQuery(
                    "SELECT c FROM Car c JOIN FETCH c.carShowroom", Car.class);
            return query.list();
        }
    }

    public List<Car> getCarsWithShowrooms() {
        try (Session session = HibernateUtil.getSession();
             EntityManager entityManager = session.getEntityManagerFactory().createEntityManager()) {
            EntityGraph<?> entityGraph = entityManager
                    .getEntityGraph("Car.withShowroom");

            return session.createQuery("FROM Car", Car.class)
                    .setHint("javax.persistence.fetchgraph", entityGraph)
                    .getResultList();
        }
    }

}
