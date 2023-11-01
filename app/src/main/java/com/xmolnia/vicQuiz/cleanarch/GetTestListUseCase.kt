package com.xmolnia.vicQuiz.cleanarch

import androidx.lifecycle.LiveData
import com.xmolnia.vicQuiz.models.Question

/**
 * Created by Osman Boy on 28.10.2021.
 **/
class GetTestListUseCase(private val repository: TestRepository) {
    fun getTestListTestList(): LiveData<List<Question>> {
        return repository.getTestList()
    }
}