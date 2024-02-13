package no.uio.ifi.in2000.sanderas.oblig2.model.alpacas

import kotlinx.serialization.Serializable

@Serializable
data class PartyInfo(
    val id : String ,
    val name: String,
    val leader: String,
    val img: String,
    val color: String,
    val description: String)

@Serializable
data class PartiesInfo(val parties: List<PartyInfo>)

