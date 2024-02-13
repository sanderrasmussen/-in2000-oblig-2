package no.uio.ifi.in2000.sanderas.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.sanderas.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartiesInfo


data class PartiesUIState(
    val parties: PartiesInfo = PartiesInfo(listOf()),
)
class HomeScreenViewModel : ViewModel() {

    val repository : AlpacaPartiesRepository = AlpacaPartiesRepository()

    private val _partiesUIState = MutableStateFlow(PartiesUIState())
    val partiesUIState: StateFlow<PartiesUIState> = _partiesUIState.asStateFlow()

    init {
        viewModelScope.launch {
            _partiesUIState.update {
                it.copy(
                    parties = repository.getPartyInfoList()
                )
            }
        }
    }
}
