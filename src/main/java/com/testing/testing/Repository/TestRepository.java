package com.testing.testing.Repository;

import com.testing.testing.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Country,Long> {

}
