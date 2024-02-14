package no.uio.ifi.in2000.sanderas.oblig2.ui.home

import kotlinx.serialization.Serializable


data class DistrictVotes(
    val district: District,
    val alpacaPartyId: String,
    val numberOfVotesForParty: Int
)

@Serializable
data class vote(val id : String)
@Serializable
data class voteList(val list : List<vote>)
