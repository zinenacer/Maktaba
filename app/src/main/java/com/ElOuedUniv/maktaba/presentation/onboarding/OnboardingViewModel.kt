package com.ElOuedUniv.maktaba.presentation.onboarding

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ElOuedUniv.maktaba.data.di.DataModule
import androidx.core.content.edit

@HiltViewModel
class OnboardingViewModel @Inject constructor(public val hasCompletedOnboarding: SharedPreferences) : ViewModel() {
    fun onCompleteOnboarding() {
        hasCompletedOnboarding.edit { putBoolean("completed", true) }
    }
}
