package ru.titov;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.cfg.Configuration;
import ru.titov.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainLesson6 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Contact mobile = new Contact(ContactType.MOBILE_PHONE, "123");
//        Contact email = new Contact(ContactType.HOME_EMAIL, "email@mail.com");
//        List<Contact> firstUserContactList = Arrays.asList(mobile, email);
//
//        Contact mobile2 = new Contact(ContactType.MOBILE_PHONE, "456");
//        Contact email2 = new Contact(ContactType.HOME_EMAIL, "email3222@mail.com");
//        List<Contact> secondUserContactList = Arrays.asList(mobile2, email2);
//
//        User user = new User("User1", firstUserContactList, "pass1");
//        User user2 = new User("User2", secondUserContactList, "pass2");
////        User user3 = new User("User3", firstUserContactList, "pass2");
//        Passport passport = new Passport("1234", "123", "RUVD", Instant.now());
//        Passport passport2 = new Passport("1234", "123", "RUVD", Instant.now());
//        user.setPassport(passport);
//        user2.setPassport(passport2);
//
//
//        firstUserContactList.forEach(contact -> contact.setUser(user));
//        secondUserContactList.forEach(contact -> contact.setUser(user2));
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(user);
//        entityManager.persist(user2);
//        entityManager.getTransaction().commit();


////        user2.setId(2L);
////        entityManager.merge(user2);
//
////        List<User> users = entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
//
        List<User> users = entityManager.createQuery("""
                        select u from User u
                        where (u.username like :usernameFilter) 
                         and u.password like :passwordFilter
                        """, User.class)
                .setParameter("usernameFilter", "%U%")
                .setParameter("passwordFilter", "%pass%")
                .getResultList();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Join<Object, Object> contacts = root.join("contacts");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(root.get("username"), "%U%"));
        predicates.add(cb.like(root.get("password"), "%pass%"));


        List<User> resultList = entityManager.createQuery(query
                        .select(root)
                        .where(predicates.toArray(new Predicate[0])))
                .getResultList();


        System.out.println();
//
//        List<Contact> contacts = users.get(0).getContacts();
//        for (User user1 : users) {
//            user1.getContacts().forEach(System.out::println);
//        }


//        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, 2L);
//        user.getContacts().remove(0);
//        entityManager.merge(user);
//
//        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


    }
}
