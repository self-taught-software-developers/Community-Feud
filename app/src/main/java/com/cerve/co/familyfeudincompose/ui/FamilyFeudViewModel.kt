package com.cerve.co.familyfeudincompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.co.familyfeudincompose.data.FamilyFeudRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamilyFeudViewModel @Inject constructor(
    private val familyFeudRepository: FamilyFeudRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            familyFeudRepository.getQuestionCards()
        }
    }

}