package com.xmolnia.vicQuiz.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.adapters.ActorsAdapter
import com.xmolnia.vicQuiz.databinding.ActivityMovieDetailsBinding
import com.xmolnia.vicQuiz.interfaces.YouTube
import com.xmolnia.vicQuiz.models.Actor
import com.xmolnia.vicQuiz.models.MovieLoader


class MovieDetails : AppCompatActivity() {


    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val bundle: Bundle = intent.getBundleExtra("actorsIntent")!!
        val movieData: MovieLoader = bundle.getSerializable("class") as MovieLoader
        val actors: ArrayList<Actor> = bundle.getSerializable("actor") as ArrayList<Actor>




        binding.YouTubePlayer.addYouTubePlayerListener(object : YouTube {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = movieData.youtubeURL.toString()
                youTubePlayer.pause()
                youTubePlayer.loadVideo(videoId, 0f)
            }

        })

        binding.watchTelegramBtn.setOnClickListener {
            val uri = Uri.parse(movieData.telegramURL.toString())
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        binding.movieDetail.text = movieData.movieDescription
        binding.movieGenre.text = movieData.movieGenres?.substringBeforeLast(",")
        binding.countries.text = movieData.countriesName?.substringBeforeLast(",")
        binding.releaseData.text = movieData.releaseData
        val (a, b) = movieData.movieTime?.split(",")!!
        binding.movieSize.text = "$a ч  ${b}м"
        binding.imdb.text = movieData.imdbRating + "/10"
        binding.kinopoisk.text = movieData.kinopoiskRating + "/10"
        binding.ageRestriction.text = movieData.ageRestriction?.toString() + "+"
        binding.movieName.text = movieData.movieName


        Glide.with(this).load(movieData.movieImage).placeholder(R.drawable.placeholder_small).
        into(binding.imageView)


        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = ActorsAdapter(this, actors)
        binding.actorsRecycler.layoutManager = layoutManager
        binding.actorsRecycler.adapter = adapter

        lifecycle.addObserver(binding.YouTubePlayer)


    }

    override fun onDestroy() {
        super.onDestroy()
        binding.YouTubePlayer.release()
    }
}


//    binding.text.text = intent.getStringExtra(MOVIE_NAME) ?: ""
//
//
//    val loading = SkeletonLoading(
//        AppCompatResources.getDrawable(this, R.drawable.gradient_stroke)!!,
//        AlphaAnimation(.1f, .6f)
//    )
//
//    loading.register(this)
//
//    val binder = loading.create {
//        binding.text.skeleton(SkeletonTextView.TextWidth.LINES, 1.0).bind()
//    }
//
////        binder.show()
//}