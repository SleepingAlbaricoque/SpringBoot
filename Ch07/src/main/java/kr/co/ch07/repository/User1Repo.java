package kr.co.ch07.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.ch07.vo.User1VO;

public interface User1Repo extends JpaRepository<User1VO, String>{

	// JPA 쿼리메서드
	public User1VO findUser1VOByUid(String uid); // uid 이미 PK로 VO에서 지정한 상태 => 결과값은 하나만 나올 수 있음
	public List<User1VO> findUser1VOByName(String name); // name은 중복 가능한 필드 => 결과값은 list로; 메서드명에 entity 이름 생략가능하지만 적어주는 게 좋음
	public List<User1VO> findUser1VOByNameNot(String name);
	
	public List<User1VO> findUser1VOByUidAndName(String uid, String name);
	public List<User1VO> findUser1VOByUidOrName(String uid, String name);
	
	public List<User1VO> findUser1VOByAgeGreaterThan(int age);
	public List<User1VO> findUser1VOByAgeGreaterThanEqual(int age);
	public List<User1VO> findUser1VOByAgeLessThan(int age);
	public List<User1VO> findUser1VOByAgeLessThanEqual(int age);
	
	public List<User1VO> findUser1VOByNameLike(String name); // %?%
	public List<User1VO> findUser1VOByNameContains(String name);
	public List<User1VO> findUser1VOByNameStartsWith(String name); // %?
	public List<User1VO> findUser1VOByNameEndsWith(String name); // ?%
	
	public List<User1VO> findUser1VOByOrderByName();
	public List<User1VO> findUser1VOByOrderByNameAsc();
	public List<User1VO> findUser1VOByOrderByNameDesc();
	public List<User1VO> findUser1VOByAgeGreaterThanOrderByAgeDesc(int age);
	
	public int countUser1VOByUid(String uid); // 0 또는 1 <- uid being PK
	public int countUser1VOByName(String name); // 0 이상
	
	// JPQL
	@Query("SELECT u1 FROM User1VO AS u1 WHERE age < 30") // Persistent Context에서 entities를 대상으로 조회 => 여기서 age는 DB 필드값이 아닌 VO 필드값
	public List<User1VO> selectUserUnderAge30();
	
	@Query("SELECT u1 FROM User1VO AS u1 WHERE u1.name =?1")
	public List<User1VO> selectUserByName(String name);
	
	@Query("SELECT u1 FROM User1VO As u1 WHERE u1.name = :name")
	public List<User1VO> selectUserByWithParam(@Param("name") String name);
}
