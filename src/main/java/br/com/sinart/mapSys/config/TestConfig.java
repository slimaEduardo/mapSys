package br.com.sinart.mapSys.config;

import br.com.sinart.mapSys.entities.Company;
import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.entities.enums.UserProfile;
import br.com.sinart.mapSys.repositories.CompanyRepository;
import br.com.sinart.mapSys.repositories.DestinyRepository;
import br.com.sinart.mapSys.repositories.TravelMapRepository;
import br.com.sinart.mapSys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig  implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DestinyRepository destinyRepository;
    @Autowired
    private TravelMapRepository travelMapRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

        //Cria os usuários
        User u1 = new User("Maria Brown", "maria.brown", "123", 1);
        User u2 = new User("Alex Green", "alex.green", "123", 2);
        //cria as empresas
        Company c1 = new Company("PROGRESSO");
        Company c2 = new Company("RÁPIDO FEDERAL");
        Company c3 = new Company("RD TURISMO");
        Company c4 = new Company("REAL EXPRESSO");
        //cria os destinos
        Destiny d1 = new Destiny("RIBEIRA DO AMPARO",150.0,2);
        Destiny d2 = new Destiny("RASPADOR",283.0,2);
        Destiny d3 = new Destiny("PALMAS",1700.0,3);
        Destiny d4 = new Destiny("ASSUNÇÃO",2900.0,4);
        //cria os mapas
       // TravelMap m1 = new TravelMap(LocalDate.parse("13/10/2009", formatadorBarra), LocalTime.parse("06:30",formatadorHora),0,1,2,2);
       // TravelMap m2 = new TravelMap(LocalDate.parse("01/10/2009", formatadorBarra), LocalTime.parse("23:00",formatadorHora),12,4,1,4);
      //  TravelMap m3 = new TravelMap(LocalDate.parse("10/10/2009", formatadorBarra),LocalTime.parse("12:00",formatadorHora),24,3,3,3);
      //  TravelMap m4 = new TravelMap(LocalDate.parse("23/10/2009", formatadorBarra), LocalTime.parse("04:30",formatadorHora),100,2,1,1);
        //Adiciona as empresas aos mapas
        //m1.setCompany(companyRepository.getById(m1.getCompanyId()));
       // m2.setCompany(companyRepository.getById(m2.getCompanyId()));
       // m3.setCompany(companyRepository.getById(m3.getCompanyId()));
       // m4.setCompany(companyRepository.getById(m4.getCompanyId()));
        //Adiciona a classe do onibus ao mapa
       // m1.setBusCategory(BusCategory.toEnum(m1.getBusId()));
       // m2.setBusCategory(BusCategory.toEnum(m2.getBusId()));
       // m3.setBusCategory(BusCategory.toEnum(m3.getBusId()));
        //m4.setBusCategory(BusCategory.toEnum(m4.getBusId()));
        //Adiciona o destino ao mapa
     //   m1.setDestiny(destinyRepository.getById(m1.getDestinyId()));
      //  m2.setDestiny(destinyRepository.getById(m2.getDestinyId()));
      //  m3.setDestiny(destinyRepository.getById(m3.getDestinyId()));
      //  m4.setDestiny(destinyRepository.getById(m4.getDestinyId()));

        u1.setUserProfile(UserProfile.toEnum(u1.getProfileId()));
        u2.setUserProfile(UserProfile.toEnum(u2.getProfileId()));
        userRepository.saveAll(Arrays.asList(u1,u2));
        companyRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
        destinyRepository.saveAll(Arrays.asList(d1,d2,d3,d4));
       // travelMapRepository.saveAll(Arrays.asList(m1,m2,m3,m4));

    }
}
