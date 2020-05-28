package br.com.dpassos.bankandroid;

import android.view.View;
import java.lang.reflect.Field;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseView extends AppCompatActivity {

    /**
     * This method override load views automatically
     * from XML references.
     * The contract needs basically xml field and Activity field
     * has same name.
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        injectViews();
    }

    private void injectViews() {
        for(Field field : getClass ().getDeclaredFields()) {
            boolean isView = View.class.isAssignableFrom(field.getType());
            if(isView) {
                try {
                    int id = R.id.class.getDeclaredField(field.getName()).getInt(this);
                    View v = findViewById(id);
                    field.set(this, v);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * avoid execution If the view is not available
     * @param runnable
     */
    protected void runOnUiSafe(Runnable runnable) {
        if(this.isFinishing()) return;
        runOnUiThread(runnable);
    }
}
