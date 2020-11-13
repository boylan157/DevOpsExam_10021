package no.kristiania;


import no.kristiania.model.Ninja;
import no.kristiania.repository.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    @Autowired
    CommandLineRunner runner(NinjaRepository ninjaRepository){
        return (args) -> {
            long count = ninjaRepository.count();
//
            if(count == 0){
                try {
                    Ninja ninja = new Ninja();
                    ninja.setName("Kirito");
                    ninja.setKatana("The Elucidator");

                    Ninja ninja2 = new Ninja();
                    ninja2.setName("Asuna");
                    ninja2.setKatana("Lambent Light");

                    Ninja ninja3 = new Ninja();
                    ninja3.setName("Klein");
                    ninja3.setKatana("Excalibur");

                    Ninja ninja4 = new Ninja();
                    ninja4.setName("testNinja");
                    ninja4.setKatana("TestKatana");

                    ninjaRepository.save(ninja);
                    ninjaRepository.save(ninja2);
                    ninjaRepository.save(ninja3);
                    ninjaRepository.save(ninja4);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
