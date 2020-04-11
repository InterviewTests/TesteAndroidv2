package pt.felipegouveia.bankapp.util

import androidx.test.espresso.idling.CountingIdlingResource
import pt.felipegouveia.bankapp.BuildConfig

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment(){
        if(BuildConfig.DEBUG){
            countingIdlingResource.increment()
        }
    }

    fun decrement(){
        if(BuildConfig.DEBUG){
            if(!countingIdlingResource.isIdleNow){
                countingIdlingResource.decrement()
            }
        }
    }
}