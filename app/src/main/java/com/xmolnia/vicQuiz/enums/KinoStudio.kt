package com.xmolnia.vicQuiz.enums

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.TAG

/**
 * Created by Osman Boy on 21.08.2021.
 * Возвращает логотип киностудий, и
 * сразу отоброжает на ImageView
 **/
enum class KinoStudio(@DrawableRes val resources: Int) {

    DreamWorksStudio(R.drawable.ic_studio_dreamworks),
    DisneyPlusStudio(R.drawable.ic_studio_apple_original),
    ParamountStudio(R.drawable.ic_studio_columbia_pictures),
    UniversalStudio(R.drawable.ic_studio_dc_comics),
    LionsgateStudio(R.drawable.ic_studio_disney),
    ColumbiaStudio(R.drawable.ic_studio_disneyplus),
    NetflixStudio(R.drawable.ic_studio_dreamworks),
    MarvelStudio(R.drawable.ic_studio_liongate),
    AmazonStudio(R.drawable.ic_studio_marvel),
    DisneyStudio(R.drawable.ic_studio_netflix),
    WarnerStudio(R.drawable.ic_studio_paramount),
    AppleStudio(R.drawable.ic_studio_sony),
    PixarStudio(R.drawable.ic_studio_universal),
    SonyStudio(R.drawable.ic_studio_warner),
    DcStudio(R.drawable.ic_studio_pixar),
    NBOStudio(R.drawable.ic_nbo_studio),
    BBCStudio(R.drawable.ic_bbc_logo);


    companion object {
        fun ImageView.getMovieStudioLogo(studio: String) {

            try {
                val studioName = valueOf(studio).resources
                this.setImageResource(studioName)
            } catch (e: Exception) {
                Log.d(TAG, "${e.message} ${e.suppressed}")
            }
        }
    }


}




