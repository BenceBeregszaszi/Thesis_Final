package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dao.entity.Reservation;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.CityRepository;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dto.ReservationDto;
import ekke.spring.service.exception.CityNotFoundException;
import ekke.spring.service.exception.RestaurantNotFoundException;
import ekke.spring.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReservationConversionService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ReservationDto ReservationEntity2ReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setVersion(reservation.getVersion());
        reservationDto.setUserId(reservation.getPerson().getId());
        reservationDto.setTime(reservation.getTime());
        reservationDto.setCityId(reservation.getCity().getId());
        reservationDto.setRestaurantId(reservation.getRestaurant().getId());
        reservationDto.setSeatNumber(reservation.getSeatNumber());
        return reservationDto;
    }

    public Reservation ReservationDto2ReservationEntity(ReservationDto reservationDto){
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setVersion(reservationDto.getVersion());
        reservation.setCity(cityId2CityEntity(reservationDto.getCityId()));
        reservation.setPerson(userId2UserEntity(reservationDto.getUserId()));
        reservation.setSeatNumber(reservationDto.getSeatNumber());
        reservation.setRestaurant(restaurantId2RestaurantEntity(reservationDto.getRestaurantId()));
        reservation.setTime(reservationDto.getTime());
        return reservation;
    }

    private City cityId2CityEntity(final Long cityId){
        return cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", cityId)));
    }

    private User userId2UserEntity(final Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", userId)));
    }

    private Restaurant restaurantId2RestaurantEntity(final Long restaurantId){
        return restaurantRepository.findById(restaurantId).orElseThrow(()
                -> new RestaurantNotFoundException(String.format("Restaurant with id %d not found", restaurantId)));
    }
}
