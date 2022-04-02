package org.telegram.ui;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.BaseFragment;

public class ShowPasswordFragmentRunnable  implements Runnable {
    private BaseFragment parentFragment;
    private BaseFragment passwordFragment;
    private ActionBarLayout parentLayout;
    private int delay;

    public ShowPasswordFragmentRunnable(BaseFragment parentFragment, BaseFragment passwordFragment, int delay) {
        this.parentFragment = parentFragment;
        this.passwordFragment = passwordFragment;
        if (parentFragment != null) {
            this.parentLayout = parentFragment.getParentLayout();
        }
        this.delay = delay;
    }

    public void run() {
        AndroidUtilities.runOnUIThread(() -> {
            boolean result = false;
            if (parentFragment.getParentLayout() != null) {
                result = parentFragment.presentFragment(passwordFragment);
            } else {
                parentLayout.presentFragment(passwordFragment);
            }
            if (!result) {
                Utilities.globalQueue.postRunnable(new ShowPasswordFragmentRunnable(parentFragment, passwordFragment, delay), delay);
            }
        });
    }
}
