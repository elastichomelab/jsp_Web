package iducs.javaweb.blog202112004.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DAO {
    // 외부에서 사용하는 방법을 정의
    Connection getConnection();
    void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs);

}
