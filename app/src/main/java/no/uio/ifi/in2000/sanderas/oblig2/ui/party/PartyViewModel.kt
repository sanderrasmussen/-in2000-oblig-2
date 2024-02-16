package no.uio.ifi.in2000.sanderas.oblig2.ui.party

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.sanderas.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo

data class PartyUIState(
    val party: PartyInfo = PartyInfo("","","","","#FFFFFF","")//et tomt default object
)
class PartyViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val Id: String = checkNotNull(savedStateHandle["PartyId"])


    val repository : AlpacaPartiesRepository = AlpacaPartiesRepository()

    private val _partyUIState = MutableStateFlow(PartyUIState())
    val partyUIState: StateFlow<PartyUIState> = _partyUIState.asStateFlow()

    private var _ErrorStateFlow = MutableStateFlow(false)
    var ErrorStateFlow: StateFlow<Boolean> = _ErrorStateFlow

    init {
        viewModelScope.launch {
            try {
                _partyUIState.update {
                    it.copy(
                        party = repository.getPartyInfo(Id)
                    )
                }
            }
            catch (e :Exception){
                _ErrorStateFlow.update { true }
            }
        }
    }
}