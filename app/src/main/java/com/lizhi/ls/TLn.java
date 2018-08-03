package com.lizhi.ls;

import com.lizhi.ls.config.ITLogGlobalConfig;
import com.lizhi.ls.config.TLogConfigCenter;
import com.lizhi.ls.inner.ITree;
import com.lizhi.ls.trees.SoulsTree;
import com.lizhi.ls.inner.Tree;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public final class TLn {
    private static final Tree TREE_OF_SOULS = new SoulsTree();
    private static final List<Tree> FOREST = new ArrayList<>();
    private static final TLogConfigCenter LOG_CONFIG_CENTER = TLogConfigCenter.getInstance();

    private TLn() {
        throw new AssertionError("No instances.");
    }

    // default config change
    public static ITLogGlobalConfig getLogConfigCenter(){
        return LOG_CONFIG_CENTER;
    }

    // temp tag use only once
    public static ITree tag(String tempTag){
        return ((SoulsTree)TREE_OF_SOULS).tag(tempTag);
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
            if (!FOREST.remove(tree)){
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

    public static void d(String message, Object... args) {
        TREE_OF_SOULS.d(message, args);
    }

    public static void d(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.d(t, message, args);
    }

    public static void d(Throwable t) {
        TREE_OF_SOULS.d(t);
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

    public static void w(String message, Object... args) {
        TREE_OF_SOULS.w(message, args);
    }

    public static void w(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.w(t, message, args);
    }

    public static void w(Throwable t) {
        TREE_OF_SOULS.w(t);
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

    public static void wtf(String message, Object... args) {
        TREE_OF_SOULS.wtf(message, args);
    }

    public static void wtf(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wtf(t, message, args);
    }

    public static void wtf(Throwable t) {
        TREE_OF_SOULS.wtf(t);
    }

    //log json
    public static void json(String json){
    }

    //log xml
    public static void xml(String xml){

    }
}
