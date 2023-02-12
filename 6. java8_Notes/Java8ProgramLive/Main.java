import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList();
		empList.add(new Employee(101, "shiva", 101, "active", 2000));
		empList.add(new Employee(102, "ready", 101, "active", 5000));
		empList.add(new Employee(103, "raju", 102, "inactive", 6000));
		empList.add(new Employee(104, "shivam", 102, "inactive", 4000));
		empList.add(new Employee(105, "bob",   103, "active", 3500));
		empList.add(new Employee(106, "alice", 103, "inactive", 7000));
		empList.add(new Employee(107, "srinu", 104, "active", 3500));
		empList.add(new Employee(108, "riya", 103, "active", 8000));

		// 1. To print employee details working in each department
		System.out.println("--------------------------------------------------------");
		System.out.println("1. To print employee details working in each department");
		System.out.println("--------------------------------------------------------");
		Map<Integer, List<Employee>> empListBasedonDept = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.toList()));
		empListBasedonDept.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + "------" + entry.getValue());
		}); 
		
		
		System.out.println("--------------------------------------------------------");
		System.out.println(" 2. To print employee count working in each department");
		System.out.println("--------------------------------------------------------");
		// 2. To print employee count working in each department
		Map<Integer, Long> empCountDept = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
		empCountDept.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + "-----" + entry.getValue());
			
		});
		
		
		System.out.println("--------------------------------------------------------");
		System.out.println("3. To print active and inactive employee in the given collection");
		System.out.println("--------------------------------------------------------");
		// 3. To print active and inactive employee in the given collection
		long activeEmpCount = empList.stream().filter(e -> "active".equals(e.getStatus())).count();
		long inactiveEmpCount = empList.stream().filter(e -> "inactive".equals(e.getStatus())).count();
		System.out.println("activeEmpCount :" + activeEmpCount);
		System.out.println("inactiveEmpCount :" + inactiveEmpCount);
		
		
		System.out.println("--------------------------------------------------------");
		System.out.println(" 4. To print Max/Min employee salary from the given collection");
		System.out.println("--------------------------------------------------------");
		// 4. To print Max/Min employee salary from the given collection
		Optional<Employee> emp1 = empList.stream().max(Comparator.comparing(Employee::getSalary));
		Optional<Employee> emp2 = empList.stream().min(Comparator.comparing(Employee::getSalary));
		System.out.println("Max :" + emp1);
		System.out.println("Min : " + emp2);
		
		
		System.out.println("--------------------------------------------------------");
		System.out.println(" 5. To print the Max salary of an employee from each dept");
		System.out.println("--------------------------------------------------------");
		// 5. To print the Max salary of an employee from each dept
		Map<Integer, Optional<Employee>> topSalaryEmpDeptWise = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId,
						Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
		topSalaryEmpDeptWise.entrySet().forEach(entry -> {

			System.out.println("Dept :" + entry.getKey() + "top Employee :" + entry.getValue());
		});

	}

}
