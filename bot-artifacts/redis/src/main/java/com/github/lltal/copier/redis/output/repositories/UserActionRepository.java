package com.github.lltal.copier.redis.output.repositories;

import com.github.lltal.copier.redis.model.UserActionEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserActionRepository extends CrudRepository<UserActionEntity, Long> {
}
