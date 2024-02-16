package no.uio.ifi.in2000.sanderas.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.sanderas.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.sanderas.oblig2.data.alpacas.PartiesResults
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartiesInfo


data class PartiesUIState(
    val parties: PartiesInfo = PartiesInfo(listOf()),
)
data class VotesUIState(
    val votes: PartiesResults = PartiesResults(listOf())
)
class HomeScreenViewModel : ViewModel() {

    val repository : AlpacaPartiesRepository = AlpacaPartiesRepository()

    private val _partiesUIState = MutableStateFlow(PartiesUIState())
    val partiesUIState: StateFlow<PartiesUIState> = _partiesUIState.asStateFlow()

    private val _votesUIState = MutableStateFlow(VotesUIState())
    val votesUIState : StateFlow<VotesUIState> = _votesUIState.asStateFlow()

    private var _ErrorStateFlow = MutableStateFlow(false)
    var ErrorStateFlow: StateFlow<Boolean> = _ErrorStateFlow


    fun byttDistrict(district : String) {
        viewModelScope.launch {
            try {
                _votesUIState.update {
                    it.copy(
                        votes = repository.getVotesFromDistrict(
                            district,
                            listOf("1", "2", "3", "4")
                        )
                    )
                }
            } catch (e: Exception) {
                _ErrorStateFlow.update { true }
            }

        }
    }
        init {
            viewModelScope.launch {
                try {
                    _partiesUIState.update {
                        it.copy(
                            parties = repository.getPartyInfoList()
                        )
                    }
                    _votesUIState.update {
                        it.copy(
                            votes = repository.getVotesFromDistrict("All", listOf("1", "2", "3", "4")
                        ))
                    }
                }
                catch (e :Exception){
                    _ErrorStateFlow.update { true }
                }
            }
        }
    }


