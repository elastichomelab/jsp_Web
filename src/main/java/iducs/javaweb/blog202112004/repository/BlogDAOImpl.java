package iducs.javaweb.blog202112004.repository;

import iducs.javaweb.blog202112004.model.Blog;

// 이 친구들은 기본객체가 아님 그래서 import
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends OracleDAOImpl implements BlogDAO{

    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public BlogDAOImpl(){
        conn = getConnection();
    }

    @Override
    public int create(Blog blog) {
        String sql = "insert into blog202112004 (id, title, content, author, email) values(seq_blog202112004.nextval,?,?,?,?)";
        int rows = 0; // 질의 처리 결과 영향받은 행의 수
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getAuthor());
            pstmt.setString(4, blog.getEmail());
            rows = pstmt.executeUpdate();// 1 이상이면 정상, 0 이하면 오류
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return rows;
        }
    }

    @Override
    public Blog read(Blog blog) {
        Blog ret = null;
        // 지난주 email 조건 -> id 조건으로 조회
        String sql = "select * from blog202112004 where id='" + blog.getId() + "'"; // 유일키로(unique key)로 조회
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                ret= new Blog();
                ret.setId(rs.getLong("id"));
                ret.setTitle(rs.getString("title"));
                ret.setContent(rs.getString("content"));
                ret.setAuthor(rs.getString("author"));
                ret.setEmail(rs.getString("email"));
                ret.setRegdate(rs.getString("create_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public List<Blog> readList() {
        List<Blog> blogList = null;
        Blog ret = null;
        String sql = "select * from blog202112004 ORDER BY create_date DESC";

        try {
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // SQL 실행 후 결과를 rs에 반환
            blogList = new ArrayList<Blog>();
            while(rs.next()) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = new Blog();
                ret.setId(rs.getLong("id"));
                ret.setTitle(rs.getString("title"));
                ret.setContent(rs.getString("content"));
                ret.setAuthor(rs.getString("author"));
                ret.setEmail(rs.getString("email"));
                ret.setRegdate(rs.getString("create_date"));
                blogList.add(ret);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return blogList;
        }
    }

    @Override
    public int update(Blog blog) {
        String sql = "update blog202112004 set author=?, email=?, title=?, content=? where id=?";
        int rows = 0; // 질의 처리 결과 영향받은 행의 수
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getAuthor());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());
            pstmt.setString(4, blog.getContent());
            pstmt.setLong(5, blog.getId());
            rows = pstmt.executeUpdate();// 1 이상이면 정상, 0 이하면 오류
        } catch(SQLException e){
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(Blog blog) {
        String sql = "delete from blog202112004 where id=?";

        int rows = 0; // 질의 처리 결과 영향받은 행의 수
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());
            rows = pstmt.executeUpdate();// 1 이상이면 정상, 0 이하면 오류
        } catch(SQLException e){
            e.printStackTrace();
        }
        return rows; }

    @Override
    public int readTotalRows() {
        int rows = 0;
        String sql = "select count(*) as totalRows from blog202112004";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                rows = rs.getInt("totalRows");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Blog> asc() {
        List<Blog> blogList = null;
        Blog ret = null;
        String sql = "select * from blog202112004 ORDER BY create_date ASC";

        try {
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // SQL 실행 후 결과를 rs에 반환
            blogList = new ArrayList<Blog>();
            while (rs.next()) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = new Blog();
                ret.setId(rs.getLong("id"));
                ret.setTitle(rs.getString("title"));
                ret.setContent(rs.getString("content"));
                ret.setAuthor(rs.getString("author"));
                ret.setEmail(rs.getString("email"));
                ret.setRegdate(rs.getString("create_date"));
                blogList.add(ret);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return blogList;
        }
    }

//    @Override
//    public List<Blog> readListPagination(Pagination pagination) {
//        ArrayList<Blog> blogList  = null;
//        String sql = "select * from (select A.*, rownum as rnum from (select * from blog200412345 order by id desc) A) where rnum >= ? and rnum <= ?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, pagination.getFirstRow());
//            pstmt.setInt(2, pagination.getEndRow());
//            rs = pstmt.executeQuery();
//            blogList = new ArrayList<Blog>();
//            while (rs.next()) {
//                Blog blog = new Blog();
//                blog.setId(rs.getLong("id")); // id 값도 dto에 저장
//                blog.setAuthor(rs.getString("name"));
//                blog.setEmail(rs.getString("email"));
//                blog.setTitle(rs.getString("title"));
//                blog.setContent(rs.getString("content"));
//                blogList.add(blog);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return blogList;
//    }
//
//    @Override
//    public List<Blog> sortListPaginationDT(Pagination pagination) {
//        ArrayList<Blog> blogList  = null;
//        String sql = "select * from (select A.*, rownum as rnum from (select * from blog200412345 order by title desc) A) where rnum >= ? and rnum <= ?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, pagination.getFirstRow());
//            pstmt.setInt(2, pagination.getEndRow());
//            rs = pstmt.executeQuery();
//            blogList = new ArrayList<Blog>();
//            while (rs.next()) {
//                Blog blog = new Blog();
//                blog.setId(rs.getLong("id")); // id 값도 dto에 저장
//                blog.setAuthor(rs.getString("name"));
//                blog.setEmail(rs.getString("email"));
//                blog.setTitle(rs.getString("title"));
//                blog.setContent(rs.getString("content"));
//                blogList.add(blog);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return blogList;
//    }
//
//    @Override
//    public List<Blog> sortListPaginationAT(Pagination pagination) {
//        ArrayList<Blog> blogList  = null;
//        String sql = "select * from (select A.*, rownum as rnum from (select * from blog200412345 order by title) A) where rnum >= ? and rnum <= ?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, pagination.getFirstRow());
//            pstmt.setInt(2, pagination.getEndRow());
//            rs = pstmt.executeQuery();
//            blogList = new ArrayList<Blog>();
//            while (rs.next()) {
//                Blog blog = new Blog();
//                blog.setId(rs.getLong("id")); // id 값도 dto에 저장
//                blog.setAuthor(rs.getString("name"));
//                blog.setEmail(rs.getString("email"));
//                blog.setTitle(rs.getString("title"));
//                blog.setContent(rs.getString("content"));
//                blogList.add(blog);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return blogList;
//    }
//
//    @Override
//    public List<Blog> sortListPaginationDE(Pagination pagination) {
//        ArrayList<Blog> blogList  = null;
//        String sql = "select * from (select A.*, rownum as rnum from (select * from blog200412345 order by email desc) A) where rnum >= ? and rnum <= ?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, pagination.getFirstRow());
//            pstmt.setInt(2, pagination.getEndRow());
//            rs = pstmt.executeQuery();
//            blogList = new ArrayList<Blog>();
//            while (rs.next()) {
//                Blog blog = new Blog();
//                blog.setId(rs.getLong("id")); // id 값도 dto에 저장
//                blog.setAuthor(rs.getString("name"));
//                blog.setEmail(rs.getString("email"));
//                blog.setTitle(rs.getString("title"));
//                blog.setContent(rs.getString("content"));
//                blogList.add(blog);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return blogList;
//    }
//
//    @Override
//    public List<Blog> sortListPaginationAE(Pagination pagination) {
//        ArrayList<Blog> blogList  = null;
//        String sql = "select * from (select A.*, rownum as rnum from (select * from blog200412345 order by email) A) where rnum >= ? and rnum <= ?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, pagination.getFirstRow());
//            pstmt.setInt(2, pagination.getEndRow());
//            rs = pstmt.executeQuery();
//            blogList = new ArrayList<Blog>();
//            while (rs.next()) {
//                Blog blog = new Blog();
//                blog.setId(rs.getLong("id")); // id 값도 dto에 저장
//                blog.setAuthor(rs.getString("name"));
//                blog.setEmail(rs.getString("email"));
//                blog.setTitle(rs.getString("title"));
//                blog.setContent(rs.getString("content"));
//                blogList.add(blog);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return blogList;
//    }
}
