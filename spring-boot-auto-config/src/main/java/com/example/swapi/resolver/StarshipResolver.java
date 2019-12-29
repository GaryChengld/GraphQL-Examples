package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.swapi.CommonUtils;
import com.example.swapi.data.DataService;
import com.example.swapi.data.LengthUnit;
import com.example.swapi.data.Starship;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class StarshipResolver implements GraphQLResolver<Starship> {
    private final DataService dataService;

    public StarshipResolver(DataService dataService) {
        this.dataService = dataService;
    }

    public Double getLength(Starship starship, LengthUnit unit) {
        return CommonUtils.getLength(starship.getLength(), unit);
    }


}
