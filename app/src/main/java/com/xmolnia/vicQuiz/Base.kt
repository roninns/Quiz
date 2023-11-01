package com.xmolnia.vicQuiz

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.widget.TextView
import android.widget.Toast
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.xmolnia.vicQuiz.models.User


/**
 * Created by Osman Boy on 22.03.2021.
 **/
object Base : BaseActivity() {


    private lateinit var shareds: SharedPreferences
    fun initial(context: Context) {
        shareds = context.getSharedPreferences("night", 0)
    }


    fun getNightTheme(): Boolean {
        return shareds.getBoolean("night_theme", true)

    }

    fun nightThemeActivated() {
        val edit: SharedPreferences.Editor = shareds.edit()
        edit.putBoolean("night_theme", true)
        edit.apply()
    }

    fun nightThemeDisActivated() {
        val edit: SharedPreferences.Editor = shareds.edit()
        edit.putBoolean("night_theme", false)
        edit.apply()
    }


    /* fun setLocale(context: Context,localeName: String) {
        val edit: SharedPreferences.Editor = shareds.edit()
        edit.putString("Language",localeName)
        edit.apply()
        locale = Locale(localeName)
        val dm = context.resources.displayMetrics
        val conf = context.resources.configuration
        conf.setLocale(locale)
        resources.updateConfiguration(conf, dm)
    }*/


    /*val loading = SkeletonLoading(
        AppCompatResources.getDrawable(requireContext(), R.drawable.skeletonloading)!!, AlphaAnimation(.1f, .6f)
    )

    loading.register(this)

    val binder = loading.create {
        movie.genreTitle.skeleton(
            SkeletonTextView.TextWidth.LINES, 1.0).bind()
        //            movie.bannerViewPager.skeleton().bind()
        movie.allMoviesTitle.skeleton(
            SkeletonTextView.TextWidth.LINES, 1.0).bind()
    }*/

    fun putProfilePhoto(imageUri: String) {
        val editor: SharedPreferences.Editor = shareds.edit()
        editor.putString("profile", imageUri)
        editor.apply()
    }

    fun getProfilePhoto(): String {
        return shareds.getString("profile", DEFAULT).toString()
    }


    fun putValues(key: String, values: String) {
        val editor: SharedPreferences.Editor = shareds.edit()
        editor.putString(key, values)
        editor.apply()
    }


    fun getValues(key: String): String {
        return shareds.getString(key, DEFAULT).orEmpty()
    }


    fun putAnswer(key: String, values: Int) {
        val editor: SharedPreferences.Editor = shareds.edit()
        editor.putInt(key, values)
        editor.apply()
    }

    fun getAnswer(key: String): Int {
        return shareds.getInt(key, DEFAULT_INT)
    }


    val mDataBase: DatabaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY)


    fun putRating() {
        mDataBase.child(getValues(USER_ID)).setValue(User(
            getValues(GAMER_NAME),
            getAnswer(MARVEL_TEST_CORRECT_ANSWERS),
            getAnswer(MARVEL_MOVIE_CORRECT_ANSWERS),
            getAnswer(MARVEL_PIXEL_CORRECT_ANSWERS),
            getAnswer(FREE_FIRE_CORRECT_ANSWERS),
            getAnswer(ZOMBIE_TEST_CORRECT_ANSWERS),
            getAnswer(DISNEYLAND_CORRECT_ANSWERS),
            getAnswer(PART1_CORRECT_ANSWERS),
            getAnswer(PART2_CORRECT_ANSWERS),
            getAnswer(DISNEYLAND2_CORRECT_ANSWERS)

            ))
    }

    //Shimmer for Glide

}

fun Toast.customToastForMain(activity: Activity) : Toast{

    val layout = activity.layoutInflater.inflate(R.layout.toast_for_mainactivty, activity.findViewById(R.id.toast_layout_root))
    return this.apply {
        duration = Toast.LENGTH_SHORT
        view = layout
    }


   /* this.duration;
    this.view = layout
    return this*/
}

fun customToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    val view = toast.view!!
    view.background.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.SRC_IN)
    val text = view.findViewById(android.R.id.message) as TextView
    text.setTextColor(Color.parseColor("#FFFFFF"))

    toast.show()
}


fun soundPlayCorrect(context: Context) {
    val play: MediaPlayer = MediaPlayer.create(context, R.raw.rigth_answer)
    play.start()
}

fun soundPlayWrong(context: Context) {
    val play: MediaPlayer = MediaPlayer.create(context, R.raw.wrong_answer)
    play.start()


}

private fun shimmerGetRes(shimmerColor: Int, shimmerBackground: Int): Shimmer {

    return Shimmer.ColorHighlightBuilder()

        .setHighlightColor(BaseActivity.getAppResources().getColor(shimmerColor))
        //background
        .setBaseColor(BaseActivity.getAppResources().getColor(shimmerBackground))// The attributes for a ShimmerDrawable is set by this builder
        .setDuration(1800) // how long the shimmering animation takes to do one full sweep
        .setBaseAlpha(0.5f) //the alpha of the underlying children
        .setHighlightAlpha(0.6f) // the shimmer alpha amount
        //прозрачность палочки мерцания
        .setIntensity(0.1f)
        //размер палочки мерцания
        .setDropoff(0.5f).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setAutoStart(true).build()
}


// This is the placeholder for the imageView
fun shimmerDrawable(shimmerColor: Int, shimmerBackground: Int): ShimmerDrawable {
    return ShimmerDrawable().apply {
        setShimmer(shimmerGetRes(shimmerColor, shimmerBackground))
    }


}















