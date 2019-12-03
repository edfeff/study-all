package com.wpp.security.distributed.uaa.mode.filter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<WorkFilter<String, String>> filters = new ArrayList<>();
        WorkFilter<String, String> f1 = (s, s2, chain) -> {
            System.out.println(" f1 before ");
            chain.doFilter(s, s2);
            System.out.println(" f1 after");
        };
        WorkFilter<String, String> f2 = (s, s2, chain) -> {
            System.out.println(" f2 before ");
            chain.doFilter(s, s2);
            System.out.println(" f2 after");
        };
        WorkFilter<String, String> f3 = (s, s2, chain) -> {
            System.out.println(" f3 before ");
            chain.doFilter(s, s2);
            System.out.println(" f3 after");
        };
        WorkFilter<String, String> f4 = new AbstractWorkFilterAdapter<String, String>() {
            @Override
            protected void beforeFilter(String o, String o2) {
                System.out.println("f4 before");
            }
        };
        WorkFilter<String, String> f5 = new AbstractWorkFilterAdapter<String, String>() {
            @Override
            protected void afterFilter(String o, String o2) {
                System.out.println("f5 after");
            }
        };

        filters.add(f5);
        filters.add(f1);
        filters.add(f2);
        filters.add(f3);
        filters.add(f4);


        VirtualWorkFilterChain<String, String> filterChain = new VirtualWorkFilterChain<>(filters);

        filterChain.doFilter("hello", "world");
    }
}
