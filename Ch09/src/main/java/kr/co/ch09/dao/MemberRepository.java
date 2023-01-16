package kr.co.ch09.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch09.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String>{

}
