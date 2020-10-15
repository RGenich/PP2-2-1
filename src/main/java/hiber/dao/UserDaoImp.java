package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUser(int series, String model) {
       User user = null;
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "from User as usr where usr.car.series = :series and usr.car.model = :model"
        );
        query.setParameter("series", series);
        query.setParameter("model", model);
        try {
           user = query.getSingleResult();
        }
        catch (NoResultException e) {
           System.err.println("User with car " + model + ", series: " + series +" not found");
        }
        return user;
    }

}
