package ru.clevertec.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Category;
import ru.clevertec.service.CategoryService;
import ru.clevertec.service.exception.ServiceException;
import ru.clevertec.util.HibernateUtil;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public void save(Category category) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = session.find(Category.class, id);
            if (category != null) {
                session.remove(category);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Category category) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(category);
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Category getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Category category = session.find(Category.class, id);
            return category;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
