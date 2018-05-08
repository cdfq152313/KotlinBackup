package com.addweup.myotp

import com.lochbridge.oath.otp.HOTP
import com.lochbridge.oath.otp.MyTOTPBuilder
import org.junit.Test
import java.util.concurrent.TimeUnit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun hotp_gen() {
        val sharedSecretKey = "12345678901234567890"
        val key = sharedSecretKey.toByteArray(charset("US-ASCII"))
        val hotp = HOTP.key(key).digits(6).movingFactor(5).build()
        // prints "254676"
        System.out.println(hotp.value())
    }

    @Test
    fun totp_gen(){
        val sharedSecretKey = "20945be92ce689a40533f136179110aaHMJ1"
        val key = sharedSecretKey.toByteArray(charset("US-ASCII"))
        val totp = MyTOTPBuilder(key).timeStep(TimeUnit.SECONDS.toMillis(40)).digits(4).hmacSha256().build()
        println("Current: " + System.currentTimeMillis()/1000%40)
        println("TOTP: " + totp.value())
    }
}
