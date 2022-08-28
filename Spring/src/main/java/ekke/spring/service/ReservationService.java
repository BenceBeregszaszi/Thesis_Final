package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.common.IdValidator;
import ekke.spring.conversion.ReservationConversionService;
import ekke.spring.dao.entity.Reservation;
import ekke.spring.dao.repository.ReservationRepository;
import ekke.spring.dto.ReservationDto;
import ekke.spring.service.exception.ReservationNotFoundException;
import ekke.spring.validators.ReservationDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationService implements CrudServices<ReservationDto> {

    @Autowired
    private ReservationDtoValidator reservationDtoValidator;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationConversionService reservationConversionService;

    @Autowired
    private IdValidator idValidator;

    @Override
    public ReservationDto add(final ReservationDto dto) {
        reservationDtoValidator.validate(dto);
        Reservation savedReservation = reservationRepository.save(reservationConversionService.ReservationDto2ReservationEntity(dto));
        return reservationConversionService.ReservationEntity2ReservationDto(savedReservation);
    }

    @Override
    public List<ReservationDto> getAll() {
        return reservationRepository.findAll().stream()
                .map(reservation -> reservationConversionService.ReservationEntity2ReservationDto(reservation)).collect(Collectors.toList());
    }

    @Override
    public ReservationDto getById(final Long id) {
        idValidator.validateId(id);
        Reservation reservation = reservationRepository.findById(id).orElseThrow(()
                -> new ReservationNotFoundException(String.format("Reservation with id %d is not found", id)));
        return reservationConversionService.ReservationEntity2ReservationDto(reservation);
    }

    @Override
    public ReservationDto update(final Long id, final ReservationDto dto) {
        return null;
    }

    @Override
    public void delete(final Long id) {
        idValidator.validateId(id);
        reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(String.format("Reservation with id %d not found", id)));
        reservationRepository.deleteById(id);
    }
}
