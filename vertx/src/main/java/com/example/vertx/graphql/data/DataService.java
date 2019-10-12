package com.example.vertx.graphql.data;

import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.vertx.graphql.data.Episode.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

/**
 * @author Gary Cheng
 */
@Slf4j
public class DataService {
    private String aboutMessage = "Star Wars GraphQL api v1.0";
    private Map<String, Human> humanData;
    private Map<String, Droid> droidData;
    private Map<String, Starship> starshipData;
    private Map<String, MovieCharacter> characterData;
    private Map<Episode, List<Review>> reviewData;

    public DataService() {
        this.initData();
    }

    /**
     * Find character by id
     *
     * @param id character id
     * @return
     */
    public Single<MovieCharacter> getCharacter(String id) {
        log.debug("getCharacter, id={}", id);
        return Single.just(characterData.get(id));
    }

    /**
     * Find human by id
     *
     * @param id human id
     * @return
     */
    public Single<Human> getHuman(String id) {
        log.debug("getHuman, id={}", id);
        return Single.just(humanData.get(id));
    }

    /**
     * Find Droid by id
     *
     * @param id Droid id
     * @return
     */
    public Single<Droid> getDroid(String id) {
        log.debug("getDroid, id={}", id);
        return Single.just(droidData.get(id));
    }

    /**
     * Find Starship by id
     *
     * @param id starship id
     * @return
     */
    public Single<Starship> getStarship(String id) {
        log.debug("getStarship, id={}", id);
        return Single.just(starshipData.get(id));
    }

    /**
     * Return Hero character by episode
     *
     * @param episode the episode
     * @return
     */
    public Single<MovieCharacter> getHero(Episode episode) {
        log.debug("getHero, episode={}", episode);
        return Single.just(characterData.get(episode == EMPIRE ? "1000" : "2001"));
    }

    /**
     * Find friends by character id
     *
     * @param id
     * @return
     */
    public Single<List<MovieCharacter>> getFriends(String id) {
        log.debug("getFriends, id={}", id);
        return Single.just(this.characterData.get(id).getFriends().stream()
            .map(cid -> characterData.get(cid))
            .collect(Collectors.toList()));
    }

    /**
     * Return starships by human id
     *
     * @param id
     * @return
     */
    public Single<List<Starship>> getStarshipsByHumanId(String id) {
        log.debug("getStarshipsByHumanId, id={}", id);
        return Single.just(this.humanData.get(id).getStarships().stream()
            .map(sid -> this.starshipData.get(sid))
            .collect(Collectors.toList()));
    }

    /**
     * Search by text
     *
     * @param text
     * @return
     */
    public Single<List<Object>> search(String text) {
        log.debug("search by text, text={}", text);
        List<Object> results = new ArrayList<>();
        results.addAll(characterData.values().stream().filter(c -> textMatch(c.getName(), text)).collect(Collectors.toList()));
        results.addAll(starshipData.values().stream().filter(s -> textMatch(s.getName(), text)).collect(Collectors.toList()));
        return Single.just(results);
    }

    /**
     * Add Review
     *
     * @param episode
     * @param review
     * @return
     */
    public Single<Review> addReview(Episode episode, Review review) {
        log.debug("addReview for episode {}", episode);
        reviewData.get(episode).add(review);
        return Single.just(review);
    }

    /**
     * Get Reviews by episode
     *
     * @param episode
     * @return
     */
    public Single<List<Review>> getReviews(Episode episode) {
        log.debug("getReviews for episode {}", episode);
        return Single.just(reviewData.get(episode));
    }

    /**
     * Return about message
     *
     * @return
     */
    public Single<String> getAboutMessage() {
        log.debug("getAboutMessage");
        return Single.just(this.aboutMessage);
    }

    /**
     * Set about message
     *
     * @param message
     */
    public Single<String> setAboutMessage(String message) {
        log.debug("setAboutMessage message={}", message);
        this.aboutMessage = message;
        return Single.just(this.aboutMessage);
    }

    private boolean textMatch(String source, String text) {
        return source.toUpperCase().indexOf(text.toUpperCase()) >= 0;
    }

    private void initData() {
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
            emptyList())
        );
        this.addHuman(new Human(
            "1004",
            "Wilhuff Tarkin",
            2.02,
            136.0,
            asList("1001"),
            asList(NEWHOPE),
            emptyList())
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
