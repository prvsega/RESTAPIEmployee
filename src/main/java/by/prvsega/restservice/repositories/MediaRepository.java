package by.prvsega.restservice.repositories;

import by.prvsega.restservice.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
