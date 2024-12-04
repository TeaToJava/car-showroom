package ru.clevertec.service;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.entity.Review_;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class ReviewServiceImpl {

    public void addReview(Client client, Car car, String text, int rating) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Review review = Review.builder().text(text).rating(rating).car(car).build();
            client.addReview(review);
            session.merge(client);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Review> searchReviews(String keyword){
        try (Session session = HibernateUtil.getSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Review> criteriaQuery = criteriaBuilder.createQuery(Review.class);
            Root<Review> review = criteriaQuery.from(Review.class);
            criteriaQuery.select(review).where(criteriaBuilder.like(review.get(Review_.TEXT),"%" + keyword + "%"));
            Query<Review> query = session.createQuery(criteriaQuery);
            List<Review> reviews =  query.getResultList();
            return reviews;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(Review review) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(review);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Review review = session.find(Review.class, id);
            if (review != null) {
                session.remove(review);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Review review) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(review);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Review get(long id) {
        Review review = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            review = session.find(Review.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return review;
    }
}
