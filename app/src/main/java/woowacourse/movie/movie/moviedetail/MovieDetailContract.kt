package woowacourse.movie.movie.moviedetail

import woowacourse.movie.movie.dto.movie.MovieDto
import woowacourse.movie.movie.dto.theater.MovieTheaterDto
import woowacourse.movie.movie.dto.ticket.TicketCountDto
import java.time.LocalDate

interface MovieDetailContract {
    interface View {
        val presenter: Presenter

        fun showMovieInfo(poster: Int, title: String, description: String)
        fun showMovieDateInfo(date: String, time: String)
        fun formatMovieRunningDate(startDate: LocalDate, endDate: LocalDate): String
        fun formatMovieRunningTime(runningTime: Int): String
        fun showNumberOfPeople()
        fun onClickDecreaseBtnListener()
        fun onClickIncreaseBtnListener()
        fun onClickBookBtnListener(movie: MovieDto)
        fun setDateSpinner(intervalDate: List<String>)
        fun setTimeSpinner()
    }

    interface Presenter {
        fun initActivity(movieDto: MovieDto, theater: MovieTheaterDto)
        fun getMovieDate(): String
        fun getMovieTime(): String
        fun decreaseCount(movieTicket: TicketCountDto): String
        fun increaseCount(movieTicket: TicketCountDto): String
        fun getIntervalDays(): List<String>
        fun getTimes(): List<String>
    }
}
