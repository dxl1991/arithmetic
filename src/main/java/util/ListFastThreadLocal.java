package util;

import java.util.ArrayList;
import java.util.List;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author liweiping
 * @date 2020/7/1 18:39
 *
 */
public class ListFastThreadLocal<E> extends FastThreadLocal<List<E>> {

    public List<E> getAndClear() {
        List<E> list = get();
        list.clear();
        return list;
    }

    @Override
    protected List<E> initialValue() {
        return new ArrayList<>();
    }

    public void clear(){
         get().clear();
    }

}


