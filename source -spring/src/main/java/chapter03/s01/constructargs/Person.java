package chapter03.s01.constructargs;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Person {
    private String name;
    private Integer age;
    private String email;
    private List<String> friends;
    private Map<String, String> metas;
    private Properties properties;
    private int[] arr;
    private Set<String> set;

    public Person(Set<String> set) {
        this.set = set;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Person(Properties properties) {
        this.properties = properties;
    }

    public Person(int[] arr) {
        this.arr = arr;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public Person(Map<String, String> metas) {
        this.metas = metas;
    }

    public Map<String, String> getMetas() {
        return metas;
    }

    public void setMetas(Map<String, String> metas) {
        this.metas = metas;
    }

    public Person(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
