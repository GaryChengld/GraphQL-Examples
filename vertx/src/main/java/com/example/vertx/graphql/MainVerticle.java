package com.example.vertx.graphql;

import graphql.GraphQL;
import io.reactivex.Completable;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.ext.web.handler.graphql.GraphQLHandler;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
    private static final String KEY_PORT = "http.port";
    private static final String PATH = "/api";

    @Override
    public void start(Promise<Void> startPromise) {
        this.startHttpServer(vertx)
            .subscribe(startPromise::complete, startPromise::fail);
    }

    private Completable startHttpServer(Vertx vertx) {
        logger.debug("Starting http server...");
        Integer port = this.config().getInteger(KEY_PORT);
        Router router = Router.router(vertx);
        Handler handler = GraphQLHandler.create(this.createGraphQL());
        router.route(PATH).handler(handler);
        return vertx.createHttpServer()
            .requestHandler(router)
            .rxListen(port)
            .ignoreElement()
            .doOnComplete(() -> logger.debug("Http server started on port {}", port));
    }

    private GraphQL createGraphQL() {
        String schema = "";
        return GraphQL.newGraphQL(null)
            .build();
    }
}
