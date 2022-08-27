package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.conversion.ReservationConversionService;
import ekke.spring.dao.repository.ReservationRepository;
import ekke.spring.dto.ReservationDto;
import ekke.spring.validators.ReservationDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService implements CrudServices<ReservationDto> {

    @Autowired
    private ReservationDtoValidator reservationDtoValidator;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationConversionService reservationConversionService;

    @Override
    public ReservationDto add(ReservationDto dto) {
        return null;
    }

    @Override
    public List<ReservationDto> getAll() {
        return null;
    }

    @Override
    public ReservationDto getById(Long id) {
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
