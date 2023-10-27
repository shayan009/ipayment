package  com.onetechsol.ipayment.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox

class CustomCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatCheckBox(context, attrs, defStyleAttr) {
    private var listener: CompoundButton.OnCheckedChangeListener? = null

    override fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener?) {
        this.listener = listener
        super.setOnCheckedChangeListener(listener)
    }

    fun setChecked(checked: Boolean, alsoNotify: Boolean) {
        if (!alsoNotify) {
            super.setOnCheckedChangeListener(null)
            super.setChecked(checked)
            super.setOnCheckedChangeListener(listener)
            return
        }
        super.setChecked(checked)
    }

    fun toggle(alsoNotify: Boolean) {
        if (!alsoNotify) {
            super.setOnCheckedChangeListener(null)
            super.toggle()
            super.setOnCheckedChangeListener(listener)
        }
        super.toggle()
    }
}