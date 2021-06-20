package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

	@Query("SELECT c from Country c where c.name like %?1% order by c.name")
	List<Country> findCountriesByPartialName(String partialName);
	
	@Query("SELECT c from Country c where c.name like ?1%")
	List<Country> findCountriesByStartingLetter(char startingLetter);
}