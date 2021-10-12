package hu.webuni.hr.panisznorbert.config;

import hu.webuni.hr.panisznorbert.service.DefaultEmployeeService;
import hu.webuni.hr.panisznorbert.service.EmployeeService;
import hu.webuni.hr.panisznorbert.service.SmartEmployeeService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {

	private Salary salary = new Salary();

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public static class Salary{

		private Default def = new Default();
		private Smart smart = new Smart();

		public Default getDef() {
			return def;
		}

		public void setDef(Default def) {
			this.def = def;
		}

		public Smart getSmart() {
			return smart;
		}

		public void setSmart(Smart smart) {
			this.smart = smart;
		}
		
	}
	
	public static class Default{

		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
		
	}

	public static class Smart{

		private int max_year;
		private int medium_year;
		private int minimum_year;
		private int minimum_month;

		private int max_percent;
		private int medium_percent;
		private int minimum_percent;

		public int getMax_year() {
			return max_year;
		}

		public void setMax_year(int max_year) {
			this.max_year = max_year;
		}

		public int getMedium_year() {
			return medium_year;
		}

		public void setMedium_year(int medium_year) {
			this.medium_year = medium_year;
		}

		public int getMinimum_year() {
			return minimum_year;
		}

		public void setMinimum_year(int minimum_year) {
			this.minimum_year = minimum_year;
		}

		public int getMinimum_month() {
			return minimum_month;
		}

		public void setMinimum_month(int minimum_month) {
			this.minimum_month = minimum_month;
		}

		public int getMax_percent() {
			return max_percent;
		}

		public void setMax_percent(int max_percent) {
			this.max_percent = max_percent;
		}

		public int getMedium_percent() {
			return medium_percent;
		}

		public void setMedium_percent(int medium_percent) {
			this.medium_percent = medium_percent;
		}

		public int getMinimum_percent() {
			return minimum_percent;
		}

		public void setMinimum_percent(int minimum_percent) {
			this.minimum_percent = minimum_percent;
		}
	}
}
