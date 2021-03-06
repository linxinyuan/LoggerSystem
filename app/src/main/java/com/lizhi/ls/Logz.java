package com.lizhi.ls;

import com.lizhi.ls.config.ILogzGlobalConfig;
import com.lizhi.ls.config.LogzConfigCenter;
import com.lizhi.ls.trees.ITree;
import com.lizhi.ls.trees.SoulsTree;
import com.lizhi.ls.base.Tree;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 日志输出工具类
 */
public final class Logz {
    private static final Tree TREE_OF_SOULS = new SoulsTree();
    private static final List<Tree> FOREST = new ArrayList<>();
    private static final LogzConfigCenter LOG_CONFIG_CENTER = LogzConfigCenter.getInstance();

    private Logz() {
        throw new AssertionError("No instances.");
    }

    // default config change
    public static ILogzGlobalConfig getLogConfigCenter() {
        return LOG_CONFIG_CENTER;
    }

    // temp tag use only once
    public static ITree tag(String tempTag) {
        return ((SoulsTree) TREE_OF_SOULS).tag(tempTag);
    }

    // add new log tree
    public static void plant(Tree tree) {
        if (tree == null) {
            throw new NullPointerException("tree == null");
        }
        if (tree == TREE_OF_SOULS) {
            throw new IllegalArgumentException("Cannot plant souls tree");
        }
        synchronized (FOREST) {
            FOREST.add(tree);
            ((SoulsTree) TREE_OF_SOULS).setForestAsArray(FOREST.toArray(new Tree[FOREST.size()]));
        }
    }

    // adds new log trees
    public static void plant(Tree... trees) {
        if (trees == null) {
            throw new NullPointerException("trees == null");
        }
        for (Tree tree : trees) {
            plant(tree);
        }
    }

    // remove log tree
    public static void uproot(Tree tree) {
        synchronized (FOREST) {
            if (!FOREST.remove(tree)) {
                throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
            }
            ((SoulsTree) TREE_OF_SOULS).setForestAsArray(FOREST.toArray(new Tree[FOREST.size()]));
        }
    }

    // remove all log tree
    public static void uprootAll() {
        synchronized (FOREST) {
            FOREST.clear();
            ((SoulsTree) TREE_OF_SOULS).setForestAsArray(new Tree[0]);
        }
    }

    // get log forset
    public static List<Tree> forset() {
        synchronized (FOREST) {
            return unmodifiableList(new ArrayList<>(FOREST));
        }
    }

    // get log tree count
    public static int treeCount() {
        synchronized (FOREST) {
            return FOREST.size();
        }
    }

    // return log root tree
    public static Tree asTree() {
        return TREE_OF_SOULS;
    }

    public static void v(String message, Object... args) {
        TREE_OF_SOULS.v(message, args);
    }

    public static void v(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.v(t, message, args);
    }

    public static void v(Throwable t) {
        TREE_OF_SOULS.v(t);
    }

    public static void v(Object o) {
        TREE_OF_SOULS.v(o);
    }

    public static void d(String message, Object... args) {
        TREE_OF_SOULS.d(message, args);
    }

    public static void d(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.d(t, message, args);
    }

    public static void d(Throwable t) {
        TREE_OF_SOULS.d(t);
    }

    public static void d(Object o) {
        TREE_OF_SOULS.d(o);
    }

    public static void i(String message, Object... args) {
        TREE_OF_SOULS.i(message, args);
    }

    public static void i(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.i(t, message, args);
    }

    public static void i(Throwable t) {
        TREE_OF_SOULS.i(t);
    }

    public static void i(Object o) {
        TREE_OF_SOULS.i(o);
    }

    public static void w(String message, Object... args) {
        TREE_OF_SOULS.w(message, args);
    }

    public static void w(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.w(t, message, args);
    }

    public static void w(Throwable t) {
        TREE_OF_SOULS.w(t);
    }

    public static void w(Object o) {
        TREE_OF_SOULS.w(o);
    }

    public static void e(String message, Object... args) {
        TREE_OF_SOULS.e(message, args);
    }

    public static void e(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.e(t, message, args);
    }

    public static void e(Throwable t) {
        TREE_OF_SOULS.e(t);
    }

    public static void e(Object o) {
        TREE_OF_SOULS.e(o);
    }

    public static void wtf(String message, Object... args) {
        TREE_OF_SOULS.wtf(message, args);
    }

    public static void wtf(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wtf(t, message, args);
    }

    public static void wtf(Throwable t) {
        TREE_OF_SOULS.wtf(t);
    }

    public static void wtf(Object o) {
        TREE_OF_SOULS.wtf(o);
    }

    //log json
    public static void json(String j) {
        TREE_OF_SOULS.json(j);
    }

    //log xml
    public static void xml(String x) {
        TREE_OF_SOULS.xml(x);
    }
}
