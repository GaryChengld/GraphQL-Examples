package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.swapi.CommonUtils;
import com.example.swapi.data.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class HumanResolver implements GraphQLResolver<Human> {
    private final DataService dataService;

    public HumanResolver(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Starship> getStarships(Human human) {
        return dataService.getStarshipsByHumanId(human.getId());
    }

    public Double getHeight(Human human, LengthUnit unit) {
        return CommonUtils.getLength(human.getHeight(), unit);
    }

    public List<MovieCharacter> getFriends(Human human) {
        return dataService.getFriends(human.getId());
    }
}
