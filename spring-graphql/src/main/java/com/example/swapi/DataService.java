package com.example.swapi;

import com.example.swapi.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.swapi.entity.Episode.*;
import static java.util.Arrays.asList;

/**
 * @author Gary Cheng
 */
@Component
@Slf4j
public class DataService {
    private Map<String, Human> humanData;
    private Map<String, Droid> droidData;
    private Map<String, Starship> starshipData;
    private Map<String, MovieCharacter> characterData;
    private Map<Episode, List<Review>> reviewData;

    /**
     * Find character by id
     *
     * @param id character id
     * @return
     */
    public MovieCharacter getCharacter(String id) {
        log.debug("getCharacter, id={}", id);
        return characterData.get(id);
    }

    /**
     * Find human by id
     *
     * @param id human id
     * @return
     */
    public Human getHuman(String id) {
        log.debug("getHuman, id={}", id);
        return humanData.get(id);
    }

    /**
     * Find Droid by id
     *
     * @param id Droid id
     * @return
     */
    public Droid getDroid(String id) {
        log.debug("getDroid, id={}", id);
        return droidData.get(id);
    }

    /**
     * Find Starship by id
     *
     * @param id starship id
     * @return
     */
    public Starship getStarship(String id) {
        log.debug("getStarship, id={}", id);
        return starshipData.get(id);
    }

    /**
     * Return Hero character by episode
     *
     * @param episode the episode
     * @return
     */
    public MovieCharacter getHero(Episode episode) {
        log.debug("getHero, episode={}", episode);
        if (episode == EMPIRE) {
            return characterData.get("1000");
        } else {
            return characterData.get("2001");
        }
    }

    /**
     * Find friends by character id
     *
     * @param id
     * @return
     */
    public List<MovieCharacter> getFriends(String id) {
        log.debug("getFriends, id={}", id);
        return this.characterData.get(id).getFriends().stream()
                .map(cid -> characterData.get(cid))
                .collect(Collectors.toList());
    }

    public List<Starship> getStarshipsByHumanId(String id) {
        log.debug("getStarshipsByHumanId, id={}", id);
        return this.humanData.get(id).getStarships().stream()
                .map(sid -> this.starshipData.get(sid))
                .collect(Collectors.toList());
    }

    public List<Object> search(String text) {
        log.debug("search by text, text={}", text);
        List<Object> results = new ArrayList<>();
        results.addAll(characterData.values().stream().filter(c -> textMatch(c.getName(), text)).collect(Collectors.toList()));
        results.addAll(starshipData.values().stream().filter(s -> textMatch(s.getName(), text)).collect(Collectors.toList()));
        return results;
    }

    public Review addReview(Episode episode, Review review) {
        log.debug("addReview for episode {}", episode);
        reviewData.get(episode).add(review);
        return review;
    }

    public List<Review> getReviews(Episode episode) {
        log.debug("getReviews for episode {}", episode);
        return reviewData.get(episode);
    }

    private boolean textMatch(String source, String text) {
        return source.toUpperCase().indexOf(text.toUpperCase()) >= 0;
    }

    /**
     * Initialize data
     */
    @PostConstruct
    public void initData() {
        log.debug("initData");
        this.humanData = new HashMap<>();
        this.droidData = new HashMap<>();
        this.starshipData = new HashMap<>();
        this.characterData = new HashMap<>();
        this.addHuman(new Human(
                "1000",
                "Luke Skywalker",
                1.72,
                77.0,
                asList("1002", "1003", "2000", "2001"),
                asList(NEWHOPE, EMPIRE, JEDI),
                asList("3001", "3003"))
        );
        this.addHuman(new Human(
                "1001",
                "Darth Vader",
                2.02,
                136.0,
                asList("1004"),
                asList(NEWHOPE, EMPIRE, JEDI),
                asList("3002"))
        );
        this.addHuman(new Human(
                "1002",
                "Han Solo",
                1.8,
                80.0,
                asList("1000", "1003", "2001"),
                asList(NEWHOPE, EMPIRE, JEDI),
                asList("3000", "3003"))
        );
        this.addHuman(new Human(
                "1003",
                "Leia Organa",
                1.5,
                49.0,
                asList("1000", "1002", "2000", "2001"),
                asList(NEWHOPE, EMPIRE, JEDI),
                asList())
        );
        this.addHuman(new Human(
                "1004",
                "Wilhuff Tarkin",
                2.02,
                136.0,
                asList("1001"),
                asList(NEWHOPE),
                asList())
        );

        this.addDroid(new Droid(
                "2000",
                "C-3PO",
                asList("1000", "1002", "1003", "2001"),
                asList(NEWHOPE, EMPIRE, JEDI),
                "Protocol"
        ));
        this.addDroid(new Droid(
                "2001",
                "R2-D2",
                asList("1000", "1002", "1003"),
                asList(NEWHOPE, EMPIRE, JEDI),
                "Astromech"
        ));

        this.addStarship(new Starship(
                "3000",
                "Millenium Falcon",
                34.37
        ));
        this.addStarship(new Starship(
                "3001",
                "X-Wing",
                12.5
        ));
        this.addStarship(new Starship(
                "3002",
                "TIE Advanced x1",
                9.2
        ));
        this.addStarship(new Starship(
                "3003",
                "Imperial shuttle",
                20.0
        ));
        reviewData = new HashMap<>();
        reviewData.put(NEWHOPE, new ArrayList<>());
        reviewData.put(EMPIRE, new ArrayList<>());
        reviewData.put(JEDI, new ArrayList<>());
    }

    private void addHuman(Human human) {
        this.humanData.put(human.getId(), human);
        this.characterData.put(human.getId(), human);
    }

    private void addDroid(Droid droid) {
        this.droidData.put(droid.getId(), droid);
        this.characterData.put(droid.getId(), droid);
    }

    private void addStarship(Starship starship) {
        this.starshipData.put(starship.getId(), starship);
    }
}
