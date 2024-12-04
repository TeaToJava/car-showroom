package ru.clevertec.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Category;
import ru.clevertec.util.HibernateUtil;

public class CategoryServiceImpl {
    public void create(Category category) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = session.find(Category.class, id);
            if (category != null) {
                session.remove(category);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Category get(long id) {
        Category category = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            category = session.find(Category.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
