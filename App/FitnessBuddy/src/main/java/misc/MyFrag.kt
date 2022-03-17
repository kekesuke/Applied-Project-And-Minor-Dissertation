package misc

import androidx.fragment.app.Fragment

public open class MyFrag : Fragment() {
    public interface MyFragInterace{
        fun needsHide(id : Int)
    }
}
