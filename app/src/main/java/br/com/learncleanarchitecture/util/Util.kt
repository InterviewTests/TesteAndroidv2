package br.com.learncleanarchitecture.util

import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class ArrayEmptyException(var msg: String? = "ArrayEmptyException") : RuntimeException()

inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}

// Inline function to create Parcel Creator
inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
    object : Parcelable.Creator<T> {
        override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
        override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
    }

// custom readParcelable to avoid reflection
fun <T : Parcelable> Parcel.readParcelable(creator: Parcelable.Creator<T>): T? {
    if (readString() != null) return creator.createFromParcel(this) else return null
}
