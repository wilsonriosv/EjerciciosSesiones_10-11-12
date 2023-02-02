package com.wilsonriosv.EjerciciosSesiones4.Repositories;

import com.wilsonriosv.EjerciciosSesiones4.Models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
