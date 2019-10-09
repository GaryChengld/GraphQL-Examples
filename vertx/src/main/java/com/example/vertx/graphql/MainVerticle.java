package com.example.vertx.graphql;

import io.reactivex.Completable;
import io.vertx.core.Promise;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) {
        this.startHttpServer(vertx)
                .subscribe(startPromise::complete, startPromise::fail);
    }

    private Completable startHttpServer(Vertx vertx) {
        logger.debug("Starting http server...");
        HttpServer server = vertx.createHttpServer()
                .requestHandler(req ->
                        req.response()
                                .putHeader("content-type", "text/plain")
                                .end("Hello from Vert.x!")
                );
        return server.rxListen(8888)
                .ignoreElement()
                .doOnComplete(() -> logger.debug("Http server started on port 8888"));
    }
}
