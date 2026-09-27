package com.krakendev.parqueadero.controladores;

import java.util.ArrayList;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakendev.parqueadero.modelo.Auto;
import com.krakendev.parqueadero.modelo.Motocicleta;
import com.krakendev.parqueadero.modelo.Vehiculo;

import com.krakendev.parqueadero.servicio.ServicioVehiculo;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final ServicioVehiculo servicioVehiculos;

    public VehiculoController(ServicioVehiculo servicioVehiculos) {
        this.servicioVehiculos = servicioVehiculos;
    }

    @PostMapping("/auto")
    public ResponseEntity<String> ingresarAuto(@RequestBody Auto auto) {

        boolean ingresado = servicioVehiculos.ingresarVehiculo(auto);

        if (ingresado) {
            return ResponseEntity.ok("Auto ingresado correctamente");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo ingresar el auto");
    }

    @PostMapping("/moto")
    public ResponseEntity<String> ingresarMoto(@RequestBody Motocicleta moto) {

        boolean ingresado = servicioVehiculos.ingresarVehiculo(moto);

        if (ingresado) {
            return ResponseEntity.ok("Motocicleta ingresada correctamente");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo ingresar la motocicleta");
    }

    @GetMapping
    public ArrayList<Vehiculo> listarVehiculos() {
        return servicioVehiculos.listarVehiculos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {

        Vehiculo vehiculo = servicioVehiculos.buscarPorPlaca(placa);

        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vehiculo);
    }
}