package com.example.j.xposehooktest;

import android.util.Log;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


/**
 * Created by J on 2017/2/2.
 */

/**
public class Module implements IXposedHookLoadPackage {
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app: " + lpparam.packageName);
    }
}
*/

public class Module implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.example.j.mytestapplication")) {

            XposedBridge.log("Yeah :) Loaded app: " + lpparam.packageName);

            findAndHookMethod("com.example.j.mytestapplication.MainActivity",
                    lpparam.classLoader,
                    "check",
                    String.class,
                    String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);
                            Log.d("zz","账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("zz", param.getResult().toString());
                        }
                    });

        }
    }
}