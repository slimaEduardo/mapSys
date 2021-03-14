package br.com.sinart.mapSys.config;

import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.entities.enums.UserProfile;
import br.com.sinart.mapSys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig  implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("Maria Brown", "maria.brown", "123", 1);
        User u2 = new User("Alex Green", "alex.green", "123", 2);
        u1.setUserProfile(UserProfile.toEnum(u1.getProfileId()));
        u2.setUserProfile(UserProfile.toEnum(u2.getProfileId()));
        userRepository.saveAll(Arrays.asList(u1,u2));
    }
}
