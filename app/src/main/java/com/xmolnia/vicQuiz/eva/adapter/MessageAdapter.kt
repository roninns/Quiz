package com.xmolnia.vicQuiz.eva.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.activities.MovieDetails
import com.xmolnia.vicQuiz.databinding.AiMsgBinding
import com.xmolnia.vicQuiz.databinding.UserMsgBinding
import com.xmolnia.vicQuiz.eva.model.MessageModal
import java.util.*

/**
 * Created by Osman Boy on 11.07.2021.
 **/
class MessageAdapter(
    val context: Context,
    private val messageModalArrayList: ArrayList<MessageModal>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //User Message Row Inflater ViewBinding
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layout = UserMsgBinding.bind(view)
    }


    //AI Message Row Inflater ViewBinding
    inner class AIViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var layout = AiMsgBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (messageModalArrayList[absoluteAdapterPosition].movieLoader != null) {

                val intent = Intent(context, MovieDetails::class.java)
                val bundle = Bundle()
                bundle.putSerializable(
                    "class",
                    messageModalArrayList[absoluteAdapterPosition].movieLoader
                )
                bundle.putSerializable(
                    "actor",
                    messageModalArrayList[absoluteAdapterPosition].movieLoader?.actors
                )
                intent.putExtra("actorsIntent", bundle)
                context.startActivity(intent)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> {
                UserViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.user_msg, parent, false)
                )

            }

            1 -> {

                AIViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.ai_msg, parent, false)
                )
            }

            else -> {
                UserViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.ai_msg, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        // below line of code is to set position.
        return when (messageModalArrayList[position].sender) {
            "user" -> 0
            "bot" -> 1
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return messageModalArrayList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // this method is use to set data to our layout file.
        val (message, sender, sd) = messageModalArrayList[position]
        when (sender) {
            "user" ->
                // below line is to set the text to our text view of user layout
                (holder as UserViewHolder).layout.userMessage.text = message

            "bot" -> {
                // below line is to set the text to our text view of bot layout

                when (messageModalArrayList[position].responseType) {
                    1 -> {
                        (holder as AIViewHolder)
                        holder.layout.aiMessage.text =
                            messageModalArrayList[position].movieLoader?.movieDescription
                        holder.layout.movieBanner.visibility = View.VISIBLE
                        Glide.with(context)
                            .load(messageModalArrayList[position].movieLoader?.bannerURL)
                            .centerCrop().into(holder.layout.movieBanner)

                        holder.layout.movieName.text =
                            messageModalArrayList[position].movieLoader?.movieName
                        holder.layout.movieName.visibility = View.VISIBLE


                    }

                    0 -> {
                        (holder as AIViewHolder)
                        holder.layout.aiMessage.text = Html.fromHtml(message)
                        holder.layout.movieBanner.visibility = View.GONE
                        holder.layout.movieName.visibility = View.GONE


                    }
                }
            }
        }
    }
}