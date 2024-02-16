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
        else if (district=="1") {
            val districtVotes : List<DistrictVotes> = individualVotesDataSource.getIndividualVotesFromAPIdistrict1()
            districtVotes.forEach{
                if (it.alpacaPartyId==id){
                    return it
                }
            }
        }
        else if (district=="2") {
            val districtVotes: List<DistrictVotes> =
                individualVotesDataSource.getIndividualVotesFromAPIdistrict2()
            districtVotes.forEach {
                if (it.alpacaPartyId == id) {
                    return it
                }
            }
        }
        throw NoSuchElementException("Party with id $id not found")
    }


}