package com.xmolnia.vicQuiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.databinding.ActorsRowBinding
import com.xmolnia.vicQuiz.models.Actor

/**
 * Created by Osman Boy on 20.07.2021.
 **/
class ActorsAdapter(val context: Context, val actors: ArrayList<Actor>) : RecyclerView.Adapter<ActorsAdapter.MainViewHolder>() {




    private val actorsNameList = mutableListOf<String>(
    )




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.actors_row, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        for(i in actors){
            val(a,b)= i.name?.split(" ")!!
            actorsNameList.add("$a\n$b")
        }

        Glide.with(context).load(actors[position].photo).into(holder.layout.actorPhoto)

        holder.layout.actorName.text = actorsNameList[position]
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ActorsRowBinding = ActorsRowBinding.bind(itemView)




    }


}


