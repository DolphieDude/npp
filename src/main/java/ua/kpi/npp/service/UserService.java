package ua.kpi.npp.service;

import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.NppRepository;
import ua.kpi.npp.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    NppRepository nppRepository;

    public void applyForAJob(User user, Npp npp) {
        if (!user.getRole().equals(npp.getRole())) {
            throw new AssertionError("Roles mismatch");
        }
        if (npp.getUsers().contains(user)) {
            System.out.println("User is already in this npp");
        }

        npp.addUser(user);
        user.setNpp(npp);

        userRepository.save(user);
        nppRepository.save(npp);
    }
}
