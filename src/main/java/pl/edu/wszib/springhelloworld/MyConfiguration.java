package pl.edu.wszib.springhelloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.edu.wszib.springhelloworld.model.KartaDoGlosowania;
import pl.edu.wszib.springhelloworld.model.Urna;
import pl.edu.wszib.springhelloworld.model.Wyborca;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class MyConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public KartaDoGlosowania kartaDoGlosowania() {
        return new KartaDoGlosowania();
    }

    @Bean
    public List<Wyborca> wyborcy() {
        return IntStream.range(0, 1200)
                .mapToObj(i -> new Wyborca(kartaDoGlosowania()))
                .collect(Collectors.toList());
    }

    @Bean
    public Urna urna(List<Wyborca> wyborcy) {
        Urna urna = new Urna();
        wyborcy.forEach(wyborca -> wyborca.oddajGlos(urna));
        urna.zlicz();
        return urna;
    }

}
