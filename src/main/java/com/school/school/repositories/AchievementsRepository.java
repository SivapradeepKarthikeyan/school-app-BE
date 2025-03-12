package com.school.school.repositories;

import com.school.school.entities.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementsRepository extends JpaRepository<Achievements,String> {}
