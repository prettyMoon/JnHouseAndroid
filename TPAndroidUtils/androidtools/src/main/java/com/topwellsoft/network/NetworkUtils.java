package com.topwellsoft.network;

import android.widget.Toast;

import com.topwellsoft.androidutils.AppLoginInterface;
import com.topwellsoft.androidutils.GlobalObjects;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkUtils {

    public static AppLoginInterface loginHelper;

    public final static String reloginCmd = GlobalObjects.urlPrefix + "/globalApp/ckeckUpdate";


    public static void asyncGetSilent(final String serverApi, final AjaxParams params, final TpAjaxCallback callback) {
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        fn.get(serverApi, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(Object t) {
                try {
                    final JSONObject json = new JSONObject(t.toString());
                    if (json.getInt("code") < 0) {
                        // Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "操作失败.", Toast.LENGTH_SHORT).show();
                    } else
                        callback.onSuccess(json);
                } catch (Exception e) {
                }

            }

        });
    }

    public static void asyncGet(final String serverApi, final AjaxParams params, final TpAjaxCallback callback) {
        FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        fn.get(serverApi, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "网络通信失败.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(Object t) {
                try {
                    final JSONObject json = new JSONObject(t.toString());
                    if (json.getInt("code") < 0) {
                        Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "操作失败.", Toast.LENGTH_SHORT).show();
                    } else if (json.getInt("code") == 1) {
                        if (loginHelper != null) {
                            String uuid = loginHelper.silentLogin();
                            FinalHttp fn1 = new FinalHttp();
                            fn1.configRequestExecutionRetryCount(0);
                            params.put("userUUID", uuid);
                            fn1.get(serverApi, params, new AjaxCallBack<Object>() {
                                public void onSuccess(Object t) {
                                    callback.onSuccess(json);
                                }
                            });
                        }
                    } else
                        callback.onSuccess(json);
                } catch (Exception e) {
                }

            }

        });
    }

    public static void asyncPost(final String serverApi, final AjaxParams params, final TpAjaxCallback callback) {
        final FinalHttp fn = new FinalHttp();
        fn.configRequestExecutionRetryCount(0);
        fn.post(serverApi, params, new AjaxCallBack<Object>() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "网络通信失败.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(Object t) {
                try {
                    final JSONObject json = new JSONObject(t.toString());
                    if (json.getInt("code") < 0) {
                        Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "操作失败.", Toast.LENGTH_SHORT).show();
                    } else if (json.getInt("code") == 1) {
                        if (loginHelper != null) {
                            String uuid = loginHelper.silentLogin();

                            fn.configRequestExecutionRetryCount(0);
                            params.put("userUUID", uuid);
                            fn.post(serverApi, params, new AjaxCallBack<Object>() {
                                public void onSuccess(Object t2) {
                                    try {
                                        JSONObject json2 = new JSONObject(t2.toString());
                                        callback.onSuccess(json2);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                public void onFailure(Throwable t, int errorNo, String strMsg) {
                                    Toast.makeText(GlobalObjects.theApplication.getApplicationContext(), "操作失败.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else
                        callback.onSuccess(json);
                } catch (Exception e) {
                }

            }
        });

    }
}