package kr.co.voard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String>{

	public int countByUid(String uid);
}
