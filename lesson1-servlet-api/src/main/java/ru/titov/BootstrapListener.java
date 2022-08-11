package ru.titov;

import ru.titov.persist.User;
import ru.titov.persist.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserRepository userRepository = new UserRepository();
        userRepository.insert(new User("Vladimir"));
        userRepository.insert(new User("Anastasia"));
        userRepository.insert(new User("Evgeniy"));
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
