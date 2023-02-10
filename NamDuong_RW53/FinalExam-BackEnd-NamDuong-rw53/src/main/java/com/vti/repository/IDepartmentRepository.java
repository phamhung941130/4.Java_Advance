package com.vti.repository;


import com.vti.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department,Integer>, JpaSpecificationExecutor<Department> {
   // @Query(value = "FROM Department WHERE name = ?1 ")
//    @Query(value = "FROM Department WHERE name = :name ")
//    public Department findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Department WHERE name =:name")
    void deleteByName(String name);

    Department findByName(String name);

    boolean existsByName(String name);
//
//    List<Department> findByTotalMembers(int totalMembers);
//
//    List<Department> findByTotalMembersGreaterThanEqualAndTotalMembersLessThanEqual(int min, int max);
}
