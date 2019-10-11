package com.example.vertx.graphql;

import io.reactivex.Completable;
import io.vertx.core.Promise;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MainVerticle extends AbstractVerticle {
    private static final String KEY_PORT = "http.port";
    private static final String PATH = "/api";

    @Override
    public void start(Promise<Void> startPromise) {
        this.startHttpServer()
            .subscribe(startPromise::complete, startPromise::fail);
    }

    private Completable startHttpServer() {
        log.debug("Starting http server...");
        Integer port = this.config().getInteger(KEY_PORT);
        Router router = Router.router(vertx);
        try {
            router.route(PATH).handler(GraphQLProvider.createHandler());
            return vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(port)
                .ignoreElement()
                .doOnComplete(() -> log.debug("Http server started on port {}", port));
        } catch (IOException e) {
            return Completable.error(e);
        }
    }
}
