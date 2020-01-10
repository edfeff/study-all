package chapter02.s01;

import org.springframework.beans.factory.FactoryBean;

public class MyTestBeanFactory implements FactoryBean<MyTestBean> {
    @Override
    public MyTestBean getObject() throws Exception {
        return new MyTestBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyTestBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
