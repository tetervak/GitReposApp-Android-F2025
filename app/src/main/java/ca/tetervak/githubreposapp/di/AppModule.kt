package ca.tetervak.githubreposapp.di

import ca.tetervak.githubreposapp.data.remote.ReposApi
import ca.tetervak.githubreposapp.data.repository.PagedReposRepository
import ca.tetervak.githubreposapp.data.repository.RemotePagedReposRepository
import ca.tetervak.githubreposapp.ui.repos.ReposViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.github.com/search/"

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ReposApi> {
        get<Retrofit>().create(ReposApi::class.java)
    }
}

val repositoryModule = module {
    singleOf(::RemotePagedReposRepository) bind PagedReposRepository::class
}

val viewModelModule = module {
    viewModelOf(::ReposViewModel)
}

val appModule = module {
    includes(retrofitModule, repositoryModule, viewModelModule)
}

