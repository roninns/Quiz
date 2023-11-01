package com.xmolnia.vicQuiz.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.adapters.MovieItemRecyclerAdapter
import com.xmolnia.vicQuiz.databinding.ActivityGenreBinding
import com.xmolnia.vicQuiz.models.MovieLoader

class GenreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra("genreList")
        val movieList: ArrayList<MovieLoader> = bundle?.getSerializable("genreList") as ArrayList<MovieLoader>

        if (movieList.isNullOrEmpty()) {
            binding.info.visibility = View.VISIBLE
            binding.genreTitle.visibility=View.GONE
        }

        binding.allMovies.setHasFixedSize(true)
        binding.allMovies.adapter = MovieItemRecyclerAdapter(this.applicationContext, this, movieList)
        binding.genreTitle.text= "Фильмы по жанру - "+intent.getStringExtra("genreName").toString()
        setLottieAnimation("По жанру - \"${intent.getStringExtra("genreName").toString()}\" пока что не добавлены никакие фильмы. Обновления в скором времени.")


    }


    private fun setLottieAnimation(infoText: String, resource: IntArray = intArrayOf(R.raw.movie1, R.raw.movie2, R.raw.movie3, R.raw.movie4, R.raw.movie5, R.raw.movie7, R.raw.deadpool)) {

        binding.lottie.setAnimation(resource.random())
        binding.lottie.playAnimation()
        binding.infoText.text = infoText

    }
}