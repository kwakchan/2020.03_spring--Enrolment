package kr.ac.ks.app.repository;

import kr.ac.ks.app.entity.Interests;
import kr.ac.ks.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestsRepository extends JpaRepository<Interests, Long>{
}
