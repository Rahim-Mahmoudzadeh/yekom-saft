package ir.rahimmahmuodzadeh.yekomsaft.utils

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val errorText = "درخواست با خطا مواجه شد دوباره امتحان کنید"
    val successText = "درخواست با موفقیت انجام شد!"
}