package iducs.javaweb.blog202112004.repository;

import iducs.javaweb.blog202112004.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl extends OracleDAOImpl implements MemberDAO {
    // 연관 객체 선언
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public MemberDAOImpl() { // 생성자 : 객체 생성 시 초기화

    }

    @Override
    public int create(Member m) {
        int ret = 0;
        /*
        String sql = "insert into member values(seq_member1.nextval, '" +
                 m.getEmail() + "', '" +
                 m.getPw() + "', '" +
                 m.getName() + "', '" +
                 m.getPhone() + "', '" +
                 m.getAddress() + "')";
         */
        String sql = "insert into member values(seq_member.nextval,?,?,?,?,?)";
        // placeholder : 자리 대체 기호
        try {
            conn = getConnection(); // DB 연결 객체 생성
            //stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getEmail());
            pstmt.setString(2, m.getPw());
            pstmt.setString(3, m.getName());
            pstmt.setString(4, m.getPhone());
            pstmt.setString(5, m.getAddress());
            ret = pstmt.executeUpdate(); // SQL 실행으로 영향받은 row 수를 반환
            if(ret > 0) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
               ret = 1;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public Member read(Member m) {
        Member retMember = null;
        String sql = "select * from member where email=?";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getEmail());
            rs = pstmt.executeQuery(); // SQL 실행 후 결과를 rs에 반환
            if(rs.next()) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                retMember = new Member();
                retMember.setEmail(rs.getString("email"));
                retMember.setName(rs.getString("name"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return retMember;
        }
    }

    @Override
    public List<Member> readList() {
        List<Member> memberList = null;
        Member retMember = null;
        String sql = "select * from member";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // SQL 실행 후 결과를 rs에 반환
            memberList = new ArrayList<Member>();
            while(rs.next()) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                retMember = new Member();
                retMember.setEmail(rs.getString("email"));
                retMember.setName(rs.getString("name"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
                memberList.add(retMember);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return memberList;
        }
    }

    @Override
    public int update(Member m) {
        int ret = 0;
        String sql = "update member set name=?, phone=?, address=? where email=?";
        // placeholder : 자리 대체 기호
        try {
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getPhone());
            pstmt.setString(3, m.getAddress());
            pstmt.setString(4, m.getEmail());
            ret = pstmt.executeUpdate(); // SQL 실행으로 영향받은 row 수를 반환
            if(ret > 0) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = 1;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public int delete(Member m) {
        int ret = 0;
        String sql = "delete from member where email=? and pw=?";
        // placeholder : 자리 대체 기호
        try {
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getEmail());
            pstmt.setString(2, m.getPw());
            ret = pstmt.executeUpdate(); // SQL 실행으로 영향받은 row 수를 반환
            if(ret > 0) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = 1;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public Member login(Member m) {
        Member retMember = null;
        String sql = "select * from member where email=? and pw=?";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getEmail());
            pstmt.setString(2, m.getPw());
            rs = pstmt.executeQuery(); // SQL 실행 후 결과를 rs에 반환
            if(rs.next()) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                retMember = new Member();
                retMember.setEmail(rs.getString("email"));
                retMember.setName(rs.getString("name"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return retMember;
        }
    }


}
