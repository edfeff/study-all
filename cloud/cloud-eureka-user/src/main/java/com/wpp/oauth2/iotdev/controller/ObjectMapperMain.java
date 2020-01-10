package com.wpp.oauth2.iotdev.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * @author wangpp
 */
public class ObjectMapperMain {
    public static void main(String[] args) throws JsonProcessingException {
        A a1 = new A();
        a1.setName("a1");
        ArrayList<A> list = new ArrayList<>();

        A a2 = new A();
        a2.setName("a2");
        A a3 = new A();
        a3.setName("a3");
        list.add(a2);
        list.add(a3);


        a1.setList(list);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(a1);
        System.out.println(s);

        A a = objectMapper.convertValue(s, A.class);
        System.out.println(a);
        System.out.println(a.getName());
        System.out.println(a.getList());
        System.out.println(a.getList().size());


    }
}

class A {
    ArrayList<A> list;
    private String name;

    public A() {
    }

    public A(ArrayList<A> list) {
        this.list = list;
    }

    public ArrayList<A> getList() {
        return list;
    }

    public void setList(ArrayList<A> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
