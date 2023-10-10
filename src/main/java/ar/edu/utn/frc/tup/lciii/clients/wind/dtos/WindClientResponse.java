package ar.edu.utn.frc.tup.lciii.clients.wind.dtos;

public record WindClientResponse(Long id,Long location_id,Integer speed,Integer direction,String created_at) {
}
