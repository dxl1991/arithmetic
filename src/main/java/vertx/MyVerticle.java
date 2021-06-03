package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;

/**
 * 同一个Verticle里的代码在同一个线程里执行
 * @author dengxinlong
 * @date 2021/1/23 15:26
 * @version 1.0
 */
public class MyVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.println("MyVerticle start!");
        vertx.setTimer(3000,id->{
            EventBus eb = vertx.eventBus();
            //广播消息
            eb.publish("news.uk.sport","2-Yay! Someone kicked a ball across a patch of grass");
            //发送给某一个Verticle
            eb.send("news.uk.sport","3-Yay! Someone kicked a ball across a patch of grass");
            //卸载这个Verticle
            vertx.undeploy(deploymentID());
            System.out.println("MyVerticleTimer:"+Thread.currentThread().getName());
        });
        propFile();
        //在work线程执行
        vertx.executeBlocking(promis -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promis.complete();
            System.out.println("executeBlocking:"+Thread.currentThread().getName());
        },res ->{
            System.out.println("The result is : " +res.result());
        });
        System.out.println("MyVerticle:"+Thread.currentThread().getName());
    }

    @Override
    public void stop() throws Exception {
        System.out.println("MyVerticle stop!");
    }

    public void propFile(){
        FileSystem fileSystem = vertx.fileSystem();
        Future<FileProps> props = fileSystem.props("test.txt");
        props.onComplete((AsyncResult<FileProps> ar) -> {
            System.out.println("propFile:"+Thread.currentThread().getName());
            if(ar.succeeded()){
                FileProps fileProps = ar.result();
                System.out.println("File size = " + fileProps.size());
            }else{
                System.out.println("Failure: " + ar.cause().getMessage());
            }
        });
    }
}
