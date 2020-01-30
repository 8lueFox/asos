package com.io.usos.config;

import com.io.usos.models.*;
import com.io.usos.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoriesInitializer {
    @Autowired
    AnkietaRepository ankietaRepository;
    @Autowired
    OcenaRepository ocenaRepository;
    @Autowired
    PracownikRepository pracownikRepository;
    @Autowired
    PrzedmiotRepository przedmiotRepository;
    @Autowired
    RokRepository rokRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StypendiumRepository stypendiumRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AnkietaOdpowiedzRepository ankietaOdpowiedzRepository;
    @Autowired
    AnkietaPytanieRepository ankietaPytanieRepository;
    @Autowired
    OdpowiedzRepository odpowiedzRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {
        return () -> {
            if (roleRepository.findAll().isEmpty()) {
                Role role = new Role(Role.Types.Pracownik);
                Role role2 = new Role(Role.Types.Pracownik_Dziekanatu);
                Role role3 = new Role(Role.Types.Student);

                roleRepository.save(role);
                roleRepository.save(role2);
                roleRepository.save(role3);

                Student student1 = new Student("kacperj", passwordEncoder.encode("kacper"), "kacper", "Kacper", "Jedrzejewski", role3, "97021411111");
                Student student2 = new Student("piotrj", passwordEncoder.encode("piotr"), "piotr", "Piotr", "Jadczuk", role3, "97072522222");
                Student student3 = new Student("kingam", passwordEncoder.encode("kinga"), "kinga", "Kinga", "Markowicz", role3, "98112133333");

                studentRepository.save(student1);
                studentRepository.save(student2);
                studentRepository.save(student3);

                Przedmiot przedmiot1 = new Przedmiot("Inzynieria oprogramowania");
                Przedmiot przedmiot2 = new Przedmiot("Platformy programowania");

                przedmiot2.addStudent(student1);
                przedmiot2.addStudent(student2);
                przedmiot2.addStudent(student3);

                przedmiot1.addStudent(student1);
                przedmiot1.addStudent(student2);
                przedmiot1.addStudent(student3);

                przedmiotRepository.save(przedmiot1);
                przedmiotRepository.save(przedmiot2);

                Stypendium stypendium1 = new Stypendium(student1, 4.5f, "");
                Stypendium stypendium2 = new Stypendium(student2, 4.45f, "Dej piniondz");

                stypendiumRepository.save(stypendium1);
                stypendiumRepository.save(stypendium2);

                Rok rok1 = new Rok("Informatyka", Instant.now(), ZonedDateTime.now().plusYears(3).plusMonths(6).toInstant());
                Rok rok2 = new Rok("Matematyka", ZonedDateTime.now().minusYears(1).toInstant(), ZonedDateTime.now().plusYears(2).toInstant());

                rokRepository.save(rok1);
                rokRepository.save(rok2);

                Pracownik pracownik1 = new Pracownik("marek", passwordEncoder.encode("marek"), "marek", "Marek", "Bond", role, false);
                Pracownik pracownik2 = new Pracownik("greg", passwordEncoder.encode("greg"), "greg", "Grzegorz", "Pond", role, false);
                Pracownik pracownik3 = new Pracownik("bets", passwordEncoder.encode("bets"), "bets", "Beata", "Ciok", role2, true);

                pracownik2.dodajPrzedmiot(przedmiot2);
                pracownik1.dodajPrzedmiot(przedmiot1);

                pracownikRepository.save(pracownik1);
                pracownikRepository.save(pracownik2);
                pracownikRepository.save(pracownik3);

                Ocena ocena1 = new Ocena(4.5f, student1, przedmiot2);
                Ocena ocena2 = new Ocena(5f, student1, przedmiot1);
                Ocena ocena3 = new Ocena(5f, student2, przedmiot1);
                Ocena ocena4 = new Ocena(5f, student3, przedmiot1);

                ocenaRepository.save(ocena1);
                ocenaRepository.save(ocena2);
                ocenaRepository.save(ocena3);
                ocenaRepository.save(ocena4);

                AnkietaPytanie ankietaPytanie1 = new AnkietaPytanie("pytanie 1");
                AnkietaPytanie ankietaPytanie2 = new AnkietaPytanie("pytanie 2");
                AnkietaPytanie ankietaPytanie3 = new AnkietaPytanie("pytanie 3");
                AnkietaPytanie ankietaPytanie4 = new AnkietaPytanie("pytanie 4");
                AnkietaPytanie ankietaPytanie5 = new AnkietaPytanie("pytanie 5");
                AnkietaPytanie ankietaPytanie6 = new AnkietaPytanie("pytanie 6");
                AnkietaPytanie ankietaPytanie7 = new AnkietaPytanie("pytanie 7");
                AnkietaPytanie ankietaPytanie8 = new AnkietaPytanie("pytanie 8");
                AnkietaPytanie ankietaPytanie9 = new AnkietaPytanie("pytanie 9");
                AnkietaPytanie ankietaPytanie10 = new AnkietaPytanie("pytanie 10");

                ankietaPytanieRepository.save(ankietaPytanie1);
                ankietaPytanieRepository.save(ankietaPytanie2);
                ankietaPytanieRepository.save(ankietaPytanie3);
                ankietaPytanieRepository.save(ankietaPytanie4);
                ankietaPytanieRepository.save(ankietaPytanie5);
                ankietaPytanieRepository.save(ankietaPytanie6);
                ankietaPytanieRepository.save(ankietaPytanie7);
                ankietaPytanieRepository.save(ankietaPytanie8);
                ankietaPytanieRepository.save(ankietaPytanie9);
                ankietaPytanieRepository.save(ankietaPytanie10);

                List<AnkietaPytanie> pytania1 = new ArrayList<>();
                List<AnkietaPytanie> pytania2 = new ArrayList<>();

                pytania1.add(ankietaPytanie1);
                pytania1.add(ankietaPytanie2);
                pytania1.add(ankietaPytanie3);
                pytania1.add(ankietaPytanie4);
                pytania1.add(ankietaPytanie5);
                pytania1.add(ankietaPytanie6);
                pytania1.add(ankietaPytanie7);
                pytania1.add(ankietaPytanie8);
                pytania1.add(ankietaPytanie9);
                pytania1.add(ankietaPytanie10);

                pytania2.add(ankietaPytanie2);
                pytania2.add(ankietaPytanie4);
                pytania2.add(ankietaPytanie6);
                pytania2.add(ankietaPytanie8);
                pytania2.add(ankietaPytanie10);

                Ankieta ankieta1 = new Ankieta(pracownik1, przedmiot1, pytania1);
                Ankieta ankieta2 = new Ankieta(pracownik2, przedmiot2, pytania2);

                ankietaRepository.save(ankieta1);
                ankietaRepository.save(ankieta2);

                Odpowiedz odpowiedz0 = new Odpowiedz(0);
                Odpowiedz odpowiedz1 = new Odpowiedz(1);
                Odpowiedz odpowiedz2 = new Odpowiedz(2);
                Odpowiedz odpowiedz3 = new Odpowiedz(3);
                Odpowiedz odpowiedz4 = new Odpowiedz(4);
                Odpowiedz odpowiedz5 = new Odpowiedz(5);
                Odpowiedz odpowiedz6 = new Odpowiedz(6);
                Odpowiedz odpowiedz7 = new Odpowiedz(7);
                Odpowiedz odpowiedz8 = new Odpowiedz(8);
                Odpowiedz odpowiedz9 = new Odpowiedz(9);
                Odpowiedz odpowiedz10 = new Odpowiedz(10);

                odpowiedzRepository.save(odpowiedz0);
                odpowiedzRepository.save(odpowiedz1);
                odpowiedzRepository.save(odpowiedz2);
                odpowiedzRepository.save(odpowiedz3);
                odpowiedzRepository.save(odpowiedz4);
                odpowiedzRepository.save(odpowiedz5);
                odpowiedzRepository.save(odpowiedz6);
                odpowiedzRepository.save(odpowiedz7);
                odpowiedzRepository.save(odpowiedz8);
                odpowiedzRepository.save(odpowiedz9);
                odpowiedzRepository.save(odpowiedz10);

                List<Odpowiedz> odpowiedzi1 = new ArrayList<>();
                List<Odpowiedz> odpowiedzi2 = new ArrayList<>();
                List<Odpowiedz> odpowiedzi3 = new ArrayList<>();
                List<Odpowiedz> odpowiedzi4 = new ArrayList<>();

                odpowiedzi1.add(odpowiedz1);
                odpowiedzi1.add(odpowiedz2);
                odpowiedzi1.add(odpowiedz3);
                odpowiedzi1.add(odpowiedz4);
                odpowiedzi1.add(odpowiedz5);
                odpowiedzi1.add(odpowiedz6);
                odpowiedzi1.add(odpowiedz7);
                odpowiedzi1.add(odpowiedz8);
                odpowiedzi1.add(odpowiedz9);
                odpowiedzi1.add(odpowiedz10);


                odpowiedzi2.add(odpowiedz2);
                odpowiedzi2.add(odpowiedz1);
                odpowiedzi2.add(odpowiedz2);
                odpowiedzi2.add(odpowiedz1);
                odpowiedzi2.add(odpowiedz2);
                odpowiedzi2.add(odpowiedz1);
                odpowiedzi2.add(odpowiedz2);
                odpowiedzi2.add(odpowiedz1);
                odpowiedzi2.add(odpowiedz2);
                odpowiedzi2.add(odpowiedz1);


                odpowiedzi3.add(odpowiedz1);
                odpowiedzi3.add(odpowiedz2);
                odpowiedzi3.add(odpowiedz3);
                odpowiedzi3.add(odpowiedz4);
                odpowiedzi3.add(odpowiedz5);


                odpowiedzi4.add(odpowiedz6);
                odpowiedzi4.add(odpowiedz7);
                odpowiedzi4.add(odpowiedz8);
                odpowiedzi4.add(odpowiedz9);
                odpowiedzi4.add(odpowiedz10);



                AnkietaOdpowiedz ankietaOdpowiedz1 = new AnkietaOdpowiedz(ankieta1, odpowiedzi1);
                AnkietaOdpowiedz ankietaOdpowiedz2 = new AnkietaOdpowiedz(ankieta1, odpowiedzi2);
                AnkietaOdpowiedz ankietaOdpowiedz3 = new AnkietaOdpowiedz(ankieta2, odpowiedzi3);
                AnkietaOdpowiedz ankietaOdpowiedz4 = new AnkietaOdpowiedz(ankieta2, odpowiedzi4);

                ankietaOdpowiedzRepository.save(ankietaOdpowiedz1);
                ankietaOdpowiedzRepository.save(ankietaOdpowiedz2);
                ankietaOdpowiedzRepository.save(ankietaOdpowiedz3);
                ankietaOdpowiedzRepository.save(ankietaOdpowiedz4);

            }
        };
    }
}
