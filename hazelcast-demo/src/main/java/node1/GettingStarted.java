package node1;

import com.hazelcast.core.*;
import com.hazelcast.config.*;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class GettingStarted {
    public static void main(String[] args) {
        Config cfg = new Config();
//        cfg.setLicenseKey("企业版秘钥");
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        IMap<Object, Object> map = instance.getMap("map");
        IQueue<Object> queue = instance.getQueue("queue");
        IList<Object> list = instance.getList("list");
        AtomicInteger count = new AtomicInteger();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);


//        new Thread(() -> {
//            while (true) {
//                queue.add(count.getAndIncrement());
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true) {
//                try {
//                    System.out.println(queue.take());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();

//        new Thread(() -> {
//            while (true) {
//                map.put(count.get(), count.getAndIncrement());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        new Thread(() -> {
//            while (true) {
//                System.out.println(map.size());
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


    }
}
