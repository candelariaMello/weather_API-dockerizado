package ar.edu.utn.frc.tup.lciii.clients.temperature.dtos;

public record TemperatureClientResponse(Long id,Long location_id,Integer temperature,String unit,String created_at) {
}
