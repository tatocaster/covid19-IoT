package me.tatocaster.core_android_testing.view

import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity

/**
 * Used as container to test fragments in isolation with Espresso
 */
@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentActivity : AppCompatActivity()