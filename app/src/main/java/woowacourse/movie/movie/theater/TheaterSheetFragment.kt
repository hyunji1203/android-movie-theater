package woowacourse.movie.movie.theater

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import woowacourse.movie.databinding.FragmentTheaterSheetBinding
import woowacourse.movie.movie.dto.movie.MovieDto
import woowacourse.movie.movie.dto.theater.MovieTheaterDto
import woowacourse.movie.movie.moviedetail.MovieDetailActivity
import woowacourse.movie.movie.moviedetail.MovieDetailActivity.Companion.MOVIE_KEY
import woowacourse.movie.movie.movielist.HomeFragment
import woowacourse.movie.movie.movielist.OnClickListener
import woowacourse.movie.movie.utils.getParcelableCompat

class TheaterSheetFragment : BottomSheetDialogFragment(), TheaterContract.View {
    private lateinit var binding: FragmentTheaterSheetBinding
    override lateinit var presenter: TheaterContract.Presenter

    private lateinit var adapter: TheaterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpBinding()
        presenter = TheaterPresenter(this)
        initDetailData()
        return binding.root
    }

    private fun setUpBinding() {
        binding = FragmentTheaterSheetBinding.inflate(layoutInflater)
    }

    override fun initDetailData() {
        val movie = arguments?.getParcelableCompat<MovieDto>(MOVIE_KEY) ?: throw IllegalArgumentException()
        presenter.initFragment(movie)
    }

    override fun setUpTheaterData(movie: MovieDto) {
        val theaterAdapter = TheaterAdapter(movie.theaters.theaters)
        adapter = theaterAdapter
        binding.theaterRecyclerview.adapter = adapter

        theaterAdapter.itemViewClick = object : OnClickListener<MovieTheaterDto> {
            override fun onClick(item: MovieTheaterDto) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MOVIE_KEY, movie)
                intent.putExtra(THEATER_KEY, item)
                dismiss()
                startActivity(intent)
            }
        }
    }

    companion object {
        const val THEATER_KEY = "theater_key"

        fun newInstance(item: MovieDto): TheaterSheetFragment {
            return TheaterSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HomeFragment.MOVIE_KEY, item)
                }
            }
        }
    }
}
