package woowacourse.movie.movie.mapper.movie

import domain.BookingMovie
import woowacourse.movie.movie.dto.movie.BookingMovieDto
import woowacourse.movie.movie.mapper.seat.mapToSeats
import woowacourse.movie.movie.mapper.seat.mapToSeatsDto
import woowacourse.movie.movie.mapper.ticket.mapToTicketCount
import woowacourse.movie.movie.mapper.ticket.mapToTicketCountDto

fun BookingMovieDto.mapToDomain(): BookingMovie {
    return BookingMovie(this.movie.mapToMovie(), this.date.mapToMovieDate(), this.time.mapToMovieTime(), this.ticketCount.mapToTicketCount(), this.seats.mapToSeats())
}

fun BookingMovie.mapToUIModel(): BookingMovieDto {
    return BookingMovieDto(this.movie.mapToMovieDto(), this.date.mapToMovieDateDto(), this.time.mapToMovieTimeDto(), this.ticketCount.mapToTicketCountDto(), this.seats.mapToSeatsDto())
}
