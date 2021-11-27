package hu.webuni.hr.panisznorbert.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

import hu.webuni.hr.panisznorbert.config.HrConfigProperties;
import hu.webuni.hr.panisznorbert.config.HrConfigProperties.Smart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		double yearsWorked = ChronoUnit.DAYS.between(employee.getEntry(), LocalDateTime.now()) / 365.0;

		Smart smartConfig = config.getSalary().getSmart();

		TreeMap<Double, Integer> limits = smartConfig.getLimits();

		Map.Entry<Double, Integer> floorEntry = limits.floorEntry(yearsWorked);
		return floorEntry == null ? 0 : floorEntry.getValue();

	}

}

