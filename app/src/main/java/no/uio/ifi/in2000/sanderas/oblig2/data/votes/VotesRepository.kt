package no.uio.ifi.in2000.sanderas.oblig2.data.votes

import no.uio.ifi.in2000.sanderas.oblig2.ui.home.DistrictVotes

class VotesRepository {
    val aggregatedVotesDataSource = AggregatedVotesDataSource()
    val individualVotesDataSource = IndividualVotesDataSource()

    suspend fun getPartyVoteFromDistrict(id:String,district: String): DistrictVotes {


        if (district=="3"){
            val districtVotes : List<DistrictVotes> = aggregatedVotesDataSource.getAggregatedVotesFromAPI()
            districtVotes.forEach{
                if (it.alpacaPartyId==id){
                    return it
                }
            }
        }
        else {
            val districtVotes : List<DistrictVotes> = individualVotesDataSource.getIndividualVotesFromAPI()
            districtVotes.forEach{
                if (it.alpacaPartyId==id){
                    return it
                }
            }
        }
        throw NoSuchElementException("Party with id $id not found")
    }


}