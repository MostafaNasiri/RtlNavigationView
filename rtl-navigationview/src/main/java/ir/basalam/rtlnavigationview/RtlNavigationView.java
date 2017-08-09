package ir.basalam.rtlnavigationview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v7.view.SupportMenuInflater;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class RtlNavigationView extends NavigationView {
    private OnNavigationItemSelectedListener navigationItemSelectedListener;

    public RtlNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, new int[]{R.attr.rtl_menu});
        // Check if rtl_menu attribute has value
        if (array.getResourceId(0, -1) != -1) {
            inflateRtlMenu(context, array.getResourceId(0, -1));
            array.recycle();
        }
    }

    @Override
    public void setNavigationItemSelectedListener(
            @Nullable OnNavigationItemSelectedListener listener) {
        navigationItemSelectedListener = listener;
    }

    /**
     * Inflate a menu resource into this navigation view.
     *
     * <p>Existing items in the menu will be removed.</p>
     *
     * @param resId ID of a menu resource to inflate
     */
    public void inflateRtlMenu(Context context, @MenuRes int resId) {
        getMenu().clear();
        Menu menu = new NavigationMenu(context);
        new SupportMenuInflater(context).inflate(resId, menu);
        createRtlMenu(menu);
    }

    private void createRtlMenu(final Menu rtlMenu) {
        for (int i = 0; i < rtlMenu.size(); i++) {
            // Add an empty MenuItem to the NavigationView for each of the items in the rtlMenu
            final MenuItem addedMenuItem = getMenu().add(null);
            final MenuItem currentRtlMenuItem = rtlMenu.getItem(i);
            
            boolean isGroup = currentRtlMenuItem.hasSubMenu();
            if (isGroup) {
                // Set group title layout if current rtlMenu item has a submenu
                addedMenuItem.setActionView(R.layout.list_item_rtl_navigationview_group_title);
                // We don't want group title to respond to user clicks
                addedMenuItem.setEnabled(false);

                TextView tvGroupTitle = addedMenuItem.getActionView()
                        .findViewById(R.id.list_item_navigationview_group_title_textview);
                tvGroupTitle.setText(currentRtlMenuItem.getTitle());

                createRtlMenu(currentRtlMenuItem.getSubMenu());
            } else {
                // Set normal menu item layout 
                addedMenuItem.setActionView(R.layout.list_item_rtl_navigationview);
                addedMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (navigationItemSelectedListener != null) {
                            navigationItemSelectedListener
                                    .onNavigationItemSelected(currentRtlMenuItem);
                        }
                        return false;
                    }
                });

                TextView tvTitle = addedMenuItem.getActionView()
                        .findViewById(R.id.list_item_rtl_navigationview_title_textview);
                ImageView ivIcon = addedMenuItem.getActionView()
                        .findViewById(R.id.list_item_rtl_navigationview_icon_imageview);

                tvTitle.setText(currentRtlMenuItem.getTitle());
                Drawable itemIcon = currentRtlMenuItem.getIcon();
                itemIcon.setColorFilter(getResources().getColor(R.color.colorListItemIcon),
                        PorterDuff.Mode.SRC_IN);
                ivIcon.setImageDrawable(itemIcon);
            }
        }
    }
}
