package ir.rahimmahmuodzadeh.yekomsaft.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Contact(
    var firstName: String,
    var lastName: String,
    var number: String,
    var address: String?,
    var email: String?
):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}