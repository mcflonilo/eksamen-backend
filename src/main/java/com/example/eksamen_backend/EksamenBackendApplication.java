package com.example.eksamen_backend;

import com.example.eksamen_backend.model.*;
import com.example.eksamen_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
public class EksamenBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EksamenBackendApplication.class, args);
	}




//https://www.baeldung.com/spring-junit-prevent-runner-beans-testing-execution
	@ConditionalOnProperty(
			prefix = "command.line.runner",
			value = "enabled",
			havingValue = "true",
			matchIfMissing = true)
	@Bean
	CommandLineRunner commandLineRunner(
			AddressRepository addressRepository,
			CustomerRepository customerRepository,
			MachineRepository machineRepository,
			OrderRepository orderRepository,
			SubAssemblyRepository subAssemblyRepository,
			PartRepository partRepository
	) {

		return args -> {

			Address address = addressRepository.save(new Address("smedstusvingen"));
			Address address2 = addressRepository.save(new Address("nydaLEN"));
			Address address3 = addressRepository.save(new Address("OSLO"));
			Address address4 = addressRepository.save(new Address("BERGEN"));
			Sub_assembly subAssembly = subAssemblyRepository.save(new Sub_assembly("gearbox"));
			Sub_assembly subAssembly2 = subAssemblyRepository.save(new Sub_assembly("engine"));
			Machine machine = machineRepository.save(new Machine("car"));
			Machine machine2 = machineRepository.save(new Machine("fridge"));
			Part part = partRepository.save(new Part("gear"));
			Part part2 = partRepository.save(new Part("cog"));
			Part part3 = partRepository.save(new Part("screw"));

			subAssembly.setPart(part);
			subAssembly.setPart(part2);
			subAssembly2.setPart(part);
			subAssembly2.setPart(part3);

			machine.setSub_assemblies(subAssembly);
			machine.setSub_assemblies(subAssembly2);
			machine2.setSub_assemblies(subAssembly);

			Order order = orderRepository.save(new Order());
			Order order2 = orderRepository.save(new Order());
			Customer customer = customerRepository.save( new Customer("larsern"));
			Customer customer2 = customerRepository.save( new Customer("trym"));
			customer.setAddress(address);
			customer.setAddress(address2);
			customer.setAddress(address3);
			customer.setAddress(address4);
			customer2.setAddress(address);
			customer2.setAddress(address2);
			customer2.setAddress(address3);
			customer2.setAddress(address4);
			customerRepository.save(customer2);
			customerRepository.save(customer);

			order.setCustomer(customer);
			order.setMachine(machine);
			order2.setCustomer(customer2);
			order2.setMachine(machine2);

			orderRepository.save(order);
			orderRepository.save(order2);
		};
	}
}
