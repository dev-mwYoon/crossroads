package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    //회원가입
    public void join(MemberVO memberVO);

    //아이디 중복체크
    public Long checkId(String memberIdentification);

    //이메일 중복체크
    public Long checkEmail(String memberEmail);

    //로그인
    public Long login(String memberIdentification, String memberPassword);

    //마이페이지 정보 조회
    public MemberVO selectMyInfo(Long memberId);

    //마이페이지 정보 수정
    public void updateMyInfo(MemberVO memberVO);

    //회원 정보 목록
    public List<MemberVO> selectAll();

    //회원삭제 회원탈퇴
    public void delete(Long memberId);

    //비밀번호 변경
    public void changePassword(String memberEmail, String memberPassword);

    //아이디로 랜덤키 찾기
    public Long selectRandomKey(String memberEmail);

    //마이페이지 비밀번호 확인
    public Long checkPassword(Long memberId, String memberPassword);

    //마이페이지 비밀번호 변경
    public Long changeNewPassword(Long memberId, String memberPassword);

    //랜덤키 삽입
    public void updateRandomKey(String memberEmail, Long memberRandomKey);

    //아이디로 셀렉
    public MemberVO selectByEmail(String memberEmail);
    //마이페이지 프로필 업로드
    public void updateProfile(MemberVO memberVO);

    //    관리자 전체 조회
    public List<MemberVO> selectAllAdmin(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

    //    관리자 게시글 총 개수
    public Integer selectCountAllAdmin(@Param("keyword") String keyword);

}
