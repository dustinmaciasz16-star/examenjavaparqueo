package com.krakendev.parqueadero.servicio;

import java.util.ArrayList;


import com.krakendev.parqueadero.modelo.Vehiculo;
import com.krakendev.parqueadero.modelo.TicketCobro;

public class ServicioCobro {
	private ArrayList<TicketCobro> historicoTickets = new ArrayList<>();

    private final ServicioVehiculo servicioVehiculos;

    public ServicioCobro(ServicioVehiculo servicioVehiculos) {
        this.servicioVehiculos = servicioVehiculos;
    }

    public TicketCobro procesarSalida(String placa, int horas) {

        Vehiculo vehiculo = servicioVehiculos.retirarVehiculo(placa);

        if (vehiculo == null) {
            return null;
        }

        double total = vehiculo.calcularTarifa(horas);

        String codigo = "TCK-" + (int)(Math.random() * 900 + 100);

        TicketCobro ticket = new TicketCobro(codigo, vehiculo, horas, total, null);

        historicoTickets.add(ticket);

        return ticket;
    }

    public double calcularTotalRecaudado() {

        double total = 0;

        for (TicketCobro ticket : historicoTickets) {
            total += ticket.getTotalPagar();
        }

        return total;
    }

    public ArrayList<TicketCobro> listarTickets() {
        return historicoTickets;
    }

}
