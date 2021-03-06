package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;



import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class UserDaoImp implements UserDao {


   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      String hql = "SELECT u from User u INNER JOIN FETCH u.car WHERE u.car.model=:m AND u.car.series=:s";
      Query query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      query.setParameter("m", model);
      query.setParameter("s", series);
      return (User) query.getSingleResult();
   }
}
