package recycler;

import io.netty.util.Recycler;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * Netty为了减少频繁new对象的性能损耗，引进了一个通用的对象池，它就是Recycler
 */
public class RecycleTest {
    private static final Recycler<User> RECYCLER = new Recycler<User>() {

        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    private static class User {
        private final Recycler.Handle<User> handle;

        public User(Recycler.Handle<User> handle) {
            this.handle = handle;
        }

        public void recycle() {
            handle.recycle(this);
        }
    }

    public static void main(String[] args) {
        FastThreadLocalThread task = new FastThreadLocalThread() {
            @Override
            public void run() {
                // 对象池为空则创建对象
                User user = RECYCLER.get();

                user.recycle();

                // 对象池不为空则复用对象
                User user1 = RECYCLER.get();

                // 输出true
                System.out.println(user1 == user);
            }
        };
        task.start();
    }
}


