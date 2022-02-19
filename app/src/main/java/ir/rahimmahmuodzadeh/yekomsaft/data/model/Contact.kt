package ir.rahimmahmuodzadeh.yekomsaft.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firstName: String,
    val lastName: String,
    val number: Int,
    val address: String?,
    val email: String?
) {
    @PrimaryKey
    val id: Int? = null
}