package com.xmolnia.vicQuiz.cleanarch

import androidx.lifecycle.LiveData

/**
 * Created by Osman Boy on 28.10.2021.
 **/
class GetTestPreviewListUseCase(private val repository: TestRepository) {
    fun getTestPreviewList():LiveData<TestPreviewModel>{
        return repository.getTestPreviewList()
    }
}