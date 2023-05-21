package com.bsuir.moviesearchsystem.sources.backend

import com.bsuir.moviesearchsystem.sources.model.auth.AuthSource
import com.bsuir.moviesearchsystem.sources.model.home.HomeSource

interface SourcesProvider {

    fun getAuthSource(): AuthSource
    fun getHomeSource(): HomeSource

}