package iducs.javaweb.blog202112004.util;

import iducs.javaweb.blog202112004.model.Blog;

import java.util.Comparator;

public class DescByBlogEmail implements Comparator<Blog> {
    @Override
    public int compare(Blog o1, Blog o2) {
        return o2.getEmail().compareTo(o1.getEmail());
    }
}
