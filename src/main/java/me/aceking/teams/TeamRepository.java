package me.aceking.teams;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ListCrudRepository<Team, Long> {
}