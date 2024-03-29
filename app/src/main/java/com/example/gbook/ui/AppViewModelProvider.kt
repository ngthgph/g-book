package com.example.gbook.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gbook.GBookApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GBookViewModel(
                gBookApplication().container.networkRepository,
                gBookApplication().container.offlineRepository,
                gBookApplication().container.layoutPreferencesRepository
            )
        }
    }
}

fun CreationExtras.gBookApplication(): GBookApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GBookApplication)