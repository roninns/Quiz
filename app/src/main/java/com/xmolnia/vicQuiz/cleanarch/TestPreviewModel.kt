package com.xmolnia.vicQuiz.cleanarch

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.xmolnia.vicQuiz.models.Question

/**
 * Created by Osman Boy on 28.10.2021.
 **/
data class TestPreviewModel(
    @StringRes
    val title: Int,
    @StringRes
    val detail: Int,
    @DrawableRes
    val image: Int,
    val questions: List<Question>
)
