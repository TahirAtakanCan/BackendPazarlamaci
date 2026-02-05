package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.TaskDef;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TaskDefRepository extends JpaRepository<TaskDef, UUID> {
}
