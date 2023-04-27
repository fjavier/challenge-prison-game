package dev.francisco.briceno.prisongame.repository;

import dev.francisco.briceno.prisongame.domain.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazeRepository extends JpaRepository<Prisoner, Long> {
    Integer countAllByCanScape(Boolean canScape);
}
