package at.technikum.blogservice.repository;

import at.technikum.blogservice.model.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightRepository extends JpaRepository<Sight, Long> {
}
