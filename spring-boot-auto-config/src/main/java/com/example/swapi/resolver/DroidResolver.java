package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.swapi.data.DataService;
import com.example.swapi.data.Droid;
import com.example.swapi.data.MovieCharacter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class DroidResolver implements GraphQLResolver<Droid> {
    private final DataService dataService;

    public DroidResolver(DataService dataService) {
        this.dataService = dataService;
    }

    public List<MovieCharacter> getFriends(Droid droid) {
        return dataService.getFriends(droid.getId());
    }
}
