package com.bsuir.moviesearchsystem

import android.content.Context
import androidx.fragment.app.Fragment
import com.bsuir.moviesearchsystem.app.repository.HomeRepository
import com.bsuir.moviesearchsystem.app.repository.UserRepository
import com.bsuir.moviesearchsystem.app.screens.Navigator
import com.bsuir.moviesearchsystem.app.setting.AppSettings
import com.bsuir.moviesearchsystem.sources.SourceProviderHolder
import com.bsuir.moviesearchsystem.sources.backend.SourcesProvider
import com.bsuir.moviesearchsystem.sources.model.auth.AuthSource
import com.bsuir.moviesearchsystem.sources.model.home.HomeSource
import com.bsuir.recreation_facility.app.model.setting.SharedPreferencesAppSettings

object Singletons {

    private lateinit var appContext: Context

    private val sourcesProvider: SourcesProvider by lazy {
        SourceProviderHolder.sourcesProvider
    }

    val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(appContext)
    }

    // source
    private val userSource: AuthSource by lazy {
        sourcesProvider.getAuthSource()
    }

    private val homeSource: HomeSource by lazy {
        sourcesProvider.getHomeSource()
    }

    // repository
    val userRepository: UserRepository by lazy {
        UserRepository(
            userSource = userSource,
            appSettings = appSettings
        )
    }

    val homeRepository: HomeRepository by lazy {
        HomeRepository(
            homeSource = homeSource,
            appSettings = appSettings
        )
    }

    fun init(appContext: Context) {
        Singletons.appContext = appContext
    }

    fun Fragment.navigator() = requireActivity() as Navigator
}