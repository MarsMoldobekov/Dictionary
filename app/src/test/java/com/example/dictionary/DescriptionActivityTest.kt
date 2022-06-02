package com.example.dictionary

import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import com.example.dictionary.frameworks.ui.DescriptionActivity
import com.example.dictionary.frameworks.utils.EquilateralImageView
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class DescriptionActivityTest {
    private lateinit var scenario: ActivityScenario<DescriptionActivity>

    @Before
    fun `set up`() {
        scenario = ActivityScenario.launch(DescriptionActivity::class.java)
    }

    @Test
    fun `check if activity is not null`() {
        scenario.onActivity { assertNotNull(it) }
    }

    @Test
    fun `check if activity is resumed`() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun `check if corresponding elements of Activity exist`() {
        scenario.onActivity {
            val descriptionHeader = it.findViewById<TextView>(R.id.description_header)
            val descriptionTextView = it.findViewById<TextView>(R.id.description_textview)
            val descriptionImageView = it.findViewById<EquilateralImageView>(R.id.description_imageview)

            assertNotNull(descriptionHeader)
            assertNotNull(descriptionTextView)
            assertNotNull(descriptionImageView)
        }
    }

    @Test
    fun `check if corresponding elements of Activity are visible`() {
        scenario.onActivity {
            val descriptionHeader = it.findViewById<TextView>(R.id.description_header)
            val descriptionTextView = it.findViewById<TextView>(R.id.description_textview)
            val descriptionImageView = it.findViewById<EquilateralImageView>(R.id.description_imageview)

            assertEquals(View.VISIBLE, descriptionHeader.visibility)
            assertEquals(View.VISIBLE, descriptionTextView.visibility)
            assertEquals(View.VISIBLE, descriptionImageView.visibility)
        }
    }

    @Test
    fun `check if textViews works`() {
        scenario.onActivity {
            val descriptionHeader = it.findViewById<TextView>(R.id.description_header)
            val descriptionTextView = it.findViewById<TextView>(R.id.description_textview)

            val header = "header"
            val description = "description"

            descriptionHeader.text = header
            descriptionTextView.text = description

            assertEquals(header, descriptionHeader.text)
            assertEquals(description, descriptionTextView.text)
        }
    }

    private fun EquilateralImageView.hasDrawable(resId: Int): Boolean =
        Shadows.shadowOf(this.drawable).createdFromResId == resId

    @Test
    fun `check if imageView works`() {
        scenario.onActivity {
            val imageView = it.findViewById<EquilateralImageView>(R.id.description_imageview)
            assertTrue(imageView.hasDrawable(R.drawable.ic_no_photo_vector))
        }
    }

    @Test
    fun `check supportActionBar`() {
        scenario.onActivity {
            assertNotNull(it.supportActionBar)
        }
    }

    @Test
    fun activityCreateIntent_NotNull() {
        val word = "word"
        val description = "description"
        val url = "url"

        scenario.onActivity {
            val intent = DescriptionActivity.getIntent(it.applicationContext, word, description, url)
            val bundle = intent.extras

            assertNotNull(intent)
            assertNotNull(bundle)

            assertEquals(word, bundle?.getString(DescriptionActivity.WORD_EXTRA, ""))
            assertEquals(description, bundle?.getString(DescriptionActivity.DESCRIPTION_EXTRA, ""))
            assertEquals(url, bundle?.getString(DescriptionActivity.URL_EXTRA, ""))
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}