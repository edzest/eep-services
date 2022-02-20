package org.edzest.eep.repositories;

import org.edzest.eep.entities.TestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestInfoRepository extends JpaRepository<TestInfo, Long> {
    List<TestInfo> findAll();
}
