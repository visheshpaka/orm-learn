package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	@Autowired
	CountryService countryService;

	@Autowired 
	StockService stockService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	SkillService skillService;

	public static void main(String[] args) {
		SpringApplication.run(OrmLearnApplication.class, args);
	}

	@Bean
	CommandLineRunner testGetAllCountries() {
		return args -> {
			LOGGER.info("START... testGetAllCountries");
			List<Country> countries = countryService.getAllCountries();
			LOGGER.debug("countries = {}", countries);
			LOGGER.info("END... testGetAllCountries");
		};
	}

	@Bean
	CommandLineRunner testGetCountryById() {
		return args -> {
			LOGGER.info("START... testGetCountryById");
			Country country = countryService.findCountryByCode("IN");
			LOGGER.debug("country = {}", country);
			LOGGER.info("END... testGetCountryById");
		};
	}

	@Bean
	CommandLineRunner testAddCountry() {
		return args -> {
			LOGGER.info("START... testAddCountry");
			Country country = new Country();
			country.setCode("PV");
			country.setName("Vishesh");
			countryService.addCountry(country);
			LOGGER.debug("country = {}", country);
			LOGGER.info("END... testAddCountry");
		};
	}

	@Bean
	CommandLineRunner testUpdateCountry() {
		return args -> {
			LOGGER.info("START... testUpdateCountry");
			countryService.updateCountry("AB", "Arsdri");
			LOGGER.info("END... testUpdateCountry");
		};
	}

	@Bean
	CommandLineRunner testDeleteCountry() {
		return args -> {
			LOGGER.info("START... testDeleteCountry");
			countryService.deleteCountry("AB");
			LOGGER.info("END... testDeleteCountry");
		};
	}

	@Bean
	CommandLineRunner testGetAllMatchingCountries() {
		return args -> {
			LOGGER.info("START... testGetAllMatchingCountries");
			LOGGER.debug("countries = {}", countryService.getAllMatchingCountries("ou"));
			LOGGER.info("END... testGetAllMatchingCountries");
		};
	}

	@Bean
	CommandLineRunner testGetAllCountriesStartingWithLetter() {
		return args -> {
			LOGGER.info("START... testGetAllCountriesStartingWithLetter");
			LOGGER.debug("countries = {}", countryService.getAllCountriesStartingWithLetter('z'));
			LOGGER.info("END... testGetAllCountriesStartingWithLetter");
		};
	}

	@Bean
	CommandLineRunner testGetAllStocks() {
		return args -> {
			LOGGER.info("START... testGetAllStocks");
			List<Stock> stocks = stockService.getAllStocks();
			LOGGER.debug("stocks = {}", stocks);
			LOGGER.info("END... testGetAllStocks");
		};
	}

	@Bean
	CommandLineRunner testGetStocksOfFBInSep2019() {
		return args -> {
			LOGGER.info("START... Stocks of FB in September 2019");
			List<Stock> stocks = stockService.getStocksOfFBInSep2019();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END... Stocks of FB in September 2019");
		};
	}

	@Bean
	CommandLineRunner testGetStocksOfGoogleGT1250() {
		return args -> {
			LOGGER.info("START... Stocks of whose price Google Greater than 1250");
			List<Stock> stocks = stockService.getStocksOfGoogleGT1250();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END... Stocks of whose price Google Greater than 1250");
		};
	}

	@Bean
	CommandLineRunner testGetTop3StocksByVolume() {
		return args -> {
			LOGGER.info("START... Stocks of top 3 dates which had highest volume of transactions");
			List<Stock> stocks = stockService.getTop3StocksByVolume();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END... Stocks of top 3 dates which had highest volume of transactions");
		};
	}

	@Bean
	CommandLineRunner test3LowestNetflixStocks() {
		return args -> {
			LOGGER.info("START... Stocks of Lowest three Netflix");
			List<Stock> stocks = stockService.get3LowestNetflixStocks();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END... Stocks of Lowest three Netflix");
		};
	}

	@Bean
	CommandLineRunner testGetEmployee() {
		return args -> {
			LOGGER.info("START... testGetEmployee");
			Employee employee = employeeService.get(1);
			LOGGER.debug("Employee:{}", employee);
			LOGGER.debug("Department:{}", employee.getDepartment());
			LOGGER.debug("Skills:{}", employee.getSkillList());
			LOGGER.info("END... testGetEmployee");
		};
	}

	@Bean
	CommandLineRunner testAddEmployee() {
		return args -> {
			LOGGER.info("START... testAddEmployee");
			Employee employee = new Employee();
			employee.setName("Paka");
			employee.setSalary(new BigDecimal(9000.0));
			employee.setPermanent(true);
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			employee.setDateOfBirth(ft.parse("1999-06-01"));
			Department department = departmentService.get(1); 
			employee.setDepartment(department);
			employeeService.save(employee);
			LOGGER.debug("Employee:{}", employee);
			LOGGER.info("END... testAddEmployee");
		};
	}

	@Bean
	CommandLineRunner testUpdateEmployee() {
		return args -> {
			LOGGER.info("START... testUpdateEmployee");
			Employee employee = employeeService.get(7);
			Department department = departmentService.get(4);
			employee.setDepartment(department);
			employeeService.save(employee);
			LOGGER.debug("Employee:{}", employee);
			LOGGER.info("END... testUpdateEmployee");
		};
	}

	@Bean
	CommandLineRunner testGetDepartment() {
		return args -> {
			LOGGER.info("START... testGetDepartment");
			Department department = departmentService.get(3);
			LOGGER.debug("Department:{}", department);
			department.getEmployeeList().forEach(employee -> LOGGER.debug("{}", employee));
			LOGGER.info("END... testGetDepartment");
		};
	}

	@Bean
	CommandLineRunner testAddSkillToEmployee() {
		return args -> {
			LOGGER.info("START... testAddSkillToEmployee");
			Employee employee = employeeService.get(4);
			Skill skill = skillService.get(3);
			employee.getSkillList().add(skill);
			employeeService.save(employee);
			LOGGER.debug("Employee:{}", employee);
			LOGGER.info("END... testAddSkillToEmployee");
		};
	}

	@Bean
	CommandLineRunner testGetAllPermanentEmployees() {
		return args -> {
			LOGGER.info("START... testGetAllPermanentEmployees");
			List<Employee> employees = employeeService.getAllPermanentEmployees();
			LOGGER.debug("Permanent Employees:{}", employees);
			employees.forEach(employee -> LOGGER.debug("Skills:{}", employee.getSkillList()));
			LOGGER.info("END... testGetAllPermanentEmployees");
		};
	}

	@Bean
	CommandLineRunner testGetAverageSalary() {
		return args -> {
			LOGGER.info("START... testGetAverageSalary");
			LOGGER.debug("Average Salary: {}", employeeService.getAverageSalary(2));
			LOGGER.info("END... testGetAverageSalary");
		};
	}

	@Bean
	CommandLineRunner testGetAllEmployeesNative() {
		return args -> {
			LOGGER.info("START... testGetAllEmployeesNative");
			List<Employee> employees = employeeService.getAllEmployeesNative();
			employees.forEach(employee -> LOGGER.debug("Employee: {}", employee));
			LOGGER.info("END... testGetAllEmployeesNative");
		};
	}
}