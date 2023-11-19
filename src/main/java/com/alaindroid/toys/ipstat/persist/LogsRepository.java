package com.alaindroid.toys.ipstat.persist;

import com.alaindroid.toys.ipstat.persist.model.LogLineRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<LogLineRecord, Integer> {
}
