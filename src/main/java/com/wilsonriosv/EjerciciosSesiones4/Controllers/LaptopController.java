package com.wilsonriosv.EjerciciosSesiones4.Controllers;

import com.wilsonriosv.EjerciciosSesiones4.Models.Laptop;
import com.wilsonriosv.EjerciciosSesiones4.Repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;


@RestController
public class LaptopController {
    //atributos
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository laptopRepository;

    //Constructor
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //Métodos CRUD
    /**
     * Buscar todos los laptops (lista de laptops)
     * http://localhost:8081/api/laptops
     * @return
     */
    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        // Devuelve una lista con todos los laptops de la base de datos
        return laptopRepository.findAll();
    }

    /**
     * findOneById(id) -> Buscar un solo laptop en base de datos según su id
     * http://localhost:8081/api/laptops/1
     * http://localhost:8081/api/laptops/2
     * Request
     * Response
     * @param id
     * @return
     */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar un laptop por clave primaria ID Long")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * create() -> Crear un nuevo laptop en base de datos
     * @param laptop
     * @param headers
     * @return
     */
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        // guardar el laptop recibido por parámetro en la base de datos
        if(laptop.getId() != null){ // quiere decir que existe el id y por tanto no es una creación
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    /**
     * update(laptop) -> Actualizar un laptop en la BdD
     */
    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null ){ // Si el ID es null quiere decir que está intentando CREAR un laptop
            log.warn("No existe el laptop en la Base de Datos");
            return ResponseEntity.badRequest().build();
        }
        //Otra forma de verificar si existe por ID
        if(!laptopRepository.existsById(laptop.getId())){ // Si el ID es no existe (!) quiere decir que está intentando CREAR un laptop
            log.warn("No existe el laptop en la Base de Datos");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); //Retorna el laptop creado
    }

    /**
     * delete(id) -> Eliminar un laptop de la BdD por su ID
     */
    @ApiIgnore //Se ignorará este método en la documentación de Swagger
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            log.warn("Tratando de eliminar un laptop que no existe");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * deleteAll() -> Eliminar todos los laptops de la BdD
     */
    @ApiIgnore //Se ignorará este método en la documentación de Swagger
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Requerido para borrar todos los laptops");
        laptopRepository.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
