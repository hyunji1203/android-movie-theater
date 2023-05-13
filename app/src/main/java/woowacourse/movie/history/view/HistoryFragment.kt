package woowacourse.movie.history.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import domain.BookingMovie
import woowacourse.movie.databinding.FragmentHistoryBinding
import woowacourse.movie.history.view.contract.HistoryContract
import woowacourse.movie.history.view.presenter.HistoryPresenter
import woowacourse.movie.mapper.movie.mapToDomain
import woowacourse.movie.mapper.movie.mapToUIModel
import woowacourse.movie.ticket.model.BookingMovieModel
import woowacourse.movie.ticket.view.TicketActivity

class HistoryFragment : Fragment(), HistoryContract.View {
    private lateinit var presenter: HistoryContract.Presenter
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setUpBinding()
        presenter = HistoryPresenter(this)
        setUpHistoryData(presenter.getHistory())
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getHistory()
    }

    private fun setUpBinding() {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun setUpHistoryData(history: List<BookingMovieModel>) {
        binding.historyRv.adapter = HistoryAdapter(
            history.map { it.mapToDomain() },
            fun (item: BookingMovie) {
                val intent = Intent(context, TicketActivity::class.java)
                intent.putExtra(BOOKING_MOVIE_KEY, item.mapToUIModel())
                startActivity(intent)
            }
        )
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        binding.historyRv.adapter = HistoryAdapter(
            presenter.getHistory().map { it.mapToDomain() },
            fun (item: BookingMovie) {
                val intent = Intent(context, TicketActivity::class.java)
                intent.putExtra(BOOKING_MOVIE_KEY, item.mapToUIModel())
                startActivity(intent)
            }
        )
    }

    companion object {
        private const val BOOKING_MOVIE_KEY = "booking_movie"
    }
}
