package com.example.innomid;

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.logging.Logger;

public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
    final private static Logger log = Logger.getLogger(BottomNavigationBehavior.class.getName());

    public BottomNavigationBehavior() {
        super();
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull BottomNavigationView child, @NonNull View dependency) {
        if(dependency instanceof Snackbar.SnackbarLayout) {

            updateSnackbar(child, (Snackbar.SnackbarLayout)dependency);

        }



        return super.layoutDependsOn(parent, child, dependency);
    }

    private void updateSnackbar(View child, Snackbar.SnackbarLayout snackbarLayout) {

        if(snackbarLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)snackbarLayout.getLayoutParams();



            params.setAnchorId(child.getId());

            params.anchorGravity = Gravity.TOP;

            params.gravity = Gravity.TOP;

            snackbarLayout.setLayoutParams(params);

        }

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {




            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

            child.setTranslationY(Math.max(0f, Math.min(child.getHeight(), child.getTranslationY() + dy)));
    }

//    private void hideBottomNavigationView(BottomNavigationView view) {
//        view.animate().translationY(view.getHeight());
//    }
//
//    private void showBottomNavigationView(BottomNavigationView view) {
//        view.animate().translationY(0);
//    }


}