package es.dmoral.toasty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * This file is part of Toasty.
 * <p>
 * Toasty is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Toasty is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Toasty.  If not, see <http://www.gnu.org/licenses/>.
 */

final class ToastyUtils {
    private ToastyUtils() {
    }

    static Drawable tintIcon(@NonNull Drawable drawable, @ColorInt int tintColor) {
        drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    static Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.toast_frame);
        return tintIcon(toastDrawable, tintColor);
    }

    static void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(drawable);
        else
            view.setBackgroundDrawable(drawable);
    }

    static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        return AppCompatResources.getDrawable(context, id);
    }

    static int getColor(@NonNull Context context, @ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    /**
     * Fix 2 bugs in {@link Toast}.
     *
     * <li>
     *     <ul>It doesn't show when the notification permission is disabled.</ul>
     *     <ul>{@link android.view.WindowManager.BadTokenException} on {@link Build.VERSION_CODES#N_MR1}.</ul>
     * </li>
     *
     * @see <a href="https://blog.csdn.net/qq331710168/article/details/85320098">Solve the problem that Toast doesn't show when the notification permission is disabled (Simplified Chinese)</a>
     * @see <a href="https://www.jianshu.com/p/c8e00943afc9">Android 7.X Toast Bug (Simplified Chinese)</a>
     */
    static void toastBugFix() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            try {
                @SuppressWarnings("JavaReflectionMemberAccess")
                @SuppressLint({"DiscouragedPrivateApi", "PrivateApi"})
                Method getService = Toast.class.getDeclaredMethod("getService");
                getService.setAccessible(true);
                final Object iNotificationManager = getService.invoke(null);
                @SuppressLint("PrivateApi")
                Object iNotificationManagerProxy = Proxy.newProxyInstance(
                        Thread.currentThread().getContextClassLoader(),
                        new Class[]{Class.forName("android.app.INotificationManager")},
                        new InvocationHandler() {

                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                if ("enqueueToast".equals(method.getName())) {
                                    args[0] = "android";
                                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                                        Object tn = args[1];
                                        Field mHandler = tn.getClass().getDeclaredField("mHandler");
                                        mHandler.setAccessible(true);
                                        final class HandlerProxy extends Handler {
                                            private final Handler mHandler;

                                            public HandlerProxy(Handler handler) {
                                                mHandler = handler;
                                            }

                                            @Override
                                            public void handleMessage(Message msg) {
                                                try {
                                                    mHandler.handleMessage(msg);
                                                } catch (WindowManager.BadTokenException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        mHandler.set(tn, new HandlerProxy((Handler) mHandler.get(tn)));
                                    }
                                }
                                return method.invoke(iNotificationManager, args);
                            }
                        });
                @SuppressWarnings("JavaReflectionMemberAccess")
                Field sService = Toast.class.getDeclaredField("sService");
                sService.setAccessible(true);
                sService.set(null, iNotificationManagerProxy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
