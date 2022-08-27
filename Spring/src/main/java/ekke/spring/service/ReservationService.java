package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.dto.ReservationDto;

import java.util.List;

public class ReservationService implements CrudServices<ReservationDto> {

    @Override
    public ReservationDto add(ReservationDto dto) {
        return null;
    }

    @Override
    public List<ReservationDto> getAll() {
        return null;
    }

    @Override
    public ReservationDto update(Long id, ReservationDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
