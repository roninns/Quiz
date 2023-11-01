package com.xmolnia.vicQuiz.cleanarch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.xmolnia.vicQuiz.adapters.Constants
import com.xmolnia.vicQuiz.models.Question

/**
 * Created by Osman Boy on 28.10.2021.
 **/
class TestRepositoryImpl:TestRepository {

    var list =MutableLiveData(Constants.testStorage)

    override fun getTestList(): LiveData<List<Question>> {
        return Transformations.map(list){
            it.flatMap { it.questions }
        }
    }

    override fun getTestPreviewList(): LiveData<TestPreviewModel> {
        TODO("Not yet implemented")
    }
}