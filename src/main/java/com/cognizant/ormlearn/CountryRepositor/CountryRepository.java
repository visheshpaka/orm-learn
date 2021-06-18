package com.cognizant.ormlearn.CountryRepositor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.mode.Country;

@Repository

public interface CountryRepository extends JpaRepository<Country, String> {

}