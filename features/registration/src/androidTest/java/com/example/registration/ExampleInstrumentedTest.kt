package com.example.registration

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.registration.usecase.DataValidation
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val dataValidation = DataValidation()

    @Test
    fun testValidationName_ValidInput_ReturnsTrue() {
        // Arrange
        val name = "Иван"

        // Act
        val result = dataValidation.validationName(name)

        println(result)
        // Assert
        Assert.assertTrue(result)
    }

    @Test
    fun testValidationName_InvalidInput_ReturnsFalse() {
        // Arrange
        val name = "123"

        // Act
        val result = dataValidation.validationName(name)
        println(result)

        // Assert
        Assert.assertFalse(result)
    }

    @Test
    fun testValidationName_ExceptionThrown_ReturnsFalse() {
        // Arrange
        val name = "Ivan"

        // Act
        val result = dataValidation.validationName(name)

        println(result)

        // Assert
        Assert.assertFalse(result)
    }
}