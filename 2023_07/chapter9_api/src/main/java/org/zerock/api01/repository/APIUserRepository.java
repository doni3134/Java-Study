package org.zerock.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api01.domain.APIUser;

import java.util.Optional;

public interface APIUserRepository extends JpaRepository<APIUser, String> {

    Optional<APIUser> findAPIUserByMidAndMpw(String mid, String mpw);

}
