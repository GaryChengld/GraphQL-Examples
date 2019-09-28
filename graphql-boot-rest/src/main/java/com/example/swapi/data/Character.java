package com.example.swapi.data;

import java.util.List;

/**
 * @author Gary Cheng
 */
public interface Character {
    String getId();

    String getName();

    List<String> getFriends();

    List<Episode> getAppearsIn();
}
