package com.registration.registration.Repository;

import com.registration.registration.Infrastructure.Models.FileInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileInfo, String> {
}
