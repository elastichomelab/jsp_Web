package iducs.javaweb.blog202112004.util;

import iducs.javaweb.blog202112004.model.Blog;

import java.util.Comparator;

public class AscByBlogEmail implements Comparator<Blog> {
    @Override
    public int compare(Blog o1, Blog o2) {
        return o1.getEmail().compareTo(o2.getEmail());
    }
    //이메일 기준 오름차순
}
