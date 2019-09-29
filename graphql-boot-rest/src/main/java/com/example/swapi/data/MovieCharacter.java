package com.example.swapi.data;

import java.util.List;

/**
 * @author Gary Cheng
 */
public interface MovieCharacter {
    String getId();

    String getName();

    List<String> getFriends();

    List<Episode> getAppearsIn();
}
