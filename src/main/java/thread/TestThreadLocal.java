package thread;

/**
 * @Author dengxinlong
 * @Date 2020/5/12 17:27
 * @slogan CODE IS TRUTH
 * 每个thread中都存在一个map, map的类型是ThreadLocal.ThreadLocalMap.
 * Map中的key为一个threadlocal实例. 这个Map的确使用了弱引用,不过弱引用只是针对key.
 * 每个key都弱引用指向threadlocal. 当把threadlocal实例置为null以后,没有任何强引用指向threadlocal实例,
 * 所以threadlocal将会被gc回收.
 * 但是,我们的value却不能回收,因为存在一条从current thread连接过来的强引用.
 * 只有当前thread结束以后, current thread就不会存在栈中,强引用断开, Current Thread, Map, value将全部被GC回收.
 * 在ThreadLocal的get,set的时候都会清除线程Map里所有key为null的value
 */
public class TestThreadLocal extends ThreadLocal<TestFuture> {
    static ThreadLocal<TestJoin> testJoinThreadLocal = ThreadLocal.withInitial(TestJoin::new);
    static TestThreadLocal testThreadLocal = new TestThreadLocal();

    @Override
    protected TestFuture initialValue() {
        return new TestFuture();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(testJoinThreadLocal.get());
            testJoinThreadLocal.remove();//用完记得移除掉
            System.out.println(testThreadLocal.get());
            testThreadLocal.remove();
        }).start();
        System.out.println(testJoinThreadLocal.get());
        System.out.println(testThreadLocal.get());
        System.out.println(Runtime.getRuntime().freeMemory());
    }
}
