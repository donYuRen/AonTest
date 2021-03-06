package aon.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aon.demo.model.Insurer;

@Repository
public interface InsurerRepository extends JpaRepository<Insurer, Long> {

}
