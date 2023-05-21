package com.bsuir.moviesearchsystem.sources.backend

import com.bsuir.moviesearchsystem.sources.model.auth.RetrofitAuthSource
import com.bsuir.moviesearchsystem.sources.model.auth.AuthSource
import com.bsuir.moviesearchsystem.sources.model.home.HomeSource
import com.bsuir.moviesearchsystem.sources.model.home.RetrofitHomeSource

class RetrofitSourcesProvider(
    private val config: RetrofitConfig
) : SourcesProvider{

    override fun getAuthSource(): AuthSource {
        return RetrofitAuthSource(config)
    }

    override fun getHomeSource(): HomeSource {
        return RetrofitHomeSource(config)
    }


}