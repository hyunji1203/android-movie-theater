package domain.screeningschedule

import domain.DayOfWeek
import java.time.LocalTime

class ReservationTime(private val dayOfWeek: DayOfWeek) {

    private fun getStartTime(): Int = when (dayOfWeek) {
        DayOfWeek.WEEKDAY -> WEEKDAY_START_TIME
        DayOfWeek.WEEKEND -> WEEKEND_START_TIME
    }

    fun getIntervalTimes(): List<String> {
        return (getStartTime()..END_TIME step 2)
            .asSequence()
            .map { LocalTime.of(it, 0) }
            .map { it.toString() }
            .toList()
    }

    companion object {
        private const val WEEKDAY_START_TIME = 10
        private const val WEEKEND_START_TIME = 9
        private const val END_TIME = 23
    }
}
