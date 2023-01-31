package shouman.iss.bored.ui.activity.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import shouman.iss.bored.data.BoredRepositoryImpl
import shouman.iss.bored.data.entity.BoredDataEntity

data class HomePageUiState(
    val initialState: Boolean = true,
    val boredDataEntity: BoredDataEntity? = null,
    val isLoading: Boolean = false
    )

class HomePageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomePageUiState())
    val uiState: StateFlow<HomePageUiState> = _uiState.asStateFlow()

    fun getBoredEntity(){
        _uiState.update {homePageUiState ->
            homePageUiState.copy(isLoading = true)
        }

        GlobalScope.launch {
            val result = BoredRepositoryImpl().getBoredData()
            _uiState.update { homePageUiState ->
                homePageUiState.copy(
                    initialState = false,
                    boredDataEntity = result,
                    isLoading = false
                )
            }
        }
    }
}