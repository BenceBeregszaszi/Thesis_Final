package ekke.spring.conversion;

import ekke.spring.dao.entity.Reservation;
import ekke.spring.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationConversionService {

    public ReservationDto ReservationEntity2ReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        return reservationDto;
    }

    public Reservation ReservationDto2ReservationEntity(ReservationDto reservationDto){
        Reservation reservation = new Reservation();
        return reservation;
    }

    public List<ReservationDto> ReservationEntityList2ReservationDtoList(List<Reservation> reservationList) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservationList){
            reservationDtos.add(this.ReservationEntity2ReservationDto(reservation));
        }
        return reservationDtos;
    }
}
