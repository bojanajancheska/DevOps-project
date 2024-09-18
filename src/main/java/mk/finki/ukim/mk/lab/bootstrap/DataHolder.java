package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class DataHolder {
    ProductionRepository productionRepository;
    MovieRepository movieRepository;
    UserRepository userRepository;
    ShoppingCartRepository shoppingCartRepository;

    public static List<Movie> movies = null;
    public static List<Production> productions = null;

    public DataHolder(ProductionRepository productionRepository, MovieRepository movieRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.productionRepository = productionRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @PostConstruct
    public void init() {
        movies = new ArrayList<>();
        productions = new ArrayList<>();

        productions.add(Production.builder().name("20th Century Fox").country("USA").address("str. 1").build());
        productions.add(Production.builder().name("Paramount Pictures").country("USA").address("str. 2").build());
        productions.add(Production.builder().name("Warner Bros").country("USA").address("str. 3").build());
        productions.add(Production.builder().name("DreamWorks Pictures").country("USA").address("str. 4").build());
        productions.add(Production.builder().name("Lionsgate").country("USA").address("str. 5").build());

        movies.add(Movie.builder().title("Inception").summary("mind-bending thriller").rating(9.3)
                .production(productions.get((int) (Math.random() * 5))).build());

        movies.add(Movie.builder().title("The Shawshank Redemption").summary("drama and redemption").rating(9.2)
                .production(productions.get((int) (Math.random() * 5))).build());

        movies.add(Movie.builder().title("Star Wars: Episode IV").summary("epic space opera").rating(8.7)
                .production(productions.get((int) (Math.random() * 5))).build());

        movies.add(Movie.builder().title("The Godfather").summary("mafia classic").rating(9.1)
                .production(productions.get((int) (Math.random() * 5))).build());

        movies.add(Movie.builder().title("Jurassic Park").summary("dinosaur adventure").rating(8.1)
                .production(productions.get((int) (Math.random() * 5))).build());

        movies.add(Movie.builder().title("The Matrix").summary("sci-fi action").rating(8.7)
                .production(productions.get((int) (Math.random() * 5))).build());

        productionRepository.saveAll(productions);
        movieRepository.saveAll(movies);

        ShoppingCart cart = ShoppingCart.builder().ticketOrders(new ArrayList<>()).build();
        User bojana = User.builder().username("bojana").password("bojana")
                .carts(new ArrayList<>()).build();
        bojana.getCarts().add(cart);
        cart.setUser(bojana);

        shoppingCartRepository.save(cart);
        userRepository.save(bojana);
    }
}