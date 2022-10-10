package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.conversion.ReservationConversionService;
import ekke.spring.dao.entity.Reservation;
import ekke.spring.dao.repository.ReservationRepository;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dto.ReservationDto;
import ekke.spring.service.exception.ReservationNotFoundException;
import ekke.spring.service.exception.UserNotFoundException;
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
    private UserRepository userRepository;

    @Override
    public ReservationDto add(final ReservationDto dto) {
        reservationDtoValidator.validate(dto);
        Reservation savedReservation = reservationRepository.save(reservationConversionService.ReservationDto2ReservationEntity(dto));
        ReservationDto savedDto = reservationConversionService.ReservationEntity2ReservationDto(savedReservation);
        reservationDtoValidator.validateCreateReservation(savedDto.getId(), savedDto.getRestaurantId(), savedDto.getSeatNumber());
        return savedDto;
    }

    @Override
    public List<ReservationDto> getAll() {
        return reservationRepository.findAll().stream()
                .map(reservation -> reservationConversionService.ReservationEntity2ReservationDto(reservation)).collect(Collectors.toList());
    }

    @Override
    public ReservationDto getById(final Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(()
                -> new ReservationNotFoundException(String.format("Reservation with id %d is not found", id)));
        return reservationConversionService.ReservationEntity2ReservationDto(reservation);
    }

    public List<ReservationDto> getByUserId(final Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id %d not found", userId)));
        return reservationRepository.findByUser(userId).stream()
                .map(reservation -> reservationConversionService.ReservationEntity2ReservationDto(reservation)).collect(Collectors.toList());
    }

    @Override
    public ReservationDto update(final Long id, final ReservationDto dto) {
        reservationDtoValidator.validate(dto);
        reservationDtoValidator.validateCreateReservation(id, dto.getRestaurantId(), dto.getSeatNumber());
        Reservation oldReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(String.format("Reservation with id %d not found", id)));
        Reservation newReservation = setReservationForUpdate(oldReservation, reservationConversionService.ReservationDto2ReservationEntity(dto));
        reservationRepository.save(newReservation);
        return reservationConversionService.ReservationEntity2ReservationDto(newReservation);
    }

    @Override
    public void delete(final Long id) {
        reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(String.format("Reservation with id %d not found", id)));
        reservationRepository.deleteById(id);
    }

    private Reservation setReservationForUpdate(final Reservation oldReservation, final Reservation newReservation) {
        oldReservation.setSeatNumber(newReservation.getSeatNumber());
        oldReservation.setTime(newReservation.getTime());
        return oldReservation;
    }
}
