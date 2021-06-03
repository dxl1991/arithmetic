package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * @author dengxinlong
 * @date 2021/1/25 14:17
 * @version 1.0
 */
public class HttpServerVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        Vertx vertx = this.vertx;
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(request -> {
            HttpServerResponse response = request.response();
            response.putHeader("Content-type","text/html;charset=utf-8");
            response.send("SUCCESS");
        });
        httpServer.listen(80);
    }
}
