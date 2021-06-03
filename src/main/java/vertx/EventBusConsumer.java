package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

/**
 * @author dengxinlong
 * @date 2021/1/23 15:38
 * @version 1.0
 */
public class EventBusConsumer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.println("EventBusConsumer start!");
        eventBus();
        System.out.println("EventBusConsumer:"+Thread.currentThread().getName());
    }

    public void eventBus(){
        EventBus eb = vertx.eventBus();

        MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
        //收到消息回调
        consumer.handler(message -> {
            System.out.println("1-I have received a message:"+message.body());
            message.reply("1-how interesting!");
        });

        //注册完成回调
        consumer.completionHandler(res -> {
            if(res.succeeded()){
                System.out.println("1-THe handler registration has reached all nodes");
            }else{
                System.out.println("Registration failed!");
            }
        });
    }
}
