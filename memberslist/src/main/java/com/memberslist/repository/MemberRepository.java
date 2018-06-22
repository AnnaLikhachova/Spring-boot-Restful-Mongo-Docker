package com.memberslist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.memberslist.model.Member;

/**
 * 
 * @author Administrator
 *
 */


public interface MemberRepository extends MongoRepository<Member, String> {
}