package com.xmolnia.vicQuiz.adapters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.activities.GenreActivity
import com.xmolnia.vicQuiz.databinding.MoviesGenreRowBinding
import com.xmolnia.vicQuiz.models.MovieLoader

/**
 * Created by Osman Boy on 04.07.2021.
 **/
class MoviesGenreAdapter(val context: Activity, val allMovies: ArrayList<MovieLoader>) : RecyclerView.Adapter<MoviesGenreAdapter.MainViewHolder>() {

    private lateinit var genreList: ArrayList<MovieLoader>
    val bundle = Bundle()

    private val genreImageList = listOf(R.drawable.ic_drama_genre, R.drawable.ic_comedy_genre, R.drawable.ic_anime_genre, R.drawable.ic_detective_genre, R.drawable.ic_horror_genre, R.drawable.ic_fentezy_genre, R.drawable.ic_thriller__genre, R.drawable.ic_prikluchenie_genre, R.drawable.ic_fantasy_genre, R.drawable.ic_family_genre)

    private val genreNameList = listOf("Драма", "Комедия", "Аниме", "Детектив", "Ужасы", "Фэнтези", "Триллер", "Приключение", "Фантастика", "Семейное")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        genreList = arrayListOf()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_genre_row, parent, false)
        return MainViewHolder(view)


    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.layout.genreImage.setImageResource(genreImageList[position])
        holder.layout.genreName.text = genreNameList[position]

    }

    override fun getItemCount(): Int {

        return if (allMovies.isNullOrEmpty()) {
            0
        }
        else {
            genreImageList.size

        }
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var layout: MoviesGenreRowBinding = MoviesGenreRowBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            genreList.clear()
            val intent = Intent(context, GenreActivity::class.java)
            bundle.putSerializable("genreList", getGenreSortedList(genreNameList[absoluteAdapterPosition], allMovies))
            intent.putExtra("genreList", bundle)
            intent.putExtra("genreName", genreNameList[absoluteAdapterPosition])
            context.startActivity(intent)
//            context.finish()


        }
    }


    private fun getGenreSortedList(genreType: String, list: ArrayList<MovieLoader>): ArrayList<MovieLoader> {

        genreList.addAll(list.filter { it.movieGenres?.contains(genreType) == true })
        return genreList

    }


}