package vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @author dengxinlong
 * @date 2021/1/23 15:25
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions d = new DeploymentOptions().setInstances(2).setWorker(true).setWorkerPoolName("the-specific-pool");
        vertx.deployVerticle("vertx.EventBusConsumer",d); //会创建2个实例
        vertx.deployVerticle(new MyVerticle());
        vertx.deployVerticle(new HttpServerVerticle());

    }
}
