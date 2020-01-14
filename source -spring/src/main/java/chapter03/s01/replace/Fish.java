package chapter03.s01.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class Fish implements MethodReplacer {
    public String kind() {
        return this.getClass().getName();
    }

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if ("kind".equals(method.getName())) {
            return this.kind();
        }
        return null;
    }
}
