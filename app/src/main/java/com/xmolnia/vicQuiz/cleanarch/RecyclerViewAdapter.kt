package com.xmolnia.vicQuiz.cleanarch

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.shape.CornerFamily
import com.xmolnia.vicQuiz.DETAIL
import com.xmolnia.vicQuiz.IMAGE_URI
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.TITLE
import com.xmolnia.vicQuiz.activities.Connection
import com.xmolnia.vicQuiz.activities.DisneyLand
import com.xmolnia.vicQuiz.activities.DisneyLand2
import com.xmolnia.vicQuiz.activities.FreeFire
import com.xmolnia.vicQuiz.activities.ImageQuiz
import com.xmolnia.vicQuiz.activities.MarvelMovie
import com.xmolnia.vicQuiz.activities.PartOne
import com.xmolnia.vicQuiz.activities.PartTwo
import com.xmolnia.vicQuiz.activities.PixelMarvel
import com.xmolnia.vicQuiz.activities.ZombieMovie
import com.xmolnia.vicQuiz.databinding.RowBinding


/**
 * Created by Osman Boy on 18.03.2021.
 **/
class RecyclerViewAdapter(
    private val context: Context,
    var titleInternet: ArrayList<String>,
    private var detailsInternet: ArrayList<String>,
    private var imagesUri: ArrayList<String>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val shimmer: Shimmer = Shimmer.ColorHighlightBuilder()
        .setBaseColor(ContextCompat.getColor(context, R.color.teal_200))
        .setBaseAlpha(0.7f).setHighlightAlpha(0.7f)
        .setHighlightColor(ContextCompat.getColor(context, R.color.purple_700))
        .setDuration(1800).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setAutoStart(true)
        .build()


    var title = arrayListOf(
        R.string.test_marvel_neon,
        R.string.test_marvel_movie,
        R.string.test_marvel_pixel,
        R.string.test_free_fire,
        R.string.test_zombies_test,
        R.string.disney_test,
        R.string.trend1_test,
        R.string.trend2_test,
        R.string.disney_test2,
        R.string.imagequiz_test
    )
    private var details = arrayListOf(
        R.string.detail1,
        R.string.detail2,
        R.string.detail3,
        R.string.detail4,
        R.string.detail5,
        R.string.detail6,
        R.string.detail7,
        R.string.detail7,
        R.string.detail6,
        R.string.detail8
    )
    private var images = intArrayOf(
        R.drawable.marvel_test_neon,
        R.drawable.marvel_movie,
        R.drawable.marvel_pixel,
        R.drawable.free_fire_test,
        R.drawable.zombie_test,
        R.drawable.disney,
        R.drawable.trendone,
        R.drawable.trendtwo,
        R.drawable.disney2,
        R.drawable.imageguiz_test
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val shimmerDrawable = ShimmerDrawable()
        shimmerDrawable.setShimmer(shimmer)



        when {
            position > 9 -> {
                holder.binding.titleTv.text = titleInternet[position - 10]
                holder.binding.descriptionTv.text = detailsInternet[position - 10]
                Glide.with(context).load("item.url").placeholder(shimmerDrawable)
                    .into(holder.binding.imageIv)

            }


            else -> {

                holder.binding.titleTv.setText(title[position])
                holder.binding.descriptionTv.setText(details[position])
                Glide.with(context).load(images[position]).into(holder.binding.imageIv)
            }
        }

        Log.d("MONSTER", "onBindViewHolder: $position")
//        Изменяет цвет одного итема, зависимо от его позиций(индекса)
        val drawableRes = if (position > 5) R.drawable.ic_circle_purple else R.drawable.ic_circle_blue
        holder.binding.status.background = ContextCompat.getDrawable(context,drawableRes)
    }


    override fun getItemCount(): Int {
        return title.size + titleInternet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {


        val binding = RowBinding.bind(itemView)

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)

            binding.imageIv.shapeAppearanceModel = binding.imageIv.shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, dpToPx(context)).build()

        }

        override fun onClick(v: View?) {

            when (absoluteAdapterPosition) {
                0 -> context.startActivity(Intent(context, MarvelTest::class.java));
                1-> context.startActivity(Intent(context, MarvelMovie::class.java))

                2 -> context.startActivity(Intent(context, PixelMarvel::class.java));

                3 -> context.startActivity(Intent(context, FreeFire::class.java));

                4 -> context.startActivity(Intent(context, ZombieMovie::class.java));

                5 -> context.startActivity(Intent(context, DisneyLand::class.java));

                6 -> context.startActivity(Intent(context, PartOne::class.java));

                7 -> context.startActivity(Intent(context, PartTwo::class.java));

                8 -> context.startActivity(Intent(context, DisneyLand2::class.java));

                9 -> context.startActivity(Intent(context, ImageQuiz::class.java));

                else -> {
                    val intent = Intent(context, Connection::class.java)
                    intent.putExtra(DETAIL, detailsInternet[absoluteAdapterPosition - 10])
                    intent.putExtra(IMAGE_URI, imagesUri[absoluteAdapterPosition - 10])
                    intent.putExtra(TITLE, titleInternet[absoluteAdapterPosition - 10])
                    context.startActivity(intent)
                }
            }
        }


    }

    private fun dpToPx(context: Context, dp: Int = 15): Float {
        return (dp * context.resources.displayMetrics.density)
    }

    interface OnTestClickListener{
        fun onTestClick(testId:Int)
    }


}