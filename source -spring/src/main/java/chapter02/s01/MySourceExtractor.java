package chapter02.s01;

import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.core.io.Resource;

public class MySourceExtractor implements SourceExtractor {
    @Override
    public Object extractSource(Object sourceCandidate, Resource definingResource) {
        System.out.println();
        System.out.println(MySourceExtractor.class.getName());
        System.out.println(sourceCandidate);
        System.out.println(definingResource);
        System.out.println();
        return definingResource;
    }
}
