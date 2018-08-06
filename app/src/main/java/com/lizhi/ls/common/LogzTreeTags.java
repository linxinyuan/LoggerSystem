package com.lizhi.ls.common;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.lizhi.ls.common.LogzTreeTags.TAG_CRASH;
import static com.lizhi.ls.common.LogzTreeTags.TAG_DEBUG;
import static com.lizhi.ls.common.LogzTreeTags.TAG_FILE;
import static com.lizhi.ls.common.LogzTreeTags.TAG_LIVE;

/**
 * Author : Create by Linxinyuan on 2018/08/06
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({TAG_DEBUG, TAG_FILE, TAG_CRASH, TAG_LIVE})
public @interface LogzTreeTags {
    String TAG_DEBUG = "LizhiFM_Debug";
    String TAG_CRASH = "LizhiFM_Crash";
    String TAG_FILE = "LizhiFM_File";
    String TAG_LIVE = "LizhiFM_Live";
}
