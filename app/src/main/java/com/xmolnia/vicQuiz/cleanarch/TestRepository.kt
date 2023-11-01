package com.xmolnia.vicQuiz.cleanarch

import androidx.lifecycle.LiveData
import com.xmolnia.vicQuiz.models.Question

/**
 * Created by Osman Boy on 28.10.2021.
 **/
interface TestRepository {

    fun getTestList():LiveData<List<Question>>
    fun getTestPreviewList():LiveData<TestPreviewModel>
}