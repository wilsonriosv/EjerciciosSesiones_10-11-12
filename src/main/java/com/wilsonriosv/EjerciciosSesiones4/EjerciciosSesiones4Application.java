package com.wilsonriosv.EjerciciosSesiones4;

import com.wilsonriosv.EjerciciosSesiones4.Models.Laptop;
import com.wilsonriosv.EjerciciosSesiones4.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class EjerciciosSesiones4Application {

	public static void main(String[] args) {

		//Genero el Beans
		ApplicationContext context = SpringApplication.run(EjerciciosSesiones4Application.class, args);
		//Uso el Beans
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// CRUD
		// crear un objeto laptop
		Laptop laptop1 = new Laptop(null,"HP","Pavilion 300","HFD5645GH","Core i7-7020",16,true);
		Laptop laptop2 = new Laptop(null,"LENOVO","E40","KL34566JH","Core i5-7020",12,false);
		Laptop laptop3 = new Laptop(null,"DELL","A45","PO78875TH","Core i3-11020",8,true);

		// Contar los laptop que hay en la BdD
		System.out.println("Cantidad de laptops en la BdD: " + repository.findAll().size());

		// almacenar un laptop en la BdD
		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);

		// Contar nuevamente los laptop que hay en la BdD después de haber guardado
		System.out.println("Cantidad de laptops en la BdD: " + repository.findAll().size());

		// recuperar todos los laptops de la BdD
		List<Laptop> todos = repository.findAll();
		System.out.println(todos);

	}

}
