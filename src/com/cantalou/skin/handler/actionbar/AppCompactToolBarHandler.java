package com.cantalou.skin.handler.actionbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.cantalou.android.util.ReflectUtil;

/**
 * @author cantalou
 * @date 2016年1月28日 下午11:02:42
 */
public class AppCompactToolBarHandler extends ActionBarHandler {

    int collapseIcon;

    int navIcon;

    @SuppressWarnings("deprecation")
    @Override
    protected void reload(View view, Resources res) {
        super.reload(view, res);
        if (navIcon != 0) {
            ReflectUtil.invoke(view, "setNavigationIcon", new Class<?>[]{Drawable.class}, res.getDrawable(navIcon));
        }

        if (collapseIcon != 0) {
            Drawable dr = res.getDrawable(collapseIcon);
            ReflectUtil.set(view, "mCollapseIcon", dr);
            Object collapseButtonView = ReflectUtil.get(view, "mCollapseButtonView");
            if (collapseButtonView != null) {
                ReflectUtil.invoke(collapseButtonView, "setImageDrawable", new Class<?>[]{Drawable.class}, dr);
            }

        }
    }

    @Override
    public boolean parseAttr(Context context, AttributeSet attrs) {

        collapseIcon = getResourceId(attrs, "collapseIcon");
        if (collapseIcon != 0) {
            cacheKeyAndIdManager.registerDrawable(collapseIcon);
        }

        navIcon = getResourceId(attrs, "navigationIcon");
        if (navIcon != 0) {
            cacheKeyAndIdManager.registerDrawable(navIcon);
        }

        return super.parseAttr(context, attrs) || collapseIcon != 0 || navIcon != 0;
    }
}
