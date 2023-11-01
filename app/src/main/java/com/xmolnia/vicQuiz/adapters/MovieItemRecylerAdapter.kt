package com.xmolnia.vicQuiz.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.shape.CornerFamily
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.activities.MovieDetails
import com.xmolnia.vicQuiz.databinding.MovieItemRowBinding
import com.xmolnia.vicQuiz.models.MovieLoader

/**
 * Created by Osman Boy on 20.06.2021.
 **/


class MovieItemRecyclerAdapter(
    var context: Context,
    val activity: Activity,
    var categoryItemList: ArrayList<MovieLoader>,
) : RecyclerView.Adapter<MovieItemRecyclerAdapter.ItemViewHolder>() {




    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var row: MovieItemRowBinding = MovieItemRowBinding.bind(view)

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {


            val intent = Intent(activity, MovieDetails::class.java)
            val bundle = Bundle()

            bundle.putSerializable("actor", categoryItemList[absoluteAdapterPosition].actors)
            bundle.putSerializable("class", categoryItemList[absoluteAdapterPosition])
            intent.putExtra("actorsIntent", bundle)

            val pair3 = Pair.create<View, String>(row.itemPublished, "itemPublished")
            val pair4 = Pair.create<View, String>(row.itemPublishedBackground, "itemPublishedBackground")
            val pair1 = Pair.create<View, String>(row.itemImage, "movie_image")
            val pair2 = Pair.create<View, String>(row.itemName, "movie_name")


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair3, pair4, pair1, pair2

            )


            activity.startActivity(intent, options.toBundle())


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemRecyclerAdapter.ItemViewHolder {


        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item_row, parent, false))


    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {


        val shimmer = Shimmer.ColorHighlightBuilder().setBaseColor(ContextCompat.getColor(context, R.color.teal_200)).setBaseAlpha(0.0f).setHighlightAlpha(0.7f).setHighlightColor(ContextCompat.getColor(context, R.color.tertiary_text)).setDuration(1800).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setAutoStart(true).build()

        //create ShimmerDrawable()
        val shimmerDrawable = ShimmerDrawable()
        shimmerDrawable.setShimmer(shimmer)
        //set the ShapeAppearanceModel to CornerFamily.ROUNDED and the radius in pixels
        val radius: Float = dpToPx(context, 8).toFloat()
        holder.row.itemImage.shapeAppearanceModel = holder.row.itemImage.shapeAppearanceModel.toBuilder().setAllCorners(CornerFamily.ROUNDED, radius).build()


        //Glide Image Resources
        Glide.with(context).load(categoryItemList[position].movieImage).thumbnail(0.05f).placeholder(R.drawable.placeholder_small).placeholder(shimmerDrawable)
//            .error(R.drawable.oops_small_small)
            .into(holder.row.itemImage)

        //Movie Name Resources

        holder.row.itemName.text = categoryItemList[position].movieName

        //Published Label Resources
        when (categoryItemList[position].moviePublicKey) {

            MOVIE_PUB_TRON -> {
                holder.row.itemPublished.text = MOVIE_PUB_VICQUIZ_NAME
            }

            MOVIE_PUB_EXCLUSIVE -> {
                holder.row.itemPublished.text = MOVIE_PUB_EXCLUSIVE_NAME
                holder.row.itemPublished.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.purple_900))
            }

            MOVIE_PUB_NEW -> {
                holder.row.itemPublished.text = MOVIE_PUB_NEW_NAME
                holder.row.itemPublished.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.purple_600))

            }

            else -> {
                holder.row.itemPublished.visibility = View.INVISIBLE
                holder.row.ShimmerLayout.visibility = View.INVISIBLE
            }
        }
        holder.row.ageRestriction.text = categoryItemList[position].ageRestriction?.toString() + "+"


//        onMovieItemClickListener.onMovieClick()
    }


    override fun getItemCount(): Int {

        return categoryItemList.size
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }



}