package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;

public class ChatScrimPopupContainerLayout extends LinearLayout {

    private ReactionsContainerLayout reactionsLayout;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout popupWindowLayout;
    private View bottomView;
    private int maxHeight;

    public ChatScrimPopupContainerLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (maxHeight != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        }
        if (reactionsLayout != null && popupWindowLayout != null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int reactionsLayoutTotalWidth = reactionsLayout.getTotalWidth();
            View menuContainer = popupWindowLayout.getSwipeBack() != null ? popupWindowLayout.getSwipeBack().getChildAt(0) : popupWindowLayout.getChildAt(0);
            int maxReactionsLayoutWidth = menuContainer.getMeasuredWidth() + AndroidUtilities.dp(16) + AndroidUtilities.dp(16) + AndroidUtilities.dp(36);
            if (maxReactionsLayoutWidth > getMeasuredWidth()) {
                maxReactionsLayoutWidth = getMeasuredWidth();
            }
            if (reactionsLayoutTotalWidth > maxReactionsLayoutWidth) {
                int maxFullCount = ((maxReactionsLayoutWidth - AndroidUtilities.dp(16)) / AndroidUtilities.dp(36)) + 1;
                int newWidth = maxFullCount * AndroidUtilities.dp(36) + AndroidUtilities.dp(16) - AndroidUtilities.dp(8);
                if (newWidth > reactionsLayoutTotalWidth || maxFullCount == reactionsLayout.getItemsCount()) {
                    newWidth = reactionsLayoutTotalWidth;
                }
                reactionsLayout.getLayoutParams().width = newWidth;
            } else {
                reactionsLayout.getLayoutParams().width = LayoutHelper.WRAP_CONTENT;
            }
            int widthDiff = 0;
            if (popupWindowLayout.getSwipeBack() != null) {
                widthDiff = popupWindowLayout.getSwipeBack().getMeasuredWidth() - popupWindowLayout.getSwipeBack().getChildAt(0).getMeasuredWidth();
            }
            if (reactionsLayout.getLayoutParams().width != LayoutHelper.WRAP_CONTENT && reactionsLayout.getLayoutParams().width + widthDiff > getMeasuredWidth() && popupWindowLayout.getSwipeBack() != null && popupWindowLayout.getSwipeBack().getMeasuredWidth() > getMeasuredWidth()) {
                widthDiff = getMeasuredWidth() - reactionsLayout.getLayoutParams().width + AndroidUtilities.dp(8);
            }
            if (widthDiff < 0) {
                widthDiff = 0;
            }
            ((LayoutParams) reactionsLayout.getLayoutParams()).rightMargin = widthDiff;
            if (bottomView != null) {
                if (popupWindowLayout.getSwipeBack() != null) {
                    ((LayoutParams) bottomView.getLayoutParams()).rightMargin = widthDiff + AndroidUtilities.dp(36);
                } else {
                    ((LayoutParams) bottomView.getLayoutParams()).rightMargin = AndroidUtilities.dp(36);
                }
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void applyViewBottom(FrameLayout bottomView) {
        this.bottomView = bottomView;
    }

    public void setReactionsLayout(ReactionsContainerLayout reactionsLayout) {
        this.reactionsLayout = reactionsLayout;
    }

    public void setPopupWindowLayout(ActionBarPopupWindow.ActionBarPopupWindowLayout popupWindowLayout) {
        this.popupWindowLayout = popupWindowLayout;
        popupWindowLayout.setOnSizeChangedListener(() -> {
            if (bottomView != null) {
                bottomView.setTranslationY(popupWindowLayout.getVisibleHeight() - popupWindowLayout.getMeasuredHeight());
            }
        });
        if (popupWindowLayout.getSwipeBack() != null) {
            popupWindowLayout.getSwipeBack().addOnSwipeBackProgressListener((layout, toProgress, progress) -> {
                if (bottomView != null) {
                    bottomView.setAlpha(1f - progress);
                }
            });
        }
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
}
