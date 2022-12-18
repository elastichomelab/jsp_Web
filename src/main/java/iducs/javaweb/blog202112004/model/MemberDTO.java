package iducs.javaweb.blog202112004.model;

// 1. model 패키지를 추가
// 2. MemberDTO 클래스 정의
public class MemberDTO {
    private String fullname;
    private String email;

    public String getFullname() { // boiler-plate code : 상용구, 관용구
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
