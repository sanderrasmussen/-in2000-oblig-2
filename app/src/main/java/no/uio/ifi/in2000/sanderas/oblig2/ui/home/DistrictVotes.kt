package no.uio.ifi.in2000.sanderas.oblig2.ui.home


data class DistrictVotes(
    val district: District,
    val alpacaPartyId: String,
    val numberOfVotesForParty: Int
)
