package ca.tetervak.githubreposapp.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ca.tetervak.githubreposapp.data.remote.Repo
import ca.tetervak.githubreposapp.data.repository.PagedReposRepository
import kotlinx.coroutines.flow.Flow

class ReposViewModel(
    pagedReposRepository: PagedReposRepository
) : ViewModel() {

    val repos: Flow<PagingData<Repo>> =
        pagedReposRepository.getReposPager().flow.cachedIn(viewModelScope)

}
