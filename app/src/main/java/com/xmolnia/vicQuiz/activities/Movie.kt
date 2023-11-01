package com.xmolnia.vicQuiz.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*
import com.takusemba.multisnaprecyclerview.MultiSnapHelper
import com.takusemba.multisnaprecyclerview.SnapGravity
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.adapters.BannerMoviesPagerAdapter
import com.xmolnia.vicQuiz.adapters.MovieItemRecyclerAdapter
import com.xmolnia.vicQuiz.adapters.MoviesGenreAdapter
import com.xmolnia.vicQuiz.databinding.MovieBinding
import com.xmolnia.vicQuiz.eva.constants.MovieBase
import com.xmolnia.vicQuiz.models.MovieLoader
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Movie : Fragment() {

    private val movieRealtimeData: DatabaseReference = FirebaseDatabase.getInstance().getReference("Movie")
    private lateinit var binding: MovieBinding
    private lateinit var bannerMoviesPagerAdapter: BannerMoviesPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieDataList = arrayListOf<MovieLoader>()

        setBannerMoviesAdapter(movieDataList)
        /**Обявление жанра recycler -Жанры*/
        val genreAdapter = MoviesGenreAdapter(requireActivity(), movieDataList)
        binding.genreRecycler.adapter = genreAdapter

        /**Обявление главного recycler - Все Фильмы*/

        val allMoviesAdapter = MovieItemRecyclerAdapter(
            requireContext(), requireActivity(),
            movieDataList
        )
        binding.allMovies.adapter = allMoviesAdapter
        MultiSnapHelper(SnapGravity.START, 1, 300f).attachToRecyclerView(binding.allMovies)


        /**Обявление второго recycler - Недавно Добавленные*/
        val latestAddedList = arrayListOf<MovieLoader>()
        if (!movieDataList.isNullOrEmpty()) {
            val size = movieDataList.size / 3
            latestAddedList.addAll(movieDataList.dropLast(size))

        }

        if (latestAddedList.size == 0) {
            binding.latestAddedTitle.visibility = View.GONE
        }
        val latestAddedAdapter = MovieItemRecyclerAdapter(
            requireContext(), requireActivity(),
            latestAddedList
        )
        binding.latestAdded.adapter = latestAddedAdapter


        setBannerMoviesAdapter(movieDataList)



        movieRealtimeData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    snap.getValue(MovieLoader::class.java)?.let {
                        //Обновление главного листа хранящий все данные всех фильмов
                        movieDataList.add(it)
                    }
                }
                allMoviesAdapter.notifyDataSetChanged()
                latestAddedAdapter.notifyDataSetChanged()
                bannerMoviesPagerAdapter.notifyDataSetChanged()
                genreAdapter.notifyDataSetChanged()
                val movieBase = MovieBase.getInstance()
                movieBase.clearData()
                movieBase.inputData(movieDataList)
                binding.ShimmerLayout.visibility = View.GONE
                binding.container.visibility = View.VISIBLE
//                movie.relativeLayout.background = ContextCompat.getDrawable(requireContext(), R.drawable.round)


            }

            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })







        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.nestedScroll.visibility = View.VISIBLE
                        binding.info.visibility = View.GONE
                        setBannerMoviesAdapter(movieDataList)
                        setScrollDefaultState()


                    }

                    1 -> {
                        binding.nestedScroll.visibility = View.GONE
                        binding.info.visibility = View.VISIBLE
                        setLottieAnimation("Этот раздел находится в разработке.\nПозднее тут будут сериалы.")
                    }

                    2 -> {
                        binding.nestedScroll.visibility = View.GONE
                        binding.info.visibility = View.VISIBLE
                        setLottieAnimation("Этот раздел находится в разработке.\nПозднее тут будут мультфильмы.")
                    }

                    3 -> {
                        binding.nestedScroll.visibility = View.GONE
                        binding.info.visibility = View.VISIBLE
                        setLottieAnimation("Этот раздел находится в разработке.\nПозднее тут будут шоу.")
                    }

                    else -> {
                        binding.nestedScroll.visibility = View.GONE
                        binding.info.visibility = View.VISIBLE
                        setLottieAnimation("Этот раздел находится в разработке.")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("TAG", "UnTab:${tab?.position.toString()}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("TAG", "OnTabRes:${tab?.position.toString()}")

            }

        })


    }

    private lateinit var future: ScheduledFuture<*>
    private lateinit var future2: ScheduledFuture<*>
    private val scheduledExecutorService: ScheduledExecutorService =
        Executors.newScheduledThreadPool(1)

    var option = true
    private var checkO = false

    private fun setBannerMoviesAdapter(bannerMoviesList: ArrayList<MovieLoader>) {


        bannerMoviesPagerAdapter = BannerMoviesPagerAdapter(requireActivity(), bannerMoviesList)
        binding.bannerViewPager.adapter = bannerMoviesPagerAdapter

        binding.tabIndicator.setupWithViewPager(binding.bannerViewPager)

        if (!bannerMoviesList.isNullOrEmpty()) {

            binding.bannerViewPager.currentItem = 0


            if (option) {
                if (checkO) {
                    future2.cancel(false)
                } else {
                    checkO = false
                }
                future = scheduledExecutorService.scheduleAtFixedRate(
                    AutoSlider(bannerMoviesList),
                    4,
                    6,
                    TimeUnit.SECONDS
                )

                binding.tabIndicator.setupWithViewPager(binding.bannerViewPager, true)
                option = false

            } else {
                future.cancel(false)
                future2 = scheduledExecutorService.scheduleAtFixedRate(
                    AutoSlider(bannerMoviesList),
                    4,
                    6,
                    TimeUnit.SECONDS
                )

                option = true
                checkO = true
            }
        }
    }

    inner class AutoSlider(val list: ArrayList<MovieLoader>) : TimerTask() {

        override fun run() {
            activity?.runOnUiThread {

                if (binding.bannerViewPager.currentItem < list.size - 1) {
                    binding.bannerViewPager.currentItem = binding.bannerViewPager.currentItem + 1
                } else {
                    binding.bannerViewPager.currentItem = 0
                }
            }
        }
    }


    /**Это функция при выборе новой вкладки, к примеру вкладку (Сериалы) прокручивается к самому нвверху макета*/
    private fun setScrollDefaultState() {
        binding.nestedScroll.fullScroll(View.FOCUS_UP)
        binding.nestedScroll.scrollTo(0, 0)
        binding.appbar.setExpanded(true)
    }


    fun setLottieAnimation(
        infoText: String,
        resource: IntArray = intArrayOf(
            R.raw.movie1,
            R.raw.movie2,
            R.raw.movie3,
            R.raw.movie4,
            R.raw.movie5,
            R.raw.movie7,
            R.raw.deadpool
        )
    ) {

        binding.lottie.setAnimation(resource.random())
        binding.lottie.playAnimation()
        binding.infoText.text = infoText

    }

    companion object {

        fun newInstance() = Movie()

    }
    init {
        Log.d("CREATED", "Movie : $this")

    }



}
