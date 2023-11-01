package com.xmolnia.vicQuiz.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.shape.CornerFamily
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.activities.MovieDetails
import com.xmolnia.vicQuiz.databinding.BannersMovieLayoutBinding
import com.xmolnia.vicQuiz.models.MovieLoader

/**
 * Created by Osman Boy on 11.05.2021.
 **/
class BannerMoviesPagerAdapter(val activity: Activity, private val categoryItemList: List<MovieLoader>) : PagerAdapter() {


    override fun getCount(): Int {
        return categoryItemList.size


    }

    override fun isViewFromObject(view: View, objec: Any): Boolean {
        return view == objec

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.banners_movie_layout, null)
        val bannerBinding: BannersMovieLayoutBinding = BannersMovieLayoutBinding.bind(view)

        val shimmer = Shimmer.ColorHighlightBuilder()
            .setBaseColor(ContextCompat.getColor(container.context, R.color.widget_color2))
            .setBaseAlpha(1f)
            .setHighlightAlpha(0.5f)
            .setHighlightColor(ContextCompat.getColor(container.context, R.color.grey2))
            .setDuration(1800)
            .setDropoff(0.4f)
            .setIntensity(0.1f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setAutoStart(true).build()

//        val loading = SkeletonLoading(
//            AppCompatResources.getDrawable(container.context,R.drawable.skeletonloading)!!,
//            AlphaAnimation(.1f, .6f)
//        )
//
//        loading.register(activity as AppCompatActivity)
//
//        val binder = loading.create {
//           bannerBinding.bannerImage.skeleton().bind()
//        }


        //create ShimmerDrawable()
        val shimmerDrawable = ShimmerDrawable()
        shimmerDrawable.setShimmer(shimmer)
        //set the ShapeAppearanceModel to CornerFamily.ROUNDED and the radius in pixels
        val radius: Float = dpToPx(container.context, 10).toFloat();
        bannerBinding.bannerImage.shapeAppearanceModel = bannerBinding.bannerImage
        .shapeAppearanceModel.toBuilder().setAllCorners(CornerFamily.ROUNDED, radius).build()


        Glide.with(container.context).load(categoryItemList[position].bannerURL).centerCrop()
            .thumbnail(0.05f).centerCrop()
            .placeholder(shimmerDrawable).into(bannerBinding.bannerImage)
        container.addView(view)

        bannerBinding.bannerImage.setOnClickListener {

            val intent = Intent(container.context, MovieDetails::class.java)
            val bundle = Bundle()
            bundle.putSerializable("actor", categoryItemList[position].actors)
            bundle.putSerializable("class", categoryItemList[position])
            intent.putExtra("actorsIntent", bundle)

            val pair1 = Pair.create<View, String>(bannerBinding.bannerImage, "bannerImage")

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1)


            activity.startActivity(intent, options.toBundle())
            activity.finish()
        }

        return view


    }

    override fun destroyItem(container: ViewGroup, position: Int, objec: Any) {
        container.removeView(objec as View)
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }
}



